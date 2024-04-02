package app.cupcake.Entities;

public class cupcakeTop {
    private final int topPrice;
    private final String topName;
    private int topID;

    public cupcakeTop(int topPrice, String topName) {
        this.topPrice = topPrice;
        this.topName = topName;
    }

    public cupcakeTop(int topPrice, String topName, int topID) {
        this.topPrice = topPrice;
        this.topName = topName;
        this.topID = topID;
    }

    public int getTopPrice() {
        return topPrice;
    }

    public String getTopName() {
        return topName;
    }

    public int getTopID() {
        return topID;
    }
}
