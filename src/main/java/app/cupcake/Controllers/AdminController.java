package app.cupcake.Controllers;

import app.cupcake.Entities.Orderline;
import app.cupcake.Entities.User;
import app.cupcake.Exceptions.Persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;
import app.cupcake.Exceptions.DatabaseException;
import app.cupcake.Exceptions.Persistence.OrderMapper;
import app.cupcake.Exceptions.Persistence.UserMapper;
import java.util.List;


public class AdminController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("deleteorder", ctx -> deleteorder(ctx, connectionPool));
        app.post("deleteorderline", ctx -> deleteorderline(ctx, connectionPool));
        app.post("addmoney", ctx -> addmoney(ctx, connectionPool));
        app.post("getallusers", ctx -> getallusers(ctx, connectionPool));
        app.post("removeuser", ctx -> removeUser(ctx, connectionPool));
        app.post("removeorderadmin", ctx -> removeOrder(ctx, connectionPool));
    }

    private static void removeOrder(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        List<Orderline> orderList = OrderMapper.getOrderlinesWithUsername(connectionPool);
        List<User> userList = UserMapper.getAllUsers(connectionPool);
        try {
            int orderlineID = Integer.parseInt(ctx.formParam("orderline_index"));
            OrderMapper.deleteOrderlineById(orderlineID, connectionPool);
            ctx.attribute("userList", userList);
            ctx.attribute("orderlinelist", orderList);
            ctx.attribute("message", "Ordren er blevet slettet.");
            ctx.render("admin.html");

        } catch (NumberFormatException | DatabaseException e) {
            ctx.attribute("userList", userList);
            ctx.attribute("orderlinelist", orderList);
            ctx.attribute("message", "Ordren kunne ikke slettes.");
            ctx.render("admin.html");
        }
    }

    private static void removeUser(Context ctx, ConnectionPool connectionPool) {
        try {
            int userIndex = Integer.parseInt(ctx.formParam("remove_user"));
            List<Orderline> orderList = OrderMapper.getOrderlinesWithUsername(connectionPool);
            ctx.attribute("orderlinelist", orderList);
            UserMapper.deleteUser(userIndex, connectionPool);
            ctx.attribute("message", "Bruger blev slettet.");
            List<User> userList = UserMapper.getAllUsers(connectionPool);
            if (userList.isEmpty()) {
                ctx.attribute("orderlinelist", orderList);
                ctx.attribute("userList", userList);
                ctx.render("admin.html");
            } else {
                ctx.render("admin.html");
            }
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index.html");
        }
    }

    private static void orderlinelist(Context ctx, ConnectionPool connectionPool) {
        try {
            List<Orderline> orderList = OrderMapper.getOrderlinesWithUsername(connectionPool);
            List<User> userList = UserMapper.getAllUsers(connectionPool);
            ctx.attribute("userList", userList);
            ctx.attribute("orderlinelist", orderList);
            ctx.render("admin.html");

        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("admin.html");
        }
    }

    private static void getallusers(Context ctx, ConnectionPool connectionPool) {
        try {
            List<User> userList = UserMapper.getAllUsers(connectionPool);
            ctx.attribute("userList", userList);
            List<Orderline> orderList = OrderMapper.getOrderlinesWithUsername(connectionPool);
            ctx.attribute("userList", userList);
            ctx.attribute("orderlinelist", orderList);
            ctx.render("admin.html");


        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("admin.html");
        }
    }

    public static void deleteorder(Context ctx, ConnectionPool connectionPool) {
        try {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));
            List<User> userList = UserMapper.getAllUsers(connectionPool);
            List<Orderline> orderList = OrderMapper.getOrderlinesWithUsername(connectionPool);
            ctx.attribute("userList", userList);
            ctx.attribute("orderlinelist", orderList);
            ctx.render("admin.html");

        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("admin.html");
        }
    }

    public static void deleteorderline(Context ctx, ConnectionPool connectionPool) {
        try {
            int orderlineId = Integer.parseInt(ctx.formParam("orderLineId"));
            OrderMapper.deleteOrderById(orderlineId, connectionPool);
            List<User> userList = UserMapper.getAllUsers(connectionPool);
            List<Orderline> orderList = OrderMapper.getOrderlinesWithUsername(connectionPool);
            ctx.attribute("userList", userList);
            ctx.attribute("orderlinelist", orderList);
            ctx.render("admin.html");


        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("admin.html");
        }
    }

    public static void addmoney(Context ctx, ConnectionPool connectionPool) {
        try {
            int userId = Integer.parseInt(ctx.formParam("addmoney"));
            int amount = Integer.parseInt(ctx.formParam("money"));
            UserMapper.addmoney(userId, amount, connectionPool);
            List<User> moneyList = UserMapper.getAllUsers(connectionPool);
            ctx.attribute("userList", moneyList);
            List<Orderline> orderList = OrderMapper.getOrderlinesWithUsername(connectionPool);
            ctx.attribute("orderlinelist", orderList);
            ctx.render("admin.html");

        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }

}


