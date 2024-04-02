package app.cupcake.Entities;

import java.util.List;

public class Orders {
    private int orderID;
    private final List<Cupcake> cupcakeOrders;
    private final boolean isPaid;

    public Orders(List<Cupcake> cupcakeOrders, boolean isPaid) {
        this.cupcakeOrders = cupcakeOrders;
        this.isPaid = isPaid;
    }

    public Orders(int orderID, List<Cupcake> cupcakeOrders, boolean isPaid) {
        this.orderID = orderID;
        this.cupcakeOrders = cupcakeOrders;
        this.isPaid = isPaid;
    }
}
