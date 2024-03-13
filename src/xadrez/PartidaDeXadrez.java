package xadrez;


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

    private void colocaNovaPeca(char coluna, int linha, PecaDeXadrez peca){
        tabuleiro.lugarPecas(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
    }

    //Método responsável por iniciar as peças no tabuleiro
    private void setupInicial(){
        //teste
        colocaNovaPeca('c', 1, new Torre(tabuleiro, Cores.BRANCO));
        colocaNovaPeca('c', 2, new Torre(tabuleiro, Cores.BRANCO));
        colocaNovaPeca('d', 2, new Torre(tabuleiro, Cores.BRANCO));
        colocaNovaPeca('e', 2, new Torre(tabuleiro, Cores.BRANCO));
        colocaNovaPeca('e', 1, new Torre(tabuleiro, Cores.BRANCO));
        colocaNovaPeca('d', 1, new Rei(tabuleiro, Cores.BRANCO));

        colocaNovaPeca('c', 7, new Torre(tabuleiro, Cores.PRETO));
        colocaNovaPeca('c', 8, new Torre(tabuleiro, Cores.PRETO));
        colocaNovaPeca('d', 7, new Torre(tabuleiro, Cores.PRETO));
        colocaNovaPeca('e', 7, new Torre(tabuleiro, Cores.PRETO));
        colocaNovaPeca('e', 8, new Torre(tabuleiro, Cores.PRETO));
        colocaNovaPeca('d', 8, new Rei(tabuleiro, Cores.PRETO));
    }
}
