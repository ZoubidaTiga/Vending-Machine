import enums.Coin;
import enums.Product;
import exceptions.InsufficientChargeException;
import exceptions.NotFoundProductException;
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
        //{2,3,4} -> {2 coinHalf_DAM,3 coinHalf_DAM,4 coinHalf_DAM}
        assertThrows(NotFoundProductException.class,() -> vendingMachine.buy (9, new double[]{2, 3, 4}));
    }

    @Test
    public void givenIncorrectProduct_whenBuy_thenTrowsInsufficientChargeException(){
        //{2,3,4,0,0} -> {2 coinHalf_DAM, 3 coinOne_DAM, 4 coinTwo_DAM, 0 coinFive_DAM, 0 coinTenDAM}
        assertThrows(InsufficientChargeException.class,() -> vendingMachine.buy (5, new double[]{2, 3, 4, 0, 0}));
    }

    @Test
    public void givenIncorrectProduct_whenBuy_thenTrowsProductSoldOutException(){
        vendingMachine.getProducts ().replace (Product.MIRENDINA,0);
        assertThrows(ProductSoldOutException.class,() -> vendingMachine.buy (1, new double[]{2, 3, 4, 0, 0}));
    }

    @Test
    public void givenCorrectCoinsAndProduct_whenBuy_thenSucceed() throws ProductSoldOutException, NotFoundProductException, InsufficientChargeException {
        //{2,4,5,0,0} -> 15DAM which means 12 DAM on rest
        List<Coin> expectedCoins=new ArrayList<> ();
        expectedCoins.add (Coin.TEN_DAM); expectedCoins.add (Coin.TWO_DAM);
        assertEquals (expectedCoins,vendingMachine.buy (2,2,4,5,0,0));
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
