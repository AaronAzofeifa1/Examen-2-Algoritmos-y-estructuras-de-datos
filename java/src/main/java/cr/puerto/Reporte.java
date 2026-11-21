package puerto;

final class Reporte {
    private final Arreglo<Parada> filas = new Arreglo<>();

    void incorporar(Parada nueva) {
        int inicio = 0, fin = filas.cantidad();
        while (inicio < fin) {
            int medio = inicio + (fin - inicio) / 2;
            Parada actual = filas.obtener(medio);
            boolean vaAntes = actual.abordajes() > nueva.abordajes()
                    || (actual.abordajes() == nueva.abordajes()
                    && actual.codigo().compareTo(nueva.codigo()) < 0);
            if (vaAntes) inicio = medio + 1;
            else fin = medio;
        }
        filas.insertar(inicio, nueva);
    }

    Parada[] listar() {
        Parada[] copia = new Parada[filas.cantidad()];
        for (int i = 0; i < copia.length; i++) copia[i] = filas.obtener(i);
        return copia;
    }
}
