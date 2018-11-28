package pt.ulusofona.lp2.crazyChess;

public class CrazyPiece {
    int id,equipa,tipoPeca;
    String imagePNG = null;
    String alcunha;
    Posicao posicao;

    public CrazyPiece(){}

    public CrazyPiece(int id,int tipoPeca,int equipa,String alcunha){
        this.id=id;
        this.tipoPeca=tipoPeca;
        this.equipa=equipa;
        this.alcunha=alcunha;
    }

    public Posicao getPosicao() {
        return posicao;
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
        return imagePNG;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public String toString(){
        return posicao.getPosX() + "|" + posicao.getPosY();
    }
}
