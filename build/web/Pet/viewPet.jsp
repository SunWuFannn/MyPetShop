<%-- 
    Document   : viewPet
    Created on : Jun 17, 2019, 4:12:58 PM
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Pet/CSS/viewPet_css.css">
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
                        <li class="nav-item active">
                            <a class="nav-link" href="Pet/addPet.jsp">Sent your Pet</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.FULLNAME != null}">
                        <li class="nav-item">
                            <a class="nav-link" href="LoadPetController">view your Pet</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>  
        <div class="container-pet">
            <c:forEach items="${requestScope.LIST_PET}" var="pet">
                <c:if test="${pet.isActive}">
                    <div class="pet-item">
                        <p>Pet ID : ${pet.petID}</p>
                        <p>Pet Name : ${pet.petName}</p>
                        <p>Pet Age : ${pet.petAge}</p>
                        <p>Pet Type : ${pet.petType}</p>
                        <div class="avatar-pet" style="background-image: url('Image-Pet/${pet.petImage}')">

                        </div>
                        <c:url value="EditPetController" var="EditLink">
                            <c:param name="txtPetID" value="${pet.petID}"/>
                        </c:url>
                        <c:url value="DeletePetController" var="DeleteLink">
                            <c:param name="txtPetID" value="${pet.petID}" />
                        </c:url>
                        <div class="row">
                            <a href="${EditLink}" class="btn btn-success" style="margin-right: 10px">Update</a>
                            <a href="${DeleteLink}" class="btn btn-danger">Delete</a>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>


        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
