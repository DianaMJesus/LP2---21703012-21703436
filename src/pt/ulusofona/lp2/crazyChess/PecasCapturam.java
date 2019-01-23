package pt.ulusofona.lp2.crazyChess;

public class PecasCapturam {
    int idTipoPeca;
    int nCapturadas;

    public PecasCapturam(int idTipoPeca){
        this.idTipoPeca = idTipoPeca;
        this.nCapturadas = 0;
    }

    public void setnCapturadas() {
        this.nCapturadas++;
    }

    public int getIdTipoPeca() {
        return idTipoPeca;
    }

    public int getnCapturadas() {
        return nCapturadas;
    }
}
