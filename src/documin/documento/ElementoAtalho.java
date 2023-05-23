package documin.documento;

public class ElementoAtalho extends Elemento {
    private Documento doc;
    private String reprCompleta;
    private String reprCurta;
    public ElementoAtalho(Documento doc) {
        super(calculaPrioridade(doc), doc.getTitulo());
        this.reprCurta = formataRepresentacaoCurta(doc);
        this.reprCompleta = formataRepresentacaoCompleta(doc);
    }

    private static String formataRepresentacaoCurta(Documento doc) {
        String retorno = "";
        for (Elemento el: doc.getElementos()) {
            if (el.getPrioridade() >= 4) { retorno += el.representacaoCurta() + "\n"; }
        }
        return retorno;
    }

    private static String formataRepresentacaoCompleta(Documento doc) {
        String retorno = "";
        for (Elemento el: doc.getElementos()) {
            if (el.getPrioridade() >= 4) { retorno += el.representacaoCompleta() + "\n"; }
        }
        return retorno;
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
        return reprCompleta;
    }

    @Override
    String representacaoCurta() {
        return reprCurta;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
