package documin;

import documin.documento.DocumentoController;
import documin.visao.VisaoController;


public class Facade {

    private final DocumentoController documentoController;
    private final VisaoController visaoController;

    public Facade() {
        this.visaoController = new VisaoController();
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
        return documentoController.exibeDocumento(titulo);
    }
    int criarTexto(String tituloDoc, String valor, int prioridade) {
        return documentoController.addTexto(tituloDoc, valor, prioridade);
    }
    int criarTitulo(String tituloDoc, String valor, int prioridade, int nivel, boolean linkavel) {
        return documentoController.addTitulo(tituloDoc, valor, prioridade, nivel, linkavel);
    }
    int criarLista(String tituloDoc, String valorLista, int prioridade, String separador, String charLista) {
        return documentoController.addLista(tituloDoc, valorLista, prioridade, separador, charLista);
    }
    int criarTermos(String tituloDoc, String valorTermos, int prioridade, String separador, String ordem) {
        return documentoController.addTermos(tituloDoc, valorTermos, prioridade, separador, ordem);
    }
    int criarAtalho(String tituloDoc, String tituloDocReferenciado) {
        return documentoController.addAtalho(tituloDoc, tituloDocReferenciado);
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
    String[] exibirVisao(int visaoId) {
        return visaoController.exibirVisao(visaoId);
    }
    int criarVisaoCompleta(String tituloDoc) {
        return visaoController.criarVisaoCompleta(tituloDoc, documentoController);
    }
    int criarVisaoResumida(String tituloDoc) {
        return visaoController.criarVisaoResumida(tituloDoc, documentoController);
    }
    int criarVisaoPrioritaria(String tituloDoc, int prioridade) {
        return visaoController.criarVisaoPrioritaria(tituloDoc, prioridade, documentoController);
    }
    int criarVisaoTitulo(String tituloDoc) {
        return visaoController.criarVisaoTitulo(tituloDoc, documentoController);
    }



}
