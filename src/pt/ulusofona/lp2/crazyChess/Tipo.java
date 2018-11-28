package pt.ulusofona.lp2.crazyChess;

public class Tipo {
    int id;
    boolean hRight,hLeft,vUp,vDown,dRightUp,dRightDown,dLeftUp,dLeftDown;
    int nPassosMax;

    public Tipo(){}

    public Tipo(int id){
        this.id=id;

        switch (id){
            case 0:
                nPassosMax=1;
                hRight = hLeft = vUp = vDown = dRightUp = dRightDown = dLeftUp = dLeftDown = true;
                break;

            case 1:
                //Será preenchido noutra parte do projeto
                break;

            default:
                //Será preenchido mais tarde
                break;
        }
    }

    public int getId() {
        return id;
    }

    public int getnPassos() {
        return nPassosMax;
    }

   /*public String getSentido() {
        return sentido;
    }*/

    /*
    String(frases),int(inteiros),long(inteiros grandes),char(letra),double(decimal),boolean(true/false),byte(),short(),float(decimais)
     */

    //apesar que o string é o melhor, pq das outras opçoes são mais dificies de coompreender as coisas
    //sentido e quantidade de passos
}
