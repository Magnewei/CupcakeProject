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
        app.post("addmoney", ctx -> addmoney(ctx, connectionPool));
        app.post("removeuser", ctx -> removeUser(ctx, connectionPool));
        app.post("removeorderline", ctx -> removeOrderline(ctx, connectionPool));
    }

    private static void removeOrderline(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        try {
            int orderlineID = Integer.parseInt(ctx.formParam("orderline_index"));
            OrderMapper.deleteOrderlineById(orderlineID, connectionPool);
            ctx.attribute("message", "Ordren er blevet slettet.");
            reRenderAdmin(ctx, connectionPool);

        } catch (NumberFormatException | DatabaseException e) {
            ctx.attribute("message", "Ordren kunne ikke slettes.");
            reRenderAdmin(ctx, connectionPool);
        }
    }

    private static void removeUser(Context ctx, ConnectionPool connectionPool) {
        try {
            int userIndex = Integer.parseInt(ctx.formParam("remove_user"));
            UserMapper.deleteUser(userIndex, connectionPool);
            ctx.attribute("message", "Bruger blev slettet.");
            reRenderAdmin(ctx, connectionPool);
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index.html");
        }
    }

    // Method adds money to a user.
    public static void addmoney(Context ctx, ConnectionPool connectionPool) {
        try {
            int userId = Integer.parseInt(ctx.formParam("addmoney"));  // Button related to the user, which is receiving money.
            int amount = Integer.parseInt(ctx.formParam("money"));  // Money input
            UserMapper.addmoney(userId, amount, connectionPool);
            reRenderAdmin(ctx, connectionPool);
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("admin.html");
        }
    }

    // DRY render admin page.
    public static void reRenderAdmin(Context ctx, ConnectionPool connectionPool) {
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


