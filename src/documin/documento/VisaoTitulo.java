package documin.documento;

import java.util.LinkedList;

public class VisaoTitulo implements Visivel {
    private Documento doc;
    VisaoTitulo(Documento doc) {
        this.doc = doc;
    }

    @Override
    public String[] exibirVisao() {
        LinkedList<Elemento> elementos = doc.getElementos();
        String[] retorno = new String[elementos.size()];
        for (int i = 0; i < elementos.size(); i++) {
            if (elementos.get(i).getClass() == ElementoTitulo.class) {
                retorno[i] = elementos.get(i).representacaoCurta();
            }
        }
        return retorno;
    }
}
