package documin.documento;

import java.util.HashMap;

public class ElementoLista extends Elemento {
    private HashMap<String, String> propriedades;
    ElementoLista(String valor, int prioridade, String separador, String caractere) {
        super(prioridade, valor);
        this.propriedades = new HashMap<String,String>();
        this.propriedades.put("separador", separador);
        this.propriedades.put("caractere", caractere);
    }

    @Override
    String representacaoCompleta() {
       String[] conteudo = valor.split(propriedades.get("separador"));
       String retorno = "";
       for (String el: conteudo) {
           retorno += propriedades.get("caractere") + " " + el + "\n";
       }
       return retorno;
    }

    @Override
    String representacaoCurta() {
        String[] conteudo = valor.split(propriedades.get("separador"));
        String retorno = conteudo[0];
        for (int i = 1; i < conteudo.length; i++) {
            retorno += ", " + conteudo[i];
        }
        return retorno;
    }
}
