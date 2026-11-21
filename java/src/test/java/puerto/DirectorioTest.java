package puerto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectorioTest {
    final Directorio d = new Directorio();
    final Parada a = new Parada("A", "Muelle", "Costa", 18);
    final Parada b = new Parada("B", "Faro", "Costa", 7);

    @Test void ciudadVaciaTieneNombreYTotal() {
        assertEquals("Puerto Celeste", d.ciudad());
        assertEquals(0L, d.totalCiudad());
    }
    @Test void separaZonasYConservaOrdenDeAlta() {
        d.agregar(a);
        d.agregar(new Parada("C", "Mercado", "Centro", 30));
        d.agregar(b);
        assertArrayEquals(new Parada[]{a,b}, d.listar("Costa"));
        assertEquals(25L, d.totalZona("Costa"));
        assertEquals(55L, d.totalCiudad());
    }
    @Test void duplicadoNoModificaElDirectorio() {
        d.agregar(a);
        assertThrows(IllegalArgumentException.class,
            () -> d.agregar(new Parada("A", "Otra", "Norte", 90)));
        assertEquals(18L, d.totalCiudad());
        assertEquals(1, d.reporte().length);
    }
    @Test void zonaDesconocidaProduceError() {
        assertThrows(IllegalArgumentException.class, () -> d.listar("Otra"));
    }
    @Test void validaCampos() {
        assertThrows(IllegalArgumentException.class, () -> new Parada("7", "X", "Y", 1));
        assertThrows(IllegalArgumentException.class, () -> new Parada("Z", "X", " ", 1));
        assertThrows(IllegalArgumentException.class, () -> new Parada("Z", "X", "Y", -1));
    }
}
