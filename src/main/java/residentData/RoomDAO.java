package residentData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/hostel", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error establishing database connection.", e);
        }
    }

    public ArrayList<Room> listAllRoom() {
        ArrayList<Room> room = new ArrayList<>();
        String query = "SELECT * FROM room";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                // Create a Room object with values from the ResultSet
                Room r = new Room(
                    rs.getInt("roomID"),          // Fetch roomID
                    rs.getString("roomType"),
                    rs.getString("roomNumber"),   // Fetch roomNumber
                    rs.getString("roomBlock"),
                    rs.getString("available")     // Fetch available status
                );
                room.add(r); // Add the Room object to the list
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return room;
    }
    
    public boolean addRoom(Room room) {
        String sql = "INSERT INTO room (roomType, roomNumber, roomBlock, available) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set parameters
        	statement.setString(1, room.getRoomType());
            statement.setString(2, room.getRoomNumber());
            statement.setString(3, room.getRoomBlock());
            statement.setString(4, room.getAvailable());

            // Debug: Print query for validation
            System.out.println("Executing SQL: " + sql);
            System.out.println("room_type" + room.getRoomType() +
            		", room_number: " + room.getRoomNumber() + 
            		", room_block" + room.getRoomBlock() +
            		", available: " + room.getAvailable());

            // Execute SQL
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Return true if at least one row was inserted
        } catch (Exception e) {
            // Debug: Log exception
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRoom(Room room) {
        String query = "UPDATE room SET roomType = ?, roomNumber = ?, "
        		+ "roomBlock = ?, available = ? WHERE roomID = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, room.getRoomType());
            ps.setString(2, room.getRoomNumber());
            ps.setString(3, room.getRoomBlock());
            ps.setString(4, room.getAvailable());
            ps.setInt(5, room.getRoomID());

            int rowsAffected = ps.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);  // Debug log

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Room getRoomById(int roomID) {
        Room room = null;
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM room WHERE roomID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, roomID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                room = new Room(rs.getInt("roomID"), rs.getString("roomType"), 
                		rs.getString("roomNumber"),rs.getString("roomBlock"),
                		rs.getString("available"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room; // If null, means no data found
    }

    public boolean deleteRoomById(int roomID) {
        String query = "DELETE FROM room WHERE roomID = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, roomID);
            int rowsAffected = ps.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected); // Debug
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace(); // Debug: Print stack trace
            return false;
        }
    }
    
    public Room getRoomByNumber(String roomNumber) {
        Room room = null;
        String query = "SELECT * FROM room WHERE roomNumber = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, roomNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	room = new Room(rs.getInt("roomID"), rs.getString("roomType"), 
                		rs.getString("roomNumber"),rs.getString("roomBlock"),
                		rs.getString("available"));
            	}
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }
    public List<Room> getRoomsByStatus(String status) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE available = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rooms.add(new Room(rs.getInt("roomID"),rs.getString("roomType"), rs.getString("roomNumber"),
                        rs.getString("roomBlock"), rs.getString("available")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public void updateRoomAvailability(Room room) {
        String query = "UPDATE room SET available = ? WHERE roomID = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, room.getAvailable());  // Assuming "No" means unavailable
            stmt.setInt(2, room.getRoomID());  // Update by roomID (after getting it from getRoomByNumber)
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	}
    }
