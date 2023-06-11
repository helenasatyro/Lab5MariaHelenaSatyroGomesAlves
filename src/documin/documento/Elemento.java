package documin.documento;

/**
 * Classe que representa um elemento básico de texto que pode ser adicionado a um documento.
 * Um elemento deve ter uma prioridade (1 a 5 inclusive) e um valor.
 * @author Maria Helena Sátyro Gomes Alves
 */
public class Elemento {
    protected int prioridade;
    protected String valor;

    /**
     * Cria um elemento de texto básico que tem uma prioridade e um valor textual
     * @param prioridade do elemento
     * @param valor conteúdo textual do elemento
     */
    public Elemento(int prioridade, String valor) {
        if (!(prioridade >= 1 && prioridade <= 5)) throw new IllegalArgumentException();
        this.prioridade = prioridade;
        this.valor = valor;
    }

    /**
     * Retorna a prioridade do elemento (1 a 5)
     * @return inteiro de 1 a 5
     */
    public int getPrioridade() { return this.prioridade; }

    /**
     * Retorna a representação completa do elemento.
     * @return valor + \n
     */
    public String representacaoCompleta() {
        return valor + "\n";
    }
    /**
     * Retorna a representação curta do elemento.
     * @return valor + \n
     */
    public String representacaoCurta() {
        return valor + "\n";
    }
}