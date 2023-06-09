package documin.tests;

import documin.documento.Documento;
import documin.documento.VisaoCompleta;
import documin.documento.VisaoPrioritaria;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VisaoPrioritariaTest {
    @Test
    void testaVisaoPrioritaria() {
        Documento dcUm = new Documento("Doc1");
        dcUm.criarTexto("Elemento texto prioridade 1", 1);
        dcUm.criarTermos("Elemento / termos / prioridade 4", 4, "/", "nenhum");
        dcUm.criarLista("Lista . prioridade . 3", 3, " . ", "*");
        // Criamos a visão prioritaria aqui para checar depois se ela é atualizada
        VisaoPrioritaria v = new VisaoPrioritaria(dcUm, 4);
        assertEquals("""
                [Total termos: 3
                - Elemento ,  termos ,  prioridade 4
                ]""", Arrays.toString(v.exibirVisao()));
        dcUm.criarTitulo("Titulo p5", 5, 2, true);
        Documento dcRef = new Documento("DocRef", 3);
        dcRef.criarTexto("Elemento texto prioridade 5", 5);
        dcUm.criarAtalho(dcRef);
        assertEquals("""
                [Total termos: 3
                - Elemento ,  termos ,  prioridade 4
                , 2. Titulo p5 -- 2-TITULOP5
                , Elemento texto prioridade 5
                ]""", Arrays.toString(v.exibirVisao()));

    }

}