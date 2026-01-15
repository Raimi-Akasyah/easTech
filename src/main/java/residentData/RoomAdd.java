package residentData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/roomAdd")
public class RoomAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to the form page (roomAdd.jsp)
        response.sendRedirect("roomAdd.jsp");
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomType = request.getParameter("roomType");
    	String roomNumber = request.getParameter("roomNumber");
    	String roomBlock = request.getParameter("roomBlock");
        String available = request.getParameter("available");

        // Debugging: Print received parameters
        System.out.println("RoomAdd - Received roomNumber: " + roomNumber + ", available: " + available);

        try {
            Room room = new Room(0, roomType, roomNumber, roomBlock, available);

            RoomDAO roomDAO = new RoomDAO();
            boolean isAdded = roomDAO.addRoom(room);

            // Debugging: Print result of database operation
            System.out.println("RoomAdd - Room added to database: " + isAdded);

            if (isAdded) {
                response.sendRedirect("roomController");
            } else {
                request.setAttribute("errorMessage", "Failed to add the room. Please try again.");
                request.getRequestDispatcher("roomAdd.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log exception for debugging
            request.setAttribute("errorMessage", "An error occurred while adding the room.");
            request.getRequestDispatcher("roomAdd.jsp").forward(request, response);
        }
    }
}
