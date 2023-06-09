package documin.tests;

import documin.documento.Elemento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoTest {
    @Test
    void testaRepresentacaoCompleta() {
        Elemento el = new Elemento(2,"Texto");
        assertEquals("Texto\n", el.representacaoCompleta());
    }
    @Test
    void testaRepresentacaoCurta() {
        Elemento el = new Elemento(2,"Texto");
        assertEquals("Texto\n", el.representacaoCurta());
    }
}