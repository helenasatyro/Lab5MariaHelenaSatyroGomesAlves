package documin.tests;

import documin.documento.DocuminController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DocuminControllerTest {

    private DocuminController dcUm;
    private DocuminController dcDois;

    @BeforeEach
    void setUp() {
        this.dcUm = new DocuminController();
        this.dcDois = new DocuminController();
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
        assertEquals("[]", Arrays.toString(dcUm.exibirDocumento("Doc1")));
    }

    @Test
    void testaCriarDocLimitado() {
        assertTrue(dcUm.criarDocumento("Doc1", 5));
        assertEquals("[]", Arrays.toString(dcUm.exibirDocumento("Doc1")));
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
        assertThrows(IllegalStateException.class, () -> dcDois.criarAtalho("Doc2", "Doc1"));
        assertThrows(IllegalStateException.class, () -> dcDois.criarTitulo("Doc2", "Texto texto", 1, 1, false));
        assertThrows(IllegalStateException.class, () -> dcDois.criarTermos("Doc2", "Texto, texto", 1, ", ", "Nenhum" ));
        assertThrows(IllegalStateException.class, () -> dcDois.criarLista("Doc2", "Texto, texto", 1, ", ", "*" ));
    }

    @Test
    void testaCriaElementoParamInvalido() {
        dcDois.criarDocumento("Doc2", 3);
        assertThrows(IllegalArgumentException.class, () -> dcDois.criarTexto("", "Texto texto", 1 ));
        assertThrows(NoSuchElementException.class, () -> dcDois.criarTexto("DocX", "Texto texto", 1 ));
        assertThrows(IllegalArgumentException.class, () -> dcDois.criarTexto("Doc2", "Texto texto", 0 ), "Não pode aceitar prioridade abaixo de 1");
        assertThrows(IllegalArgumentException.class, () -> dcDois.criarTexto("Doc2", "Texto texto", 6 ), "Não pode aceitar prioridade acima de 5.");
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
        assertThrows(NoSuchElementException.class, () -> dcDois.exibirDocumento("Doc2"));
    }
    @Test
    void testaRemoverDocParametroInvalido() {
        assertThrows(IllegalArgumentException.class, () -> dcDois.exibirDocumento(""));
        assertThrows(IllegalArgumentException.class, () -> dcDois.exibirDocumento("   "));
    }
    @Test
    void testaRemoverDocInexistente() {
        dcDois.criarDocumento("Doc2", 3);
        assertThrows(NoSuchElementException.class, () -> dcDois.removeDocumento("Doc3"));
    }

    @Test
    void testaExibirDocumento() {
        dcDois.criarDocumento("Doc2", 3);
        assertEquals("[]", Arrays.toString(dcDois.exibirDocumento("Doc2")));
        assertEquals(0, dcDois.exibirDocumento("Doc2").length);
        dcDois.criarTexto("Doc2", "Texto texto", 1 );
        assertEquals("[Texto texto\n]", Arrays.toString(dcDois.exibirDocumento("Doc2")));
        assertEquals(1, dcDois.exibirDocumento("Doc2").length);
        dcDois.criarTexto("Doc2", "Mais Texto", 1 );
        assertEquals("[Texto texto\n, Mais Texto\n]", Arrays.toString(dcDois.exibirDocumento("Doc2")));
        assertEquals(2, dcDois.exibirDocumento("Doc2").length);
    }
    @Test
    void testaExibirDocumentoParamInvalido() {
        dcDois.criarDocumento("Doc2", 3);
        assertThrows(NoSuchElementException.class, () -> dcDois.exibirDocumento("DocInexistente"));
        assertThrows(IllegalArgumentException.class, () -> dcDois.exibirDocumento(""));
        assertThrows(IllegalArgumentException.class, () -> dcDois.exibirDocumento("   "));
    }

    @Test
    void testaCriaTermos() {
        dcDois.criarTermos("Doc1", "termo | outro | mais um", 1, "|", "alfabetica");
        assertEquals("Total termos: 3\n-  mais um,  outro , termo \n", dcDois.pegarRepresentacaoCompleta("Doc1", 0));
        assertEquals(" mais um /  outro  / termo \n", dcDois.pegarRepresentacaoResumida("Doc1", 0));

        dcDois.criarTermos("Doc1", "termo | um | mais um", 1, "|", "tamanho");
        assertEquals("Total termos: 3\n-  mais um, termo ,  um \n",
                dcDois.pegarRepresentacaoCompleta("Doc1", 1));
        assertEquals(" mais um / termo  /  um \n", dcDois.pegarRepresentacaoResumida("Doc1", 1));

        dcDois.criarTermos("Doc1","termo | outro | mais um", 1, "|", "nenhum");
        assertEquals("Total termos: 3\n" +
                "- termo ,  outro ,  mais um\n", dcDois.pegarRepresentacaoCompleta("Doc1", 2));
        assertEquals("termo  /  outro  /  mais um\n", dcDois.pegarRepresentacaoResumida("Doc1", 2));
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
        assertEquals("[um dois tres\n]", Arrays.toString(dcUm.exibirDocumento("Doc Com Atalho")));


        dcUm.criarDocumento("Doc Referenciado");
        dcUm.criarTexto("Doc Referenciado", "texto referenciado", 4);

        dcUm.criarAtalho("Doc Com Atalho","Doc Referenciado");
        assertEquals("[um dois tres\n, texto referenciado\n]", Arrays.toString(dcUm.exibirDocumento("Doc Com Atalho")));
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
    void testaCriarAtalhoSemElementos() {
        dcUm.criarDocumento("Doc Referenciado");

        dcUm.criarDocumento("Doc Com Atalho");
        dcUm.criarTexto("Doc Com Atalho", "um dois tres", 2);
        // deve lançar excecão pois não tem como calcular a prioridade
        assertThrows(IllegalArgumentException.class, () -> dcUm.criarAtalho("Doc Com Atalho", "Doc Referenciado"));

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
                "]", Arrays.toString(dcUm.exibirDocumento("Doc Com Atalho")));
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

    @Test
    void testaMoveParaCima() {
        dcUm.criarDocumento("Doc1", 3);
        dcUm.criarTexto("Doc1", "texto1", 5);
        dcUm.criarTexto("Doc1", "texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibirDocumento("Doc1")));

        dcUm.moverParaCima("Doc1", 1);
        assertEquals("[texto2\n, texto1\n]", Arrays.toString(dcUm.exibirDocumento("Doc1")));
    }
    @Test
    void testaMoveParaCimaPrimeiro() {
        dcUm.criarDocumento("Doc1", 3);
        dcUm.criarTexto("Doc1", "texto1", 5);
        dcUm.criarTexto("Doc1", "texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibirDocumento("Doc1")));

        dcUm.moverParaCima("Doc1", 0);
        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibirDocumento("Doc1")));
    }
    @Test
    void testaMoveParaBaixo() {
        dcUm.criarDocumento("Doc1", 3);
        dcUm.criarTexto("Doc1", "texto1", 5);
        dcUm.criarTexto("Doc1", "texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibirDocumento("Doc1")));

        dcUm.moverParaBaixo("Doc1", 0);
        assertEquals("[texto2\n, texto1\n]", Arrays.toString(dcUm.exibirDocumento("Doc1")));
    }
    @Test
    void testaMoveParaBaixoUltimo() {
        dcUm.criarDocumento("Doc1", 3);
        dcUm.criarTexto("Doc1", "texto1", 5);
        dcUm.criarTexto("Doc1", "texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibirDocumento("Doc1")));

        dcUm.moverParaBaixo("Doc1", 1);
        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibirDocumento("Doc1")));
    }

    @Test
    void TestaRemoveElemento() {
        dcUm.criarDocumento("Doc1", 3);
        dcUm.criarTexto("Doc1", "texto1", 5);
        dcUm.criarTexto("Doc1", "texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibirDocumento("Doc1")));

        dcUm.apagarElemento("Doc1", 1);
        assertEquals(1, dcUm.getNumeroElementos("Doc1"));
        assertEquals("[texto1\n]", Arrays.toString(dcUm.exibirDocumento("Doc1")));

        dcUm.apagarElemento("Doc1", 0);
        assertEquals("[]", Arrays.toString(dcUm.exibirDocumento("Doc1")));
    }
    @Test
    void TestaRemoverElementoAtalhoPermiteAdicionarAtalhosAoDocumentoReferenciado() {
        dcUm.criarDocumento("DocRef", 3);
        dcUm.criarTexto("DocRef", "texto1", 5);

        // deve ser possível adicionar um atalho ao doc2 referenciando o docref
        dcUm.criarDocumento("Doc2");
        dcUm.criarTexto("Doc2", "texto2", 5);
        dcUm.criarAtalho("Doc2", "DocRef");
        assertEquals("[texto2\n, texto1\n]", Arrays.toString(dcUm.exibirDocumento("Doc2")));

        // então docref não poderá receber atalhos
        dcUm.criarDocumento("Doc3");
        dcUm.criarTexto("Doc3", "texto3", 5);
        assertThrows(IllegalStateException.class, () -> dcUm.criarAtalho("DocRef", "Doc3"));

        // removemos o atalho de doc2 que referencia docRef
        dcUm.apagarElemento("Doc2", 1);
        dcUm.criarAtalho("DocRef", "Doc3");

        // então podemos adicionar atalhos ao docRef
        assertEquals("[texto1\n, texto3\n]", Arrays.toString(dcUm.exibirDocumento("DocRef")));
    }

    @Test
    void testaVisaoCompleta() {
        dcUm.criarDocumento("Doc1");
        dcUm.criarTexto("Doc1", "Elemento texto prioridade 1", 1);
        dcUm.criarTermos("Doc1", "Elemento / termos / prioridade 4",4, "/", "nenhum");
        dcUm.criarLista("Doc1", "Lista . prioridade . 3", 3, " . ", "*");
        // Criamos a visão completa aqui para checar depois se ela é atualizada
        assertEquals("""
                [Elemento texto prioridade 1
                , Total termos: 3
                - Elemento ,  termos ,  prioridade 4
                , * Lista
                * prioridade
                * 3
                ]""", Arrays.toString(dcUm.exibirVisao(dcUm.criarVisaoCompleta("Doc1"))));
        dcUm.criarTitulo("Doc1", "Titulo p5", 5, 2, true);
        dcUm.criarDocumento("DocRef");
        dcUm.criarTexto("DocRef", "Elemento texto prioridade 5", 5);
        dcUm.criarAtalho("Doc1", "DocRef");
        assertEquals("""
                [Elemento texto prioridade 1
                , Total termos: 3
                - Elemento ,  termos ,  prioridade 4
                , * Lista
                * prioridade
                * 3
                , 2. Titulo p5 -- 2-TITULOP5
                , Elemento texto prioridade 5
                ]""", Arrays.toString(dcUm.exibirVisao(dcUm.criarVisaoCompleta("Doc1"))));

    }
    @Test
    void testaVisaoResumida() {
        dcUm.criarDocumento("Doc1");
        dcUm.criarTexto("Doc1", "Elemento texto prioridade 1", 1);
        dcUm.criarTermos("Doc1", "Elemento / termos / prioridade 4",4, "/", "nenhum");
        dcUm.criarLista("Doc1", "Lista . prioridade . 3", 3, " . ", "*");
        // Criamos a visão resumida aqui para checar depois se ela é atualizada
        assertEquals("""
                [Elemento texto prioridade 1
                , Elemento  /  termos  /  prioridade 4
                , Lista, prioridade, 3
                ]""", Arrays.toString(dcUm.exibirVisao(dcUm.criarVisaoResumida("Doc1"))));
        dcUm.criarTitulo("Doc1", "Titulo p5", 5, 2, true);
        dcUm.criarDocumento("DocRef");
        dcUm.criarTexto("DocRef", "Elemento texto prioridade 5", 5);
        dcUm.criarAtalho("Doc1", "DocRef");
        assertEquals("""
                [Elemento texto prioridade 1
                , Elemento  /  termos  /  prioridade 4
                , Lista, prioridade, 3
                , 2. Titulo p5
                , Elemento texto prioridade 5
                ]""", Arrays.toString(dcUm.exibirVisao(dcUm.criarVisaoResumida("Doc1"))));
    }
    @Test
    void testaVisaoPrioritaria() {
        dcUm.criarDocumento("Doc1");
        dcUm.criarTexto("Doc1", "Elemento texto prioridade 1", 1);
        dcUm.criarTermos("Doc1", "Elemento / termos / prioridade 4",4, "/", "nenhum");
        dcUm.criarLista("Doc1", "Lista . prioridade . 3", 3, " . ", "*");
        // Criamos a visão prioritaria aqui para checar depois se ela é atualizada
        assertEquals("""
                [Total termos: 3
                - Elemento ,  termos ,  prioridade 4
                ]""", Arrays.toString(dcUm.exibirVisao(dcUm.criarVisaoPrioritaria("Doc1", 4))));
        dcUm.criarTitulo("Doc1", "Titulo p5", 5, 2, true);
        dcUm.criarDocumento("DocRef");
        dcUm.criarTexto("DocRef", "Elemento texto prioridade 5", 5);
        dcUm.criarAtalho("Doc1", "DocRef");
        assertEquals("""
                [Total termos: 3
                - Elemento ,  termos ,  prioridade 4
                , 2. Titulo p5 -- 2-TITULOP5
                , Elemento texto prioridade 5
                ]""", Arrays.toString(dcUm.exibirVisao(dcUm.criarVisaoPrioritaria("Doc1", 4))));

    }
    @Test
    void testaVisaoTitulo() {
        dcUm.criarDocumento("Doc1");
        dcUm.criarTexto("Doc1", "Elemento texto prioridade 1", 1);
        dcUm.criarTermos("Doc1", "Elemento / termos / prioridade 4",4, "/", "nenhum");
        dcUm.criarLista("Doc1", "Lista . prioridade . 3", 3, " . ", "*");
        // Criamos a visão titulo aqui para checar depois se ela é atualizada
        assertEquals("[]", Arrays.toString(dcUm.exibirVisao(dcUm.criarVisaoTitulo("Doc1"))));
        dcUm.criarTitulo("Doc1", "Titulo p5", 5, 2, true);
        dcUm.criarDocumento("DocRef");
        dcUm.criarTexto("DocRef", "Elemento texto prioridade 5", 5);
        dcUm.criarAtalho("Doc1", "DocRef");
        assertEquals("[2. Titulo p5\n]", Arrays.toString(dcUm.exibirVisao(dcUm.criarVisaoTitulo("Doc1"))));
    }
}