package rs.ac.bg.fon.silab.masterrad.exception;

public class UserAlreadyRegisteredException extends RuntimeException{

    public UserAlreadyRegisteredException(String message){
        super(message);
    }

    public UserAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}
