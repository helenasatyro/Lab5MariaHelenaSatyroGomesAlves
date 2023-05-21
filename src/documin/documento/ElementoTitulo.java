package documin.documento;

import java.util.HashMap;

public class ElementoTitulo extends Elemento {
    ElementoTitulo(String valor, int prioridade, int nivel, boolean linkavel) {
        super(prioridade, valor);
        this.propriedades.put("nivel", String.valueOf(nivel));
        this.propriedades.put("linkavel", String.valueOf(linkavel));
    }

    @Override
    String representacaoCompleta() {
        if (propriedades.get("linkavel").equals("true")) { return getPrioridade() + ". " + getValor() + " -- " + propriedades.get("nivel") + "-" + propriedades.get("nivel").toUpperCase().replaceAll(" ", ""); }
        return propriedades.get("valor") + ". " + getValor();
    }

    @Override
    String representacaoCurta() {
        return propriedades.get("valor") + ". " + getValor();
    }

}
