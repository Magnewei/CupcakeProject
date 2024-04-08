package app.cupcake.Controllers;

import app.cupcake.Entities.Order;
import app.cupcake.Entities.User;
import app.cupcake.Persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;
import app.cupcake.Exceptions.DatabaseException;
import app.cupcake.Persistence.OrderMapper;
import app.cupcake.Persistence.UserMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


import java.sql.PreparedStatement;
import java.util.List;

public class AdminController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("deleteorder", ctx -> deleteorder(ctx, connectionPool));
        app.post("deleteorderline", ctx -> deleteorderline(ctx, connectionPool));
        app.post("addmoney", ctx -> addmoney(ctx, connectionPool));
    }

    public static void deleteorder(Context ctx, ConnectionPool connectionPool) {
        try {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));
            OrderMapper.deleteOrderById(orderId, connectionPool);
            List<Order> orderList = OrderMapper.getAllOrders(connectionPool);
            ctx.attribute("orderList", orderList);
            ctx.render("admin");

        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("admin");
        }
    }

    public static void deleteorderline(Context ctx, ConnectionPool connectionPool) {
        try {
            int orderlineId = Integer.parseInt(ctx.formParam("orderLineId"));
            OrderMapper.deleteOrderById(orderlineId, connectionPool);
            List<Order> orderlineList = OrderMapper.getAllOrders(connectionPool);
            ctx.attribute("orderList", orderlineList);
            ctx.render("admin");

        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("admin");
        }
    }

    public static void addmoney(Context ctx, ConnectionPool connectionPool) {
        try {
            int userId = Integer.parseInt(ctx.formParam("userId"));
            int amount = Integer.parseInt(ctx.formParam("amount"));
            UserMapper.addmoney(userId, amount, connectionPool);
            ctx.render("admin");

        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("admin");
        }
    }
}


