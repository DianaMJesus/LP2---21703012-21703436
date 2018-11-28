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
        if((this.posX==1+x || this.posX==-1+x || this.posY==1+y || this.posY==-1+y) && (this.posX!=x || this.posY!=y)){
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
        return getPosX() + "|" + getPosY();
    }
}
