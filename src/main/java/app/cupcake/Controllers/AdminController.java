package app.cupcake.Controllers;

import app.cupcake.Entities.Orderline;
import app.cupcake.Entities.User;
import app.cupcake.Persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;
import app.cupcake.Exceptions.DatabaseException;
import app.cupcake.Persistence.OrderMapper;
import app.cupcake.Persistence.UserMapper;
import java.util.List;

public class AdminController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("deleteorder", ctx -> deleteorder(ctx, connectionPool));
        app.post("deleteorderline", ctx -> deleteorderline(ctx, connectionPool));
        app.post("addmoney", ctx -> addmoney(ctx, connectionPool));
        app.post("getallusers", ctx -> getallusers(ctx, connectionPool));
        app.post("removeuser", ctx -> removeUser(ctx, connectionPool));
    }

    private static void removeUser(Context ctx, ConnectionPool connectionPool) {
        try {
            int userIndex = Integer.parseInt(ctx.formParam("remove_user"));
            List<Orderline> orderList = OrderMapper.getOrderlinesWithUsername(connectionPool);
            ctx.attribute("orderlinelist", orderList);
            UserMapper.deleteUser(userIndex, connectionPool);
            ctx.attribute("message", "USER DELETED");
            List<User> userList = UserMapper.getAllUsers(connectionPool);
            if (!userList.isEmpty()) {
                ctx.attribute("userList", userList);
                ctx.render("admin");
            } else {

                ctx.render("admin");

            }
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index");
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
            ctx.render("admin");
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
            ctx.render("admin");
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
            ctx.render("admin");
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
            ctx.render("admin");
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
            ctx.render("index");
        }
    }

}


