package complaint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {
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

    // Save complaint
    public boolean saveComplaint(complaint complaint) {
        String sql = "INSERT INTO complaint (complaintDescription, cmp_priority, cmp_status, cmp_Date) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setString(1, complaint.getComplaintDescription());
        	stmt.setString(2, complaint.getCmp_priority());
            stmt.setString(3, complaint.getCmp_status());
            stmt.setDate(4, new java.sql.Date(complaint.getCmp_Date().getTime()));
            

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // List all complaints
    public ArrayList<complaint> listAllComplaints() {
        ArrayList<complaint> complaints = new ArrayList<>();
        String query = "SELECT * FROM complaint";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                complaint complaint = new complaint(
                        rs.getInt("complaintID"),
                        rs.getString("complaintDescription"), 
                        rs.getString("cmp_priority"),
                        rs.getString("cmp_status"),
                        rs.getDate("cmp_Date")
                );
                complaints.add(complaint);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaints;
    }

    // Update complaint
    public boolean updateComplaint(complaint complaint) {
        String query = "UPDATE complaint SET complaintDescription = ?,  cmp_priority = ?, cmp_status = ?, cmp_Date = ? WHERE complaintID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

        	stmt.setString(1, complaint.getComplaintDescription());
        	stmt.setString(2, complaint.getCmp_priority());
            stmt.setString(3, complaint.getCmp_status());
            stmt.setDate(4, new java.sql.Date(complaint.getCmp_Date().getTime()));
            stmt.setInt(5, complaint.getComplaintID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get complaint by ID
    public complaint getComplaintById(int id) {
        String query = "SELECT * FROM complaint WHERE complaintID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new complaint(
                        rs.getInt("complaintID"),
                        rs.getString("complaintDescription"),
                        rs.getString("cmp_priority"),
                        rs.getString("cmp_status"),
                        rs.getDate("cmp_Date")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Delete complaint by ID
    public boolean deleteComplaintById(int complaintID) {
        String query = "DELETE FROM complaint WHERE complaintID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, complaintID);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
