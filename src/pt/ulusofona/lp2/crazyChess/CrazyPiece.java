package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CrazyPiece {
    int id, equipa, tipoPeca, posX = -1, posY = -1, passoMax;
    String imagePNG;
    String alcunha;
    int valorRelativo;
    int nrCapturadas = 0;
    int nrPontos = 0;
    int jogadasValidas = 0;
    int jogadasInvalidas = 0;
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

    public int getValorRelativo() {
        return valorRelativo;
    }

    public int getNrCapturadas(){
        return nrCapturadas;
    }

    public int getNrPontos(){
        return nrPontos;
    }

    public int getJogadasValidas() {
        return jogadasValidas;
    }

    public int getJogadasInvalidas() {
        return jogadasInvalidas;
    }

    public void setPosicao(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void setEmJogo(boolean emJogo) {
        this.emJogo = emJogo;
    }

    public void captorou(){
        this.nrCapturadas ++;
    }

    public void setPontos(int valor){
        this.nrPontos += valor;
    }

    public void setJogadasValidas() {
        this.jogadasValidas ++;
    }

    public void setJogadasInvalidas() {
        this.jogadasInvalidas ++;
    }

    public boolean comida() {
        return this.posY == -1 && this.posX == -1;
    }

    public void estaEmJogo() {
        this.emJogo = true;
    }

    public boolean podeMover(int x, int y, List<CrazyPiece> pecasMalucas, int turno, int tamanhoTabuleiro) {
        return false;
    }

    public List<Comparable> sugetaoJogada(int x, int y, List<CrazyPiece> pecasMalucas, int turno, int tamanhoTabuleiro, CrazyPiece pecaPassada) {
        List<Comparable> posibilidades = new ArrayList<>();
        Jogada resultado;
        CrazyPiece peca = Simulador.receberPeca(x, y, pecasMalucas);
        boolean pecaPonei = false;

        //Definir a informação para o caso da peca ser um Joker
        if (pecaPassada != null && pecaPassada.getTipoPeca() == 7) {
            switch (turno % 6) {
                case 0: //Rainha
                    pecaPassada.passoMax = 5;
                    break;

                case 1: //Ponei Magico
                    pecaPonei = true;
                    break;

                case 2: //Padre da Vila
                    pecaPassada.passoMax = 3;
                    break;

                case 3: //TorreH
                    pecaPassada.passoMax = tamanhoTabuleiro;
                    break;

                case 4: //TorreV
                    pecaPassada.passoMax = tamanhoTabuleiro;
                    break;

                case 5: //Lebre
                    pecaPassada.passoMax = 1;
                    break;

                default:
                    break;
            }
        }

        //Caso a peça não seja um Ponei Magico
        if ((pecaPassada != null) && (pecaPassada.getTipoPeca() != 2) && !pecaPonei) {
            for (int pos = 1; pos <= pecaPassada.passoMax; pos++) {
                //Esquerda - Cima
                if (podeMover(x - pos, y - pos, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x - pos >= 0 && x - pos < tamanhoTabuleiro) && (y - pos >= 0 && y - pos < tamanhoTabuleiro)) {
                        peca = Simulador.receberPeca((x - pos) , (y - pos), pecasMalucas);

                        if(peca == null){
                            resultado = new Jogada((x - pos) , (y - pos) ,0);
                        }else{
                            resultado = new Jogada((x - pos) , (y - pos) , peca.getValorRelativo());
                        }

                        posibilidades.add(resultado);
                    }
                }
                //Mantem - Cima
                if (podeMover(x, y - pos, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x >= 0 && x < tamanhoTabuleiro) && (y - pos >= 0 && y - pos < tamanhoTabuleiro)) {
                        peca = Simulador.receberPeca(x , (y - pos), pecasMalucas);

                        if(peca == null){
                            resultado = new Jogada(x , (y - pos) ,0);
                        }else{
                            resultado = new Jogada(x , (y - pos) , peca.getValorRelativo());
                        }

                        posibilidades.add(resultado);
                    }
                }
                //Direita - Cima
                if (podeMover(x + pos, y - pos, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x + pos >= 0 && x + pos < tamanhoTabuleiro) && (y - pos >= 0 && y - pos < tamanhoTabuleiro)) {
                        peca = Simulador.receberPeca((x + pos) , (y - pos), pecasMalucas);

                        if(peca == null){
                            resultado = new Jogada((x + pos) , (y - pos) ,0);
                        }else{
                            resultado = new Jogada((x + pos) , (y - pos) , peca.getValorRelativo());
                        }

                        posibilidades.add(resultado);
                    }
                }
                //Direita - Mantem
                if (podeMover(x + pos, y, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x + pos >= 0 && x + pos < tamanhoTabuleiro) && (y >= 0 && y < tamanhoTabuleiro)) {
                        peca = Simulador.receberPeca((x + pos) , y, pecasMalucas);

                        if(peca == null){
                            resultado = new Jogada((x + pos), y ,0);
                        }else{
                            resultado = new Jogada((x + pos) , y  , peca.getValorRelativo());
                        }

                        posibilidades.add(resultado);
                    }
                }
                //Direita - Baixo
                if (podeMover(x + pos, y + pos, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x + pos >= 0 && x + pos < tamanhoTabuleiro) && (y + pos >= 0 && y + pos < tamanhoTabuleiro)) {
                        peca = Simulador.receberPeca((x + pos) , (y + pos), pecasMalucas);

                        if(peca == null){
                            resultado = new Jogada((x + pos), (y + pos) ,0);
                        }else{
                            resultado = new Jogada((x + pos) , (y + pos)  , peca.getValorRelativo());
                        }

                        posibilidades.add(resultado);
                    }
                }
                //Mantem - Baixo
                if (podeMover(x, y + pos, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x >= 0 && x < tamanhoTabuleiro) && (y + pos >= 0 && y + pos < tamanhoTabuleiro)) {
                        peca = Simulador.receberPeca(x , (y + pos), pecasMalucas);

                        if(peca == null){
                            resultado = new Jogada(x , (y + pos) ,0);
                        }else{
                            resultado = new Jogada(x , (y + pos)  , peca.getValorRelativo());
                        }

                        posibilidades.add(resultado);
                    }
                }
                //Esquerda - Baixo
                if (podeMover(x - pos, y + pos, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x - pos >= 0 && x - pos < tamanhoTabuleiro) && (y + pos >= 0 && y + pos < tamanhoTabuleiro)) {
                        peca = Simulador.receberPeca((x - pos) , (y + pos), pecasMalucas);

                        if(peca == null){
                            resultado = new Jogada((x - pos) , (y + pos) ,0);
                        }else{
                            resultado = new Jogada((x - pos) , (y + pos)  , peca.getValorRelativo());
                        }

                        posibilidades.add(resultado);
                    }
                }
                //Esquerda - Mantem
                if (podeMover(x - pos, y, pecasMalucas, turno, tamanhoTabuleiro)) {
                    if ((x - pos >= 0 && x - pos < tamanhoTabuleiro) && (y >= 0 && y < tamanhoTabuleiro)) {
                        peca = Simulador.receberPeca((x - pos) , y , pecasMalucas);

                        if(peca == null){
                            resultado = new Jogada((x - pos) , y ,0);
                        }else{
                            resultado = new Jogada((x - pos) , y  , peca.getValorRelativo());
                        }

                        posibilidades.add(resultado);
                    }
                }
            }
        }
        //Caso a peça seja um Ponei
        else if (peca.getTipoPeca() == 2 || pecaPonei) {
            if ((x - 2 >= 0) && (y - 2 >= 0)) {
                if (podeMover(x - 2, y - 2, pecasMalucas, turno, tamanhoTabuleiro)) {
                    peca = Simulador.receberPeca((x - 2) , (y - 2), pecasMalucas);

                    if(peca == null){
                        resultado = new Jogada((x - 2) , (y - 2) ,0);
                    }else{
                        resultado = new Jogada((x - 2) , (y - 2)  , peca.getValorRelativo());
                    }

                    posibilidades.add(resultado);
                }
            }

            if ((x - 2 >= 0) && (y + 2 < tamanhoTabuleiro)) {
                if (podeMover(x - 2, y + 2, pecasMalucas, turno, tamanhoTabuleiro)) {
                    peca = Simulador.receberPeca((x - 2) , (y + 2), pecasMalucas);

                    if(peca == null){
                        resultado = new Jogada((x - 2) , (y + 2) ,0);
                    }else{
                        resultado = new Jogada((x - 2) , (y + 2)  , peca.getValorRelativo());
                    }

                    posibilidades.add(resultado);
                }
            }

            if ((x + 2 < tamanhoTabuleiro) && (y - 2 >= 0)) {
                if (podeMover(x + 2, y - 2, pecasMalucas, turno, tamanhoTabuleiro)) {
                    peca = Simulador.receberPeca((x + 2) , (y - 2), pecasMalucas);

                    if(peca == null){
                        resultado = new Jogada((x + 2) , (y - 2) ,0);
                    }else{
                        resultado = new Jogada((x + 2) , (y - 2)  , peca.getValorRelativo());
                    }

                    posibilidades.add(resultado);
                }
            }

            if ((2 < tamanhoTabuleiro) && (y - 2 < tamanhoTabuleiro)) {
                if (podeMover(x + 2, y + 2, pecasMalucas, turno, tamanhoTabuleiro)) {
                    peca = Simulador.receberPeca((x + 2) , (y + 2), pecasMalucas);

                    if(peca == null){
                        resultado = new Jogada((x + 2) , (y + 2) ,0);
                    }else{
                        resultado = new Jogada((x + 2) , (y + 2)  , peca.getValorRelativo());
                    }
                    posibilidades.add(resultado);
                }
            }
        }

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

    public double calcularRacio(){
        if((this.jogadasValidas + this.jogadasInvalidas) > 0) {
            return (double)this.jogadasInvalidas / ((double)this.jogadasValidas + (double) this.jogadasInvalidas);
        }
        return 0;
    }

    public  String toString(){
        if(!emJogo){
            return id + " | " + tipo + " | " + valorRelativo + " | " + equipa + " | " + alcunha + " @ (n/a)";
        }
        return id + " | " + tipo + " | " + valorRelativo + " | " + equipa + " | " + alcunha + " @ (" + posX + ", " +  posY + ")";
    }

}
