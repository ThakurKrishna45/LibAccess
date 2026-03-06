<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
    <title>Issue Book</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>

    <h2>Issue Book</h2>
    
    <p class="success">${param.msg}</p>
    <p class="error">${param.error}</p>

    <form action="issueBook?action=issue" method="post">
        Student ID: <input type="text" name="studentId" required><br><br>
        Book ISBN: <input type="text" name="isbn" required><br><br>
        <button type="submit">Process Issue</button>
    </form>


</body>
</html>