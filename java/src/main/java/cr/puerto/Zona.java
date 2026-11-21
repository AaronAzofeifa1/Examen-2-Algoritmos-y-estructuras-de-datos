package puerto;

final class Zona {
    final String nombre;
    final Arreglo<Parada> paradas = new Arreglo<>();

    Zona(String nombre) { this.nombre = nombre; }

    long total() {
        long suma = 0;
        for (int i = 0; i < paradas.cantidad(); i++) suma += paradas.obtener(i).abordajes();
        return suma;
    }
}
