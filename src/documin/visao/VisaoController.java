package documin.visao;

import documin.documento.Documento;
import documin.documento.DocumentoController;

import java.util.ArrayList;

public class VisaoController {
    private ArrayList<Visivel> visoes;

    public int criarVisaoCompleta(String tituloDoc, DocumentoController dc) {
        Documento doc = dc.buscaPorTitulo(tituloDoc);
        visoes.add(new VisaoCompleta(doc));
        return visoes.size() -1;
    }
    public int criarVisaoResumida(String tituloDoc, DocumentoController dc) {
        Documento doc = dc.buscaPorTitulo(tituloDoc);
        visoes.add(new VisaoResumida(doc));
        return visoes.size() -1;
    }
    public int criarVisaoPrioritaria(String tituloDoc, int prioridade, DocumentoController dc) {
        Documento doc = dc.buscaPorTitulo(tituloDoc);
        visoes.add(new VisaoPrioritaria(doc, prioridade));
        return visoes.size() -1;
    }
    public int criarVisaoTitulo(String tituloDoc, DocumentoController dc) {
        Documento doc = dc.buscaPorTitulo(tituloDoc);
        visoes.add(new VisaoTitulo(doc));
        return visoes.size() -1;
    }
    public String[] exibirVisao(int visaoId) {
        return visoes.get(visaoId).exibirVisao();
    }
}
