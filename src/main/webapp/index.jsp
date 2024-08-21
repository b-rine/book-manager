<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Store Application</title>
</head>
<body>
    <div style="text-align: center;">
        <h1>Books Management System</h1>
        <h2>
            <a href="/new">Add New Book</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Books</a>
        </h2>
    </div>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Books</h2></caption>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="book" items="${bookList}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>$${book.price}</td>
                    <td>
                        <a href="/edit?id=${book.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=${book.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>