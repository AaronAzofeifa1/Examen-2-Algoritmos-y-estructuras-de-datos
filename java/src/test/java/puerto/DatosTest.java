package puerto;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DatosTest {
    @Test void csvDelExamenAlimentanLosTresModulos() throws Exception {
        Directorio d = new Directorio();
        Planificador red = new Planificador(d);
        Datos.cargar(Path.of("datos"), d, red);
        assertEquals(7835L, d.totalCiudad());
        assertEquals(3810L, d.totalZona("Centro"));
        String[] esperado = {"S01","S03","S08","S02","S05","S04","S07","S06"};
        Parada[] filas = d.reporte();
        assertEquals(esperado.length,filas.length);
        for (int i=0; i<filas.length; i++) assertEquals(esperado[i],filas[i].codigo());
        Ruta r = red.ruta("S01","S03");
        assertEquals(11L,r.minutos());
        assertEquals("Bus 4",r.pasos()[2].medio());
        assertEquals(10L,red.ruta("S06","S02").minutos());
    }
}
