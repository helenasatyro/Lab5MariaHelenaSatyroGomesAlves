package documin.documento;

/**
 * Classe que implementa uma visualização de um documento. A visão prioritária consiste em um array
 * de strings em que cada uma é a representação completa dos elementos de um documenot cuja prioridade é
 * maior ou igual ao valor passado como parâmetro.
 * @author Maria Helena Sátyro Gomes Alves
 */
import java.util.ArrayList;

public class VisaoPrioritaria implements Visivel {
    private Documento doc;
    private int prioridade;
    /**
     * Cria uma visão baseada num documento.
     * Mudanças no documento após a criação da visão alteram a visão.
     * @param doc base da visão
     * @param prioridade mínima para que o elemento seja representado na visão
     */
    public VisaoPrioritaria(Documento doc, int prioridade) {
        this.doc = doc;
        this.prioridade = prioridade;
    }
    /**
     * Constrói a visão do documento consistindo nas representações completas
     * de cada elemeto do documento cuja prioridade é maior ou igual ao valor passado como parâmetro.
     * @return array de strings com cada elemento do documento
     */
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
