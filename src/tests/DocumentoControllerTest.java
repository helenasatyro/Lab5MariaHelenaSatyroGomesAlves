package tests;

import documin.documento.DocumentoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DocumentoControllerTest {

    private DocumentoController dcUm;
    private DocumentoController dcDois;

    @BeforeEach
    void setUp() {
        this.dcUm = new DocumentoController();
        this.dcDois = new DocumentoController();
        dcDois.criarDocumento("Doc1", 3);

    }

    @Test
    void testaCriarDocIlimitado() {
        assertTrue(dcUm.criarDocumento("Doc1"));
        assertEquals("[]", Arrays.toString(dcUm.exibeDocumento("Doc1")));
    }

    @Test
    void testaCriarDocLimitado() {
        assertTrue(dcUm.criarDocumento("Doc1", 5));
        assertEquals("[]", Arrays.toString(dcUm.exibeDocumento("Doc1")));
    }
    @Test
    void testaCriarDocNomeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {dcUm.criarDocumento("");});
        assertThrows(IllegalArgumentException.class, () -> {dcUm.criarDocumento("", 1);});
        assertThrows(IllegalArgumentException.class, () -> {dcUm.criarDocumento("    ");});
        assertThrows(IllegalArgumentException.class, () -> {dcUm.criarDocumento("    ", 1);});
    }

    @Test
    void testaCriarDocRepetido() {
        assertTrue(dcUm.criarDocumento("Doc1", 5));
        assertFalse(dcUm.criarDocumento("Doc1"));
        assertFalse(dcUm.criarDocumento("Doc1", 5));
    }
    @Test
    void testaCriarDocTamanhoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> dcUm.criarDocumento("Doc1", 0));
        assertThrows(IllegalArgumentException.class, () -> dcUm.criarDocumento("Doc1", -5));
    }
    @Test
    void testaCriarDocs() {
        assertTrue(dcUm.criarDocumento("Doc1", 5));
        assertTrue(dcUm.criarDocumento("Doc2", 5));
    }

    @Test
    void testaCriaElementoNormal() {
        dcDois.criarDocumento("Doc2", 3);
        assertEquals(0, dcDois.criarTexto("Doc2", "Texto texto", 1 ));
    }

    @Test
    void testaCriaElementoLimite() {
        dcDois.criarDocumento("Doc2", 3);
        assertEquals(0, dcDois.criarTexto("Doc2", "Texto texto", 1 ));
        assertEquals(1, dcDois.criarTexto("Doc2", "Texto texto", 1 ));
        assertEquals(2, dcDois.criarTexto("Doc2", "Texto texto", 1 ));
    }
    @Test
    void testaCriaElementoAcimaLimite() {
        dcDois.criarDocumento("Doc2", 3);
        assertEquals(0, dcDois.criarTexto("Doc2", "Texto texto", 1 ));
        assertEquals(1, dcDois.criarTexto("Doc2", "Texto texto", 1 ));
        assertEquals(2, dcDois.criarTexto("Doc2", "Texto texto", 1 ));
        assertThrows(IllegalStateException.class, () -> dcDois.criarTexto("Doc2", "Texto texto", 1 ));
    }

    @Test
    void testaCriaElementoParamInvalido() {
        dcDois.criarDocumento("Doc2", 3);
        assertThrows(IllegalArgumentException.class, () -> dcDois.criarTexto("", "Texto texto", 1 ));
        assertThrows(NoSuchElementException.class, () -> dcDois.criarTexto("DocX", "Texto texto", 1 ));

    }

    @Test
    void testaVerQuantidadeElementos() {
        dcDois.criarDocumento("Doc2", 3);
        assertEquals(0, dcDois.getNumeroElementos("Doc2"));
        dcDois.criarTexto("Doc2", "Texto texto", 1 );
        assertEquals(1, dcDois.getNumeroElementos("Doc2"));
        dcDois.criarTexto("Doc2", "Texto texto", 1 );
        assertEquals(2, dcDois.getNumeroElementos("Doc2"));
    }
    @Test
    void testaVerQuantidadeElementosParamInvalido() {
        assertThrows(NoSuchElementException.class, () -> dcDois.getNumeroElementos("DocInexistente"));
        assertThrows(IllegalArgumentException.class, () -> dcDois.getNumeroElementos(""));
        assertThrows(IllegalArgumentException.class, () -> dcDois.getNumeroElementos("  "));
    }
    @Test
    void testaRemoverDoc() {
        dcDois.criarDocumento("Doc2", 3);
        dcDois.criarTexto("Doc2", "Texto texto", 1 );
        dcDois.criarTexto("Doc2", "Texto texto", 1 );
        dcDois.removeDocumento("Doc2");
        assertThrows(NoSuchElementException.class, () -> dcDois.exibeDocumento("Doc2"));
    }
    @Test
    void testaRemoverDocParametroInvalido() {
        assertThrows(IllegalArgumentException.class, () -> dcDois.exibeDocumento(""));
        assertThrows(IllegalArgumentException.class, () -> dcDois.exibeDocumento("   "));
    }
    @Test
    void testaRemoverDocInexistente() {
        dcDois.criarDocumento("Doc2", 3);
        assertThrows(NoSuchElementException.class, () -> dcDois.removeDocumento("Doc3"));
    }

    @Test
    void testaExibirDocumento() {
        dcDois.criarDocumento("Doc2", 3);
        assertEquals("[]", Arrays.toString(dcDois.exibeDocumento("Doc2")));
        assertEquals(0, dcDois.exibeDocumento("Doc2").length);
        dcDois.criarTexto("Doc2", "Texto texto", 1 );
        assertEquals("[Texto texto]", Arrays.toString(dcDois.exibeDocumento("Doc2")));
        assertEquals(1, dcDois.exibeDocumento("Doc2").length);
        dcDois.criarTexto("Doc2", "Mais Texto", 1 );
        assertEquals("[Texto texto, Mais Texto]", Arrays.toString(dcDois.exibeDocumento("Doc2")));
        assertEquals(2, dcDois.exibeDocumento("Doc2").length);
    }
    @Test
    void testaExibirDocumentoParamInvalido() {
        dcDois.criarDocumento("Doc2", 3);
        assertThrows(NoSuchElementException.class, () -> dcDois.exibeDocumento("DocInexistente"));
        assertThrows(IllegalArgumentException.class, () -> dcDois.exibeDocumento(""));
        assertThrows(IllegalArgumentException.class, () -> dcDois.exibeDocumento("   "));
    }
}