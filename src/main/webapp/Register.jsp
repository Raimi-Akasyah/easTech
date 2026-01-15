<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
<style>
  body {
    display: flex;
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

  .form-group input[type="text"],
  .form-group input[type="email"],
  .form-group input[type="password"] {
    width: calc(100% - 10px);
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
<form method="POST" action="RegisterController">
  <h3>Register Page</h3>
  <div class="form-group">
    <label for="name">Full Name</label>
    <input type="text" name="name" id="name" placeholder="Enter your name" required>
  </div>
  <div class="form-group">
    <label for="email">Email address</label>
    <input type="email" name="email" id="email" placeholder="Enter email" required>
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" name="password" id="password" placeholder="Enter password" required>
  </div>
  <div class="form-group">
    <label>Select Role:</label><br>
    <label class="radio-label">
      <input type="radio" name="role" value="customer" id="customer" required>
      Customer
    </label>
    <label class="radio-label">
      <input type="radio" name="role" value="employee" id="employee" required>
      Employee
    </label>
  </div>
  <button type="submit">Register</button>
  <p>
    Already have an account? <a href="Log in.jsp">Login here</a>
  </p>
</form>
</body>
</html>
