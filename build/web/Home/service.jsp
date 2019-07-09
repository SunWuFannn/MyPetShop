<%-- 
    Document   : service
    Created on : Jun 22, 2019, 9:40:21 AM
    Author     : 99hai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <title>Title</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Home/CSS/accessory_css.css">
        <style>
            #snackbar {
                visibility: hidden;
                min-width: 250px;
                margin-left: -125px;
                background-color: #333;
                color: #fff;
                text-align: center;
                border-radius: 2px;
                padding: 16px;
                position: fixed;
                z-index: 1;
                left: 50%;
                bottom: 30px;
                font-size: 17px;
            }

            #snackbar.show {
                visibility: visible;
                -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
                animation: fadein 0.5s, fadeout 0.5s 2.5s;
            }

            @-webkit-keyframes fadein {
                from {bottom: 0; opacity: 0;} 
                to {bottom: 30px; opacity: 1;}
            }

            @keyframes fadein {
                from {bottom: 0; opacity: 0;}
                to {bottom: 30px; opacity: 1;}
            }

            @-webkit-keyframes fadeout {
                from {bottom: 30px; opacity: 1;} 
                to {bottom: 0; opacity: 0;}
            }

            @keyframes fadeout {
                from {bottom: 30px; opacity: 1;}
                to {bottom: 0; opacity: 0;}
            }
        </style>
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
                        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="LoadAccessoryUserController">Accessory</a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="LoadServiceUser">Service</a>
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
                            <a class="nav-link" href="LoadUserInfoController">Hello ${sessionScope.FULLNAME}</a>
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
        <div class="container-item">
            <div class="Form-Search">
                <form action="UserSearchServiceController" method="POST">
                    <input type="text" name="txtSearch" />
                    <input class="btn btn-success" type="submit" value="SEARCH" />
                </form>
            </div>
            <c:forEach items="${requestScope.LIST_SERVICE}" var="service" varStatus="counter">
                <div class="item-accessory">
                    <p>Stt: ${counter.count}</p>
                    <p>Name:${service.serviceName}</p>
                    <p>Price:${service.price}</p>
                    <p>Have:${service.slot}</p>
                    <div class="image-item" style="background-image: url('ImgService/${service.image}')">

                    </div>
                    <div class="add-cart-button">
                        <form action="AddServiceToCartController" method="POST">
                            <input type="hidden" name="serviceID" value="${service.serviceID}">
                            <input type="hidden" name="serviceName" value="${service.serviceName}">
                            <input type="hidden" name="servicePrice" value="${service.price}">
                            <c:if test="${service.isActive and service.slot != 0}"><input type="submit" class="btn btn-success" value="Add To Cart"></c:if>
                            <c:if test="${!service.isActive}"><input type="submit" class="btn btn-danger" value="Closing" disabled></c:if>
                            <c:if test="${service.slot == 0}"><input type="submit" class="btn btn-danger" value="Out of slot" disabled></c:if>
                            </form>
                        </div>
                    </div>
            </c:forEach>
        </div>
        <div class="cart">
            <center><h4>My Cart</h4></center>
                <c:if test="${sessionScope.CART_SERVICE.serviceCart.size() > 0}">
                <form action="CheckCartServiceController" method="POST">
                    <table>
                        <tr>
                            <th style="width: 120px">Name</th>
                            <th style="width: 100px">Price</th>
                            <th style="width: 100px">Quantity</th>
                            <th>Total</th>
                            <th><th>
                        </tr>
                        <c:forEach items="${sessionScope.CART_SERVICE.serviceCart}" var="cart">
                            <tr>
                                <td><p>${cart.value.serviceName}</p></td>
                                <td><p>${cart.value.price}</p></td>
                                <td><p><input type="number" name="txtAccessQuantity" min="1" max="5" value="${cart.value.slot}" style="width: 50px"></p></td>
                                <td><p>${cart.value.price*cart.value.slot}</p></td>
                                <td>
                                    <c:url var="DeleteItem" value="DeleteServiceCartController">
                                        <c:param name="serviceID" value="${cart.value.serviceID}"/>
                                    </c:url>
                                    <a href="${DeleteItem}" class="btn btn-danger">x</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>${sessionScope.CART_SERVICE.getTotalServicePriceInCart()}</td>
                        </tr>
                        <tr>
                            <td><input type="submit" class="btn btn-success" value="Book"></td>
                            <td colspan="3"><font color="red">${requestScope.ERROR_CART.errSlot}</font></td>
                        </tr>
                    </table>
                </form>
            </c:if>
        </div>
        <div id="snackbar">Add success</div>
        <c:if test="${requestScope.CHECK == '1'}">
            <script type="text/javascript">
                var x = document.getElementById("snackbar");
                x.className = "show";
                setTimeout(function () {
                    x.className = x.className.replace("show", "");
                }, 1000);
            </script>
        </c:if>
        <c:if test="${requestScope.CHECK == '2'}">
            <script type="text/javascript">
                var x = document.getElementById("snackbar");
                x.className = "show";
                x.innerHTML = "Delete Success"
                setTimeout(function () {
                    x.className = x.className.replace("show", "");
                }, 1000);
            </script>
        </c:if>
        <c:if test="${requestScope.WALLET == '3'}">
            <script type="text/javascript">
                var x = document.getElementById("snackbar");
                x.className = "show";
                x.innerHTML = "Not Enough Money"
                setTimeout(function () {
                    x.className = x.className.replace("show", "");
                }, 1000);
            </script>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
