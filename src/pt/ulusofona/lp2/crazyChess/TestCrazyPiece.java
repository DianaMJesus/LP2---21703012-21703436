package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;


public class TestCrazyPiece {
    @Test
    public void testeEquipa(){
        CrazyPiece peace = new Rei(1,20,"João Ratão");
        int equipaEsperada = 20;
        int equipaObtida = peace.getEquipa();
        assertEquals(equipaEsperada,equipaObtida);
    }

    @Test
    public void testeTipoPeca(){
        CrazyPiece peace = new Rei(7,10,"Joaquim Couves");
        int tipoEsperado = 0;
        int tipoObtido = peace.getTipoPeca();
        assertEquals(tipoEsperado,tipoObtido);
    }

}
