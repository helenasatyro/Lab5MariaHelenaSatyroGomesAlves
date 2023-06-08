package documin;

import documin.documento.DocuminController;
/**
 *  Classe que agrega funções do sistema Documin.
 */
public class Facade {
    private final DocuminController documinController;

    /*
    Construtor cria um objeto facade que gera um controller.
     */
    public Facade() {
        this.documinController = new DocuminController();
    }

    /**
     * cria um documento de tamanho ilimitado que tem o título identificador único especificado
     * @param titulo do documento
     * @return boolean se foi criado com sucesso ou não
     */
    boolean criarDocumento(String titulo) {
        return this.documinController.criarDocumento(titulo);
    }
    /**
     * cria um documento de tamanho limitado que tem o título identificador único especificado
     * @param titulo do documento@
     * @param tamanhoMaximo dp documento
     * @return boolean se foi criado com sucesso ou não
     */
    boolean criarDocumento(String titulo, int tamanhoMaximo) {
        return this.documinController.criarDocumento(titulo, tamanhoMaximo);
    }

    /**
     * Remove um documento do sistema a partir do título
     * @param titulo
     */
    void removerDocumento(String titulo) {
        this.documinController.removeDocumento(titulo);
    }

    /**
     * Retorna a quantidade de elementos de um documento, buscado pelo título.
     * @param titulo
     * @return int a quantidade de elementos que o documento tem, independente do limite
     */
    int contarElementos(String titulo) {
        return documinController.getNumeroElementos(titulo);
    }

    /**
     * Gera uma exibiçao resumida em array do documento
     * @param titulo do documento
     * @return um array de strings com as representações resumidas dos elementos
     */
    String[] exibirDocumento(String titulo) {
        return documinController.exibirDocumento(titulo);
    }
    /**
     * Cria um elemento do tipo texto no documento especificado.
     * @param valor o texto do elemento
     * @param prioridade a prioridade do elemento (1 a 5)
     * @return a posição do elemento no documento
     */
    int criarTexto(String tituloDoc, String valor, int prioridade) {
        return documinController.criarTexto(tituloDoc, valor, prioridade);
    }

    /**
     * Cria um elemento do tipo título no documento especificado
     * @param tituloDoc
     * @param valor o título
     * @param prioridade a prioridade do elemento (1 a 5)
     * @param nivel o nível do título
     * @param linkavel se ele é ou não linkável
     * @return a posição do elemento no documento
     */
    int criarTitulo(String tituloDoc, String valor, int prioridade, int nivel, boolean linkavel) {
        return documinController.criarTitulo(tituloDoc, valor, prioridade, nivel, linkavel);
    }

    /**
     * Cria um elemento do tipo lista no documento especificado
     * @param tituloDoc
     * @param valorLista string com itens separados por um separador
     * @param prioridade prioridade do elemento (1 a 5)
     * @param separador separador usado na string valor
     * @param charLista caractere que deve ser usado na representação da lista
     * @return posição do elemento no documento
     */
    int criarLista(String tituloDoc, String valorLista, int prioridade, String separador, String charLista) {
        return documinController.criarLista(tituloDoc, valorLista, prioridade, separador, charLista);
    }

    /**
     * Cria um elemento do tipo termos no documento especificado
     * @param tituloDoc
     * @param valorTermos termos separados por um separador
     * @param prioridade do elemento (1 a 5)
     * @param separador usado para separar os termos no valor passado
     * @param ordem forma de ordenação que pode ser usada para os termos (nenhuma, alfabetica ou tamanho)
     * @return posição do elemento no documento
     */
    int criarTermos(String tituloDoc, String valorTermos, int prioridade, String separador, String ordem) {
        return documinController.criarTermos(tituloDoc, valorTermos, prioridade, separador, ordem);
    }

    /**
     * Cria um elemento atalho no documento especificado.
     * O documento a ser referenciado não pode ter atalhos dentro,
     * e o documento em que se vai adicionar o atalh não pode ser atalho de outro documento.
     * @param tituloDoc a receber o atalho
     * @param tituloDocReferenciado que será referenciado
     * @return a posição do elemento no documento
     */
    int criarAtalho(String tituloDoc, String tituloDocReferenciado) {
        return documinController.criarAtalho(tituloDoc, tituloDocReferenciado);
    }

    /**
     * Pega a representação completa de um elemento no doc especificado
     * @param tituloDoc o documento em que se encontra o elemento
     * @param elementoPosicao do elemento a ser representado
     * @return a representação em string do elemento
     */
    String pegarRepresentacaoCompleta(String tituloDoc, int elementoPosicao) {
        return documinController.pegarRepresentacaoCompleta(tituloDoc, elementoPosicao);
    }
    /**
     * Pega a representação curta de um elemento no doc especificado
     * @param tituloDoc o documento em que se encontra o elemento
     * @param elementoPosicao do elemento a ser representado
     * @return a representação em string do elemento
     */
    String pegarRepresentacaoResumida(String tituloDoc, int elementoPosicao) {
        return documinController.pegarRepresentacaoResumida(tituloDoc, elementoPosicao);
    }

    /**
     * Apaga um elemento de um doc, após a remoção de elementos não ficam espaços vazios entre os elementos restantes
     * @param tituloDoc  o documento em que se encontra o elemento
     * @param elementoPosicao do elemento a ser removido
     * @return boolean se o elemento foi ou não removido
     */
    boolean apagarElemento(String tituloDoc, int elementoPosicao) {
        return documinController.apagarElemento(tituloDoc, elementoPosicao);
    }

    /**
     * move um elemento para cima dentro do documento especificado, se o elemento for o primeiro, nada acontece
     * @param tituloDoc o documento em qe se encontrao elemento
     * @param elementoPosicao do elemento a ser movido
     */
    void moverParaCima(String tituloDoc, int elementoPosicao) {
        documinController.moverParaCima(tituloDoc, elementoPosicao);
    }
    /**
     * Move um elemento uma posição abaixo no documento especificado, se for o último elmento nada acontece
     * @param tituloDoc doc que contém o elemento
     * @param elementoPosicao posição atual do elemento
     */
    void moverParaBaixo(String tituloDoc, int elementoPosicao) {
        documinController.moverParaBaixo(tituloDoc, elementoPosicao);
    }

    /**
     * Cria uma visão completa do documento especificado e a guarda no sistema
     * @param tituloDoc do qual a visão será gerada
     * @return o índice da visão no sistema
     */
    int criarVisaoCompleta(String tituloDoc) {
        return documinController.criarVisaoCompleta(tituloDoc);
    }
    /**
     * Cria uma visão resumida do documento especificado e a guarda no sistema
     * @param tituloDoc do qual a visão será gerada
     * @return o índice da visão no sistema
     */
    int criarVisaoResumida(String tituloDoc) {
        return documinController.criarVisaoResumida(tituloDoc);
    }
    /**
     * Cria uma visão que é a representação completa de cada elemento do documento referenciado
     * que tenha prioridade maior (ou igual) que um determinado valor informado como parâmetro;
     * @param tituloDoc do qual a visão será gerada
     * @param prioridade mínima que será representada
     * @return o índice da visão no sistema
     */
    int criarVisaoPrioritaria(String tituloDoc, int prioridade) {
        return documinController.criarVisaoPrioritaria(tituloDoc, prioridade);
    }

    /**
     * Cria uma visão completa de todods os elementos do tipo título que se encontram no documento especificado
     * @param tituloDoc do qual a visão sera gerada
     * @return índice da visão no sistema
     */
    int criarVisaoTitulo(String tituloDoc) {
        return documinController.criarVisaoTitulo(tituloDoc);
    }

    /**
     * Exibe uma visão previamente gerada e guardada no sitema a a partir de seu índice.
     * @param visaoId id da visão a ser exibida
     * @return array de strings com cada elemento do documento sendo visualizado
     */
    String[] exibirVisao(int visaoId) {
        return documinController.exibirVisao(visaoId);
    }
}
