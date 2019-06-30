<%-- 
    Document   : allOrder
    Created on : Jun 23, 2019, 3:30:03 PM
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
            <div class="box-Order">
                <h2>All Order</h2>
                <c:if test="${requestScope.LIST_ORDER.size() > 0}">
                    <c:forEach items="${requestScope.LIST_ORDER}" var="order">
                        <div class="item-Order">
                            <p>Date: ${order.date}</p>
                            <p>Total:${order.total}</p>
                            <p>ID:${order.orderID}</p>
                            <div class="button-detail">
                                <button class="btn btn-primary" data-toggle="modal" data-target="#modelId_${order.orderID}">Detail</button>
                            </div>
                        </div>             
                    </c:forEach>
                </c:if>
            </div>
            <div class="box-service">
                <c:if test="${requestScope.LIST_SERVICE != null}">
                    <table class="table table-bordered table-hover myTable">
                        <thead class="text-primary">
                            <tr>
                                <th>Service ID</th>
                                <th>User Name</th>
                                <th>Slot</th>
                                <th>Total</th>
                                <th>Status</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <c:forEach items="${requestScope.LIST_SERVICE}" var="process">
                            <tr>
                                <td>${process.serviceID}</td>
                                <td>${process.username}</td>
                                <td>${process.slot}</td>
                                <td>${process.total}</td>
                                <td><button class="btn btn-<c:if test='${process.finished}'>success</c:if><c:if test='${!process.finished}'>danger</c:if>" style="width: 100px;">
                                        ${process.finished}
                                    </button></td>
                                <td>${process.dateBook}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
            <c:if test="${requestScope.LIST_ORDER != null}">
                <c:forEach items="${requestScope.LIST_ORDER}" var="order">
                    <div class="modal fade" id="modelId_${order.orderID}" tabindex="-1" role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Order ID : ${order.orderID}</h5>
                                </div>
                                <div class="modal-body">
                                    <p>Order's User : ${order.username} </p>
                                    <table class="table table-bordered table-hover myTable">
                                        <thead class="text-primary">
                                            <tr>
                                                <th>Acessory ID</th>
                                                <th>Unit</th>
                                                <th>Total</th>
                                            </tr>
                                            <c:forEach items="${order.allLine}" var="line">
                                                <tr>
                                                    <td>${line.split("-")[0]}</td>
                                                    <td>${line.split("-")[1]}</td>
                                                    <td>${line.split("-")[2]}</td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <td colspan="2">Total</td>
                                                <td>${order.total}</td>
                                            </tr>
                                        </thead>
                                    </table>
                                    <p>Date: ${order.date}</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>


        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </body>
</html>