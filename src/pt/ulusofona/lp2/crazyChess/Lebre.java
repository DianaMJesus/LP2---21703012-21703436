package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class Lebre extends CrazyPiece {
    public Lebre(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=6;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo="2";
        super.passoMax=1;
        if(equipa == 10){
            imagePNG = "lebre_preta.png";
        }else if(equipa == 20){
            imagePNG = "lebre_branca.png";
        }
    }

    @Override
    public boolean podeMover(int x, int y, List<CrazyPiece> pecasMalucas , int tamanhoTabuleiro, int equipaJogar, int turno){
        CrazyPiece novaPeace = Simulador.receberPeca(x,y,pecasMalucas);
        if(Math.abs(x - this.getPosX()) <= 1 && Math.abs(y - this.getPosY()) <= 1 && (this.getPosX() != x || this.getPosY() != y)) {
           if(Math.abs(x - this.getPosX()) == Math.abs(y - this.getPosY())) {
               if(novaPeace!=null && novaPeace.getEquipa()==this.getEquipa()){
                   return false;
               }

               return turno%2 == 0;
           }
        }
        return false;
    }
}
