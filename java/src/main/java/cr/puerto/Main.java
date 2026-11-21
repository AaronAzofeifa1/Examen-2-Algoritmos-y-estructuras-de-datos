package puerto;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public final class Main {
    public static void main(String[] args) throws IOException {
        Directorio d = new Directorio();
        Planificador red = new Planificador(d);
        Datos.cargar(Path.of(args.length == 0 ? "datos" : args[0]), d, red);
        System.out.println(d.ciudad());
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("1 Agregar parada | 2 Listar zona | 3 Total zona | 4 Total ciudad"
                    + " | 5 Reporte | 6 Planificar viaje | 0 Salir");
                try {
                    switch (pedir(entrada, "Opción")) {
                        case "0" -> { return; }
                        case "1" -> {
                            d.agregar(new Parada(pedir(entrada,"Código"), pedir(entrada,"Nombre"),
                                pedir(entrada,"Zona"), Integer.parseInt(pedir(entrada,"Abordajes diarios"))));
                            System.out.println("Parada agregada.");
                        }
                        case "2" -> imprimir(d.listar(pedir(entrada,"Zona")));
                        case "3" -> System.out.println(d.totalZona(pedir(entrada,"Zona")) + " abordajes");
                        case "4" -> System.out.println(d.totalCiudad() + " abordajes");
                        case "5" -> imprimir(d.reporte());
                        case "6" -> {
                            Ruta ruta = red.ruta(pedir(entrada,"Código de salida"), pedir(entrada,"Código de destino"));
                            imprimirRuta(ruta,d);
                        }
                        default -> System.out.println("Opción desconocida.");
                    }
                } catch (EOFException e) {
                    return;
                } catch (IllegalArgumentException e) {
                    System.out.println("Entrada inválida: " + e.getMessage());
                }
            }
        }
    }

    private static String pedir(BufferedReader entrada, String campo) throws IOException {
        System.out.print(campo + "> ");
        String valor = entrada.readLine();
        if (valor == null) throw new EOFException();
        return valor.strip();
    }

    private static void imprimir(Parada[] paradas) {
        System.out.println("Código | Parada | Zona | Abordajes");
        for (Parada p : paradas)
            System.out.printf("%s | %s | %s | %d%n", p.codigo(), p.nombre(), p.zona(), p.abordajes());
    }

    private static void imprimirRuta(Ruta ruta, Directorio d) {
        if (ruta.minutos() < 0) {
            System.out.println("Sin ruta disponible.");
            return;
        }
        long acumulado = 0;
        for (Paso p : ruta.pasos()) {
            acumulado += p.minutos();
            String desde = d.vertice(d.indice(p.desde())).nombre();
            String hacia = d.vertice(d.indice(p.hacia())).nombre();
            System.out.printf("%s | %s → %s | %d min | llegada a %d min de la salida%n",
                p.medio(), desde, hacia, p.minutos(), acumulado);
        }
        System.out.println("Total: " + ruta.minutos() + " min");
    }
}
