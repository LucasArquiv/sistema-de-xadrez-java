package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;

public class Rei extends PecaDeXadrez {

    private PartidaDeXadrez partidaDeXadrez;

    public Rei(Tabuleiro tabuleiro, Cores cores, PartidaDeXadrez partidaDeXadrez) {
        super(tabuleiro, cores);
        this.partidaDeXadrez = partidaDeXadrez;
    }

    //método para testar condição de Roque
    private boolean testTorreRoque(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
        return p instanceof Torre && p.getCores() == getCores() && p.getContarMovimento() == 0;
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

        //Movimento especial "Roque"
        if (getContarMovimento() == 0 && !partidaDeXadrez.getCheck()) {
            // Roque do lado Rei
            Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if (testTorreRoque(posT1)) {
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
                    mat[posicao.getLinha()][posicao.getColuna() + 2] = true;

                }
            }
            //Roque do lado Rainha
            Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if (testTorreRoque(posT2)) {
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
                    mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
                }
            }
        }

        return mat;
    }
}
