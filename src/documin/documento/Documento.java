package documin.documento;

import java.util.ArrayList;
import java.util.Objects;

public class Documento {
    private String titulo;
    private int tamanho;
    private ArrayList<Elemento> elementos;

    Documento(String titulo) {
        if (titulo.isBlank()) throw new IllegalArgumentException();
        this.titulo = titulo;
        this.tamanho = -1;
        this.elementos = new ArrayList<>();
    }
    Documento(String titulo, int tamanho) {
        if (titulo.isBlank()) throw new IllegalArgumentException();
        this.titulo = titulo;
        if (tamanho <= 0) throw new IllegalArgumentException();
        this.tamanho = tamanho;
        this.elementos = new ArrayList<>();
    }

    int addTexto(String valor, int prioridade) {
        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new ElementoTexto(valor, prioridade));
        }
        return elementos.size() -1;
    }
    int addTitulo(String valor, int prioridade, int nivel, boolean linkavel) {
        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new ElementoTitulo(valor, prioridade, nivel, linkavel));
        }
        return elementos.size() -1;
    }
    int addLista(String valor, int prioridade, String separador, String caractere) {
        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new ElementoLista(valor, prioridade, separador, caractere));
        }
        return elementos.size() -1;
    }
    int addTermos(String valor, int prioridade, String separador, String ordem) {
        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new ElementoTermos(valor, prioridade, separador, ordem));
        }
        return elementos.size() -1;
    }

    int addAtalho(Documento doc) {
        for (Elemento el: elementos) {
            if (el.getClass() == ElementoAtalho.class) throw new IllegalStateException();
        }

        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new ElementoAtalho(doc));
        }
        return elementos.size() -1;
    }

    int

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return Objects.equals(titulo, documento.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo);
    }

    String getTitulo() {
        return titulo;
    }

    int getNumElementos() {
        return elementos.size();
    }

    void moveAcima(int elementoPosicaoReal) {
        if (elementoPosicaoReal == 1) return;
        Elemento temp = elementos.get(elementoPosicaoReal);
        elementos.set(elementoPosicaoReal, elementos.get(elementoPosicaoReal -1));
        elementos.set(elementoPosicaoReal -1, temp);
    }
    void moveAbaixo(int elementoPosicaoReal) {
        if (elementoPosicaoReal == getNumElementos()) return;
        Elemento temp = elementos.get(elementoPosicaoReal);
        elementos.set(elementoPosicaoReal, elementos.get(elementoPosicaoReal +1));
        elementos.set(elementoPosicaoReal +1, temp);
    }
    void removeElemento(int elementoPosicaoReal) {
        for (int i = elementoPosicaoReal; i < getNumElementos() -1; i++) {
            elementos.set(i, elementos.get(i+1));
        }
    }

    public ArrayList<Elemento> getElementos() {
        return (ArrayList<Elemento>) elementos.clone();
    }
}
