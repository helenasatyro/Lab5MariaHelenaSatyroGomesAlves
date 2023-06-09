package documin.tests;

import documin.documento.Documento;
import documin.documento.VisaoResumida;
import documin.documento.VisaoTitulo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VisaoTituloTest {
    @Test
    void testaVisaoTitulo() {
        Documento dcUm = new Documento("Doc1");
        dcUm.criarTexto("Elemento texto prioridade 1", 1);
        dcUm.criarTermos("Elemento / termos / prioridade 4", 4, "/", "nenhum");
        dcUm.criarLista("Lista . prioridade . 3", 3, " . ", "*");

        // Criamos a visão titulo aqui para checar depois se ela é atualizada
        VisaoTitulo v = new VisaoTitulo(dcUm);
        assertEquals("[]", Arrays.toString(v.exibirVisao()));
        dcUm.criarTitulo("Titulo p5", 5, 2, true);
        Documento dcRef = new Documento("DocRef", 3);
        dcRef.criarTexto("Elemento texto prioridade 5", 5);
        dcUm.criarAtalho(dcRef);
        assertEquals("[2. Titulo p5\n]", Arrays.toString(v.exibirVisao()));
    }

}