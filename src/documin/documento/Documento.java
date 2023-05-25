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
        Ordem o;
        if (tamanho == -1 || elementos.size() < tamanho) {
            if (ordem.equalsIgnoreCase("alfabetica") || ordem.equalsIgnoreCase("alfabética")) {
                o = Ordem.ALFABETICA;
            } else  if (ordem.equalsIgnoreCase("tamanho")) {
                o = Ordem.TAMANHO;
            } else {
                o = Ordem.NENHUM;
            }
            elementos.add(new ElementoTermos(valor, prioridade, separador, o));
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
        ArrayList<Elemento> novo = new ArrayList<>();
        for (int i = 0; i < elementos.size(); i++) {
            if (i == elementoPosicaoReal) { continue;}
            novo.add(elementos.get(i));
        }
    }

    public ArrayList<Elemento> getElementos() {
        return (ArrayList<Elemento>) elementos.clone();
    }

    public String[] exibir() {
        String[] retorno = new String[elementos.size()];
        for (int i = 0; i < elementos.size(); i++) {
            retorno[i] = elementos.get(i).representacaoCurta();
        }
        return retorno;
    }
}
