package documin.tests;
import documin.documento.Documento;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ElementoAtalhoTest {
    // Os testes de atalho dependem da existência de mais de um documento,
    // portanto tiveram que ser feitos através de documentos
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