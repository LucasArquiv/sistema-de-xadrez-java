package xadrez;

import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public  abstract class PecaDeXadrez extends Peca {

    private Cores cores;

    public PecaDeXadrez(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro);
        this.cores = cores;
    }

    public Cores getCores() {
        return cores;
    }
    public PosicaoXadrez getPosicaoXadrez(){
        return PosicaoXadrez.PosicaoDe(posicao);
    }

    protected boolean existePecaAdversaria(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
        return p != null && p.getCores() != cores;
    }

}
