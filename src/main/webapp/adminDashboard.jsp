<%@ page import="java.sql.*" %>
<%
    // Database connection details
    String url = "jdbc:mysql://localhost:3306/hostel"; // Change to your DB name
    String user = "root"; // Default XAMPP user
    String password = ""; // Default is empty in XAMPP

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    int totalResidents = 0;
    int totalEmployees = 0;
    int totalRooms = 0;
    int totalComplaints = 0;
    int openComplaints = 0;
    int pendingComplaints = 0;
    int completeComplaints = 0;
    int pendingRooms = 0;
    int availableRooms = 0;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL Driver
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();

        // Fetch total residents
        rs = stmt.executeQuery("SELECT COUNT(*) FROM residents");
        if (rs.next()) totalResidents = rs.getInt(1);

        // Fetch total employees
        rs = stmt.executeQuery("SELECT COUNT(*) FROM employee");
        if (rs.next()) totalEmployees = rs.getInt(1);

        // Fetch total rooms
        rs = stmt.executeQuery("SELECT COUNT(*) FROM room");
        if (rs.next()) totalRooms = rs.getInt(1);

        // Fetch total complaints
        rs = stmt.executeQuery("SELECT COUNT(*) FROM complaint");
        if (rs.next()) totalComplaints = rs.getInt(1);

        // Fetch complaint status breakdown
        rs = stmt.executeQuery("SELECT cmp_status, COUNT(*) FROM complaint GROUP BY cmp_status");
        while (rs.next()) {
            String status = rs.getString(1);
            int count = rs.getInt(2);
            if (status.equalsIgnoreCase("Open")) openComplaints = count;
            if (status.equalsIgnoreCase("Pending")) pendingComplaints = count;
            if (status.equalsIgnoreCase("Complete")) completeComplaints = count;
        }

     // Count total rooms
        rs = stmt.executeQuery("SELECT COUNT(*) FROM room");
        if (rs.next()) totalRooms = rs.getInt(1);

        // Count available rooms
        rs = stmt.executeQuery("SELECT COUNT(*) FROM room WHERE available = 'Available'");
        if (rs.next()) availableRooms = rs.getInt(1);

        // Count pending rooms
        rs = stmt.executeQuery("SELECT COUNT(*) FROM room WHERE available = 'Pending'");
        if (rs.next()) pendingRooms = rs.getInt(1);

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrator Dashboard</title>
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
            <a class="navbar-brand" href="#">easTech | Administrator Dashboard</a>
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
    
    <div class="container dashboard-container">
        <div class="row mb-4">
            <div class="col-md-3">
                <div class="card text-white bg-primary p-3">
                    <h5>Residents</h5>
                    <h3><%= totalResidents %></h3>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white bg-success p-3">
                    <h5>Employees</h5>
                    <h3><%= totalEmployees %></h3>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white bg-warning p-3">
                    <h5>Rooms</h5>
                    <h3><%= totalRooms %></h3>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white bg-danger p-3">
                    <h5>Complaints</h5>
                    <h3><%= totalComplaints %></h3>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="col-md-6">
                <div class="chart-container">
                    <h5>Complaint Status</h5>
                    <canvas id="complaintChart"></canvas>
                </div>
            </div>
            <div class="col-md-6">
                <div class="chart-container">
                    <h5>Room Status</h5>
                    <canvas id="roomChart"></canvas>
                </div>
            </div>
        </div>
    </div>
    
    <script>
        const complaintChart = new Chart(document.getElementById('complaintChart'), {
            type: 'doughnut',
            data: {
                labels: ['Open', 'Pending', 'Complete'],
                datasets: [{
                    data: [<%= openComplaints %>, <%= pendingComplaints %>, <%= completeComplaints %>],
                    backgroundColor: ['green', 'cyan', 'blue']
                }]
            }
        });

        const roomChart = new Chart(document.getElementById('roomChart'), {
            type: 'bar',
            data: {
                labels: ['Pending', 'Available'],
                datasets: [{
                    data: [<%= pendingRooms %>, <%= availableRooms %>], 
                    backgroundColor: ['cyan', 'green'],
                    label: 'Room Status'
                }]
            },
            options: {
                plugins: {
                    legend: {
                        display: true,
                        position: 'top',
                        labels: {
                            generateLabels: function(chart) {
                                return [
                                    {
                                        text: 'Pending',
                                        fillStyle: 'cyan'
                                    },
                                    {
                                        text: 'Available',
                                        fillStyle: 'green'
                                    }
                                ];
                            }
                        }
                    }
                }
            }
        });

    </script>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
