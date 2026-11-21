package puerto;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class Datos {
    static void cargar(Path carpeta, Directorio directorio, Planificador red) throws IOException {
        leer(carpeta.resolve("abordajes.csv"), "codigo,parada,zona,abordajes",
            c -> directorio.agregar(new Parada(c[0], c[1], c[2], Integer.parseInt(c[3]))));
        leer(carpeta.resolve("tramos.csv"), "linea,desde,hacia,minutos",
            c -> red.conectar(c[0], directorio.codigoDeNombre(c[1]),
                              directorio.codigoDeNombre(c[2]), Integer.parseInt(c[3])));
    }

    private interface Fila { void aceptar(String[] campos); }

    private static void leer(Path archivo, String cabecera, Fila procesar) throws IOException {
        try (BufferedReader entrada = Files.newBufferedReader(archivo)) {
            if (!cabecera.equals(entrada.readLine()))
                throw new IOException("Cabecera incorrecta en " + archivo);
            String linea;
            int numero = 1;
            while ((linea = entrada.readLine()) != null) {
                numero++;
                if (linea.isBlank()) continue;
                String[] c = linea.split(",", -1);
                try {
                    if (c.length != 4) throw new IllegalArgumentException("Se esperan cuatro columnas.");
                    for (int i = 0; i < c.length; i++) c[i] = c[i].strip();
                    procesar.aceptar(c);
                } catch (IllegalArgumentException e) {
                    throw new IOException(archivo + ", fila " + numero + ": " + e.getMessage(), e);
                }
            }
        }
    }
}
