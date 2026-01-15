package Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginController") // Ensure this matches the action in your JSP form
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDAO loginDAO = new LoginDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        boolean isValidUser = loginDAO.validateUser(email, password, role);

        if (isValidUser) {
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", email);
            session.setAttribute("userRole", role);

            if ("customer".equalsIgnoreCase(role)) {
                response.sendRedirect("CustomerController");
            } else if ("employee".equalsIgnoreCase(role)) {
                response.sendRedirect("adminDashboard.jsp");
            }
        } else {
            request.setAttribute("errorMessage", "Invalid email, password, or role.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
