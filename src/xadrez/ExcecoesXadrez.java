package xadrez;

import java.io.Serial;

public class ExcecoesXadrez extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ExcecoesXadrez(String msg){
        super(msg);
    }

}
