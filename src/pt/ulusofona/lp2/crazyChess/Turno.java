package pt.ulusofona.lp2.crazyChess;

public class Turno {
    int trunoAtual,equipaJogar,captura;

    public Turno(){}

    public Turno(int turno){
        this.trunoAtual=turno;

        if(trunoAtual%2==0){
            equipaJogar=0;
        }else{
            equipaJogar=1;
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
