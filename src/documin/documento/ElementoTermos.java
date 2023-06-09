package documin.documento;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Classe que implementa um elemento que é um conjunto de termos. O elemento termos tem
 * uma string de termos separados por um separador, uma prioridade (1 a 5) e pode ter uma ordem para s termos;
 * Alfabética, Tamanho ou Nenhuma
 */
public class ElementoTermos extends Elemento {
    private HashMap<String, String> propriedades;

    /**
     * Cria um elemento de termos.
     * @param valor termos separados pelo separador
     * @param prioridade do elemento, 1 a 5
     * @param separador será usado para separar os termos da sting de valor
     * @param ordem ALFABÉTICA, TAMANHO, NENHUM
     */
    public ElementoTermos(String valor, int prioridade, String separador, Ordem ordem) {
        super(prioridade, valor);
        this.propriedades = new HashMap<>();
        // o pipe | em regex significa OR, então para usá-lo como separador é necessário escapá-lo
        this.propriedades.put("separador", separador);
        this.propriedades.put("ordem", ordem.toString());
    }

    /**
     * A representação completa do elemento termos tem o formato:
     * Total termos - <numero de termos>
     * Temrmo1, termo2, temro3
     * @return string representando os termos
     */
    @Override
    public String representacaoCompleta() {
        String[] conteudo = this.valor.split(Pattern.quote(propriedades.get("separador")));

        switch (propriedades.get("ordem")) {
            case "ALFABETICA" -> Arrays.sort(conteudo, String.CASE_INSENSITIVE_ORDER);
            case "TAMANHO" -> Arrays.sort(conteudo, Comparator.comparingInt(String::length).reversed());
        }

        String retorno = "Total termos: " + conteudo.length + "\n- " + conteudo[0];
        for (int i = 1; i < conteudo.length; i++) {
            retorno += ", " + conteudo[i];
        }
        return retorno + "\n";
    }

    /**
     * A representação curta do elemento termos é dada pelos termos separados por "/" no formato
     * termo1 / termo2 / termo3
     * @return string com os termos
     */
    @Override
    public String representacaoCurta() {
        String[] conteudo = valor.split(Pattern.quote(propriedades.get("separador")));

        switch (propriedades.get("ordem")) {
            case "ALFABETICA" -> Arrays.sort(conteudo, String.CASE_INSENSITIVE_ORDER);
            case "TAMANHO" -> Arrays.sort(conteudo, Comparator.comparingInt(String::length).reversed());
        }
        String retorno = conteudo[0];
        for (int i = 1; i < conteudo.length; i++) {
            retorno += " / " + conteudo[i];
        }
        return retorno + "\n";
    }

}
