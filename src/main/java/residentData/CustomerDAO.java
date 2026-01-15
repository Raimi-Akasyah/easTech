package residentData;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO {
    // Database connection details
    private final String DB_URL = "jdbc:mysql://localhost:3306/hostel";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";

    private Connection getConnection() throws SQLException {
        try {
            // Load the JDBC driver (optional for modern JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }


    public boolean saveCustomer(Customer customer) {
        String sql = "INSERT INTO residents (full_name, no_ic, email, phone_num, date_birth, room, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set parameters
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getNoIC());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhoneNum());
            stmt.setString(5, customer.getDateBirth());
            stmt.setString(6, customer.getRoom());
            stmt.setString(7, customer.getStartDate());
            stmt.setString(8, customer.getEndDate());

            // Execute the update
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Customer> listAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM residents";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("no_ic"),
                        rs.getString("email"),
                        rs.getString("phone_num"),
                        rs.getString("date_birth"),
                        rs.getString("room"),
                        rs.getString("start_date"),
                        rs.getString("end_date")
                );
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    // Update an existing customer in the database
    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE residents SET full_name = ?, no_ic = ?, email = ?, phone_num = ?, date_birth = ?, room = ?, start_date = ?, end_date = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getNoIC());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhoneNum());
            stmt.setString(5, customer.getDateBirth());
            stmt.setString(6, customer.getRoom());
            stmt.setString(7, customer.getStartDate());
            stmt.setString(8, customer.getEndDate());
            stmt.setInt(9, customer.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a customer by ID
    public Customer getCustomerById(int id) {
        String query = "SELECT * FROM residents WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("no_ic"),
                        rs.getString("email"),
                        rs.getString("phone_num"),
                        rs.getString("date_birth"),
                        rs.getString("room"),
                        rs.getString("start_date"),
                        rs.getString("end_date")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean deleteCustomerById(int customerId) {
        String query = "DELETE FROM residents WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Customer getCustomerByEmail(String email) throws SQLException {
        // Assume you have a connection method to your DB
        Connection conn = getConnection();
        Customer customer = null;
        try {
            String query = "SELECT * FROM residents WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new Customer(
                    rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getString("no_ic"),
                    rs.getString("email"),
                    rs.getString("phone_num"),
                    rs.getString("date_birth"),
                    rs.getString("room"),
                    rs.getString("start_date"),
                    rs.getString("end_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
}
