<%--
  Created by IntelliJ IDEA.
  User: jselimba
  Date: 2019-08-28
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Add Contact</title>
</head>
<body>

<form action = "phonebook" method = "POST">
    First Name: <input type = "text" name = "firstname">
    <br/>
    Last Name: <input type = "text" name = "lastname">
    <br/>
    PhoneNumber Name: <input type = "text" name = "phonenumber">
    <br/>
    <input type = "submit" value = "კონტაქტის დამატება" />
</form>

<div>
    <%
        if (session.getAttribute("contactAddResult") != null) {
            out.print(session.getAttribute("contactAddResult"));
        }
    %>
</div>
<br/>
<button onclick="window.location.href='/phone-book-web-1.0/index.jsp'">მთავარი</button>

</body>
</html>
