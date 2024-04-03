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
    public static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException
    {
        List<Order> orderList = new ArrayList<>();
        String sql = "select * from orders";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int orderId = rs.getInt("orderID");
                Boolean idPaidFor = rs.getBoolean("done");
                int userId = rs.getInt("userID");
                List<Orderline> orderlineList= getOrderLinesByOrderId(orderId,connectionPool);
                orderList.add(new Order(orderId,idPaidFor,orderlineList,userId));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return orderList;
    }

    public static List<Orderline> getOrderLinesByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException
    {
    List<Orderline> orderlineList = new ArrayList<>();
    String sql = "SELECT * FROM orderline WHERE orderID = ?";
    try(
            Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
    )
    {
        ResultSet rs = ps.executeQuery();
        ps.setInt(1,orderId);
        while (rs.next())
        {
            int orderlineId = rs.getInt("orderlineID");
            int amount = rs.getInt("amount");
            int price = rs.getInt("price");
            Cupcake cupcake = getCupcakeByCupcakeId(rs.getInt("cupcakeID"),connectionPool);
            orderlineList.add(new Orderline(price,amount,cupcake,orderlineId));
        }
    }
    catch (SQLException e)
    {
        throw new DatabaseException("Fejl!!!!", e.getMessage());
    }
        return orderlineList;
    }

    public static Cupcake getCupcakeByCupcakeId(int cupcakeId,ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "SELECT * FROM cupcake WHERE cupcakeID = ?";
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        )
        {
            ResultSet rs = ps.executeQuery();
            ps.setInt(1,cupcakeId);
            if (rs.next())
            {
                int cupcakeID = rs.getInt("cupcakeID");
                int bottomId = rs.getInt("bottomID");
                int price = rs.getInt("price");
                int toppingId = rs.getInt("toppingID");
                return new Cupcake(getBottomByBottomId(bottomId,connectionPool),getToppingByToppingId(toppingId,connectionPool),price,cupcakeID);
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return null;
    }

    private static Topping getToppingByToppingId(int toppingId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM topping WHERE toppingID = ?";
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        )
        {
            ResultSet rs = ps.executeQuery();
            ps.setInt(1,toppingId);
            if (rs.next())
            {
                int toppingID = rs.getInt("toppingID");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                return new Topping(price,name,toppingID);
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return null;
    }

    private static Bottom getBottomByBottomId(int bottomId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM bottom WHERE bottomID = ?";
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        )
        {
            ResultSet rs = ps.executeQuery();
            ps.setInt(1,bottomId);
            if (rs.next())
            {
                int bottomID = rs.getInt("bottomID");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                return new Bottom(price,name,bottomID);
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return null;
    }


}
