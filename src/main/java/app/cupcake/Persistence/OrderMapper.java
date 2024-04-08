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

    public static List<Orderline> getOrderlinesWithUsername(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT orderline.\"amount\", orderline.\"orderlineID\", orders.\"isPaidFor\", users.\"email\", orderline.\"cupcakeID\", orderline.\"orderID\" \n" +
                "FROM orderline \n" +
                "INNER JOIN orders ON orderline.\"orderID\" = orders.\"orderID\" \n" +
                "INNER JOIN users ON orders.\"userID\" = users.\"userID\" \n" +
                "ORDER BY users.\"email\" ASC";
        List<Orderline> orderlines = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Assuming Cupcake object can be instantiated here. You'll need to adjust based on actual Cupcake constructor

                int amount = rs.getInt("amount");
                int orderlineId = rs.getInt("orderlineId");
                int cupcakeID = rs.getInt("cupcakeID");

                Cupcake cupcake = CupcakeMapper.getCupcakeByCupcakeId(cupcakeID, connectionPool);
                Orderline orderline = new Orderline(amount, cupcake, orderlineId);

                // Assuming setName and setIsPaidFor methods exist in Orderline class
                orderline.setUsername(rs.getString("email")); // Set username as name, adjust if necessary
                orderline.setIsPaidFor(rs.getBoolean("isPaidFor"));
                orderline.setOrderID(rs.getInt("orderID"));
                orderlines.add(orderline);
            }
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Orderline med username fejl", e.getMessage());
        }
        return orderlines;
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



    public static void deleteOrderById(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        String sqlDeleteOrderline = "DELETE FROM orderline WHERE \"orderID\" = ?";
        String sqlDeleteOrder = "DELETE FROM orders WHERE \"orderID\" = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement psOrderline = connection.prepareStatement(sqlDeleteOrderline)) {
                psOrderline.setInt(1, orderId);
                psOrderline.executeUpdate();
            }
            try (PreparedStatement psOrder = connection.prepareStatement(sqlDeleteOrder)) {
                psOrder.setInt(1, orderId);
                int rowsAffected = psOrder.executeUpdate();
                if (rowsAffected != 1) {
                    throw new DatabaseException("Fejl i opdatering af en order");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved sletning af en order", e.getMessage());
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

    public static void updateIsPaidFor(int orderId, boolean isPaidFor, ConnectionPool connectionPool) throws DatabaseException {
        String sqlUpdatePaymentStatus = "UPDATE orders SET \"isPaidFor\" = ? WHERE \"orderID\" = ?";
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlUpdatePaymentStatus)) {
            ps.setBoolean(1, isPaidFor);
            ps.setInt(2, orderId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Error updating order payment status, see sqlUpdatePaymentStatus");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error updating order payment status", e.getMessage());
        }
    }
}


