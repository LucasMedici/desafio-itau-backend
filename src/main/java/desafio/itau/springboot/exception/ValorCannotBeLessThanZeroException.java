package desafio.itau.springboot.exception;

public class ValorCannotBeLessThanZeroException extends RuntimeException{
    public ValorCannotBeLessThanZeroException(String message){
        super(message);
    }
}
