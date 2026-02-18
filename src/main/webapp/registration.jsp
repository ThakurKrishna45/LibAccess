<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<h2>User Registration</h2>

<form action="register" method="post">
	Full Name: <input type="text" name="name" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    Password: <input type="password" name="password" required><br><br>

    Role:
    <select name="role">
        <option value="USER">User</option>
        <option value="ADMIN">Admin</option>
    </select><br><br>

    <button type="submit">Register</button>
</form>

<p style="color:red">${error}</p>
<p style="color:green">${msg}</p>

<a href="login.jsp">Back to Login</a>

</body>
</html>
