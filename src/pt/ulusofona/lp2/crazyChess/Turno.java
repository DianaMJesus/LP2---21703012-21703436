package pt.ulusofona.lp2.crazyChess;

public class Turno {
    int trunoAtual,equipaJogar,captura;

    public Turno(){}

    public Turno(int turno){
        this.trunoAtual=turno;

        if(trunoAtual==0){
            equipaJogar=1;
        }else if(trunoAtual==1){
            equipaJogar=0;
        }
    }

    public int getTrunoAtual() {
        return trunoAtual;
    }

    public int getEquipaJogar() {
        return equipaJogar;
    }

    public int getCaptura() {
        return captura;
    }
}
