package residentData;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CustomerController") // Servlet mapping
public class CustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET requests (Load available rooms)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoomDAO roomDAO = new RoomDAO();

        // Fetch rooms that are marked as "Available"
        List<Room> availableRooms = roomDAO.getRoomsByStatus("Available");

        // Set the list of available rooms as an attribute for JSP
        request.setAttribute("availableRooms", availableRooms);

        // Forward to the customer dashboard page
        request.getRequestDispatcher("customerDashboard.jsp").forward(request, response);
    }

    // Handle POST requests (Booking process)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String noIC = request.getParameter("noIC");
        String email = request.getParameter("email");
        String phoneNum = request.getParameter("phoneNum");
        String dateBirth = request.getParameter("dateBirth");
        String roomNumber = request.getParameter("room");  // Customer selects room number
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        RoomDAO roomDAO = new RoomDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        try {
            // Check if the room is available
            Room room = roomDAO.getRoomByNumber(roomNumber);
            if (room == null || !"Available".equalsIgnoreCase(room.getAvailable())) {
                request.setAttribute("popupMessage", "Room is not available. Please choose another.");
                request.setAttribute("popupIcon", "error");
                request.getRequestDispatcher("customerDashboard.jsp").forward(request, response);
                return;
            }

            // Create a Customer object to save
            Customer customer = new Customer(0, fullName, noIC, email, phoneNum, dateBirth, roomNumber, startDate, endDate);
            boolean success = customerDAO.saveCustomer(customer);

            if (success) {
                // Mark the room as "Pending" (customer has booked but not yet confirmed)
                room.setAvailable("Pending");
                roomDAO.updateRoomAvailability(room);

                // Set a success message attribute
                request.setAttribute("popupMessage", "Booking successful! Room is now pending confirmation.");
                request.setAttribute("popupIcon", "success");
            } else {
                request.setAttribute("popupMessage", "Booking failed! Please try again.");
                request.setAttribute("popupIcon", "error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("popupMessage", "An error occurred: " + e.getMessage());
            request.setAttribute("popupIcon", "error");
        }

        // Fetch updated room list
        List<Room> availableRooms = roomDAO.getRoomsByStatus("Available");
        request.setAttribute("availableRooms", availableRooms);

        // Forward to the JSP page
        request.getRequestDispatcher("customerDashboard.jsp").forward(request, response);
    }
}
