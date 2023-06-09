package documin.documento;
/**
 * Classe que implementa uma visualização de um documento. A visão resumida consiste em um array
 * de strings em que cada uma é a representação resumida de um elemento do documento, em ordem.
 */
public class VisaoResumida implements Visivel {
    private final Documento doc;
    /**
     * Cria uma visão baseada num documento.
     * Mudanças no documento após a criação da visão alteram a visão.
     * @param doc base da visão
     */
    public VisaoResumida(Documento doc) {
        this.doc = doc;
    }
    /**
     * Constrói a visão do documento consistindo nas representações resumidas de cada elemeto do documento.
     * @return array de strings com cada elemento do documento
     */
    @Override
    public String[] exibirVisao() {
        String[] retorno = new String[doc.getElementos().size()];
        for (int i = 0; i < retorno.length; i++) {
            retorno[i] = doc.exibeElementoResumido(i);
        }
        return retorno;
    }
}
