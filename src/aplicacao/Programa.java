package aplicacao;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();

        //teste
        //imprimindo o tabuleiro e peças
        //logica basica para impressão do movimento das peças
        while (true){
            UI.impressaoTabuleiro(PartidaDeXadrez.getPecas());
            System.out.println();
            System.out.print("Origem: ");
            PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);

            System.out.println();
            System.out.print("Destino: ");
            PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

            PecaDeXadrez capturaPeca = partidaDeXadrez.executaMovimento(origem, destino);
        }



    }
}