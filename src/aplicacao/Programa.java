package aplicacao;

import xadrez.ExcecoesXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
        List<PecaDeXadrez> capturada = new ArrayList<>();

        //imprimindo o tabuleiro e peças
        while (!partidaDeXadrez.getCheckMate()) {
            try {
                UI.telaLimpa();
                UI.impressaoDaPartida(partidaDeXadrez, capturada);
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);

                //Indicador de movimentos Possiveis da peça
                boolean [][] possiveisMovimentos = partidaDeXadrez.possiveisMovimentos(origem);
                UI.telaLimpa();
                UI.impressaoTabuleiro(PartidaDeXadrez.getPecas(), possiveisMovimentos);

                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

                PecaDeXadrez capturaPeca = partidaDeXadrez.executaMovimento(origem, destino);

                if (capturaPeca != null){
                    capturada.add(capturaPeca);
                }
                if (partidaDeXadrez.getPromocao() != null){
                    System.out.print("Escolha a promoção da peça (B/C/T/Q): ");
                    String tipo = sc.nextLine().toUpperCase();
                    while (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") & !tipo.equals("Q")){
                        System.out.print("Valor invalido! Escolha a promoção da peça (B/C/T/Q): ");
                        tipo = sc.nextLine().toUpperCase();
                    }
                    partidaDeXadrez.substituirPromocaoPeca(tipo);
                }
            }
            catch (ExcecoesXadrez | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.telaLimpa();
        UI.impressaoDaPartida(partidaDeXadrez,capturada);
    }
}