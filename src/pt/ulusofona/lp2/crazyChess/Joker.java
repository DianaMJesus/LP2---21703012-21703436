package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class Joker extends CrazyPiece {
    public Joker(int id,int equipa,String alcunha, int turno,int tamanhoTabuleiro){
        super.id=id;
        super.tipoPeca=7;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo="4";
        if(equipa == 10){
            imagePNG = "joker_preto.png";
        }else if(equipa == 20){
            imagePNG = "joker_branco.png";
        }
    }

    @Override
    public boolean podeMover(int x, int y, List<CrazyPiece> pecasMalucas , int tamanhoTabuleiro, int equipaJogar, int turno){
        CrazyPiece piece = Simulador.receberPeca(x,y,pecasMalucas);
        switch (turno %6){
            case 0: //Rainha
                piece = new Rainha(id,equipaJogar,alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 1: //Ponei Magico
                piece = new PoneiMagico(id,equipaJogar,alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 2: //Padre da Vila
                piece = new PadreDaVila(id,equipaJogar,alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 3: //TorreH
                piece = new TorreH(id,equipaJogar,alcunha,tamanhoTabuleiro);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 4: //TorreV
                piece = new TorreV(id,equipaJogar,alcunha,tamanhoTabuleiro);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 5: //Lebre
                piece = new Lebre(id,equipaJogar,alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;
        }
        return piece!=null && piece.podeMover(x,y,pecasMalucas,tamanhoTabuleiro,equipaJogar,turno);
    }
}
