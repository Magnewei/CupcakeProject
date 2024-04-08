package app.cupcake.Controllers;

import app.cupcake.Entities.Orderline;
import app.cupcake.Entities.User;
import app.cupcake.Exceptions.DatabaseException;
import app.cupcake.Persistence.ConnectionPool;
import app.cupcake.Persistence.CupcakeMapper;
import app.cupcake.Persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class HeaderController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("loadshop", ctx -> loadshop(ctx, connectionPool));
        app.post("loadlogin", ctx -> loadlogin(ctx, connectionPool));
        app.post("loadcart", ctx -> loadcart(ctx, connectionPool));
        app.post("loadadmin", ctx -> loadAdmin(ctx, connectionPool));
        app.post("loadUser", ctx -> loadUser(ctx, connectionPool));
        app.post("loadAdmin", ctx -> createAdmin(ctx, connectionPool));

    }

    public static void loadUser(Context ctx, ConnectionPool connectionPool) {
        try {
            ctx.render("createuser.html");

        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("Index.html");
        }
    }


    public static void createAdmin(Context ctx, ConnectionPool connectionPool) {
        try {
            ctx.render("createadmin.html");

        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");

        }
    }


    public static void loadshop(Context ctx, ConnectionPool connectionPool) {
        try {
            ctx.attribute("bottomList", CupcakeMapper.getAllBottoms(connectionPool));
            ctx.attribute("toppingList", CupcakeMapper.getAllToppings(connectionPool));
            ctx.render("cupcakeshop.html");

        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("cupcakeshop.html");
        }
    }

    public static void loadlogin(Context ctx, ConnectionPool connectionPool) {
        try {
            ctx.sessionAttribute("currentUser");
            ctx.render("index.html");

        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("cupcakeshop.html");
        }
    }

    public static void loadcart(Context ctx, ConnectionPool connectionPool) {
        try {
            List<Orderline> orderlineList = ctx.sessionAttribute("orderlineList");
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
            ctx.attribute("userList", userList);
            ctx.render("admin.html");


        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("Index.html");

        }
    }

}

