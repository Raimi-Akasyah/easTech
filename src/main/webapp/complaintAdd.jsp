<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="complaint.complaint" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Complaint</title>
</head>
<body>
    <h2>Add New Complaint</h2>
    <form action="complaintAdd" method="POST">
    
		<label for="complaintDescription">Description:</label>
        <input type="text" id="complaintDescription" name="complaintDescription" required><br><br>    
    
        <label for="cmp_priority">Priority:</label>
            <select id="cmp_priority" name="cmp_priority" required><br>
                    <option value="High">High</option>
                    <option value="Medium">Medium</option>
                    <option value="Low">Low</option>
                </select><br><br>

            <label for="cmp_status">Status:</label>
            <select id="cmp_status" name="cmp_status" required><br>
                    <option value="Open">Open</option>
                    <option value="Pending">Pending</option>
                    <option value="Complete">Complete</option>
                </select><br><br>

        <label for="cmp_date">Date:</label>
        <input type="date" id="cmp_date" name="cmp_date" required><br><br>

        <input type="submit" value="Add Complaint">
    </form>

    <br>
    <a href="complaintView">Back to Complaint List</a>
</body>
</html>
