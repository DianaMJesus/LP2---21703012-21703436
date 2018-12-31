package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.awt.*;
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
        CrazyPiece rei = new Rei (1,10,"Rei Preto");
        rei.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rei);
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void naoMoverRei(){
        Simulador simulador = new Simulador(4);
        CrazyPiece rei = new Rei (1,10,"Rei Preto");
        rei.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rei);
        assertFalse("Não deveria poder mover",simulador.processaJogada(1,1,3,3));
    }

    @Test
    public void moverReiDepoisLimite(){
        Simulador simulador = new Simulador(4);
        CrazyPiece rei = new Rei(1,10,"Rei Preto");
        rei.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rei);
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,0,3));
    }

    @Test
    public void reiComePecaEquipa(){
        Simulador simulador = new Simulador(4);
        CrazyPiece rei = new Rei(1,10,"Rei Preto");
        rei.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rei);
        CrazyPiece ponei = new PoneiMagico(2,10,"Ponei Preto");
        ponei.setPosicao(1,2);
        pecasMalucas.add(ponei);
        assertFalse("Não deveria poder comer",simulador.processaJogada(1,1,1,2));
    }

    @Test
    public void reiComePecaEquipaAdversaria(){
        Simulador simulador = new Simulador(4);
        CrazyPiece rei = new Rei(1,10,"Rei Preto");
        rei.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rei);
        CrazyPiece ponei = new PoneiMagico(2,20,"Ponei Preto");
        ponei.setPosicao(1,2);
        pecasMalucas.add(ponei);
        assertTrue("Deveria poder comer",simulador.processaJogada(1,1,1,2));
    }

    @Test
    public void sugetaoRei(){
        Simulador simulador = new Simulador(4);
        CrazyPiece rei = new Rei(1,10,"Rei Preto");
        rei.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rei);
        List<String> possiveisMovimentos = new ArrayList<>();
        possiveisMovimentos.add("1, 0");
        possiveisMovimentos.add("1, 1");
        possiveisMovimentos.add("0, 1");
        assertEquals("Deveria dar as posições [1, 0, 1, 1, 0, 1]",possiveisMovimentos,simulador.obterSugestoesJogada(0,0));
    }

//Testes da peca Rainha
    @Test
    public void moverRainhaDiagonal(){
        Simulador simulador = new Simulador(4);
        CrazyPiece rainha = new Rainha (1,10,"Rainha Preta");
        rainha.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rainha);
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void moverRainhaHorizontal(){
        Simulador simulador = new Simulador(4);
        CrazyPiece rainha = new Rainha(1,10,"Rainha Preta");
        rainha.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rainha);
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,2,1));
    }

    @Test
    public void moverRainhaVertical(){
        Simulador simulador = new Simulador(4);
        CrazyPiece rainha = new Rainha(1,10,"Rainha Preta");
        rainha.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rainha);
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,1,2));
    }

    @Test
    public void moverRainhaDepoisLimite(){
        Simulador simulador = new Simulador(8);
        CrazyPiece rainha = new Rainha(1,10,"Padre Preto");
        rainha.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rainha);
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,0,7));
    }

    @Test
    public void moverRainhaDiagonalComPecaFrente(){
        Simulador simulador = new Simulador();
        CrazyPiece rainha = new Rainha(1,10,"Rainha Preta");
        rainha.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rainha);
        CrazyPiece ponei = new PoneiMagico(2,20,"Ponei Branco");
        ponei.setPosicao(2,2);
        pecasMalucas.add(ponei);
        assertFalse("Nao deveria poder andar",simulador.processaJogada(1,1,3,3));
    }

    @Test
    public void moverRainhaHorizontalComPecaFrente(){
        Simulador simulador = new Simulador();
        CrazyPiece rainha = new Rainha(1,10,"Rainha Preta");
        rainha.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rainha);
        CrazyPiece ponei = new PoneiMagico(2,20,"Ponei Branco");
        ponei.setPosicao(2,1);
        pecasMalucas.add(ponei);
        assertFalse("Nao deveria poder andar",simulador.processaJogada(1,1,3,1));
    }

    @Test
    public void moverRainhaVerticalComPecaFrente(){
        Simulador simulador = new Simulador();
        CrazyPiece rainha = new Rainha(1,10,"Rainha Preta");
        rainha.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rainha);
        CrazyPiece ponei = new PoneiMagico(2,20,"Ponei Branco");
        ponei.setPosicao(1,2);
        pecasMalucas.add(ponei);
        assertFalse("Nao deveria poder andar",simulador.processaJogada(1,1,1,3));
    }

    @Test
    public void rainhaComePecaEquipa(){
        Simulador simulador = new Simulador(4);
        CrazyPiece rainha = new Rainha(1,10,"Rainha Preta");
        rainha.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rainha);
        CrazyPiece padre = new PadreDaVila(2,10,"Padre Preto");
        padre.setPosicao(2,2);
        pecasMalucas.add(padre);
        assertFalse("Não deveria poder comer",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void rainhaComeRainhaAdversaria(){
        Simulador simulador = new Simulador(4);
        CrazyPiece rainha = new Rainha(1,10,"Rainha Preta");
        rainha.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rainha);
        CrazyPiece rainhaB = new Rainha(2,20,"Rainha Branca");
        rainhaB.setPosicao(2,2);
        pecasMalucas.add(rainhaB);
        assertFalse("Não deveria poder comer",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void rainhaComePecaAdversaria(){
        Simulador simulador = new Simulador(4);
        CrazyPiece rainha = new Rainha(1,10,"Rainha Preta");
        rainha.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rainha);
        CrazyPiece ponei = new PoneiMagico(2,20,"Ponei Branco");
        ponei.setPosicao(2,2);
        pecasMalucas.add(ponei);
        assertTrue("Deveria poder comer",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void sugestaoRainha(){
        Simulador simulador = new Simulador(4);
        CrazyPiece rainha = new Rainha(1,10,"Rainha Preta");
        rainha.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(rainha);
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
        assertEquals("Deveria dar [1, 0, 1, 1, 0, 1, 2, 0, 2, 2, 0, 2, 3, 0, 3, 3, 0, 3]",possiveisMovimentos,simulador.obterSugestoesJogada(0,0));
    }

//Testes para a peça Ponei Magico
    @Test
    public void moverPonei(){
        Simulador simulador = new Simulador(4);
        CrazyPiece ponei = new PoneiMagico(1,10, "Ponei Preto");
        ponei.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(ponei);
        assertTrue("Deveria poder mover",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void moverPoneiDepoisLimite(){
        Simulador simulador = new Simulador(6);
        CrazyPiece ponei = new PoneiMagico(1,10,"Ponei Preto");
        ponei.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(ponei);
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,3,3));
    }

    @Test
    public void moverPoneiCom1Rei() {
        Simulador simulador = new Simulador(4);
        CrazyPiece ponei = new PoneiMagico(1,  10, "Ponei Preto");
        ponei.setPosicao(3, 1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(ponei);
        CrazyPiece rei = new Rei(2, 20, "Rei Branco");
        rei.setPosicao(3, 3);
        pecasMalucas.add(rei);
        assertTrue("Deveria poder mover", simulador.processaJogada(3, 1, 1, 3));
    }

    @Test
    public void moverPoneiCom2Reis() {
        Simulador simulador = new Simulador(4);
        CrazyPiece ponei = new PoneiMagico(1,  10, "Ponei Preto");
        ponei.setPosicao(3, 1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(ponei);
        CrazyPiece rei = new Rei(2, 20, "Rei Branco");
        rei.setPosicao(3, 3);
        pecasMalucas.add(rei);
        CrazyPiece reiSegundo = new Rei (3,20,"Segundo Rei Branco");
        reiSegundo.setPosicao(1,2);
        pecasMalucas.add(reiSegundo);
        assertFalse("Não deveria poder mover", simulador.processaJogada(3, 1, 1, 3));
    }

    @Test
    public void moverPoneiPecaCaminho(){
        Simulador simulador = new Simulador(4);
        CrazyPiece ponei = new PoneiMagico(1,10,"Ponei Preto");
        ponei.setPosicao(3,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(ponei);
        CrazyPiece rainha = new Rainha(2,10,"Rainha Preta");
        rainha.setPosicao(3,3);
        pecasMalucas.add(rainha);
        assertTrue("Deveria poder mover",simulador.processaJogada(3,1,1,3));
    }

    @Test
    public void poneiComePecaEquipa(){
        Simulador simulador = new Simulador(4);
        CrazyPiece ponei = new PoneiMagico(1,10,"Ponei Preto");
        ponei.setPosicao(3,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(ponei);
        CrazyPiece rei = new Rei(2,10,"Rei Branca");
        rei.setPosicao(1,3);
        pecasMalucas.add(rei);
        assertFalse("Não deveria poder comer",simulador.processaJogada(3,1,1,3));
    }

    @Test
    public void poneiComePecaEquipaAdversaria(){
        Simulador simulador = new Simulador(4);
        CrazyPiece ponei = new PoneiMagico(1,10,"Ponei Preto");
        ponei.setPosicao(3,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(ponei);
        CrazyPiece rei = new Rei(2,20,"Rei Branca");
        rei.setPosicao(1,3);
        pecasMalucas.add(rei);
        assertTrue("Deveria poder comer",simulador.processaJogada(3,1,1,3));
    }

    @Test
    public void sugetaoPoneiMagico(){
        Simulador simulador = new Simulador(4);
        CrazyPiece ponei = new PoneiMagico(1,10,"Ponei Preto");
        ponei.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(ponei);
        List<String> possiveisMovimentos = new ArrayList<>();
        possiveisMovimentos.add("2, 2");
        assertEquals("Deveria dar a posição [2, 2]", possiveisMovimentos, simulador.obterSugestoesJogada(0,0));
    }

//Testes da peca Padre da Vila
    @Test
    public void moverPadre(){
        Simulador simulador = new Simulador(4);
        CrazyPiece padre = new PadreDaVila(1,10,"Padre Preto");
        padre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(padre);
        assertTrue("Deveria poder mover",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void moverPadreDepoisLimite(){
        Simulador simulador = new Simulador(6);
        CrazyPiece padre = new PadreDaVila(1,10,"Padre Preto");
        padre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(padre);
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,5,5));
    }

    @Test
    public void moverPadreHorizontal(){
        Simulador simulador = new Simulador(6);
        CrazyPiece padre = new PadreDaVila(1,10,"Padre Preto");
        padre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(padre);
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,0,3));
    }

    @Test
    public void moverPadreVertical(){
        Simulador simulador = new Simulador(6);
        CrazyPiece padre = new PadreDaVila(1,10,"Padre Preto");
        padre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(padre);
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,3,0));
    }

    @Test
    public void moverPadreComRainhaAdversariaPerto(){
        Simulador simulador = new Simulador(6);
        CrazyPiece padre = new PadreDaVila(1,10,"Padre Preto");
        padre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(padre);
        CrazyPiece rainha = new Rainha(2,20,"Rainha Branco");
        rainha.setPosicao(1,3);
        pecasMalucas.add(rainha);
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void moverComPecaNoCaminho(){
        Simulador simulador = new Simulador(6);
        CrazyPiece padre = new PadreDaVila(1,10,"Padre Preto");
        padre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(padre);
        CrazyPiece rainha = new Rainha(2,20,"Rainha Branco");
        rainha.setPosicao(2,2);
        pecasMalucas.add(rainha);
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,3,3));
    }

    @Test
    public void padreComeRainhaAdversaria(){
        Simulador simulador = new Simulador(6);
        CrazyPiece padre = new PadreDaVila(1,10,"Padre Preto");
        padre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(padre);
        CrazyPiece rainha = new Rainha(2,20,"Rainha Branca");
        rainha.setPosicao(2,2);
        pecasMalucas.add(rainha);
        assertFalse("Não deveria poder comer",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void padreComePecaEquipa(){
        Simulador simulador = new Simulador(6);
        CrazyPiece padre = new PadreDaVila(1,10,"Padre Preto");
        padre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(padre);
        CrazyPiece rainha = new Rainha(2,10,"Rainha Branca");
        rainha.setPosicao(2,2);
        pecasMalucas.add(rainha);
        assertFalse("Não deveria poder comer",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void sugestaoPadre(){
        Simulador simulador = new Simulador(4);
        CrazyPiece padre = new PadreDaVila(1,10,"Padre Preto");
        padre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(padre);
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
        CrazyPiece torre = new TorreH(1,10,"TorreH Preta",4);
        torre.setPosicao(0,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(torre);
        assertTrue("Deveria poder mover",simulador.processaJogada(0,1,3,1));
    }

    @Test
    public void moverTorreHNaVertical(){
        Simulador simulador = new Simulador(4);
        CrazyPiece torre = new TorreH(1,10,"TorreH Preta",4);
        torre.setPosicao(0,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(torre);
        assertFalse("Deveria poder mover",simulador.processaJogada(0,1,0,3));
    }

    @Test
    public void moverTorreHorizontalComPecaCaminho(){
        Simulador simulador = new Simulador(4);
        CrazyPiece torre = new TorreH(1,10,"TorreH Preta",4);
        torre.setPosicao(0,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(torre);
        CrazyPiece padre = new PadreDaVila(2,20,"Padre Branco");
        padre.setPosicao(2,1);
        pecasMalucas.add(padre);
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,1,3,1));
    }

    @Test
    public void torreHorizontalComePecaEquipa(){
        Simulador simulador = new Simulador(4);
        CrazyPiece torre = new TorreH(1,10,"TorreH Preta",4);
        torre.setPosicao(0,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(torre);
        CrazyPiece padre = new PadreDaVila(2,10,"Padre Branco");
        padre.setPosicao(2,1);
        pecasMalucas.add(padre);
        assertFalse("Não deveria poder comer",simulador.processaJogada(0,1,2,1));
    }

    @Test
    public void torreHorizontalComePecaEquipaAdversaria(){
        Simulador simulador = new Simulador(4);
        CrazyPiece torre = new TorreH(1,10,"TorreH Preta",4);
        torre.setPosicao(0,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(torre);
        CrazyPiece padre = new PadreDaVila(2,20,"Padre Branco");
        padre.setPosicao(2,1);
        pecasMalucas.add(padre);
        assertTrue("Deveria poder comer",simulador.processaJogada(0,1,2,1));
    }

    @Test
    public void sugestaoTorreHorizontal(){
        Simulador simulador = new Simulador(4);
        CrazyPiece torre = new TorreH(1,10,"TorreH Preta",4);
        torre.setPosicao(0,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(torre);
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
        CrazyPiece torre = new TorreV(1,10,"TorreV Preta",4);
        torre.setPosicao(1,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(torre);
        assertTrue("Deveria poder mover",simulador.processaJogada(1,0,1,3));
    }

    @Test
    public void moverTorreVNaHorizontal(){
        Simulador simulador = new Simulador(4);
        CrazyPiece torre = new TorreV(1,10,"TorreV Preta",4);
        torre.setPosicao(1,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(torre);
        assertFalse("Deveria poder mover",simulador.processaJogada(1,0,2,0));
    }

    @Test
    public void moverTorreVerticalComPecaCaminho(){
        Simulador simulador = new Simulador(4);
        CrazyPiece torre = new TorreV(1,10,"TorreV Preta",4);
        torre.setPosicao(1,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(torre);
        CrazyPiece padre = new PadreDaVila(2,20,"Padre Branco");
        padre.setPosicao(1,1);
        pecasMalucas.add(padre);
        assertFalse("Não deveria poder mover",simulador.processaJogada(1,0,1,3));
    }

    @Test
    public void torreVerticalComePecaEquipa(){
        Simulador simulador = new Simulador(4);
        CrazyPiece torre = new TorreV(1,10,"TorreV Preta",4);
        torre.setPosicao(1,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(torre);
        CrazyPiece padre = new PadreDaVila(2,10,"Padre Branco");
        padre.setPosicao(1,2);
        pecasMalucas.add(padre);
        assertFalse("Não deveria poder comer",simulador.processaJogada(1,0,1,2));
    }

    @Test
    public void torreVerticalComePecaEquipaAdversaria(){
        Simulador simulador = new Simulador(4);
        CrazyPiece torre = new TorreV(1,10,"TorreH Preta",4);
        torre.setPosicao(1,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(torre);
        CrazyPiece padre = new PadreDaVila(2,20,"Padre Branco");
        padre.setPosicao(1,2);
        pecasMalucas.add(padre);
        assertTrue("Deveria poder comer",simulador.processaJogada(1,0,1,2));
    }

    @Test
    public void sugestaoTorreVertical(){
        Simulador simulador = new Simulador(4);
        CrazyPiece torre = new TorreV(1,10,"TorreH Preta",4);
        torre.setPosicao(1,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(torre);
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
        CrazyPiece lebre = new Lebre(1,10,"Lebra Preta");
        lebre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(lebre);
        assertTrue("Deveria poder mover",simulador.processaJogada(0,0,1,1));
    }

    @Test
    public void naoMoverLebre(){
        Simulador simulador = new Simulador(4);
        CrazyPiece lebre = new Lebre(1,20,"Lebra Branca");
        lebre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(lebre);
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,1,1));
    }

    @Test
    public void lebreComePecaEquipa(){
        Simulador simulador = new Simulador(4);
        CrazyPiece lebre = new Lebre(1,10,"Lebre Preta");
        lebre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(lebre);
        CrazyPiece padre = new PadreDaVila(2,10,"Padre Preto");
        padre.setPosicao(1,1);
        pecasMalucas.add(padre);
        assertFalse("Não deveria poder mover",simulador.processaJogada(0,0,1,1));
    }

    @Test
    public void lebreComePecaEquipaAdversaria(){
        Simulador simulador = new Simulador(4);
        CrazyPiece lebre = new Lebre(1,10,"Lebre Preta");
        lebre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(lebre);
        CrazyPiece padre = new PadreDaVila(2,20,"Padre Preto");
        padre.setPosicao(1,1);
        pecasMalucas.add(padre);
        assertTrue("Deveria poder mover",simulador.processaJogada(0,0,1,1));
    }

    @Test
    public void lebreMoverLimite(){
        Simulador simulador = new Simulador(4);
        CrazyPiece lebre = new Lebre(1,10,"Lebre Preta");
        lebre.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(lebre);
        assertFalse("Não deveria mover",simulador.processaJogada(0,0,2,2));
    }

    @Test
    public void sugetaoLebre(){
        Simulador simulador = new Simulador(4);
        CrazyPiece lebre = new Lebre(1,10,"Lebre Preto");
        lebre.setPosicao(2,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(lebre);
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
        CrazyPiece joker = new Joker (1,10,"Rainha Preta",0,4);
        joker.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(joker);
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void moverJokerComoPoneiMagico(){
        Simulador simulador = new Simulador(4);
        CrazyPiece joker = new Joker (1,20,"Ponei Branco",1,4);
        joker.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(joker);

        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,3,3));
    }

    @Test
    public void moverJokerComoPadreDaVila(){
        Simulador simulador = new Simulador(4);
        CrazyPiece joker = new Joker (1,10,"Padre Preta",2,4);
        joker.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(joker);
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,3,3));
    }

    @Test
    public void moverJokerComoTorreHor(){
        Simulador simulador = new Simulador(4);
        CrazyPiece joker = new Joker (1,20,"Rainha Preta",3,4);
        joker.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(joker);
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,3,1));
    }

    @Test
    public void moverJokerComoTorreVer(){
        Simulador simulador = new Simulador(4);
        CrazyPiece joker = new Joker (1,10,"Rainha Preta",4,4);
        joker.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(joker);
        assertTrue("Deveria poder mover",simulador.processaJogada(1,1,1,3));
    }

    @Test
    public void moverJokerComoLebre(){
        Simulador simulador = new Simulador(4);
        CrazyPiece joker = new Joker (1,20,"Rainha Preta",5,4);
        joker.setPosicao(1,1);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(joker);
        assertFalse("Não deveria poder mover",simulador.processaJogada(1,1,2,2));
    }

    @Test
    public void sugetaoJoker(){
        Simulador simulador = new Simulador(4);
        CrazyPiece joker = new Joker(1,10,"Joker Preto",0,4);
        joker.setPosicao(0,0);
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.add(joker);
        List<String> possiveisMovimentos = new ArrayList<>();
        assertEquals("Deveria dar as posições [1, 0, 1, 1, 0, 1 , 2, 0 , 2, 2 , 0, 2, 3, 0, 3, 3 , 0, 3]",possiveisMovimentos,simulador.obterSugestoesJogada(0,0));
    }

    @Test
    public void processajogada_recuperacaoDePecas() {
        List<CrazyPiece>pecasMalucas=new ArrayList<>();
        pecasMalucas.clear();
        Simulador simulador = new Simulador(5);
        CrazyPiece novaPeace = new Rainha(1, 10, "1156");
        novaPeace.setPosicao(1, 1);
        pecasMalucas.add(novaPeace);
        CrazyPiece recoperacao = pecasMalucas.get(0);
        assertTrue("deveria mexer", simulador.processaJogada(1, 1, 0, 0));
        assertNotEquals("deveria ser diferente", String.valueOf(recoperacao), String.valueOf(simulador.recuperaPecas.get(0)));
    }

}
