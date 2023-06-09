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
        assertThrows(IllegalArgumentException.class, () -> facade.criarTexto("Doc2", "Texto texto", 0 ), "Não pode aceitar prioridade abaixo de 1");
        assertThrows(IllegalArgumentException.class, () -> facade.criarTexto("Doc2", "Texto texto", 6 ), "Não pode aceitar prioridade acima de 5.");
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
        assertThrows(IllegalArgumentException.class, () -> facade.exibirDocumento(""));
        assertThrows(IllegalArgumentException.class, () -> facade.exibirDocumento("   "));
    }

    @Test
    void testaCriaTermos() {
        facade.criarTermos("Doc1", "termo | outro | mais um", 1, "|", "alfabetica");
        assertEquals("Total termos: 3\n-  mais um,  outro , termo \n", facade.pegarRepresentacaoCompleta("Doc1", 0));
        assertEquals(" mais um /  outro  / termo \n", facade.pegarRepresentacaoResumida("Doc1", 0));

        facade.criarTermos("Doc1", "termo | um | mais um", 1, "|", "tamanho");
        assertEquals("Total termos: 3\n-  mais um, termo ,  um \n",
                facade.pegarRepresentacaoCompleta("Doc1", 1));
        assertEquals(" mais um / termo  /  um \n", facade.pegarRepresentacaoResumida("Doc1", 1));

        facade.criarTermos("Doc1","termo | outro | mais um", 1, "|", "nenhum");
        assertEquals("Total termos: 3\n" +
                "- termo ,  outro ,  mais um\n", facade.pegarRepresentacaoCompleta("Doc1", 2));
        assertEquals("termo  /  outro  /  mais um\n", facade.pegarRepresentacaoResumida("Doc1", 2));
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
    void testaCriarAtalhoSemElementos() {
        facade.criarDocumento("Doc Referenciado");

        facade.criarDocumento("Doc Com Atalho");
        facade.criarTexto("Doc Com Atalho", "um dois tres", 2);
        // deve lançar excecão pois não tem como calcular a prioridade
        assertThrows(IllegalArgumentException.class, () -> facade.criarAtalho("Doc Com Atalho", "Doc Referenciado"));

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
    @Test
    void testaMoveParaCima() {
        facade.criarDocumento("Doc1", 3);
        facade.criarTexto("Doc1", "texto1", 5);
        facade.criarTexto("Doc1", "texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(facade.exibirDocumento("Doc1")));

        facade.moverParaCima("Doc1", 1);
        assertEquals("[texto2\n, texto1\n]", Arrays.toString(facade.exibirDocumento("Doc1")));
    }
    @Test
    void testaMoveParaCimaPrimeiro() {
        facade.criarDocumento("Doc1", 3);
        facade.criarTexto("Doc1", "texto1", 5);
        facade.criarTexto("Doc1", "texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(facade.exibirDocumento("Doc1")));

        facade.moverParaCima("Doc1", 0);
        assertEquals("[texto1\n, texto2\n]", Arrays.toString(facade.exibirDocumento("Doc1")));
    }
    @Test
    void testaMoveParaBaixo() {
        facade.criarDocumento("Doc1", 3);
        facade.criarTexto("Doc1", "texto1", 5);
        facade.criarTexto("Doc1", "texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(facade.exibirDocumento("Doc1")));

        facade.moverParaBaixo("Doc1", 0);
        assertEquals("[texto2\n, texto1\n]", Arrays.toString(facade.exibirDocumento("Doc1")));
    }
    @Test
    void testaMoveParaBaixoUltimo() {
        facade.criarDocumento("Doc1", 3);
        facade.criarTexto("Doc1", "texto1", 5);
        facade.criarTexto("Doc1", "texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(facade.exibirDocumento("Doc1")));

        facade.moverParaBaixo("Doc1", 1);
        assertEquals("[texto1\n, texto2\n]", Arrays.toString(facade.exibirDocumento("Doc1")));
    }

    @Test
    void TestaRemoveElemento() {
        facade.criarDocumento("Doc1", 3);
        facade.criarTexto("Doc1", "texto1", 5);
        facade.criarTexto("Doc1", "texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(facade.exibirDocumento("Doc1")));

        facade.apagarElemento("Doc1", 1);
        assertEquals(1, facade.contarElementos("Doc1"));
        assertEquals("[texto1\n]", Arrays.toString(facade.exibirDocumento("Doc1")));

        facade.apagarElemento("Doc1", 0);
        assertEquals("[]", Arrays.toString(facade.exibirDocumento("Doc1")));
    }
    @Test
    void TestaRemoverElementoAtalhoPermiteAdicionarAtalhosAoDocumentoReferenciado() {
        facade.criarDocumento("DocRef", 3);
        facade.criarTexto("DocRef", "texto1", 5);

        // deve ser possível adicionar um atalho ao doc2 referenciando o docref
        facade.criarDocumento("Doc2");
        facade.criarTexto("Doc2", "texto2", 5);
        facade.criarAtalho("Doc2", "DocRef");
        assertEquals("[texto2\n, texto1\n]", Arrays.toString(facade.exibirDocumento("Doc2")));

        // então docref não poderá receber atalhos
        facade.criarDocumento("Doc3");
        facade.criarTexto("Doc3", "texto3", 5);
        assertThrows(IllegalStateException.class, () -> facade.criarAtalho("DocRef", "Doc3"));

        // removemos o atalho de doc2 que referencia docRef
        facade.apagarElemento("Doc2", 1);
        facade.criarAtalho("DocRef", "Doc3");

        // então podemos adicionar atalhos ao docRef
        assertEquals("[texto1\n, texto3\n]", Arrays.toString(facade.exibirDocumento("DocRef")));
    }
    @Test
    void testaVisaoCompleta() {
        facade.criarDocumento("Doc1");
        facade.criarTexto("Doc1", "Elemento texto prioridade 1", 1);
        facade.criarTermos("Doc1", "Elemento / termos / prioridade 4",4, "/", "nenhum");
        facade.criarLista("Doc1", "Lista . prioridade . 3", 3, " . ", "*");
        // Criamos a visão completa aqui para checar depois se ela é atualizada
        assertEquals("""
                [Elemento texto prioridade 1
                , Total termos: 3
                - Elemento ,  termos ,  prioridade 4
                , * Lista
                * prioridade
                * 3
                ]""", Arrays.toString(facade.exibirVisao(facade.criarVisaoCompleta("Doc1"))));
        facade.criarTitulo("Doc1", "Titulo p5", 5, 2, true);
        facade.criarDocumento("DocRef");
        facade.criarTexto("DocRef", "Elemento texto prioridade 5", 5);
        facade.criarAtalho("Doc1", "DocRef");
        assertEquals("""
                [Elemento texto prioridade 1
                , Total termos: 3
                - Elemento ,  termos ,  prioridade 4
                , * Lista
                * prioridade
                * 3
                , 2. Titulo p5 -- 2-TITULOP5
                , Elemento texto prioridade 5
                ]""", Arrays.toString(facade.exibirVisao(facade.criarVisaoCompleta("Doc1"))));

    }
    @Test
    void testaVisaoResumida() {
        facade.criarDocumento("Doc1");
        facade.criarTexto("Doc1", "Elemento texto prioridade 1", 1);
        facade.criarTermos("Doc1", "Elemento / termos / prioridade 4",4, "/", "nenhum");
        facade.criarLista("Doc1", "Lista . prioridade . 3", 3, " . ", "*");
        // Criamos a visão completa aqui para checar depois se ela é atualizada
        assertEquals("""
                [Elemento texto prioridade 1
                , Elemento  /  termos  /  prioridade 4
                , Lista, prioridade, 3
                ]""", Arrays.toString(facade.exibirVisao(facade.criarVisaoResumida("Doc1"))));
        facade.criarTitulo("Doc1", "Titulo p5", 5, 2, true);
        facade.criarDocumento("DocRef");
        facade.criarTexto("DocRef", "Elemento texto prioridade 5", 5);
        facade.criarAtalho("Doc1", "DocRef");
        assertEquals("""
                [Elemento texto prioridade 1
                , Elemento  /  termos  /  prioridade 4
                , Lista, prioridade, 3
                , 2. Titulo p5
                , Elemento texto prioridade 5
                ]""", Arrays.toString(facade.exibirVisao(facade.criarVisaoResumida("Doc1"))));
    }
    @Test
    void testaVisaoPrioritaria() {
        facade.criarDocumento("Doc1");
        facade.criarTexto("Doc1", "Elemento texto prioridade 1", 1);
        facade.criarTermos("Doc1", "Elemento / termos / prioridade 4",4, "/", "nenhum");
        facade.criarLista("Doc1", "Lista . prioridade . 3", 3, " . ", "*");
        // Criamos a visão completa aqui para checar depois se ela é atualizada
        assertEquals("""
                [Total termos: 3
                - Elemento ,  termos ,  prioridade 4
                ]""", Arrays.toString(facade.exibirVisao(facade.criarVisaoPrioritaria("Doc1", 4))));
        facade.criarTitulo("Doc1", "Titulo p5", 5, 2, true);
        facade.criarDocumento("DocRef");
        facade.criarTexto("DocRef", "Elemento texto prioridade 5", 5);
        facade.criarAtalho("Doc1", "DocRef");
        assertEquals("""
                [Total termos: 3
                - Elemento ,  termos ,  prioridade 4
                , 2. Titulo p5 -- 2-TITULOP5
                , Elemento texto prioridade 5
                ]""", Arrays.toString(facade.exibirVisao(facade.criarVisaoPrioritaria("Doc1", 4))));

    }
    @Test
    void testaVisaoTitulo() {
        facade.criarDocumento("Doc1");
        facade.criarTexto("Doc1", "Elemento texto prioridade 1", 1);
        facade.criarTermos("Doc1", "Elemento / termos / prioridade 4",4, "/", "nenhum");
        facade.criarLista("Doc1", "Lista . prioridade . 3", 3, " . ", "*");
        // Criamos a visão completa aqui para checar depois se ela é atualizada
        assertEquals("[]", Arrays.toString(facade.exibirVisao(facade.criarVisaoTitulo("Doc1"))));
        facade.criarTitulo("Doc1", "Titulo p5", 5, 2, true);
        facade.criarDocumento("DocRef");
        facade.criarTexto("DocRef", "Elemento texto prioridade 5", 5);
        facade.criarAtalho("Doc1", "DocRef");
        assertEquals("[2. Titulo p5\n]", Arrays.toString(facade.exibirVisao(facade.criarVisaoTitulo("Doc1"))));
    }

}