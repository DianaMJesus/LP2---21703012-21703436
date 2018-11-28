package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulador {

    //iniciar variáveis da classe
    int tamanhoTabuleiro,idEquipaAJogar;

    //Variaveis que seram mudadas de lugar
    int validasPretas,capturadasPretas,invalidasPretas; // Equipa a jogar - 0
    int validasBrancas,capturadasBrancas,invalidasBrancas; //Equipa a jogar - 1
    int vencedor,turno,equipaJogar,semCaptura;
    boolean capturaPrevia;

    //Variaveis que são listas
    List<CrazyPiece> pecasMalucas=new ArrayList<>();
    List<String> autores=new ArrayList<>();
    List<String> resultados=new ArrayList<>();

    //Contrutores
    public Simulador(){
        this.turno=0;
        this.semCaptura=0;
        this.capturaPrevia=false;
        this.validasPretas = 0;
        this.capturadasPretas = 0;
        this.invalidasPretas = 0;
        this.validasBrancas = 0;
        this.capturadasBrancas = 0;
        this.invalidasBrancas = 0;
    }

    //Leitura do Ficheiro (Feito)
    public boolean iniciaJogo(File ficheiroInicial){
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
                    tamanhoTabuleiro = Integer.parseInt(linha); //guarda o tamanho do tabuleiro

                }else if(countLinha==1){
                    nPecas=Integer.parseInt(linha); //guarda o numero de pecas

                }else if((countLinha-2)<nPecas){
                    info=linha.split(":"); //coloca num array a infomacao da linha e é separada pelos :
                    CrazyPiece novaPeca=new CrazyPiece(Integer.parseInt(info[0]),Integer.parseInt(info[1]),Integer.parseInt(info[2]),info[3]);
                    pecasMalucas.add(novaPeca);

                }else if((countLinha-nPecas-2)<tamanhoTabuleiro){
                    info=linha.split(":");
                    for(int i=0;i<tamanhoTabuleiro;i++){
                        if(Integer.parseInt(info[i])!=0){
                            for(CrazyPiece crazyPiece:pecasMalucas){
                                if(Integer.parseInt(info[i])==crazyPiece.getId()){
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
        if(xO>=0 && xO<tamanhoTabuleiro && yO>=0 &&yO<tamanhoTabuleiro && xD>=0 && xD<tamanhoTabuleiro && yD>=0 && yD<tamanhoTabuleiro){
            CrazyPiece origem=receberPeca(xO,yO);
            if(origem!=null && origem.getEquipa()==this.getIDEquipaAJogar()){
                CrazyPiece destino=receberPeca(xD,yD);
                if (destino == null) {
                    if (origem.mover(xD,yD)){
                        origem.setPosicao(xD,yD);
                        this.semCaptura++;
                        if(this.getIDEquipaAJogar()==0){
                            this.validasPretas++;
                        }else if(this.getIDEquipaAJogar()==1){
                            this.validasBrancas++;
                        }
                        this.turno++;
                        return true;
                    }
                }
                if (!destino.equipaEquals(equipaJogar)) {
                    System.out.println("destino é equipa oposta");
                    if (origem.mover(xD,yD)){
                        System.out.println("mover2");
                        destino.setPosicao(-1,-1);
                        origem.setPosicao(xD,yD);
                        this.semCaptura=0;
                        this.capturaPrevia=true;
                        if(getIDEquipaAJogar()==0){
                            this.validasPretas++;
                            this.capturadasPretas++;
                        }else if(getIDEquipaAJogar()==1){
                            this.validasBrancas++;
                            this.capturadasBrancas++;
                        }
                        this.turno++;
                        return true;
                    }
                }
            }
        }
        //contar jogada invalida
        if(this.getIDEquipaAJogar()==0){
            invalidasPretas++;
        }else if(this.getIDEquipaAJogar()==1){
            invalidasBrancas++;
        }
        System.out.println("invalida");
        return false;
    }

    //Devolve a lista de todas asa peças em jogo(Feito)
    public List<CrazyPiece> getPecasMalucas(){
        return pecasMalucas;
    }

    //Premite finalizar o jogo se for comprida alguma das condições
    public boolean jogoTerminado(){
        int reisBrancos = 0,reisPretos = 0;

        for(CrazyPiece piece:pecasMalucas){
            if(!piece.comida()){
                if(piece.getTipoPeca()==0){
                    if(piece.getEquipa()==0){
                        reisBrancos++;
                    }else if(piece.getEquipa()==1){
                        reisPretos++;
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
        if(autores.size()==0){
            autores.add("Ana Maria - nº 21703436");
            autores.add("Diana Jesus - nº 21703012");
        }
        return autores;
    }

    //Devolve o valor dos resultados do jogo
    public List<String> getResultados(){
        //Ver vencedor:
        //0 - Pretas
        //1 - Brancas
        //2 - Empate

        this.resultados.add("JOGO DE CRAZY CHESS");
        switch (this.vencedor){
            case 0:{
                this.resultados.add("Resultado: VENCERAM AS PRETAS");
                break;}

            case 1:{
                this.resultados.add("Resultado: VENCERAM AS BRANCAS");
                break;}

            case 2:{
                this.resultados.add("Resultado: EMPATE");
                break;}
        }
        this.resultados.add("---");

        //Informacao da equipa preta
        this.resultados.add("Equipa das Pretas");
        this.resultados.add(this.capturadasPretas + "");
        this.resultados.add(this.validasPretas + "");
        this.resultados.add(this.invalidasPretas + "");

        //Informacao da equipa branca
        this.resultados.add("Equipa das Brancas");
        this.resultados.add(this.capturadasBrancas + "");
        this.resultados.add(this.validasBrancas + "");
        this.resultados.add(this.invalidasBrancas + "");
        return this.resultados;
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
        if(turno%2==0){
            equipaJogar=0;
        }else{
            equipaJogar=1;
        }
        return equipaJogar;
    }

    public CrazyPiece receberPeca(int x,int y){
        for(CrazyPiece peace: pecasMalucas) {
            if (peace.posX == x && peace.posY == y) {
                return peace;
            }
        }
        return null;
    }
}