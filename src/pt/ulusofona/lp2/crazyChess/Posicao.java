package pt.ulusofona.lp2.crazyChess;

public class Posicao {
    int posX;
    int posY;

    public Posicao(){}

    public Posicao(int posX,int posY){
        this.posX=posX;
        this.posY=posY;
    }

    //Get
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean equals(int x,int y){
        if(this.posX==x && this.posY==y){
            return true;
        }
        return false;
    }

    public void moveRight(int x){
        this.posX+=x;
    }

    public void moveLeft(int x){
        this.posX-=x;
    }

    public void moveUp(int y){
        this.posY-=y;
    }

    public void moveDown(int y){
        this.posY+=y;
    }

}
