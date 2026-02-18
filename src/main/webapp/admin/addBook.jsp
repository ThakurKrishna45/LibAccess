<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
<h2>Add Book</h2>

<form action="addBook" method="post">
    Book Name: <input type="text" name="name" required><br><br>
    Author: <input type="text" name="author" required><br><br>
    ISBN: <input type="text" name="isbn" required><br><br>
    Quantity: <input type="number" name="qty" required><br><br>
    <button type="submit">Add Book</button>
</form>

<p style="color:green">${msg}</p>
</body>
</html>
