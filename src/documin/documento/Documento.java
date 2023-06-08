package documin.documento;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Classe que representa um documento. Um documento é composto de diversos tipos de elemento, pode terseu tamanho limitado ou não,
 * ele é identificado unicamente pelo seu título. Um documento pode ser atalho ou conter atalhos, mas nunca so dois ao mesmo tempo.
 */
public class Documento {
    private String titulo;
    private int tamanho;
    private LinkedList<Elemento> elementos;
    private boolean ehAtalho;

    /**
     * Cria um documento com o título passado como parâmetro e de tamanho ilimitado.
     * A falta de limite é representada pelo valor -1 no atributo tamanho.
     * @param titulo
     * @throws  IllegalArgumentException se o título passado for nulo ou vazio.
     */
    public Documento(String titulo) {
        if (titulo.isEmpty()) throw new IllegalArgumentException();
        this.titulo = titulo;
        this.tamanho = -1;
        this.elementos = new LinkedList<>();
        this.ehAtalho = false;
    }
    /**
     * Cria um documento com o título passado como parâmetro e de tamanho ilimitado.
     * A falta de limite é representada pelo valor -1 no atributo tamanho.
     * @param titulo identificador único do documento
     * @param tamanho quantidade máxima de elementos que pode ser armazenada no documento
     * @throws IllegalArgumentException se o título passado for nulo ou vazio, ou o tamaho passado for menor que zero.
     */
    public Documento(String titulo, int tamanho) {
        if (titulo.isEmpty()) throw new IllegalArgumentException();
        this.titulo = titulo;
        if (tamanho <= 0) throw new IllegalArgumentException();
        this.tamanho = tamanho;
        this.elementos = new LinkedList<>();
    }

    /**
     * Cria um elemento do tipo texto.
     * @param valor o texto do elemento
     * @param prioridade a prioridade do elemento (1 a 5)
     * @return a posição do elemento no documento
     */
    public int addTexto(String valor, int prioridade) {
        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new Elemento(prioridade, valor));
            return elementos.size() -1;
        }
        throw new IllegalStateException("Documento atingiu o limite de elementos.");
    }
    /**
     * Cria um elemento do tipo título
     * @param valor o título
     * @param prioridade a prioridade do elemento (1 a 5)
     * @param nivel o nível do título
     * @param linkavel se ele é ou não linkável
     * @return a posição do elemento no documento
     */
    public int addTitulo(String valor, int prioridade, int nivel, boolean linkavel) {
        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new ElementoTitulo(valor, prioridade, nivel, linkavel));
            return elementos.size() -1;
        }
        throw new IllegalStateException("Documento atingiu o limite de elementos.");
    }

    /**
     * Retorna o nível de prioridade do elemento buscado.
     * @param elementoPosicaoReal a ser consultado
     * @return o nível de prioridade
     */
    public int getPrioridade(int elementoPosicaoReal) {
        return elementos.get(elementoPosicaoReal).getPrioridade();
    }
    /**
     * Cria um elemento do tipo lista
     * @param valor string com itens separados por um separador
     * @param prioridade prioridade do elemento (1 a 5)
     * @param separador separador usado na string valor
     * @param caractere caractere que deve ser usado na representação da lista
     * @return posição do elemento no documento
     */
    public int addLista(String valor, int prioridade, String separador, String caractere) {
        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new ElementoLista(valor, prioridade, separador, caractere));
            return elementos.size() -1;
        }
        throw new IllegalStateException("Documento atingiu o limite de elementos.");
    }
    /**
     * Cria um elemento do tipo termos
     * @param valor termos separados por um separador
     * @param prioridade do elemento (1 a 5)
     * @param separador usado para separar os termos no valor passado
     * @param ordem forma de ordenação que pode ser usada para os termos (nenhuma, alfabetica ou tamanho)
     * @return posição do elemento no documento
     */
    public int addTermos(String valor, int prioridade, String separador, String ordem) {
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

    /**
     * Cria um elemento atalho
     * O documento a ser referenciado não pode ter atalhos dentro,
     * e o documento em que se vai adicionar o atalho não pode ser atalho de outro documento.
     * @param doc que será referenciado
     * @return a posição do elemento no documento
     */
    public int addAtalho(Documento doc) {
        for (Elemento el: doc.getElementos()) {
            if (el.getClass() == ElementoAtalho.class) throw new IllegalStateException();
        }

        if (tamanho == -1 || elementos.size() < tamanho) {
            elementos.add(new ElementoAtalho(doc));
            return elementos.size() -1;
        }
        throw new IllegalStateException("Documento atingiu o limite de elementos.");
    }

    /**
     * Define o documento como atalho de outro documento ou remove a condição de atalho
     * @param at true ou false informando se é atalho ou não.
     */
    public void setEhAtalho(boolean at) {
        this.ehAtalho = at;
    }

    /**
     * Dois documentos são iguais se seus títulos são iguais.
     * @param o objeto sendo comparado
     * @return true se os documentos são iguais
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return Objects.equals(titulo, documento.titulo);
    }

    /**
     * Gera um hashcode baseado no título do documento
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(titulo);
    }

    /**
     * Retorna o título do documento
     * @return títuo do documento
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Informa o número de elementos presentes no documento independente de seu limite
     * @return número de elementos cadastrados no documento
     */
    public int getNumElementos() {
        return elementos.size();
    }

    /**
     * Move o elemento especificado uma posição acima, se o elemento for o primeiro nada aocntece
     * @param elementoPosicaoReal
     */
    public void moveAcima(int elementoPosicaoReal) {
        if (elementoPosicaoReal == 0) return;
        Elemento temp = elementos.get(elementoPosicaoReal);
        elementos.set(elementoPosicaoReal, elementos.get(elementoPosicaoReal -1));
        elementos.set(elementoPosicaoReal -1, temp);
    }
    /**
     * Move o elemento especificado uma posição abaixo, se o elemento for o último nada aocntece
     * @param elementoPosicaoReal
     */
    public void moveAbaixo(int elementoPosicaoReal) {
        if (elementoPosicaoReal == getNumElementos() -1) return;
        Elemento temp = elementos.get(elementoPosicaoReal);
        elementos.set(elementoPosicaoReal, elementos.get(elementoPosicaoReal +1));
        elementos.set(elementoPosicaoReal +1, temp);
    }

    /**
     * Remove o elemento de índice especificado
     * @param elementoPosicaoReal
     * @return true se o elemento tiver sido removido
     */
    public boolean removeElemento(int elementoPosicaoReal) {
        if (elementos.get(elementoPosicaoReal).getClass() == ElementoAtalho.class) {
            ElementoAtalho ela = (ElementoAtalho) elementos.get(elementoPosicaoReal);
            ela.setEhAtalho(false);
        }
        return this.elementos.remove(elementoPosicaoReal) != null;
    }

    /**
     * Retorna um clone da linked list de elementos
     * @return clone da lista deS elementos do documento
     */
    public LinkedList<Elemento> getElementos() {
        return (LinkedList<Elemento>) elementos.clone();
    }
    /**
     * Pega a representação completa de um elemento
     * @param posicaoReal do elemento a ser representado
     * @return a representação em string do elemento
     */
    public String exibeElementoCompleto(int posicaoReal) {
        Elemento el = elementos.get(posicaoReal);
        return el.representacaoCompleta();
    }
    /**
     * Pega a representação resumida de um elemento
     * @param posicaoReal do elemento a ser representado
     * @return a representação em string do elemento
     */
    public String exibeElementoResumido(int posicaoReal) {
        Elemento el = elementos.get(posicaoReal);
        return el.representacaoCurta();
    }
    /**
     * Gera uma exibiçao resumida em array do documento
     * @return um array de strings com as representações resumidas dos elementos
     */
    public String[] exibir() {
        String[] retorno = new String[elementos.size()];
        for (int i = 0; i < elementos.size(); i++) {
            retorno[i] = elementos.get(i).representacaoCurta();
        }
        return retorno;
    }

    /**
     * Informa se o documento é referenciado como atalho em algum outro documento.
     * @return true se for atalho em outro documento
     */
    public boolean getEhAtalho() {
        return this.ehAtalho;
    }
}
