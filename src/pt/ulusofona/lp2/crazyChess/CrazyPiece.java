package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class CrazyPiece {
    int id, equipa, tipoPeca, posX = -1, posY = -1, passoMax;
    String imagePNG;
    String alcunha;
    String valorRelativo;
    String tipo;
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

    public String getTipo(){
        return tipo;
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

    public boolean podeMover(int x,int y,List<CrazyPiece>pecasMalucas) {
        return false;
    }

    public List<String> sugetaoJogada(int x, int y,List<CrazyPiece>pecasMalucas) {
        List<String> posibilidades = new ArrayList<>();
        String resultado;
        CrazyPiece pecaRecebida=Simulador.receberPeca(x,y,pecasMalucas);

        if(pecaRecebida!=null && pecaRecebida.getTipoPeca()!=2) {
            for (int pos = 1; pos <= pecaRecebida.passoMax; pos++) {
                if (podeMover(x - pos, y - pos,pecasMalucas)){
                    if ((x - pos >= 0 && x - pos < Simulador.tamanhoTabuleiro) && (y - pos >= 0 && y - pos < Simulador.tamanhoTabuleiro)) {
                        System.out.println("0");
                        resultado = (x - pos) + ", " + (y - pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x, y - pos,pecasMalucas)){
                    if ((x >= 0 && x < Simulador.tamanhoTabuleiro) && (y - pos >= 0 && y - pos < Simulador.tamanhoTabuleiro)){
                        System.out.println("1");
                        resultado = x + ", " + (y - pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x + pos, y - pos,pecasMalucas)){
                    if ((x + pos >= 0 && x + pos < Simulador.tamanhoTabuleiro) && (y - pos >= 0 && y - pos < Simulador.tamanhoTabuleiro)) {
                        System.out.println("2");
                        resultado = (x + pos) + ", " + (y - pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x + pos, y,pecasMalucas)) {
                    if ((x + pos >= 0 && x + pos < Simulador.tamanhoTabuleiro) && (y >= 0 && y < Simulador.tamanhoTabuleiro)) {
                        System.out.println("3");
                        resultado = (x + pos) + ", " + y;
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x + pos, y + pos,pecasMalucas)) {
                    if ((x + pos >= 0 && x + pos < Simulador.tamanhoTabuleiro) && (y + pos >= 0 && y + pos < Simulador.tamanhoTabuleiro)) {
                        System.out.println("4");
                        resultado = (x + pos) + ", " + (y + pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x, y + pos,pecasMalucas)) {
                    if ((x >= 0 && x < Simulador.tamanhoTabuleiro) && (y + pos >= 0 && y + pos < Simulador.tamanhoTabuleiro)) {
                        System.out.println("5");
                        resultado = x + ", " + (y + pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x - pos, y + pos,pecasMalucas)) {
                    if ((x - pos >= 0 && x - pos < Simulador.tamanhoTabuleiro) && (y + pos >= 0 && y + pos < Simulador.tamanhoTabuleiro)) {
                        System.out.println("6");
                        resultado = (x - pos) + ", " + (y + pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x - pos, y,pecasMalucas)) {
                    if ((x - pos >= 0 && x - pos < Simulador.tamanhoTabuleiro) && (y >= 0 && y < Simulador.tamanhoTabuleiro)) {
                        System.out.println("7");
                        resultado = (x - pos) + ", " + y;
                        posibilidades.add(resultado);
                    }
                }
            }
        }
        else if(pecaRecebida.getTipoPeca()== 2){
            if((x - 2 >= 0) && (y - 2 >= 0)){
                if(podeMover(x - 2,y - 2,pecasMalucas)) {
                    System.out.println("Esquerda-Cima");
                    resultado = (x - 2) + ", " + (y - 2);
                    posibilidades.add(resultado);
                }
            }

            if((x - 2 >= 0) && (y + 2 < Simulador.tamanhoTabuleiro)){
                if(podeMover(x - 2,y + 2,pecasMalucas)) {
                    System.out.println("Esquerda-Baixo");
                    resultado = (x - 2) + ", " + (y + 2);
                    posibilidades.add(resultado);
                }
            }

            if((x + 2 < Simulador.tamanhoTabuleiro) && (y - 2 >= 0)){
                if(podeMover(x + 2,y-2,pecasMalucas)) {
                    System.out.println("Direita-Cima");
                    resultado = (x + 2) + ", " + (y - 2);
                    posibilidades.add(resultado);
                }
            }

            if((2 < Simulador.tamanhoTabuleiro) && (y - 2 < Simulador.tamanhoTabuleiro)){
                if(podeMover(x + 2,y + 2,pecasMalucas)) {
                    System.out.println("Direita-Baixo");
                    resultado = (x + 2) + ", " + (y + 2);
                    posibilidades.add(resultado);
                }
            }
        }
        System.out.println(posibilidades);
        return posibilidades;
    }


    @Override
    public  String toString(){
        if(this.posX==-1 && this.posY==-1){
            return getId() + " | " + getTipo() + " | " + valorRelativo + " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return getId() + " | " + getTipo() + " | " + valorRelativo + " | " + getEquipa() + " | " + getAlcunha() + " @ (" + getPosX() + ", " +  getPosY() + ")";
    }
}
