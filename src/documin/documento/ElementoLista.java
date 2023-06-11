package documin.documento;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Classe que implementa um element no formato de lista. Uma lista tem elementos,
 * um separador, um caractere para representação e uma prioridade.
 * @author Maria Helena Sátyro Gomes Alves
 */
public class ElementoLista extends Elemento {
    private HashMap<String, String> propriedades;

    /**
     * Cria uma lista
     * @param valor itens da lista em uma string
     * @param prioridade da lista
     * @param separador que será usado para separar os itensdo valor
     * @param caractere que será usado na representação da lista
     */
    public ElementoLista(String valor, int prioridade, String separador, String caractere) {
        super(prioridade, valor);
        this.propriedades = new HashMap<>();
        // o pipe | em regex significa OR, então para usá-lo como separador é necessário escapá-lo
        this.propriedades.put("separador", separador);
        this.propriedades.put("caractere", caractere);
    }

    /**
     * A representação completa de uma lista consiste em seus itens precedidos do
     * caractere escolhido, por exemplo se o caractere for "-"
     * - item 1
     * - item 2
     * - item 3
     *
     * @return representação completa da lista
     */
    @Override
    public String representacaoCompleta() {
       String[] conteudo = valor.split(Pattern.quote(propriedades.get("separador")));
       String retorno = "";
       for (String el: conteudo) {
           retorno += propriedades.get("caractere") + " " + el + "\n";
       }
       return retorno;
    }

    /**
     * A representação resumida da lista consiste nos itens da mesma separados por vírgula
     * @return string no formato item1, item2, item3
     */
    @Override
    public String representacaoCurta() {
        String[] conteudo = valor.split(Pattern.quote(propriedades.get("separador")));
        String retorno = conteudo[0];
        for (int i = 1; i < conteudo.length; i++) {
            retorno += ", " + conteudo[i];
        }
        return retorno + "\n";
    }
}
