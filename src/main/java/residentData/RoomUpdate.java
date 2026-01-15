package residentData;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/roomUpdate")
public class RoomUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET request to pre-fill the form with existing room data
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int roomID = Integer.parseInt(request.getParameter("roomID")); // Get the roomID from the URL
            RoomDAO roomDAO = new RoomDAO();
            Room room = roomDAO.getRoomById(roomID); // Fetch the room by ID

            if (room != null) {
                request.setAttribute("room", room);
                RequestDispatcher dispatcher = request.getRequestDispatcher("roomUpdate.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("roomController?error=RoomNotFound"); // Redirect if no room found
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("roomController?error=InvalidRoomID"); // Redirect in case of an error
        }
    }

    // Handle POST request to update the room details
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int roomID = Integer.parseInt(request.getParameter("roomID")); // Get the roomID from the form
            String roomType = request.getParameter("roomType");
            String roomNumber = request.getParameter("roomNumber");
            String roomBlock = request.getParameter("roomBlock");
            String available = request.getParameter("available");

            // Create a new Room object with updated data
            Room updatedRoom = new Room(roomID, roomType, roomNumber, roomBlock, available);

            // Update the room in the database
            RoomDAO roomDAO = new RoomDAO();
            boolean success = roomDAO.updateRoom(updatedRoom);

            if (success) {
                response.sendRedirect("roomController"); // Redirect to room controller after successful update
            } else {
                request.setAttribute("errorMessage", "Failed to update room. Please try again.");
                request.setAttribute("room", updatedRoom);
                request.getRequestDispatcher("roomUpdate.jsp").forward(request, response); // Forward back to the form if failed
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("roomUpdate.jsp").forward(request, response); // Forward to the form in case of an error
        }
    }
}
