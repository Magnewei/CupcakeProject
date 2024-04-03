package app.cupcake.Controllers;

import app.cupcake.Entities.Order;
import app.cupcake.Persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;
import app.cupcake.Exceptions.DatabaseException;
import app.cupcake.Persistence.OrderMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
    }

    public static void deleteOrder(Context ctx, ConnectionPool connectionPool) {
        try {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));
            OrderMapper.deleteOrderById(orderId,connectionPool);
            List<Order> orderList = OrderMapper.getAllOrders(connectionPool);
            ctx.attribute("orderList",orderList);
            ctx.render("admin.html");
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message",e.getMessage());
            ctx.render("admin.html");
        }
    }

}
