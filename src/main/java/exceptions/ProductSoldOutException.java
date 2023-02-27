package exceptions;

public class ProductSoldOutException extends Exception{
    public ProductSoldOutException(String message){
        super(message);
    }
}
