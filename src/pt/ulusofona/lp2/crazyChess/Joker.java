package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class Joker extends CrazyPiece {
    public Joker(int id,int equipa,String alcunha, int turno){
        super.id=id;
        super.tipoPeca=7;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo="4";
        super.tipo=setTipo(turno);
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
                break;

            case 1: //Ponei Magico
                piece = new PoneiMagico(id,Simulador.getEquipaJogar(turno),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 2: //Padre da Vila
                piece = new PadreDaVila(id,Simulador.getEquipaJogar(turno),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 3: //TorreH
                piece = new TorreH(id,Simulador.getEquipaJogar(turno),alcunha,tamanhoTabuleiro);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 4: //TorreV
                piece = new TorreV(id,Simulador.getEquipaJogar(turno),alcunha,tamanhoTabuleiro);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;

            case 5: //Lebre
                piece = new Lebre(id,Simulador.getEquipaJogar(turno),alcunha);
                piece.setPosicao(this.getPosX(),this.getPosY());
                break;
        }
        return piece!=null && piece.podeMover(x,y,pecasMalucas,turno,tamanhoTabuleiro);
    }

    public String setTipo(int turno){
        String nomeTipo="";
        switch (turno%6){
            case 0:
                this.tipo="Joker/Rainha";
                break;

            case 1:
                this.tipo="Joker/Ponei Mágico";
                break;

            case 2:
                this.tipo="Joker/Padre da Vila";
                break;

            case 3:
                this.tipo="Joker/TorreH";
                break;

            case 4:
                this.tipo="Joker/TorreV";
                break;

            case 5:
                this.tipo="Joker/Lebre";
                break;
        }
        return nomeTipo;

    }
}
