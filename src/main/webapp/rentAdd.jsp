<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, rent.Rent" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Rent</title>
<style>
    body {
        font-family: Arial, sans-serif;
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
        <h1>Add Rent</h1>
        <form action="rentAdd" method="POST">
            <div class="form-group">
                <label for="rent_amount">Rent Amount</label>
                <input type="text" class="form-control" id="rent_amount" placeholder="Insert Rent Amount" name="rent_amount">
            </div>
            <div class="form-group">
                <label for="rent_paymentStatus">Payment Status</label>
                <input type="text" class="form-control" id="rent_paymentStatus" placeholder="Insert Payment Status" name="rent_paymentStatus">
            </div>
            <div class="form-group">
                <label for="rent_Due">Rent Due Date</label>
                <input type="date" class="form-control" id="rent_Due" name="rent_Due">
            </div>
            <button type="submit" class="btn">Add Rent</button>
        </form>
    </div>
</body>
</html>
