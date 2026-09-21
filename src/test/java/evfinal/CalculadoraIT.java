package evfinal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculadoraIT {
    @Test
    public void testIntegracionServicio() {
        Calculadora calc = new Calculadora();
        assertTrue(calc.verificarServicio(), "El servicio de integración debe estar activo");
    }
}