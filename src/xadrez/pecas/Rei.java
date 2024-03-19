package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaDeXadrez;

public class Rei extends PecaDeXadrez {

    public Rei(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro, cores);
    }

    @Override
    public String toString(){
        return "R";
    }

    private boolean podeMover(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
        return p == null || p.getCores() != getCores();
    }

    @Override
    public boolean[][] possiveisMovimentos() {
        //matriz temporariamente
        boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //Para cima
        p.setValues(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().posicaoExistente(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Para baixo
        p.setValues(posicao.getLinha() + 1, posicao.getColuna() );
        if (getTabuleiro().posicaoExistente(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Esquerda
        p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Direita
        p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //diagonais
        //Noroeste
        p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Nordeste
        p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Sudoeste
        p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Sudeste
        p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        return mat;
    }
}
