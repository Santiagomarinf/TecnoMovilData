package com.tecnomovil;

import com.tecnomovil.model.RegistroTransporte;
import com.tecnomovil.service.TecnoMovilProcessor;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("=== SISTEMA TECNOMOVIL DATA - PROCESAMIENTO FUNCIONAL ===");
        System.out.println("==========================================================");
        
        List<RegistroTransporte> lista = List.of(
            new RegistroTransporte("U001", "Ruta A", "Estacion Central", "entrada", LocalDateTime.of(2026, 6, 6, 7, 30)),
            new RegistroTransporte("U001", "Ruta A", "Estacion Norte", "salida", LocalDateTime.of(2026, 6, 6, 8, 0)),
            new RegistroTransporte("U002", "Ruta B", "Estacion Sur", "entrada", LocalDateTime.of(2026, 6, 6, 7, 45)),
            new RegistroTransporte("U003", "Ruta A", "Estacion Central", "entrada", LocalDateTime.of(2026, 6, 6, 8, 15)),
            new RegistroTransporte("U002", "Ruta B", "Estacion Central", "salida", LocalDateTime.of(2026, 6, 6, 8, 30))
        );

        System.out.println("\na) Afluencia por Estación (Solo Entradas):");
        TecnoMovilProcessor.calcularAfluenciaPorEstacion(lista)
                .forEach((estacion, total) -> System.out.println("  - " + estacion + ": " + total + " personas"));

        System.out.println("\nb) Identificación de Horas Pico:");
        TecnoMovilProcessor.identificarHorasPico(lista)
                .forEach((hora, total) -> System.out.println("  - Hora " + hora + ":00 hs: " + total + " registros"));

        System.out.println("\nc) Rutas Más Utilizadas:");
        TecnoMovilProcessor.rutasMasUtilizadas(lista)
                .forEach(ruta -> System.out.println("  - " + ruta));

        System.out.println("\nd) Patrones de Viaje por Usuario:");
        TecnoMovilProcessor.patronesViajePorUsuario(lista)
                .forEach((user, estaciones) -> System.out.println("  - Usuario " + user + ": " + estaciones));

        System.out.println("\ne) Tiempo Promedio entre Estaciones (Minutos):");
        TecnoMovilProcessor.tiempoPromedioEntreEstaciones(lista)
                .forEach((tramo, prom) -> System.out.println("  - Tramo " + tramo + ": " + prom + " min"));

        System.out.println("\nf) Detección de Rutas Críticas (Umbral > 1):");
        TecnoMovilProcessor.detectarRutasCriticas(lista, 1)
                .forEach(ruta -> System.out.println("  - Alerta Ruta Crítica: " + ruta));
    }
}