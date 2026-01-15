<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="rent.Rent" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rent Update</title>
</head>
<body>

<h2>Rent Update</h2>

<% 
    Rent rents = (Rent) request.getAttribute("rent");
    if (rents != null) {
%>
        <form action="rentUpdate" method="POST">
            <input type="hidden" name="rentID" value="<%= rents.getRentID() %>">
            
             <label for="rent_amount">Rent Amount:</label>
            <input type="text" id="rent_amount" name="rent_amount" value="<%= rents.getRent_amount() %>"><br>
            
            <label for="rent_paymentStatus">Room Number:</label>
            <input type="text" id="rent_paymentStatus" name="rent_paymentStatus" value="<%= rents.getRent_paymentStatus() %>"><br>

			 <label for="rent_Due">Rent Due:</label>
            <input type="text" id="rent_Due" name="rent_Due" value="<%= rents.getRent_Due() %>"><br>

            <button type="submit">Update Rent</button>
        </form>
<% 
    } else { 
%>
        <p>Rent data not found.</p>
<% 
    }
%>

</body>
</html>