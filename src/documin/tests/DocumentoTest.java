package documin.tests;

import documin.documento.Documento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;

class DocumentoTest {
    private Documento dcDois;

    @BeforeEach
    void setUp() {
        this.dcDois = new Documento("Doc2", 3);
    }

    @Test
    void testaCriarDocIlimitado() {
        Documento dcUm = new Documento("Doc1");
        assertEquals("[]", Arrays.toString(dcUm.exibir()));
    }

    @Test
    void testaCriarDocLimitado() {
        Documento dcUm = new Documento("Doc1",5);
        assertEquals("[]", Arrays.toString(dcUm.exibir()));
    }
    @Test
    void testaCriarDocNomeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Documento(""));
        assertThrows(IllegalArgumentException.class, () -> new Documento("", 1));
        assertThrows(IllegalArgumentException.class, () -> new Documento("    "));
        assertThrows(IllegalArgumentException.class, () -> new Documento("    ", 1));
    }

    @Test
    void testaCriarDocTamanhoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Documento("Doc1", 0));
        assertThrows(IllegalArgumentException.class, () -> new Documento("Doc1", -5));
    }

    @Test
    void testaCriaElementoNormal() {
        assertEquals(0, dcDois.criarTexto("Texto texto", 1 ));
    }

    @Test
    void testaCriaElementoLimite() {
        assertEquals(0, dcDois.criarTexto("Texto texto", 1 ));
        assertEquals(1, dcDois.criarTexto( "Texto texto", 1 ));
        assertEquals(2, dcDois.criarTexto("Texto texto", 1 ));
    }
    @Test
    void testaCriaElementoAcimaLimite() {
        Documento dcUm = new Documento("DcUm");
        dcUm.criarTexto("OIOI", 1);
        assertEquals(0, dcDois.criarTexto("Texto texto", 1 ));
        assertEquals(1, dcDois.criarTexto("Texto texto", 1 ));
        assertEquals(2, dcDois.criarTexto("Texto texto", 1 ));
        assertThrows(IllegalStateException.class, () -> dcDois.criarTexto( "Texto texto", 1 ));
        assertThrows(IllegalStateException.class, () -> dcDois.criarAtalho(dcUm));
        assertThrows(IllegalStateException.class, () -> dcDois.criarTitulo( "Texto texto", 1, 1, false));
        assertThrows(IllegalStateException.class, () -> dcDois.criarTermos( "Texto, texto", 1, ", ", "Nenhum" ));
        assertThrows(IllegalStateException.class, () -> dcDois.criarLista( "Texto, texto", 1, ", ", "*" ));
    }

    @Test
    void testaCriaElementoParamInvalido() {
        assertThrows(IllegalArgumentException.class, () -> dcDois.criarTexto("Texto texto", 0 ), "Não pode aceitar prioridade abaixo de 1");
        assertThrows(IllegalArgumentException.class, () -> dcDois.criarTexto("Texto texto", 6 ), "Não pode aceitar prioridade acima de 5.");
    }

    @Test
    void testaVerQuantidadeElementos() {
        assertEquals(0, dcDois.getNumElementos());
        dcDois.criarTexto( "Texto texto", 1 );
        assertEquals(1, dcDois.getNumElementos());
        dcDois.criarTexto( "Texto texto", 1 );
        assertEquals(2, dcDois.getNumElementos());
    }

    @Test
    void testaExibirDocumento() {
        assertEquals("[]", Arrays.toString(dcDois.exibir()));
        assertEquals(0, dcDois.exibir().length);
        dcDois.criarTexto("Texto texto", 1 );
        assertEquals("[Texto texto\n]", Arrays.toString(dcDois.exibir()));
        assertEquals(1, dcDois.exibir().length);
        dcDois.criarTexto("Mais Texto", 1 );
        assertEquals("[Texto texto\n, Mais Texto\n]", Arrays.toString(dcDois.exibir()));
        assertEquals(2, dcDois.exibir().length);
    }

    @Test
    void testaCriaTermos() {
        dcDois.criarTermos( "termo | outro | mais um", 1, "|", "alfabetica");
        assertEquals("Total termos: 3\n-  mais um,  outro , termo \n", dcDois.exibeElementoCompleto( 0));
        assertEquals(" mais um /  outro  / termo \n", dcDois.exibeElementoResumido(0));

        dcDois.criarTermos( "termo | um | mais um", 1, "|", "tamanho");
        assertEquals("Total termos: 3\n-  mais um, termo ,  um \n",
                dcDois.exibeElementoCompleto( 1));
        assertEquals(" mais um / termo  /  um \n", dcDois.exibeElementoResumido( 1));

        dcDois.criarTermos("termo | outro | mais um", 1, "|", "nenhum");
        assertEquals("""
                Total termos: 3
                - termo ,  outro ,  mais um
                """, dcDois.exibeElementoCompleto( 2));
        assertEquals("termo  /  outro  /  mais um\n", dcDois.exibeElementoResumido( 2));
    }

    @Test
    void testaCriaLista() {
        dcDois.criarLista( "um, dois, tres", 2, ", ", "*");
        assertEquals("* um\n* dois\n* tres\n", dcDois.exibeElementoCompleto( 0));
        assertEquals("um, dois, tres\n", dcDois.exibeElementoResumido( 0));
    }
    @Test
    void testaCriaTexto() {
        dcDois.criarTexto( "um, dois, tres", 2);
        assertEquals("um, dois, tres\n", dcDois.exibeElementoCompleto( 0));
        assertEquals("um, dois, tres\n", dcDois.exibeElementoResumido( 0));
    }
    @Test
    void testaCriaTitulo() {
        dcDois.criarTitulo( "um, dois, tres", 2, 2, true);
        assertEquals("2. um, dois, tres -- 2-UM,DOIS,TRES\n", dcDois.exibeElementoCompleto( 0));
        assertEquals("2. um, dois, tres\n", dcDois.exibeElementoResumido( 0));
        dcDois.criarTitulo( "um, dois, tres", 2, 2, false);
        assertEquals("2. um, dois, tres\n", dcDois.exibeElementoCompleto(1));
        assertEquals("2. um, dois, tres\n", dcDois.exibeElementoResumido( 1));
    }

    @Test
    void testaCriarAtalhoSemRestricao() {
        Documento dcUm = new Documento("Doc Com Atalho");
        dcUm.criarTexto("um dois tres", 2);

        Documento dcRefBaixo = new Documento("Doc Referenciado Baixa Prioridade");
        dcRefBaixo.criarTexto( "texto referenciado nao aparece", 2);
        assertEquals("[um dois tres\n]", Arrays.toString(dcUm.exibir()));


        Documento dcRef = new Documento("Doc Referenciado");
        dcRef.criarTexto("texto referenciado", 4);

        dcUm.criarAtalho(dcRef);
        assertEquals("[um dois tres\n, texto referenciado\n]", Arrays.toString(dcUm.exibir()));
        assertEquals(2, dcUm.getNumElementos());
    }
    @Test
    void testaCriarAtalhoElementoTemAtalho() {
        Documento dcRef = new Documento("Doc Referenciado");
        dcRef.criarTexto( "texto referenciado", 4);

        Documento dcUm = new Documento("Doc Com Atalho");
        dcUm.criarTexto("um dois tres", 2);
        dcUm.criarAtalho(dcRef);

        Documento novoDoc = new Documento("NovoDoc");
        assertThrows(IllegalStateException.class, () -> novoDoc.criarAtalho(dcUm));
    }

    @Test
    void testaCriarAtalhoSemElementos() {
        Documento dcRef = new Documento("Doc Referenciado");

        Documento dcUm = new Documento("Doc Com Atalho");
        dcUm.criarTexto("um dois tres", 2);
        // deve lançar excecão, pois não tem como calcular a prioridade
        assertThrows(IllegalArgumentException.class, () -> dcUm.criarAtalho( dcRef));

    }
    @Test
    void testaCriarAtalhoElementoEhAtalho() {
        Documento dcRef = new Documento("Doc Referenciado");
        dcRef.criarTexto("texto referenciado", 4);

        Documento dcUm = new Documento("Doc Com Atalho");
        dcUm.criarTexto("um dois tres", 2);
        dcUm.criarAtalho(dcRef);

        Documento novoDoc = new Documento("NovoDoc");
        novoDoc.criarTexto("texto", 3);
        assertThrows(IllegalStateException.class, () -> dcRef.criarAtalho(novoDoc));
    }
    @Test
    void testaCriarDoisAtalhos() {
        Documento dcRef = new Documento("Doc Referenciado");
        dcRef.criarTexto( "texto referenciado", 4);
        Documento dcRef2 = new Documento("Doc Referenciado");
        dcRef2.criarTexto("texto referenciado 2", 4);

        Documento dcUm = new Documento("Doc Com Atalho");
        dcUm.criarTexto( "um dois tres", 2);

        dcUm.criarAtalho(dcRef);
        dcUm.criarAtalho(  dcRef2);

        assertEquals("""
                [um dois tres
                , texto referenciado
                , texto referenciado 2
                ]""", Arrays.toString(dcUm.exibir()));
    }

    @Test
    void testaExibirAtalhoCompleto() {
        Documento dcRef = new Documento("Doc Referenciado");
        dcRef.criarLista( "um, dois, tres", 5, ", ", "*");

        Documento dcUm = new Documento("Doc Com Atalho");
        dcUm.criarTexto( "um dois tres", 5);
        dcUm.criarAtalho(dcRef);

        assertEquals("* um\n* dois\n* tres\n", dcUm.exibeElementoCompleto( 1));
    }

    @Test
    void testaMoveParaCima() {
        Documento dcUm = new Documento("Doc1", 3);
        dcUm.criarTexto( "texto1", 5);
        dcUm.criarTexto( "texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibir()));

        dcUm.moveAcima( 1);
        assertEquals("[texto2\n, texto1\n]", Arrays.toString(dcUm.exibir()));
    }
    @Test
    void testaMoveParaCimaPrimeiro() {
        Documento dcUm = new Documento("Doc1", 3);
        dcUm.criarTexto("texto1", 5);
        dcUm.criarTexto("texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibir()));

        dcUm.moveAcima(0);
        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibir()));
    }
    @Test
    void testaMoveParaBaixo() {
        Documento dcUm = new Documento("Doc1", 3);
        dcUm.criarTexto( "texto1", 5);
        dcUm.criarTexto("texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibir()));

        dcUm.moveAbaixo(0);
        assertEquals("[texto2\n, texto1\n]", Arrays.toString(dcUm.exibir()));
    }
    @Test
    void testaMoveParaBaixoUltimo() {
        Documento dcUm = new Documento("Doc1", 3);
        dcUm.criarTexto("texto1", 5);
        dcUm.criarTexto("texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibir()));

        dcUm.moveAbaixo( 1);
        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibir()));
    }

    @Test
    void TestaRemoveElemento() {
        Documento dcUm = new Documento("Doc1", 3);
        dcUm.criarTexto("texto1", 5);
        dcUm.criarTexto("texto2", 5);

        assertEquals("[texto1\n, texto2\n]", Arrays.toString(dcUm.exibir()));

        dcUm.removeElemento( 1);
        assertEquals(1, dcUm.getNumElementos());
        assertEquals("[texto1\n]", Arrays.toString(dcUm.exibir()));

        dcUm.removeElemento( 0);
        assertEquals("[]", Arrays.toString(dcUm.exibir()));
    }
    @Test
    void TestaRemoverElementoAtalhoPermiteAdicionarAtalhosAoDocumentoReferenciado() {
        Documento dcRef = new Documento("DocRef", 3);
        dcRef.criarTexto("texto1", 5);

        // deve ser possível adicionar um atalho ao doc2 referenciando o docref
        Documento dcDois = new Documento("Doc2");
        dcDois.criarTexto("texto2", 5);
        dcDois.criarAtalho( dcRef);
        assertEquals("[texto2\n, texto1\n]", Arrays.toString(dcDois.exibir()));

        // então docref não poderá receber atalhos
        Documento dcTres = new Documento("Doc3");
        dcTres.criarTexto("texto3", 5);
        assertThrows(IllegalStateException.class, () -> dcRef.criarAtalho(dcTres));

        // removemos o atalho de doc2 que referencia docRef
        dcDois.removeElemento( 1);
        dcRef.criarAtalho( dcTres);

        // então podemos adicionar atalhos ao docRef
        assertEquals("[texto1\n, texto3\n]", Arrays.toString(dcRef.exibir()));
    }
}