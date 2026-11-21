package puerto;

/** Minutos totales y tramos en orden de viaje. -1 indica destino inalcanzable. */
public record Ruta(long minutos, Paso[] pasos) {}
