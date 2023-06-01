package documin.visao;

import documin.documento.Documento;
import documin.documento.Elemento;

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
        LinkedList<Elemento> elementos = doc.getElementos();
        String[] retorno = new String[elementos.size()];
        for (int i = 0; i < elementos.size(); i++) {
            if (elementos.get(i).getPrioridade() >= prioridade) {
                retorno[i] = elementos.get(i).representacaoCurta();
            }
        }
        return retorno;
    }
}
