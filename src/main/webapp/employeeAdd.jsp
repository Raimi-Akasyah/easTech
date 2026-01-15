<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="employeeData.employee" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Employee</title>
</head>
<body>
    <h2>Add New Employee</h2>
    <form action="employeeAdd" method="POST">
        <label for="emp_name">Name:</label>
        <input type="text" id="emp_name" name="emp_name" required><br><br>

        <label for="emp_email">Email:</label>
        <input type="email" id="emp_email" name="emp_email" required><br><br>

        <label for="emp_password">Password:</label>
        <input type="password" id="emp_password" name="emp_password" required><br><br>

        <label for="emp_phoneNum">Phone Number:</label>
        <input type="text" id="emp_phoneNum" name="emp_phoneNum" required><br><br>

        <label for="emp_dob">Date of Birth:</label>
        <input type="date" id="emp_dob" name="emp_dob"><br><br>

        <input type="submit" value="Add Employee">
    </form>

    <br>
    <a href="employeeView">Back to Employee List</a>
</body>
</html>
