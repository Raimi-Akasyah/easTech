package residentData;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/customerUpdate")
public class CustomerUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET request to pre-fill the form with existing customer data
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomerById(customerId);

        if (customer != null) {
            request.setAttribute("customer", customer);
            RequestDispatcher dispatcher = request.getRequestDispatcher("customerUpdate.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Customer not found.");
            response.sendRedirect("customerView");
        }
    }

    // Handle POST request to update the customer details
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Print all request parameters for debugging
            System.out.println("Request Parameters: ");
            for (String paramName : request.getParameterMap().keySet()) {
                System.out.println(paramName + ": " + request.getParameter(paramName));
            }

            int customerId = Integer.parseInt(request.getParameter("id"));
            String fullName = request.getParameter("full_name");
            String icNumber = request.getParameter("ic_number");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phone_number");
            String dateBirthStr = request.getParameter("date_birth");
            String room = request.getParameter("room");
            String startDateStr = request.getParameter("start_date");
            String endDateStr = request.getParameter("end_date");

            // Log values for debugging
            System.out.println("Full Name: " + fullName);
            System.out.println("IC Number: " + icNumber);
            System.out.println("Email: " + email);
            System.out.println("Phone Number: " + phoneNumber);
            System.out.println("Date of Birth: " + dateBirthStr);
            System.out.println("Room: " + room);
            System.out.println("Start Date: " + startDateStr);
            System.out.println("End Date: " + endDateStr);

            // Create a new Customer object with updated data
            Customer updatedCustomer = new Customer(customerId, fullName, icNumber, email, phoneNumber, dateBirthStr, room, startDateStr, endDateStr);

            // Update the customer in the database
            CustomerDAO customerDAO = new CustomerDAO();
            boolean success = customerDAO.updateCustomer(updatedCustomer);

            if (success) {
                response.sendRedirect("customerView");
            } else {
                request.setAttribute("errorMessage", "Failed to update customer. Please try again.");
                request.setAttribute("customer", updatedCustomer);
                request.getRequestDispatcher("customerUpdate.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("customerUpdate.jsp").forward(request, response);
        }
    }
}
