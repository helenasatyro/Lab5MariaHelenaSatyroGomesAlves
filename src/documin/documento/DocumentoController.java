package documin.documento;

import java.util.HashSet;
import java.util.NoSuchElementException;

public class DocumentoController {

    private HashSet<Documento> documentos;

    public boolean addDocumento(String titulo, int tamanho) {
        if (titulo.isBlank()) throw new IllegalArgumentException();
        return documentos.add(new Documento(titulo, tamanho));
    }
    public boolean addDocumento(String titulo) {
        if (titulo.isBlank()) throw new IllegalArgumentException();
        return documentos.add(new Documento(titulo));
    }
    public void removeDocumento(String titulo) {
        if (titulo.isBlank()) throw new IllegalArgumentException();
        Documento doc = buscaPorTitulo(titulo);
        if (doc == null ) throw new NoSuchElementException();
        documentos.remove(doc);
    }

    public String[] exibeDocumento(String titulo) {
        if (titulo.isBlank()) throw new IllegalArgumentException();
        Documento doc = buscaPorTitulo(titulo);
        if (doc == null ) throw new NoSuchElementException();
        return doc.visualizar();
    }

    public boolean cadastraElemento() {
        return false;
    }

    public int getNumeroElementos(String titulo) {
        if (titulo.isBlank()) throw new IllegalArgumentException();
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


}
