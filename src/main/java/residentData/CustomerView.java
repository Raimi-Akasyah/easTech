package residentData;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/customerView")
public class CustomerView extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDAO customerDAO = new CustomerDAO();
        ArrayList<Customer> customers = customerDAO.listAllCustomers();

        request.setAttribute("customers", customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("residentsView.jsp");
        dispatcher.forward(request, response);
    }
}
