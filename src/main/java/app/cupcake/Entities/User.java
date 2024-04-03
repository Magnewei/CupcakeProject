package app.cupcake.Entities;

public class User {
    private int userId;
    private final String email;
    private final String password;
    private final String role;

    public User(String role, String email, String password) {
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public User(int userId, String email, String password, String role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        if (userId != 0) {
            return userId;
        }
        return 0;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

}
