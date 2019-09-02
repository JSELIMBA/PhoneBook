<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: jselimba
  Date: 2019-08-28
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Contact</title>
</head>
<body>

<%--
  Created by IntelliJ IDEA.
  User: jselimba
  Date: 2019-08-28
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action = "phonebook" method = "GET">
    <center>

        Search Contact
        <br/>
        <br/>

    Search Value: <input type = "text" name = "sValue">
    <br/>
    <br/>
    <input type = "submit" value = "კონტაქტის ძიება" />
    </center>
</form>

<center>

    <form action = "phonebookUpdate" method = "GET">
<table class="table table-bordered table-triped table-hover">

    <thead>

    <tr>

        <th>ID</th>
        <th>Firs Name</th>
        <th>Lasr Name</th>
        <th>Phone Number</th>

    </tr>

    </thead>

    <tbody>

    <%
        if (session.getAttribute("contactSearchResult") != null) {
            out.print(session.getAttribute("contactSearchResult"));
            session.setAttribute("contactUpdateResult", "");
        }


    %>

    </tbody>

</table>
    </form>

</center>

<br/>
<button onclick="window.location.href='/phone-book-web-1.0/index.jsp'">მთავარი</button>

</body>
</html>
