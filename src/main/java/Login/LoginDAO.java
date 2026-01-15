package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private static final String LOGIN_QUERY = 
        "SELECT * FROM login WHERE email = ? AND password = ? AND role = ?";

    protected Connection getConnection() {
        Connection connection = null;
        String jdbcURL = "jdbc:mysql://localhost:3306/hostel";
        String jdbcUsername = "root"; // Replace with your DB username
        String jdbcPassword = ""; // Replace with your DB password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean validateUser(String email, String password, String role) {
        boolean isValid = false;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);

            ResultSet resultSet = preparedStatement.executeQuery();
            isValid = resultSet.next(); // If resultSet has data, user exists
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }

    public static boolean registerUser(login log) {
        boolean isRegistered = false;
        String REGISTER_QUERY = "INSERT INTO login (name, email, password, role) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel", "root", "")) {
            System.out.println("Successfully connected to the database.");

            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_QUERY);
            preparedStatement.setString(1, log.getName());
            preparedStatement.setString(2, log.getEmail());
            preparedStatement.setString(3, log.getPassword());
            preparedStatement.setString(4, log.getRole());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            if (rowsAffected > 0) {
                isRegistered = true;
            } else {
                System.out.println("No rows affected. Check if the table accepts NULL or invalid values.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception during registration: " + e.getMessage());
            e.printStackTrace(); // Detailed logging for SQL errors
        } catch (Exception e) {
            System.out.println("General exception during registration: " + e.getMessage());
            e.printStackTrace(); // Any other exception
        }

        return isRegistered;
    }


}
