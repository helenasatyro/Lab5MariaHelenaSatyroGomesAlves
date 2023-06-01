package documin.visao;

import documin.documento.Documento;
import documin.documento.DocumentoController;
import documin.documento.Elemento;

import java.util.LinkedList;

public class VisaoCompleta implements Visivel {
    private String tituloDoc;
    VisaoCompleta(String tituloDoc, DocumentoController dc) {
        this.doc = dc.buscaPorTitulo(tituloDoc);
    }
// editar, colocar pra receber elementos por parametro e acessar suas representacoes por controller
    @Override
    public String[] exibirVisao(String tituloDoc, DocumentoController dc) {
        String[] retorno = new String[dc.getNumeroElementos(tituloDoc)];
        for (int i = 0; i < dc.getNumeroElementos(tituloDoc); i++) {
            retorno[i] = dc.exibeElementoCompleto(tituloDoc, i);
        }
        return retorno;
    }
}