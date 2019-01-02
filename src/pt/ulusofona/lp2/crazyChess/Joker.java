package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class Joker extends CrazyPiece {
    public Joker(int id,int equipa,String alcunha){
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
    public boolean podeMover(int x, int y,List<CrazyPiece>pecasMalucas){
        CrazyPiece piece = Simulador.receberPeca(x,y,pecasMalucas);
        switch (Simulador.turno %6){
            case 0: //Rainha
                piece = new Rainha(id,Simulador.getEquipaJogar(),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 1: //Ponei Magico
                piece = new PoneiMagico(id,Simulador.getEquipaJogar(),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 2: //Padre da Vila
                piece = new PadreDaVila(id,Simulador.getEquipaJogar(),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 3: //TorreH
                piece = new TorreH(id,Simulador.getEquipaJogar(),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 4: //TorreV
                piece = new TorreV(id,Simulador.getEquipaJogar(),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 5: //Lebre
                piece = new Lebre(id,Simulador.getEquipaJogar(),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;
        }
        return piece!=null && piece.podeMover(x,y,pecasMalucas);
    }
}
