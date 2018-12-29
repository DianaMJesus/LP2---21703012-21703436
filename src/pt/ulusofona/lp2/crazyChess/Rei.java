package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Rei extends CrazyPiece{
    public Rei(){}

    public Rei(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=0;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo=Integer.MAX_VALUE;
        super.passoMax=1;
    }

    @Override
    public boolean podeMover(int x,int y){
        if((x>=0 && x<Simulador.tamanhoTabuleiro) && (y>=0 && y<Simulador.tamanhoTabuleiro)) {
            if (Math.abs(x - this.getPosX()) <= 1 && Math.abs(y - this.getPosY()) <= 1 && (this.getPosX() != x || this.getPosY() != y)) {
                CrazyPiece novaPeace=Simulador.receberPeca(x,y);
                if(novaPeace==null || novaPeace.getEquipa()!=Simulador.equipaJogar){
                    return true;
                }
            }
        }
        return false;
    }
}
