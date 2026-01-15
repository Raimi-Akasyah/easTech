package rent;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/rentView")
public class RentView extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RentDAO rentDAO = new RentDAO();
        ArrayList<Rent> rents = rentDAO.listAllRents();

        request.setAttribute("rents", rents);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rentView.jsp");
        dispatcher.forward(request, response);
    }
}