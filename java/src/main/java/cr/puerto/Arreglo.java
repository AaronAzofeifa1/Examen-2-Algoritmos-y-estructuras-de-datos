package puerto;

/** Secuencia respaldada exclusivamente por un arreglo que duplica su capacidad. */
final class Arreglo<T> {
    private Object[] datos = new Object[8];
    private int longitud;

    int cantidad() { return longitud; }

    @SuppressWarnings("unchecked")
    T obtener(int i) {
        if (i < 0 || i >= longitud) throw new IndexOutOfBoundsException(i);
        return (T) datos[i];
    }

    void agregar(T valor) { insertar(longitud, valor); }

    void insertar(int i, T valor) {
        if (i < 0 || i > longitud) throw new IndexOutOfBoundsException(i);
        if (longitud == datos.length) {
            Object[] nuevo = new Object[longitud * 2];
            System.arraycopy(datos, 0, nuevo, 0, longitud);
            datos = nuevo;
        }
        System.arraycopy(datos, i, datos, i + 1, longitud - i);
        datos[i] = valor;
        longitud++;
    }
}
