<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.moviesearch.model.Director" %>
<html>
<head>
    <title>Add Movie</title>
</head>
<body>
    <h2>Add Movie</h2>
    <form action="addMovie" method="post">
        <label for="title">Title:</label><br>
        <input type="text" id="title" name="title"><br><br>
        <label for="directorId">Director ID:</label><br>
        <input type="text" id="directorId" name="directorId"><br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>

