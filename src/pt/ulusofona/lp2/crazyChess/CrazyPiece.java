package pt.ulusofona.lp2.crazyChess;

public class CrazyPiece {
    int id,equipa,tipoPeca,posX,posY;
    String imagePNG;
    String alcunha;

    public CrazyPiece(){}

    public CrazyPiece(int id,int tipoPeca,int equipa,String alcunha){
        this.id=id;
        this.tipoPeca=tipoPeca;
        this.equipa=equipa;
        this.alcunha=alcunha;
    }

    public boolean equipaEquals(int valor){
        if(this.equipa==valor){
            return true;
        }
        return false;
    }

    public int getEquipa() {
        return equipa;
    }

    public int getTipoPeca() {
        return tipoPeca;
    }

    public String getAlcunha() {
        return alcunha;
    }

    public int getId(){
        return id;
    }

    public String getImagePNG(){
        if(equipa==0){
            return "crazy_emoji_black.png";
        }else {
            return "crazy_emoji_white.png";
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosicao(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
    //se existe peca e se é da equipa

    public boolean mover(int x,int y){
        if(Math.abs(x - this.posX) <= 1 && Math.abs(y - this.posY) <= 1 && (this.posX!=x || this.posY!=y)){
            return true;
        }
        return false;
    }

    public boolean comida(){
        if(this.posY==-1 && this.posX==-1){
            return true;
        }
        return false;
    }

    public String toString(){
        if(this.posX==-1 && this.posY==-1){
            return getId() + " | " + getTipoPeca() + " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return getId() + " | " + getTipoPeca() + " | " + getEquipa() + " | " + getAlcunha() + " @ (" + getPosX() + ", " +  getPosY() + ")";
    }
}
