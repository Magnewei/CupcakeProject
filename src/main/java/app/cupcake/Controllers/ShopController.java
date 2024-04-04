package app.cupcake.Controllers;

import app.cupcake.Entities.Orderline;
import app.cupcake.Entities.User;
import app.cupcake.Exceptions.DatabaseException;
import app.cupcake.Persistence.ConnectionPool;
import app.cupcake.Persistence.OrderMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

public class ShopController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("removeorder", ctx -> removeOrder(ctx, connectionPool));
    }

    public static void removeOrder(Context ctx, ConnectionPool connectionPool) {
        int orderlineID = Integer.parseInt(ctx.formParam("orderline_id"));

        try {
            User user = ctx.sessionAttribute("currentUser");
            OrderMapper.deleteOrderlineById(orderlineID, connectionPool);
            List<Orderline> orderlineList = OrderMapper.getOrderLinesByUserId(user.getUserID(), connectionPool);
            ctx.attribute("orderlineList", orderlineList);
            ctx.render("orders.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index");
        }
    }



    public static void seeCart(Context ctx, ConnectionPool connectionPool) {
        User user = ctx.sessionAttribute("currentUser");

        try {
            List<Orderline> orderlineList = OrderMapper.getOrderLinesByUserId(user.getUserID() ,connectionPool);
            ctx.attribute("orderlineList", orderlineList);
            ctx.render("orders.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index");
        }
    }

}
