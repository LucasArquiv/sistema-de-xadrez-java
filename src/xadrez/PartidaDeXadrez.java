package xadrez;


import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

import java.util.ArrayList;
import java.util.List;

public class PartidaDeXadrez {

    private int turno;
    private Cores jogadorAtual;
    private static Tabuleiro tabuleiro;
    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecaCapturadas = new ArrayList<>();

    public PartidaDeXadrez(){
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cores.BRANCO;
        setupInicial();
    }

    public int getTurno() {
        return turno;
    }

    public Cores getJogadorAtual() {
        return jogadorAtual;
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

    public boolean [][] possiveisMovimentos(PosicaoXadrez posicaoOrigem){
        Posicao posicao = posicaoOrigem.paraPosicao();
        validaOrigemPosicao(posicao);
        return tabuleiro.peca(posicao).possiveisMovimentos();
    }

    public PecaDeXadrez executaMovimento(PosicaoXadrez origemPosicao, PosicaoXadrez destinoPosicao){
        Posicao origem = origemPosicao.paraPosicao();
        Posicao destino = destinoPosicao.paraPosicao();
        validaOrigemPosicao(origem);
        validaDestinoPosicao(origem, destino);
        Peca capturaPeca = fazerMover(origem, destino);
        trocaTurno();
        return (PecaDeXadrez)capturaPeca;
    }

    private Peca fazerMover(Posicao origem, Posicao destino){
       Peca p = tabuleiro.removePeca(origem);
       Peca capturaPeca = tabuleiro.removePeca(destino);
       tabuleiro.lugarPecas(p,destino);

       if (capturaPeca != null){
           pecasNoTabuleiro.remove(capturaPeca);
           pecaCapturadas.add(capturaPeca);
       }

       return capturaPeca;
    }

    private void validaOrigemPosicao(Posicao posicao){
        if (!tabuleiro.haUmaPeca(posicao)){
            throw new ExcecoesXadrez("Não existe peça na posição de origem");
        }
        if (jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCores()){
            throw new ExcecoesXadrez("A peça escolhida pertence ao adversário");
        }
        if (!tabuleiro.peca(posicao).possivelMovimentoExistente()){
            throw new ExcecoesXadrez("Não há movimentos possiveis para a peça escolhida");
        }
    }

    private void validaDestinoPosicao(Posicao origem, Posicao destino){
        if (!tabuleiro.peca(origem).possivelMovimento(destino)){
            throw new ExcecoesXadrez("A peça escolhida não pode se mover para a posição de destino");
        }
    }

    private void trocaTurno(){
        turno ++;
        jogadorAtual = (jogadorAtual == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
    }

    private void colocaNovaPeca(char coluna, int linha, PecaDeXadrez peca){
        tabuleiro.lugarPecas(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
        pecasNoTabuleiro.add(peca);

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
