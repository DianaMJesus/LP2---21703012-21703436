package pt.ulusofona.lp2.crazyChess;

import java.util.List;
import java.util.stream.Collectors;

public class Comparable {
    int x , y , nPontos;

    Comparable(int x, int y, int nPontos){
        this.x = x;
        this.y = y;
        this.nPontos = nPontos;
    }

    @Override
    public String toString() {
        return this.x + ", " + this.y + ", " + this.nPontos;
    }

    public List<Comparable> compareTo(List<Comparable> listaSusgestoes){

        return listaSusgestoes.stream()
                .sorted((s1 , s2) -> s2.nPontos - s1.nPontos)
                .collect(Collectors.toList());
    }
}
