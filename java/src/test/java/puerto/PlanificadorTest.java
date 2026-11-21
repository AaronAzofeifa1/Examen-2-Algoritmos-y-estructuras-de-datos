package puerto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlanificadorTest {
    final Directorio d = new Directorio();
    final Planificador red = new Planificador(d);
    PlanificadorTest() {
        for (String codigo : new String[]{"A","B","C","D"})
            d.agregar(new Parada(codigo, codigo, "Centro", 0));
    }
    @Test void minimizaMinutosYReconstruyeTransbordoYCaminata() {
        red.conectar("Bus 1","A","D",20);
        red.conectar("Bus 2","A","B",3);
        red.conectar("Caminata (ambos sentidos)","C","B",2);
        red.conectar("Bus 4","C","D",4);
        Ruta r = red.ruta("A","D");
        assertEquals(9L, r.minutos());
        assertArrayEquals(new Paso[]{
            new Paso("Bus 2","A","B",3), new Paso("Caminata (ambos sentidos)","B","C",2),
            new Paso("Bus 4","C","D",4)}, r.pasos());
    }
    @Test void busNoCreaRegreso() {
        red.conectar("Bus 3","A","B",3);
        Ruta r = red.ruta("B","A");
        assertEquals(-1L,r.minutos());
        assertEquals(0,r.pasos().length);
    }
    @Test void mismoOrigenProduceCero() {
        assertEquals(0L,red.ruta("C","C").minutos());
        assertEquals(0,red.ruta("C","C").pasos().length);
    }
    @Test void rechazaDatosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> red.conectar("Bus 5","A","B",2));
        assertThrows(IllegalArgumentException.class, () -> red.conectar("Bus 1","A","B",-1));
        assertThrows(IllegalArgumentException.class, () -> red.ruta("A","X"));
    }
}
