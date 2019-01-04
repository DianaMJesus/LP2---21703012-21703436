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
            for(int mov = 1;mov < Math.abs(x - this.getPosX());mov++){
                if((x - this.getPosX()) < 0){
                    novaPeace = Simulador.receberPeca(this.getPosX() - mov,y,pecasMalucas);
                }
                else if((x - this.getPosX()) > 0){
                    novaPeace = Simulador.receberPeca(this.getPosX() + mov,y,pecasMalucas);
                }

                if(novaPeace != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
