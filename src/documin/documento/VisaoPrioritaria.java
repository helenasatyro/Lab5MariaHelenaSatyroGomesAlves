package documin.documento;

import java.util.ArrayList;
import java.util.LinkedList;

public class VisaoPrioritaria implements Visivel {
    private Documento doc;
    private int prioridade;
    VisaoPrioritaria(Documento doc, int prioridade) {
        this.doc = doc;
        this.prioridade = prioridade;
    }

    @Override
    public String[] exibirVisao() {
        ArrayList<String> retorno = new ArrayList<>();
        for (int i = 0; i < doc.getNumElementos(); i++) {
            if (doc.getPrioridade(i) >= prioridade) {
                retorno.add(doc.exibeElementoCompleto(i));
            }
        }
        String[] ret = new String[retorno.size()];
        for (int i = 0; i < retorno.size(); i++) {
            ret[i] = retorno.get(i);
        }
        return ret;
    }
}
