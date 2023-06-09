package documin.documento;

import java.util.ArrayList;
/**
 * Classe que implementa uma visualização de um documento. A visão título consiste em um array
 * de strings em que cada uma é a representação resumida dos elementos do tipo título de um documento.
 */
public class VisaoTitulo implements Visivel {
    private Documento doc;
    /**
     * Cria uma visão baseada num documento.
     * Mudanças no documento após a criação da visão alteram a visão.
     * @param doc base da visão
     */
    public VisaoTitulo(Documento doc) {
        this.doc = doc;
    }
    /**
     * Constrói a visão do documento consistindo nas representações resumidas
     * de cada elemento do tipo título de um documento.
     * @return array de strings com cada elemento título do documento
     */
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
