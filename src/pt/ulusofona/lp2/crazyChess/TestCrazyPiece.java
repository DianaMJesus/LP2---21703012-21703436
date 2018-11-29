package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;


public class TestCrazyPiece {
    @Test
    public void testeEquipa(){
        CrazyPiece peace = new CrazyPiece(1,0,1,"João Ratão");
        int equipaEsperada = 1;
        int equipaObtida = peace.getEquipa();
        assertEquals(equipaEsperada,equipaObtida);
    }

    @Test
    public void testeTipoPeca(){
        CrazyPiece peace = new CrazyPiece(7,0,0,"Joaquim Couves");
        int tipoEsperado = 0;
        int tipoObtido = peace.getEquipa();
        assertEquals(tipoEsperado,tipoObtido);
    }

}
