package documin.documento;

import java.util.HashSet;
import java.util.NoSuchElementException;

public class DocumentoController {

    private HashSet<Documento> documentos;

    public boolean criarDocumento(String titulo, int tamanho) {
        if (titulo.isBlank()) throw new IllegalArgumentException();
        return documentos.add(new Documento(titulo, tamanho));
    }
    public boolean criarDocumento(String titulo) {
        if (titulo.isBlank()) throw new IllegalArgumentException();
        return documentos.add(new Documento(titulo));
    }
    public void removeDocumento(String titulo) {
        Documento doc = buscaPorTitulo(titulo);
        if (doc == null ) throw new NoSuchElementException();
        documentos.remove(doc);
    }

    public String[] exibeDocumento(String titulo) {
        Documento doc = buscaPorTitulo(titulo);
        if (doc == null ) throw new NoSuchElementException();
        return doc.exibir();
    }

    public int addTexto(String titulo, String valor, int prioridade) {
        Documento doc = buscaPorTitulo(titulo);
        if (doc == null ) throw new NoSuchElementException();
        return doc.addTexto(valor, prioridade);
    }
    public int addTitulo(String tituloDoc, String valor, int prioridade, int nivel, boolean linkavel) {
        Documento doc = buscaPorTitulo(tituloDoc);
        if (doc == null ) throw new NoSuchElementException();
        return doc.addTitulo(valor, prioridade, nivel, linkavel);
    }
    public int addTermos(String tituloDoc,String valor, int prioridade, String separador, String ordem) {
        Documento doc = buscaPorTitulo(tituloDoc);
        if (doc == null ) throw new NoSuchElementException();
        return doc.addTermos(valor, prioridade, separador, ordem);
    }
    public int addLista(String tituloDoc,String valor, int prioridade, String separador, String caractere) {
        Documento doc = buscaPorTitulo(tituloDoc);
        if (doc == null ) throw new NoSuchElementException();
        return doc.addLista(valor, prioridade, separador, caractere);
    }

    public int addAtalho(String tituloDoc,String docReferenciado) {
        Documento doc = buscaPorTitulo(tituloDoc);
        Documento docRef = buscaPorTitulo(docReferenciado);
        if (doc == null || docRef == null ) throw new NoSuchElementException();
        return doc.addAtalho(docRef);
    }

    public int getNumeroElementos(String titulo) {
        Documento doc = buscaPorTitulo(titulo);
        if (doc == null ) throw new NoSuchElementException();
        return doc.getNumElementos();
    }
    private Documento buscaPorTitulo(String titulo) {
        if (titulo.isBlank()) throw new IllegalArgumentException();
        for (Documento d: documentos) {
            if (d.getTitulo().equals(titulo)) return d;
        }
        return null;
    }

    public String exibeElementoResumido(String tituloDoc, int elementoPosicaoReal) {
        Documento doc = buscaPorTitulo(tituloDoc);
        if (doc == null ) throw new NoSuchElementException();
        return doc.exibeElementoResumido(elementoPosicaoReal);
    }

    public String exibeElementoCompleto(String tituloDoc, int elementoPosicaoReal) {
        Documento doc = buscaPorTitulo(tituloDoc);
        if (doc == null ) throw new NoSuchElementException();
        return doc.exibeElementoCompleto(elementoPosicaoReal);
    }
}
