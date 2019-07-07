<%-- 
    Document   : admin
    Created on : Jun 13, 2019, 5:45:28 PM
    Author     : 99hai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <!--<link rel="stylesheet" href="CSS/jquery-ui.min.css">-->
        <!-- My CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Admin/CSS/modal.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Admin/CSS/style4.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Admin/CSS/main.css">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body id="mybody">
        <div class="wrapper">
            <c:if test="${requestScope.CHECK == '1'}">
                <script type="text/javascript">
                    swal("Success", "Good Job", "success");
                </script>
            </c:if>
            <c:if test="${sessionScope.ROLE == null}">
                <c:redirect url="/Auth/login.jsp"/>
            </c:if>
            <nav id="sidebar">
                <div class="sidebar-header">
                    <h3>WelCome ${sessionScope.FULLNAME}</h3>
                    <h5><a href="/PETWATER/LogoutController">Logout</h5>
                </div>

                <ul class="list-unstyled components">
                    <li class="active">
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
                    <li>
                        <a href="LoadUserInfoController">
                            <i class="fa fa-paperclip"></i>
                            ProFile
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
                                    <h3 class="text-left text-primary font-weight-bold">List Product</h3>
                                </div>
                                <div class="col-md-6 text-right">
                                    <a class="btn btn-primary" href="Admin/adminAddAccessory.jsp" >Add New Product</a>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="row mb-3">
                                <div class="col">
                                    <div class="input-group">
                                        <form action="SearchAccessoryController" method="POST">
                                            <input type="text" class="form-control" placeholder="Search By Name Product" name="txtSearch">
                                            <input type="submit" class="btn btn-outline-success"  value="Search"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-bordered table-hover myTable">
                                <thead class="text-primary">
                                    <tr>
                                        <th class="nowrap">
                                            <span class="mr-1">Acessory ID</span>
                                            <i class="fa fa-arrow-up" id="SapXepTang"></i>
                                            <i class="fa fa-arrow-down" id="SapXepGiam"></i>
                                        </th>
                                        <th>Acessory Name</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Type</th>
                                        <th>Status</th>
                                        <th>Update</th>
                                        <th>Delete</th>
                                    </tr>
                                </thead>
                                <c:if test="${requestScope.LIST_ACCESSORY != null}">
                                    <c:forEach items="${requestScope.LIST_ACCESSORY}" var="accessory">
                                        <tr>
                                            <td>${accessory.accessID}</td>
                                            <td>${accessory.accessName}</td>
                                            <td>${accessory.price}</td>
                                            <td>${accessory.quantity}</td>
                                            <td>${accessory.typeID}</td>
                                            <td>
                                                <span style="width: 100px;height: 20px" class="badge badge-<c:if test='${accessory.status and accessory.quantity > 0}'>success</c:if><c:if test='${!accessory.status}'>danger</c:if><c:if test='${accessory.quantity == 0 }'>warning</c:if>">
                                                    <c:if test='${accessory.status and accessory.quantity > 0}'>Active</c:if>
                                                    <c:if test='${accessory.status and accessory.quantity == 0}'>Out of Quantity</c:if>
                                                    <c:if test='${!accessory.status}'>Closing</c:if>
                                                    </span>
                                                </td>
                                                <td><a href="AdminEditController?txtID=${accessory.accessID}" class="btn btn-success <c:if test="${accessory.status == 'false'}"> disabled </c:if>">Update</a></td>
                                            <td><a href="AdminDeleteController?txtID=${accessory.accessID}" class="btn btn-danger <c:if test="${accessory.status == 'false'}"> disabled </c:if>">Delete</a></td>
                                            </tr>
                                    </c:forEach>    
                                </c:if>
                            </table>
                        </div>
                        <div class="card-footer myCardFooter">
                            <nav>
                                <ul class="pagination">
                                    <c:forEach var="i" begin="1" end="${requestScope.NUMBER_PAGE}" step="1">
                                        <li class="page-item"><a class="page-link" href="LoadAccessoryAdmin?idPage=${i}">${i}</a></li>
                                        </c:forEach>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- The Modal -->
        <div class="modal fade show" <c:if test="${requestScope.ERROR_ACCESSORY != null}">style="position: fixed ; display: block" </c:if> id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <header class="head-form mb-0">
                            <h2 id="header-title">Add new product</h2>
                        </header>
                        <form action="AddAccessoryController" method="POST" class="form-popup">
                            <div class="modal-body">
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="fa fa-bookmark"></i></span>
                                        </div>
                                        <input type="text"  class="form-control input-sm" name="txtAccessID" placeholder="AcessoryID"/>
                                        <font color="red">${requestScope.ERROR_ACCESSORY.errID}</font>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fa fa-address-book"></i></span>
                                    </div>
                                    <input type="text" name="txtAccessName" placeholder="AccessoryName"/>
                                    <font color="red">${requestScope.ERROR_ACCESSORY.errName}</font>

                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fa fa-money"></i></span>
                                    </div>
                                    <input type="text" name="txtAccessPrice" placeholder="Price"/>
                                    <font color="red">${requestScope.ERROR_ACCESSORY.errPrice}</font>

                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fa fa-file-photo-o"></i></span>
                                    </div>
                                    <input type="file" name="txtAccessImage" placeholder="Image"/>
                                    <font color="red">${requestScope.ERROR_ACCESSORY.errImage}</font>

                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fa fa-plus"></i></span>
                                    </div>
                                    <input type="text" name="txtAccessQuantity" placeholder="Quantity"/>
                                    <font color="red">${requestScope.ERROR_ACCESSORY.errQuantity}</font>

                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fa fa-file"></i></span>
                                    </div>
                                    <select class="form-control" name="txtAccessTypeID">
                                        <option value="0">Select type</option>
                                        <option value="1">Ring</option>
                                        <option value="2">Coat</option>
                                        <option value="3">Food</option>
                                        <option value="4">Medical</option>
                                    </select>
                                    <font color="red">${requestScope.ERROR_ACCESSORY.errTypeID}</font>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer" id="modal-footer">
                            <input type="submit" class="btn btn-success" name="action" value="Create Product">
                            <c:if test="${requestScope.ERROR_ACCESSORY != null}">
                                <a href="/PETWATER/LoadAccessoryAdmin" class="btn btn-danger">Close</a>
                            </c:if>   
                            <c:if test="${requestScope.ERROR_ACCESSORY == null}">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </c:if>   
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </body>

</html>

