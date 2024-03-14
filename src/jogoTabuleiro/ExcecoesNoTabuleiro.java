package jogoTabuleiro;


public class ExcecoesNoTabuleiro extends RuntimeException{
    private static final long serialVersionUID = 1L;

    //Recebedor da mensagem
    public ExcecoesNoTabuleiro(String msg){
        super(msg);
    }
}
