package rs.ac.bg.fon.silab.diplomskirad.exception;

public class CollectionSizeMismatchException extends RuntimeException{

    public CollectionSizeMismatchException(String message){
        super(message);
    }
    public CollectionSizeMismatchException(String message, Throwable source){
        super(message, source);
    }

}
