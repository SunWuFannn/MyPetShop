<%-- 
    Document   : adminAddService
    Created on : Jun 21, 2019, 4:52:28 PM
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
                                            <h3 class="text-left text-primary font-weight-bold">List Product</h3>
                                        </div>
                                        <div class="col-md-6 text-right">
                                            <button class="btn btn-primary" id="btnThem" data-toggle="modal"
                                                    data-target="#myModal">Add New Product</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <form action="/PETWATER/AdminAddServiceController" method="POST">
                                <table>
                                    <tr>
                                        <td><p>Service ID</p></td>
                                        <td><input type="text" name="txtServiceID" required="true" /></td>
                                        <td><font color="red">${requestScope.ERROR_SERVICE.errID}</font></td>
                                    </tr>
                                    <tr>
                                        <td><p>Service Name</p></td>
                                        <td><input type="text" name="txtServiceName" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_SERVICE.errName}</font></td>
                                    </tr>
                                    <tr>
                                        <td><p>Slot</p></td>
                                        <td><input type="number" min="1" name="txtSlot" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_SERVICE.errSlot}</font></td>
                                    </tr>
                                    <tr>
                                        <td><p>Price</p></td>
                                        <td><input type="number" min="1" name="txtPrice" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_SERVICE.errPrice}</font></td>
                                    </tr>
                                    <tr>
                                        <td><p>Image</p></td>
                                        <td><input type="file" name="txtImage" style="margin-left: 50px"/></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td><p>Date Schedual</p></td>
                                        <td><input type="text" name="txtDate" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_SERVICE.errDate}</font></td>
                                    </tr>
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
