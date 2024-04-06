package app.cupcake.Controllers;

import app.cupcake.Entities.*;
import app.cupcake.Persistence.ConnectionPool;
import app.cupcake.Persistence.CupcakeMapper;
import app.cupcake.Persistence.OrderMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import app.cupcake.Exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CupcakeController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("ordercupcakes", ctx -> orderCupcakes(ctx, connectionPool));
        app.post("removeorder", ctx -> removeOrder(ctx, connectionPool));
        app.post("seecart", ctx -> seeCart(ctx, connectionPool));
        app.post("paynow", ctx -> payNow(ctx, connectionPool));
        app.post("paylater", ctx -> payLater(ctx, connectionPool));
    }

    public static void payLater(Context ctx, ConnectionPool connectionPool) {
/*
        try {

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index");
        }

 */
    }

    public static void payNow(Context ctx, ConnectionPool connectionPool) {
/*
        try {

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index");
        }

 */
    }

    public static void removeOrder(Context ctx, ConnectionPool connectionPool) {
        int orderlineID = Integer.parseInt(ctx.formParam("orderline_id"));

        try {
            User user = ctx.sessionAttribute("currentUser");
            OrderMapper.deleteOrderlineById(orderlineID, connectionPool);
            List<Orderline> orderlineList = OrderMapper.getOrderLinesByUserId(user.getUserID(), connectionPool);
            ctx.attribute("orderlineList", orderlineList);
            ctx.render("shoppingcart.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index");
        }
    }

    public static void seeCart(Context ctx, ConnectionPool connectionPool) {
        User user = ctx.sessionAttribute("currentUser");

        try {
            //List<Orderline> orderlineList = OrderMapper.getOrderLinesByUserId(user.getUserID() ,connectionPool);
            List<Orderline> orderlineList = ctx.sessionAttribute("orderlineList");
            ctx.attribute("orderlineList", orderlineList);
            ctx.render("shoppingcart.html");

        } catch (RuntimeException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index");
        }
    }

    public static void orderCupcakes(Context ctx, ConnectionPool connectionPool) {
        List<Orderline> orderList = new ArrayList<>();

        try {
            Topping topping = CupcakeMapper.getToppingByName(ctx.formParam("topValue"), connectionPool);
            Bottom bottom = CupcakeMapper.getBottomByName(ctx.formParam("bottomValue"), connectionPool);
            int cupcakeID = CupcakeMapper.getCupcakeIDByPartIDs(topping, bottom, connectionPool);
            int amount = Integer.parseInt(ctx.formParam("amountValue"));

            Orderline orderline = new Orderline(amount, new Cupcake(bottom, topping, cupcakeID));
            orderList.add(orderline);

            List<Orderline> sessionList = ctx.sessionAttribute("orderlineList");
            if (sessionList != null) orderList.addAll(sessionList);

            ctx.sessionAttribute("orderlineList", orderList);
            ctx.attribute("orderlineList", orderList);
            ctx.render("shoppingcart.html");

        } catch (RuntimeException | DatabaseException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index.html");
        }
    }
}
