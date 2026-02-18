<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Book</title>
</head>
<body>
<h2>Issue Book</h2>

<form action="issueBook" method="post">
    Book ID: <input type="number" name="bookId" required><br><br>
    User ID: <input type="number" name="userId" required><br><br>
    <button type="submit">Issue</button>
</form>

<p style="color:red">${error}</p>
<p style="color:green">${msg}</p>
</body>
</html>
