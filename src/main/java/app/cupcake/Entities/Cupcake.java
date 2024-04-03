package app.cupcake.Entities;

public class Cupcake {
    private final cupcakeBottom bottom;
    private final cupcakeTop top;
    private String name;
    private int price;
    private int cupcakeID;

    public Cupcake(Bottom bottom, Topping top, int price, int cupcakeID) {
        this.bottom = bottom;
        this.top = top;
        this.name = name;
        this.price = price;
        this.cupcakeID = cupcakeID;
    }

    public Cupcake(cupcakeBottom bottom, cupcakeTop top, String name, int price) {
        this.bottom = bottom;
        this.top = top;
        this.name = name;
        this.price = price;
    }

    public Cupcake(cupcakeBottom bottom, cupcakeTop top, int price) {
        this.bottom = bottom;
        this.top = top;
        this.price = price;
    }

    public Cupcake(cupcakeBottom bottom, cupcakeTop top, String name) {
        this.bottom = bottom;
        this.top = top;
        this.name = name;
    }

    public Cupcake(cupcakeBottom bottom, cupcakeTop top) {
        this.bottom = bottom;
        this.top = top;
    }

    public String getName() {
        if (name == null) {
            return (name = bottom.getBottomName() + " " + top.getTopName());
        }
        return name;

    }

    public int getPrice() {
        if (price == 0) {
            return (price = bottom.getBottomPrice() + top.getTopPrice());
        }
        return price;
    }


}
