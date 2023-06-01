package documin.visao;

import documin.documento.Documento;
import documin.documento.Elemento;

import java.util.LinkedList;

public class VisaoCompleta implements Visivel {
    private Documento doc;
    VisaoCompleta(Documento doc) {
        this.doc = doc;
    }
// editar, colocar pra receber elementos por parametro e acessar suas representacoes por controller
    @Override
    public String[] exibirVisao(Elemento[] elementos) {
        LinkedList<Elemento> elementos = doc.getElementos();
        String[] retorno = new String[elementos.size()];
        for (int i = 0; i < elementos.size(); i++) {
            retorno[i] = elementos.get(i).representacaoCompleta();
        }
        return retorno;
    }
}