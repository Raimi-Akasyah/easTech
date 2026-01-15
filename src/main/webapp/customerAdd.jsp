<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.*, residentData.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
    <h2>Add Customer</h2>

    <% if (request.getAttribute("errorMessage") != null) { %>
        <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
    <% } %>

    <form action="customerAdd" method="POST">
        <label>Full Name:</label>
        <input type="text" name="full_name" required><br><br>

        <label>IC Number:</label>
        <input type="text" name="no_ic" required><br><br>

        <label>Email:</label>
        <input type="email" name="email" required><br><br>

        <label>Phone Number:</label>
        <input type="text" name="phone_num" required><br><br>

        <label>Date of Birth:</label>
        <input type="date" name="date_birth"><br><br>

        <label>Room:</label>
        <input type="text" name="room" required><br><br>

        <label>Start Date:</label>
        <input type="date" name="start_date" required><br><br>

        <label>End Date:</label>
        <input type="date" name="end_date"><br><br>

        <input type="submit" value="Add Customer">
    </form>

    <br>
    <a href="customerView">Back to Customer List</a>
</body>
</html>
