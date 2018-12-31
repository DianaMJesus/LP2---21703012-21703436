package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class CrazyPiece {
    int id, equipa, tipoPeca, posX = -1, posY = -1, passoMax;
    String imagePNG;
    String alcunha;
    String valorRelativo;
    boolean emJogo = false;

    public CrazyPiece() {
    }

    public CrazyPiece(int id, String alcunha) {
        this.id = id;
        this.alcunha = alcunha;
    }

    public boolean equipaEquals(int valor) {
        return this.equipa == valor;
    }

    public int getId() {
        return id;
    }

    public int getEquipa() {
        return equipa;
    }

    public int getTipoPeca() {
        return tipoPeca;
    }

    public String getAlcunha() {
        return alcunha;
    }

    public boolean getEmJogo() {
        return emJogo;
    }

    public String getImagePNG() {
        return imagePNG;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosicao(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
    //se existe peca e se é da equipa

    public boolean comida() {
        return this.posY == -1 && this.posX == -1;
    }

    public void estaEmJogo() {
        this.emJogo = true;
    }

    public boolean podeMover(int x, int y) {
        return false;
    }

    /*public boolean haPadre(int x, int y, int tPeca) {
        CrazyPiece novaPeace = Simulador.receberPeca(x,y);
        System.out.println("ola");

        for (int pos = 0; pos <= 7; pos++) {
            switch (pos) {
                case 0: {
                    novaPeace = Simulador.receberPeca(x - 1, y - 1);
                    System.out.println(novaPeace);
                    break;
                }
                case 1: {
                    novaPeace = Simulador.receberPeca(x, y - 1);
                    System.out.println(novaPeace);
                    break;
                }
                case 2: {
                    novaPeace = Simulador.receberPeca(x + 1, y - 1);
                    System.out.println(novaPeace);
                    break;
                }
                case 3: {
                    novaPeace = Simulador.receberPeca(x + 1, y);
                    System.out.println(novaPeace);
                    break;
                }
                case 4: {
                    novaPeace = Simulador.receberPeca(x + 1, y + 1);
                    System.out.println(novaPeace);
                    break;
                }
                case 5: {
                    novaPeace = Simulador.receberPeca(x, y + 1);
                    System.out.println(novaPeace);
                    break;
                }
                case 6: {
                    novaPeace = Simulador.receberPeca(x - 1, y + 1);
                    System.out.println(novaPeace);
                    break;
                }
                case 7: {
                    novaPeace = Simulador.receberPeca(x - 1, y);
                    System.out.println(novaPeace);
                    break;
                }
                default: {
                    break;
                }
            }
            System.out.println(novaPeace);
            if (novaPeace != null && novaPeace.getTipoPeca() == tPeca && novaPeace.getEquipa() != Simulador.equipaJogar) {
                return false;
            }
        }
        return true;
    }*/

    public List<String> sugetaoJogada(int x, int y,int tamanhoTabuleiro,int equipaJogar,int turno) {
        List<String> posibilidades = new ArrayList<>();
        String resultado;
        CrazyPiece pecaRecebida=Simulador.receberPeca(x,y);

        if(pecaRecebida!=null && pecaRecebida.getTipoPeca()!=2) {
            for (int pos = 1; pos <= pecaRecebida.passoMax; pos++) {
                if (podeMover(x - pos, y - pos)){
                    if ((x - pos >= 0 && x - pos < tamanhoTabuleiro) && (y - pos >= 0 && y - pos < tamanhoTabuleiro)) {
                        System.out.println("0");
                        resultado = (x - pos) + ", " + (y - pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x, y - pos)){
                    if ((x >= 0 && x < tamanhoTabuleiro) && (y - pos >= 0 && y - pos < tamanhoTabuleiro)){
                        System.out.println("1");
                        resultado = x + ", " + (y - pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x + pos, y - pos)){
                    if ((x + pos >= 0 && x + pos < tamanhoTabuleiro) && (y - pos >= 0 && y - pos < tamanhoTabuleiro)) {
                        System.out.println("2");
                        resultado = (x + pos) + ", " + (y - pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x + pos, y)) {
                    if ((x + pos >= 0 && x + pos < tamanhoTabuleiro) && (y >= 0 && y < tamanhoTabuleiro)) {
                        System.out.println("3");
                        resultado = (x + pos) + ", " + y;
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x + pos, y + pos)) {
                    if ((x + pos >= 0 && x + pos < tamanhoTabuleiro) && (y + pos >= 0 && y + pos < tamanhoTabuleiro)) {
                        System.out.println("4");
                        resultado = (x + pos) + ", " + (y + pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x, y + pos)) {
                    if ((x >= 0 && x < tamanhoTabuleiro) && (y + pos >= 0 && y + pos < tamanhoTabuleiro)) {
                        System.out.println("5");
                        resultado = x + ", " + (y + pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x - pos, y + pos)) {
                    if ((x - pos >= 0 && x - pos < tamanhoTabuleiro) && (y + pos >= 0 && y + pos < tamanhoTabuleiro)) {
                        System.out.println("6");
                        resultado = (x - pos) + ", " + (y + pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x - pos, y)) {
                    if ((x - pos >= 0 && x - pos < tamanhoTabuleiro) && (y >= 0 && y < tamanhoTabuleiro)) {
                        System.out.println("7");
                        resultado = (x - pos) + ", " + y;
                        posibilidades.add(resultado);
                    }
                }
            }
        }
        else if(pecaRecebida.getTipoPeca()== 2){
            if((x - 2 >= 0) && (y - 2 >= 0)){
                if(podeMover(x - 2,y - 2)) {
                    System.out.println("Esquerda-Cima");
                    resultado = (x - 2) + ", " + (y - 2);
                    posibilidades.add(resultado);
                }
            }

            if((x - 2 >= 0) && (y + 2 < tamanhoTabuleiro)){
                if(podeMover(x - 2,y + 2)) {
                    System.out.println("Esquerda-Baixo");
                    resultado = (x - 2) + ", " + (y + 2);
                    posibilidades.add(resultado);
                }
            }

            if((x + 2 < tamanhoTabuleiro) && (y - 2 >= 0)){
                if(podeMover(x + 2,y-2)) {
                    System.out.println("Direita-Cima");
                    resultado = (x + 2) + ", " + (y - 2);
                    posibilidades.add(resultado);
                }
            }

            if((2 < tamanhoTabuleiro) && (y - 2 < tamanhoTabuleiro)){
                if(podeMover(x + 2,y + 2)) {
                    System.out.println("Direita-Baixo");
                    resultado = (x + 2) + ", " + (y + 2);
                    posibilidades.add(resultado);
                }
            }
        }
        System.out.println(posibilidades);
        return posibilidades;
    }


    public String toString(){
        String nomeTipo = "";
        switch (getTipoPeca()){
            case 0:
                nomeTipo = "Rei";
                break;

            case 1:
                nomeTipo = "Rainha";
                break;

            case 2:
                nomeTipo = "Ponei Mágico";
                break;

            case 3:
                nomeTipo = "Padre da Vila";
                break;

            case 4:
                nomeTipo = "TorreH";
                break;

            case 5:
                nomeTipo = "TorreV";
                break;

            case 6:
                nomeTipo = "Lebre";
                break;

            case 7:
                nomeTipo = "Joker";
                break;

            default:
                break;
        }
        if(this.posX==-1 && this.posY==-1){
            return getId() + " | " + nomeTipo + " | " + valorRelativo + " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return getId() + " | " + nomeTipo + " | " + valorRelativo + " | " + getEquipa() + " | " + getAlcunha() + " @ (" + getPosX() + ", " +  getPosY() + ")";
    }
}
