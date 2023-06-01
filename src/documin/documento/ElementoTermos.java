package documin.documento;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class ElementoTermos extends Elemento {
    private HashMap<String, String> propriedades;

    ElementoTermos(String valor, int prioridade, String separador, Ordem ordem) {
        super(prioridade, valor);
        this.propriedades = new HashMap<String, String>();
        this.propriedades.put("separador", separador);
        this.propriedades.put("ordem", ordem.toString());
        switch (ordem) {
            case ALFABETICA -> Arrays.sort(valor.split(propriedades.get("separador")), String.CASE_INSENSITIVE_ORDER);
            case TAMANHO -> Arrays.sort(valor.split(propriedades.get("separador")), Comparator.comparingInt(String::length).reversed());

        }
    }

    @Override
    String representacaoCompleta() {
        String[] conteudo = valor.split(propriedades.get("separador"));
        String retorno = "Total termos: " + conteudo.length + "\n- " + conteudo[0];
        for (int i = 1; i < conteudo.length; i++) {
            retorno += ", " + conteudo[i];
        }
        return retorno;
    }

    @Override
     String representacaoCurta() {
        String[] conteudo = valor.split(propriedades.get("separador"));
        String retorno = conteudo[0];
        for (int i = 1; i < conteudo.length; i++) {
            retorno += " / " + conteudo[i];
        }
        return retorno;
    }

}
