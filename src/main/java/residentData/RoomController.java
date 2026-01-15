package residentData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/roomController")
public class RoomController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoomDAO roomDAO = new RoomDAO();

        // Fetch the list of rooms
        ArrayList<Room> rooms = roomDAO.listAllRoom();


        if (rooms.isEmpty()) {
            System.out.println("No rooms retrieved in RoomController.");
        } else {
            for (Room room : rooms) {
                System.out.println("RoomController: " + room.getRoomID() + ", " + room.getRoomNumber() + ", " + room.getAvailable());
            }
        }
        
        // Pass the rooms list to the JSP
        request.setAttribute("rooms", rooms);

            request.getRequestDispatcher("roomView.jsp").forward(request, response);
    }
}

