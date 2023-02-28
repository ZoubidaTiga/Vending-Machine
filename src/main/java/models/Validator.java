package models;

import enums.Coin;
import enums.Product;
import exceptions.InsufficientChargeException;
import exceptions.NotFoundProductException;
import exceptions.NotSufficientNumberOfCoinsException;
import exceptions.ProductSoldOutException;

import java.util.HashMap;
import java.util.List;

public class Validator {

    public void checkExistenceOfProduct(HashMap<Product,Integer> products, Product selectedProduct) throws NotFoundProductException, ProductSoldOutException {
        if(!products.containsKey (selectedProduct))
            throw new NotFoundProductException ("error: not found product");
        if(products.get (selectedProduct) == 0)
            throw new ProductSoldOutException ("error: sold out");
    }

    public void checkSufficientAmount(Product selectedProduct, List<Coin> coins) throws InsufficientChargeException {
        Change change1=new Change ();
        if(change1.getEnterAmount (coins)<selectedProduct.getPrice ())
            throw new InsufficientChargeException ("insufficient charge");
    }

    public List<Coin> getCoinsToReturnRest(HashMap<Coin,Integer> coins, List<Coin> restCoins) throws NotSufficientNumberOfCoinsException {
        Change change=new Change ();
        double sum=0;
        for (Coin coin:coins.keySet ()){
            sum+=coin.getValue ()*coins.get (coin);
        }
        if(sum<change.getEnterAmount (restCoins))
            throw new NotSufficientNumberOfCoinsException ("Not Sufficient Number Of Coins Exception ");
        return restCoins;
    }


}
