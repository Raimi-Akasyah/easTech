<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, employeeData.employee" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            cursor: pointer;
            padding: 5px 10px;
        }
        form {
            display: inline;
        }
    </style>
</head>
<body>
   <div class="container mt-5">
        <h2>Employee Details</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Employee ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Date of Birth</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
<%
    ArrayList<employeeData.employee> employees = (ArrayList<employeeData.employee>) request.getAttribute("employeeList");
    if (employees != null && !employees.isEmpty()) {
        for (employeeData.employee emp : employees) {
%>
            <tr>
                <td><%= emp.getEmployeeID() %></td>
                <td><%= emp.getEmp_name() %></td>
                <td><%= emp.getEmp_email() %></td>
                <td><%= emp.getEmp_phoneNum() %></td>
                <td><%= emp.getEmp_dob() %></td>
                <td>
                    <!-- Update Button -->
                    <form method="GET" action="employeeUpdate">
                        <input type="hidden" name="id" value="<%= emp.getEmployeeID() %>">
                        <button type="submit">Update</button>
                    </form>

                    <!-- Delete Form -->
                    <form action="employeeDelete" method="POST">
                        <input type="hidden" name="employeeID" value="<%= emp.getEmployeeID() %>">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
<%
        }
    } else {
        out.println("No employees available.");
    }
%>
            </tbody>
        </table>
        <br><br>
        <a href="employeeAdd"><input type="button" value="Add"></a>
        <a href="adminDashboard.jsp"><input type="button" value="Back"></a>
    </div>
</body>
</html>
