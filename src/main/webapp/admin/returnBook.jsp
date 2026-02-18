<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Return Book</title>
</head>
<body>
<h2>Return Book</h2>

<form action="returnBook" method="post">
    Issue ID: <input type="number" name="issueId" required><br><br>
    <button type="submit">Return</button>
</form>

<p style="color:green">${msg}</p>
</body>
</html>
