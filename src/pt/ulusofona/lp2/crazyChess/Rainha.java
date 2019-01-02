package pt.ulusofona.lp2.crazyChess;


import java.util.List;

public class Rainha extends CrazyPiece {
    public Rainha(int id,int equipa,String alcunha){
        super.id=id;
        super.tipoPeca=1;
        super.equipa=equipa;
        super.alcunha=alcunha;
        super.valorRelativo="8";
        super.passoMax=5;
        super.tipo="Rainha";
        if(equipa == 10){
            imagePNG = "rainha_preta.png";
        }else if(equipa == 20){
            imagePNG = "rainha_branca.png";
        }
    }

    @Override
    public boolean podeMover(int x, int y, List<CrazyPiece> pecasMalucas){
        CrazyPiece novaPeace=Simulador.receberPeca(x,y,pecasMalucas);

//      check if the movement is between the rang that the queen can move it self and if the destiny is different from the actual position
        if(Math.abs(x - this.getPosX()) <= 5 && Math.abs(y - this.getPosY()) <= 5 && (this.getPosX() != x || this.getPosY() != y)){
//          if the movement is made in the diagonal
            if(Math.abs(x-this.getPosX())==Math.abs(y-this.getPosY())){

                for(int mov=1;mov<Math.abs(x-this.getPosX());mov++){ //Diagonal

                    if((x-this.getPosX())<0 && (y-this.getPosY())<0){ //Esquerda-Cima
                        novaPeace=Simulador.receberPeca(this.getPosX()-mov,this.getPosY()-mov,pecasMalucas);
                    }
                    else if((x-this.getPosX())<0 && (y-this.getPosY())>0){ //Esquerda-Baixo
                        novaPeace=Simulador.receberPeca(this.getPosX()-mov,this.getPosY()+mov,pecasMalucas);
                    }
                    else if((x-this.getPosX())>0 && (y-this.getPosY())<0){ //Direita-Cima
                        novaPeace=Simulador.receberPeca(this.getPosX()+mov,this.getPosY()-mov,pecasMalucas);
                    }
                    else if((x-this.getPosX())>0 && (y-this.getPosY())>0){ //Direita-Baixo
                        novaPeace=Simulador.receberPeca(this.getPosX()+mov,this.getPosY()+mov,pecasMalucas);
                    }

                    if(novaPeace!=null){
                        return false;
                    }
                }
            }else{
                if(x==0 && y!=0){
                    for(int mov=1;mov<Math.abs(y-this.getPosY());mov++){ //Vertical

                        if((y-this.getPosY())<0){ //Cima
                            novaPeace=Simulador.receberPeca(x,this.getPosY()-mov,pecasMalucas);
                        }
                        else if((y-this.getPosY())>0){ //Baixo
                            novaPeace=Simulador.receberPeca(x,this.getPosY()-mov,pecasMalucas);
                        }

                        if(novaPeace!=null){
                            return false;
                        }
                    }
                }else if(x!=0 && y==0){
                    System.out.println("Horizontal");
                    for(int mov=1;mov<Math.abs(x-this.getPosX());mov++){ //Horizontal
                        if((x-this.getPosX())<0){ //Cima
                            novaPeace=Simulador.receberPeca(this.getPosX()-mov,y,pecasMalucas);
                        }
                        else if((y-this.getPosY())>0){ //Baixo
                            novaPeace=Simulador.receberPeca(this.getPosX()-mov,y,pecasMalucas);
                        }

                        if(novaPeace!=null){
                            return false;
                        }
                    }

                }
            }

            return (novaPeace == null) || (novaPeace.getTipoPeca() != 1 && novaPeace.getEquipa() != Simulador.getEquipaJogar());
        }
        return false;
    }
}
