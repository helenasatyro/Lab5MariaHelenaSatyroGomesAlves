package documin.documento;

public class ElementoAtalho extends Elemento {
    private Documento doc;
    public ElementoAtalho(Documento doc) {
        super(calculaPrioridade(doc), doc.getTitulo());
        this.doc = doc;
    }

    private static int calculaPrioridade(Documento doc) {
        int sum = 0;
        for (Elemento el: doc.getElementos()) {
            if (el.getClass() == ElementoAtalho.class) throw new IllegalStateException("Não se pode linkar atalhos dentro de atalhos.");
            sum += el.getPrioridade();
        }
        return sum / (doc.getNumElementos());
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
            if (el.getPrioridade() >= 4) { retorno += el.representacaoCurta() + "\n"; }
        }
        return retorno;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
