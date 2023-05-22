package documin.documento;

import java.util.HashMap;

public abstract class Elemento implements Cloneable {
    protected int prioridade;
    protected String valor;
    protected HashMap<String,String> propriedades;
    Elemento(int prioridade, String valor) {
        if (!(prioridade >= 1 && prioridade <= 5)) throw new IllegalArgumentException();
        this.prioridade = prioridade;
        this.valor = valor;
        this.propriedades = new HashMap<String,String>();
    }
    int getPrioridade() { return this.prioridade; }
    String getValor() { return this.valor; }


    abstract String representacaoCompleta();
    abstract String representacaoCurta();
}
