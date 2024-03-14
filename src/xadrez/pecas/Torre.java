package xadrez.pecas;

import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaDeXadrez;

public class Torre extends PecaDeXadrez {

    public Torre(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro, cores);
    }

    @Override
    public String toString(){
        return "T";
    }

    @Override
    public boolean[][] possiveisMovimentos() {
        //matriz temporariamente
        boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        return mat;
    }
}
