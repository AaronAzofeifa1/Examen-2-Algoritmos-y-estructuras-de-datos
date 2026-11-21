package puerto;

public final class Planificador {
    static final String CAMINATA = "Caminata (ambos sentidos)";
    private record Arista(int destino, Paso paso) {}
    private final Arreglo<Arreglo<Arista>> salidas = new Arreglo<>();
    private final Directorio directorio;

    public Planificador(Directorio directorio) { this.directorio = directorio; }

    public void conectar(String medio, String desde, String hacia, int minutos) {
        boolean caminar = CAMINATA.equals(medio);
        if (!caminar && (medio == null || !medio.matches("Bus [1-4]")))
            throw new IllegalArgumentException("Medio desconocido: " + medio);
        if (minutos < 0) throw new IllegalArgumentException("Duración negativa.");
        int a = directorio.indice(desde), b = directorio.indice(hacia);
        while (salidas.cantidad() < directorio.cantidad()) salidas.agregar(new Arreglo<>());
        salidas.obtener(a).agregar(new Arista(b, new Paso(medio, desde, hacia, minutos)));
        if (caminar) salidas.obtener(b).agregar(new Arista(a, new Paso(medio, hacia, desde, minutos)));
    }

    public Ruta ruta(String origen, String destino) {
        int inicio = directorio.indice(origen), fin = directorio.indice(destino);
        int n = directorio.cantidad();
        long[] tiempos = new long[n];
        int[] anterior = new int[n];
        Paso[] llegada = new Paso[n];
        boolean[] cerrado = new boolean[n];
        for (int i = 0; i < n; i++) tiempos[i] = Long.MAX_VALUE;
        tiempos[inicio] = 0;
        while (true) {
            int actual = masCercano(tiempos, cerrado);
            if (actual < 0 || actual == fin) break;
            cerrado[actual] = true;
            if (actual >= salidas.cantidad()) continue;
            Arreglo<Arista> vecinas = salidas.obtener(actual);
            for (int i = 0; i < vecinas.cantidad(); i++) {
                Arista arista = vecinas.obtener(i);
                int siguiente = arista.destino();
                long propuesta = tiempos[actual] + arista.paso().minutos();
                if (propuesta < tiempos[siguiente]) {
                    tiempos[siguiente] = propuesta;
                    anterior[siguiente] = actual;
                    llegada[siguiente] = arista.paso();
                }
            }
        }
        if (tiempos[fin] == Long.MAX_VALUE) return new Ruta(-1, new Paso[0]);
        int largo = 0;
        for (int v = fin; v != inicio; v = anterior[v]) largo++;
        Paso[] pasos = new Paso[largo];
        for (int v = fin; v != inicio; v = anterior[v]) pasos[--largo] = llegada[v];
        return new Ruta(tiempos[fin], pasos);
    }

    private static int masCercano(long[] tiempos, boolean[] cerrado) {
        long minimo = Long.MAX_VALUE;
        int elegido = -1;
        for (int i = 0; i < tiempos.length; i++) {
            if (!cerrado[i] && tiempos[i] < minimo) {
                minimo = tiempos[i];
                elegido = i;
            }
        }
        return elegido;
    }
}
