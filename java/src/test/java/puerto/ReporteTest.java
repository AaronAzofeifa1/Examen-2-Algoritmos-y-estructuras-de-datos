package puerto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReporteTest {
    @Test void ordenaPorCantidadYDesempataPorCodigo() {
        Directorio d = new Directorio();
        Parada z = new Parada("Z","Plaza","Sur",50);
        Parada a = new Parada("A","Faro","Costa",50);
        Parada b = new Parada("B","Parque","Sur",70);
        d.agregar(z);
        d.agregar(a);
        d.agregar(b);
        assertArrayEquals(new Parada[]{b,a,z}, d.reporte());
        d.reporte()[0] = null;
        assertEquals(b, d.reporte()[0]);
    }
    @Test void admiteMasDeMilDoscientasParadas() {
        Directorio d = new Directorio();
        for (int i = 0; i < 1250; i++)
            d.agregar(new Parada("S"+i, "Parada "+i, "Centro", i));
        Parada[] filas = d.reporte();
        assertEquals(1250, filas.length);
        for (int i = 0; i < filas.length; i++)
            assertEquals(1249-i, filas[i].abordajes());
    }
}
