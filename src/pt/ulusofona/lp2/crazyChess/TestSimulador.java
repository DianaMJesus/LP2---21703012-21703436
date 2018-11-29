package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;
import java.io.File;
import static org.junit.Assert.assertFalse;

public class TestSimulador {
    @Test
    public void faltaFicheiro(){
        Simulador simulador = new Simulador();
        boolean resultado = simulador.iniciaJogo(new File(""));
        assertFalse("Não selecionou nenhum ficheiro",resultado);
    }

    @Test
    public void tentaJogada(){
        Simulador simulador = new Simulador();
        boolean resultado = simulador.processaJogada(2,3,5,8);
        assertFalse("Não foi possivel passar no teste",resultado);
    }
}
