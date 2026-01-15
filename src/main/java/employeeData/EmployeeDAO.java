package employeeData;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {

    // Database connection method
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/hostel", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error establishing database connection.", e);
        }
    }

    // Method to list all employees
    public ArrayList<employee> listAllEmployees() {
        ArrayList<employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                // Create an employee object and add it to the list
                employee emp = new employee(
                        rs.getInt("employeeID"),
                        rs.getString("emp_name"),
                        rs.getString("emp_email"),
                        rs.getString("emp_password"),
                        rs.getString("emp_phoneNum"),
                        rs.getDate("emp_dob")
                );
                employees.add(emp); // Add the employee object to the list
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
    }

    // Method to add a new employee
    public boolean addEmployee(employee emp) {
        String sql = "INSERT INTO employee (emp_name, emp_email, emp_password, emp_phoneNum, emp_dob) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set parameters
            statement.setString(1, emp.getEmp_name());
            statement.setString(2, emp.getEmp_email());
            statement.setString(3, emp.getEmp_password());
            statement.setString(4, emp.getEmp_phoneNum());
            
            // Handle emp_dob null value
            if (emp.getEmp_dob() != null) {
                statement.setDate(5, new java.sql.Date(emp.getEmp_dob().getTime()));
            } else {
                statement.setNull(5, Types.DATE); // Set null if date is not available
            }

            // Execute SQL
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Return true if at least one row was inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Method to get an employee by ID
    public employee getEmployeeById(int employeeID) {
        employee emp = null;
        String query = "SELECT * FROM employee WHERE employeeID = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, employeeID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                emp = new employee(
                        rs.getInt("employeeID"),
                        rs.getString("emp_name"),
                        rs.getString("emp_email"),
                        rs.getString("emp_password"),
                        rs.getString("emp_phoneNum"),
                        rs.getDate("emp_dob")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emp;
    }

    // Method to update an existing employee
    public boolean updateEmployee(employee emp) {
        String query = "UPDATE employee SET emp_name = ?, emp_email = ?, emp_password = ?, emp_phoneNum = ?, emp_dob = ? WHERE employeeID = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, emp.getEmp_name());
            ps.setString(2, emp.getEmp_email());
            ps.setString(3, emp.getEmp_password());
            ps.setString(4, emp.getEmp_phoneNum());
            ps.setDate(5, new java.sql.Date(emp.getEmp_dob().getTime()));
            ps.setInt(6, emp.getEmployeeID());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete an employee by ID
    public boolean deleteEmployeeById(int employeeID) {
        String query = "DELETE FROM employee WHERE employeeID = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, employeeID);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
