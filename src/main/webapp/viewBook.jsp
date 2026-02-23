<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,model.Book" %>
<!DOCTYPE html>
<html>
<head>
    <title>Available Books</title>
</head>
<body>
<h2>Available Books</h2>

<table border="1">
<tr>
    <th>Name</th><th>Author</th><th>Qty</th>
</tr>

<%
List<Book> list = (List<Book>) request.getAttribute("books");
if (list != null && !list.isEmpty()) {
    for(Book b : list) {
%>
    <tr>
        <td><%= b.getTitle() %></td>
        <td><%= b.getAuthor() %></td>
        <td><%= b.getAvailableCopies() %></td>
    </tr>
<% 
    }
} else { 
%>
    <tr>
        <td colspan="3" style="text-align:center;">No books found.</td>
    </tr>
<% } %>
</table>
<div style="margin-top: 20px;">

    <% if ((Integer)request.getAttribute("currentPage") > 1) { %>
        <a href="viewBook?page=<%= (Integer)request.getAttribute("currentPage") - 1 %>">Prev</a>
    <% } %>

 
    <span> Page <%= request.getAttribute("currentPage") %> of <%= request.getAttribute("totalPages") %> </span>


    <% if ((Integer)request.getAttribute("currentPage") < (Integer)request.getAttribute("totalPages")) { %>
        <a href="viewBook?page=<%= (Integer)request.getAttribute("currentPage") + 1 %>">Next</a>
    <% } %>
</div>
</body>
</html>
