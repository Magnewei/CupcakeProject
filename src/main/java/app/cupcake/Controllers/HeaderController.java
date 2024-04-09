package app.cupcake.Controllers;

import app.cupcake.Entities.Orderline;
import app.cupcake.Entities.User;
import app.cupcake.Exceptions.DatabaseException;
import app.cupcake.Exceptions.Persistence.ConnectionPool;
import app.cupcake.Exceptions.Persistence.CupcakeMapper;
import app.cupcake.Exceptions.Persistence.OrderMapper;
import app.cupcake.Exceptions.Persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

public class HeaderController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("loadshop", ctx -> loadshop(ctx, connectionPool));
        app.post("loadlogin", ctx -> loadlogin(ctx));
        app.post("loadcart", ctx -> loadcart(ctx));
        app.post("loadadmin", ctx -> loadAdmin(ctx, connectionPool));
        app.post("loadUser", ctx -> loadUser(ctx));
        app.post("loadAdmin", ctx -> createAdmin(ctx));

    }

    public static void loadUser(Context ctx) {
        try {
            User user = ctx.sessionAttribute("currentUser");
            if (user != null) ctx.attribute("userBalance", user.getBalance());
            ctx.render("createuser.html");

        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }


    public static void createAdmin(Context ctx) {
        try {
            User user = ctx.sessionAttribute("currentUser");
            if (user != null) ctx.attribute("userBalance", user.getBalance());
            ctx.render("createadmin.html");

        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }

    public static void loadshop(Context ctx, ConnectionPool connectionPool) {
        try {
            User user = ctx.sessionAttribute("currentUser");
            if (user != null) ctx.attribute("userBalance", user.getBalance());
            ctx.attribute("bottomList", CupcakeMapper.getAllBottoms(connectionPool));
            ctx.attribute("toppingList", CupcakeMapper.getAllToppings(connectionPool));
            ctx.render("cupcakeshop.html");

        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("cupcakeshop.html");
        }
    }

    public static void loadlogin(Context ctx) {
        try {
            User user = ctx.sessionAttribute("currentUser");
            if (user != null) ctx.attribute("userBalance", user.getBalance());
            ctx.render("index.html");

        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("cupcakeshop.html");
        }
    }

    public static void loadcart(Context ctx) {
        try {
            User user = ctx.sessionAttribute("currentUser");
            List<Orderline> orderlineList = ctx.sessionAttribute("orderlineList");
            if (user != null) ctx.attribute("userBalance", user.getBalance());
            if (orderlineList != null) ctx.attribute("orderlineList", orderlineList);
            ctx.render("shoppingcart");

        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("cupcakeshop.html");
        }
    }

    public static void loadAdmin(Context ctx, ConnectionPool connectionPool) {
        try {
            List<User> userList = UserMapper.getAllUsers(connectionPool);
            List<Orderline> orderList = OrderMapper.getOrderlinesPlusUsername(connectionPool);
            ctx.attribute("userList", userList);
            ctx.attribute("orderlinelist", orderList);
            ctx.render("admin.html");

        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("Index.html");
        }
    }
}

