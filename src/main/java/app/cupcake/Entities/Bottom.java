package app.cupcake.Entities;

public class Bottom {
    private final int price;
    private final String name;
    private int bottomID;


    public Bottom(int price, String name, int bottomID) {
        this.price = price;
        this.name = name;
        this.bottomID = bottomID;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getBottomID() {
        return bottomID;
    }
}
