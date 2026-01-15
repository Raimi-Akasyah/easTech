package complaint;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.List;

@WebServlet("/complaintView")
public class ComplaintView extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Process GET requests to view complaints
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComplaintDAO complaintDAO = new ComplaintDAO(); // Create DAO object
        List<complaint> complaints = complaintDAO.listAllComplaints(); // Fetch all complaints from the DAO

        if (complaints != null && !complaints.isEmpty()) {
            // If complaints are found, pass the list to the JSP page
            request.setAttribute("complaintList", complaints);
            System.out.println("Complaints found: " + complaints.size()); // Log complaint count for debugging
            request.getRequestDispatcher("complaintView.jsp").forward(request, response);
        } else {
            // If no complaints found, set an error message
            request.setAttribute("errorMessage", "No complaints found.");
            request.getRequestDispatcher("complaintView.jsp").forward(request, response);
        }
    }
}
