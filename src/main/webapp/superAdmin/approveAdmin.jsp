<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,model.User" %>
<!DOCTYPE html>
<html>


<script>
    const urlParams = new URLSearchParams(window.location.search);
    const status = urlParams.get('status');

    if (status === 'activated') {
        alert("Success! User has been activated.");
        window.history.replaceState({}, document.title, window.location.pathname);
    } else if (status === 'error') {
        alert("Error: Could not activate user.");
    }
</script>



<head>
<meta charset="UTF-8">
<title>Admin List</title>
</head>
<body>
<h2> Admin List</h2>
<table>
<tr>
    <th>User Id</th><th>Name</th><th>Email</th><th>Account Status</th>
</tr>

<% List<User> admins= (List<User>) request.getAttribute("admins");
for(User u:admins){
	%>
	<tr>
		<td><%=u.getId() %></td>
		<td><%= u.getName() %></td>
		<td><%=u.getEmail() %></td>
		<td>
    <% if(u.isActive()) { %>
        <span style="color: green; font-weight: bold;">Active</span>
    <% } else { %>
        <form action="approveAdmin" method="post" style="display:inline;">
            <input type="hidden" name="user_id" value="<%= u.getId() %>">
            <button type="submit" 
                    style="background:none; border:none; color:red; font-weight:bold; cursor:pointer; text-decoration:underline; padding:0;">
                Activate
            </button>
        </form>
    <% } %>
</td>
	</tr>
<%}%>
</table>
</body>
</html>