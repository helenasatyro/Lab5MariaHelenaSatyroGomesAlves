package documin.documento;

public class VisaoResumida implements Visivel {
    private Documento doc;
    VisaoResumida(Documento doc) {
        this.doc = doc;
    }

    @Override
    public String[] exibirVisao() {
        String[] retorno = new String[doc.getElementos().size()];
        for (int i = 0; i < retorno.length; i++) {
            retorno[i] = doc.exibeElementoResumido(i);
        }
        return retorno;
    }
}
