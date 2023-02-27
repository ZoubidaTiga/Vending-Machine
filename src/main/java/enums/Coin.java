package enums;

import java.util.HashMap;
import java.util.Map;

public enum Coin {
    HALF_DAM(0.5), ONE_DAM(1), TWO_DAM(2), FIVE_DAM(5), TEN_DAM(10),
    ;

    private double value;

    private static final Map<Double, Coin> byValue = new HashMap<Double, Coin> ();

    public static Coin getCoinByValue(int id){
        return byValue.get(id);
    }
    Coin(double value) {
        this.value=value;
    }

    public double getValue() {
        return value;
    }
}
