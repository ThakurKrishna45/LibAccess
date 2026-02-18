<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,model.Book" %>
<html>
<head>
    <title>All Books</title>
</head>
<body>
<h2>Book List</h2>

<table border="1">
<tr>
    <th>ID</th><th>Name</th><th>Author</th><th>ISBN</th><th>Qty</th>
</tr>

<%
List<Book> list = (List<Book>) request.getAttribute("books");
for(Book b : list){
%>
<tr>
<%--     <td><%=b.getId()%></td> --%>
<%--     <td><%=b.getName()%></td> --%>
<%--     <td><%=b.getAuthor()%></td> --%>
<%--     <td><%=b.getIsbn()%></td> --%>
<%--     <td><%=b.getQuantity()%></td> --%>
</tr>
<% } %>
</table>
</body>
</html>
