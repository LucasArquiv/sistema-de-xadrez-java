package xadrez;

import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

    private static Tabuleiro tabuleiro;

    public PartidaDeXadrez(){
        tabuleiro = new Tabuleiro(8, 8);
        setupInicial();
    }
    //retorna uma matriz de peças correspondente a partida:
    public static PecaDeXadrez[][] getPecas(){
        //variavel axiliar
        PecaDeXadrez[][] part = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i=0; i<tabuleiro.getLinhas(); i++){
            for (int j=0; j<tabuleiro.getColunas(); j++){
                part[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
            }
        }
        return part;
    }

    //Método responsável por iniciar as peças no tabuleiro
    private void setupInicial(){
        //teste
        tabuleiro.lugarPecas(new Torre(tabuleiro, Cores.BRANCO), new Posicao(2,1));
        tabuleiro.lugarPecas(new Rei(tabuleiro,Cores.PRETO), new Posicao(0,4));
    }
}
