<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="employeeData.employee" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Employee</title>
</head>
<body>
    <h2>Update Employee</h2>

    <%
        // Cast the "employee" attribute to the correct type
        employee emp = (employee) request.getAttribute("employee");
        if (emp == null) {
    %>
        <p style="color: red;">Employee data not found. Please try again.</p>
        <a href="employeeList.jsp">Back to Employee List</a>
    <%
        } else {
    %>
    <form action="employeeUpdate" method="POST">
        <input type="hidden" name="employeeID" value="<%= emp.getEmployeeID() %>">

        <label for="emp_name">Name:</label>
        <input type="text" id="emp_name" name="emp_name" value="<%= emp.getEmp_name() %>" required><br><br>

        <label for="emp_email">Email:</label>
        <input type="email" id="emp_email" name="emp_email" value="<%= emp.getEmp_email() %>" required><br><br>

        <label for="emp_password">Password:</label>
        <input type="password" id="emp_password" name="emp_password" value="<%= emp.getEmp_password() %>" required><br><br>

        <label for="emp_phoneNum">Phone Number:</label>
        <input type="text" id="emp_phoneNum" name="emp_phoneNum" value="<%= emp.getEmp_phoneNum() %>" required><br><br>

        <label for="emp_dob">Date of Birth:</label>
        <input type="date" id="emp_dob" name="emp_dob" value="<%= emp.getEmp_dob() %>"><br><br>

        <input type="submit" value="Update Employee">
    </form>

    <br>
    <a href="employeeView">Back to Employee List</a>
    <%
        }
    %>
</body>
</html>
