package pt.ulusofona.lp2.crazyChess;


public class Rainha extends CrazyPiece {
    public Rainha(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=1;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo="8";
        super.passoMax=5;
    }

    @Override
    public boolean podeMover(int x, int y){
        CrazyPiece novaPeace=Simulador.receberPeca(x,y);
        System.out.println(this.getPosX() + "," + posY);

//      check if the movement is between the rang that the queen can move it self and if the destiny is different from the actual position
        if(Math.abs(x - this.getPosX()) <= 5 && Math.abs(y - this.getPosY()) <= 5 && (this.getPosX() != x || this.getPosY() != y)){
//          if the movement is made in the diagonal
            if(Math.abs(x-this.getPosX())==Math.abs(y-this.getPosY())){
                System.out.println("Diagonal");

                for(int mov=1;mov<Math.abs(x-this.getPosX());mov++){ //Diagonal
                    System.out.println("oi");

                    if((x-this.getPosX())<0 && (y-this.getPosY())<0){ //Esquerda-Cima
                        novaPeace=Simulador.receberPeca(this.getPosX()-mov,this.getPosY()-mov);
                    }
                    else if((x-this.getPosX())<0 && (y-this.getPosY())>0){ //Esquerda-Baixo
                        novaPeace=Simulador.receberPeca(this.getPosX()-mov,this.getPosY()+mov);
                    }
                    else if((x-this.getPosX())>0 && (y-this.getPosY())<0){ //Direita-Cima
                        novaPeace=Simulador.receberPeca(this.getPosX()+mov,this.getPosY()-mov);
                    }
                    else if((x-this.getPosX())>0 && (y-this.getPosY())>0){ //Direita-Baixo
                        novaPeace=Simulador.receberPeca(this.getPosX()+mov,this.getPosY()+mov);
                    }
                    System.out.println((this.getPosX()) + " , " + (this.getPosY()));
                    System.out.println(novaPeace);
                    if(novaPeace!=null){
                        System.out.println("deu false");
                        return false;
                    }
                }

            }else{
                if(x==0 && y!=0){
                    System.out.println("Vertical");
                    for(int mov=1;mov<Math.abs(y-this.getPosY());mov++){ //Vertical

                        if((y-this.getPosY())<0){ //Cima
                            novaPeace=Simulador.receberPeca(x,this.getPosY()-mov);
                        }
                        else if((y-this.getPosY())>0){ //Baixo
                            novaPeace=Simulador.receberPeca(x,this.getPosY()-mov);
                        }

                        if(novaPeace!=null){
                            return false;
                        }
                    }

                }else if(x!=0 && y==0){
                    System.out.println("Horizontal");
                    for(int mov=1;mov<Math.abs(x-this.getPosX());mov++){ //Horizontal
                        if((x-this.getPosX())<0){ //Cima
                            novaPeace=Simulador.receberPeca(this.getPosX()-mov,y);
                        }
                        else if((y-this.getPosY())>0){ //Baixo
                            novaPeace=Simulador.receberPeca(this.getPosX()-mov,y);
                        }

                        if(novaPeace!=null){
                            return false;
                        }
                    }

                }
            }
            return (novaPeace == null) || (novaPeace.getTipoPeca() != 1 && novaPeace.getEquipa() != Simulador.equipaJogar);
        }

        /*|| (!novaPeace.haPadre(x, y, 3))*/
        return false;
    }

}
