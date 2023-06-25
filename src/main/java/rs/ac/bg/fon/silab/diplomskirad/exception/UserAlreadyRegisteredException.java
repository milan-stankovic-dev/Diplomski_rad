package rs.ac.bg.fon.silab.diplomskirad.exception;

public class UserAlreadyRegisteredException extends Exception{

    public UserAlreadyRegisteredException(String message){
        super(message);
    }

    public UserAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}
