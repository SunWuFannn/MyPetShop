<%-- 
    Document   : userInfo
    Created on : Jun 20, 2019, 4:22:22 PM
    Author     : 99hai
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <title>Title</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/User/CSS/addPet_css.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-sm navbar-light bg-light">
            <a class="navbar-brand" href="#">My Pet Shop</a>
            <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavId">
                <ul class="navbar-nav mr-auto ">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="LoadAccessoryUserController">Accessory</a>
                    </li>
                    <c:if test="${sessionScope.FULLNAME == null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="Auth/login.jsp">Login</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.FULLNAME == null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="Auth/register.jsp">Register</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.FULLNAME != null}">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Hello ${sessionScope.FULLNAME}</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.FULLNAME != null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="/PETWATER/LogoutController">Logout</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.FULLNAME != null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="Pet/addPet.jsp">Sent your Pet</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.FULLNAME != null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="LoadPetController">view your Pet</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>  
        <div class="container-pet">
            <div class="form-Update-user">
                <form action="UpdateUserController" method="POST" accept-charset="ISO-8859-1">
                    <p>Username </p><input type="text" name="txtUsername" readonly="true" value="${sessionScope.USERNAME}" required="true"/><br/>
                    <p>Fullname</p><input type="text" name="txtFullname" value="${requestScope.USER.fullname}" required="true"/><br/>
                    <font color="red">${requestScope.ERROR_ACCOUNT.errorFullname}</font>  <br/>
                    <p>Phone</p><input type="text" name="txtPhone" value="${requestScope.USER.phone}" required="true"/><br/>
                    <font color="red">${requestScope.ERROR_ACCOUNT.errorPhone}</font>  <br/>
                    <p>Role</p><input type="text" name="txtRole" readonly="true" value="${sessionScope.ROLE}" required="true"/> 
                    <br/>                  
                    <p>Address </p> <input type="text" name="txtAddress" value="${requestScope.USER.address}" required="true"/>
                    <br/>
                    <font color="red">${requestScope.ERROR_ACCOUNT.errorAddress}</font>  <br/>
                    <br/>
                    <input type="submit" name="action" value="Update" class="btn btn-success"/>
                </form>
            </div>
        </div>


        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>

