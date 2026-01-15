<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<style>
  body {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
    background-color: #f5f5f5;
  }

  form {
    border: 2px solid #ccc;
    border-radius: 10px;
    padding: 20px;
    background-color: #ffffff;
    width: 320px;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  }
  .welcome-message {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 10px;
    color: #333;
  }

  .subtitle {
    font-size: 18px;
    color: #555;
    margin-bottom: 20px;
  }

  h3 {
    text-align: center;
    margin-bottom: 20px;
  }

  .form-group {
    margin-bottom: 15px;
  }

  .form-group label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
  }

  .form-group input[type="email"],
  .form-group input[type="password"] {
    width: calc(100% - 10px); /* Adjust to fit perfectly within the box */
    padding: 8px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }

  .form-group input[type="radio"] {
    margin-right: 5px;
  }

  .form-group .radio-label {
    display: inline-block;
    margin-right: 15px;
  }

  button {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
  }

  button:hover {
    background-color: #0056b3;
  }

  p {
    text-align: center;
    margin-top: 15px;
  }

  a {
    color: #007bff;
    text-decoration: underline;
    font-weight: bold;
  }

  a:hover {
    text-decoration: none;
  }
</style>
</head>
<body>
	<div class="welcome-message">Welcome to EasTech</div>
    <div class="subtitle">EFFORTLESSLY MANAGE YOUR HOSTEL WITH EASE</div>
    
<form method="POST" action="${pageContext.request.contextPath}/LoginController">
    <h3>Login Page</h3>
    <div class="form-group">
        <label for="exampleInputEmail1">Email address</label>
        <input type="email" name="email" id="exampleInputEmail1" placeholder="Enter email">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" name="password" id="exampleInputPassword1" placeholder="Password">
    </div>
    <div class="form-group">
        <label>Select Role:</label><br>
        <label class="radio-label">
            <input type="radio" name="role" value="customer" id="customer"> Customer
        </label>
        <label class="radio-label">
            <input type="radio" name="role" value="employee" id="employee"> Employee
        </label>
    </div>
    <button type="submit">Login</button>
    <p>
        <a href="Register.jsp">Register New Account</a>
    </p>
</form>
</body>
</html>
