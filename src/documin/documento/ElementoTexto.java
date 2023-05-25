package documin.documento;

public class ElementoTexto extends Elemento {
    ElementoTexto(String valor, int prioridade) {
        super(prioridade, valor);
    }

    @Override
    String representacaoCompleta() {
        return getValor();
    }

    @Override
    String representacaoCurta() {
        return getValor();
    }

}
