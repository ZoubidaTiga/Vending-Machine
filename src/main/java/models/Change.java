package models;

import enums.Coin;
import enums.Product;

public class Change {

    private double numberHalfDHCoins;
    private double numberOneDHCoins;
    private double numberTwoDHCoins;
    private double numberFiveDHCoins;
    private double numberTenDHCoins;

    public Change(){

    }

    public double getEtnterAmount(double[] enterAmount){

        double total=0;

        total+=enterAmount[0] * Coin.HALF_DAM.getValue ();
        total+=enterAmount[1]  * Coin.ONE_DAM.getValue ();
        total+=enterAmount[2] * Coin.TWO_DAM.getValue ();
        total+=enterAmount[3]  * Coin.FIVE_DAM.getValue ();
        total+=enterAmount[4]  * Coin.TEN_DAM.getValue ();

        return  total;
    }

}
