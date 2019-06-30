<%-- 
    Document   : adminViewService
    Created on : Jun 21, 2019, 4:06:09 PM
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
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <c:if test="${requestScope.CHECK == '1'}">
                <script type="text/javascript">
                    swal("Success", "Good Job", "success");
                </script>
            </c:if>
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
                    <li class="active">
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
                    <li>
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
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h3 class="text-left text-primary font-weight-bold">List Product</h3>
                                        </div>
                                        <div class="col-md-6 text-right">
                                            <a href="Admin/adminAddService.jsp"  class="btn btn-primary" >Add New Product</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <c:if test="${requestScope.LIST_SERVICE != null}">
                                <table class="table table-bordered table-hover myTable">
                                    <thead class="text-primary">
                                        <tr>
                                            <th>Service ID</th>
                                            <th>Service Name</th>
                                            <th>Price</th>
                                            <th>Slot</th>
                                            <th>Image</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                            <th>Edit</th>
                                        </tr>
                                    </thead>
                                    <c:forEach items="${requestScope.LIST_SERVICE}" var="service">
                                        <tr>
                                            <td>${service.serviceID}</td>
                                            <td>${service.serviceName}</td>
                                            <td>${service.price}</td>
                                            <td>${service.slot}</td>
                                            <td><img src="ImgService/${service.image}" style="width: 150px; height: 100px"></td>
                                            <td>
                                                <span style="width: 60px;height: 20px" class="badge badge-<c:if test='${service.isActive}'>success</c:if><c:if test='${!service.isActive}'>danger</c:if>" >
                                                    <c:if test='${service.isActive}'>Active</c:if>
                                                    <c:if test='${!service.isActive}'>Closing</c:if>
                                                    </span>
                                                </td>
                                            <c:url var="openlink" value="AdminOpenService">
                                                <c:param name="serviceID" value="${service.serviceID}"/>
                                            </c:url>
                                            <c:url var="closelink" value="AdminCloseService">
                                                <c:param name="serviceID" value="${service.serviceID}"/>
                                            </c:url>
                                            <c:if test="${service.isActive}">
                                                <td><a href="${closelink}" class="btn btn-warning">Close</a></td>
                                            </c:if>
                                            <c:if test="${!service.isActive}">
                                                <td><a href="${openlink}" class="btn btn-success">open</a></td>
                                            </c:if>
                                            <c:url var="updateLink" value="AdminEditServiceController">
                                                <c:param name="serviceID" value="${service.serviceID}"/>
                                            </c:url>    
                                            <td><a href="${updateLink}" class="btn btn-primary" >Edit</a></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </div>
                        <div class="card-footer myCardFooter">
                            <ul class="pagination">
                                <c:forEach var="i" begin="1" end="${requestScope.NUMBER_PAGE}" step="1">
                                    <li class="page-item"><a class="page-link" href="AdminViewPetController?idPage=${i}">${i}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>
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

