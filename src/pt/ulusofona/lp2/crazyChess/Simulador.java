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
    int validasPretas = 0,capturadasPretas = 0,invalidaspretas = 0; // Equipa a jogar - 0
    int validasBrancas = 0,capturadasBrancas = 0,invalidasBrancas = 0; //Equipa a jogar - 1
    int vencedor;
    Turno turnoAtual=new Turno();

    List<CrazyPiece> pecasMalucas=new ArrayList<>();
    List<String> autores=new ArrayList<>();
    List<String> resultados=new ArrayList<>();

    //Contrutores
    public Simulador(){}
    public Simulador(int tamanhoTabuleiro,List<CrazyPiece> pecasMalucas,int idEquipaAJogar){
        this.tamanhoTabuleiro=tamanhoTabuleiro;
        this.pecasMalucas=pecasMalucas;
        this.idEquipaAJogar=idEquipaAJogar;
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
                    Tipo tPeca=new Tipo(Integer.parseInt(info[1]));
                    CrazyPiece novaPeca=new CrazyPiece(Integer.parseInt(info[0]),tPeca,Integer.parseInt(info[2]),info[3]);
                    pecasMalucas.add(novaPeca);

                }else if((countLinha-nPecas-2)<tamanhoTabuleiro){
                    info=linha.split(":");
                    for(int i=0;i<tamanhoTabuleiro;i++){
                        if(Integer.parseInt(info[i])!=0){
                            for(CrazyPiece crazyPiece:pecasMalucas){
                                if(Integer.parseInt(info[i])==crazyPiece.getId()){
                                    Posicao posicao=new Posicao(i,countLinha-nPecas-2);
                                    crazyPiece.setPosicao(posicao);
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

    //Executa o movimento de uma peça
    public boolean processaJogada(int xO, int yO, int xD, int Yd){
        if(xD>=0 && xD<tamanhoTabuleiro && Yd>=0 && Yd<tamanhoTabuleiro){
            //Posicao origem= new Posicao(xO,yO);
            for(CrazyPiece peca:pecasMalucas){
                if(peca.getPosicao().equals(xO,yO)){
                    if(peca.getEquipa()==turnoAtual.getEquipaJogar()){
                        int difX=xD-xO;
                        int difY=Yd-yO;
                        int difXModulo = difX,difYModulo = difY;

                        //Criar o modulo para o caso da diferenca ser negativa
                        if(difX<0){
                            difXModulo=difX * (-1);
                        }
                        if(difY<0){
                            difYModulo=difY * (-1);
                        }

                        if(peca.getTipoPeca().getnPassosMax()<=difXModulo && peca.getTipoPeca().getnPassosMax()<=difYModulo){
                            if(difX>0){
                                if(peca.getTipoPeca().getHRight()){
                                    if(difY>0){
                                        if(peca.getTipoPeca().getVUp()){
                                            peca.getPosicao().moveRight(difXModulo);
                                            peca.getPosicao().moveUp(difYModulo);
                                        }else{
                                            switch (turnoAtual.getEquipaJogar()){
                                                case 0:

                                            }
                                        }
                                    }
                                }
                            }
                            //Funcao .remove usada para remover da lista já existe na biblioteca
                        }

                    }
                }
            }
        }
        return true;
    }

    //Devolve a lista de todas asa peças em jogo
    public List<CrazyPiece> getPecasMalucas(){
        return pecasMalucas;
    }

    //Premite finalizar o jogo se for comprida alguma das condições
    public boolean jogoTerminado(){
        return true;
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
            if (crazyPiece.getPosicao().equals(x,y)) {
                return crazyPiece.getId();
            }
        }
        return 0;
    }

    //Devolve a equipa a jogar (Feito)
    public int getIDEquipaAJogar(){
        return turnoAtual.getEquipaJogar();
    }
}