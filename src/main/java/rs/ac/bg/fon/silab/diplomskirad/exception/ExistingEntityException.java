package rs.ac.bg.fon.silab.diplomskirad.exception;

public class ExistingEntityException extends RuntimeException{
    public ExistingEntityException(String message){
        super(message);
    }

    public ExistingEntityException(String message, Throwable throwable){
        super(message,throwable);
    }
}