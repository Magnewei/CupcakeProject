package app.cupcake.Entities;

public class User {
    private int userId;
    private final String userName;
    private final String password;
    private final String role;

    public User(String role, String userName, String password) {
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

    public User(int userId, String userName, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        if (userId != 0) {
            return userId;
        }
        return 0;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

}
