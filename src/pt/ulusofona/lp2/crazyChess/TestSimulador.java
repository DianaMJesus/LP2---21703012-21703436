package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestSimulador {

//Teste de leitura de ficheiro
    /*@Test
    public void faltaFicheiro() throws InvalidSimulatorInputException {
        Simulador simulador = new Simulador();
        simulador.iniciaJogo(new File(""));
        assertEquals("Não selecionou nenhum ficheiro", false);
    }*/

//Testes da peca Rei
    @Test
    public void podeMoverRei() throws InvalidSimulatorInputException {
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rei.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void naoMoverRei() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rei.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(1,1,3,3));
    }

    @Test
    public void reiComePecaEquipa() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_reiComePecaEquipa.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(1,1,1,2));
    }

    @Test
    public void reiComePecaEquipaAdversaria() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_reiComePecaEquipaAdversaria.txt"));
        assertTrue("Deveria poder comer",simulador.processaJogada(1,1,1,2));
    }

    @Test
    public void sugetaoRei() throws InvalidSimulatorInputException{
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
    public void moverRainhaDiagonal() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainha.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void moverRainhaHorizontal() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainha.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,2,1));
    }

    @Test
    public void moverRainhaVertical() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainha.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,1,2));
    }

    @Test
    public void moverRainhaDepoisLimite() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(8);
        simulador.iniciaJogo(new File("test-files/test_rainha.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(1,1,0,7));
    }

    @Test
    public void moverRainhaDiagonalComPecaFrente() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador();
        simulador.iniciaJogo(new File("test-files/test_rainhaDiagonalComPecaFrente.txt"));
        assertFalse("Nao deveria poder andar",simulador.processaJogada(1,1,3,3));
    }

    @Test
    public void moverRainhaHorizontalComPecaFrente()throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador();
        simulador.iniciaJogo(new File("test-files/test_rainhaHorizontalComPecaFrente.txt"));
        assertFalse("Nao deveria poder andar",simulador.processaJogada(1,1,3,1));
    }

    @Test
    public void moverRainhaVerticalComPecaFrente() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador();
        simulador.iniciaJogo(new File("test-files/test_rainhaVerticalComPecaFrente.txt"));
        assertFalse("Nao deveria poder andar",simulador.processaJogada(1,1,1,3));
    }

    @Test
    public void rainhaComePecaEquipa() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainhaComePecaEquipa.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void rainhaComeRainhaAdversaria() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainhaComeRainhaAdversaria.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public  void rainhaMovePertoPadre() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_rainhaMovePertoPadre.txt"));
        assertFalse("Não deveria poder mover para perto do padre",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void sugestaoRainha() throws InvalidSimulatorInputException{
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
    public void moverPonei() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_ponei.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void moverPoneiDepoisLimite() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_ponei.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,3,3));
    }

    @Test
    public void moverPoneiCom1Rei() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_poneiCom1Rei.txt"));
        assertTrue("Deveria poder mover", simulador.processaJogada(3, 1, 1, 3));
    }

    @Test
    public void moverPoneiCom2Reis() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_poneiCom2Rei.txt"));
        assertFalse("Não deveria poder mover", simulador.processaJogada(3, 1, 1, 3));
    }

    @Test
    public void moverPoneiPecaCaminho() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_poneiComPecaCaminho.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(3,1,1,3));
    }

    @Test
    public void moverPonei2PecaCaminho() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_poneiCom2PecaCaminho.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(3,1,1,3));
    }

    @Test
    public void poneiComePecaEquipa() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_poneiComePecaEquipa.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(3,1,1,3));
    }

    @Test
    public void poneiComePecaEquipaAdversaria() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_poneiComePecaEquipaAdversaria.txt"));
        assertTrue("Deveria poder comer",simulador.processaJogada(3,1,1,3));
    }

    @Test
    public void sugetaoPoneiMagico() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_ponei.txt"));
        List<String> possiveisMovimentos = new ArrayList<>();
        possiveisMovimentos.add("2, 2");
        assertEquals("Deveria dar a posição [2, 2]", possiveisMovimentos, simulador.obterSugestoesJogada(0,0));
    }

//Testes da peca Padre da Vila
    @Test
    public void moverPadre() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_padre.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void moverPadreDepoisLimite() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padre.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,5,5));
    }

    @Test
    public void moverPadreHorizontal() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padre.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,0,3));
    }

    @Test
    public void moverPadreVertical() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padre.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,3,0));
    }

    @Test
    public void moverPadreComRainhaAdversariaPerto() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padreComRainhaAdversariaPerto.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void moverComPecaNoCaminho() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padreComPecaNoCaminho.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,3,3));
    }

    @Test
    public void padreComeRainhaAdversaria() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padreComPecaNoCaminho.txt"));
        assertTrue("Deveria poder comer",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void padreComePecaEquipa() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(6);
        simulador.iniciaJogo(new File("test-files/test_padreComPecaEquipa.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void sugestaoPadre() throws InvalidSimulatorInputException{
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
    public void moverTorreHorizontal() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreH.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(0,1,3,1));
    }

    @Test
    public void moverTorreHNaVertical() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreH.txt"));
        assertFalse("Deveria poder mover",simulador.processaJogada(0,1,0,3));
    }

    @Test
    public void moverTorreHorizontalComPecaCaminho() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreHComPecaCaminho.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,1,3,1));
    }

    @Test
    public void torreHorizontalComePecaEquipa() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreHComePecaEquipa.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(0,1,2,1));
    }

    @Test
    public void torreHorizontalComePecaEquipaAdversaria() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreHComPecaCaminho.txt"));
        assertTrue("Deveria poder comer",simulador.processaJogada(0,1,2,1));
    }

    @Test
    public void sugestaoTorreHorizontal() throws InvalidSimulatorInputException{
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
    public void moverTorreVertical() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreV.txt"));
        assertTrue("Deveria poder mover",simulador.processaJogada(1,0,1,3));
    }

    @Test
    public void moverTorreVNaHorizontal() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreV.txt"));
        assertFalse("Deveria poder mover",simulador.processaJogada(1,0,2,0));
    }

    @Test
    public void moverTorreVerticalComPecaCaminho() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreVComPecaCaminho.txt"));
        assertFalse("Não deveria poder mover",simulador.processaJogada(1,0,1,3));
    }

    @Test
    public void torreVerticalComePecaEquipa() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreVComePecaEquipa.txt"));
        assertFalse("Não deveria poder comer",simulador.processaJogada(1,0,1,2));
    }

    @Test
    public void torreVerticalComePecaEquipaAdversaria() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_torreVComPecaCaminho.txt"));
        assertTrue("Deveria poder comer",simulador.processaJogada(1,0,1,2));
    }

    @Test
    public void sugestaoTorreVertical() throws InvalidSimulatorInputException{
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
    public void moverLebre() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_lebre.txt"));
        simulador.turno=0;
        assertTrue("Deveria poder mover",simulador.processaJogada(0,0,1,1));
    }

    @Test
    public void naoMoverLebre() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_lebre.txt"));
        simulador.turno = 1;
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,1,1));
    }

    @Test
    public void lebreComePecaEquipa() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_lebreComePecaEquipa.txt"));
        simulador.turno=0;
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,1,1));
    }

    @Test
    public void lebreComePecaEquipaAdversaria() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_lebreComePecaAdversaria.txt"));
        simulador.turno=0;
        assertTrue("Deveria poder mover",simulador.processaJogada(0,0,1,1));
    }

    @Test
    public void lebreMoverLimite() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_lebre.txt"));
        simulador.turno=0;
        assertFalse("Não deveria mover",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void sugetaoLebre() throws InvalidSimulatorInputException{
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
    public void moverJokerComoRainha() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerPar.txt"));
        simulador.turno=0;
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void moverJokerComoPoneiMagico() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerImpar.txt"));
        simulador.turno=1;
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,3,3));
    }

    @Test
    public void moverJokerComoPadreDaVila() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerPar.txt"));
        simulador.turno=2;
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,3,3));
    }

    @Test
    public void moverJokerComoTorreHor() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerImpar.txt"));
        simulador.turno=3;
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,3,1));
    }

    @Test
    public void moverJokerComoTorreVer() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerPar.txt"));
        simulador.turno=4;
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,1,3));
    }

    @Test
    public void moverJokerComoLebre() throws InvalidSimulatorInputException{
        Simulador simulador = new Simulador(4);
        simulador.iniciaJogo(new File("test-files/test_jokerImpar.txt"));
        simulador.turno=5;
        assertFalse("Não deveria poder mover",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void sugetaoJoker() throws InvalidSimulatorInputException{
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
