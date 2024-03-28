package xadrez;


import jogoTabuleiro.Peca;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.pecas.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaDeXadrez {

    private int turno;
    private Cores jogadorAtual;
    private static Tabuleiro tabuleiro;
    private boolean check;
    private boolean checkMate;
    private PecaDeXadrez enPassantVuneravel;
    private PecaDeXadrez promocao;

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

    public boolean getCheck(){
        return check;
    }

    public boolean getCheckMate(){
        return checkMate;
    }

    public PecaDeXadrez getEnPassantVuneravel() {
        return enPassantVuneravel;
    }

    public PecaDeXadrez getPromocao() {
        return promocao;
    }

    //retorna uma matriz de peças correspondente a partida
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
    //fim

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

        if (testCheck(jogadorAtual)){
            desfazerMovimento(origem, destino, capturaPeca);
            throw new ExcecoesXadrez("Você não pode se colocar em check");
        }

        PecaDeXadrez pecaMovida = (PecaDeXadrez)tabuleiro.peca(destino);

        //Movimento especial "Promotion" = Promoção
        promocao = null;
        if (pecaMovida instanceof Peao){
            if ((pecaMovida.getCores() == Cores.BRANCO && destino.getLinha() == 0)
                    || (pecaMovida.getCores() == Cores.PRETO && destino.getLinha() == 7)){
                promocao = (PecaDeXadrez) tabuleiro.peca(destino);
                promocao = substituirPromocaoPeca("Q");
            }
        }

        check = (testCheck(oponente(jogadorAtual))) ? true : false;

        if (testCheckMate(oponente(jogadorAtual))){
            checkMate = true;
        }
        else {
            trocaTurno();
        }
        //Movimento especial "en passant"
        if (pecaMovida instanceof Peao && (destino.getLinha() == origem.getLinha() - 2
                || destino.getLinha() == origem.getLinha() + 2)) {
            enPassantVuneravel = pecaMovida;
        }
        else {
            enPassantVuneravel = null;
        }
        return (PecaDeXadrez)capturaPeca;
    }

    //Método para substituir o Peão para promoção
    public PecaDeXadrez substituirPromocaoPeca(String tipo){
        if (promocao == null){
            throw new IllegalStateException("Não há peça a ser promovida");
        }
        if (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("Q")) {
            return promocao;
        }
        Posicao pos = promocao.getPosicaoXadrez().paraPosicao();
        Peca p = tabuleiro.removePeca(pos);
        pecasNoTabuleiro.remove(p);

        PecaDeXadrez novaPeca = novaPeca(tipo, promocao.getCores());
        tabuleiro.lugarPecas(novaPeca, pos);
        pecasNoTabuleiro.add(novaPeca);

        return novaPeca;
    }

    //método auxiliar para instanciar uma nova peça para a promoção
    private PecaDeXadrez novaPeca(String tipo, Cores cores){
        if (tipo.equals("B")) return new Bispo(tabuleiro, cores);
        if (tipo.equals("C")) return new Cavalo(tabuleiro, cores);
        if (tipo.equals("Q")) return new Rainha(tabuleiro, cores);
        return new Torre(tabuleiro, cores);
    }

    private Peca fazerMover(Posicao origem, Posicao destino){
       PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removePeca(origem);
       p.aumentarContarMovimento();
       Peca capturaPeca = tabuleiro.removePeca(destino);
       tabuleiro.lugarPecas(p,destino);

       if (capturaPeca != null){
           pecasNoTabuleiro.remove(capturaPeca);
           pecaCapturadas.add(capturaPeca);
       }
        // Tratamento de Roque do lado Rei
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2){
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.removePeca(origemT);
            tabuleiro.lugarPecas(torre,destinoT);
            torre.aumentarContarMovimento();
        }
        // Tratamento de Roque do lado Rainha
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2){
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.removePeca(origemT);
            tabuleiro.lugarPecas(torre,destinoT);
            torre.aumentarContarMovimento();
        }
        // Tratamento de en passant
        if (p instanceof Peao){
            if (origem.getColuna() != destino.getColuna() && capturaPeca == null){
                Posicao posicaoPeao;
                if (p.getCores() == Cores.BRANCO){
                    posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
                }
                else {
                    posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
                }
                capturaPeca = tabuleiro.removePeca(posicaoPeao);
                pecaCapturadas.add(capturaPeca);
                pecasNoTabuleiro.remove(capturaPeca);
            }
        }
       return capturaPeca;
    }

    private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada){
        PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removePeca(destino);
        p.diminuirContarMovimento();
        tabuleiro.lugarPecas(p, origem);

        if (pecaCapturada != null){
            tabuleiro.lugarPecas(pecaCapturada, destino);
            pecaCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }
        // Tratamento de Roque do lado Rei
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2){
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.removePeca(destinoT);
            tabuleiro.lugarPecas(torre,origemT);
            torre.diminuirContarMovimento();
        }
        // Tratamento de Roque do lado Rainha
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2){
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.removePeca(destinoT);
            tabuleiro.lugarPecas(torre,origemT);
            torre.diminuirContarMovimento();
        }
        // Tratamento de en passant
        if (p instanceof Peao){
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassantVuneravel){
                PecaDeXadrez peao = (PecaDeXadrez)tabuleiro.removePeca(destino);
                Posicao posicaoPeao;
                if (p.getCores() == Cores.BRANCO){
                    posicaoPeao = new Posicao(3, destino.getColuna());
                }
                else {
                    posicaoPeao = new Posicao(4, destino.getColuna());
                }
                tabuleiro.lugarPecas(peao, posicaoPeao);
            }
        }
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

    //Devolve o oponente de uma cor
    private Cores oponente(Cores cores){
        return (cores == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
    }
    //fim

    //Método responsavel por localizar o Rei de uma determinada cor
    private PecaDeXadrez rei(Cores cores){
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCores() == cores).collect(Collectors.toList());
        for(Peca p : list){
            if (p instanceof Rei){
                return (PecaDeXadrez)p;
            }
        }
        throw new IllegalStateException("Não existe o "+ cores + " rei no tabuleiro");
    }
    //fim

    private boolean testCheck(Cores cores){
        Posicao posicaoRei =  rei(cores).getPosicaoXadrez().paraPosicao();
        List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCores() == oponente(cores)).collect(Collectors.toList());
        for(Peca p : pecasOponente){
            boolean [][] mat = p.possiveisMovimentos();
            if(mat[posicaoRei.getLinha()][posicaoRei.getColuna()]){
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Cores cores){
        if(!testCheck(cores)){
            return false;
        }
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCores() == cores).collect(Collectors.toList());
        for(Peca p : list){
            boolean [][] mat = p.possiveisMovimentos();
            for (int i=0; i < tabuleiro.getLinhas(); i++){
                for (int j=0; j < tabuleiro.getColunas(); j++){
                    if (mat[i][j]){
                        Posicao origem = ((PecaDeXadrez)p).getPosicaoXadrez().paraPosicao();
                        Posicao destino = new Posicao(i,j);
                        Peca capturaPeca = fazerMover(origem, destino);
                        boolean testCheck = testCheck(cores);
                        desfazerMovimento(origem, destino,capturaPeca);
                        if (!testCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void colocaNovaPeca(char coluna, int linha, PecaDeXadrez peca){
        tabuleiro.lugarPecas(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
        pecasNoTabuleiro.add(peca);

    }

    //Método responsável por iniciar as peças no tabuleiro
    private void setupInicial(){
        colocaNovaPeca('a', 1, new Torre(tabuleiro, Cores.BRANCO));
        colocaNovaPeca('b', 1, new Cavalo(tabuleiro, Cores.BRANCO));
        colocaNovaPeca('c', 1, new Bispo(tabuleiro, Cores.BRANCO));
        colocaNovaPeca('d', 1, new Rainha(tabuleiro, Cores.BRANCO));
        colocaNovaPeca('e', 1, new Rei(tabuleiro, Cores.BRANCO,this));//auto-referência
        colocaNovaPeca('f', 1, new Bispo(tabuleiro, Cores.BRANCO));
        colocaNovaPeca('g', 1, new Cavalo(tabuleiro, Cores.BRANCO));
        colocaNovaPeca('h', 1, new Torre(tabuleiro, Cores.BRANCO));
        colocaNovaPeca('a', 2, new Peao(tabuleiro, Cores.BRANCO,this));
        colocaNovaPeca('b', 2, new Peao(tabuleiro, Cores.BRANCO,this));
        colocaNovaPeca('c', 2, new Peao(tabuleiro, Cores.BRANCO,this));
        colocaNovaPeca('d', 2, new Peao(tabuleiro, Cores.BRANCO,this));
        colocaNovaPeca('e', 2, new Peao(tabuleiro, Cores.BRANCO,this));
        colocaNovaPeca('f', 2, new Peao(tabuleiro, Cores.BRANCO,this));
        colocaNovaPeca('g', 2, new Peao(tabuleiro, Cores.BRANCO,this));
        colocaNovaPeca('h', 2, new Peao(tabuleiro, Cores.BRANCO,this));

        colocaNovaPeca('a', 8, new Torre(tabuleiro, Cores.PRETO));
        colocaNovaPeca('b', 8, new Cavalo(tabuleiro, Cores.PRETO));
        colocaNovaPeca('c', 8, new Bispo(tabuleiro, Cores.PRETO));
        colocaNovaPeca('d', 8, new Rainha(tabuleiro, Cores.PRETO));
        colocaNovaPeca('e', 8, new Rei(tabuleiro, Cores.PRETO,this));//auto-referência
        colocaNovaPeca('f', 8, new Bispo(tabuleiro, Cores.PRETO));
        colocaNovaPeca('g', 8, new Cavalo(tabuleiro, Cores.PRETO));
        colocaNovaPeca('h', 8, new Torre(tabuleiro, Cores.PRETO));
        colocaNovaPeca('a', 7, new Peao(tabuleiro, Cores.PRETO,this));
        colocaNovaPeca('b', 7, new Peao(tabuleiro, Cores.PRETO,this));
        colocaNovaPeca('c', 7, new Peao(tabuleiro, Cores.PRETO,this));
        colocaNovaPeca('d', 7, new Peao(tabuleiro, Cores.PRETO,this));
        colocaNovaPeca('e', 7, new Peao(tabuleiro, Cores.PRETO,this));
        colocaNovaPeca('f', 7, new Peao(tabuleiro, Cores.PRETO,this));
        colocaNovaPeca('g', 7, new Peao(tabuleiro, Cores.PRETO,this));
        colocaNovaPeca('h', 7, new Peao(tabuleiro, Cores.PRETO,this));
    }
    //fim
}
