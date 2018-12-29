package pt.ulusofona.lp2.crazyChess;

public class TorreH extends CrazyPiece {
    public TorreH(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=4;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo=3;
        super.passoMax=Simulador.tamanhoTabuleiro;
    }

    @Override
    public boolean podeMover(int x,int y){
        CrazyPiece novaPeace;

        if(Math.abs(y-this.getPosY())==0 && (this.getPosX() != x || this.getPosY() != y)){
            for(int mov=1;mov<=Math.abs(y-this.getPosY());mov++){
                novaPeace=Simulador.receberPeca(x,this.getPosY()+mov);
                if(novaPeace!=null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
