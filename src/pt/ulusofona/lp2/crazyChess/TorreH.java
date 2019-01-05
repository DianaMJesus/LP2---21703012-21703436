package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class TorreH extends CrazyPiece {
    public TorreH(int id, int equipa, String alcunha, int tamanhoTabuleiro){
        super.id=id;
        super.tipoPeca=4;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo="3";
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
        CrazyPiece novaPeace = Simulador.receberPeca(x,y,pecasMalucas);

        if(y - this.getPosY() == 0 && this.getPosX() != x ){
            if(novaPeace != null && novaPeace.validaMovimentoHorizontal(x, y, pecasMalucas)){
                return false;
            }
            return true;
        }
        return false;
    }
}
