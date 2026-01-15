<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.*, residentData.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>Resident Data</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .dashboard-container {
            margin: 20px;
        }
        .card {
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .chart-container {
            padding: 20px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="adminDashboard.jsp">easTech | Administrator Dashboard</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="customerView">Residents Data</a></li>
                    <li class="nav-item"><a class="nav-link" href="employeeView">Employees Data</a></li>
                    <li class="nav-item"><a class="nav-link" href="roomController">Room Data</a></li>
                    <li class="nav-item"><a class="nav-link" href="rentView">Rent Data</a></li>
                    <li class="nav-item"><a class="nav-link" href="complaintView">Complaint Data</a></li>
                    <li class="nav-item"><a class="nav-link" href="Log in.jsp">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
    
    
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
    <h2>Resident Details</h2>

    <% if (request.getAttribute("errorMessage") != null) { %>
        <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
    <% } %>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>IC Number</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Date of Birth</th>
                <th>Room</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                ArrayList<Customer> customers = (ArrayList<Customer>) request.getAttribute("customers");
                if (customers != null && !customers.isEmpty()) {
                    for (Customer customer : customers) { 
            %>
                <tr>
                    <td><%= customer.getId() %></td>
                    <td><%= customer.getFullName() %></td>
                    <td><%= customer.getNoIC() %></td>
                    <td><%= customer.getEmail() %></td>
                    <td><%= customer.getPhoneNum() %></td>
                    <td><%= customer.getDateBirth() %></td>
                    <td><%= customer.getRoom() %></td>
                    <td><%= customer.getStartDate() %></td>
                    <td><%= customer.getEndDate() %></td>
                    <td>
                        <!-- Update Button -->
                        <form method="GET" action="customerUpdate">
                            <input type="hidden" name="id" value="<%= customer.getId() %>">
                            <button type="submit">Update</button>
                        </form>

                        <!-- Delete Button -->
                        <form method="POST" action="customerDelete">
                            <input type="hidden" name="id" value="<%= customer.getId() %>">
                            <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
                        </form>
                    </td>
                </tr>
            <% 
                    }
                } else { 
            %>
                <tr>
                    <td colspan="10">No residents found.</td>
                </tr>
            <% } %>
        </tbody>
    </table>

    <br><br>
    <a href="customerAdd"><input type="button" value="Add"></a>
    <a href="adminDashboard.jsp"><input type="button" value="Back"></a>
</body>
</html>
