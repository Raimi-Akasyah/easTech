package complaint;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/complaintUpdate")
public class ComplaintUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // To handle GET request for displaying complaint details in the update form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get complaint ID from the request
        int complaintID = Integer.parseInt(request.getParameter("complaintID"));

        // Get complaint details from DAO
        ComplaintDAO dao = new ComplaintDAO();
        complaint complaint = dao.getComplaintById(complaintID);

        // Check if complaint was found and pass to JSP
        if (complaint != null) {
            request.setAttribute("complaint", complaint);
            request.getRequestDispatcher("complaintUpdate.jsp").forward(request, response);
        } else {
            // If complaint not found, set error message and redirect to complaint list
            request.setAttribute("errorMessage", "Complaint not found.");
            request.getRequestDispatcher("complaintView").forward(request, response);
        }
    }

    // To handle POST request for updating the complaint details
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the updated complaint details from the form
        int complaintID = Integer.parseInt(request.getParameter("complaintID"));
        String complaintDescription = request.getParameter("complaintDescription");
        String cmpPriority = request.getParameter("cmp_priority");
        String cmpStatus = request.getParameter("cmp_status");
        String cmpDateStr = request.getParameter("cmp_date");

        // Convert the Date string to java.sql.Date
        java.sql.Date cmpDate = null;
        if (cmpDateStr != null && !cmpDateStr.isEmpty()) {
            cmpDate = java.sql.Date.valueOf(cmpDateStr);
        }

        // Create complaint object with complaintDescription as the first parameter
        complaint complaint = new complaint(complaintID, complaintDescription, cmpPriority, cmpStatus, cmpDate);

        // Update complaint in the database
        ComplaintDAO dao = new ComplaintDAO();
        boolean success = dao.updateComplaint(complaint);

        // Handle the result of the update
        if (success) {
            // On success, redirect to the complaint list page
            response.sendRedirect("complaintView");
        } else {
            // On failure, set an error message and forward back to the update page
            request.setAttribute("errorMessage", "Failed to update complaint. Please try again.");
            request.setAttribute("complaint", complaint); // Re-populate the form with the old data
            request.getRequestDispatcher("complaintUpdate.jsp").forward(request, response);
        }
    }
}
