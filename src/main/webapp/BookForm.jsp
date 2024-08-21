<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Book Store Application</title>
</head>
<body>
    <center>
        <h1>Books Management System</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/new">Add New Book</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/list">List All Books</a>
        </h2>
    </center>

    <div align="center">

        <c:choose>
            <c:when test="${book != null}">
                <form action="/update" method="post">
                    <input type="hidden" name="id" value="${book.id}"/>
                    <table border="1" cellpadding="5">
                        <caption><h2>Edit Book</h2></caption>
                        <tr>
                            <th>Title:</th>
                            <td><input type="text" name="title" size="45" value="${book.title}"></td>
                        </tr>
                        <tr>
                            <th>Author:</th>
                            <td><input type="text" name="author" size="45" value="${book.author}"></td>
                        </tr>
                        <tr>
                            <th>Price:</th>
                            <td><input type="text" name="price" size="5" value="${book.price}"></td>
                        </tr>
                        <td colspan="2" align="center"><input type="submit" value="Save" /></td>
                    </table>
                </form>
            </c:when>
            <c:otherwise>
                <form action="/insert" method="post">
                    <table border="1" cellpadding="5">
                        <caption><h2>Add New Book</h2></caption>
                        <tr>
                            <th>Title:</th>
                            <td><input type="text" name="title" size="45"></td>
                        </tr>
                        <tr>
                            <th>Author:</th>
                            <td><input type="text" name="author" size="45"></td>
                        </tr>
                        <tr>
                            <th>Price:</th>
                            <td><input type="text" name="price" size="5"></td>
                        </tr>
                        <td colspan="2" align="center"><input type="submit" value="Save" /></td>
                    </table>
                </form>
            </c:otherwise>
        </c:choose>
    </div>

</body>
</html>
