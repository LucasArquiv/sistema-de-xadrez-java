package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaDeXadrez;

public class Bispo extends PecaDeXadrez {

    public Bispo(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro, cores);
    }

    @Override
    public String toString(){
        return "B";
    }

    @Override
    public boolean[][] possiveisMovimentos() {
        boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        // Noroeste
        p.setValues(posicao.getLinha() - 1 , posicao.getColuna() - 1);
        while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() - 1, p.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Nordeste
        p.setValues(posicao.getLinha() - 1 , posicao.getColuna() + 1);
        while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() - 1, p.getColuna() +  1);
        }
        if (getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Sudeste
        p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() + 1, p.getColuna() +  1);
        }
        if (getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Sudoeste
        p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() + 1, p.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        return mat;
    }
}
