package documin.documento;

import java.util.HashMap;

public class Elemento implements Cloneable {
    protected int prioridade;
    protected String valor;
    Elemento(int prioridade, String valor) {
        if (!(prioridade >= 1 && prioridade <= 5)) throw new IllegalArgumentException();
        this.prioridade = prioridade;
        this.valor = valor;
    }
    int getPrioridade() { return this.prioridade; }
    String getValor() { return this.valor; }

    String representacaoCompleta() {
        return getValor();
    }

    String representacaoCurta() {
        return getValor();
    }
}
