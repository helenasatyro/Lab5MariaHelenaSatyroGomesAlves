package documin.tests;

import documin.documento.ElementoLista;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoListaTest {

    @Test
    void testaRepresentacaoCompleta() {
        ElementoLista el = new ElementoLista("termos - da - lista", 1, "-", "*");
        assertEquals("""
                * termos\s
                *  da\s
                *  lista
                """, el.representacaoCompleta());
    }
    @Test
    void testaRepresentacaoCurta() {
        ElementoLista el = new ElementoLista("termos | da | lista", 1, "|", "*");
        assertEquals("termos ,  da ,  lista\n", el.representacaoCurta());
    }

}