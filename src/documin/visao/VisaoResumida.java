package documin.visao;

import documin.documento.Documento;

public class VisaoResumida implements Visivel {
    private Documento doc;
    VisaoResumida(Documento doc) {
        this.doc = doc;
    }

    @Override
    public String[] exibirVisao() {
        return doc.exibir();
    }
}
