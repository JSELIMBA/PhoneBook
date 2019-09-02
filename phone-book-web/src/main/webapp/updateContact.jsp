<%--
  Created by IntelliJ IDEA.
  User: jselimba
  Date: 2019-08-30
  Time: 02:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Update Contact</title>
</head>
<body>

<form action = "phonebookUpdate" method = "POST">
    <center>

        Add Contact
        <br/>
        <br/>

        <input type="hidden" name="id"  value='<%=session.getAttribute("id")%>' />

        First Name: <input type = "text" name = "firstname" value='<%=session.getAttribute("firstname")%>' />
        <br/>
        <br/>
        Last Name: <input type = "text" name = "lastname" value='<%=session.getAttribute("lastname")%>' />
        <br/>
        <br/>
        PhoneNumber: <input type = "text" name = "phonenumber" value='<%=session.getAttribute("phonenumber")%>' />
        <br/>
        <br/>
        <input type = "submit" value = "კონტაქტის განახლება" />
    </center>
</form>

<div>
    <%
        if (session.getAttribute("contactUpdateResult") != null) {
            out.print(session.getAttribute("contactUpdateResult"));
        }
    %>
</div>

<br/>
<button onclick="window.location.href='/phone-book-web-1.0/index.jsp'">მთავარი</button>


</form>
</body>
</html>
