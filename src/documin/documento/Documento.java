package documin.documento;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Documento {
    private String titulo;
    private int tamanho;
    private LinkedList<Elemento> elementos;
    private boolean ehAtalho;

    Documento(String titulo) {
        if (titulo.isEmpty()) throw new IllegalArgumentException();
        this.titulo = titulo;
        this.tamanho = -1;
        this.elementos = new LinkedList<>();
        this.ehAtalho = false;
    }
    Documento(String titulo, int tamanho) {
        if (titulo.isEmpty()) throw new IllegalArgumentException();
        this.titulo = titulo;
        if (tamanho <= 0) throw new IllegalArgumentException();
        this.tamanho = tamanho;
        this.elementos = new LinkedList<>();
    }

    int addTexto(String valor, int prioridade) {
        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new Elemento(prioridade, valor));
            return elementos.size() -1;
        }
        throw new IllegalStateException("Documento atingiu o limite de elementos.");
    }
    int addTitulo(String valor, int prioridade, int nivel, boolean linkavel) {
        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new ElementoTitulo(valor, prioridade, nivel, linkavel));
            return elementos.size() -1;
        }
        throw new IllegalStateException("Documento atingiu o limite de elementos.");
    }
    int addLista(String valor, int prioridade, String separador, String caractere) {
        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new ElementoLista(valor, prioridade, separador, caractere));
            return elementos.size() -1;
        }
        throw new IllegalStateException("Documento atingiu o limite de elementos.");
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
            return elementos.size() -1;
        }
        throw new IllegalStateException("Documento atingiu o limite de elementos.");
    }

    int addAtalho(Documento doc) {
        for (Elemento el: doc.getElementos()) {
            if (el.getClass() == ElementoAtalho.class) throw new IllegalStateException();
        }

        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new ElementoAtalho(doc));
            return elementos.size() -1;
        }
        throw new IllegalStateException("Documento atingiu o limite de elementos.");
    }
    void setEhAtalho(boolean at) {
        this.ehAtalho = at;
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
    boolean removeElemento(int elementoPosicaoReal) {
        return this.elementos.remove(elementoPosicaoReal) != null;
    }

    LinkedList<Elemento> getElementos() {
        return (LinkedList<Elemento>) elementos.clone();
    }

    String exibeElementoCompleto(int posicaoReal) {
        Elemento el = elementos.get(posicaoReal);
        return el.representacaoCompleta();
    }
    String exibeElementoResumido(int posicaoReal) {
        Elemento el = elementos.get(posicaoReal);
        return el.representacaoCurta();
    }

    public String[] exibir() {
        String[] retorno = new String[elementos.size()];
        for (int i = 0; i < elementos.size(); i++) {
            retorno[i] = elementos.get(i).representacaoCurta();
        }
        return retorno;
    }

    public boolean getEhAtalho() {
        return this.ehAtalho;
    }
}
