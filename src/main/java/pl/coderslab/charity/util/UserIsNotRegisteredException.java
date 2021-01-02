package pl.coderslab.charity.util;

public class UserIsNotRegisteredException extends RuntimeException {
    public UserIsNotRegisteredException(){
        super();
    }

    public UserIsNotRegisteredException(final String message, final Throwable cause){
        super(message, cause);
    }

    public UserIsNotRegisteredException(final String message){
        super(message);
    }

    public UserIsNotRegisteredException(final Throwable cause){
        super(cause);
    }
}
