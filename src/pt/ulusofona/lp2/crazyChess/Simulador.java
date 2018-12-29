package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulador {

    //Variaveis que seram mudadas de lugar
    int validasPretas = 0,capturadasPretas = 0,invalidasPretas = 0; // Equipa a jogar - 0
    int validasBrancas = 0,capturadasBrancas = 0,invalidasBrancas = 0; //Equipa a jogar - 1
    int vencedor,semCaptura = 0;
    boolean capturaPrevia = false;
    static int turno=0,equipaJogar,tamanhoTabuleiro;

    //Variaveis que são listas
    static List<CrazyPiece> pecasMalucas=new ArrayList<>();

    //Contrutores
    public Simulador(){
    }

    public Simulador(int boardSize) {

        Simulador.tamanhoTabuleiro = boardSize;

    }

    //Leitura do Ficheiro (Feito)
    public boolean iniciaJogo(File ficheiroInicial){
        this.reset();
        try{
            Scanner leitorFicheiro = new Scanner (ficheiroInicial);
            int countLinha=0;
            int nPecas = 0;

            /*
            primeira info-> dimensões do tabuleiro
            segunda info-> n peças
            terceira info-> caracterização de peças
            quarta info-> localização das peças no tabuleiro
             */

            while(leitorFicheiro.hasNextLine()){

                String linha= leitorFicheiro.nextLine();
                String info[];

                if(countLinha == 0){
                    if(Integer.parseInt(linha)>=4 && Integer.parseInt(linha)<=12) {
                        tamanhoTabuleiro = Integer.parseInt(linha); //guarda o tamanho do tabuleiro
                    }else{
                        return false;
                    }

                }else if(countLinha==1){
                    if(Integer.parseInt(linha)<(tamanhoTabuleiro*tamanhoTabuleiro)) {
                        nPecas = Integer.parseInt(linha); //guarda o numero de pecas
                    }else{
                        return false;
                    }

                }else if((countLinha-2)<nPecas){
                    info=linha.split(":"); //coloca num array a infomacao da linha e é separada pelos :
                    CrazyPiece novaPeca=null;
                    int tipoP=Integer.parseInt(info[1]);

                    switch (tipoP){
                        case 0:
                            novaPeca=new Rei(Integer.parseInt(info[0]),Integer.parseInt(info[2]),info[3]);
                            break;

                        case 1:
                            novaPeca=new Rainha(Integer.parseInt(info[0]),Integer.parseInt(info[2]),info[3]);
                            break;

                        case 2:
                            novaPeca=new PoneiMagico(Integer.parseInt(info[0]),Integer.parseInt(info[2]),info[3]);
                            break;

                        case 3:
                            novaPeca=new PadreDaVila(Integer.parseInt(info[0]),Integer.parseInt(info[2]),info[3]);
                            break;

                        case 4:
                            novaPeca=new TorreH(Integer.parseInt(info[0]),Integer.parseInt(info[2]),info[3]);
                            break;

                        case 5:
                            novaPeca=new TorreV(Integer.parseInt(info[0]),Integer.parseInt(info[2]),info[3]);
                            break;

                        case 6:
                            novaPeca=new Lebre(Integer.parseInt(info[0]),Integer.parseInt(info[2]),info[3]);
                            break;

                        case 7:
                            novaPeca=new Joker(Integer.parseInt(info[0]),Integer.parseInt(info[2]),info[3]);
                            break;

                        default:
                            break;
                    }
                    pecasMalucas.add(novaPeca);

                }else if((countLinha-nPecas-2)<tamanhoTabuleiro){
                    info=linha.split(":");
                    for(int i=0;i<tamanhoTabuleiro;i++){
                        if(Integer.parseInt(info[i])!=0){
                            for(CrazyPiece crazyPiece:pecasMalucas){
                                if(Integer.parseInt(info[i])==crazyPiece.getId()){
                                    crazyPiece.estaEmJogo();
                                    crazyPiece.setPosicao(i,countLinha-(nPecas+2));
                                }
                            }
                        }
                    }
                }else{
                    //tem linhas a mais
                    return false;
                }
                countLinha++;
            }
            return true;

        } catch (FileNotFoundException e) {
            return false;
        }
    }

    //Envia o tamanho do tabuleiro (Feito)
    public int getTamanhoTabuleiro(){
        return tamanhoTabuleiro;
    }

    //Executa o movimento de uma peça (Resolver o problema da horizontal)
    public boolean processaJogada(int xO, int yO, int xD, int yD){
        if(((xO>=0 && xO<tamanhoTabuleiro) && (yO>=0 && yO<tamanhoTabuleiro)) &&
                ((xD>=0 && xD<tamanhoTabuleiro) && (yD>=0 && yD<tamanhoTabuleiro))){
            CrazyPiece origem=receberPeca(xO,yO);
            if(origem!=null && origem.getEquipa()==this.getIDEquipaAJogar()){
                CrazyPiece destino=receberPeca(xD,yD);
                if (destino == null) {
                    if (origem.podeMover(xD,yD)){
                        origem.setPosicao(xD,yD);
                        this.semCaptura++;
                        if(this.getIDEquipaAJogar()==10){ //Pretas
                            this.validasPretas++;
                        }else if(this.getIDEquipaAJogar()==20){ //Brancas
                            this.validasBrancas++;
                        }
                        turno++;
                        return true;
                    }
                } else if (!destino.equipaEquals(equipaJogar)) {
                    if (origem.podeMover(xD,yD)){
                        destino.setPosicao(-1,-1);
                        origem.setPosicao(xD,yD);
                        this.semCaptura=0;
                        this.capturaPrevia=true;
                        if(this.getIDEquipaAJogar()==10){ //Pretas
                            this.validasPretas++;
                            this.capturadasPretas++;
                        }else if(this.getIDEquipaAJogar()==20){ //Brancas
                            this.validasBrancas++;
                            this.capturadasBrancas++;
                        }
                        turno++;
                        return true;
                    }
                }
            }
        }
        //contar jogada invalida
        if(this.getIDEquipaAJogar()==10){ //Pretas
            invalidasPretas++;
        }else if(this.getIDEquipaAJogar()==20){ //Brancas
            invalidasBrancas++;
        }
        System.out.println("invalida");
        return false;
    }

    //Devolve a lista de todas asa peças em jogo(Feito)
    public List<CrazyPiece> getPecasMalucas(){
        return pecasMalucas;
    }

    //Premite finalizar o jogo se for comprida alguma das condições (Alterar)
    public boolean jogoTerminado(){
        int reisBrancos = 0,reisPretos = 0;
        if (pecasMalucas == null){
            return true;
        }
        for(CrazyPiece piece:pecasMalucas){
            if(piece.getEmJogo()) {
                if (!piece.comida()) {
                    if (piece.getTipoPeca() == 0) {
                        if (piece.getEquipa() == 10) { //Pecas Pretas
                            reisBrancos++;
                        } else if (piece.getEquipa() == 20) { //Pecas Brancas
                            reisPretos++;
                        }
                    }
                }
            }
        }
        //Ver vencedor:
        //0 - Pretas
        //1 - Brancas
        //2 - Empate

        if(reisBrancos==0){
            //PRETAS VENCEM
            this.vencedor=1;
            return true;
        }else if(reisPretos==0){
            //BRANCAS VENCEM
            this.vencedor=0;
            return true;
        }else if(reisBrancos==1 && reisPretos==1){
            //EMPATE
            this.vencedor=2;
            return true;
        }

        if(this.capturaPrevia){
            if(semCaptura==10){
                this.vencedor=2;
                return true;
            }
        }

        return false;
    }

    //Devolve a lista dos autores (Feito)
    public List<String> getAutores(){
        List<String> autores=new ArrayList<>();
        autores.add("Ana Maria - nº 21703436");
        autores.add("Diana Jesus - nº 21703012");
        return autores;
    }

    //Devolve o valor dos resultados do jogo
    public List<String> getResultados(){
        List<String> resultados=new ArrayList<>();
        //Ver vencedor:
        //0 - Pretas
        //1 - Brancas
        //2 - Empate

        resultados.add("JOGO DE CRAZY CHESS");
        switch (this.vencedor){
            case 0:{
                resultados.add("Resultado: VENCERAM AS PRETAS");
                break;}

            case 1:{
                resultados.add("Resultado: VENCERAM AS BRANCAS");
                break;}

            case 2:{
                resultados.add("Resultado: EMPATE");
                break;}
        }
        resultados.add("---");

        //Informacao da equipa preta
        resultados.add("Equipa das Pretas");
        resultados.add(this.capturadasPretas + "");
        resultados.add(this.validasPretas + "");
        resultados.add(this.invalidasPretas + "");

        //Informacao da equipa branca
        resultados.add("Equipa das Brancas");
        resultados.add(this.capturadasBrancas + "");
        resultados.add(this.validasBrancas + "");
        resultados.add(this.invalidasBrancas + "");
        return resultados;
    }

    //Devolve o id de uma peça naquela posição (Feito)
    public int getIDPeca(int x, int y){
        for(CrazyPiece crazyPiece:pecasMalucas) {
            if (crazyPiece.getPosX()==x && crazyPiece.getPosY()==y) {
                return crazyPiece.getId();
            }
        }
        return 0;
    }

    //Devolve a equipa a jogar (Feito)
    public int getIDEquipaAJogar(){
        if(Simulador.turno%2==0){
            Simulador.equipaJogar=10; //Pretas
        }else{
            Simulador.equipaJogar=20; //Brancas
        }
        return Simulador.equipaJogar;
    }

    public static CrazyPiece receberPeca(int x,int y){
        for(CrazyPiece peace: Simulador.pecasMalucas) {
            if (peace.posX == x && peace.posY == y) {
                return peace;
            }
        }
        return null;
    }

    //Metodos para os botoes da segunda parte do projeto
    public List<String> obterSugestoesJogada(int xO, int yO){
        List<String> sugetoesJogada = new ArrayList<>();
        CrazyPiece peace = receberPeca(xO,yO);
        if (peace != null) {
            sugetoesJogada = peace.sugetaoJogada(xO, yO, pecasMalucas, receberPeca(xO, yO));
        }
        return sugetoesJogada;
    }

    public void anularJogadaAnterior(){}

    public boolean gravarJogo(File ficheiroDestino){
        return true;
    }

    //Reenicia as variaveis do jogo
    public void reset(){
        pecasMalucas.clear();
        turno=0;
        this.semCaptura=0;
        this.capturaPrevia=false;
        this.validasPretas = 0;
        this.capturadasPretas = 0;
        this.invalidasPretas = 0;
        this.validasBrancas = 0;
        this.capturadasBrancas = 0;
        this.invalidasBrancas = 0;
    }
}