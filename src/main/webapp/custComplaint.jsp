<%@ page import="java.util.List" %>
<%@ page import="complaint.complaint" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Complaints</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        /* Navbar Styles */
        .navbar {
            background-color: #333;
            padding: 14px;
            text-align: center;
        }

        .navbar h1 {
            color: white;
            margin: 0;
        }

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

        .success-message {
            background-color: #e6f9e6;
            color: #2d862d;
            font-weight: bold;
            padding: 10px;
            border-radius: 5px;
            display: flex;
            align-items: center;
            width: fit-content;
        }

        .success-message::before {
            content: '\2714'; /* Checkmark symbol */
            color: #fff; /* Icon color */
            font-weight: bold;
            margin-right: 8px;
            padding: 4px; /* Smaller padding */
            border-radius: 50%; /* Makes it a circle */
            background-color: #2d862d; /* Green background for circle */
            width: 20px; /* Smaller circle size */
            height: 20px; /* Smaller circle size */
            text-align: center;
            line-height: 20px; /* Aligns the checkmark vertically in the center */
            display: inline-block;
        }

        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <!-- Navbar with Customer Complaints Text -->
    <div class="navbar">
        <h1>Customer Complaints</h1>
    </div>

    <% 
        String successMessage = (String) request.getAttribute("successMessage");
        if (successMessage != null) { 
    %>
        <p class="success-message"><%= successMessage %></p>
    <% } %>

    <% 
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { 
    %>
        <p class="error-message"><%= errorMessage %></p>
    <% } %>

    <h3>Your Complaints</h3>
    <table>
        <thead>
            <tr>
                <th>Complaint ID</th>
                <th>Description</th>
                <th>Priority</th>
                <th>Status</th>
                <th>Date Submitted</th>
            </tr>
        </thead>
        <tbody>
        <% 
            List<complaint> complaints = (List<complaint>) request.getAttribute("complaints");
            if (complaints != null && !complaints.isEmpty()) {
                for (complaint cmp : complaints) { 
        %>
            <tr>
                <td><%= cmp.getComplaintID() %></td>
                <td><%= cmp.getComplaintDescription() %></td>
                <td><%= cmp.getCmp_priority() %></td>
                <td><%= cmp.getCmp_status() %></td>
                <td><%= cmp.getCmp_Date() %></td>
            </tr>
        <% 
                }
            } else { 
        %>
            <tr>
                <td colspan="5">No complaints found.</td>
            </tr>
        <% } %>
        </tbody>
    </table>

    <h3>Submit Your Complaint</h3>
    <form method="POST" action="submitComplaint">
        <label for="complaintDescription">Complaint Description:</label><br>
        <textarea id="complaintDescription" name="complaintDescription" rows="4" cols="50" required></textarea><br><br>

        <label for="cmp_priority">Priority:</label><br>
        <select id="cmp_priority" name="cmp_priority">
            <option value="High">High</option>
            <option value="Medium">Medium</option>
            <option value="Low">Low</option>
        </select><br><br>

        <button type="submit">Submit Complaint</button>
        <button onclick="window.location.href='CustomerController'">Back to Customer Page</button><br><br>
    </form>
</body>
</html>
