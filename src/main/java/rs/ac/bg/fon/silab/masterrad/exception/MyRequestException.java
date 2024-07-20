package rs.ac.bg.fon.silab.masterrad.exception;

public class MyRequestException extends RuntimeException{

    public MyRequestException(String message){
        super(message);
    }

    public MyRequestException(String message, Throwable cause){
        super(message, cause);
    }

}
