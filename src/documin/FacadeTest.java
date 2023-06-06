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
        facade.criarDocumento("Doc1");
    }
    @Test
    void testaCriarDocIlimitado() {
        assertTrue(facade.criarDocumento("Doc3"));
        assertEquals("[]", Arrays.toString(facade.exibirDocumento("Doc3")));
    }

    @Test
    void testaCriarDocLimitado() {
        assertTrue(facade.criarDocumento("Doc3", 5));
        assertEquals("[]", Arrays.toString(facade.exibirDocumento("Doc3")));
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
        assertTrue(facade.criarDocumento("Doc4", 5));
        assertFalse(facade.criarDocumento("Doc4"));
        assertFalse(facade.criarDocumento("Doc4", 5));
    }
    @Test
    void testaCriarDocTamanhoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> facade.criarDocumento("Doc4", 0));
        assertThrows(IllegalArgumentException.class, () -> facade.criarDocumento("Doc4", -5));
    }
    @Test
    void testaCriarDocs() {
        assertTrue(facade.criarDocumento("Doc4", 5));
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
        assertEquals("[Texto texto\n]", Arrays.toString(facade.exibirDocumento("Doc2")));
        assertEquals(1, facade.exibirDocumento("Doc2").length);
        facade.criarTexto("Doc2", "Mais Texto", 1 );
        assertEquals("[Texto texto\n, Mais Texto\n]", Arrays.toString(facade.exibirDocumento("Doc2")));
        assertEquals(2, facade.exibirDocumento("Doc2").length);
    }
    @Test
    void testaExibirDocumentoParamInvalido() {
        facade.criarDocumento("Doc2", 3);
        assertThrows(NoSuchElementException.class, () -> facade.exibirDocumento("DocInexistente"));
        assertThrows(IllegalArgumentException.class, () -> facade.exibirDocumento(""));
        assertThrows(IllegalArgumentException.class, () -> facade.exibirDocumento("   "));
    }

    @Test
    void testaCriaTermos() {
        facade.criarTermos("Doc1", "termo | outro | mais um", 1, "|", "alfabetica");
        assertEquals("Total termos: 3\n-  mais um,  outro , termo ", facade.pegarRepresentacaoCompleta("Doc1", 0));
        assertEquals(" mais um /  outro  / termo ", facade.pegarRepresentacaoResumida("Doc1", 0));

        facade.criarTermos("Doc1", "termo | um | mais um", 1, "|", "tamanho");
        assertEquals("Total termos: 3\n-  mais um, termo ,  um ",
                facade.pegarRepresentacaoCompleta("Doc1", 1));
        assertEquals(" mais um / termo  /  um ", facade.pegarRepresentacaoResumida("Doc1", 1));

        facade.criarTermos("Doc1","termo | outro | mais um", 1, "|", "nenhum");
        assertEquals("Total termos: 3\n" +
                "- termo ,  outro ,  mais um", facade.pegarRepresentacaoCompleta("Doc1", 2));
        assertEquals("termo  /  outro  /  mais um", facade.pegarRepresentacaoResumida("Doc1", 2));
    }

    @Test
    void testaCriaLista() {
        facade.criarLista("Doc1", "um, dois, tres", 2, ", ", "*");
        assertEquals("* um\n* dois\n* tres\n", facade.pegarRepresentacaoCompleta("Doc1", 0));
        assertEquals("um, dois, tres\n", facade.pegarRepresentacaoResumida("Doc1", 0));
    }
    @Test
    void testaCriaTexto() {
        facade.criarTexto("Doc1", "um, dois, tres", 2);
        assertEquals("um, dois, tres\n", facade.pegarRepresentacaoCompleta("Doc1", 0));
        assertEquals("um, dois, tres\n", facade.pegarRepresentacaoResumida("Doc1", 0));
    }
    @Test
    void testaCriaTitulo() {
        facade.criarTitulo("Doc1", "um, dois, tres", 2, 2, true);
        assertEquals("2. um, dois, tres -- 2-UM,DOIS,TRES\n", facade.pegarRepresentacaoCompleta("Doc1", 0));
        assertEquals("2. um, dois, tres\n", facade.pegarRepresentacaoResumida("Doc1", 0));
        facade.criarTitulo("Doc1", "um, dois, tres", 2, 2, false);
        assertEquals("2. um, dois, tres\n", facade.pegarRepresentacaoCompleta("Doc1", 1));
        assertEquals("2. um, dois, tres\n", facade.pegarRepresentacaoResumida("Doc1", 1));
    }
    @Test
    void testaCriarAtalhoSemRestricao() {
        facade.criarDocumento("Doc Com Atalho");
        facade.criarTexto("Doc Com Atalho", "um dois tres", 2);

        facade.criarDocumento("Doc Referenciado Baixa Prioridade");
        facade.criarTexto("Doc Referenciado Baixa Prioridade", "texto referenciado nao aparece", 2);
        assertEquals("[um dois tres\n]", Arrays.toString(facade.exibirDocumento("Doc Com Atalho")));

        facade.criarDocumento("Doc Referenciado");
        facade.criarTexto("Doc Referenciado", "texto referenciado", 4);

        facade.criarAtalho("Doc Com Atalho","Doc Referenciado");
        assertEquals("[um dois tres\n, texto referenciado\n]", Arrays.toString(facade.exibirDocumento("Doc Com Atalho")));
        assertEquals(2, facade.contarElementos("Doc Com Atalho"));
    }
    @Test
    void testaCriarAtalhoElementoJaEhAtalho() {
        facade.criarDocumento("Doc Referenciado");
        facade.criarTexto("Doc Referenciado", "texto referenciado", 4);

        facade.criarDocumento("Doc Com Atalho");
        facade.criarTexto("Doc Com Atalho", "um dois tres", 2);
        facade.criarAtalho("Doc Com Atalho", "Doc Referenciado");

        facade.criarDocumento("NovoDoc");
        assertThrows(IllegalStateException.class, () -> facade.criarAtalho("NovoDoc","Doc Com Atalho"));
    }
    @Test
    void testaCriarAtalhoElementoEhAtalho() {
        facade.criarDocumento("Doc Referenciado");
        facade.criarTexto("Doc Referenciado", "texto referenciado", 4);

        facade.criarDocumento("Doc Com Atalho");
        facade.criarTexto("Doc Com Atalho", "um dois tres", 2);
        facade.criarAtalho("Doc Com Atalho", "Doc Referenciado");

        facade.criarDocumento("NovoDoc");
        assertThrows(IllegalStateException.class, () -> facade.criarAtalho("Doc Referenciado","NovoDoc"));
    }
    @Test
    void testaCriarDoisAtalhos() {
        facade.criarDocumento("Doc Referenciado");
        facade.criarTexto("Doc Referenciado", "texto referenciado", 4);
        facade.criarDocumento("Doc Referenciado 2");
        facade.criarTexto("Doc Referenciado 2", "texto referenciado 2", 4);

        facade.criarDocumento("Doc Com Atalho");
        facade.criarTexto("Doc Com Atalho", "um dois tres", 2);

        facade.criarAtalho("Doc Com Atalho", "Doc Referenciado");
        facade.criarAtalho("Doc Com Atalho", "Doc Referenciado 2");

        assertEquals("[um dois tres\n" +
                ", texto referenciado\n" +
                ", texto referenciado 2\n" +
                "]", Arrays.toString(facade.exibirDocumento("Doc Com Atalho")));
    }
    @Test
    void testaExibirAtalhoCompleto() {
        facade.criarDocumento("Doc referenciado");
        facade.criarLista("Doc referenciado", "um, dois, tres", 5, ", ", "*");

        facade.criarDocumento("Doc Com Atalho");
        facade.criarTexto("Doc Com Atalho", "um dois tres", 5);
        facade.criarAtalho("Doc Com Atalho", "Doc referenciado");

        assertEquals("* um\n* dois\n* tres\n", facade.pegarRepresentacaoCompleta("Doc Com Atalho", 1));
    }
}