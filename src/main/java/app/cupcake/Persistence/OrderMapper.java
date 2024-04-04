package app.cupcake.Persistence;

import app.cupcake.Entities.*;
import app.cupcake.Exceptions.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        List<Order> orderList = new ArrayList<>();
        String sql = "select * from orders";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("orderID");
                Boolean idPaidFor = rs.getBoolean("done");
                int userId = rs.getInt("userID");
                List<Orderline> orderlineList = getOrderLinesByOrderId(orderId,connectionPool);
                orderList.add(new Order(orderId,idPaidFor,orderlineList,userId));
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return orderList;
    }

    public static List<Orderline> getOrderLinesByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException {
    List<Orderline> orderlineList = new ArrayList<>();
    String sql = "SELECT * FROM orderline WHERE \"orderID\" = ?";
    try(
            Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
    ) {
        ps.setInt(1,orderId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int orderlineId = rs.getInt("orderlineID");
            int amount = rs.getInt("amount");
            int price = rs.getInt("price");
            Cupcake cupcake = getCupcakeByCupcakeId(rs.getInt("cupcakeID"),connectionPool);
            orderlineList.add(new Orderline(price,amount,cupcake,orderlineId));
        }
    }
    catch (SQLException e) {
        throw new DatabaseException("OrderLine id", e.getMessage());
    }
        return orderlineList;
    }

    public static Cupcake getCupcakeByCupcakeId(int cupcakeId,ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM cupcake WHERE \"cupcakeID\" = ?";
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1,cupcakeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int cupcakeID = rs.getInt("cupcakeID");
                int bottomId = rs.getInt("bottomID");
                int price = rs.getInt("price");
                int toppingId = rs.getInt("toppingID");
                return new Cupcake(getBottomByBottomId(bottomId,connectionPool),getToppingByToppingId(toppingId,connectionPool),price,cupcakeID);
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("Get cupcake fejl", e.getMessage());
        }
        return null;
    }


    public static void addOrderline(String bottom, String topping, int amount, int userID, ConnectionPool connectionPool) throws DatabaseException {

        // Viva la chatGPT
        String sqlCupcake = "INSERT INTO Cupcake (bottomID, toppingID, orderID)\n" +
                "SELECT \n" +
                "    Bottom.\"BottomID\",\n" +
                "    Topping.\"toppingID\",\n" +
                "    Orders.\"orderID\"\n" +
                "FROM Bottom \n" +
                "JOIN Topping ON Bottom.\"name\" = ? AND Topping.\"name\" = ?\n" +
                "JOIN Orders ON Orders.\"userID\" = ?\n" +
                "WHERE Orders.\"isPaidFor\" = 0;";

        String sqlOrderline = "INSERT INTO Orderline (cupcakeID, amount)\n" +
                "SELECT Cupcake.\"cupcakeID\", ? FROM Cupcake\n" +
                "JOIN Orders ON Orders.\"orderID\" = Cupcake.\"orderID\" " +
                "WHERE Orders.\"userID\" = ? AND Orders.isPaidFor = 0;";

        /*
       String sql = "INSERT INTO Cupcake (bottomID, toppingID, orderID)\n" +
                "SELECT \n" +
                "    Bottom.\"BottomID\",\n" +
                "    Topping.\"toppingID\",\n" +
                "    Orders.\"orderID\"\n" +
                "FROM Bottom \n" +
                "JOIN \n" +
                "Topping ON Bottom.\"name\" = " + bottom +" AND Topping.\"name\" = "+ topping +
                "\nJOIN Orders ON Orders.\"userID\" = " + userID +
                "WHERE Orders.\"isPaidFor\" = 0;\n" +
                "INSERT INTO Orderline (cupcakeID, amount)\n" +
                "SELECT Cupcake.\"cupcakeID\", " + amount + " FROM Cupcake\n" +
                "JOIN Orders ON Orders.\"orderID\" = Cupcake.\"orderID\" " +
                "WHERE Orders.\"userID\" = " + userID + " AND Orders.isPaidFor = 0;";

         */
       /*
       sql = "INSERT INTO Cupcake (bottomID, toppingID, orderID)\n" +
            "SELECT \n" +
            "    Bottom.\"BottomID\",\n" +
            "    Topping.\"toppingID\",\n" +
            "    Orders.\"orderID\"\n" +
            "FROM Bottom \n" +
            "JOIN Topping ON Bottom.\"name\" = '" + bottom + "' AND Topping.\"name\" = '" + topping + "'\n" +
            "JOIN Orders ON Orders.\"userID\" = " + userID + "\n" +
            "WHERE Orders.\"isPaidFor\" = 0;\n" +
            "INSERT INTO Orderline (cupcakeID, amount)\n" +
            "SELECT Cupcake.\"cupcakeID\", " + amount + " FROM Cupcake\n" +
            "JOIN Orders ON Orders.\"orderID\" = Cupcake.\"orderID\" " +
            "WHERE Orders.\"userID\" = " + userID + " AND Orders.isPaidFor = 0;";
            */
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement psCupcake = connection.prepareStatement(sqlCupcake, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement psOrderline = connection.prepareStatement(sqlOrderline, Statement.RETURN_GENERATED_KEYS)
        ) {
            // Execute the first statement to insert cupcake
            int rowsAffectedCupcake = psCupcake.executeUpdate();
            if (rowsAffectedCupcake == 1) {
                ResultSet rsCupcake = psCupcake.getGeneratedKeys();
                if (rsCupcake.next()) {
                    int cupcakeId = rsCupcake.getInt(1);

                    // Execute the second statement to insert orderline
                    psOrderline.setInt(1, cupcakeId);
                    psOrderline.setInt(2, amount);

                    int rowsAffectedOrderline = psOrderline.executeUpdate();
                    if (rowsAffectedOrderline == 1) {
                    } else {
                    }
                } else {
                }
            } else {
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error in DB connection", e.getMessage());
        }
    }

    public static List<Topping> getAllToppings(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM topping;";
                List<Topping> toppingList = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int toppingID = rs.getInt("toppingID");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                toppingList.add(new Topping(price,name,toppingID));
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("Topping fejl", e.getMessage());
        }
        return toppingList;
    }

    public static List<Bottom> getAllBottoms(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM bottom;";
        List<Bottom> bottomList = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bottomID = rs.getInt("bottomID");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                bottomList.add(new Bottom(price,name,bottomID));
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("Topping fejl", e.getMessage());
        }
        return bottomList;
    }

    private static Topping getToppingByToppingId(int toppingId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM topping WHERE \"toppingID\" = ?";
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1,toppingId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int toppingID = rs.getInt("toppingID");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                return new Topping(price,name,toppingID);
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("Topping fejl", e.getMessage());
        }
        return null;
    }

    private static Bottom getBottomByBottomId(int bottomId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM bottom WHERE \"bottomID\" = ?";
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1,bottomId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int bottomID = rs.getInt("bottomID");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                return new Bottom(price,name,bottomID);
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("Get bottomID fejl", e.getMessage());
        }
        return null;
    }


    public static void deleteOrderById(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM orders WHERE \"orderID\" = ?";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, orderId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl i opdatering af en task");
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("Fejl ved sletning af en task", e.getMessage());
        }
    }

    public static void deleteOrderlineById(int orderlineID, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM orderline WHERE \"orderlineID\" = ?";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, orderlineID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl i opdatering af en task");
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("Fejl ved sletning af en task", e.getMessage());
        }
    }

    public static List<Orderline> getOrderLinesByUserId(int userId, ConnectionPool connectionPool) throws DatabaseException {
        List<Orderline> orderlineList = new ArrayList<>();
        String sql = "SELECT orderline.\"orderlineID\", orderline.\"orderID\", orderline.\"amount\", orderline.\"cupcakeID\", users.\"userID\"\n" +
                "FROM orderline\n" +
                "INNER JOIN orders ON orderline.\"orderID\" = orders.\"orderID\"\n" +
                "INNER JOIN users ON orders.\"userID\" = users.\"userID\" \n" +
                "where users.\"userID\" = ?;";
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("userID");
                int orderlineId = rs.getInt("orderlineID");
                int amount = rs.getInt("amount");
                Cupcake cupcake = getCupcakeByCupcakeId(rs.getInt("cupcakeID"), connectionPool);
                orderlineList.add(new Orderline(amount,cupcake,orderlineId));
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("Orderlines by userID error", e.getMessage());
        }
        return orderlineList;
    }
}
