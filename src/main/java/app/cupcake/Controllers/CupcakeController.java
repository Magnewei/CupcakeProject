package app.cupcake.Controllers;

import app.cupcake.Entities.*;
import app.cupcake.Persistence.ConnectionPool;
import app.cupcake.Persistence.CupcakeMapper;
import app.cupcake.Persistence.OrderMapper;
import app.cupcake.Persistence.UserMapper;
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
        app.post("paynow", ctx -> pay(false, ctx, connectionPool));
        app.post("paylater", ctx -> pay(true, ctx, connectionPool));
    }

    public static void pay(boolean payLater, Context ctx, ConnectionPool connectionPool) {
        List<Orderline> orderlineList = ctx.sessionAttribute("orderlineList");
        User user = ctx.sessionAttribute("currentUser");
        int price = 0;

        try {
            // Insert new order. Get orderID from order. Set orderID for every orderline,
            // so that they're a part of the same order in the database.
            OrderMapper.insertNewOrder(user, payLater, connectionPool);
            int orderID = OrderMapper.getLastOrder(connectionPool);

            for (Orderline orderline : orderlineList) {
                orderline.setOrderID(orderID);
                OrderMapper.insertNewOrderline(orderline, connectionPool);
                price += orderline.getPrice();
            }

            if (payLater) {
                orderlineList.clear();
                ctx.render("shoppingcart");

            } else if (!payLater && user.getBalance() >= price) {
                UserMapper.removeMoney(user, price, connectionPool);
                ctx.render("shoppingcart");
                orderlineList.clear();

                // If paylater == false but user doesn't have enough balance.
            } else {
                ctx.attribute("message", "Du har ikke nok penge på din konto.");
                ctx.render("shoppingcart");
            }

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("shoppingcart");
        }
    }

    public static void removeOrder(Context ctx, ConnectionPool connectionPool) {
        int orderlineIndex = Integer.parseInt(ctx.formParam("orderline_index"));

        try {
            List<Orderline> orderlineList = ctx.sessionAttribute("orderlineList");
            orderlineList.remove(orderlineIndex);

            if (orderlineList.size() >= 0) {
                ctx.attribute("orderlineList", orderlineList);
                ctx.render("shoppingcart");
            } else {
                ctx.render("cupcakeShop");
            }
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
            ctx.render("shoppingcart");

        } catch (RuntimeException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index");
        }
    }

    public static void orderCupcakes(Context ctx, ConnectionPool connectionPool) {
        List<Orderline> orderList = new ArrayList<>();
        String topValue = ctx.formParam("topValue");
        String bottomValue = ctx.formParam("bottomValue");
        int amountOfCupcakes = Integer.parseInt(ctx.formParam("amount"));
        List<Orderline> sessionList = ctx.sessionAttribute("orderlineList");

        try {
            // Create cupcake object from form parameters and add it to Orderline.
            Topping topping = CupcakeMapper.getToppingByName(topValue, connectionPool);
            Bottom bottom = CupcakeMapper.getBottomByName(bottomValue, connectionPool);
            int cupcakeID = CupcakeMapper.getCupcakeIDByPartIDs(topping, bottom, connectionPool);
            Cupcake cupcake = new Cupcake(bottom, topping, cupcakeID);
            Orderline orderline = new Orderline(amountOfCupcakes, cupcake);
            orderList.add(orderline);

            // Combine session orderlist and orderlist instantiated on method call.
            if (sessionList != null) orderList.addAll(sessionList);

            // Render lists and re-render website.
            ctx.sessionAttribute("orderlineList", orderList);
            ctx.attribute("orderlineList", orderList);
            ctx.render("shoppingcart.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index.html");
        }
    }
}
