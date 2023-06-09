package documin.tests;
import documin.documento.ElementoTitulo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoTituloTest {
    @Test
    void testaRepresentacaoLinkavel() {
        ElementoTitulo el = new ElementoTitulo("Titulo", 5, 2, true);
        assertEquals("2. Titulo -- 2-TITULO\n", el.representacaoCompleta());
        assertEquals("2. Titulo\n", el.representacaoCurta());
    }
    @Test
    void testaRepresentacaoNaoLinkavel() {
        ElementoTitulo el = new ElementoTitulo("Titulo", 5, 2, false);
        assertEquals("2. Titulo\n", el.representacaoCompleta());
        assertEquals("2. Titulo\n", el.representacaoCurta());
    }
}