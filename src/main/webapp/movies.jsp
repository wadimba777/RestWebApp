<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.moviesearch.model.Movie" %>
<%@ page import="com.moviesearch.service.DirectorService" %>
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

<h2>List Of Movies</h2>
<a href="http://localhost:8080/RestWebApp/addMovie">Add Movie</a><br>
<table>
    <head>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th> <a href="http://localhost:8080/RestWebApp/directors">Director</a></th>
        </tr>
    </head>
  <%
     ArrayList<Movie> movies = (ArrayList<Movie>) request.getAttribute("movies");
     if (movies != null && !movies.isEmpty()) {
     DirectorService director = DirectorService.getDirectorService();
     for (Movie movie : movies) {
     %>
        <tr>
            <td><%= movie.getId() %></td>
            <td><%= movie.getTitle() %></td>
            <td><%= director.get(movie.getDirectorId()).getName() %></td>
            <td>
                  <form action="movies" method="post">
                       <input type="hidden" name="id" value="<%= movie.getId() %>">
                       <button type="submit">Delete</button>
                  </form>
            </td>
        </tr>
        <% } %>
     <%
     } else {
     %>
     <tr>
     <td colspan="3"> Фильмы не найдены.</td>
     </tr>
     <%
     }
     %>
</table>
</body>
</html>