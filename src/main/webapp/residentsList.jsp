<%@ page import="booking.residentInfo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resident Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #ffffff;
            text-align: center;
        }
        h2 {
            color: #000;
        }
        table {
            width: 90%;
            margin: 0 auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #e0e0e0;
            color: black;
        }
    </style>
</head>
<body>
    <h2>Resident Information</h2>
    <table>
        <tr>
            <th>Name</th>
            <th>No IC</th>
            <th>Room Type</th>
            <th>Room Number</th>
            <th>Room Block</th>
            <th>Availability</th>
            <th>Rent Amount</th>
            <th>Rent Payment Status</th>
            <th>Rent Due</th>
        </tr>
        <%
            List<residentInfo> residents = (List<residentInfo>) request.getAttribute("residents");
            if (residents != null) {
                for (residentInfo res : residents) {
        %>
        <tr>
            <td><%= res.getCustomerName() %></td>
            <td><%= res.getNoIC() %></td>
            <td><%= res.getRoomType() %></td>
            <td><%= res.getRoomNumber() %></td>
            <td><%= res.getRoomBlock() %></td>
            <td><%= res.getAvailable() %></td>
            <td><%= res.getRentAmount() %></td>
            <td><%= res.getRentPaymentStatus() %></td>
            <td><%= res.getRentDue() != null ? res.getRentDue().toString() : "N/A" %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="9">No Resident Data Available</td>
        </tr>
        <% } %>
    </table>
    
    <br><br>
    <button onclick="history.back()">Back</button>
</body>
</html>
