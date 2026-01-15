package employeeData;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/employeeUpdate")
public class EmployeeUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // To handle GET request for displaying employee details in the update form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get employee ID from the request
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));

        // Get employee details from DAO
        EmployeeDAO dao = new EmployeeDAO();
        employee emp = dao.getEmployeeById(employeeID);

        // Check if employee was found and pass to JSP
        if (emp != null) {
            request.setAttribute("employee", emp);
            request.getRequestDispatcher("employeeUpdate.jsp").forward(request, response);
        } else {
            // If employee not found, set error message and redirect to employee list
            request.setAttribute("errorMessage", "Employee not found.");
            request.getRequestDispatcher("employeeView").forward(request, response);
        }
    }

    // To handle POST request for updating the employee details
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the updated employee details from the form
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        String empName = request.getParameter("emp_name");
        String empEmail = request.getParameter("emp_email");
        String empPassword = request.getParameter("emp_password");
        String empPhoneNum = request.getParameter("emp_phoneNum");
        String empDobStr = request.getParameter("emp_dob");

        // Convert the DOB string to java.sql.Date
        java.sql.Date empDob = null;
        if (empDobStr != null && !empDobStr.isEmpty()) {
            empDob = java.sql.Date.valueOf(empDobStr);
        }

        // Create employee object
        employee emp = new employee(employeeID, empName, empEmail, empPassword, empPhoneNum, empDob);

        // Update employee in the database
        EmployeeDAO dao = new EmployeeDAO();
        boolean success = dao.updateEmployee(emp);

        // Handle the result of the update
        if (success) {
            // On success, redirect to the employee list page
            response.sendRedirect("employeeView");
        } else {
            // On failure, set an error message and forward back to the update page
            request.setAttribute("errorMessage", "Failed to update employee. Please try again.");
            request.setAttribute("employee", emp); // Re-populate the form with the old data
            request.getRequestDispatcher("employeeUpdate.jsp").forward(request, response);
        }
    }
}
