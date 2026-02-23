<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*,model.BookIssue, model.Book" %>
<html>
<head>
    <title>My Issued Books</title>
</head>
<body>
<h2>My Issued Books</h2>

<table border="1">
<tr>
    <th>Book</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine</th>
</tr>

<%
List<BookIssue> list = (List<BookIssue>) request.getAttribute("IssuedBooks");
if(list==null)return;
for(BookIssue i : list){
%>
<tr>
    <td><%=i.getBook().getTitle()%></td>
    <td><%=i.getIssueDate()%></td>
    <td><%=i.getDueDate()%></td>
    <td><%=i.getReturnDate()%></td>
    <td><%=i.getFine()%></td>
</tr>
<% } %>
</table>
</body>
</html>
