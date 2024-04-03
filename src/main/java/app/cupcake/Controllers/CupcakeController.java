package app.cupcake.Controllers;

import app.cupcake.Entities.User;
import app.cupcake.Persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;
import app.cupcake.Exceptions.DatabaseException;

public class CupcakeController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("ordercupcakes", ctx -> buyCupcakes(ctx, connectionPool));
        //app.get("ordercupcakes", ctx -> ctx.render("ordercupcakes.html"));

        app.post("removeOrder", ctx -> removeOrder(ctx, connectionPool));
        app.post("removeOrder", ctx -> seeCart(ctx, connectionPool));

    }

    public static void buyCupcakes(Context ctx, ConnectionPool connectionPool) {
        User user = ctx.sessionAttribute("currentUser");

    }


    public static void seeCart(Context ctx, ConnectionPool connectionPool) {

    }

    public static void removeOrder(Context ctx, ConnectionPool connectionPool) {

    }


}
