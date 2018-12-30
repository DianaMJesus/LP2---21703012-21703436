package pt.ulusofona.lp2.crazyChess;

public class Lebre extends CrazyPiece {
    public Lebre(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=6;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo="2";
        super.passoMax=1;
    }

    @Override
    public boolean podeMover(int x,int y){
        CrazyPiece novaPeace = Simulador.receberPeca(x,y);
        if(Math.abs(x - this.getPosX()) <= 1 && Math.abs(y - this.getPosY()) <= 1 && (this.getPosX() != x || this.getPosY() != y)) {
           if(Math.abs(x - this.getPosX()) == Math.abs(y - this.getPosY())) {
               if(novaPeace!=null && novaPeace.getEquipa()==this.getEquipa()){
                   return false;
               }
               return Simulador.turno%2 == 0;
           }
        }
        return false;
    }
}
