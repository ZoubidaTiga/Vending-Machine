package enums;

import java.util.HashMap;
import java.util.Map;

public enum Product {
    MIRENDINA(1,"Mirendina",5), TANGO(2,"Tango",3), KITKAT(3,"Kitkat",13), SNICKERS(4,"Snickers",15), MILKA(5,"Milka",26);


    private int id;
    private String name;
    private int price;

    private static final Map<Integer, Product> byId = new HashMap<Integer, Product> ();
    static {
        for (Product e : Product.values()) {
            if (byId.put(e.getId(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getId());
            }
        }
    }

    Product( int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public static Product getProductById(int id){
        return byId.get(id);
    }


}
