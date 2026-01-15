<%@ page import="java.util.*, rent.Rent" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rent List</title>
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
        <h2>Rent Details</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Rent ID</th>
                    <th>Rent Amount</th>
                    <th>Payment Status</th>
                    <th>Rent Due</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
<%
    ArrayList<Rent> rents = (ArrayList<Rent>) request.getAttribute("rents");
    if (rents != null && !rents.isEmpty()) {
        for (Rent rent : rents) {
%>
            <tr>
                <td><%= rent.getRentID() %></td>
                <td><%= rent.getRent_amount() %></td>
                <td><%= rent.getRent_paymentStatus() %></td>
                <td><%= rent.getRent_Due() %></td>
                <td>
                                    <!-- Update Button -->
                                    <form method="GET" action="rentUpdate">
                                        <input type="hidden" name="rentID" value="<%= rent.getRentID() %>">
                                        <button type="submit">Update</button>
                                    </form>

                                    <!-- Delete Form -->
                                    <form action="rentDelete" method="POST">
   										<input type="hidden" name="rentID" value="<%= rent.getRentID()%>">
    									<button type="submit" class="btn btn-danger">Delete</button>
									</form>

                                </td>
                            </tr>
<%
        }
    } else {
        out.println("No rent available.");
    }
%>
            </tbody>
    </table>
    <br><br>
    <a href="rentAdd"><input type="button" value="Add"></a>
<a href="adminDashboard.jsp"><input type="button" value="Back"></a>
</body>
</html>