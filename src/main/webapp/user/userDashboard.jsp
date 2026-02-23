<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
User user = (User) session.getAttribute("user");
if(user == null){
    response.sendRedirect("../login.jsp");
    return;
}
%>

<html>
<head>
    <title>User Dashboard</title>
</head>
<body>

<h2>Welcome, <%= user.getName() %></h2>

<hr>

<h3>Your Profile</h3>
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

<h3>Actions</h3>

<!-- Student / Normal User -->
<% if("student".equals(user.getRole().getRoleName())) { %>
    <a href="../UserIssuedBook">View My Issued Books</a><br><br>
    <a href="../viewBook">View Available Books</a><br><br>
<% } %>



<hr>
<a href="../logout">Logout</a>

</body>
</html>