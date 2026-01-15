package employeeData;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.List;

@WebServlet("/employeeView")
public class EmployeeView extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<employee> employees = employeeDAO.listAllEmployees(); // Fetch all employees from the DAO

        if (employees != null && !employees.isEmpty()) {
            // If employees are found, pass the list to the JSP page
            request.setAttribute("employeeList", employees);
            System.out.println("Employees found: " + employees.size()); // Log employee count for debugging
            request.getRequestDispatcher("employeeView.jsp").forward(request, response);
        } else {
            // If no employees found, set an error message
            request.setAttribute("errorMessage", "No employees found.");
            request.getRequestDispatcher("employeeView.jsp").forward(request, response);
        }
    }
}

