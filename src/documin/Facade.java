package documin;

import documin.documento.DocumentoController;


public class Facade {

    private final DocumentoController documentoController;
    //private final VisaoController visaoController;

    public Facade() {
        //this.visaoController = new VisaoController();
        this.documentoController = new DocumentoController();
    }

    boolean criarDocumento(String titulo) {
        return this.documentoController.criarDocumento(titulo);
    }
    boolean criarDocumento(String titulo, int tamanhoMaximo) {
        return this.documentoController.criarDocumento(titulo, tamanhoMaximo);
    }
    void removerDocumento(String titulo) {
        this.documentoController.removeDocumento(titulo);
    }
    int contarElementos(String titulo) {
        return documentoController.getNumeroElementos(titulo);
    }
    String[] exibirDocumento(String titulo) {
        return documentoController.exibirDocumento(titulo);
    }
    int criarTexto(String tituloDoc, String valor, int prioridade) {
        return documentoController.criarTexto(tituloDoc, valor, prioridade);
    }
    int criarTitulo(String tituloDoc, String valor, int prioridade, int nivel, boolean linkavel) {
        return documentoController.criarTitulo(tituloDoc, valor, prioridade, nivel, linkavel);
    }
    int criarLista(String tituloDoc, String valorLista, int prioridade, String separador, String charLista) {
        return documentoController.criarLista(tituloDoc, valorLista, prioridade, separador, charLista);
    }
    int criarTermos(String tituloDoc, String valorTermos, int prioridade, String separador, String ordem) {
        return documentoController.criarTermos(tituloDoc, valorTermos, prioridade, separador, ordem);
    }
    int criarAtalho(String tituloDoc, String tituloDocReferenciado) {
        return documentoController.criarAtalho(tituloDoc, tituloDocReferenciado);
    }
    String pegarRepresentacaoCompleta(String tituloDoc, int elementoPosicao) {
        return documentoController.exibeElementoCompleto(tituloDoc, elementoPosicao);

    }
    String pegarRepresentacaoResumida(String tituloDoc, int elementoPosicao) {
        return documentoController.exibeElementoResumido(tituloDoc, elementoPosicao);
    }
    boolean apagarElemento(String tituloDoc, int elementoPosicao) {
        return documentoController.removeElemento(tituloDoc, elementoPosicao);
    }
    void moverParaCima(String tituloDoc, int elementoPosicao) {
        documentoController.moverAcima(tituloDoc, elementoPosicao);
    }
    void moverParaBaixo(String tituloDoc, int elementoPosicao) {
        documentoController.moverAbaixo(tituloDoc, elementoPosicao);

    }

}
