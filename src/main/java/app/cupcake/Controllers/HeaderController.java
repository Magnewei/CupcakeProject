package app.cupcake.Controllers;

import app.cupcake.Persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class HeaderController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("loadshop", ctx -> loadshop(ctx, connectionPool));
        app.post("loadlogin", ctx -> loadlogin(ctx, connectionPool));
        app.post("loadorder", ctx -> loadorder(ctx, connectionPool));
        app.post("loadcart", ctx -> loadcart(ctx, connectionPool));
        app.post("loadadmin", ctx -> loadadmin(ctx, connectionPool));
    }
    public static void loadshop(Context ctx, ConnectionPool connectionPool) {}
    public static void loadlogin(Context ctx, ConnectionPool connectionPool) {}
    public static void loadorder(Context ctx, ConnectionPool connectionPool) {}
    public static void loadcart(Context ctx, ConnectionPool connectionPool) {}
    public static void loadadmin(Context ctx, ConnectionPool connectionPool) {}
}
