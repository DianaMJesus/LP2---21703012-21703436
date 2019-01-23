package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class Joker extends CrazyPiece {
    public Joker(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=7;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo=4;
        super.tipo="Joker/Rainha";
        if(equipa == 10){
            imagePNG = "joker_preto.png";
        }else if(equipa == 20){
            imagePNG = "joker_branco.png";
        }
    }

    @Override
    public boolean podeMover(int x, int y, List<CrazyPiece>pecasMalucas, int turno, int tamanhoTabuleiro){
        CrazyPiece piece = Simulador.receberPeca(x,y,pecasMalucas);
        switch (turno %6){
            case 0: //Rainha
                piece = new Rainha(id,Simulador.getEquipaJogar(turno),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                setTipo(0);
                break;

            case 1: //Ponei Magico
                piece = new PoneiMagico(id,Simulador.getEquipaJogar(turno),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                setTipo(1);
                break;

            case 2: //Padre da Vila
                piece = new PadreDaVila(id,Simulador.getEquipaJogar(turno),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                setTipo(2);
                break;

            case 3: //TorreH
                piece = new TorreH(id,Simulador.getEquipaJogar(turno),alcunha,tamanhoTabuleiro);
                piece.setPosicao(this.getPosX(),this.getPosY());
                setTipo(3);
                break;

            case 4: //TorreV
                piece = new TorreV(id,Simulador.getEquipaJogar(turno),alcunha,tamanhoTabuleiro);
                piece.setPosicao(this.getPosX(),this.getPosY());
                setTipo(4);
                break;

            case 5: //Lebre
                piece = new Lebre(id,Simulador.getEquipaJogar(turno),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                setTipo(5);
                break;
        }
        return piece!=null && piece.podeMover(x,y,pecasMalucas,turno,tamanhoTabuleiro);
    }

    public void setTipo(int turno){
        switch (turno%6){
            case 0:
                tipo="Joker/Rainha";
                break;

            case 1:
                tipo="Joker/Ponei Mágico";
                break;

            case 2:
                tipo="Joker/Padre da Vila";
                break;

            case 3:
                tipo="Joker/TorreH";
                break;

            case 4:
                tipo="Joker/TorreV";
                break;

            case 5:
                tipo="Joker/Lebre";
                break;
        }
    }



}
