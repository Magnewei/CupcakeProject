package app.cupcake.Entities;

public class Orderline {
    //orderline objektet
    private String username;
    private boolean isPaidFor;

    public void setUsername(String setter) {
        username = setter;
    }

    public String getUsername() {
        return username;
    }

    public void setIsPaidFor(boolean setter) {
        isPaidFor = setter;
    }

    public boolean getIsPaidFor() {
        return isPaidFor;
    }
    private int orderlineId;
    private int price;
    private int amount;
    private Cupcake cupcake;
    private int orderID;

    private String cupcakeName;

    public Orderline(int amount, Cupcake cupcake, int orderlineId) {
        this.amount = amount;
        this.cupcake = cupcake;
        this.orderlineId = orderlineId;
        setPrice();
        setCupcakeName();
    }

    public Orderline(int amount, Cupcake cupcake) {
        this.amount = amount;
        this.cupcake = cupcake;
        setPrice();
        setCupcakeName();
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

    public String getCupcakeName() {
        return cupcakeName;
    }

    public void setCupcakeName(){
        cupcakeName = cupcake.getBottom().getName()+" "+cupcake.getTop().getName();
    }
}

