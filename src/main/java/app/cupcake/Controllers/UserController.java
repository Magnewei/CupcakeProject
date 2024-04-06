package app.cupcake.Controllers;

import app.cupcake.Entities.User;
import app.cupcake.Exceptions.DatabaseException;
import app.cupcake.Persistence.ConnectionPool;
import app.cupcake.Persistence.OrderMapper;
import app.cupcake.Persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool){
        app.post("login", ctx -> login(ctx, connectionPool));
        app.get("logout", ctx -> logout(ctx));
        app.get("createuser", ctx->ctx.render("createuser.html"));
        app.post("createuser", ctx->createuser(ctx,connectionPool));
    }

    private static void createuser(Context ctx,ConnectionPool connectionPool){
        String username = ctx.formParam("username");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");

        if(password1.equals(password2)){
            try {
                UserMapper.createuser(username,password1,connectionPool);
                ctx.attribute("message","Du er hermed oprettet med brugernavn: " + username +". Nu skal du logge på");
                ctx.render("index.html");
            }
            catch (DatabaseException e) {
                ctx.attribute("message","Dit brugernavn findes allerede, Prøv igen eller login");
                ctx.render("createuser.html");
            }
        } else{
            ctx.attribute("message","Dine 2 passwords matcher ikke! prøv igen");
            ctx.render("createuser.html");
        }
    }

    private static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        //Hent form parametre
        String mail = ctx.formParam("username");
        String password = ctx.formParam("password");

        //Check om bruger findes i database og med de angivne username + password
        try {
            User user = UserMapper.login(mail, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);

            //Send videre til task siden
            ctx.attribute("bottomList", OrderMapper.getAllBottoms(connectionPool));
            ctx.attribute("toppingList", OrderMapper.getAllToppings(connectionPool));
            ctx.render("cupcakeshop.html");


        } catch (DatabaseException e) {
            //hvis nej send tilbage til login side med fejl
            ctx.render("index.html");
            ctx.attribute("message", e.getMessage());
        }
    }
}
