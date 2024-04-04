package app.cupcake.Entities;

public class Cupcake {
    private final Bottom bottom;
    private final Topping top;
    private int price;
    private int cupcakeID;

    public Cupcake(Bottom bottom, Topping top, int cupcakeID) {
        this.bottom = bottom;
        this.top = top;
        this.cupcakeID = cupcakeID;
    }

    public Cupcake(Bottom bottom, Topping top, int price, int cupcakeID) {
        this.bottom = bottom;
        this.top = top;
        this.cupcakeID = cupcakeID;
        this.price = price;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public Topping getTop() {
        return top;
    }

    public int getPrice() {
        return price;
    }

    public int getCupcakeID() {
        return cupcakeID;
    }
}
