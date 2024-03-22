package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaDeXadrez;

public class Peao extends PecaDeXadrez {

    public Peao(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro, cores);
    }
    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possiveisMovimentos() {
        boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        if (getCores() == Cores.BRANCO) {
            //movimento de uma casa
            p.setValues(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            //movimento de duas casas
            p.setValues(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)
                    && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().haUmaPeca(p2) && getContarMovimento() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            //diagonal esquerda
            p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            //diagonal direita
            p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
        }
        else {
            //movimento de uma casa
            p.setValues(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            //movimento de duas casas
            p.setValues(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)
                    && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().haUmaPeca(p2) && getContarMovimento() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            //diagonal esquerda
            p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            //diagonal direita
            p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
        }
        return mat;
    }
}

