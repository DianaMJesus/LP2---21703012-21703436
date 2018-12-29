package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertFalse;

public class TestSimulador {
    @Test
    public void faltaFicheiro(){
        Simulador simulador = new Simulador();
        boolean resultado = simulador.iniciaJogo(new File(""));
        assertFalse("Não selecionou nenhum ficheiro",resultado);
    }

    @Test
    public void CheckMovement_Pony_KingInTheWay_Valid() {

        Simulador simulador = new Simulador(5);
        CrazyPiece pony = new PoneiMagico(1,  10, "Black");
        pony.setPosicao(3, 1);
        Simulador.pecasMalucas.add(pony);
        CrazyPiece king = new Rei(1, 20, "White");
        king.setPosicao(3, 3);
        Simulador.pecasMalucas.add(king);
        assertTrue("Should be able to move!", simulador.processaJogada(3, 1, 1, 3));
    }

    @Test
    public void sugetaoJoker(){
        Simulador simulador = new Simulador(5);
        CrazyPiece joker = new Joker(1,10,"Joker Preto");
        joker.setPosicao(0,0);
        Simulador.pecasMalucas.add(joker);
        List<String> opcao=simulador.obterSugestoesJogada(0,0);
        assertNotNull("Não deu", opcao);
    }

    @Test
    public void sugetaoRei(){
        Simulador simulador = new Simulador(5);
        CrazyPiece rei = new Rei(1,10,"Rei Preto");
        rei.setPosicao(0,0);
        Simulador.pecasMalucas.add(rei);
        List<String> possiveisMovimentos = new ArrayList<>();
        assertEquals("Deveria dar as possições ",possiveisMovimentos,simulador.obterSugestoesJogada(0,0));
    }

    @Test
    public void sugetaoPoneiMagico(){
        Simulador simulador = new Simulador(5);
        CrazyPiece ponei = new PoneiMagico(1,10,"Ponei Preto");
        ponei.setPosicao(0,0);
        Simulador.pecasMalucas.add(ponei);
        List<String> possiveisMovimentos = new ArrayList<>();
        possiveisMovimentos.add("2, 2");
        assertEquals("Deveria dar a posição 2, 2", possiveisMovimentos, simulador.obterSugestoesJogada(0,0));
    }

    @Test
    public void tentaJogada(){
        Simulador simulador = new Simulador();
        boolean resultado = simulador.processaJogada(2,3,5,8);
        assertFalse("Não foi possivel passar no teste",resultado);
    }

    @Test
    public void tentaHaJogador(){
        CrazyPiece peace=new CrazyPiece();

    }


}
