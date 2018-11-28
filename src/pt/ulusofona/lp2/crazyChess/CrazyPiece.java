package pt.ulusofona.lp2.crazyChess;

public class CrazyPiece {
    int id;
    String imagePNG = null;
    String alcunha;
    Tipo tipoPeca;
    Equipa equipa;
    Posicao posicao;

    public CrazyPiece(){}

    public CrazyPiece(int id,Tipo tipoPeca,Equipa equipa,String alcunha){
        this.id=id;
        this.tipoPeca=tipoPeca;
        this.equipa=equipa;
        this.alcunha=alcunha;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public Equipa getEquipa() {
        return equipa;
    }

    public Tipo getTipoPeca() {
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
