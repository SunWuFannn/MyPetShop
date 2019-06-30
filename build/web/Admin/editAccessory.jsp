<%-- 
    Document   : editAccessory
    Created on : Jun 14, 2019, 5:02:45 PM
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
                                    <h3 class="text-left text-primary font-weight-bold">Update Accessory</h3>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">

                        </div>
                        <div class="card-footer myCardFooter">
                            <form action="MainControllerAdmin" method="POST">
                                <table>
                                    <tr>
                                        <td>AccessoryID :</td>
                                        <td> <input class="form-control" type="text" name="txtAccessID" value="${requestScope.INFO.accessID}" readonly="true"></td>
                                    </tr>
                                    <tr>
                                        <td>AccessoryName :</td>
                                        <td> <input class="form-control" type="text" name="txtAccessName" value="${requestScope.INFO.accessName}" required="true"></td>
                                        <td><font color="red">${requestScope.ERROR_ACCESSORY.errName}</font></td>
                                    </tr><tr>
                                        <td>Price :</td>
                                        <td> <input class="form-control" type="number" min="1" name="txtPrice" value="${requestScope.INFO.price}" required="true"></td>
                                        <td><font color="red">${requestScope.ERROR_ACCESSORY.errPrice}</font></td>
                                    </tr><tr>
                                        <td>Quantity :</td>
                                        <td> <input class="form-control" type="number" min="1" name="txtQuantity" value="${requestScope.INFO.quantity}" required="true"></td>
                                        <td><font color="red">${requestScope.ERROR_ACCESSORY.errQuantity}</font></td>
                                    </tr><tr>
                                        <td>Image :</td>
                                        <td> <input class="form-control"  type="file" name="txtImage" value="${requestScope.INFO.image}"></td>
                                        <td><font color="red">${requestScope.ERROR_ACCESSORY.errImage}</font></td>
                                    </tr><tr>
                                        <td>Type :</td>
                                        <td> 
                                            <select class="form-control" name="txtAccessTypeID">
                                                <option value="0">Select type</option>
                                                <option value="1" <c:if test="${requestScope.INFO.typeID == '1'}">selected=""</c:if> >Ring</option>
                                                <option value="2" <c:if test="${requestScope.INFO.typeID == '2'}">selected=""</c:if> >Coat</option>
                                                <option value="3" <c:if test="${requestScope.INFO.typeID == '3'}">selected=""</c:if> >Food</option>
                                                <option value="4" <c:if test="${requestScope.INFO.typeID == '4'}">selected=""</c:if> >Medical</option>
                                                </select>
                                            </td>
                                            <td><font color="red">${requestScope.ERROR_ACCESSORY.errTypeID}</font></td>
                                    </tr>
                                </table>
                                <input type="submit" name="action" class="form-control btn btn-success w-50" value="Update"/>
                            </form>
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


