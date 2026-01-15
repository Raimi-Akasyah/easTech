package employeeData;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/employeeAdd")
public class EmployeeAdd extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to the form page (roomAdd.jsp)
        response.sendRedirect("employeeAdd.jsp");
    }	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String empName = request.getParameter("emp_name");
        String empEmail = request.getParameter("emp_email");
        String empPassword = request.getParameter("emp_password");
        String empPhoneNum = request.getParameter("emp_phoneNum");
        String empDobStr = request.getParameter("emp_dob");

        // Convert string to Date
        java.sql.Date empDob = null;
        if (empDobStr != null && !empDobStr.isEmpty()) {
            empDob = java.sql.Date.valueOf(empDobStr);
        }

        // Create Employee object
        employee newEmployee = new employee(0, empName, empEmail, empPassword, empPhoneNum, empDob);

        // Use DAO to add the employee to the database
        EmployeeDAO dao = new EmployeeDAO();
        boolean success = dao.addEmployee(newEmployee);

        // Redirect to appropriate page
        if (success) {
            response.sendRedirect("employeeView");
        } else {
            request.setAttribute("errorMessage", "Failed to add employee. Please try again.");
            request.getRequestDispatcher("employeeAdd.jsp").forward(request, response);
        }
    }
}
