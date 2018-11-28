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

    //Variaveis que são listas
    List<CrazyPiece> pecasMalucas=new ArrayList<>();
    List<String> autores=new ArrayList<>();
    List<String> resultados=new ArrayList<>();

    //Contrutores
    public Simulador(){
        this.turno=0;
        this.semCaptura=0;
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
            System.out.println("pos existe");
            CrazyPiece origem=receberPeca(xO,yO);
            if(origem!=null && origem.getEquipa()==this.getIDEquipaAJogar()){
                System.out.println("origem existe");
                CrazyPiece destino=receberPeca(xD,yD);
                if (destino == null) {
                    System.out.println("destino é null");
                    if (origem.mover(xD,yD)){
                        System.out.println("move");
                        origem.setPosicao(xD,yD);
                        turno++;
                        semCaptura++;
                        return true;
                    }
                }
                else if (destino.getEquipa() != equipaJogar) {
                    System.out.println("destino é equipa oposta");
                    if (origem.mover(xD,yD)){
                        System.out.println("mover2");
                        destino.setPosicao(-1,-1);
                        origem.setPosicao(xD,yD);
                        turno++;
                        semCaptura=0;
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
        if(resultados==null){

        }
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