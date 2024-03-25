<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.moviesearch.model.Director" %>
<html>
<head>
    <title>Movie Search - List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<h2>List Of Directors</h2>
<a href="http://localhost:8080/RestWebApp/addDirector">Add Director</a><br>
<a href="http://localhost:8080/RestWebApp/movies">Movies</a><br>
<table>
    <head>
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
    </head>
  <%
     ArrayList<Director> directors = (ArrayList<Director>) request.getAttribute("directors");
     if (directors != null && !directors.isEmpty()) {
     for (Director director : directors) {
     %>
        <tr>
            <td><%= director.getId() %></td>
            <td><%= director.getName() %></td>
            <td>
                  <form action="directors" method="post">
                        <input type="hidden" name="id" value="<%= director.getId() %>">
                        <button type="submit">Delete</button>
                  </form>
            </td>
        </tr>
        <% } %>
     <%
     } else {
     %>
     <tr>
     <td colspan="3"> Directors not found.</td>
     </tr>
     <%
     }
     %>
</table>
</body>
</html>