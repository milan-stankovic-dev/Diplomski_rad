package rs.ac.bg.fon.silab.diplomskirad.exception;

public class MyRequestException extends RuntimeException{

    public MyRequestException(String message){
        super(message);
    }

    public MyRequestException(String message, Throwable cause){
        super(message, cause);
    }

}
