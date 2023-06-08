package documin.documento;

import java.util.HashMap;

/**
 * Classe que implementa um elmento do tipo título. Um título tem um valor (texto) uma prioridade(1 a 5)
 * um nível e pode ou não ser linkável.
 */
public class ElementoTitulo extends Elemento {
    private HashMap<String, String> propriedades;

    /**
     * Cria um elemento título. Um título linkávelgera um link em sua representação.
     * @param valor texto do título
     * @param prioridade de 1 a 5
     * @param nivel do título
     * @param linkavel boolean verdadeiro ou falso
     */
    public ElementoTitulo(String valor, int prioridade, int nivel, boolean linkavel) {
        super(prioridade, valor);
        this.propriedades = new HashMap<>();
        this.propriedades.put("nivel", String.valueOf(nivel));
        this.propriedades.put("linkavel", String.valueOf(linkavel));
    }

    /**
     * a representação completa d eum título consiste em seu nível, seu valor, e seulink caso seja linkável
     * @return string no formato 0. Titulo do Titulo -- 0-TITULODOTITULO ou 0. Titulo do Titulo
     */
    @Override
    public String representacaoCompleta() {
        if (propriedades.get("linkavel").equals("true")) { return getPrioridade() + ". " + this.valor + " -- " + propriedades.get("nivel") + "-" + valor.toUpperCase().replaceAll(" ", "") + "\n"; }
        return propriedades.get("nivel") + ". " + this.valor + "\n";
    }

    /**
     * A representaçã curta de um título consiste em seunível e seu conteúdo (valor) textual
     * @return string no formato 0. Titulo do titulo
     */
    @Override
    public String representacaoCurta() {
        return propriedades.get("nivel") + ". " + valor + "\n";
    }

}
