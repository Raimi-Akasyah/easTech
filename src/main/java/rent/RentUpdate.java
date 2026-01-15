package rent;

import java.io.IOException;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/rentUpdate")
public class RentUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET request to pre-fill the form with existing rent data
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int rentID = Integer.parseInt(request.getParameter("rentID")); // Get the rentID from the URL
            RentDAO rentDAO = new RentDAO();
            Rent rent = rentDAO.getRentById(rentID); // Fetch the rent by ID

            if (rent != null) {
                request.setAttribute("rent", rent); // Set rent object as attribute for JSP
                RequestDispatcher dispatcher = request.getRequestDispatcher("rentUpdate.jsp");
                dispatcher.forward(request, response); // Forward to the update form
            } else {
                response.sendRedirect("rentView?error=RentNotFound"); // Redirect if no rent found
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("rentView?error=InvalidRentID"); // Redirect in case of an error
        }
    }

    // Handle POST request to update the rent details
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int rentID = Integer.parseInt(request.getParameter("rentID")); // Get the rentID from the form
            double rentAmount = Double.parseDouble(request.getParameter("rent_amount"));
            String rentPaymentStatus = request.getParameter("rent_paymentStatus");
            Date rentDue = java.sql.Date.valueOf(request.getParameter("rent_Due"));

            // Create a new Rent object with updated data
            Rent updatedRent = new Rent(rentID, rentAmount, rentPaymentStatus, rentDue);

            // Update the rent in the database
            RentDAO rentDAO = new RentDAO();
            boolean success = rentDAO.updateRent(updatedRent);

            if (success) {
                response.sendRedirect("rentView"); // Redirect to rent controller after successful update
            } else {
                request.setAttribute("errorMessage", "Failed to update rent. Please try again.");
                request.setAttribute("rent", updatedRent); // Set updated rent object as attribute for JSP
                request.getRequestDispatcher("rentUpdate.jsp").forward(request, response); // Forward back to the form if failed
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage()); // Set error message to be displayed in the JSP
            request.getRequestDispatcher("rentUpdate.jsp").forward(request, response); // Forward to the form in case of an error
        }
    }
}
