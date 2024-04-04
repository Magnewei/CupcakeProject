package app.cupcake.Entities;

public class Topping {
    private final int price;
    private final String name;
    private int toppingID;
    public Topping(int price, String name, int toppingID) {
        this.price = price;
        this.name = name;
        this.toppingID = toppingID;
    }
    public int getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }
    public int getToppingID() {
        return toppingID;
    }
}
