package models;

import enums.Coin;
import exceptions.NotSufficientNumberOfCoinsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Change {

    private double numberHalfDHCoins;
    private double numberOneDHCoins;
    private double numberTwoDHCoins;
    private double numberFiveDHCoins;
    private double numberTenDHCoins;

    public Change(){

    }

    public double getEnterAmount(List<Coin> enterAmount){

        double total=0;
        for (Coin coin: enterAmount){
            total+=coin.getValue ();
        }
        return total;
/*
        total+=enterAmount[0] * Coin.HALF_DAM.getValue ();
        total+=enterAmount[1]  * Coin.ONE_DAM.getValue ();
        total+=enterAmount[2] * Coin.TWO_DAM.getValue ();
        total+=enterAmount[3]  * Coin.FIVE_DAM.getValue ();
        total+=enterAmount[4]  * Coin.TEN_DAM.getValue ();

        return  total;*/
    }


    public List<Coin> getChange(double rest, HashMap<Coin, Integer> coins) throws NotSufficientNumberOfCoinsException {
        Validator validator=new Validator ();
        List<Coin> changeCoins =new ArrayList<> ();
        while (rest>0){

            if(rest>=Coin.TEN_DAM.getValue () && coins.get (Coin.TEN_DAM)>0){
                rest-=Coin.TEN_DAM.getValue ();
                coins.replace (Coin.TEN_DAM,coins.get (Coin.TEN_DAM)-1);
                changeCoins.add (Coin.TEN_DAM);
            }
            else if(rest>=Coin.FIVE_DAM.getValue () && coins.get (Coin.FIVE_DAM)>0){
                rest-=Coin.FIVE_DAM.getValue ();
                coins.replace (Coin.FIVE_DAM,coins.get (Coin.FIVE_DAM)-1);
                changeCoins.add (Coin.FIVE_DAM);
            }
            else if(rest>=Coin.TWO_DAM.getValue () && coins.get (Coin.TWO_DAM)>0){
                rest-=Coin.TWO_DAM.getValue ();
                coins.replace (Coin.TWO_DAM,coins.get (Coin.TWO_DAM)-1);
                changeCoins.add (Coin.TWO_DAM);
            }else if(rest>=Coin.ONE_DAM.getValue () && coins.get (Coin.ONE_DAM)>0){
                rest-=Coin.ONE_DAM.getValue ();
                coins.replace (Coin.ONE_DAM,coins.get (Coin.ONE_DAM)-1);
                changeCoins.add (Coin.ONE_DAM);
            }else if(rest>=Coin.HALF_DAM.getValue () && coins.get (Coin.HALF_DAM)>0){
                rest-=Coin.HALF_DAM.getValue ();
                coins.replace (Coin.HALF_DAM,coins.get (Coin.HALF_DAM)-1);
                changeCoins.add (Coin.HALF_DAM);
            }
        }
        return validator.getCoinsToReturnRest(coins,changeCoins);
    }
}
