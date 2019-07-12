<%-- 
    Document   : adminViewOrder
    Created on : Jun 18, 2019, 8:35:02 PM
    Author     : 99hai
--%>

<%@page import="haipm.dtos.OrderDTO"%>
<%@page import="java.util.ArrayList"%>
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
        <div class="wrapper">
            <nav id="sidebar">
                <div class="sidebar-header">
                    <h3>WelCome ${sessionScope.FULLNAME}</h3>
                    <h5><a href="/PETWATER/LogoutController">Logout</h5>
                </div>

                <ul class="list-unstyled components">
                    <li>
                        <a href="LoadAccessoryAdmin?idPage=1">
                            <i class="fa fa-home"></i>
                            Manage All Product
                        </a>
                    </li>
                    <li>
                        <a href="LoadServiceAdminController">
                            <i class="fa fa-paperclip"></i>
                            Manage Service for Pet
                        </a>
                    </li>
                    <li>
                        <a href="AdminViewPetController?idPage=1">
                            <i class="fa fa-paperclip"></i>
                            Manage Pet
                        </a>
                    </li>
                    <li class="active">
                        <a href="AdminViewOrderController?idPage=1">
                            <i class="fa fa-paperclip"></i>
                            Manage Order
                        </a>
                    </li>
                    <li>
                        <a href="AdminManageServiceProcessController">
                            <i class="fa fa-paperclip"></i>
                            Manage In Process Service
                        </a>
                    </li>
                    <li>
                        <a href="AdminManageHistoryService?idPage=1">
                            <i class="fa fa-paperclip"></i>
                            History of Service Process
                        </a>
                    </li>
                    <li>
                        <a href="AdminManageAccount?idPage=1">
                            <i class="fa fa-paperclip"></i>
                            Manage Account
                        </a>
                    </li>
                    <li>
                        <a href="Admin/registerAdmin.jsp">
                            <i class="fa fa-paperclip"></i>
                            Register Admin
                        </a>
                    </li>
                </ul>
            </nav>
            <div id="content">
                <div class="container">
                    <div class="card text-center">
                        <!-- Header -->
                        <div class="card-header myCardHeader">
                            <div class="row">
                                <div class="col-md-6">
                                    <h3 class="text-left text-primary font-weight-bold">List Pet Of User</h3>
                                </div>
                            </div>
                        </div>
                        <div class="input-group date w-50">
                            <form action="AdminSearchOrder" method="POST">
                                <input type="date" name="txtDate" class="form-control">
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-th"></span>
                                </div>
                                <input type="submit" class="btn btn-success" value="Search"/>
                            </form>
                        </div>
                        <!--                        <form action="AdminSearchOrderDay" method="POST">
                                                    <input type="date" name="txtDate" /><br/>
                                                    <input type="submit" class="btn btn-success" value="Search"/>
                                                </form>-->
                        <div class="card-body">
                            <c:if test="${requestScope.LIST_ORDER != null}">
                                <table class="table table-bordered table-hover myTable">
                                    <thead class="text-primary">
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Order's Owner</th>
                                            <th>Total</th>
                                            <th>Data Order</th>
                                            <th>Detail</th>
                                        </tr>
                                    </thead>
                                    <c:forEach items="${requestScope.LIST_ORDER}" var="order" varStatus="counter">
                                        <tr>
                                            <td>${order.orderID}</td>
                                            <td>${order.username}</td>
                                            <td>${order.total}</td>
                                            <td>${order.date}</td>
                                            <td><button class="btn btn-primary" 
                                                        data-toggle="modal" data-target="#modelId_${order.orderID}">More Detail</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </div>
                        <div class="card-footer myCardFooter">
                            <ul class="pagination">
                                <c:forEach var="i" begin="1" end="${requestScope.NUMBER_PAGE}" step="1">
                                    <li class="page-item"><a class="page-link" href="AdminViewOrderController?idPage=${i}">${i}</a></li>
                                    </c:forEach>
                            </ul>
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
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </body>

</html>
