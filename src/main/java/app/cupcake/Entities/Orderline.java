package app.cupcake.Entities;

public class Orderline {
    private int orderlineId;
    private int price;
    private int amount;
    private Cupcake cupcake;
    private String bottomName;
    private String topName;

    public Orderline(int amount, Cupcake cupcake, int orderlineId) {
        this.amount = amount;
        this.cupcake = cupcake;
        this.orderlineId = orderlineId;
        setCupcakeNames();
        setPrice();
    }

    public Orderline(int amount, int price, Cupcake cupcake, int orderlineId) {
        this.amount = amount;
        this.cupcake = cupcake;
        this.orderlineId = orderlineId;
        this.price = price;
    }

    private void setCupcakeNames() {
        if (cupcake != null) {
            topName = cupcake.getTop().getName();
            bottomName = cupcake.getBottom().getName();
        }
    }

    private void setPrice() {
        int topPrice = cupcake.getTop().getPrice();
        int bottomPrice = cupcake.getBottom().getPrice();
        price = topPrice + bottomPrice;
    }

    public int getAmount() {
        return amount;
    }




}


/*
1. Lav cupcake bottoms/top
1.1 Kombiner price for, for at sende videre til cupcake.
1.2 Kombiner navne for at sende videre til cupcake.

2. Lav cupcake


3. Lav orderline
  Orderline price =
  Cupcake price = bottom price + top price;

  Bottom name = cupcake = bottom.getName();
  Top name = cupcake = top.getName();



 */