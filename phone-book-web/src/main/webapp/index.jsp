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
    <title>PhoneBook</title>
</head>
<body>

<button onclick="window.location.href='/phone-book-web-1.0/addContact.jsp'">ახალი კონტაქტი</button>

<button onclick="window.location.href='/phone-book-web-1.0/searchContact.jsp'">კონტაქტის ძიება</button>

<div>
    <%
        session.setAttribute("contactSearchResult", "");
        session.setAttribute("contactAddResult", "");
    %>
    <br/>
</div>


</div>


</body>
</html>
