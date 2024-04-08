package app.cupcake.Controllers;

import app.cupcake.Entities.User;
import app.cupcake.Exceptions.DatabaseException;
import app.cupcake.Persistence.ConnectionPool;
import app.cupcake.Persistence.CupcakeMapper;
import app.cupcake.Persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("createadmin", ctx -> ctx.render("createadmin.html"));
        app.get("createuser", ctx -> ctx.render("createuser.html"));
        app.get("logout", ctx -> logout(ctx));
        app.post("login", ctx -> login(ctx, connectionPool));
        app.post("createuser", ctx -> createuser(ctx,true ,connectionPool));
        app.post("createadmin", ctx -> createuser(ctx,false ,connectionPool));
    }

    private static void createuser(Context ctx, boolean isadmin, ConnectionPool connectionPool) throws DatabaseException {
        String username = ctx.formParam("username");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");
        String role = isadmin ? "bruger" : "admin";

        if (password1.equals(password2)) {
            try {
                if (!UserMapper.isUserExists(username, connectionPool)) {
                    UserMapper.createuser(username, password1, role, connectionPool);
                    ctx.attribute("message", "Du er hermed oprettet med brugernavn: " + username + ". Nu skal du logge på");
                    ctx.render("index.html");
                } else {
                    ctx.attribute("message", "Brugernavnet eksisterer allerede. Vælg venligst et andet brugernavn.");
                    ctx.render("createuser.html");
                }
            } catch (DatabaseException e) {
                ctx.attribute("message", "Der opstod en fejl under oprettelsen. Prøv venligst igen.");
                ctx.render("createuser.html");
            }
        } else {
            ctx.attribute("message", "Der er fejl ved din adgangskode. Prøv venligst igen.");
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
            ctx.attribute("bottomList", CupcakeMapper.getAllBottoms(connectionPool));
            ctx.attribute("toppingList", CupcakeMapper.getAllToppings(connectionPool));
            ctx.render("cupcakeshop.html");


        } catch (DatabaseException e) {
            //hvis nej send tilbage til login side med fejl
            ctx.attribute("message", "Forkert login, Prøv venligst igen.");
            ctx.render("index.html");
        }
    }

}
