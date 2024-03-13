package xadrez;

import jogoTabuleiro.ExcecoesNoTabuleiro;

import java.io.Serial;

public class ExcecoesXadrez extends ExcecoesNoTabuleiro {

    @Serial
    private static final long serialVersionUID = 1L;

    public ExcecoesXadrez(String msg){
        super(msg);
    }

}
