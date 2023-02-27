package models;

import enums.Coin;
import enums.Product;
import exceptions.InsufficientChargeException;
import exceptions.NotFoundProductException;
import exceptions.ProductSoldOutException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VendingMachine {
    private HashMap<Product,Integer> products;
    private HashMap<Coin,Integer> coins;
    private double[] insertedCoins;

    public VendingMachine(HashMap<Product,Integer> products, HashMap<Coin,Integer> coins)
    {
        this.products=products;
        this.coins=coins;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public HashMap<Coin, Integer> getCoins() {
        return coins;
    }


    public void display() {

        System.out.println ("     WELCOME TO THE VENDING MACHINE           ");
        System.out.println ("------------------------------------------------");
        System.out.println ("            Products available:               ");
        System.out.println ("          -----------------------               ");


        for (Product product: products.keySet ()) {

            System.out.println ("     " + product.getId () + "  " + product.getName () + " - Price: " + product.getPrice () + "DAM   ");
        }
        System.out.println ("------------------------------------------------");
        System.out.println("Please enter coins as following: ");
        System.out.println(" num of 0.5 DAM coins,num of 1 DAM coins,num of 2 DAM coins,num of 5 DAM coins,num of 10 DAM coins  ");
    }
    public Product addProduct(int productId, int quantity){
        Product productToAdd = Product.getProductById (productId);

        if(!products.containsKey (productToAdd))
        {
            products.put (productToAdd,quantity);
        }
        products.replace (productToAdd,quantity + products.get (productToAdd));
        return productToAdd;
    }

    public Coin addCoins(int coinId, int quantity){
        Coin coinToAdd = Coin.getCoinByValue (coinId);

        if(!coins.containsKey (coinToAdd))
        {
            coins.put (coinToAdd,quantity);
        }
        coins.replace (coinToAdd,quantity + coins.get (coinToAdd));
        return coinToAdd;
    }

    public Product removeProduct(int productId){
        Product productToAdd = Product.getProductById (productId);

        if(products.containsKey (productToAdd))
        {
            products.remove (productToAdd);
        }
        return productToAdd;
    }
    public List<Coin> buy(int idOfSelectedProduct, double... coins) throws NotFoundProductException, ProductSoldOutException, InsufficientChargeException {
        this.insertedCoins=coins;
        Product selectedProduct = Product.getProductById (idOfSelectedProduct);
        Change change1=new Change ();
        if(!products.containsKey (selectedProduct))
            throw new NotFoundProductException ("error: not found product");
        if(products.get (selectedProduct) == 0)
            throw new ProductSoldOutException ("error: sold out");
        products.replace (selectedProduct,products.get (selectedProduct)-1);
        if(change1.getEtnterAmount (coins)<selectedProduct.getPrice ())
            throw new InsufficientChargeException ("insufficient charge");

        double rest=change1.getEtnterAmount (coins)-selectedProduct.getPrice ();
        List<Coin> changeCoins =new ArrayList<> ();
        while (rest>0){
            if(rest>=Coin.TEN_DAM.getValue () && this.coins.get (Coin.TEN_DAM)>0){
                rest-=Coin.TEN_DAM.getValue ();
                this.coins.replace (Coin.TEN_DAM,this.coins.get (Coin.TEN_DAM)-1);
                changeCoins.add (Coin.TEN_DAM);
            }
            else if(rest>=Coin.FIVE_DAM.getValue () && this.coins.get (Coin.FIVE_DAM)>0){
                rest-=Coin.FIVE_DAM.getValue ();
                this.coins.replace (Coin.FIVE_DAM,this.coins.get (Coin.FIVE_DAM)-1);
                changeCoins.add (Coin.FIVE_DAM);
            }
            else if(rest>=Coin.TWO_DAM.getValue () && this.coins.get (Coin.TWO_DAM)>0){
                rest-=Coin.TWO_DAM.getValue ();
                this.coins.replace (Coin.TWO_DAM,this.coins.get (Coin.TWO_DAM)-1);
                changeCoins.add (Coin.TWO_DAM);
            }else if(rest>=Coin.ONE_DAM.getValue () && this.coins.get (Coin.ONE_DAM)>0){
                rest-=Coin.ONE_DAM.getValue ();
                this.coins.replace (Coin.ONE_DAM,this.coins.get (Coin.ONE_DAM)-1);
                changeCoins.add (Coin.ONE_DAM);
            }else if(rest>=Coin.HALF_DAM.getValue () && this.coins.get (Coin.HALF_DAM)>0){
                rest-=Coin.HALF_DAM.getValue ();
                this.coins.replace (Coin.HALF_DAM,this.coins.get (Coin.HALF_DAM)-1);
                changeCoins.add (Coin.HALF_DAM);
            }
            else {
                refundButton();
            }

        }

        return changeCoins;
    }
    public List<Coin> refundButton(){
        List<Coin> returnCoins= new ArrayList<> ();
        for (int i=0;i<this.insertedCoins.length;i++){
            while (this.insertedCoins[i]>0)
            {
                returnCoins.add (Coin.getCoinByValue (i+1));
                this.insertedCoins[i]--;
            }
        }
        return returnCoins;
    }

    public void reset(){
        this.products=null;
        this.coins=null;
    }





}
