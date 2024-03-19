package aplicacao;

import xadrez.ExcecoesXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();

        //teste
        //imprimindo o tabuleiro e peças
        //logica basica para impressão dos movimentos das peças
        while (true) {
            try {
                UI.telaLimpa();
                UI.impressaoTabuleiro(PartidaDeXadrez.getPecas());
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
            } catch (ExcecoesXadrez e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

    }
}