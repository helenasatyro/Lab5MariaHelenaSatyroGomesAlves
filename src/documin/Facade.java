package documin;

import documin.documento.DocumentoController;


public class Facade {

    private DocumentoController documentoController;

    public Facade() {
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
        return documentoController.exibeDocumento(titulo); aaaaa;
    }
    int criarTexto(String tituloDoc, String valor, int prioridade) {
        return documentoController.addTexto(tituloDoc, valor, prioridade);
    }
    int criarTitulo(String tituloDoc, String valor, int prioridade, int nivel, boolean linkavel {
        return documentoController.addTitulo(tituloDoc, valor, prioridade, nivel, linkavel);
    }
    int criarLista(String tituloDoc, String valorLista, int prioridade, String separador, String charLista) {
        return documentoController.addLista(tituloDoc, valorLista, prioridade, separador, charLista)
    }
    int criarTermos(String tituloDoc, String valorTermos, int prioridade, String separador, String ordem) {
        return documentoController.addTermos(tituloDoc, valorTermos, prioridade, separador, ordem);
    }
    int criarAtalho(String tituloDoc, String tituloDocReferenciado) {
        return documentoController.add
    }
    String pegarRepresentacaoCompleta(String tituloDoc, int elementoPosicao) {

    }
    String pegarrepresentacaoResumida(String tituloDoc, int elementoPosicao)
    boolean apagarElemento(String tituloDoc, int elementoPosicao)
    void moverParaCima(String tituloDoc, int elementoPosicao)
    void moverParaBaixo(String tituloDoc, int elementoPosicao)





}
