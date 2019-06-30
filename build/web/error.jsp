<%-- 
    Document   : error
    Created on : Jun 14, 2019, 3:54:23 PM
    Author     : 99hai
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ERROR Page</h1>
        <font color="red">${requestScope.ERROR}</font>
        <c:if test="${requestScope.LOGIN != null}">
            <a href="Auth/login.jsp">Login Again</a>
        </c:if>
    </body>
</html>
