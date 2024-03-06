package jogoTabuleiro;

import java.io.Serial;

public class ExcecoesNoTabuleiro extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    //Recebedor da mensagem
    public ExcecoesNoTabuleiro(String msg){
        super(msg);
    }
}
