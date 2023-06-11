package documin.documento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
/**
 *  Classe que agrega funções do sistema Documin.
 *  @author Maria Helena Sátyro Gomes Alves
 */
public class DocuminController {

    private HashSet<Documento> documentos;
    private ArrayList<Visivel> visoes;

    /**
     * Cria um objeto DocuminController e inicializa as coleções de documento e visão
     */
    public DocuminController() {
        this.documentos = new HashSet<>();
        this.visoes = new ArrayList<>();
    }
    /**
     * cria um documento de tamanho limitado que tem o título identificador único especificado
     * @param titulo do documento@
     * @param tamanho dp documento
     * @return boolean se foi criado com sucesso ou não
     */
    public boolean criarDocumento(String titulo, int tamanho) {
        if (titulo.isEmpty() || titulo.isBlank()) throw new IllegalArgumentException();
        return documentos.add(new Documento(titulo, tamanho));
    }

    /**
     * cria um documento de tamanho ilimitado que tem o título identificador único especificado
     * @param titulo do documento
     * @return boolean se foi criado com sucesso ou não
     */
    public boolean criarDocumento(String titulo) {
        if (titulo.isEmpty() || titulo.isBlank()) throw new IllegalArgumentException();
        return documentos.add(new Documento(titulo));
    }
    /**
     * Remove um documento do sistema a partir do título
     * @param titulo
     */
    public void removeDocumento(String titulo) {
        Documento doc = buscaPorTitulo(titulo);
        documentos.remove(doc);
    }
    /**
     * Gera uma exibiçao resumida em array do documento
     * @param titulo do documento
     * @return um array de strings com as representações resumidas dos elementos
     */
    public String[] exibirDocumento(String titulo) {
        Documento doc = buscaPorTitulo(titulo);
        return doc.exibir();
    }
    /**
     * Cria um elemento do tipo texto no documento especificado.
     * @param tituloDoc que receberá o elemento
     * @param valor o texto do elemento
     * @param prioridade a prioridade do elemento (1 a 5)
     * @return a posição do elemento no documento
     */
    public int criarTexto(String tituloDoc, String valor, int prioridade) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.criarTexto(valor, prioridade);
    }
    /**
     * Cria um elemento do tipo título no documento especificado
     * @param tituloDoc do documento que receberá o elemento
     * @param valor o título
     * @param prioridade a prioridade do elemento (1 a 5)
     * @param nivel o nível do título
     * @param linkavel se ele é ou não linkável
     * @return a posição do elemento no documento
     */
    public int criarTitulo(String tituloDoc, String valor, int prioridade, int nivel, boolean linkavel) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.criarTitulo(valor, prioridade, nivel, linkavel);
    }
    /**
     * Cria um elemento do tipo termos no documento especificado
     * @param tituloDoc do documento que receberá  elemento
     * @param valor termos separados por um separador
     * @param prioridade do elemento (1 a 5)
     * @param separador usado para separar os termos no valor passado
     * @param ordem forma de ordenação que pode ser usada para os termos (nenhuma, alfabetica ou tamanho)
     * @return posição do elemento no documento
     */
    public int criarTermos(String tituloDoc, String valor, int prioridade, String separador, String ordem) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.criarTermos(valor, prioridade, separador, ordem);
    }
    /**
     * Cria um elemento do tipo lista no documento especificado
     * @param tituloDoc
     * @param valor string com itens separados por um separador
     * @param prioridade prioridade do elemento (1 a 5)
     * @param separador separador usado na string valor
     * @param caractere caractere que deve ser usado na representação da lista
     * @return posição do elemento no documento
     */
    public int criarLista(String tituloDoc, String valor, int prioridade, String separador, String caractere) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.criarLista(valor, prioridade, separador, caractere);
    }
    /**
     * Cria um elemento atalho no documento especificado.
     * O documento a ser referenciado não pode ter atalhos dentro,
     * e o documento em que se vai adicionar o atalh não pode ser atalho de outro documento.
     * @param tituloDoc a receber o atalho
     * @param docReferenciado título do documento que será referenciado
     * @return a posição do elemento no documento
     */
    public int criarAtalho(String tituloDoc, String docReferenciado) {
        Documento doc = buscaPorTitulo(tituloDoc);
        Documento docRef = buscaPorTitulo(docReferenciado);
        return doc.criarAtalho(docRef);
    }
    /**
     * Retorna a quantidade de elementos de um documento, buscado pelo título.
     * @param titulo
     * @return int a quantidade de elementos que o documento tem, independente do limite
     */
    public int getNumeroElementos(String titulo) {
        Documento doc = buscaPorTitulo(titulo);
        return doc.getNumElementos();
    }

    /**
     * Realiza a busca por título entre os documentos, verificando também se o parâmetro passado é válido
     * @param titulo do oc sendo buscado
     * @return o documento buscado
     * @throws NoSuchElementException se o documento não existe
     * @throws IllegalArgumentException se o título passado for nulo ou vazio
     */
    public Documento buscaPorTitulo(String titulo) {
        if (titulo.isEmpty() || titulo.isBlank()) throw new IllegalArgumentException();
        for (Documento d: documentos) {
            if (d.getTitulo().equals(titulo)) return d;
        }
        throw new NoSuchElementException();
    }
    /**
     * Pega a representação curta de um elemento no doc especificado
     * @param tituloDoc o documento em que se encontra o elemento
     * @param elementoPosicaoReal do elemento a ser representado
     * @return a representação em string do elemento
     */
    public String pegarRepresentacaoResumida(String tituloDoc, int elementoPosicaoReal) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.exibeElementoResumido(elementoPosicaoReal);
    }
    /**
     * Pega a representação completa de um elemento no doc especificado
     * @param tituloDoc o documento em que se encontra o elemento
     * @param elementoPosicaoReal do elemento a ser representado
     * @return a representação em string do elemento
     */
    public String pegarRepresentacaoCompleta(String tituloDoc, int elementoPosicaoReal) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.exibeElementoCompleto(elementoPosicaoReal);
    }
    /**
     * Apaga um elemento de um doc, após a remoção de elementos não ficam espaços vazios entre os elementos restantes
     * @param tituloDoc  o documento em que se encontra o elemento
     * @param elementoPosicao do elemento a ser removido
     * @return boolean se o elemento foi ou não removido
     */
    public boolean apagarElemento(String tituloDoc, int elementoPosicao) {
        Documento doc = buscaPorTitulo(tituloDoc);
        return doc.removeElemento(elementoPosicao);
    }
    /**
     * move um elemento para cima dentro do documento especificado, se o elemento for o primeiro, nada acontece
     * @param tituloDoc o documento em qe se encontrao elemento
     * @param elementoPosicaoReal do elemento a ser movido
     */
    public void moverParaCima(String tituloDoc, int elementoPosicaoReal) {
        Documento doc = buscaPorTitulo(tituloDoc);
        doc.moveAcima(elementoPosicaoReal);
    }
    /**
     * Move um elemento uma posição abaixo no documento especificado, se for o último elmento nada acontece
     * @param tituloDoc doc que contém o elemento
     * @param elementoPosicaoReal posição atual do elemento
     */
    public void moverParaBaixo(String tituloDoc, int elementoPosicaoReal) {
        Documento doc = buscaPorTitulo(tituloDoc);
        doc.moveAbaixo(elementoPosicaoReal);
    }
    /**
     * Cria uma visão completa do documento especificado e a guarda no sistema
     * @param tituloDoc do qual a visão será gerada
     * @return o índice da visão no sistema
     */
    public int criarVisaoCompleta(String tituloDoc) {
        Documento doc = buscaPorTitulo(tituloDoc);
        visoes.add(new VisaoCompleta(doc));
        return visoes.size() -1;
    }
    /**
     * Cria uma visão resumida do documento especificado e a guarda no sistema
     * @param tituloDoc do qual a visão será gerada
     * @return o índice da visão no sistema
     */
    public int criarVisaoResumida(String tituloDoc) {
        Documento doc = buscaPorTitulo(tituloDoc);
        visoes.add(new VisaoResumida(doc));
        return visoes.size() -1;
    }
    /**
     * Cria uma visão que é a representação completa de cada elemento do documento referenciado
     * que tenha prioridade maior (ou igual) que um determinado valor informado como parâmetro;
     * @param tituloDoc do qual a visão será gerada
     * @param prioridade mínima que será representada
     * @return o índice da visão no sistema
     */
    public int criarVisaoPrioritaria(String tituloDoc, int prioridade) {
        Documento doc = buscaPorTitulo(tituloDoc);
        visoes.add(new VisaoPrioritaria(doc, prioridade));
        return visoes.size() -1;
    }
    /**
     * Cria uma visão completa de todods os elementos do tipo título que se encontram no documento especificado
     * @param tituloDoc do qual a visão sera gerada
     * @return índice da visão no sistema
     */
    public int criarVisaoTitulo(String tituloDoc) {
        Documento doc = buscaPorTitulo(tituloDoc);
        visoes.add(new VisaoTitulo(doc));
        return visoes.size() -1;
    }
    /**
     * Exibe uma visão previamente gerada e guardada no sitema a a partir de seu índice.
     * @param visaoId id da visão a ser exibida
     * @return array de strings com cada elemento do documento sendo visualizado
     */
    public String[] exibirVisao(int visaoId) {
        return visoes.get(visaoId).exibirVisao();
    }
}
