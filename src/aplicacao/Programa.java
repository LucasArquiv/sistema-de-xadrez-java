package aplicacao;

import xadrez.PartidaDeXadrez;

public class Programa {
    public static void main(String[] args) {

        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();

        //teste
        //imprimindo o tabuleiro e peças
        UI.printTabuleiro(PartidaDeXadrez.getPecas());


    }
}