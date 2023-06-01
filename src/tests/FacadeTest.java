package tests;

import documin.documento.DocumentoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FacadeTest {

    private DocumentoController dc;

    @BeforeEach
    void setUp() {
        this.dc = new DocumentoController();
    }

    @Test
    void testaCriarDocIlimitado() {
        assertTrue(dc.criarDocumento("Doc1"));
        assertEquals(dc.exibeDocumento("Doc1"), "");
    }
}