package rs.ac.bg.fon.silab.masterrad.exception;

public class UserNotVerifiedException extends RuntimeException{

    public UserNotVerifiedException(String message){
        super(message);
    }

    public UserNotVerifiedException(String message, Throwable cause){
        super(message, cause);
    }
}
