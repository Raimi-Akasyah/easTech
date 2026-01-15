<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.*, residentData.Customer" %>
<!Doctype html>
<html>
<head>
<title>Update Customer</title>
</head>
<body>
<%
    Customer customer = (Customer) request.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("customerView");
        return;
    }
%>

<h2>Update Customer</h2>

<% if (request.getAttribute("errorMessage") != null) { %>
    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
<% } %>

<form action="customerUpdate" method="POST">
    <input type="hidden" name="id" value="<%= customer.getId() %>">

    <label>Full Name:</label>
    <input type="text" name="full_name" value="<%= customer.getFullName() %>" required><br><br>

    <label>IC Number:</label>
    <input type="text" name="ic_number" value="<%= customer.getNoIC() %>" required><br><br>

    <label>Email:</label>
    <input type="email" name="email" value="<%= customer.getEmail() %>" required><br><br>

    <label>Phone Number:</label>
    <input type="text" name="phone_number" value="<%= customer.getPhoneNum() %>" required><br><br>

    <label>Date of Birth:</label>
    <input type="date" name="date_birth" value="<%= customer.getDateBirth() %>" required><br><br>

    <label>Room:</label>
    <input type="text" name="room" value="<%= customer.getRoom() %>" required><br><br>

    <label>Start Date:</label>
    <input type="date" name="start_date" value="<%= customer.getStartDate() %>" required><br><br>

    <label>End Date:</label>
    <input type="date" name="end_date" value="<%= customer.getEndDate() %>" required><br><br>

    <button type="submit">Update Customer</button><br><br>
</form>

<a href="customerView">Back to Customer List</a>

</body>
</html>

