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
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("orderID");
                Boolean idPaidFor = rs.getBoolean("done");
                int userId = rs.getInt("userID");
                List<Orderline> orderlineList = getOrderLinesByOrderId(orderId, connectionPool);
                orderList.add(new Order(orderId, idPaidFor, orderlineList, userId));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return orderList;
    }

    public static List<Orderline> getOrderLinesByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        List<Orderline> orderlineList = new ArrayList<>();
        String sql = "SELECT * FROM orderline WHERE \"orderID\" = ?";
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderlineId = rs.getInt("orderlineID");
                int amount = rs.getInt("amount");
                Cupcake cupcake = CupcakeMapper.getCupcakeByCupcakeId(rs.getInt("cupcakeID"), connectionPool);
                orderlineList.add(new Orderline(amount, cupcake, orderlineId));
            }
        } catch (SQLException e) {
            throw new DatabaseException("OrderLine id", e.getMessage());
        }
        return orderlineList;
    }


    public static void addOrderline(String bottom, String topping, int amount, int userID, ConnectionPool connectionPool) throws DatabaseException {

        //Henter den bottom som er blevet valgt
        String sqlBottom = "SELECT * FROM bottom WHERE name = ?";
        Bottom bottom1 = null;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlBottom);) {
            ps.setString(1, bottom);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int bottomID = rs.getInt("bottomID");
                int price = rs.getInt("price");
                String name = rs.getString("name");
                bottom1 = new Bottom(price, name, bottomID);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Get bottom fejl", e.getMessage());
        }


        //Henter den topping som er blevet valgt
        String sqlTopping = "SELECT * FROM topping WHERE name = ?";
        Topping topping1 = null;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlTopping);) {
            ps.setString(1, topping);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int toppingID = rs.getInt("toppingID");
                int price = rs.getInt("price");
                String name = rs.getString("name");
                topping1 = new Topping(price, name, toppingID);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Get topping fejl", e.getMessage());
        }


        //Sørger for at der er en ordre som ordrelinjerne kan blive knyttet til
        String sqlMakeOrder = "INSERT INTO orders (\"isPaidFor\",\"userID\") VALUES (FALSE,?)";
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlMakeOrder)) {
            ps.setInt(1, userID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl i opdatering af ordrer, se String sqlMakeOrder");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved indsættelse af ordre", e.getMessage());
        }


        //Finder det cupcake id som svarer til den valgte top og bund
        String sqlFindCupcakeId = "SELECT * FROM cupcake WHERE \"bottomID\" = ? AND \"toppingID\" = ?";
        int cupcakeID = 0;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlFindCupcakeId);) {
            ps.setInt(1, bottom1.getBottomID());
            ps.setInt(2, topping1.getToppingID());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cupcakeID = rs.getInt("cupcakeID");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Get cupcakeID fejl", e.getMessage());
        }


        //Finder den ordreID som lige er blevet lavet
        String sqlFindOrderId = "SELECT * FROM orders WHERE \"userID\" = ? AND \"isPaidFor\" = FALSE";
        int orderID = 0;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlFindOrderId);) {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                orderID = rs.getInt("orderID");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Get orderID fejl", e.getMessage());
        }


        //Tilføjer ordrelinjen, så den kan ses af brugeren.
        String sqlMakeOrderline = "INSERT INTO orderline (\"cupcakeID\",\"orderID\",amount) VALUES (?,?,?)";
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlMakeOrderline)) {
            ps.setInt(1, cupcakeID);
            ps.setInt(2, orderID);
            ps.setInt(3, amount);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl i opdatering af ordrelinjer, se String sqlMakeOrderline");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved indsættelse af ordrelinje", e.getMessage());
        }
    }


    public static void deleteOrderById(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM orders WHERE \"orderID\" = ?";
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl i opdatering af en task");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved sletning af en task", e.getMessage());
        }
    }

    public static void deleteOrderlineById(int orderlineID, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM orderline WHERE \"orderlineID\" = ?";
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderlineID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl i opdatering af en task");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved sletning af en task", e.getMessage());
        }
    }

    public static List<Orderline> getOrderLinesByUserId(int userId, ConnectionPool connectionPool) throws DatabaseException {
        List<Orderline> orderlineList = new ArrayList<>();
        String sql = "SELECT orderline.\"orderlineID\", orderline.\"orderID\", orderline.\"amount\", orderline.\"cupcakeID\", users.\"userID\"\n" + "FROM orderline\n" + "INNER JOIN orders ON orderline.\"orderID\" = orders.\"orderID\"\n" + "INNER JOIN users ON orders.\"userID\" = users.\"userID\" \n" + "where users.\"userID\" = ?;";
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("userID");
                int orderlineId = rs.getInt("orderlineID");
                int amount = rs.getInt("amount");
                Cupcake cupcake = CupcakeMapper.getCupcakeByCupcakeId(rs.getInt("cupcakeID"), connectionPool);
                orderlineList.add(new Orderline(amount, cupcake, orderlineId));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Orderlines by userID error", e.getMessage());
        }
        return orderlineList;
    }

    public static int getLastOrder(ConnectionPool connectionPool) throws DatabaseException {
        int orderNumber = 0;
        String sql = "SELECT \"orderID\" " + "FROM orders " + "ORDER BY \"orderID\" DESC " + "LIMIT 1;";
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            if (rs.next()) {
                orderNumber = rs.getInt("orderID");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving the latest order ID", e.getMessage());
        }
        return orderNumber;
    }


    public static void insertNewOrder(User user, boolean isPaidFor, ConnectionPool connectionPool) throws DatabaseException {
        String sqlMakeOrder = "INSERT INTO orders (\"isPaidFor\",\"userID\") VALUES (?,?)";
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlMakeOrder)) {
            ps.setBoolean(1, isPaidFor);
            ps.setInt(2, user.getUserID());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl i opdatering af ordrer, se String sqlMakeOrder");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved indsættelse af ordre", e.getMessage());
        }
    }
public static void insertNewOrderline(Orderline orderline, ConnectionPool connectionPool) throws DatabaseException {
    String sqlMakeOrderline = "INSERT INTO orderline (\"cupcakeID\",\"orderID\",amount) VALUES (?,?,?)";
    try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlMakeOrderline)) {
        ps.setInt(1, orderline.getCupcake().getCupcakeID());
        ps.setInt(2, orderline.getOrderID());
        ps.setInt(3, orderline.getAmount());
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected != 1) {
            throw new DatabaseException("Fejl i opdatering af ordrelinjer, se String sqlMakeOrderline");
        }
    } catch (SQLException e) {
        throw new DatabaseException("Fejl ved indsættelse af ordrelinje", e.getMessage());
    }
    }
}


