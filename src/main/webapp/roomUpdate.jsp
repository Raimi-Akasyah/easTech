<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="residentData.Room" %>
<html>
<head>
    <title>Room Update</title>
</head>
<body>

<h2>Room Update</h2>

<% 
    Room room = (Room) request.getAttribute("room");
    if (room != null) {
%>
        <form action="roomUpdate" method="POST">
            <input type="hidden" name="roomID" value="<%= room.getRoomID() %>">
            
            <label for="roomType">Room Type:</label>
            <select id="roomType" name="roomType" value="<%= room.getRoomType() %>"><br>
                    <option value="Single">Single</option>
                    <option value="Double">Double</option>
            </select><br><br>
            
            <label for="roomNumber">Room Number:</label>
            <input type="text" id="roomNumber" name="roomNumber" value="<%= room.getRoomNumber() %>"><br><br>

            <label for="roomBlock">Room Block</label>
            <select id="roomBlock" name="roomBlock" value="<%= room.getRoomBlock() %>"><br>
                    <option value="Block A">Block A</option>
                    <option value="Block B">Block B</option>
                    <option value="Block C">Block C</option>
                </select><br><br>

            <label for="available">Available:</label>
            <select id="available" name="available" value="<%= room.getAvailable() %>"><br>
                    <option value="Available">Available</option>
                    <option value="Not available">Not available</option>
                    <option value="Pending">Pending</option>
                </select><br><br>

            <button type="submit">Update Room</button>
        </form>
<% 
    } else { 
%>
        <p>Room data not found.</p>
<% 
    }
%>
    <a href="roomController">Back to Room List</a>
    
</body>
</html>
