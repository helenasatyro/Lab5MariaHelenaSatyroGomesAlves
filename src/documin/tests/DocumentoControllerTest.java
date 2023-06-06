package documin.tests;

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
    void testaBuscaDocInexistente() {
        // todos os métodos que precisam executar busca por título usam esse método
        assertThrows(NoSuchElementException.class, () -> dcDois.buscaPorTitulo("DOOOOOOOOC"));
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
        assertEquals("[Texto texto\n]", Arrays.toString(dcDois.exibeDocumento("Doc2")));
        assertEquals(1, dcDois.exibeDocumento("Doc2").length);
        dcDois.criarTexto("Doc2", "Mais Texto", 1 );
        assertEquals("[Texto texto\n, Mais Texto\n]", Arrays.toString(dcDois.exibeDocumento("Doc2")));
        assertEquals(2, dcDois.exibeDocumento("Doc2").length);
    }
    @Test
    void testaExibirDocumentoParamInvalido() {
        dcDois.criarDocumento("Doc2", 3);
        assertThrows(NoSuchElementException.class, () -> dcDois.exibeDocumento("DocInexistente"));
        assertThrows(IllegalArgumentException.class, () -> dcDois.exibeDocumento(""));
        assertThrows(IllegalArgumentException.class, () -> dcDois.exibeDocumento("   "));
    }

    @Test
    void testaCriaTermos() {
        dcDois.criarTermos("Doc1", "termo | outro | mais um", 1, "|", "alfabetica");
        assertEquals("Total termos: 3\n-  mais um,  outro , termo ", dcDois.pegarRepresentacaoCompleta("Doc1", 0));
        assertEquals(" mais um /  outro  / termo ", dcDois.pegarRepresentacaoResumida("Doc1", 0));

        dcDois.criarTermos("Doc1", "termo | um | mais um", 1, "|", "tamanho");
        assertEquals("Total termos: 3\n-  mais um, termo ,  um ",
                dcDois.pegarRepresentacaoCompleta("Doc1", 1));
        assertEquals(" mais um / termo  /  um ", dcDois.pegarRepresentacaoResumida("Doc1", 1));

        dcDois.criarTermos("Doc1","termo | outro | mais um", 1, "|", "nenhum");
        assertEquals("Total termos: 3\n" +
                "- termo ,  outro ,  mais um", dcDois.pegarRepresentacaoCompleta("Doc1", 2));
        assertEquals("termo  /  outro  /  mais um", dcDois.pegarRepresentacaoResumida("Doc1", 2));
    }

    @Test
    void testaCriaLista() {
        dcDois.criarLista("Doc1", "um, dois, tres", 2, ", ", "*");
        assertEquals("* um\n* dois\n* tres\n", dcDois.pegarRepresentacaoCompleta("Doc1", 0));
        assertEquals("um, dois, tres\n", dcDois.pegarRepresentacaoResumida("Doc1", 0));
    }
    @Test
    void testaCriaTexto() {
        dcDois.criarTexto("Doc1", "um, dois, tres", 2);
        assertEquals("um, dois, tres\n", dcDois.pegarRepresentacaoCompleta("Doc1", 0));
        assertEquals("um, dois, tres\n", dcDois.pegarRepresentacaoResumida("Doc1", 0));
    }
    @Test
    void testaCriaTitulo() {
        dcDois.criarTitulo("Doc1", "um, dois, tres", 2, 2, true);
        assertEquals("2. um, dois, tres -- 2-UM,DOIS,TRES\n", dcDois.pegarRepresentacaoCompleta("Doc1", 0));
        assertEquals("2. um, dois, tres\n", dcDois.pegarRepresentacaoResumida("Doc1", 0));
        dcDois.criarTitulo("Doc1", "um, dois, tres", 2, 2, false);
        assertEquals("2. um, dois, tres\n", dcDois.pegarRepresentacaoCompleta("Doc1", 1));
        assertEquals("2. um, dois, tres\n", dcDois.pegarRepresentacaoResumida("Doc1", 1));
    }

    @Test
    void testaCriarAtalhoSemRestricao() {
        dcUm.criarDocumento("Doc Com Atalho");
        dcUm.criarTexto("Doc Com Atalho", "um dois tres", 2);

        dcUm.criarDocumento("Doc Referenciado Baixa Prioridade");
        dcUm.criarTexto("Doc Referenciado Baixa Prioridade", "texto referenciado nao aparece", 2);
        assertEquals("[um dois tres\n]", Arrays.toString(dcUm.exibeDocumento("Doc Com Atalho")));


        dcUm.criarDocumento("Doc Referenciado");
        dcUm.criarTexto("Doc Referenciado", "texto referenciado", 4);

        dcUm.criarAtalho("Doc Com Atalho","Doc Referenciado");
        assertEquals("[um dois tres\n, texto referenciado\n]", Arrays.toString(dcUm.exibeDocumento("Doc Com Atalho")));
        assertEquals(2, dcUm.getNumeroElementos("Doc Com Atalho"));
    }
    @Test
    void testaCriarAtalhoElementoTemAtalho() {
        dcUm.criarDocumento("Doc Referenciado");
        dcUm.criarTexto("Doc Referenciado", "texto referenciado", 4);

        dcUm.criarDocumento("Doc Com Atalho");
        dcUm.criarTexto("Doc Com Atalho", "um dois tres", 2);
        dcUm.criarAtalho("Doc Com Atalho", "Doc Referenciado");

        dcUm.criarDocumento("NovoDoc");
        assertThrows(IllegalStateException.class, () -> dcUm.criarAtalho("NovoDoc","Doc Com Atalho"));
    }
    @Test
    void testaCriarAtalhoElementoEhAtalho() {
        dcUm.criarDocumento("Doc Referenciado");
        dcUm.criarTexto("Doc Referenciado", "texto referenciado", 4);

        dcUm.criarDocumento("Doc Com Atalho");
        dcUm.criarTexto("Doc Com Atalho", "um dois tres", 2);
        dcUm.criarAtalho("Doc Com Atalho", "Doc Referenciado");

        dcUm.criarDocumento("NovoDoc");
        assertThrows(IllegalStateException.class, () -> dcUm.criarAtalho("Doc Referenciado","NovoDoc"));
    }
    @Test
    void testaCriarDoisAtalhos() {
        dcUm.criarDocumento("Doc Referenciado");
        dcUm.criarTexto("Doc Referenciado", "texto referenciado", 4);
        dcUm.criarDocumento("Doc Referenciado 2");
        dcUm.criarTexto("Doc Referenciado 2", "texto referenciado 2", 4);

        dcUm.criarDocumento("Doc Com Atalho");
        dcUm.criarTexto("Doc Com Atalho", "um dois tres", 2);

        dcUm.criarAtalho("Doc Com Atalho", "Doc Referenciado");
        dcUm.criarAtalho("Doc Com Atalho", "Doc Referenciado 2");

        assertEquals("[um dois tres\n" +
                ", texto referenciado\n" +
                ", texto referenciado 2\n" +
                "]", Arrays.toString(dcUm.exibeDocumento("Doc Com Atalho")));
    }

    @Test
    void testaExibirAtalhoCompleto() {
        dcUm.criarDocumento("Doc referenciado");
        dcUm.criarLista("Doc referenciado", "um, dois, tres", 5, ", ", "*");

        dcUm.criarDocumento("Doc Com Atalho");
        dcUm.criarTexto("Doc Com Atalho", "um dois tres", 5);
        dcUm.criarAtalho("Doc Com Atalho", "Doc referenciado");

        assertEquals("* um\n* dois\n* tres\n", dcUm.pegarRepresentacaoCompleta("Doc Com Atalho", 1));
    }
}