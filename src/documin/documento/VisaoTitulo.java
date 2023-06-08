package documin.documento;

import java.util.ArrayList;
import java.util.LinkedList;

public class VisaoTitulo implements Visivel {
    private Documento doc;
    VisaoTitulo(Documento doc) {
        this.doc = doc;
    }

    @Override
    public String[] exibirVisao() {
        ArrayList<String> retorno = new ArrayList<>();
        for (int i = 0; i < doc.getElementos().size(); i++) {
            if (doc.getElementos().get(i).getClass() == ElementoTitulo.class) {
                retorno.add(doc.exibeElementoResumido(i));
            }
        }
        String[] ret = new String[retorno.size()];
        for (int i = 0; i < retorno.size(); i++) {
            ret[i] = retorno.get(i);
        }
        return ret;
    }
}
