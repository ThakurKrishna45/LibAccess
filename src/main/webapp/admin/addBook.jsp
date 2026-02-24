<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Add Book</title>
    <style>
        .alert { padding: 10px; margin-bottom: 15px; border-radius: 5px; }
        .success { background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .error { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
    </style>
</head>
<body>
<h2>Add Book</h2>

<%-- Check for Success Message --%>
<% if (session.getAttribute("successMsg") != null) { %>
    <div id="status" class="alert success">
        <%= session.getAttribute("successMsg") %>
    </div>
    <% session.removeAttribute("successMsg"); %>
<% } %>

<%-- Check for Error Message --%>
<% if (session.getAttribute("errorMsg") != null) { %>
    <div id="status" class="alert error">
        <%= session.getAttribute("errorMsg") %>
    </div>
    <% session.removeAttribute("errorMsg"); %>
<% } %>

<form action="addBook" method="post">
   
    Book Title: <input type="text" name="title" required><br><br>
    Author: <input type="text" name="author" required><br><br>
    ISBN: <input type="text" name="isbn" required><br><br>
    Quantity: <input type="number" name="qty" required><br><br>
    <button type="submit">Add Book</button>
</form>

<script>

    var statusDiv = document.getElementById("status");
    if (statusDiv) {
        setTimeout(function() {
            statusDiv.style.display = "none";
        }, 3000);
    }
</script>

</body>
</html>