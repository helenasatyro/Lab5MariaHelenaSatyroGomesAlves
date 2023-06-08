package documin.documento;

/**
 * Classe que representa um elemento atalho. Um elemento atalho serve para representar um documento dentro de outro.
 * O atalho tem sua prioridade calculada a partir das prioridades dos elementos do documento que referencia.
 * Um atalho não pode conter atalhos, e documentos que contenham atalhos não podem se tornar atalhos.
 */
public class ElementoAtalho extends Elemento {
    private final Documento doc;

    /**
     * Cria um elemento do tipo atalho, calcula sua prioridade com base na média das prioridades do documento referenciado.
     * @param doc documento referenciado
     */
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

    /**
     * Redefine o estado de atalho do elemento.
     * @return
     */
    public void setEhAtalho(boolean at) {
        this.doc.setEhAtalho(at);
    }

    /**
     * Representação completa do documento referenciado
     * @return a representação completa de cada elemento do documento rferenciado com prioridade maior que 4
     */
    @Override
    public String representacaoCompleta() {
        String retorno = "";
        for (Elemento el: doc.getElementos()) {
            if (el.getPrioridade() >= 4) { retorno += el.representacaoCompleta(); }
        }
        return retorno;
    }
    /**
     * Representação curta do documento referenciado
     * @return a representação curta de cada elemento do documento rferenciado com prioridade maior que 4
     */
    @Override
    public String representacaoCurta() {
        String retorno = "";
        for (Elemento el: doc.getElementos()) {
            if (el.getPrioridade() >= 4) { retorno += el.representacaoCurta(); }
        }
        return retorno;
    }
}
