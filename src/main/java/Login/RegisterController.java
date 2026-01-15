package Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        System.out.println("Received data: Name=" + name + ", Email=" + email + ", Role=" + role);

        login log = new login(name, email, password, role);

        if (LoginDAO.registerUser(log)) {
            System.out.println("Registration successful");
            response.sendRedirect("Log in.jsp");
        } else {
            System.out.println("Registration failed");
            request.setAttribute("errorMessage", "Registration failed.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
    }

}
