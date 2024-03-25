package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaDeXadrez;

public class Rainha extends PecaDeXadrez {

    public Rainha(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro, cores);
    }

    @Override
    public String toString(){
        return "Q"; // coloquei Q para Queen = rainha. para diferenciar a letra do R = Rei
    }

    @Override
    public boolean[][] possiveisMovimentos() {
        boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        // Para cima
        p.setValues(posicao.getLinha() - 1 , posicao.getColuna());
        while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
        }
        if (getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Para baixo
        p.setValues(posicao.getLinha() + 1 , posicao.getColuna());
        while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
        }
        if (getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Esquerda
        p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
        while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //Direita
        p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
        while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

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
