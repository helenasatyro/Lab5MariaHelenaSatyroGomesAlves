package documin.documento;

import java.util.HashSet;
import java.util.NoSuchElementException;

public class DocumentoController {

    private HashSet<Documento> documentos;

    public DocumentoController() {
        this.documentos = new HashSet<>();
    }

    public boolean criarDocumento(String titulo, int tamanho) {
        if (titulo.isEmpty() || titulo.isBlank()) throw new IllegalArgumentException();
        return documentos.add(new Documento(titulo, tamanho));
    }
    public boolean criarDocumento(String titulo) {
        if (titulo.isEmpty() || titulo.isBlank()) throw new IllegalArgumentException();
        return documentos.add(new Documento(titulo));
    }
    public void removeDocumento(String titulo) {
        Documento doc = buscaPorTitulo(titulo);
        documentos.remove(doc);
    }

    public String[] exibirDocumento(String titulo) {
        Documento doc = buscaPorTitulo(titulo);
        return doc.exibir();
    }

    public int criarTexto(String tituloDoc, String valor, int prioridade) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.addTexto(valor, prioridade);
    }
    public int criarTitulo(String tituloDoc, String valor, int prioridade, int nivel, boolean linkavel) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.addTitulo(valor, prioridade, nivel, linkavel);
    }
    public int criarTermos(String tituloDoc, String valor, int prioridade, String separador, String ordem) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.addTermos(valor, prioridade, separador, ordem);
    }
    public int criarLista(String tituloDoc, String valor, int prioridade, String separador, String caractere) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.addLista(valor, prioridade, separador, caractere);
    }

    public int criarAtalho(String tituloDoc, String docReferenciado) {
        Documento doc = buscaPorTitulo(tituloDoc);
        Documento docRef = buscaPorTitulo(docReferenciado);
        return doc.addAtalho(docRef);
    }

    public int getNumeroElementos(String titulo) {
        Documento doc = buscaPorTitulo(titulo);
        return doc.getNumElementos();
    }
    private Documento buscaPorTitulo(String titulo) {
        if (titulo.isEmpty() || titulo.isBlank()) throw new IllegalArgumentException();
        for (Documento d: documentos) {
            if (d.getTitulo().equals(titulo)) return d;
        }
        throw new NoSuchElementException();
    }

    public String exibeElementoResumido(String tituloDoc, int elementoPosicaoReal) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.exibeElementoResumido(elementoPosicaoReal);
    }

    public String exibeElementoCompleto(String tituloDoc, int elementoPosicaoReal) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.exibeElementoCompleto(elementoPosicaoReal);
    }

    public boolean removeElemento(String tituloDoc, int elementoPosicao) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.removeElemento(elementoPosicao);
    }

    public void moverAcima(String tituloDoc, int elementoPosicaoReal) {
        Documento doc = buscaPorTitulo(tituloDoc);
        doc.moveAcima(elementoPosicaoReal);
    }
    public void moverAbaixo(String tituloDoc, int elementoPosicaoReal) {
        Documento doc = buscaPorTitulo(tituloDoc);
        doc.moveAbaixo(elementoPosicaoReal);
    }
}
