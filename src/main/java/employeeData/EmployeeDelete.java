package employeeData;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/employeeDelete")
public class EmployeeDelete extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));

        // Use DAO to delete the employee from the database
        EmployeeDAO dao = new EmployeeDAO();
        boolean success = dao.deleteEmployeeById(employeeID);

        // Redirect to appropriate page
        if (success) {
            response.sendRedirect("employeeView");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
