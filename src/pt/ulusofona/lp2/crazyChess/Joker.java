package pt.ulusofona.lp2.crazyChess;

public class Joker extends CrazyPiece {
    public Joker(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=7;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo=4;
        switch (Simulador.turno %6){
            case 0:
                super.passoMax = 5;
                break;

            case 1:
                super.passoMax = 2;
                break;

            case 2:
                super.passoMax = 3;
                break;

            case 3:
                super.passoMax = Simulador.tamanhoTabuleiro;
                break;

            case 4:
                super.passoMax = Simulador.tamanhoTabuleiro;
                break;

            case 5:
                super.passoMax =1;
                break;
        }
    }

    @Override
    public boolean podeMover(int x,int y){
        CrazyPiece piece = null;
        switch (Simulador.turno %6){
            case 0:
                piece = new Rainha(id,Simulador.equipaJogar,alcunha);
                break;

            case 1:
                piece = new PoneiMagico(id,Simulador.equipaJogar,alcunha);
                break;

            case 2:
                piece = new PadreDaVila(id,Simulador.equipaJogar,alcunha);
                break;

            case 3:
                piece = new TorreH(id,Simulador.equipaJogar,alcunha);
                break;

            case 4:
                piece = new TorreV(id,Simulador.equipaJogar,alcunha);
                break;

            case 5:
                piece = new Lebre(id,Simulador.equipaJogar,alcunha);
                break;
        }

        if (piece != null) {
            System.out.println(piece.getTipoPeca());
            return piece.podeMover(x,y);
        } else {
            System.out.println("null");
            return false;
        }
    }
}
