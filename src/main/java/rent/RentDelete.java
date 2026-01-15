package rent;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/rentDelete")
public class RentDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rentID = Integer.parseInt(request.getParameter("rentID"));

        RentDAO rentDAO = new RentDAO();
        boolean success = rentDAO.deleteRentById(rentID);

        if (success) {
            response.sendRedirect("rentView");
        } else {
            response.getWriter().write("Error deleting rent record");
        }
    }
}
