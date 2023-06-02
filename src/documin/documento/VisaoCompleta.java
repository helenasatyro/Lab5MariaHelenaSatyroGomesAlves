package documin.documento;

public class VisaoCompleta implements Visivel {
    private Documento doc;
    VisaoCompleta(Documento doc) {
        this.doc = doc;
    }
    @Override
    public String[] exibirVisao() {
        String[] retorno = new String[doc.getElementos().size()];
        for (int i = 0; i < retorno.length; i++) {
            retorno[i] = doc.exibeElementoCompleto(i);
        }
        return retorno;
    }
}