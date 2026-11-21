package puerto;

public final class Directorio {
    private final Arreglo<Zona> zonas = new Arreglo<>();
    private final Arreglo<Parada> vertices = new Arreglo<>();
    private final Reporte reporte = new Reporte();

    public String ciudad() { return "Puerto Celeste"; }

    public void agregar(Parada parada) {
        if (buscarCodigo(parada.codigo()) >= 0)
            throw new IllegalArgumentException("Código repetido: " + parada.codigo());
        Zona zona = buscarZona(parada.zona());
        if (zona == null) {
            zona = new Zona(parada.zona());
            zonas.agregar(zona);
        }
        zona.paradas.agregar(parada);
        vertices.agregar(parada);
        reporte.incorporar(parada);
    }

    private Zona buscarZona(String nombre) {
        for (int i = 0; i < zonas.cantidad(); i++)
            if (zonas.obtener(i).nombre.equals(nombre)) return zonas.obtener(i);
        return null;
    }

    private Zona exigirZona(String nombre) {
        Zona zona = buscarZona(nombre);
        if (zona == null) throw new IllegalArgumentException("Zona desconocida: " + nombre);
        return zona;
    }

    public Parada[] listar(String zona) {
        Arreglo<Parada> hijas = exigirZona(zona).paradas;
        Parada[] salida = new Parada[hijas.cantidad()];
        for (int i = 0; i < salida.length; i++) salida[i] = hijas.obtener(i);
        return salida;
    }

    public long totalZona(String zona) { return exigirZona(zona).total(); }

    public long totalCiudad() {
        long total = 0;
        for (int i = 0; i < zonas.cantidad(); i++) total += zonas.obtener(i).total();
        return total;
    }

    public Parada[] reporte() { return reporte.listar(); }

    int cantidad() { return vertices.cantidad(); }
    Parada vertice(int i) { return vertices.obtener(i); }

    private int buscarCodigo(String codigo) {
        for (int i = 0; i < cantidad(); i++)
            if (vertice(i).codigo().equals(codigo)) return i;
        return -1;
    }
    int indice(String codigo) {
        int i = buscarCodigo(codigo);
        if (i < 0) throw new IllegalArgumentException("Código desconocido: " + codigo);
        return i;
    }
    String codigoDeNombre(String nombre) {
        String codigo = null;
        for (int i = 0; i < cantidad(); i++) {
            if (vertice(i).nombre().equals(nombre)) {
                if (codigo != null) throw new IllegalArgumentException("Nombre ambiguo: " + nombre);
                codigo = vertice(i).codigo();
            }
        }
        if (codigo == null) throw new IllegalArgumentException("Parada desconocida: " + nombre);
        return codigo;
    }
}
