<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Delete Book</title>
</head>
<body>

<h2>Delete Book</h2>

<form action="<%= request.getContextPath() %>/admin/deleteBook" method="post">

    <p>
        Enter Book ID:<br>
        <input type="number" name="id" min="1" required>
    </p>

    <button type="submit">Delete</button>

</form>

<br>
<a href="<%= request.getContextPath() %>/admin/books">Back to Books</a>

</body>
</html>
