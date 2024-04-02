package app.cupcake.Entities;

public class cupcakeBottom {
    private final int bottomPrice;
    private final String bottomName;
    private int bottomID;

    public cupcakeBottom(int bottomPrice, String bottomName) {
        this.bottomPrice = bottomPrice;
        this.bottomName = bottomName;
    }

    public cupcakeBottom(int bottomPrice, String bottomName, int bottomID) {
        this.bottomPrice = bottomPrice;
        this.bottomName = bottomName;
        this.bottomID = bottomID;
    }

    public int getBottomPrice() {
        return bottomPrice;
    }

    public String getBottomName() {
        return bottomName;
    }

    public int getBottomID() {
        return bottomID;
    }
}
