package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class TorreH extends CrazyPiece {
    public TorreH(int id, int equipa, String alcunha, int tamanhoTabuleiro){
        super.id=id;
        super.tipoPeca=4;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo=3;
        super.passoMax=tamanhoTabuleiro;
        super.tipo="TorreH";
        if(equipa == 10){
            imagePNG = "torreh_preta.png";
        }else if(equipa == 20){
            imagePNG = "torreh_branca.png";
        }
    }

    @Override
    public boolean podeMover(int x, int y, List<CrazyPiece> pecasMalucas, int turno, int tamanhoTabuleiro){
        CrazyPiece novaPeace = Simulador.receberPeca(this.getPosX(),this.getPosY(),pecasMalucas);
        List<CrazyPiece> pecasCaminho = novaPeace.getPecasCaminho(this.getPosX(),this.getPosY(),x,y,pecasMalucas);
        if(y - this.getPosY() == 0 && this.getPosX() != x ){
            if(pecasCaminho.size() != 0){
                return false;
            }
            novaPeace = Simulador.receberPeca(x,y,pecasMalucas);
            if(novaPeace != null && novaPeace.getEquipa() == Simulador.getEquipaJogar(turno)){
                return false;
            }
            return true;
        }
        return false;
    }
}
