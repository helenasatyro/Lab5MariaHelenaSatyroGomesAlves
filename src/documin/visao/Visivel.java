package documin.visao;

import documin.documento.DocumentoController;

import javax.print.Doc;

public interface Visivel {
    String[] exibirVisao(String tituloDoc, DocumentoController dc);
}
