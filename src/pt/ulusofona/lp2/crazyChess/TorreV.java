package pt.ulusofona.lp2.crazyChess;

public class TorreV extends CrazyPiece {
    public TorreV(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=5;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo=3;
        super.passoMax=Simulador.tamanhoTabuleiro;
    }

    @Override
    public boolean podeMover(int x,int y){
        CrazyPiece novaPeace;

        if(Math.abs(x-this.getPosX())==0 && (this.getPosX() != x || this.getPosY() != y)){
            for(int mov=1;mov<=Math.abs(x-this.getPosX());mov++){
                novaPeace=Simulador.receberPeca(x+mov,this.getPosY());
                if(novaPeace!=null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
