package documin.documento;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class ElementoTermos extends Elemento {
    private HashMap<String, String> propriedades;

    ElementoTermos(String valor, int prioridade, String separador, Ordem ordem) {
        super(prioridade, valor);
        this.propriedades = new HashMap<>();
        // o pipe | em regex significa OR, então para usá-lo como separador é necessário escapá-lo
        if (separador.equals("|")) separador = "\\|";
        this.propriedades.put("separador", separador);
        this.propriedades.put("ordem", ordem.toString());
    }

    @Override
    String representacaoCompleta() {
        String[] conteudo = this.valor.split(propriedades.get("separador"));

        switch (propriedades.get("ordem")) {
            case "ALFABETICA" -> Arrays.sort(conteudo, String.CASE_INSENSITIVE_ORDER);
            case "TAMANHO" -> Arrays.sort(conteudo, Comparator.comparingInt(String::length).reversed());
        }

        String retorno = "Total termos: " + conteudo.length + "\n- " + conteudo[0];
        for (int i = 1; i < conteudo.length; i++) {
            retorno += ", " + conteudo[i];
        }
        return retorno;
    }

    @Override
     String representacaoCurta() {
        String[] conteudo = valor.split(propriedades.get("separador"));

        switch (propriedades.get("ordem")) {
            case "ALFABETICA" -> Arrays.sort(conteudo, String.CASE_INSENSITIVE_ORDER);
            case "TAMANHO" -> Arrays.sort(conteudo, Comparator.comparingInt(String::length).reversed());
        }
        String retorno = conteudo[0];
        for (int i = 1; i < conteudo.length; i++) {
            retorno += " / " + conteudo[i];
        }
        return retorno;
    }

}
