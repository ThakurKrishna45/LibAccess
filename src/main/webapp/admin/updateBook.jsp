<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Book</title>

    <script>
        function prepareQty() {
            const add = parseInt(document.getElementById("addQty").value) || 0;
            const remove = parseInt(document.getElementById("removeQty").value) || 0;

            // qty = add - remove
            document.getElementById("qty").value = add - remove;

            return true;
        }
    </script>
</head>
<body>

<h2>Update Book</h2>

<form action="<%= request.getContextPath() %>/admin/updateBook"
      method="post"
      onsubmit="return prepareQty()">

    <p>
        Book ID:<br>
        <input type="number" name="id" min="1" required>
    </p>

    <p>
        Title:<br>
        <input type="text" name="title" required>
    </p>

    <p>
        Author:<br>
        <input type="text" name="author" required>
    </p>

    <p>
        Qty to ADD:<br>
        <input type="number" id="addQty" min="0" value="0">
    </p>

    <p>
        Qty to REMOVE:<br>
        <input type="number" id="removeQty" min="0" value="0">
    </p>

    <!-- Hidden field sent to backend -->
    <input type="hidden" name="qty" id="qty">

    <button type="submit">Update Book</button>

</form>

<br>
<a href="<%= request.getContextPath() %>/admin/books">Back to Books</a>

</body>
</html>
