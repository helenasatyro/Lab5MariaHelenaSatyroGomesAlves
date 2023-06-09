package documin.tests;

import documin.documento.Documento;
import documin.documento.VisaoCompleta;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VisaoCompletaTest {
    @Test
    void testaExibirVisaoCompleta() {
        Documento dcUm = new Documento("Doc1");
        dcUm.criarTexto("Elemento texto prioridade 1", 1);
        dcUm.criarTermos("Elemento / termos / prioridade 4", 4, "/", "nenhum");
        dcUm.criarLista("Lista . prioridade . 3", 3, " . ", "*");
        // Criamos a visão completa aqui para checar depois se ela é atualizada
        VisaoCompleta v = new VisaoCompleta(dcUm);
        assertEquals("""
                [Elemento texto prioridade 1
                , Total termos: 3
                - Elemento ,  termos ,  prioridade 4
                , * Lista
                * prioridade
                * 3
                ]""", Arrays.toString(v.exibirVisao()));
        dcUm.criarTitulo("Titulo p5", 5, 2, true);
        Documento dcRef = new Documento("DocRef", 3);
        dcRef.criarTexto("Elemento texto prioridade 5", 5);
        dcUm.criarAtalho(dcRef);
        assertEquals("""
                [Elemento texto prioridade 1
                , Total termos: 3
                - Elemento ,  termos ,  prioridade 4
                , * Lista
                * prioridade
                * 3
                , 2. Titulo p5 -- 2-TITULOP5
                , Elemento texto prioridade 5
                ]""", Arrays.toString(v.exibirVisao()));

    }
}