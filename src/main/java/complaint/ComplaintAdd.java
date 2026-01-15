package complaint;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/complaintAdd")
public class ComplaintAdd extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Redirect to the form page
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("complaintAdd.jsp"); // Redirect to complaint add form page
    }

    // Handle form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String complaintDescription = request.getParameter("complaintDescription"); // Get complaintDescription from the form
        String cmpPriority = request.getParameter("cmp_priority");
        String cmpStatus = request.getParameter("cmp_status");
        String cmpDateStr = request.getParameter("cmp_date");

        // Convert string to Date
        java.sql.Date cmpDate = null;
        if (cmpDateStr != null && !cmpDateStr.isEmpty()) {
            cmpDate = java.sql.Date.valueOf(cmpDateStr);
        }

        // Create Complaint object with complaintDescription as the first parameter
        complaint newComplaint = new complaint(0, complaintDescription, cmpPriority, cmpStatus, cmpDate);

        // Use DAO to add the complaint to the database
        ComplaintDAO dao = new ComplaintDAO();
        boolean success = dao.saveComplaint(newComplaint);

        // Redirect to appropriate page
        if (success) {
            response.sendRedirect("complaintView"); // Redirect to the complaint list view
        } else {
            request.setAttribute("errorMessage", "Failed to add complaint. Please try again.");
            request.getRequestDispatcher("complaintAdd.jsp").forward(request, response); // Forward to form with error
        }
    }
}
