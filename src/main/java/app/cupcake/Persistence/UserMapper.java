package app.cupcake.Persistence;

import app.cupcake.Entities.User;
import app.cupcake.Exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from users where email=? and password=?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("userID");
                String role = rs.getString("role");
                int balance = rs.getInt("balance");
                return new User(id, email, password, role, balance);
            } else {
                throw new DatabaseException("Fejl i login. Prøv igen");
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
    }

    public static void createuser(String Email, String password, String role, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into users (email, password,role) values (?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, Email);
            ps.setString(2, password);
            ps.setString(3,role);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
            }
        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value ")) {
                msg = "Brugernavnet findes allerede. Vælg et andet";
            }
            throw new DatabaseException(msg, e.getMessage());
        }
    }

    public static boolean isUserExists(String email, ConnectionPool connectionPool) {
        String sql = "SELECT COUNT(*) AS count FROM users WHERE email = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addmoney(int userId, int amount, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE users SET balance = balance + ? WHERE users.\"userID\" = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, amount);
            ps.setInt(2, userId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved tilføjelse af penge");
            }
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Database Fejl");
        }
    }

    public static void removeMoney(User user, int amount, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE users SET balance = balance - ? WHERE users.\"userID\" = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, amount);
            ps.setInt(2, user.getUserID());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved tilføjelse af penge");
            }
        } catch (SQLException | DatabaseException e) {
            throw new DatabaseException("Database Fejl");
        }
    }

    public static List<User> getAllUsers(ConnectionPool connectionPool) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                int balance = rs.getInt("balance");

                User user = new User(id, email, password, role, balance);
                users.add(user);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}