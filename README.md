# TecnoMovil Data - Análisis de Datos de Transporte

Proyecto desarrollado para la materia de Programación Orientada a Objetos II. Consiste en un sistema de análisis sobre datos de transporte urbano implementando técnicas de programación funcional en Java.

## Descripción del Proyecto

El sistema procesa conjuntos de datos inmutables para responder a 6 métricas analíticas sobre el flujo de pasajeros y movilidad:

1. **Afluencia de pasajeros:** Cálculo del volumen total y desglose por estaciones.
2. **Horas pico:** Identificación de los intervalos con mayor demanda de usuarios.
3. **Filtro de rutas:** Análisis y ordenamiento de los trayectos con mayor recorrido.
4. **Patrones de uso:** Clasificación y agrupación de tipos de viaje según frecuencia.
5. **Tiempos de viaje:** Medición de promedios de duración y cálculo de retrasos.
6. **Tramos críticos:** Detección de sectores de alta congestión para la toma de decisiones.

## Aspectos Técnicos

* **Paradigma Funcional:** Aplicación de funciones puras, inmutabilidad de objetos y procesamiento declarativo.
* **Colecciones Inmutables:** Uso de datos estáticos precargados (`List.of`) para optimizar las pruebas y la generación de evidencias.
* **Herramientas de Java:** Java Records, API de Streams y Expresiones Lambda.
* **IDE y Control de Versiones:** Desarrollado en Visual Studio Code y gestionado mediante Git/GitHub.

## Estructura del Código

* `src/com/tecnomovil/model/`: Contiene los Records que definen las estructuras inmutables de datos.
* `src/com/tecnomovil/service/`: Contiene la lógica del negocio y las consultas funcionales con Streams.
* `src/com/tecnomovil/Main.java`: Punto de entrada del programa donde se ejecutan y muestran las 6 analíticas.

## Instrucciones para Ejecutar

1. Clonar el repositorio:
   git clone https://github.com/Santiagomarinf/TecnoMovilData.git

2. Abrir la carpeta del proyecto en Visual Studio Code (o cualquier IDE con soporte para Java 17+).

3. Ejecutar la clase principal desde la terminal o el entorno:
   `src/com/tecnomovil/Main.java`
