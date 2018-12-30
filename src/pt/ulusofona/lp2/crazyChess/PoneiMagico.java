package pt.ulusofona.lp2.crazyChess;

public class PoneiMagico extends CrazyPiece {
    public PoneiMagico(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=2;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo="5";
        super.passoMax=4;
    }

    @Override
    public boolean podeMover(int x,int y){
        CrazyPiece novaPeace = Simulador.receberPeca(x,y);

        if(Math.abs(x-this.getPosX())==2 && Math.abs(y-this.getPosY())==2){
            for(int caminho1=1;caminho1<=4;caminho1++){
                if(caminho1==1) {
                    novaPeace = Simulador.receberPeca(this.getPosX()+1,this.getPosY());
                }else if(caminho1==2){
                    novaPeace = Simulador.receberPeca(x,this.getPosY());
                }else if(caminho1==3){
                    novaPeace = Simulador.receberPeca(x,this.getPosY()+1);
                }

                if(novaPeace!=null && novaPeace.getTipoPeca()==0){
                    System.out.println("entrou no segundo caminho");
                    for(int caminho2=1;caminho2<4;caminho2++){
                        if(caminho2==1) {
                            novaPeace = Simulador.receberPeca(this.getPosX(),this.getPosY()+1);
                        }else if(caminho2==2){
                            novaPeace = Simulador.receberPeca(this.getPosX(),y);
                        }else if(caminho2==3){
                            novaPeace = Simulador.receberPeca(this.getPosX()+1,y);
                        }

                        if(novaPeace!=null && novaPeace.getTipoPeca()==0){
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}
