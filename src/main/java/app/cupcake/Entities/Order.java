package app.cupcake.Entities;

import java.util.List;

public class Order {
    private final int orderID;
    private final List<Orderline> orderlineList;
    private final boolean isPaid;
    private final int userId;
    private String username;

    public Order(int orderID, boolean isPaid, List<Orderline> orderLineList, int userId) {
        this.orderID = orderID;
        this.orderlineList = orderLineList;
        this.isPaid = isPaid;
        this.userId = userId;
    }

    public int getOrderID() {
        return orderID;
    }

    public List<Orderline> getOrderlineList() {
        return orderlineList;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        if(username !=null) return username;
        return "";
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
