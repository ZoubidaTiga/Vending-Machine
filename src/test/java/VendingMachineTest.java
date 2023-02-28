import enums.Coin;
import enums.Product;
import exceptions.InsufficientChargeException;
import exceptions.NotFoundProductException;
import exceptions.NotSufficientNumberOfCoinsException;
import exceptions.ProductSoldOutException;
import models.VendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VendingMachineTest {

    VendingMachine vendingMachine;
    @BeforeEach
    public void setUp(){
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

        vendingMachine = new VendingMachine (products,coins);
    }

     @Test
    public void givenIncorrectProduct_whenBuy_thenTrowsNotFoundProductException(){
         List<Coin> listCoins=new ArrayList<> ();
         listCoins.add (Coin.TWO_DAM);
         listCoins.add (Coin.TWO_DAM);
         assertThrows(NotFoundProductException.class,() -> vendingMachine.buy (9,listCoins));
    }
    @Test
    public void givenIncorrectCoins_whenBuy_thenTrowsInsufficientChargeException(){
        List<Coin> listCoins=new ArrayList<> ();
        listCoins.add (Coin.TWO_DAM);
        listCoins.add (Coin.TWO_DAM);
        listCoins.add (Coin.ONE_DAM);
        listCoins.add (Coin.FIVE_DAM);
        assertThrows(InsufficientChargeException.class,() -> vendingMachine.buy (5, listCoins));
    }

    @Test
    public void givenIncorrectProduct_whenBuy_thenTrowsProductSoldOutException(){
        vendingMachine.getProducts ().replace (Product.MIRENDINA,0);
        List<Coin> listCoins=new ArrayList<> ();
        listCoins.add (Coin.TWO_DAM);
        listCoins.add (Coin.TWO_DAM);
        assertThrows(ProductSoldOutException.class,() -> vendingMachine.buy (1, listCoins));
    }

    @Test
    public void givenCorrectCoinsAndProduct_whenBuy_thenSucceed() throws ProductSoldOutException, NotFoundProductException, InsufficientChargeException, NotSufficientNumberOfCoinsException {
        List<Coin> listCoins=new ArrayList<> ();
        listCoins.add (Coin.TWO_DAM);
        listCoins.add (Coin.TWO_DAM);
        listCoins.add (Coin.ONE_DAM);
        listCoins.add (Coin.FIVE_DAM);
        List<Coin> expectedCoins=new ArrayList<> ();
        expectedCoins.add (Coin.FIVE_DAM); expectedCoins.add (Coin.TWO_DAM);
        assertEquals (expectedCoins,vendingMachine.buy (2,listCoins));
    }

    @Test
    public void givenCorrectProduct_whenAddProduct_thenSucceed() {
        int expectedQuantity= 9;
        Product product=vendingMachine.addProduct (2,4);
        assertEquals (expectedQuantity,vendingMachine.getProducts ().get (product));
    }

    @Test
    public void givenCorrectProduct_whenRemoveProduct_thenSucceed() {
        //remove and return this product
        assertEquals (Product.MIRENDINA, vendingMachine.removeProduct (1));
        assertEquals (4, vendingMachine.getProducts ().size ());
    }

    @Test
    public void givenCorrectCoin_whenAddCoin_thenSucceed() {
        int expectedQuantity= 8;
        Coin coin=vendingMachine.addCoins (2,4);
        assertEquals (expectedQuantity,vendingMachine.getCoins ().get (coin));
    }







}
