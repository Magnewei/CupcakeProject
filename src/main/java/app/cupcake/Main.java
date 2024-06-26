package app.cupcake;

import app.cupcake.Controllers.CupcakeController;
import app.cupcake.Controllers.HeaderController;
import app.cupcake.Controllers.AdminController;
import app.cupcake.Controllers.UserController;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;
import app.cupcake.Persistence.ConnectionPool;
import app.cupcake.Thymeleaf.ThymeleafConfig;

public class Main {
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "cupcake";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);
    public static void main(String[] args) {
        // Initializing Javalin and Jetty webserver
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7073);

        // Routing
        app.get("/", ctx -> ctx.render("index.html"));
        UserController.addRoutes(app, connectionPool);
        AdminController.addRoutes(app, connectionPool);
        CupcakeController.addRoutes(app, connectionPool);
        HeaderController.addRoutes(app, connectionPool);
    }
}