<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.*, residentData.Room" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Room</title>
<style>
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .container {
        width: 400px;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        background-color: #f9f9f9;
    }
    h1 {
        text-align: center;
        margin-bottom: 20px;
    }
    .form-group {
        margin-bottom: 15px;
    }
    label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }
    .form-control {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    .btn {
        display: block;
        width: 100%;
        padding: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .btn:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>Add Room</h1>
        <form action="roomAdd" method="POST">
        <div class="form-group">
                <label for="roomType">Room Type</label>
                <select class="form-control" id="roomType" name="roomType">
                    <option value="Single">Single</option>
                    <option value="Double">Double</option>
                </select>
            </div>
            <div class="form-group">
                <label for="roomNumber">Room Number</label>
                <input type="text" class="form-control" id="roomNumber" placeholder="Insert Room Number" name="roomNumber">
            </div>
            <div class="form-group">
                <label for="roomBlock">Room Block</label>
                <select class="form-control" id="roomBlock" name="roomBlock">
                    <option value="Block A">Block A</option>
                    <option value="Block B">Block B</option>
                    <option value="Block C">Block C</option>
                </select>
            </div>
            <div class="form-group">
                <label for="available">Availability</label>
                <select class="form-control" id="available" name="available">
                    <option value="Available">Available</option>
                    <option value="Not available">Not available</option>
                    <option value="Pending">Pending</option>
                </select>
            </div>
            <button type="submit" class="btn">Add</button>
        </form>
        <br>
    <a href="roomController">Back to Room List</a>
    </div>
</body>
</html>
