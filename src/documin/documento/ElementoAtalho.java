package documin.documento;

public class ElementoAtalho extends Elemento {
    private final Documento doc;
    public ElementoAtalho(Documento doc) {
        super(calculaPrioridade(doc), doc.getTitulo());
        this.doc = doc;
        doc.setEhAtalho(true);
    }

    private static int calculaPrioridade(Documento doc) {
        validador(doc);
        int sum = 0;
        for (Elemento el: doc.getElementos()) {
            sum += el.getPrioridade();
        }
        if (doc.getNumElementos() == 0) return 0;
        return sum / (doc.getNumElementos());
    }
    private static void validador(Documento doc) {
        for (Elemento el: doc.getElementos()) {
            if (el.getClass() == ElementoAtalho.class) throw new IllegalStateException("Não se pode linkar atalhos dentro de atalhos.");
        }
    }

    @Override
    String representacaoCompleta() {
        String retorno = "";
        for (Elemento el: doc.getElementos()) {
            if (el.getPrioridade() >= 4) { retorno += el.representacaoCompleta(); }
        }
        return retorno;
    }

    @Override
    String representacaoCurta() {
        String retorno = "";
        for (Elemento el: doc.getElementos()) {
            if (el.getPrioridade() >= 4) { retorno += el.representacaoCurta(); }
        }
        return retorno;
    }
}
