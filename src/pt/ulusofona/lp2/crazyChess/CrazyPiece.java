package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class CrazyPiece {
    int id, equipa, tipoPeca, posX = -10, posY = -10, passoMax;
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

    public String getTipo() {
        return tipo;
    }

    public String getValorRelativo() {
        return valorRelativo;
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

    public boolean podeMover(int x, int y, List<CrazyPiece> pecasMalucas, int turno, int tamanhoTabuleiro) {
        return false;
    }

    public List<String> sugetaoJogada(int x, int y, List<CrazyPiece> pecasMalucas, int turno, int tamanhoTabuleiro) {
        List<String> posibilidades = new ArrayList<>();
        String resultado;
        CrazyPiece pecaRecebida = Simulador.receberPeca(x, y, pecasMalucas);
        boolean torreEspecial = false;

        if (pecaRecebida.getTipoPeca() == 7) {
            switch (turno % 6) {
                case 0:
                    pecaRecebida.passoMax = 5;
                    break;

                case 1:
                    torreEspecial = true;
                    break;

                case 2:
                    pecaRecebida.passoMax = 3;
                    break;

                case 3:
                    pecaRecebida.passoMax = tamanhoTabuleiro;
                    break;

                case 4:
                    pecaRecebida.passoMax = tamanhoTabuleiro;
                    break;

                case 5:
                    pecaRecebida.passoMax = 1;
                    break;

                default:
                    break;
            }
        }

        if (pecaRecebida != null && pecaRecebida.getTipoPeca() != 2 && torreEspecial == false) {
            for (int pos = 1; pos <= pecaRecebida.passoMax; pos++) {
                if (podeMover(x - pos, y - pos, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x - pos >= 0 && x - pos < tamanhoTabuleiro) && (y - pos >= 0 && y - pos < tamanhoTabuleiro)) {
                        System.out.println("0");
                        resultado = (x - pos) + ", " + (y - pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x, y - pos, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x >= 0 && x < tamanhoTabuleiro) && (y - pos >= 0 && y - pos < tamanhoTabuleiro)) {
                        System.out.println("1");
                        resultado = x + ", " + (y - pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x + pos, y - pos, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x + pos >= 0 && x + pos < tamanhoTabuleiro) && (y - pos >= 0 && y - pos < tamanhoTabuleiro)) {
                        System.out.println("2");
                        resultado = (x + pos) + ", " + (y - pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x + pos, y, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x + pos >= 0 && x + pos < tamanhoTabuleiro) && (y >= 0 && y < tamanhoTabuleiro)) {
                        System.out.println("3");
                        resultado = (x + pos) + ", " + y;
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x + pos, y + pos, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x + pos >= 0 && x + pos < tamanhoTabuleiro) && (y + pos >= 0 && y + pos < tamanhoTabuleiro)) {
                        System.out.println("4");
                        resultado = (x + pos) + ", " + (y + pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x, y + pos, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x >= 0 && x < tamanhoTabuleiro) && (y + pos >= 0 && y + pos < tamanhoTabuleiro)) {
                        System.out.println("5");
                        resultado = x + ", " + (y + pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x - pos, y + pos, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x - pos >= 0 && x - pos < tamanhoTabuleiro) && (y + pos >= 0 && y + pos < tamanhoTabuleiro)) {
                        System.out.println("6");
                        resultado = (x - pos) + ", " + (y + pos);
                        posibilidades.add(resultado);
                    }
                }

                if (podeMover(x - pos, y, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x - pos >= 0 && x - pos < tamanhoTabuleiro) && (y >= 0 && y < tamanhoTabuleiro)) {
                        System.out.println("7");
                        resultado = (x - pos) + ", " + y;
                        posibilidades.add(resultado);
                    }
                }
            }
        } else if (pecaRecebida.getTipoPeca() == 2 || torreEspecial == true) {
            if ((x - 2 >= 0) && (y - 2 >= 0)) {
                if (podeMover(x - 2, y - 2, pecasMalucas, turno, tamanhoTabuleiro)) {
                    System.out.println("Esquerda-Cima");
                    resultado = (x - 2) + ", " + (y - 2);
                    posibilidades.add(resultado);
                }
            }

            if ((x - 2 >= 0) && (y + 2 < tamanhoTabuleiro)) {
                if (podeMover(x - 2, y + 2, pecasMalucas, turno, tamanhoTabuleiro)) {
                    System.out.println("Esquerda-Baixo");
                    resultado = (x - 2) + ", " + (y + 2);
                    posibilidades.add(resultado);
                }
            }

            if ((x + 2 < tamanhoTabuleiro) && (y - 2 >= 0)) {
                if (podeMover(x + 2, y - 2, pecasMalucas, turno, tamanhoTabuleiro)) {
                    System.out.println("Direita-Cima");
                    resultado = (x + 2) + ", " + (y - 2);
                    posibilidades.add(resultado);
                }
            }

            if ((2 < tamanhoTabuleiro) && (y - 2 < tamanhoTabuleiro)) {
                if (podeMover(x + 2, y + 2, pecasMalucas, turno, tamanhoTabuleiro)) {
                    System.out.println("Direita-Baixo");
                    resultado = (x + 2) + ", " + (y + 2);
                    posibilidades.add(resultado);
                }
            }
        }
        System.out.println(posibilidades);
        return posibilidades;
    }

    public List<CrazyPiece> getPecasAoRedor(int x, int y, List<CrazyPiece> pecasMalucas) {
        List<CrazyPiece> pecasAoRedor = new ArrayList<>();
        CrazyPiece novaPeace;

        novaPeace = Simulador.receberPeca(x - 1, y - 1, pecasMalucas);
        if (novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x, y - 1, pecasMalucas);
        if (novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x + 1, y - 1, pecasMalucas);
        if (novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x + 1, y, pecasMalucas);
        if (novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x + 1, y + 1, pecasMalucas);
        if (novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x, y + 1, pecasMalucas);
        if (novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x - 1, y + 1, pecasMalucas);
        if (novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        novaPeace = Simulador.receberPeca(x - 1, y, pecasMalucas);
        if (novaPeace != null) {
            pecasAoRedor.add(novaPeace);
        }

        return pecasAoRedor;
    }

    public List<CrazyPiece> getPecasCaminho(int xO, int yO, int xD, int yD, List<CrazyPiece> pecasMalucas) {
        List<CrazyPiece> pecasCaminho = new ArrayList<>();
        CrazyPiece peace = Simulador.receberPeca(xO, yO, pecasMalucas);

        if (Math.abs(xD - xO) > 0 && yO == yD) { //Horizontal
            for (int mov = 1; mov < Math.abs(xD - xO); mov++) {
                if (xD - xO > 0) { //Direita
                    peace = Simulador.receberPeca(xO + mov, yO, pecasMalucas);
                } else if (xD - xO < 0) { //Esquerda
                    peace = Simulador.receberPeca(xO - mov, yO, pecasMalucas);
                }

                if (peace != null) {
                    pecasCaminho.add(peace);
                }
            }

        } else if (Math.abs(yD - yO) > 0 && xO == xD) { //Vertical
            for (int mov = 1; mov < Math.abs(yD - yO); mov++) {
                if (yD - yO < 0) { //Cima
                    peace = Simulador.receberPeca(xO, yO - mov, pecasMalucas);
                } else if (yD - yO > 0) { //Baixo
                    peace = Simulador.receberPeca(xO, yO + mov, pecasMalucas);
                }

                if (peace != null) {
                    pecasCaminho.add(peace);
                }
            }
        } else if (Math.abs(yD - yO) == Math.abs(xD - xO)) {//Diagonal
            for (int mov = 1; mov < Math.abs(xD - xO); mov++) {
                if ((xD - xO) < 0 && (yD - yO) < 0) { //Esquerda-Cima
                    peace = Simulador.receberPeca(xO - mov, yO - mov, pecasMalucas);
                } else if ((xD - xO) < 0 && (yD - yO) > 0) { //Esquerda-Baixo
                    peace = Simulador.receberPeca(xO - mov, yO + mov, pecasMalucas);
                } else if ((xD - xO) > 0 && (yD - yO) < 0) { //Direita-Cima
                    peace = Simulador.receberPeca(xO + mov, yO - mov, pecasMalucas);
                } else if ((xD - xO) > 0 && (yD - yO) > 0) { //Direita-Baixo
                    peace = Simulador.receberPeca(xO + mov, yO + mov, pecasMalucas);
                }

                if (peace != null) {
                    pecasCaminho.add(peace);
                }
            }
        }
        return pecasCaminho;
    }

    public void setTipo(int turno){}

    public  String toString(){
        if(this.posX==-1 && this.posY==-1){
            return id + " | " + tipo + " | " + valorRelativo + " | " + equipa + " | " + alcunha + " @ (n/a)";
        }
        return id + " | " + tipo + " | " + valorRelativo + " | " + equipa + " | " + alcunha + " @ (" + posX + ", " +  posY + ")";
    }
}
