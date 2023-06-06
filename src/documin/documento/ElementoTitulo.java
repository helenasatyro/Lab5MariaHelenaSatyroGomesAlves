package documin.documento;

import java.util.HashMap;

public class ElementoTitulo extends Elemento {
    private HashMap<String, String> propriedades;
    ElementoTitulo(String valor, int prioridade, int nivel, boolean linkavel) {
        super(prioridade, valor);
        this.propriedades = new HashMap<String,String>();
        this.propriedades.put("nivel", String.valueOf(nivel));
        this.propriedades.put("linkavel", String.valueOf(linkavel));
    }

    @Override
    String representacaoCompleta() {
        if (propriedades.get("linkavel").equals("true")) { return getPrioridade() + ". " + this.valor + " -- " + propriedades.get("nivel") + "-" + valor.toUpperCase().replaceAll(" ", "") + "\n"; }
        return propriedades.get("nivel") + ". " + this.valor + "\n";
    }

    @Override
    String representacaoCurta() {
        return propriedades.get("nivel") + ". " + valor + "\n";
    }

}
