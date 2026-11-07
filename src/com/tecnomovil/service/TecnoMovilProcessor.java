package com.tecnomovil.service;

import com.tecnomovil.model.RegistroTransporte;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class TecnoMovilProcessor {

    public static Map<String, Long> calcularAfluenciaPorEstacion(List<RegistroTransporte> registros) {
        return registros.stream()
                .filter(r -> "entrada".equalsIgnoreCase(r.accion()))
                .collect(Collectors.groupingBy(
                        RegistroTransporte::estacion,
                        Collectors.counting()
                ));
    }

    public static Map<Integer, Long> identificarHorasPico(List<RegistroTransporte> registros) {
        return registros.parallelStream()
                .collect(Collectors.groupingBy(
                        r -> r.timestamp().getHour(),
                        Collectors.counting()
                ));
    }

    public static List<String> rutasMasUtilizadas(List<RegistroTransporte> registros) {
        Map<String, Long> conteo = registros.stream()
                .collect(Collectors.groupingBy(
                        RegistroTransporte::ruta,
                        Collectors.counting()
                ));

        return conteo.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(entry -> entry.getKey() + " (" + entry.getValue() + " viajes)")
                .toList();
    }

    public static Map<String, List<String>> patronesViajePorUsuario(List<RegistroTransporte> registros) {
        return registros.stream()
                .sorted(Comparator.comparing(RegistroTransporte::timestamp))
                .collect(Collectors.groupingBy(
                        RegistroTransporte::idUsuario,
                        Collectors.mapping(RegistroTransporte::estacion, Collectors.toList())
                ));
    }

    public static Map<String, Double> tiempoPromedioEntreEstaciones(List<RegistroTransporte> registros) {
        Map<String, List<RegistroTransporte>> porUsuario = registros.stream()
                .sorted(Comparator.comparing(RegistroTransporte::timestamp))
                .collect(Collectors.groupingBy(RegistroTransporte::idUsuario));

        Map<String, List<Long>> duracionesPorTramo = new HashMap<>();

        porUsuario.forEach((usuario, regs) -> {
            for (int i = 0; i < regs.size() - 1; i++) {
                RegistroTransporte actual = regs.get(i);
                RegistroTransporte siguiente = regs.get(i + 1);
                
                long minutos = Duration.between(actual.timestamp(), siguiente.timestamp()).toMinutes();
                String tramo = actual.estacion() + " -> " + siguiente.estacion();
                
                duracionesPorTramo.computeIfAbsent(tramo, k -> new ArrayList<>()).add(minutos);
            }
        });

        Map<String, Double> promedios = new HashMap<>();
        duracionesPorTramo.forEach((tramo, tiempos) -> {
            double promedio = tiempos.stream().mapToLong(Long::longValue).average().orElse(0.0);
            promedios.put(tramo, promedio);
        });

        return promedios;
    }

    public static List<String> detectarRutasCriticas(List<RegistroTransporte> registros, long umbral) {
        Map<String, Long> conteoRutas = registros.stream()
                .collect(Collectors.groupingBy(RegistroTransporte::ruta, Collectors.counting()));

        return conteoRutas.entrySet().stream()
                .filter(entry -> entry.getValue() > umbral)
                .map(Map.Entry::getKey)
                .toList();
    }
}