<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, complaint.complaint" %>
<!DOCTYPE html>
<html>
<head>
    <title>Complaint List</title>
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
   <div class="container mt-5">
        <h2>Complaint Details</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Complaint ID</th>
                    <th>Complaint Desciption</th>
                    <th>Priority</th>
                    <th>Status</th>
                    <th>Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
<%
    // Retrieve the complaint list attribute
    List<complaint> complaints = (List<complaint>) request.getAttribute("complaintList");
    
    if (complaints != null && !complaints.isEmpty()) {
        for (complaint cmp : complaints) {
%>
            <tr>
                <td><%= cmp.getComplaintID() %></td>
                <td><%= cmp.getComplaintDescription() %></td>
                <td><%= cmp.getCmp_priority() %></td>
                <td><%= cmp.getCmp_status() %></td>
                <td><%= cmp.getCmp_Date() %></td>
                <td>
                    <!-- Update Button -->
                    <form method="GET" action="complaintUpdate">
                        <input type="hidden" name="complaintID" value="<%= cmp.getComplaintID() %>">
                        <button type="submit">Update</button>
                    </form>

                    <!-- Delete Form -->
                    <form action="complaintDelete" method="POST">
                        <input type="hidden" name="complaintID" value="<%= cmp.getComplaintID() %>">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>

                </td>
            </tr>
<%
        }
    } else {
        out.println("No complaints available.");
    }
%>
            </tbody>
        </table>
        <br><br>
        <a href="complaintAdd"><input type="button" value="Add Complaint"></a>
        <a href="adminDashboard.jsp"><input type="button" value="Back"></a>
    </div>
</body>
</html>
