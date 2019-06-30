<%-- 
    Document   : registerAdmin
    Created on : Jun 30, 2019, 4:55:14 PM
    Author     : 99hai
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Manage Product</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- Bootstrap 4 -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/jquery-ui.min.css">
        <!-- My CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Admin/CSS/modal.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Admin/CSS/style4.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Admin/CSS/main.css">
    </head>
    <body>
        <div class="wrapper">
            <%@include file="myNav.jsp" %>
            <div id="content">
                <div class="container">
                    <div class="card text-center">
                        <!-- Header -->
                        <div class="card-header myCardHeader">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h3 class="text-left text-primary font-weight-bold">Register Admin</h3>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <form action="/PETWATER/RegisterController" method="POST" accept-charset="ISO-8859-1">
                                <table>
                                    <tr>
                                        <td><p>Username</p></td>
                                        <td><input type="text" name="txtUsername" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_ACCOUNT.errorUsername}</font></td>
                                    </tr>
                                    <tr>
                                        <td><p>Password</p></td>
                                        <td><input type="password" name="txtPassword" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_ACCOUNT.errorPassword}</font></td>
                                    </tr>
                                    <tr>
                                        <td><p>Confirm</p></td>
                                        <td><input type="password" name="txtPasswordConfirm" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_ACCOUNT.errorPasswordConfirm}</font></td>
                                    </tr>
                                    <tr>
                                        <td><p>Fullname</p></td>
                                        <td><input type="text" name="txtFullname" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_ACCOUNT.errorFullname}</font></td>
                                    </tr>
                                    <input type="hidden" name="state" value="admin"/>
                                    <tr>
                                        <td></td>
                                        <td><input type="submit" class="btn btn-success" value="Add Service" /></td>
                                        <td></td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </body>

</html>
