package pt.ulusofona.lp2.crazyChess;


import java.util.List;

public class Rainha extends CrazyPiece {
    public Rainha(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=1;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo="8";
        super.passoMax=5;
        super.tipo="Rainha";
        if(equipa == 10){
            imagePNG = "rainha_preta.png";
        }else if(equipa == 20){
            imagePNG = "rainha_branca.png";
        }
    }

    @Override
    public boolean podeMover(int x, int y, List<CrazyPiece> pecasMalucas, int turno, int tamanhoTabuleiro){
        CrazyPiece novaPeace = Simulador.receberPeca(this.getPosX(),this.getPosY(),pecasMalucas);

//      check if the movement is between the rang that the queen can move it self and if the destiny is different from the actual position
        if(Math.abs(x - this.getPosX()) <= 5 && Math.abs(y - this.getPosY()) <= 5 && (this.getPosX() != x || this.getPosY() != y)){
//          if the movement is made in the diagonal
            if(Math.abs(x - this.getPosX()) == Math.abs(y - this.getPosY())){

                for(int mov = 1;mov < Math.abs(x-this.getPosX());mov++){ //Diagonal

                    if((x - this.getPosX()) < 0 && (y - this.getPosY()) < 0){ //Esquerda-Cima
                        novaPeace = Simulador.receberPeca(this.getPosX() - mov,this.getPosY() - mov,pecasMalucas);
                    }
                    else if((x - this.getPosX()) < 0 && (y - this.getPosY()) > 0){ //Esquerda-Baixo
                        novaPeace = Simulador.receberPeca(this.getPosX() - mov,this.getPosY() + mov,pecasMalucas);
                    }
                    else if((x - this.getPosX()) > 0 && (y - this.getPosY()) < 0){ //Direita-Cima
                        novaPeace = Simulador.receberPeca(this.getPosX() + mov,this.getPosY() - mov,pecasMalucas);
                    }
                    else if((x - this.getPosX()) > 0 && (y - this.getPosY()) > 0){ //Direita-Baixo
                        novaPeace=Simulador.receberPeca(this.getPosX() + mov,this.getPosY() + mov,pecasMalucas);
                    }

                    if(novaPeace != null){
                        return false;
                    }
                }
            }else{
                if(Math.abs(x - this.getPosX()) == 0 && Math.abs(y - this.getPosY()) != 0){ //Vertical
                    if(!novaPeace.validaMovimentoVertical(x,y,pecasMalucas)){
                        return false;
                    }

                }else if(Math.abs(x - this.getPosX()) != 0 && Math.abs(y - this.getPosY()) == 0){ //Horizontal
                    if(!novaPeace.validaMovimentoHorizontal(x, y, pecasMalucas)){
                        return false;
                    }
                }
            }
            List<CrazyPiece> pecasAoRedor = getPecasAoRedor(x,y,pecasMalucas);
            for (CrazyPiece peace : pecasAoRedor) {
                if (peace != null && peace.getTipoPeca() == 3 && peace.getEquipa() != Simulador.getEquipaJogar(turno)) {
                    return false;
                }
            }

            if(novaPeace != null && novaPeace.getTipoPeca() == 1 && novaPeace.getEquipa() != Simulador.getEquipaJogar(turno)){
                return false;
            }
            return true;
        }
        return false;
    }
}
