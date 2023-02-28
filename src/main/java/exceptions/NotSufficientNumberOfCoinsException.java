package exceptions;

public class NotSufficientNumberOfCoinsException extends Exception{
    public NotSufficientNumberOfCoinsException(String message){
        super(message);
    }
}
