<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="residentData.Room" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>easTech | Customer Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            animation: fadeIn 0.5s ease-in-out;
        }
        .navbar {
            margin-bottom: 20px;
            padding: 10px 20px;
        }
        .navbar-brand {
            font-weight: bold;
            color: #f8f9fa !important;
        }
        .navbar-nav .nav-link:hover {
            color: #f8f9fa !important;
            background-color: rgba(255, 255, 255, 0.2);
            border-radius: 5px;
        }
        h2 {
            color: #343a40;
            text-align: center;
            margin-bottom: 20px;
        }
        .table {
            animation: fadeIn 0.5s ease-in-out;
        }
        .table-hover tbody tr:hover {
            background-color: #f1f1f1;
            transition: background-color 0.3s ease;
        }
        .form-control {
            border-radius: 15px;
            padding: 15px;
            font-size: 1rem;
        }
        .btn-success {
            transition: transform 0.3s ease, background-color 0.3s ease;
        }
        .btn-success:hover {
            transform: scale(1.05);
            background-color: #28a745;
        }
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">EasTech | Customer Dashboard</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="residents">My Booking</a></li>
                    <li class="nav-item"><a class="nav-link" href="submitComplaint">Complaint</a></li>
                    <li class="nav-item"><a class="nav-link" href="Log in.jsp">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h2>Available Rooms</h2>
        <table class="table table-striped table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Room ID</th>
                    <th>Room Type</th>
                    <th>Room Number</th>
                    <th>Room Block</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                ArrayList<Room> rooms = (ArrayList<Room>) request.getAttribute("availableRooms");
                if (rooms != null && !rooms.isEmpty()) {
                    for (Room room : rooms) {
                %>
                <tr>
                    <td><%= room.getRoomID() %></td>
                    <td><%= room.getRoomType() %></td>
                    <td><%= room.getRoomNumber() %></td>
                    <td><%= room.getRoomBlock() %></td>
                    <td><%= room.getAvailable() %></td>
                </tr>
                <% 
                    }
                } else {
                %>
                <tr>
                    <td colspan="5" class="text-center">No rooms available</td>
                </tr>
                <% } %>
            </tbody>
        </table>
		<br>
        <h2>Customer Information</h2>
        <form method="POST" action="CustomerController">
            <div class="mb-3">
                <label for="fullName" class="form-label">Full Name</label>
                <input type="text" class="form-control" id="fullName" name="fullName" required>
            </div>
            <div class="mb-3">
                <label for="noIC" class="form-label">IC Number</label>
                <input type="text" class="form-control" id="noIC" name="noIC" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="phoneNum" class="form-label">Phone Number</label>
                <input type="text" class="form-control" id="phoneNum" name="phoneNum" required>
            </div>
            <div class="mb-3">
                <label for="dateBirth" class="form-label">Date of Birth</label>
                <input type="date" class="form-control" id="dateBirth" name="dateBirth" required>
            </div>


            <div class="mb-3">
                <label for="room" class="form-label">Room Number</label>
                <select class="form-control" id="room" name="room" required>
                    <option value="">Select a room</option>
                    <%
                    if (rooms != null && !rooms.isEmpty()) {
                        for (Room room : rooms) {
                            if ("Available".equalsIgnoreCase(room.getAvailable())) { 
                    %>
                            <option value="<%= room.getRoomNumber() %>">
                                <%= room.getRoomNumber() %> - <%= room.getRoomType() %>
                            </option>
                    <%
                            }
                        }
                    }
                    %>
                </select>
            </div>

            <div class="mb-3">
                <label for="startDate" class="form-label">Start Date</label>
                <input type="date" class="form-control" id="startDate" name="startDate" required>
            </div>
            <div class="mb-3">
                <label for="endDate" class="form-label">End Date</label>
                <input type="date" class="form-control" id="endDate" name="endDate" required>
            </div>

            <button type="submit" class="btn btn-success">Book</button>

        </form>
    </div>

    <% 
    String popupMessage = (String) request.getAttribute("popupMessage");
    if (popupMessage != null) { 
        String escapedMessage = popupMessage.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    %>
        <script>
            Swal.fire({
                icon: '<%= request.getAttribute("popupIcon") != null ? request.getAttribute("popupIcon") : "info" %>',
                title: 'Notification',
                text: "<%= escapedMessage %>",
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'OK'
            });
        </script>
    <% } %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

