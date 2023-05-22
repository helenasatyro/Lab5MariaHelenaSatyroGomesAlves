package documin.documento;

public class ElementoAtalho extends Elemento {
    private Documento doc;
    private String reprCompleta;
    private String reprResumida;
    public ElementoAtalho(String valor, int prioridade, Documento doc) {
        this.doc = doc;
    }

    @Override
    String representacaoCompleta() {
        String retorno = "";
        for (Elemento el: doc.getElementos()) {
            if (el.getPrioridade() >= 4) { retorno += el.representacaoCompleta() + "\n"; }
        }

        return retorno;
    }

    @Override
    String representacaoCurta() {
        String retorno = "";
        for (Elemento el: doc.getElementos()) {
            if (el.getPrioridade() >= 4) { retorno += el.representacaoCompleta() + "\n"; }
        }

        return retorno;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
