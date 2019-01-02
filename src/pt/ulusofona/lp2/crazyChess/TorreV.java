package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class TorreV extends CrazyPiece {
    public TorreV(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=5;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo="3";
        super.passoMax=Simulador.tamanhoTabuleiro;
        super.tipo="TorreV";
        if(equipa == 10){
            imagePNG = "torrev_preta.png";
        }else if(equipa == 20){
            imagePNG = "torrev_branca.png";
        }
    }

    @Override
    public boolean podeMover(int x, int y, List<CrazyPiece> pecasMalucas){
        CrazyPiece novaPeace = Simulador.receberPeca(x,y,pecasMalucas);

        if(x-this.getPosX()==0 && this.getPosY() != y ){
            for(int mov=1;mov<Math.abs(y-this.getPosY());mov++){
                if((y-this.getPosY())<0){
                    novaPeace=Simulador.receberPeca(x,this.getPosY()-mov,pecasMalucas);
                }
                else if((y-this.getPosY())>0){
                    novaPeace=Simulador.receberPeca(x,this.getPosY()+mov,pecasMalucas);
                }

                if(novaPeace!=null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
