package rs.ac.bg.fon.silab.masterrad.exception;

public class ExistingEntityException extends RuntimeException{
    public ExistingEntityException(String message){
        super(message);
    }

    public ExistingEntityException(String message, Throwable throwable){
        super(message,throwable);
    }
}