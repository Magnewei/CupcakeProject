package app.cupcake.Controllers;

import app.cupcake.Entities.*;
import app.cupcake.Persistence.ConnectionPool;
import app.cupcake.Persistence.CupcakeMapper;
import app.cupcake.Persistence.OrderMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import app.cupcake.Exceptions.DatabaseException;
import java.util.ArrayList;
import java.util.List;

public class CupcakeController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("ordercupcakes", ctx -> orderCupcakes(ctx, connectionPool));
        app.post("removeorder", ctx -> removeOrder(ctx, connectionPool));
        app.post("seecart", ctx -> seeCart(ctx, connectionPool));
        app.post("paylater", ctx -> pay(false, ctx, connectionPool));
        app.post("paylater", ctx -> pay(true, ctx, connectionPool));
    }

    public static void pay(boolean payLater, Context ctx, ConnectionPool connectionPool) {
            List <Orderline> orderlineList = ctx.sessionAttribute("orderlineList");
            User user = ctx.sessionAttribute("currentUser");

            //TODO: If user.balance exists, remove on button press.

        try {
            // Insert new order. Get orderID from order. Set orderID for every orderline,
            // so that they're a part of the same order in the database.
            OrderMapper.insertNewOrder(user, payLater, connectionPool);
            int orderID = OrderMapper.getLastOrder(connectionPool);

            for (Orderline orderline : orderlineList) {
                orderline.setOrderID(orderID);
                OrderMapper.insertNewOrderline(orderline, connectionPool);
            }

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index");
        }
    }



    public static void removeOrder(Context ctx, ConnectionPool connectionPool) {
        int orderlineIndex = Integer.parseInt(ctx.formParam("orderline_index"));

        try {
            List<Orderline> orderlineList = ctx.sessionAttribute("orderlineList");
            orderlineList.remove(orderlineIndex);
            ctx.attribute("orderlineList", orderlineList);
            ctx.render("shoppingcart.html");

        } catch (RuntimeException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index");
        }
    }

    public static void seeCart(Context ctx, ConnectionPool connectionPool) {
        User user = ctx.sessionAttribute("currentUser");

        try {
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
