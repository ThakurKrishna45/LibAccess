<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<%
User user = (User) session.getAttribute("user");

if (user == null || !"super admin".equals(user.getRole().getRoleName())) {
	response.sendError(403);
	return;
}
%>

<html>
<head>
<title>Super Admin Panel</title>
</head>
<body>

	<h2>Super Admin Panel</h2>

	<p>
		<strong>Name:</strong>
		<%=user.getName()%></p>
	<p>
		<strong>Email:</strong>
		<%=user.getEmail()%></p>
	<p>
		<strong>Role:</strong>
		<%=user.getRole().getRoleName()%></p>
	<p>
		<strong>Account:</strong> <span
			style="color: <%=user.isActive() ? "green" : "red"%>; font-weight: bold;">
			<%=user.isActive() ? "Active" : "Inactive"%>
		</span>
	</p>
	<hr>

	<h3>Actions</h3>

	<!-- Admin Features Included -->
	<a href="../admin/addBook.jsp">Add Book</a>
	<br>
	<br>
	<a href="../admin/issueBook.jsp">Issue Book</a>
	<br>
	<br>
	<a href="../admin/returnBook.jsp">Return Book</a>
	<br>
	<br>
	<a href="../viewBook">View Available Books</a>
	<br>
	<br>
	<hr>

	<h3>SuperAdmin Controls</h3>
	<a href="approveAdmins">Approve Admin Requests</a>
	<br>
	<br>

	<hr>
	<a href="../logout">Logout</a>

</body>
</html>