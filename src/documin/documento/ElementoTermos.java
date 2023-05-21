package documin.documento;

import java.util.HashMap;

public class ElementoTermos extends Elemento {
    ElementoTermos(String valor, int prioridade, String separador, String ordem) {
        super(prioridade, valor);
        this.propriedades.put("separador", separador);
        this.propriedades.put("ordem", ordem);
    }

    @Override
    String representacaoCompleta() {
    }

    @Override
    String representacaoCurta() {
    }
}
