package booking;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/residents")
public class residentController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ResidentService residentService;

    public void init() {
        residentService = new ResidentService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the logged-in user's ID (email) from session
        String loggedInUserEmail = (String) request.getSession().getAttribute("userEmail");

        if (loggedInUserEmail != null) {
            try {
                // Fetch resident details for the logged-in user only
                List<residentInfo> residentList = residentService.getResidentDetailsForUser(loggedInUserEmail);

                // Set the fetched details to the request attribute and forward to JSP
                request.setAttribute("residents", residentList);
                request.getRequestDispatcher("/residentsList.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();  // You can log it for debugging purposes
                request.setAttribute("errorMessage", "Error fetching resident details.");
                request.getRequestDispatcher("/residentsList.jsp").forward(request, response);
            }
        } else {
            // If user is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
        }
    }
}