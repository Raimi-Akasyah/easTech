package complaint;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/complaintDelete")
public class ComplaintDelete extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the complaint ID from the request
        int complaintID = Integer.parseInt(request.getParameter("complaintID"));

        // Use DAO to delete the complaint from the database
        ComplaintDAO dao = new ComplaintDAO();
        boolean success = dao.deleteComplaintById(complaintID);

        // Redirect to appropriate page based on success or failure
        if (success) {
            // Redirect to the complaint view page if deletion is successful
            response.sendRedirect("complaintView");
        } else {
            // Redirect to an error page if deletion fails
            response.sendRedirect("error.jsp");
        }
    }
}
