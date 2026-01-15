package residentData;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/customerDelete")
public class CustomerDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        CustomerDAO customerDAO = new CustomerDAO();

        if (customerDAO.deleteCustomerById(customerId)) {
            response.sendRedirect("customerView");
        } else {
            request.setAttribute("errorMessage", "Failed to delete the customer.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("customerView");
            dispatcher.forward(request, response);
        }
    }
}
