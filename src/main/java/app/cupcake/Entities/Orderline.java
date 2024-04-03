package app.cupcake.Entities;

public class Orderline {
    private int orderlineId;
    private int price;
    private int amount;
    private Cupcake cupcake;


    public Orderline(int price, int amount, Cupcake cupcake, int orderlineId) {
        this.price = price;
        this.amount = amount;
        this.cupcake = cupcake;
        this.orderlineId = orderlineId;
    }
}
