package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class TestSimulador {

//Teste de leitura de ficheiro
    @Test
    public void faltaFicheiro(){
        Simulador simulador = new Simulador();
        boolean resultado = simulador.iniciaJogo(new File(""));
        assertFalse("Não selecionou nenhum ficheiro",resultado);
    }

//Testes da peca Rei
    @Test
    public void podeMoverRei(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rei.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void naoMoverRei(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rei.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(1,1,3,3));
    }

    @Test
    public void reiComePecaEquipa(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_reiComePecaEquipa.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(1,1,1,2));
    }

    @Test
    public void reiComePecaEquipaAdversaria(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_reiComePecaEquipaAdversaria.txt"));
        assertTrue("Deveria poder comer",simulador.processaJogada(1,1,1,2));
    }

    @Test
    public void sugetaoRei(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rei.txt"));
        List<String> possiveisMovimentos = new ArrayList<>();
        possiveisMovimentos.add("0, 0");
        possiveisMovimentos.add("1, 0");
        possiveisMovimentos.add("2, 0");
        possiveisMovimentos.add("2, 1");
        possiveisMovimentos.add("2, 2");
        possiveisMovimentos.add("1, 2");
        possiveisMovimentos.add("0, 2");
        possiveisMovimentos.add("0, 1");
        assertEquals("Deveria dar as posições [0, 0, 1, 0, 2, 0, 2, 1, 2, 2, 1, 2, 0, 2, 0, 1]",possiveisMovimentos,simulador.obterSugestoesJogada(1,1));
    }

//Testes da peca Rainha
    @Test
    public void moverRainhaDiagonal(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainha.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void moverRainhaHorizontal(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainha.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,2,1));
    }

    @Test
    public void moverRainhaVertical(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainha.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,1,2));
    }

    @Test
    public void moverRainhaDepoisLimite(){
        Simulador simulador = new Simulador(8);
        simulador.iniciaJogo(new File("test-files/test_rainha.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(1,1,0,7));
    }

    @Test
    public void moverRainhaDiagonalComPecaFrente(){
        Simulador simulador = new Simulador();
        simulador.iniciaJogo(new File("test-files/test_rainhaDiagonalComPecaFrente.txt"));
        assertFalse("Nao deveria poder andar",simulador.processaJogada(1,1,3,3));
    }

    @Test
    public void moverRainhaHorizontalComPecaFrente(){
        Simulador simulador = new Simulador();
        simulador.iniciaJogo(new File("test-files/test_rainhaHorizontalComPecaFrente.txt"));
        assertFalse("Nao deveria poder andar",simulador.processaJogada(1,1,3,1));
    }

    @Test
    public void moverRainhaVerticalComPecaFrente(){
        Simulador simulador = new Simulador();
        simulador.iniciaJogo(new File("test-files/test_rainhaVerticalComPecaFrente.txt"));
        assertFalse("Nao deveria poder andar",simulador.processaJogada(1,1,1,3));
    }

    @Test
    public void rainhaComePecaEquipa(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainhaComePecaEquipa.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void rainhaComeRainhaAdversaria(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainhaComeRainhaAdversaria.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public  void rainhaMovePertoPadre(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainhaMovePertoPadre.txt"));
        assertFalse("Não deveria poder mover para perto do padre",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void sugestaoRainha(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainha.txt"));
        List<String> possiveisMovimentos = new ArrayList<>();
        possiveisMovimentos.add("0, 0");
        possiveisMovimentos.add("1, 0");
        possiveisMovimentos.add("2, 0");
        possiveisMovimentos.add("2, 1");
        possiveisMovimentos.add("2, 2");
        possiveisMovimentos.add("1, 2");
        possiveisMovimentos.add("0, 2");
        possiveisMovimentos.add("0, 1");
        possiveisMovimentos.add("3, 1");
        possiveisMovimentos.add("3, 3");
        possiveisMovimentos.add("1, 3");
        assertEquals("Deveria dar [1, 0, 1, 1, 0, 1, 2, 0, 2, 2, 0, 2, 3, 0, 3, 3, 0, 3]",possiveisMovimentos,simulador.obterSugestoesJogada(1,1));
    }
//Testes para a peça Ponei Magico
    @Test
    public void moverPonei(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_ponei.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void moverPoneiDepoisLimite(){
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_ponei.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,3,3));
    }

    @Test
    public void moverPoneiCom1Rei() {
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_poneiCom1Rei.txt"));
        assertTrue("Deveria poder mover", simulador.processaJogada(3, 1, 1, 3));
    }

    @Test
    public void moverPoneiCom2Reis() {
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_poneiCom2Rei.txt"));
        assertFalse("Não deveria poder mover", simulador.processaJogada(3, 1, 1, 3));
    }

    @Test
    public void moverPoneiPecaCaminho(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_poneiComPecaCaminho.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(3,1,1,3));
    }

    @Test
    public void moverPonei2PecaCaminho(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_poneiCom2PecaCaminho.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(3,1,1,3));
    }

    @Test
    public void poneiComePecaEquipa(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_poneiComePecaEquipa.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(3,1,1,3));
    }

    @Test
    public void poneiComePecaEquipaAdversaria(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_poneiComePecaEquipaAdversaria.txt"));
        assertTrue("Deveria poder comer",simulador.processaJogada(3,1,1,3));
    }

    @Test
    public void sugetaoPoneiMagico(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_ponei.txt"));
        List<String> possiveisMovimentos = new ArrayList<>();
        possiveisMovimentos.add("2, 2");
        assertEquals("Deveria dar a posição [2, 2]", possiveisMovimentos, simulador.obterSugestoesJogada(0,0));
    }

//Testes da peca Padre da Vila
    @Test
    public void moverPadre(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_padre.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void moverPadreDepoisLimite(){
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padre.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,5,5));
    }

    @Test
    public void moverPadreHorizontal(){
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padre.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,0,3));
    }

    @Test
    public void moverPadreVertical(){
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padre.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,3,0));
    }

    @Test
    public void moverPadreComRainhaAdversariaPerto(){
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padreComRainhaAdversariaPerto.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void moverComPecaNoCaminho(){
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padreComPecaNoCaminho.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,3,3));
    }

    @Test
    public void padreComeRainhaAdversaria(){
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padreComPecaNoCaminho.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void padreComePecaEquipa(){
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padreComPecaEquipa.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void sugestaoPadre(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_padre.txt"));
        List<String> possiveisMovimentos = new ArrayList<>();
        possiveisMovimentos.add("1, 1");
        possiveisMovimentos.add("2, 2");
        possiveisMovimentos.add("3, 3");
        assertEquals("Deveria dar as posições [1, 1, 2, 2, 3, 3]",possiveisMovimentos,simulador.obterSugestoesJogada(0,0));
    }

//Testes da peça Torre Horizontal
    @Test
    public void moverTorreHorizontal(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreH.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(0,1,3,1));
    }

    @Test
    public void moverTorreHNaVertical(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreH.txt"));
        assertFalse("Deveria poder mover",simulador.processaJogada(0,1,0,3));
    }

    @Test
    public void moverTorreHorizontalComPecaCaminho(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreHComPecaCaminho.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,1,3,1));
    }

    @Test
    public void torreHorizontalComePecaEquipa(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreHComePecaEquipa.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(0,1,2,1));
    }

    @Test
    public void torreHorizontalComePecaEquipaAdversaria(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreHComPecaCaminho.txt"));
        assertTrue("Deveria poder comer",simulador.processaJogada(0,1,2,1));
    }

    @Test
    public void sugestaoTorreHorizontal(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreH.txt"));
        List<String> possiveisMovimentos = new ArrayList<>();
        possiveisMovimentos.add("1, 1");
        possiveisMovimentos.add("2, 1");
        possiveisMovimentos.add("3, 1");
        assertEquals("Deveria dar as posições [1, 1, 2, 1, 3, 1]",possiveisMovimentos,simulador.obterSugestoesJogada(0,1));
    }

//Testes da peça Torre Vertical
    @Test
    public void moverTorreVertical(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreV.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(1,0,1,3));
    }

    @Test
    public void moverTorreVNaHorizontal(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreV.txt"));
        assertFalse("Deveria poder mover",simulador.processaJogada(1,0,2,0));
    }

    @Test
    public void moverTorreVerticalComPecaCaminho(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreVComPecaCaminho.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(1,0,1,3));
    }

    @Test
    public void torreVerticalComePecaEquipa(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreVComePecaEquipa.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(1,0,1,2));
    }

    @Test
    public void torreVerticalComePecaEquipaAdversaria(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreVComPecaCaminho.txt"));
        assertTrue("Deveria poder comer",simulador.processaJogada(1,0,1,2));
    }

    @Test
    public void sugestaoTorreVertical(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreV.txt"));
        List<String> possiveisMovimentos = new ArrayList<>();
        possiveisMovimentos.add("1, 1");
        possiveisMovimentos.add("1, 2");
        possiveisMovimentos.add("1, 3");
        assertEquals("Deveria dar as posições [1, 1, 1, 2, 1, 3]",possiveisMovimentos,simulador.obterSugestoesJogada(1,0));
    }
//Testes da peça Lebre
    @Test
    public void moverLebre(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_lebre.txt"));
        simulador.turno=0;
        assertTrue("Deveria poder mover",simulador.processaJogada(0,0,1,1));
    }

    @Test
    public void naoMoverLebre(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_lebre.txt"));
        simulador.turno = 1;
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,1,1));
    }

    @Test
    public void lebreComePecaEquipa(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_lebreComePecaEquipa.txt"));
        simulador.turno=0;
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,1,1));
    }

    @Test
    public void lebreComePecaEquipaAdversaria(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_lebreComePecaAdversaria.txt"));
        simulador.turno=0;
        assertTrue("Deveria poder mover",simulador.processaJogada(0,0,1,1));
    }

    @Test
    public void lebreMoverLimite(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_lebre.txt"));
        simulador.turno=0;
        assertFalse("Não deveria mover",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void sugetaoLebre(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_lebreSugestao.txt"));
        List<String> posiveisMovimentos = new ArrayList<>();
        posiveisMovimentos.add("1, 0");
        posiveisMovimentos.add("3, 0");
        posiveisMovimentos.add("3, 2");
        posiveisMovimentos.add("1, 2");
        assertEquals("Deveria dar as posições [1, 0, 3, 0, 3, 2, 1, 2]",posiveisMovimentos,simulador.obterSugestoesJogada(2,1));
    }
//Testes da peca Joker

    @Test
    public void moverJokerComoRainha(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerPar.txt"));
        simulador.turno=0;
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void moverJokerComoPoneiMagico(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerImpar.txt"));
        simulador.turno=1;
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,3,3));
    }

    @Test
    public void moverJokerComoPadreDaVila(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerPar.txt"));
        simulador.turno=2;
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,3,3));
    }

    @Test
    public void moverJokerComoTorreHor(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerImpar.txt"));
        simulador.turno=3;
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,3,1));
    }

    @Test
    public void moverJokerComoTorreVer(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerPar.txt"));
        simulador.turno=4;
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,1,3));
    }

    @Test
    public void moverJokerComoLebre(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerImpar.txt"));
        simulador.turno=5;
        assertFalse("Não deveria poder mover",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void sugetaoJoker(){
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerSugestao.txt"));
        simulador.turno=0;
        List<String> possiveisMovimentos = new ArrayList<>();
        possiveisMovimentos.add("1, 0");
        possiveisMovimentos.add("1, 1");
        possiveisMovimentos.add("0, 1");
        possiveisMovimentos.add("2, 0");
        possiveisMovimentos.add("2, 2");
        possiveisMovimentos.add("0, 2");
        possiveisMovimentos.add("3, 0");
        possiveisMovimentos.add("3, 3");
        possiveisMovimentos.add("0, 3");
        assertEquals("Deveria dar as posições [1, 0, 1, 1, 0, 1 , 2, 0 , 2, 2, 0, 2, 3, 0, 3, 3, 0, 3]",possiveisMovimentos,simulador.obterSugestoesJogada(0,0));
    }

}
