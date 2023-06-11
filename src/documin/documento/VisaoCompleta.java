package documin.documento;

/**
 * Classe que implementa uma visualização de um documento. A visão completa consiste em um array
 * de strings em que cada uma é a representação completa de um elemento do documento, em ordem.
 * @author Maria Helena Sátyro Gomes Alves
 */
public class VisaoCompleta implements Visivel {
    private Documento doc;

    /**
     * Cria uma visão baseada num documento.
     * Mudanças no documenot após a criação da visão alteram a visão.
     * @param doc base da visão
     */
    public VisaoCompleta(Documento doc) {
        this.doc = doc;
    }
    /**
     * Constrói a visão do documento consistindo nas representações completas de cada elemeto do documento.
     * @return array de strings com cada elemento do documento
     */
    @Override
    public String[] exibirVisao() {
        String[] retorno = new String[doc.getElementos().size()];
        for (int i = 0; i < retorno.length; i++) {
            retorno[i] = doc.exibeElementoCompleto(i);
        }
        return retorno;
    }
}