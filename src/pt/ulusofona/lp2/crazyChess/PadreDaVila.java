package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class PadreDaVila extends CrazyPiece {

    public PadreDaVila(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=3;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo="3";
        super.passoMax=3;
        super.tipo="Padre da Vila";
        if(equipa == 10){
            imagePNG = "padre_preto.png";
        }else if(equipa == 20){
            imagePNG = "padre_branco.png";
        }
    }

    @Override
    public boolean podeMover(int x, int y, List<CrazyPiece>pecasMalucas, int turno, int tamanhoTabuleiro){
        CrazyPiece novaPeace;
        if(Math.abs(x - this.getPosX()) <= 3 && Math.abs(y - this.getPosY()) <= 3 && (this.getPosX() != x || this.getPosY() != y)){
            if(Math.abs(x - this.getPosX()) == Math.abs(y - this.getPosY())){
                for(int mov = 0;mov < Math.abs(x - this.getPosX());mov++){
                    novaPeace = Simulador.receberPeca(Math.abs((this.getPosX()+(x-this.getPosX()))-mov),Math.abs((this.getPosY()+(y-this.getPosY()))-mov),pecasMalucas);

                    if(novaPeace!=null){
                        return false;
                    }
                }
                List<CrazyPiece> pecasAoRedor = getPecasAoRedor(x,y,pecasMalucas);
                for (CrazyPiece peace : pecasAoRedor) {
                    if (peace != null && peace.getTipoPeca() == 1 && peace.getEquipa() != Simulador.getEquipaJogar(turno)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
