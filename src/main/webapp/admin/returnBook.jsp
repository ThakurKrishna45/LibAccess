<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
    <title>Return Book</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>

    <h2>Return a Book</h2>
    
    <p class="success">${param.msg}</p>
    <p class="error">${param.error}</p>

    <form action="returnBook?action=return" method="post">
        Student ID: <input type="text" name="userId" required><br><br>
        Book ISBN: <input type="text" name="isbn" required><br><br>
        <button type="submit">Process Return</button>
    </form>

    <hr>

    <h3>All Currently Issued Books</h3>
    <table>
        <thead>
            <tr>
                <th>Student ID</th>
                <th>Book Title</th>
                <th>ISBN</th>
                <th>Issue Date</th>
                <th>Due Date</th>
                <th>Return Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="issue" items="${issuedBooksList}">
                <tr>
                    <td>${issue.user.id}</td>
                    <td>${issue.book.title}</td>
                    <td>${issue.book.isbn}</td>
                    <td>${issue.issueDate}</td>
                    <td>${issue.dueDate}</td>
                    <td>${issue.returnDate}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty issuedBooksList}">
                <tr>
                    <td colspan="6" style="text-align:center;">No books are currently issued.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

</body>
</html>