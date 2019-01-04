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
        super.valorRelativo="(infinito)";
        super.passoMax=1;
        super.tipo="Rei";
        if(equipa==10){
            super.imagePNG="rei_preto.png";
        }else if(equipa==20){
            super.imagePNG="rei_branco.png";
        }
    }

    @Override
    public boolean podeMover(int x, int y, List<CrazyPiece> pecasMalucas, int turno, int tamanhoTabuleiro){
        if((x >= 0 && x < tamanhoTabuleiro) && (y >= 0 && y < tamanhoTabuleiro)) {
            if (Math.abs(x - this.getPosX()) <= 1 && Math.abs(y - this.getPosY()) <= 1 && (this.getPosX() != x || this.getPosY() != y)) {
                CrazyPiece novaPeace=Simulador.receberPeca(x,y,pecasMalucas);
                return novaPeace == null || novaPeace.getEquipa() != Simulador.getEquipaJogar(turno);
            }
        }
        return false;
    }
}
