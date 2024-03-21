package aplicacao;

import xadrez.Cores;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    //Códigos para impressão no console
    //Cores do texto:
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //Cores do fundo:
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    //método responsavel pela limpeza do console
    public static void telaLimpa() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static PosicaoXadrez lerPosicaoXadrez(Scanner sc){
        try {
            String local = sc.nextLine();
            char coluna = local.charAt(0);
            int linha = Integer.parseInt(local.substring(1));
            return new PosicaoXadrez(coluna, linha);
        }
        catch (RuntimeException e){
            throw new InputMismatchException("Erro ao ler a posição no xadrez. Valores validos são de a1 até h8.");
        }
    }

    public static void impressaoDaPartida(PartidaDeXadrez partidaDeXadrez, List<PecaDeXadrez> capturada){
        impressaoTabuleiro(PartidaDeXadrez.getPecas());
        System.out.println();
        impressaoPecacapturada(capturada);
        System.out.println();
        System.out.println("Turno: " + partidaDeXadrez.getTurno());
        System.out.println("Aguardando jogador: " + partidaDeXadrez.getJogadorAtual());
        if (partidaDeXadrez.getCheck()){
            System.out.println("CHECK!");
        }
    }

    // sobrecarga do método impressaoTabuleiro()
    public static void impressaoTabuleiro(PecaDeXadrez[][] pecas){
        for (int i=0; i< pecas.length; i++){
            System.out.print((8 - i) + " ");
            for (int j=0; j< pecas.length; j++){
                impressaoPeca(pecas[i][j],false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h ");
    }

    public static void impressaoTabuleiro(PecaDeXadrez[][] pecas, boolean [][] possiveisMovimentos){

        for (int i=0; i< pecas.length; i++){
            System.out.print((8 - i) + " ");
            for (int j=0; j< pecas.length; j++){
                impressaoPeca(pecas[i][j], possiveisMovimentos[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h ");
    }


    //Metodo auxiliar para imprimir uma peça
    private static void impressaoPeca(PecaDeXadrez peca, boolean background){
        if (background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (peca.getCores() == Cores.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    //método responsavel por imprimir as peças capturadas
    private static void impressaoPecacapturada(List<PecaDeXadrez> capturada){
        List<PecaDeXadrez> branco = capturada.stream().filter(x -> x.getCores() == Cores.BRANCO).collect(Collectors.toList());
        List<PecaDeXadrez> preto = capturada.stream().filter(x -> x.getCores() == Cores.PRETO).collect(Collectors.toList());
        //Peças brancas
        System.out.println("Peças capturadas:");
        System.out.print("BRANCA: ");
        System.out.print(ANSI_WHITE);
        System.out.print(Arrays.toString(branco.toArray()));
        System.out.println(ANSI_RESET);
        //Peças pretas
        System.out.print("PRETO:  ");
        System.out.print(ANSI_YELLOW);
        System.out.print(Arrays.toString(preto.toArray()));
        System.out.println(ANSI_RESET);


    }


}
