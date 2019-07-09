<%-- 
    Document   : addPet
    Created on : Jun 17, 2019, 2:21:49 PM
    Author     : 99hai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Title</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Pet/CSS/addPet_css.css">
        <script type="text/javascript">
            function noBack()
            {
                window.history.forward()
            }
            noBack();
            window.onload = noBack;
            window.onpageshow = function (evt) {
                if (evt.persisted)
                    noBack()
            }
            window.onunload = function () {
                void (0)
            }
        </script>
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
                        <a class="nav-link" href="/PETWATER/index.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/PETWATER/LoadAccessoryUserController">Accessory</a>
                    </li>
                    <c:if test="${sessionScope.FULLNAME == null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="/PETWATER/Auth/login.jsp">Login</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.FULLNAME == null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="/PETWATER/Auth/register.jsp">Register</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.FULLNAME != null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="/PETWATER/LoadUserInfoController">Hello ${sessionScope.FULLNAME}</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.FULLNAME != null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="/PETWATER/LogoutController">Logout</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.FULLNAME != null}">
                        <li class="nav-item">
                            <a class="nav-link" href="/PETWATER/Pet/addPet.jsp">Sent your Pet</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.FULLNAME != null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="/PETWATER/LoadPetController">view your Pet</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>  
        <div class="add-pet-container">
            <div class="form-add-pet">
                <form action="/PETWATER/MainPetController" method="POST" accept-charset="ISO-8859-1">
                    <p>Pet ID </p><input type="text" name="txtPetID"/><br/>
                    <font color="red">${requestScope.ERROR_PET.errorID}</font><br/>
                    <p>Pet Name</p><input type="text" name="txtPetName" /><br/>
                    <font color="red">${requestScope.ERROR_PET.errorName}</font><br/>
                    <p>Pet Age</p><input type="text" name="txtPetAge" /><br/>
                    <font color="red">${requestScope.ERROR_PET.errorAge}</font><br/>
                    <p>Type</p>: <select name="txtPetType">
                        <option value="choose">Choose Type</option>
                        <option value="Cat">Cat</option>
                        <option value="Dog">Dog</option>
                        <option value="Bird">Bird</option>
                        <option value="Fish">Fish</option>
                    </select>  <br/>                  
                    <font color="red">${requestScope.ERROR_PET.errorType}</font><br/>
                    <p>Image Pet: </p> <input type="file" name="txtPetImage"/>
                    <br/><br/>
                    <input type="submit" name="action" value="Add Pet" class="btn btn-success"/>
                </form>
            </div>
        </div>
    </body>
</html>
