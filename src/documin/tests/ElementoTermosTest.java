package documin.tests;
import documin.documento.ElementoTermos;
import documin.documento.Ordem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElementoTermosTest {

    @Test
    void testaTermosNenhum() {
        ElementoTermos el = new ElementoTermos("termo | outro | mais um", 1, "|", Ordem.NENHUM);
        assertEquals("Total termos: 3\n" +
                "- termo ,  outro ,  mais um\n", el.representacaoCompleta(), "");
        assertEquals("termo  /  outro  /  mais um\n", el.representacaoCurta(),"");
    }
    @Test
    void testaCriaTermosAlfabetica() {
        ElementoTermos el = new ElementoTermos("termo | outro | mais um", 1, "|", Ordem.ALFABETICA);
        assertEquals("Total termos: 3\n" +
                "-  mais um,  outro , termo \n", el.representacaoCompleta(), "");
        assertEquals(" mais um /  outro  / termo \n", el.representacaoCurta(),"");
    }
    @Test
    void testaCriaTermosTamanho() {
        ElementoTermos el = new ElementoTermos("termo | um | mais um", 1, "|", Ordem.TAMANHO);
        assertEquals("Total termos: 3\n" +
                "-  mais um, termo ,  um \n", el.representacaoCompleta(), "");
        assertEquals(" mais um / termo  /  um \n", el.representacaoCurta(),"");
    }
    @Test
    void testaCriaTermosTamanhoEmpate() {
        ElementoTermos el = new ElementoTermos("termo | um | ermot", 1, "|", Ordem.TAMANHO);
        assertEquals("Total termos: 3\n" +
                "- termo ,  ermot,  um \n", el.representacaoCompleta(), "");
        assertEquals("termo  /  ermot /  um \n", el.representacaoCurta(),"");
    }
}
