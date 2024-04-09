package app.cupcake.Controllers;

import app.cupcake.Entities.*;
import app.cupcake.Exceptions.Persistence.ConnectionPool;
import app.cupcake.Exceptions.Persistence.CupcakeMapper;
import app.cupcake.Exceptions.Persistence.OrderMapper;
import app.cupcake.Exceptions.Persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import app.cupcake.Exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.List;

public class CupcakeController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("ordercupcakes", ctx -> orderCupcakes(ctx, connectionPool));
        app.post("removeorder", ctx -> removeOrder(ctx, connectionPool));
        app.post("seecart", ctx -> seeCart(ctx));
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
            OrderMapper.insertNewOrder(user, false, connectionPool);
            int orderID = OrderMapper.getLastOrder(connectionPool);

            for (Orderline orderline : orderlineList) {
                orderline.setOrderID(orderID);
                OrderMapper.insertNewOrderline(orderline, connectionPool);
                price += orderline.getPrice();
            }

            if (payLater) {
                orderlineList.clear();
                ctx.attribute("userBalance", user.getBalance());
                ctx.attribute("message", "Du kan nu hente dine muffins i butikken.");
                ctx.render("shoppingcart.html");

            } else if (user.getBalance() >= price) {
                UserMapper.removeMoney(user, price, connectionPool);
                user.removeBalance(price);
                OrderMapper.updateIsPaidFor(orderID, true, connectionPool);

                // Re-render user balance.
                ctx.sessionAttribute("currentUser");
                ctx.attribute("userBalance");
                ctx.attribute("message", "Du har nu betalt for dine cupcakes.");

                //Render shop and lists.
                orderlineList.clear();
                reRenderCupcakeShop(ctx, connectionPool, "");

                // If paylater == false but user doesn't have enough balance.
            } else {
                ctx.attribute("message", "Du har ikke nok penge på din konto.");
                reRenderShoppingCart(ctx);
                OrderMapper.deleteOrderById(orderID, connectionPool);
            }
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("shoppingcart.html");
        }
    }

    public static void removeOrder(Context ctx, ConnectionPool connectionPool) {
        try {
            int orderlineIndex = Integer.parseInt(ctx.formParam("orderline_index"));
            List<Orderline> orderlineList = ctx.sessionAttribute("orderlineList");
            orderlineList.remove(orderlineIndex);

            if (!orderlineList.isEmpty()) {
                reRenderShoppingCart(ctx);

            } else {
                reRenderCupcakeShop(ctx, connectionPool, "");
            }
        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index.html");
        }
    }

    public static void seeCart(Context ctx) {
        User user = ctx.sessionAttribute("currentUser");
        reRenderShoppingCart(ctx);
    }

    public static void orderCupcakes(Context ctx, ConnectionPool connectionPool) {
        List<Orderline> orderList = new ArrayList<>();
        try {
            String topValue = ctx.formParam("topValue");
            String bottomValue = ctx.formParam("bottomValue");
            User user = ctx.sessionAttribute("currentUser");
            List<Orderline> sessionList = ctx.sessionAttribute("orderlineList");
            int amountValue = Integer.parseInt(ctx.formParam("amountValue"));

            // Check null on cupcake top and bottom.
            if (topValue == null || bottomValue == null) {
                String message = "Alle felter skal have en værdi.";
                reRenderCupcakeShop(ctx, connectionPool, message);

                // Break the method call, if no number was chosen.
                return;
            }

            Topping topping = CupcakeMapper.getToppingByName(topValue, connectionPool);
            Bottom bottom = CupcakeMapper.getBottomByName(bottomValue, connectionPool);
            int cupcakeID = CupcakeMapper.getCupcakeIDByPartIDs(topping, bottom, connectionPool);
            Cupcake cupcake = new Cupcake(bottom, topping, cupcakeID);
            Orderline orderline = new Orderline(amountValue, cupcake);
            orderList.add(orderline);

            // Combine session orderlist and orderlist instantiated on method call.
            if (sessionList != null) orderList.addAll(sessionList);
            if (user != null) ctx.attribute("userBalance", user.getBalance());

            // Keep orderlineList session attribute intact.
            ctx.sessionAttribute("orderlineList", orderList);

            String addedCupcake = "Du har nu tilfølet " + amountValue + " cupcakes med " +
                    cupcake.getBottom().getName()
                    + " bunde og " + cupcake.getTop().getName()
                    + " toppe til din indkøbskurv.";

            reRenderCupcakeShop(ctx, connectionPool, addedCupcake);

            // Check null on cupcake amount.
        } catch (NumberFormatException e) {
            reRenderCupcakeShop(ctx, connectionPool, "Vælg en antal af cupcakes tak.");

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("index.html");
        }
    }

    public static void reRenderShoppingCart(Context ctx) {
        try {
            List<Orderline> orderlineList = ctx.sessionAttribute("orderlineList");
            ctx.attribute("orderlineList", orderlineList);
            ctx.render("shoppingcart.html");

        } catch (RuntimeException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }

    public static void reRenderCupcakeShop(Context ctx, ConnectionPool connectionPool, String message) {
        try {
            ctx.attribute("bottomList", CupcakeMapper.getAllBottoms(connectionPool));
            ctx.attribute("toppingList", CupcakeMapper.getAllToppings(connectionPool));
            ctx.attribute("message", message);
            ctx.render("cupcakeshop.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getCause());
            ctx.render("cupcakeshop.html");
        }

    }
}
