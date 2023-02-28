package models;

import enums.Coin;
import enums.Product;
import exceptions.InsufficientChargeException;
import exceptions.NotFoundProductException;
import exceptions.NotSufficientNumberOfCoinsException;
import exceptions.ProductSoldOutException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VendingMachine {
    private HashMap<Product,Integer> products;
    private HashMap<Coin,Integer> coins;

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
        System.out.println("Please enter coins : ");
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
    public List<Coin> buy(int idOfSelectedProduct, List<Coin> coins) throws NotFoundProductException, ProductSoldOutException, InsufficientChargeException, NotSufficientNumberOfCoinsException {
        Product selectedProduct = Product.getProductById (idOfSelectedProduct);
        Validator validator=new Validator ();
        validator.checkExistenceOfProduct (products,selectedProduct); //validate existence of selected product
        validator.checkSufficientAmount (selectedProduct, coins); //verify if charge is sufficient
        Change change1=new Change ();
        double rest= change1.getEnterAmount (coins) - selectedProduct.getPrice ();
        products.replace (selectedProduct,products.get (selectedProduct)-1);
        return change1.getChange(rest, this.coins);
    }
    public List<Coin> refundButton(){
        List<Coin> returnCoins= new ArrayList<> ();
        /*for (int i=0;i<this.insertedCoins.length;i++){
            while (this.insertedCoins[i]>0)
            {
                returnCoins.add (Coin.getCoinByValue (i+1));
                this.insertedCoins[i]--;
            }
        }*/
        return returnCoins;
    }

    public void reset(){
        this.products=null;
        this.coins=null;
    }







}
