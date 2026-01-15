package residentData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/roomDelete")
public class RoomDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int roomID = Integer.parseInt(request.getParameter("roomID"));
            System.out.println("RoomDelete - Received roomID: " + roomID);

            RoomDAO roomDAO = new RoomDAO();
            boolean success = roomDAO.deleteRoomById(roomID);

            System.out.println("RoomDelete - Deletion success: " + success);

            if (success) {
                request.setAttribute("popupMessage", "Room deleted successfully.");
            } else {
                request.setAttribute("popupMessage", "Failed to delete room.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("popupMessage", "Invalid room ID.");
        }

        response.sendRedirect("roomController");
    }
}
