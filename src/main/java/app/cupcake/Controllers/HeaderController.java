package app.cupcake.Controllers;

import app.cupcake.Persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class HeaderController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("loadshop", ctx -> loadshop(ctx, connectionPool));
        app.post("loadlogin", ctx -> loadlogin(ctx, connectionPool));
        app.post("loadorders", ctx -> loadorders(ctx, connectionPool));
        app.post("loadcart", ctx -> loadcart(ctx, connectionPool));
        app.post("loadadmin", ctx -> loadadmin(ctx, connectionPool));
    }
    public static void loadshop(Context ctx, ConnectionPool connectionPool) {
        try {
            ctx.render("cupcakeshop.html");

        } catch (NumberFormatException e) {
            ctx.attribute("message",e.getMessage());
            ctx.render("cupcakeshop.html");
        }
    }
    public static void loadlogin(Context ctx, ConnectionPool connectionPool) {
        try {
            ctx.render("index.html");

        } catch (NumberFormatException e) {
            ctx.attribute("message",e.getMessage());
            ctx.render("cupcakeshop.html");
        }
    }
    public static void loadorders(Context ctx, ConnectionPool connectionPool) {
        try {
            // TODO: Add en html destination.
           // ctx.render("orders.html");

        } catch (NumberFormatException e) {
            ctx.attribute("message",e.getMessage());
            ctx.render("cupcakeshop.html");
        }
    }

    public static void loadcart(Context ctx, ConnectionPool connectionPool) {
        try {
            ctx.render("orders.html");

        } catch (NumberFormatException e) {
            ctx.attribute("message",e.getMessage());
            ctx.render("cupcakeshop.html");
        }
    }

    public static void loadadmin(Context ctx, ConnectionPool connectionPool) {
        try {
            ctx.render("admin.html");

        } catch (NumberFormatException e) {
            ctx.attribute("message",e.getMessage());
            ctx.render("cupcakeshop.html");
        }
    }
}

