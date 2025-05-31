# Simulador Publicador/Suscriptor - ELO329

Este proyecto implementa un simulador basado en el patrón Publicador/Suscriptor usando Java y JavaFX. Se desarrollaron todas las etapas requeridas por la tarea, incluyendo el extra-crédito solicitado.

## Etapas desarrolladas

- **Etapa 1**: Creación de publicadores y suscriptores simples (video y tópico).
- **Etapa 2**: Reproducción de video con publicador de URL y suscriptor visual.
- **Etapa 3**: Publicador de coordenadas GPS desde archivo y seguimiento con `CarTracker`.
- **Etapa 4**: Visualización animada del móvil en movimiento con `CarTracker`, incluyendo ventana con `Circle` que sigue las coordenadas.

### Extra-crédito
Se implementó un `ComboBox` en la clase `VideoFollower`:
- Muestra URLs disponibles
- Se actualiza con cada mensaje recibido
- Reproduce automáticamente el video seleccionado usando `MediaPlayer`

## Estructura del proyecto

```
src/
├── Broker.java
├── Publisher.java
├── Subscriber.java
├── Topic.java
├── VideoPublisher.java
├── VideoFollower.java
├── GPSCarPublisher.java
├── CarTracker.java
└── PubSubsPatternSimulator.java
```

## Requisitos

- JDK 17 o superior (probado con JDK 24)
- JavaFX 21+
- IntelliJ IDEA (recomendado)

## Cómo ejecutar

1. Clonar el repositorio:
```
git clone <https://github.com/RexxaRtlb/tarea2-etapa1>
```

2. Abrir en IntelliJ
3. Agregar JavaFX a las dependencias
4. Ejecutar `PubSubsPatternSimulator.java`

## Documentación

Para generar documentación con Javadoc desde IntelliJ:
```
Tools > Generate JavaDoc...
```

O desde terminal:
```
javadoc -d doc *.java
```

## Enlace al repositorio

> Reemplazar por el URL real:
```
https://github.com/RexxaRtlb/tarea2-etapa1.git
```

---

## Autor

- Nombre: [Sebastian Moya]
- Curso: ELO329 - 1er Semestre 2025
