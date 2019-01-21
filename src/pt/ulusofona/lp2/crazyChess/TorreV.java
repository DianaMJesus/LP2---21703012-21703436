package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class TorreV extends CrazyPiece {
    public TorreV(int id,int equipa,String alcunha, int tamanhoTabuleiro){
        super.id=id;
        super.tipoPeca=5;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo=3;
        super.passoMax=tamanhoTabuleiro;
        super.tipo="TorreV";
        if(equipa == 10){
            imagePNG = "torrev_preta.png";
        }else if(equipa == 20){
            imagePNG = "torrev_branca.png";
        }
    }

    @Override
    public boolean podeMover(int x, int y, List<CrazyPiece> pecasMalucas, int turno, int tamanhoTabuleiro){
        CrazyPiece novaPeace = Simulador.receberPeca(this.getPosX(),this.getPosY(),pecasMalucas);
        List<CrazyPiece> pecasCaminho = novaPeace.getPecasCaminho(this.getPosX(),this.getPosY(),x,y,pecasMalucas);
        if(x - this.getPosX() == 0 && this.getPosY() != y ){
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
