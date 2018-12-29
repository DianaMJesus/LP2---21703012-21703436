package pt.ulusofona.lp2.crazyChess;

public class PadreDaVila extends CrazyPiece {

    public PadreDaVila(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=3;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo=3;
        super.passoMax=3;
    }

    @Override
    public boolean podeMover(int x,int y){
        CrazyPiece novaPeace=Simulador.receberPeca(x,y);
        if(Math.abs(x-this.getPosX())<=3 && Math.abs(y-this.getPosY())<=3 && (this.getPosX() != x || this.getPosY() != y)){

            if(Math.abs(x-this.getPosX())==Math.abs(y-this.getPosY())){

                for(int mov=0;mov<Math.abs(x-this.getPosX());mov++){
                    novaPeace=Simulador.receberPeca(Math.abs((this.getPosX()+(x-this.getPosX()))-mov),Math.abs((this.getPosY()+(y-this.getPosY()))-mov));

                    if(novaPeace!=null){
                        return false;
                    }
                }

                for (int pos = 0; pos <= 7; pos++) {
                    switch (pos) {
                        case 0: {
                            novaPeace = Simulador.receberPeca(x - 1, y - 1);
                            System.out.println(novaPeace);
                            break;
                        }
                        case 1: {
                            novaPeace = Simulador.receberPeca(x, y - 1);
                            System.out.println(novaPeace);
                            break;
                        }
                        case 2: {
                            novaPeace = Simulador.receberPeca(x + 1, y - 1);
                            System.out.println(novaPeace);
                            break;
                        }
                        case 3: {
                            novaPeace = Simulador.receberPeca(x + 1, y);
                            System.out.println(novaPeace);
                            break;
                        }
                        case 4: {
                            novaPeace = Simulador.receberPeca(x + 1, y + 1);
                            System.out.println(novaPeace);
                            break;
                        }
                        case 5: {
                            novaPeace = Simulador.receberPeca(x, y + 1);
                            System.out.println(novaPeace);
                            break;
                        }
                        case 6: {
                            novaPeace = Simulador.receberPeca(x - 1, y + 1);
                            System.out.println(novaPeace);
                            break;
                        }
                        case 7: {
                            novaPeace = Simulador.receberPeca(x - 1, y);
                            System.out.println(novaPeace);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    System.out.println(novaPeace);
                    if (novaPeace != null && novaPeace.getTipoPeca() == 1 && novaPeace.getEquipa() != Simulador.equipaJogar) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
