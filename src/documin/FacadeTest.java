package documin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FacadeTest {
    private Facade facade;
    @BeforeEach
    void setUp() {
        this.facade = new Facade();
    }
    @Test
    void testaCriarDocIlimitado() {
        assertTrue(facade.criarDocumento("Doc1"));
        assertEquals("[]", Arrays.toString(facade.exibirDocumento("Doc1")));
    }

    @Test
    void testaCriarDocLimitado() {
        assertTrue(facade.criarDocumento("Doc1", 5));
        assertEquals("[]", Arrays.toString(facade.exibirDocumento("Doc1")));
    }
    @Test
    void testaCriarDocNomeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {facade.criarDocumento("");});
        assertThrows(IllegalArgumentException.class, () -> {facade.criarDocumento("", 1);});
        assertThrows(IllegalArgumentException.class, () -> {facade.criarDocumento("    ");});
        assertThrows(IllegalArgumentException.class, () -> {facade.criarDocumento("    ", 1);});
    }

    @Test
    void testaCriarDocRepetido() {
        assertTrue(facade.criarDocumento("Doc1", 5));
        assertFalse(facade.criarDocumento("Doc1"));
        assertFalse(facade.criarDocumento("Doc1", 5));
    }
    @Test
    void testaCriarDocTamanhoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> facade.criarDocumento("Doc1", 0));
        assertThrows(IllegalArgumentException.class, () -> facade.criarDocumento("Doc1", -5));
    }
    @Test
    void testaCriarDocs() {
        assertTrue(facade.criarDocumento("Doc1", 5));
        assertTrue(facade.criarDocumento("Doc2", 5));
    }

    @Test
    void testaCriaElementoNormal() {
        facade.criarDocumento("Doc2", 3);
        assertEquals(0, facade.criarTexto("Doc2", "Texto texto", 1 ));
    }

    @Test
    void testaCriaElementoLimite() {
        facade.criarDocumento("Doc2", 3);
        assertEquals(0, facade.criarTexto("Doc2", "Texto texto", 1 ));
        assertEquals(1, facade.criarTexto("Doc2", "Texto texto", 1 ));
        assertEquals(2, facade.criarTexto("Doc2", "Texto texto", 1 ));
    }
    @Test
    void testaCriaElementoAcimaLimite() {
        facade.criarDocumento("Doc2", 3);
        assertEquals(0, facade.criarTexto("Doc2", "Texto texto", 1 ));
        assertEquals(1, facade.criarTexto("Doc2", "Texto texto", 1 ));
        assertEquals(2, facade.criarTexto("Doc2", "Texto texto", 1 ));
        assertThrows(IllegalStateException.class, () -> facade.criarTexto("Doc2", "Texto texto", 1 ));
    }

    @Test
    void testaCriaElementoParamInvalido() {
        facade.criarDocumento("Doc2", 3);
        assertThrows(IllegalArgumentException.class, () -> facade.criarTexto("", "Texto texto", 1 ));
        assertThrows(NoSuchElementException.class, () -> facade.criarTexto("DocX", "Texto texto", 1 ));

    }

    @Test
    void testaVerQuantidadeElementos() {
        facade.criarDocumento("Doc2", 3);
        assertEquals(0, facade.contarElementos("Doc2"));
        facade.criarTexto("Doc2", "Texto texto", 1 );
        assertEquals(1, facade.contarElementos("Doc2"));
        facade.criarTexto("Doc2", "Texto texto", 1 );
        assertEquals(2, facade.contarElementos("Doc2"));
    }
    @Test
    void testaVerQuantidadeElementosParamInvalido() {
        assertThrows(NoSuchElementException.class, () -> facade.contarElementos("DocInexistente"));
        assertThrows(IllegalArgumentException.class, () -> facade.contarElementos(""));
        assertThrows(IllegalArgumentException.class, () -> facade.contarElementos("  "));
    }
    @Test
    void testaRemoverDoc() {
        facade.criarDocumento("Doc2", 3);
        facade.criarTexto("Doc2", "Texto texto", 1 );
        facade.criarTexto("Doc2", "Texto texto", 1 );
        facade.removerDocumento("Doc2");
        assertThrows(NoSuchElementException.class, () -> facade.exibirDocumento("Doc2"));
    }
    @Test
    void testaRemoverDocParametroInvalido() {
        assertThrows(IllegalArgumentException.class, () -> facade.exibirDocumento(""));
        assertThrows(IllegalArgumentException.class, () -> facade.exibirDocumento("   "));
    }
    @Test
    void testaRemoverDocInexistente() {
        facade.criarDocumento("Doc2", 3);
        assertThrows(NoSuchElementException.class, () -> facade.removerDocumento("Doc3"));
    }

    @Test
    void testaExibirDocumento() {
        facade.criarDocumento("Doc2", 3);
        assertEquals("[]", Arrays.toString(facade.exibirDocumento("Doc2")));
        assertEquals(0, facade.exibirDocumento("Doc2").length);
        facade.criarTexto("Doc2", "Texto texto", 1 );
        assertEquals("[Texto texto]", Arrays.toString(facade.exibirDocumento("Doc2")));
        assertEquals(1, facade.exibirDocumento("Doc2").length);
        facade.criarTexto("Doc2", "Mais Texto", 1 );
        assertEquals("[Texto texto, Mais Texto]", Arrays.toString(facade.exibirDocumento("Doc2")));
        assertEquals(2, facade.exibirDocumento("Doc2").length);
    }
    @Test
    void testaExibirDocumentoParamInvalido() {
        facade.criarDocumento("Doc2", 3);
        assertThrows(NoSuchElementException.class, () -> facade.exibirDocumento("DocInexistente"));
        assertThrows(IllegalArgumentException.class, () -> facade.exibirDocumento(""));
        assertThrows(IllegalArgumentException.class, () -> facade.exibirDocumento("   "));
    }
}