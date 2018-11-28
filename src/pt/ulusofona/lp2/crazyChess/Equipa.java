package pt.ulusofona.lp2.crazyChess;

public class Equipa {
    int id;
    String cor;
    int capturas;
    int jogadasValidas;
    int jogadasInvalidas;

    public Equipa() {
    }

    public Equipa(int id) {
        this.id = id;

        switch (id) {
            case 0:
                cor = "Branca";
                break;

            case 1:
                cor = "Preta";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJogadasInvalidas() {
        return jogadasInvalidas;
    }

    public int getJogadasValidas() {
        return jogadasValidas;
    }

    public void addJogadasValidas() {
        this.jogadasValidas += 1;
    }

    public void addJogadasInvalidas() {
        this.jogadasInvalidas += 1;
    }

    public void addCapturadas(){
        this.capturas+=1;
    }
}