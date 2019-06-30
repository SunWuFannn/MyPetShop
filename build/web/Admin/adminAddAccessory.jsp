<%-- 
    Document   : adminAddAccessory
    Created on : Jun 30, 2019, 2:43:44 PM
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
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h3 class="text-left text-primary font-weight-bold">Add Accessory</h3>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <form action="/PETWATER/AddAccessoryController"  method="POST">
                                <table>
                                    <tr>
                                        <td><p>Accessory ID</p></td>
                                        <td><input type="text" name="txtAccessID" placeholder="AcessoryID" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_ACCESSORY.errID}</font></td>
                                    </tr>
                                    <tr>
                                        <td><p>Accessory Name</p></td>
                                        <td><input type="text" name="txtAccessName" placeholder="AccessoryName" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_ACCESSORY.errName}</font></td>
                                    </tr>
                                    <tr>
                                        <td><p>Price</p></td>
                                        <td><input type="number" min="1" name="txtAccessPrice" placeholder="Price" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_ACCESSORY.errPrice}</font></td>
                                    </tr>
                                    <tr>
                                        <td><p>Image</p></td>
                                        <td><input type="file" name="txtAccessImage" placeholder="Image" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_ACCESSORY.errImage}</font></td>
                                    </tr>
                                    <tr>
                                        <td><p>Quantity</p></td>
                                        <td><input type="number" min="1" name="txtAccessQuantity" placeholder="Quantity" required="true"/></td>
                                        <td><font color="red">${requestScope.ERROR_ACCESSORY.errQuantity}</font></td>
                                    </tr>
                                    <tr>
                                        <td><p>Type</p></td>
                                        <td><select class="form-control" name="txtAccessTypeID" required="true">
                                                <option value="0">Select type</option>
                                                <option value="1">Ring</option>
                                                <option value="2">Coat</option>
                                                <option value="3">Food</option>
                                                <option value="4">Medical</option>
                                            </select>
                                        </td>
                                        <td><font color="red">${requestScope.ERROR_ACCESSORY.errTypeID}</font></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="submit" class="btn btn-success" value="Add Product" /></td>
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
