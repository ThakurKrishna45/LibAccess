<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<%
User user = (User) session.getAttribute("user");

if(user == null ||
   (!"admin".equals(user.getRole().getRoleName()))) {

    response.sendError(403);
    return;
}
%>

<html>
<head>
    <title>Admin Panel</title>
</head>
<body>

<h2>Admin Panel</h2>

<p><strong>Name:</strong> <%= user.getName() %></p>
<p><strong>Email:</strong> <%= user.getEmail() %></p>
<p><strong>Role:</strong> <%= user.getRole().getRoleName() %></p>
<p>
<strong>Account:</strong> 
<span style="color: <%= user.isActive() ? "green" : "red" %>; font-weight: bold;">
<%= user.isActive() ? "Active" : "Inactive" %>
</span>
</p>
<hr>

<a href="addBook">Add Book</a><br><br>
<a href="issueBook">Issue Book</a><br><br>
<a href="returnBook">Return Book</a><br><br>
<a href="../viewBook">View All Books</a><br><br>

<hr>
<a href="../logout">Logout</a>

</body>
</html>