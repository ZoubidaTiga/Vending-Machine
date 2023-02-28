import enums.Coin;
import enums.Product;
import exceptions.InsufficientChargeException;
import exceptions.NotFoundProductException;
import exceptions.NotSufficientNumberOfCoinsException;
import exceptions.ProductSoldOutException;
import models.VendingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ProductSoldOutException, NotFoundProductException, InsufficientChargeException, NotSufficientNumberOfCoinsException {
        HashMap<Coin,Integer> coins=new HashMap<> ();
        coins.put (Coin.TEN_DAM,3);
        coins.put (Coin.HALF_DAM,3);
        coins.put (Coin.ONE_DAM,3);
        coins.put (Coin.TWO_DAM,5);
        coins.put (Coin.FIVE_DAM,9);


        HashMap<Product,Integer> products=new HashMap<> ();
        products.put (Product.MILKA,3);
        products.put (Product.MIRENDINA,3);
        products.put (Product.TANGO,5);
        products.put (Product.KITKAT,9);
        products.put (Product.SNICKERS,13);



        VendingMachine vendingMachine2 = new VendingMachine (products,coins);

        List<Coin> listCoins=new ArrayList<> ();
        listCoins.add (Coin.TWO_DAM);
        listCoins.add (Coin.TWO_DAM);/*
        listCoins.add (Coin.ONE_DAM);
        listCoins.add (Coin.FIVE_DAM);*/
        System.out.println ("total= "+vendingMachine2.buy (2,listCoins).toString ());

       /* //display products and enter coins
        vendingMachine2.display ();


        String amount="2,4,5,0,0";
        System.out.println ("2,4,5,0,0 --->  2 Half_DAM, 4 One_DAM, 5 Two_DAM");
        System.out.println ("------------------------------------------------");
        String[] coinsString = amount.split(",");
        double[] insertedCoins=convertStringToInt(coinsString);
        List<Coin> listCoins=vendingMachine2.buy (2,insertedCoins);

        System.out.println ("------------------------------------------------");
        System.out.println ("your rest coins is");
        listCoins.forEach (coin -> System.out.println (""+coin.getValue ()));*/

    }
    public static double[] convertStringToInt(String[] str){
        double[] coins = new double[5];

        if(str.length<6){
            for (int i=str.length-1;i<5;i++)
                coins[i] = 0;

        }
        for (int i=0;i<str.length;i++)
            coins[i] = Double.parseDouble (str[i]);

        return coins;
    }
}
