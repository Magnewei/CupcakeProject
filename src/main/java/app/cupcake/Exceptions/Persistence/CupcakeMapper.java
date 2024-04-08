package app.cupcake.Exceptions.Persistence;

import app.cupcake.Entities.*;
import app.cupcake.Exceptions.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CupcakeMapper {
    public static Bottom getBottomByName(String bottomnName, ConnectionPool connectionPool) throws DatabaseException {
        String sqlTopping = "SELECT * FROM bottom WHERE name = ?";
        Bottom bottom = null;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlTopping);) {
            ps.setString(1, bottomnName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int bottomID = rs.getInt("bottomID");
                int price = rs.getInt("price");
                String name = rs.getString("name");
                bottom = new Bottom(price, name, bottomID);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Get topping fejl", e.getMessage());
        }
        return bottom;
    }

    public static Topping getToppingByName(String toppingName, ConnectionPool connectionPool) throws DatabaseException {
        String sqlTopping = "SELECT * FROM topping WHERE name = ?";
        Topping topping = null;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlTopping);) {
            ps.setString(1, toppingName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int toppingID = rs.getInt("toppingID");
                int price = rs.getInt("price");
                String name = rs.getString("name");
                topping = new Topping(price, name, toppingID);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Get topping fejl", e.getMessage());
        }
        return topping;
    }

    public static int getCupcakeIDByPartIDs(Topping topping, Bottom bottom, ConnectionPool connectionPool) throws DatabaseException {
        String sqlFindCupcakeId = "SELECT * FROM cupcake WHERE \"bottomID\" = ? AND \"toppingID\" = ?";
        int cupcakeID = 0;
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlFindCupcakeId)) {
            ps.setInt(1, bottom.getBottomID());
            ps.setInt(2, topping.getToppingID());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cupcakeID = rs.getInt("cupcakeID");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Get cupcakeID fejl", e.getMessage());
        }
        return cupcakeID;
    }

    public static Bottom getBottomByBottomId(int bottomId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM bottom WHERE \"bottomID\" = ?";
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, bottomId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int bottomID = rs.getInt("bottomID");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                return new Bottom(price, name, bottomID);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Get bottomID fejl", e.getMessage());
        }
        return null;
    }

    public static Topping getToppingByToppingId(int toppingId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM topping WHERE \"toppingID\" = ?";
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, toppingId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int toppingID = rs.getInt("toppingID");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                return new Topping(price, name, toppingID);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Topping fejl", e.getMessage());
        }
        return null;
    }

    public static List<Topping> getAllToppings(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM topping;";
        List<Topping> toppingList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int toppingID = rs.getInt("toppingID");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                toppingList.add(new Topping(price, name, toppingID));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Topping fejl", e.getMessage());
        }
        return toppingList;
    }

    public static List<Bottom> getAllBottoms(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM bottom;";
        List<Bottom> bottomList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bottomID = rs.getInt("bottomID");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                bottomList.add(new Bottom(price, name, bottomID));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Topping fejl", e.getMessage());
        }
        return bottomList;
    }

    public static Cupcake getCupcakeByCupcakeId(int cupcakeId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM cupcake WHERE \"cupcakeID\" = ?";
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, cupcakeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int cupcakeID = rs.getInt("cupcakeID");
                int bottomId = rs.getInt("bottomID");
                int toppingId = rs.getInt("toppingID");
                return new Cupcake(getBottomByBottomId(bottomId, connectionPool), getToppingByToppingId(toppingId, connectionPool), cupcakeID);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Get cupcake fejl", e.getMessage());
        }
        return null;
    }


}
