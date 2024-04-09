package app.cupcake.Controllers;

import app.cupcake.Entities.Orderline;
import app.cupcake.Entities.User;
import app.cupcake.Persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

import static app.cupcake.Controllers.AdminController.reRenderAdmin;
import static app.cupcake.Controllers.CupcakeController.reRenderCupcakeShop;
import static app.cupcake.Controllers.CupcakeController.reRenderShoppingCart;

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
            reRenderCupcakeShop(ctx, connectionPool, "");

        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            reRenderShoppingCart(ctx);
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
            reRenderShoppingCart(ctx);

        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("cupcakeshop.html");
        }
    }

    public static void loadAdmin(Context ctx, ConnectionPool connectionPool) {
        try {
            reRenderAdmin(ctx, connectionPool);
        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }
}

