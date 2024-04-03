package app.cupcake.Entities;

import java.util.List;

public class Order {
    private int orderID;
    private final List<Orderline> orderlineList;
    private final boolean isPaid;
    private final int userId;

    public Order(int orderID, boolean isPaid, List<Orderline> orderLineList, int userId) {
        this.orderID = orderID;
        this.orderlineList = orderLineList;
        this.isPaid = isPaid;
        this.userId = userId;
    }
}
