<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="complaint.complaint" %>
<html>
<head>
    <title>Update Complaint</title>
</head>
<body>
    <h2>Update Complaint</h2>
    
<%
    // Retrieve the complaint object from the request
    complaint complaint = (complaint) request.getAttribute("complaint");
    
    if (complaint != null) {
%>
        <!-- If the complaint object is available, display the update form -->
        <form action="complaintUpdate" method="post">
            <input type="hidden" name="complaintID" value="<%= complaint.getComplaintID() %>" />

			<label for="complaintDescription">Description:</label>
            <input type="text" id="complaintDescription" name="complaintDescription" value="<%= complaint.getComplaintDescription() %>" required /><br/><br>

            <label for="cmp_priority">Priority:</label>
            <select id="cmp_priority" name="cmp_priority" value="<%= complaint.getCmp_priority() %>" required /><br/>
                    <option value="High">High</option>
                    <option value="Medium">Medium</option>
                    <option value="Low">Low</option>
                </select><br><br>

            <label for="cmp_status">Status:</label>
            <select id="cmp_status" name="cmp_status" value="<%= complaint.getCmp_status() %>" required /><br/>
                    <option value="Open">Open</option>
                    <option value="Pending">Pending</option>
                    <option value="Complete">Complete</option>
                </select><br><br>

            <label for="cmp_date">Date:</label>
            <input type="date" id="cmp_date" name="cmp_date" value="<%= complaint.getCmp_Date() %>" required /><br/><br>

            <input type="submit" value="Update Complaint" />
        </form>
<%
    } else {
%>
        <!-- If the complaint object is not found, display an error message -->
        <p>No complaint found to update.</p>
<%
    }
%>
    <a href="complaintView">Back to Complaint List</a>

</body>
</html>
