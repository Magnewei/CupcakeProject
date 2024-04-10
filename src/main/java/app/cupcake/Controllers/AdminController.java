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
        app.post("addmoney", ctx -> addMoney(ctx, connectionPool));
        app.post("removeuser", ctx -> removeUser(ctx, connectionPool));
        app.post("removeorderline", ctx -> removeOrderline(ctx, connectionPool));
    }

    public static void removeOrderline(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        try {
            int orderlineID = Integer.parseInt(ctx.formParam("orderline_index"));
            OrderMapper.deleteOrderlineById(orderlineID, connectionPool);
            reRenderAdmin(ctx, connectionPool, "Ordren er blevet slettet.");

        } catch (NumberFormatException | DatabaseException e) {
            reRenderAdmin(ctx, connectionPool, "Ordren kunne ikke slettes.");
        }
    }

    public static void removeUser(Context ctx, ConnectionPool connectionPool) {
        try {
            int userIndex = Integer.parseInt(ctx.formParam("remove_user"));
            UserMapper.deleteUser(userIndex, connectionPool);
            reRenderAdmin(ctx, connectionPool, "Bruger blev slettet.");
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index.html");
        }
    }

    // Method adds money to a user.
    public static void addMoney(Context ctx, ConnectionPool connectionPool) {
        try {
            int userId = Integer.parseInt(ctx.formParam("addmoney"));  // Button related to the user, which is receiving money.
            int amount = Integer.parseInt(ctx.formParam("money"));  // Money input
            UserMapper.addMoney(userId, amount, connectionPool);
            reRenderAdmin(ctx, connectionPool, "");
        } catch (DatabaseException | NumberFormatException e) {
            reRenderAdmin(ctx, connectionPool, e.getMessage());
        }
    }

    // DRY render admin page.
    public static void reRenderAdmin(Context ctx, ConnectionPool connectionPool, String message) {
        try {
            List<Orderline> orderList = OrderMapper.getOrderlinesPlusUsername(connectionPool);
            List<User> userList = UserMapper.getAllUsers(connectionPool);
            ctx.attribute("orderlinelist", orderList);
            ctx.attribute("userList", userList);
            ctx.render("admin.html");
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("admin.html");
        }
    }
}


