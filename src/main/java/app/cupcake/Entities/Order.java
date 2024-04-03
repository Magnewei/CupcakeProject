package app.cupcake.Entities;

import java.util.List;

public class Order {
    private int orderID;
    private final List<Cupcake> cupcakeOrders;
    private final boolean isPaid;

    public Order(int orderID, boolean isPaid, List<Orderline> orderLineList, int userId) {
        this.orderID = orderID;
        this.cupcakeOrders = cupcakeOrders;
        this.isPaid = isPaid;
    }
}
