package app.cupcake.Entities;

import app.cupcake.Persistence.CupcakeMapper;

public class Orderline {
    private int orderlineId;
    private int price;
    private int amount;
    private Cupcake cupcake;
    private int orderID;

    public Orderline(int amount, Cupcake cupcake, int orderlineId) {
        this.amount = amount;
        this.cupcake = cupcake;
        this.orderlineId = orderlineId;
        setPrice();
    }

    public Orderline(int amount, Cupcake cupcake) {
        this.amount = amount;
        this.cupcake = cupcake;
        setPrice();
    }

    private void setPrice() {
        int topPrice = cupcake.getTop().getPrice();
        int bottomPrice = cupcake.getBottom().getPrice();
        price = (topPrice + bottomPrice) * amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getOrderlineId() {
        return orderlineId;
    }

    public int getPrice() {
        return price;
    }

    public String getBottomName() {
        return cupcake.getBottom().getName();
    }

    public String getTopName() {
        return cupcake.getTop().getName();
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Cupcake getCupcake() {
        return cupcake;
    }

    public int getOrderID() {
        return orderID;
    }
}
