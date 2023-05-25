package documin.documento;

import java.util.Arrays;

public class ElementoTermos extends Elemento {
    ElementoTermos(String valor, int prioridade, String separador, Ordem ordem) {
        super(prioridade, valor);
        this.propriedades.put("separador", separador);
        this.propriedades.put("ordem", ordem.toString());
        switch (ordem) {
            case ALFABETICA -> Arrays.sort(valor.split(propriedades.get("separador")), String.CASE_INSENSITIVE_ORDER);
            case TAMANHO -> sortSize(valor.split(propriedades.get("separador")));
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

    private void sortSize(String[] palavras) {
        for (int i = 0; i < palavras.length; i++) {
            for (int j = i; j < palavras.length -1; j++) {
                if (palavras[j].length() < palavras[j+1].length()) {
                    String temp = palavras[j];
                    palavras[j] = palavras[j+1];
                    palavras[j+1] = temp;
                }
            }
        }
    }
}
