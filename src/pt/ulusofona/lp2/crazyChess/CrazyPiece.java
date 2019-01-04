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

    public boolean podeMover(int x,int y,List<CrazyPiece>pecasMalucas,int turno, int tamanhoTabuleiro) {
        return false;
    }

    public List<String> sugetaoJogada(int x, int y, List<CrazyPiece>pecasMalucas, int turno, int tamanhoTabuleiro) {
        List<String> posibilidades = new ArrayList<>();
        String resultado;
        CrazyPiece pecaRecebida=Simulador.receberPeca(x,y,pecasMalucas);

        if(pecaRecebida!=null && pecaRecebida.getTipoPeca()!=2) {
            for (int pos = 1; pos <= pecaRecebida.passoMax; pos++) {
                if (podeMover(x - pos, y - pos,pecasMalucas,turno,tamanhoTabuleiro)){
                    if ((x - pos >= 0 && x - pos < tamanhoTabuleiro) && (y - pos >= 0 && y - pos < tamanhoTabuleiro)) {
                        System.out.println("0");
                        resultado = (x - pos) + ", " + (y - pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x, y - pos,pecasMalucas,turno,tamanhoTabuleiro)){
                    if ((x >= 0 && x < tamanhoTabuleiro) && (y - pos >= 0 && y - pos < tamanhoTabuleiro)){
                        System.out.println("1");
                        resultado = x + ", " + (y - pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x + pos, y - pos,pecasMalucas,turno,tamanhoTabuleiro)){
                    if ((x + pos >= 0 && x + pos < tamanhoTabuleiro) && (y - pos >= 0 && y - pos < tamanhoTabuleiro)) {
                        System.out.println("2");
                        resultado = (x + pos) + ", " + (y - pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x + pos, y,pecasMalucas,turno,tamanhoTabuleiro)) {
                    if ((x + pos >= 0 && x + pos < tamanhoTabuleiro) && (y >= 0 && y < tamanhoTabuleiro)) {
                        System.out.println("3");
                        resultado = (x + pos) + ", " + y;
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x + pos, y + pos,pecasMalucas,turno,tamanhoTabuleiro)) {
                    if ((x + pos >= 0 && x + pos < tamanhoTabuleiro) && (y + pos >= 0 && y + pos < tamanhoTabuleiro)) {
                        System.out.println("4");
                        resultado = (x + pos) + ", " + (y + pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x, y + pos,pecasMalucas,turno,tamanhoTabuleiro)) {
                    if ((x >= 0 && x < tamanhoTabuleiro) && (y + pos >= 0 && y + pos < tamanhoTabuleiro)) {
                        System.out.println("5");
                        resultado = x + ", " + (y + pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x - pos, y + pos,pecasMalucas,turno,tamanhoTabuleiro)) {
                    if ((x - pos >= 0 && x - pos < tamanhoTabuleiro) && (y + pos >= 0 && y + pos < tamanhoTabuleiro)) {
                        System.out.println("6");
                        resultado = (x - pos) + ", " + (y + pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x - pos, y,pecasMalucas,turno,tamanhoTabuleiro)) {
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
                if(podeMover(x - 2,y - 2,pecasMalucas,turno,tamanhoTabuleiro)) {
                    System.out.println("Esquerda-Cima");
                    resultado = (x - 2) + ", " + (y - 2);
                    posibilidades.add(resultado);
                }
            }

            if((x - 2 >= 0) && (y + 2 < tamanhoTabuleiro)){
                if(podeMover(x - 2,y + 2,pecasMalucas,turno,tamanhoTabuleiro)) {
                    System.out.println("Esquerda-Baixo");
                    resultado = (x - 2) + ", " + (y + 2);
                    posibilidades.add(resultado);
                }
            }

            if((x + 2 < tamanhoTabuleiro) && (y - 2 >= 0)){
                if(podeMover(x + 2,y-2,pecasMalucas,turno,tamanhoTabuleiro)) {
                    System.out.println("Direita-Cima");
                    resultado = (x + 2) + ", " + (y - 2);
                    posibilidades.add(resultado);
                }
            }

            if((2 < tamanhoTabuleiro) && (y - 2 < tamanhoTabuleiro)){
                if(podeMover(x + 2,y + 2,pecasMalucas,turno,tamanhoTabuleiro)) {
                    System.out.println("Direita-Baixo");
                    resultado = (x + 2) + ", " + (y + 2);
                    posibilidades.add(resultado);
                }
            }
        }
        System.out.println(posibilidades);
        return posibilidades;
    }

    public List<CrazyPiece> getPecasAoRedor(int x, int y, List<CrazyPiece> pecasMalucas){
        List<CrazyPiece> pecasAoRedor = new ArrayList<>();
        CrazyPiece novaPeace;

        novaPeace = Simulador.receberPeca(x - 1, y - 1,pecasMalucas);
        if(novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x, y - 1,pecasMalucas);
        if(novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x + 1, y - 1,pecasMalucas);
        if(novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x + 1, y,pecasMalucas);
        if(novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x + 1, y + 1,pecasMalucas);
        if(novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x, y + 1,pecasMalucas);
        if(novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x - 1, y + 1,pecasMalucas);
        if(novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x - 1, y,pecasMalucas);
        if(novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        return pecasAoRedor;
    }

    public boolean validaMovimentoHorizontal(int x, int y, List<CrazyPiece> pecasMalucas){
        CrazyPiece novaPeace = Simulador.receberPeca(x,y,pecasMalucas);

        for(int mov = 1;mov < Math.abs(x - this.getPosX());mov++){ //Horizontal

            if((x - this.getPosX()) < 0){ //Esquerda
                novaPeace = Simulador.receberPeca(this.getPosX() - mov,y,pecasMalucas);
            }
            else if((x - this.getPosX()) > 0){ //Direita
                novaPeace = Simulador.receberPeca(this.getPosX() + mov,y,pecasMalucas);
            }

            if(novaPeace != null){
                return false;
            }
        }
        return true;
    }

    public boolean validaMovimentoVertical(int x, int y, List<CrazyPiece> pecasMalucas){
        CrazyPiece novaPeace = Simulador.receberPeca(x,y,pecasMalucas);
        for(int mov = 1;mov < Math.abs(y-this.getPosY());mov++){ //Vertical

            if((y - this.getPosY()) < 0){ //Cima
                novaPeace = Simulador.receberPeca(x,this.getPosY() - mov,pecasMalucas);
            }
            else if((y - this.getPosY()) > 0){ //Baixo
                novaPeace = Simulador.receberPeca(x,this.getPosY() + mov,pecasMalucas);
            }

            if(novaPeace != null){
                return false;
            }
        }
        return true;
    }


    @Override
    public  String toString(){
        if(this.posX==-1 && this.posY==-1){
            return getId() + " | " + getTipo() + " | " + valorRelativo + " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return getId() + " | " + getTipo() + " | " + valorRelativo + " | " + getEquipa() + " | " + getAlcunha() + " @ (" + getPosX() + ", " +  getPosY() + ")";
    }
}
