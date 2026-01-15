package rent;

import java.sql.*;
import java.util.ArrayList;

public class RentDAO {
    private final String DB_URL = "jdbc:mysql://localhost:3306/hostel";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public boolean saveRent(Rent rent) {
        String sql = "INSERT INTO rent (rent_amount, rent_paymentStatus, rent_Due) VALUES (?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, rent.getRent_amount());
            stmt.setString(2, rent.getRent_paymentStatus());
            stmt.setDate(3, new java.sql.Date(rent.getRent_Due().getTime()));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Rent> listAllRents() {
        ArrayList<Rent> rents = new ArrayList<>();
        String query = "SELECT * FROM rent";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Rent rent = new Rent(
                        rs.getInt("rentID"),
                        rs.getDouble("rent_amount"),
                        rs.getString("rent_paymentStatus"),
                        rs.getDate("rent_Due")
                );
                rents.add(rent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rents;
    }

    public boolean updateRent(Rent rent) {
        String query = "UPDATE rent SET rent_amount = ?, rent_paymentStatus = ?, rent_Due = ? WHERE rentID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, rent.getRent_amount());
            stmt.setString(2, rent.getRent_paymentStatus());
            stmt.setDate(3, new java.sql.Date(rent.getRent_Due().getTime()));
            stmt.setInt(4, rent.getRentID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Rent getRentById(int id) {
        String query = "SELECT * FROM rent WHERE rentID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Rent(
                        rs.getInt("rentID"),
                        rs.getDouble("rent_amount"),
                        rs.getString("rent_paymentStatus"),
                        rs.getDate("rent_Due")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteRentById(int rentID) {
        String query = "DELETE FROM rent WHERE rentID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, rentID);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
