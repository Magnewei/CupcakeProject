package app.cupcake.Entities;

public class Cupcake {
    private final Bottom bottom;
    private final Topping top;
    private int price;
    private int cupcakeID;

    public Cupcake(Bottom bottom, Topping top, int price, int cupcakeID) {
        this.bottom = bottom;
        this.top = top;
        this.price = price;
        this.cupcakeID = cupcakeID;
    }




}
