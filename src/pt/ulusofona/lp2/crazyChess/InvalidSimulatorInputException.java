package pt.ulusofona.lp2.crazyChess;

import java.io.IOException;

public class InvalidSimulatorInputException extends IOException {

    int linhaErro;
    String descricaoProblema;

    InvalidSimulatorInputException(){}

    InvalidSimulatorInputException(int linhaErro, String descricaoProblema){
        this.linhaErro = linhaErro + 1;
        this.descricaoProblema = descricaoProblema;
    }

    public int getLinhaErro() {
        return this.linhaErro;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }
}
