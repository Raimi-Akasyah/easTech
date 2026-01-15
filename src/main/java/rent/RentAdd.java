package rent;

import java.io.IOException;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/rentAdd")
public class RentAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to the form page (roomAdd.jsp)
        response.sendRedirect("rentAdd.jsp");
    }	
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double rentAmount = Double.parseDouble(request.getParameter("rent_amount"));
        String rentPaymentStatus = request.getParameter("rent_paymentStatus");
        Date rentDue = java.sql.Date.valueOf(request.getParameter("rent_Due"));

        Rent rent = new Rent(0, rentAmount, rentPaymentStatus, rentDue);
        RentDAO rentDAO = new RentDAO();
        boolean success = rentDAO.saveRent(rent);

        if (success) {
            response.sendRedirect("rentView");
        } else {
            response.getWriter().write("Error adding rent record");
        }
    }
}
