package app.cupcake.Entities;

public class Cupcake {
    private final Bottom bottom;
    private final Topping top;
    private int cupcakeID;

    public Cupcake(Bottom bottom, Topping top, int cupcakeID) {
        this.bottom = bottom;
        this.top = top;
        this.cupcakeID = cupcakeID;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public Topping getTop() {
        return top;
    }

    public int getCupcakeID() {
        return cupcakeID;
    }
}
