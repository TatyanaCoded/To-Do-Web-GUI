<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>To-Do List</title>
</head>
<body>

<h1>To-Do List</h1>

<h3>Add a New Task</h3>
<form action="todo" method="post">
    <label for="task">Task:</label>
    <input type="text" id="task" name="task" required />
    <button type="submit">Add</button>
</form>

<h3>Current Tasks</h3>
<ul>
    <c:forEach var="item" items="${items}">
        <li>
                ${item.id}. ${item.task} -
            <c:if test="${item.completed}">
                Completed
            </c:if>
            <c:if test="${!item.completed}">
                Not completed
            </c:if>
            <a href="todo?action=delete&id=${item.id}">Delete</a>
        </li>
    </c:forEach>
</ul>

</body>
</html>
