package residentData;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/customerAdd")
public class CustomerAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to the form page (roomAdd.jsp)
        response.sendRedirect("customerAdd.jsp");
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("full_name");
        String noIC = request.getParameter("no_ic");
        String email = request.getParameter("email");
        String phoneNum = request.getParameter("phone_num");
        String dateBirth = request.getParameter("date_birth");
        String room = request.getParameter("room");
        String startDate = request.getParameter("start_date");
        String endDate = request.getParameter("end_date");

        Customer customer = new Customer(0, fullName, noIC, email, phoneNum, dateBirth, room, startDate, endDate);
        CustomerDAO customerDAO = new CustomerDAO();

        if (customerDAO.saveCustomer(customer)) {
            response.sendRedirect("customerView");
        } else {
            request.setAttribute("errorMessage", "Failed to add the customer. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("customerAdd");
            dispatcher.forward(request, response);
        }
    }
}
