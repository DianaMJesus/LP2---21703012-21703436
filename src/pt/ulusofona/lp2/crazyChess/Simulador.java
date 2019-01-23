package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Simulador {

//Variaveis que seram mudadas de lugar
    private int validasPretas = 0,capturadasPretas = 0,invalidasPretas = 0; // Equipa a jogar - 0
    private int validasBrancas = 0,capturadasBrancas = 0,invalidasBrancas = 0; //Equipa a jogar - 1
    private int vencedor,semCaptura = 0;
    private boolean capturaPrevia = false, antigaCapturaPrevia;
    private int turnoAntigo,capturasAntigas;
    int equipaJogar,countAnulaJogada=0,turno = 0,tamanhoTabuleiro;

    //Listas
    List<CrazyPiece> pecasMalucas = new ArrayList<>();
    List<PecasCapturam> pecasCapturam = new ArrayList<>();
    List<String> recuperaPecas = new ArrayList<>();
    private List<String> informacaoEquipas = new ArrayList<>();

 //Contrutores
    public Simulador(){
    }

    public Simulador(int boardSize) {
        tamanhoTabuleiro = boardSize;
    }

//Leitura do Ficheiro (Feito)
    public void iniciaJogo(File ficheiroInicial) throws InvalidSimulatorInputException , IOException{
        this.reset();
        try {
            Scanner leitorFicheiro = new Scanner(ficheiroInicial);
            int countLinha = 0;
            int nPecas = 0;

            /*
            primeira info-> dimensões do tabuleiro
            segunda info-> n peças
            terceira info-> caracterização de peças
            quarta info-> localização das peças no tabuleiro
            quinta info -> atribuir informação caso seja aberto um jogo antigo
             */

            while (leitorFicheiro.hasNextLine()) {

                String linha = leitorFicheiro.nextLine();
                String info[];

                //Primeira e Segunda parte
                if (countLinha == 0 || countLinha == 1) {
                    info = linha.split(":");
                    if (info.length == 1) {
                        //Primeira parte -> tamanho do tabuleiro
                        if(countLinha == 0) {
                            tamanhoTabuleiro = Integer.parseInt(info[0]); //guarda o tamanho do tabuleiro
                        }
                        //Segunda parte -> numero de peças existentes no jogo
                        else if(countLinha == 1){
                            nPecas = Integer.parseInt(info[0]); //guarda o numero de pecas
                        }
                    } else {
                        if(info.length > 1) {
                            throw new InvalidSimulatorInputException(countLinha,"DADOS A MAIS (Esperava: 1 ; Obtive: " + info.length + ")");
                        }else if(info.length < 1){
                            throw new InvalidSimulatorInputException(countLinha,"DADOS A MENOS (Esperava: 1 ; Obtive: " + info.length + ")");
                        }
                    }
                }
                //Terceira parte -> informação sobre as peças
                else if ((countLinha - 2) < nPecas) {
                    info = linha.split(":");

                    if (info.length == 4) {
                        CrazyPiece novaPeca = null;
                        PecasCapturam peca = null;

                        int tipoP = Integer.parseInt(info[1]);

                        switch (tipoP) {
                            case 0:
                                novaPeca = new Rei(Integer.parseInt(info[0]), Integer.parseInt(info[2]), info[3]);
                                peca = new PecasCapturam(0);
                                break;

                            case 1:
                                novaPeca = new Rainha(Integer.parseInt(info[0]), Integer.parseInt(info[2]), info[3]);
                                peca = new PecasCapturam(1);
                                break;

                            case 2:
                                novaPeca = new PoneiMagico(Integer.parseInt(info[0]), Integer.parseInt(info[2]), info[3]);
                                peca = new PecasCapturam(2);
                                break;

                            case 3:
                                novaPeca = new PadreDaVila(Integer.parseInt(info[0]), Integer.parseInt(info[2]), info[3]);
                                peca = new PecasCapturam(3);
                                break;

                            case 4:
                                novaPeca = new TorreH(Integer.parseInt(info[0]), Integer.parseInt(info[2]), info[3], tamanhoTabuleiro);
                                peca = new PecasCapturam(4);
                                break;

                            case 5:
                                novaPeca = new TorreV(Integer.parseInt(info[0]), Integer.parseInt(info[2]), info[3], tamanhoTabuleiro);
                                peca = new PecasCapturam(5);
                                break;

                            case 6:
                                novaPeca = new Lebre(Integer.parseInt(info[0]), Integer.parseInt(info[2]), info[3]);
                                peca = new PecasCapturam(6);
                                break;

                            case 7:
                                novaPeca = new Joker(Integer.parseInt(info[0]), Integer.parseInt(info[2]), info[3]);
                                peca = new PecasCapturam(7);
                                break;

                            default:
                                break;
                        }

                        pecasMalucas.add(novaPeca);

                        boolean pExiste = false;
                        for(PecasCapturam pecaExiste : pecasCapturam){
                            if(pecaExiste.getIdTipoPeca() == peca.getIdTipoPeca()){
                                pExiste = true;
                            }
                        }

                        if(!pExiste) {
                            pecasCapturam.add(peca);
                        }

                    } else {
                        if (info.length > 4) {
                            throw new InvalidSimulatorInputException(countLinha, "DADOS A MAIS (Esperava: 4 ; Obtive: " + info.length + ")");
                        } else if (info.length < 4) {
                            throw new InvalidSimulatorInputException(countLinha, "DADOS A MENOS (Esperava: 4 ; Obtive: " + info.length + ")");
                        }
                    }
                }
                //Quarta parte -> definir a localização de cada peça no tabuleiro
                else if ((countLinha - nPecas - 2) < tamanhoTabuleiro) {
                    info = linha.split(":");
                    if (info.length == tamanhoTabuleiro) {
                        for (int i = 0; i < tamanhoTabuleiro; i++) {
                            if (Integer.parseInt(info[i]) != 0) {
                                for (CrazyPiece crazyPiece : pecasMalucas) {
                                    if (Integer.parseInt(info[i]) == crazyPiece.getId()) {
                                        crazyPiece.estaEmJogo();
                                        crazyPiece.setPosicao(i, countLinha - (nPecas + 2));
                                        crazyPiece.setEmJogo(true);
                                    }
                                }
                            }
                        }
                    } else {
                        if(info.length > tamanhoTabuleiro){
                            throw new InvalidSimulatorInputException(countLinha, "DADOS A MAIS (Esperava: " + tamanhoTabuleiro + "; Obtive: " + info.length + ")");
                        }else if(info.length < tamanhoTabuleiro){
                            throw new InvalidSimulatorInputException(countLinha, "DADOS A MENOS (Esperava: " + tamanhoTabuleiro + "; Obtive: " + info.length + ")");
                        }
                    }
                }
                //Quinta parte -> defenição dos parametros caso seja aberto um jogo antigo
                else {
                    info = linha.split(":");
                    validasPretas = Integer.parseInt(info[1]);
                    capturadasPretas = Integer.parseInt(info[2]);
                    invalidasPretas = Integer.parseInt(info[3]);
                    validasBrancas = Integer.parseInt(info[4]);
                    capturadasBrancas = Integer.parseInt(info[5]);
                    invalidasBrancas = Integer.parseInt(info[6]);
                    semCaptura = Integer.parseInt(info[7]);
                    if(semCaptura != 0){
                        capturaPrevia = true;
                    }
                    turno = validasBrancas + validasPretas;
                }

                countLinha++;
            }

        } catch (FileNotFoundException e){
            throw new IOException();
        }
    }

//Envia o tamanho do tabuleiro (Feito)
    public int getTamanhoTabuleiro(){
        return tamanhoTabuleiro;
    }

//Executa o movimento de uma peça (Resolver o problema da horizontal)
    public boolean processaJogada(int xO, int yO, int xD, int yD){
        //Guardar a posição e estado das peças
        recuperaPecas.clear();
        String linha;
        CrazyPiece origem = receberPeca(xO, yO, pecasMalucas);
        for(CrazyPiece peace:pecasMalucas){
            linha = "" + peace.getId() + ":" + peace.getPosX() + ":" + peace.getPosY();
            recuperaPecas.add(linha);
        }

        //Apaga a informação existente anteriormente
        informacaoEquipas.clear();

        //Guardar a informação da Equipa Preta
        informacaoEquipas.add(Integer.toString(validasPretas));
        informacaoEquipas.add(Integer.toString(capturadasPretas));
        informacaoEquipas.add(Integer.toString(invalidasPretas));

        //Guardar a informação da Equipa Branca
        informacaoEquipas.add(Integer.toString(validasBrancas));
        informacaoEquipas.add(Integer.toString(capturadasBrancas));
        informacaoEquipas.add(Integer.toString(invalidasBrancas));

        //Guardar a informação relativa ao jogo
        turnoAntigo = turno;
        capturasAntigas=semCaptura;
        antigaCapturaPrevia=capturaPrevia;

        if(!(xD == xO && yD == yO)) {
            if (((xO >= 0 && xO < tamanhoTabuleiro) && (yO >= 0 && yO < tamanhoTabuleiro)) &&
                    ((xD >= 0 && xD < tamanhoTabuleiro) && (yD >= 0 && yD < tamanhoTabuleiro))) {

                if (origem != null && origem.getEquipa() == this.getIDEquipaAJogar()) {
                    CrazyPiece destino = receberPeca(xD, yD, pecasMalucas);
                    if (destino == null) {
                        if (origem.podeMover(xD, yD, pecasMalucas, turno, tamanhoTabuleiro)) {
                            origem.setPosicao(xD, yD);

                            if(capturaPrevia) {
                                this.semCaptura++;
                            }

                            if (this.getIDEquipaAJogar() == 10) { //Pretas
                                this.validasPretas++;
                            } else if (this.getIDEquipaAJogar() == 20) { //Brancas
                                this.validasBrancas++;
                            }

                            turno++;

                            for(CrazyPiece novaPeace : pecasMalucas){
                                if(novaPeace.getTipoPeca() == 7){
                                    novaPeace.setTipo(turno);
                                }
                            }

                            origem.setPontos(0);
                            origem.setJogadasValidas();
                            System.out.println(origem.getJogadasValidas());
                            return true;
                        }
                    } else if (!destino.equipaEquals(equipaJogar)) {
                        if (origem.podeMover(xD, yD, pecasMalucas, turno, tamanhoTabuleiro)) {

                            destino.setEmJogo(false);
                            destino.comida();
                            origem.setPosicao(xD, yD);
                            this.semCaptura = 0;
                            this.capturaPrevia = true;
                            if (this.getIDEquipaAJogar() == 10) { //Pretas
                                this.validasPretas++;
                                this.capturadasPretas++;
                            } else if (this.getIDEquipaAJogar() == 20) { //Brancas
                                this.validasBrancas++;
                                this.capturadasBrancas++;
                            }

                            for(PecasCapturam peca:pecasCapturam){
                                if(origem.getTipoPeca() == peca.getIdTipoPeca()){
                                    peca.setnCapturadas();
                                }
                            }

                            turno++;

                            for(CrazyPiece novaPeace : pecasMalucas){
                                if(novaPeace.getTipoPeca() == 7){
                                    novaPeace.setTipo(turno);
                                }
                            }

                            origem.captorou();
                            origem.setJogadasValidas();
                            origem.setPontos(destino.getValorRelativo());
                            System.out.println(origem.getJogadasValidas());
                            return true;
                        }
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

        origem.setJogadasInvalidas();
        return false;
    }

//Devolve a lista de todas asa peças em jogo(Feito)
    public List<CrazyPiece> getPecasMalucas(){
        return pecasMalucas;
    }

//Premite finalizar o jogo se for comprida alguma das condições (Alterar)
    public boolean jogoTerminado(){
        int pecasNaoRei=0;
        int reisBrancos = 0,reisPretos = 0;
        if (pecasMalucas == null){
            reset();
            return true;
        }
        for(CrazyPiece piece:pecasMalucas){
            if(piece.getEmJogo()) {
                if (!piece.comida()) {
                    if (piece.getTipoPeca() == 0) {
                        if (piece.getEquipa() == 10) { //Pecas Pretas
                            reisPretos++;
                        } else if (piece.getEquipa() == 20) { //Pecas Brancas
                            reisBrancos++;
                        }
                    }else{
                        pecasNaoRei++;
                    }
                }
            }
        }
        //Ver vencedor:
        //0 - Pretas
        //1 - Brancas
        //2 - Empate

        if(reisBrancos==0 && reisPretos!=0){
            //PRETAS VENCEM
            this.vencedor=0;
            return true;
        }else if(reisPretos==0 && reisBrancos!=0){
            //BRANCAS VENCEM
            this.vencedor=1;
            return true;
        }else if(reisBrancos==0 && reisPretos==0){
            //EMPATE
            this.vencedor=2;
            return true;
        }else if(reisBrancos==1 && reisPretos==1 && pecasNaoRei==0){
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
        jogoTerminado();
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
        resultados.add(" Capturas: " + this.capturadasPretas);
        resultados.add(" Jogadas válidas: " + this.validasPretas);
        resultados.add(" Tentativas inválidas: " + this.invalidasPretas);

        //Informacao da equipa branca
        resultados.add("Equipa das Brancas");
        resultados.add(" Capturas: " + this.capturadasBrancas + "");
        resultados.add(" Jogadas válidas: " + this.validasBrancas + "");
        resultados.add(" Tentativas inválidas: " + this.invalidasBrancas + "");
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
            equipaJogar=10; //Pretas
        }else{
            equipaJogar=20; //Brancas
        }
        return equipaJogar;
    }

    public static int getEquipaJogar(int turno){
        if(turno%2==0){
            return 10; //Pretas
        }else{
            return 20; //Brancas
        }
    }

//Devolve a peça que se encontra numa determinada coordenada
    public static CrazyPiece receberPeca(int x,int y,List<CrazyPiece> pecasMalucas){

        for(CrazyPiece piece: pecasMalucas) {
            if (piece.posX == x && piece.posY== y) {
                return piece;
            }
        }
        return null;
    }

//Disponibiliza as possíveis jogadas de cada peça
    public List<Comparable> obterSugestoesJogada(int xO, int yO){
        List<Comparable> sugetoesJogada;
        CrazyPiece peace = receberPeca(xO,yO,pecasMalucas);
        if(peace != null && peace.getEquipa() == getEquipaJogar(turno)) {
            sugetoesJogada = peace.sugetaoJogada(xO,yO,pecasMalucas,turno,tamanhoTabuleiro,peace);
        }else{
            sugetoesJogada = new ArrayList<>();
        }

        if(sugetoesJogada.size() ==0){ // colocar o espaço
            sugetoesJogada = new ArrayList<>();
        }

        return sugetoesJogada;
    }

//Premite anular a ultima jogada realizada
    public void anularJogadaAnterior(){
        if(countAnulaJogada<=1) {
            int count = 0;
            //Repor as peças
            for (String linha : recuperaPecas) {
                String info[];
                info = linha.split(":");
                for (CrazyPiece peace : pecasMalucas) {
                    if (peace.getId() == Integer.parseInt(info[0])) {
                        int x = Integer.parseInt(info[1]);
                        int y = Integer.parseInt(info[2]);
                        peace.setPosicao(x, y);
                    }
                }
            }

            for (String equipas : informacaoEquipas) {
//Repor a informação da equipa Preta
                if (count == 0) {
                    validasPretas = Integer.parseInt(equipas);
                } else if (count == 1) {
                    capturadasPretas = Integer.parseInt(equipas);
                } else if (count == 2) {
                    invalidasPretas = Integer.parseInt(equipas);
                }
//Repor a informação da equipa Branca
                else if (count == 3) {
                    validasBrancas = Integer.parseInt(equipas);
                } else if (count == 4) {
                    capturadasBrancas = Integer.parseInt(equipas);
                } else if (count == 5) {
                    invalidasBrancas = Integer.parseInt(equipas);
                }
                count++;
            }

//Repor a informação do jogo
            turno = turnoAntigo;
            semCaptura = capturasAntigas;
            capturaPrevia = antigaCapturaPrevia;

            countAnulaJogada++;
        }
    }

//Grava o jogo como ele se encontra atualmente
    public boolean gravarJogo(File ficheiroDestino){
        String newLine=System.getProperty("line.separator");
        String linhaAdicionar;
        try{
            FileWriter escrever = new FileWriter(ficheiroDestino);

            for(int secao=0;secao<=4;secao++){
                if(secao == 0){
                    escrever.write("" + tamanhoTabuleiro);
                    escrever.write(newLine);
                }

                if(secao == 1){
                    escrever.write("" + pecasMalucas.size());
                    escrever.write(newLine);
                }

                if(secao == 2){
                    for(CrazyPiece peace : pecasMalucas){
                        linhaAdicionar = "" + peace.getId() + ":" + peace.getTipoPeca() + ":" + peace.getEquipa() + ":" + peace.getAlcunha();
                        escrever.write(linhaAdicionar);
                        escrever.write(newLine);
                    }
                }

                if(secao == 3){
                    for(int y=0;y<tamanhoTabuleiro;y++){
                        linhaAdicionar="";
                        for(int x=0;x<tamanhoTabuleiro;x++){
                            CrazyPiece peace = receberPeca(x,y,pecasMalucas);
                            if(x < tamanhoTabuleiro-1){
                                if(peace != null) {
                                    linhaAdicionar = linhaAdicionar + peace.getId() + ":";
                                }else{
                                    linhaAdicionar = linhaAdicionar + "0:";
                                }
                            }
                            else if(x == tamanhoTabuleiro-1){
                                if(peace != null) {
                                    linhaAdicionar = linhaAdicionar + peace.getId();
                                }else{
                                    linhaAdicionar = linhaAdicionar + "0";
                                }
                            }

                        }
                        escrever.write(linhaAdicionar);
                        escrever.write(newLine);
                    }
                }
                else if(secao == 4){
                    linhaAdicionar = getIDEquipaAJogar() + ":" + validasPretas + ":" + capturadasPretas + ":" + invalidasPretas + ":" + validasBrancas + ":" + capturadasBrancas + ":" + invalidasBrancas + ":" + semCaptura;
                    escrever.write(linhaAdicionar);
                }
            }
            escrever.close();
        } catch (IOException e) {
            System.out.println("Deu erro ao guardar o jogo");
        }
        return true;
    }

    public Map<String, List<String>> getEstatisticas(){
        Map<String, List<String>> estatisticas = new HashMap<>();;
        List<String> topInfo = new ArrayList<>();

        //A lista contém as 5 peças com mais capturas, ordenadas da peça com mais capturas para a peça com menos capturas.
        pecasMalucas.stream()
                .sorted((p1,p2) -> p1.getAlcunha().compareTo(p2.getAlcunha()))
                .sorted((p1,p2) -> p2.getNrCapturadas() - p1.getNrCapturadas())
                .limit(5)
                .forEach((p)-> topInfo.add("" + p.getEquipa() + ":" + p.getAlcunha() + ":" + p.getNrPontos() + ":" + p.getNrCapturadas()));

        estatisticas.put("top5Capturas",topInfo);

        //A lista contém as 5 peças com mais pontos, ordenadas da peça com mais pontos para a peça com menos
        pecasMalucas.stream()
                .sorted((p1,p2) -> p1.getAlcunha().compareTo(p2.getAlcunha()))
                .sorted((p1,p2) -> p2.getNrPontos() - p1.getNrPontos())
                .limit(5)
                .forEach((p)-> topInfo.add("" + p.getEquipa() + ":" + p.getAlcunha() + ":" + p.getNrPontos() + ":" + p.getNrCapturadas()));

        estatisticas.put("top5Pontos",topInfo);

        //A lista contém todas as peças com mais do que 5 capturas
        pecasMalucas.stream()
                .filter((p) -> p.getNrCapturadas() > 5)
                .forEach((p)-> topInfo.add("" + p.getEquipa() + ":" + p.getAlcunha() + ":" + p.getNrPontos() + ":" + p.getNrCapturadas()));

        estatisticas.put("pecasMais5Capturas",topInfo);

        //contendo as 3 peças cujo rácio (Nr Jogadas Inválidas / Nr. Jogadas) é maior.
        pecasMalucas.stream()
                .filter((p) -> p.calcularRacio() != 0)
                .sorted((p1,p2) -> Double.compare(p2.calcularRacio(),p1.calcularRacio()))
                .limit(3)
                .forEach((p)-> topInfo.add("" + p.getEquipa() + ":" + p.getAlcunha() + ":" + p.getJogadasInvalidas() + ":" + p.getJogadasValidas()));

        estatisticas.put("3pecasMaisBaralhadas",topInfo);

        //contendo todos os tipos de peças que tiveram capturas, ordenados do tipo que teve mais capturas para o tipo que teve menos capturas.

        pecasCapturam.stream()
                .sorted((p1,p2) -> (p2.getnCapturadas()- p1.getnCapturadas()))
                .forEach((p)-> topInfo.add("" + p.getIdTipoPeca() + ":" + p.getnCapturadas()));

        estatisticas.put("tiposPecaCapturados",topInfo);

        return estatisticas;
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