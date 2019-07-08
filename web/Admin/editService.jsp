<%-- 
    Document   : editService
    Created on : Jun 29, 2019, 1:55:02 PM
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
                                    <h3 class="text-left text-primary font-weight-bold">Update Accessory</h3>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">

                        </div>
                        <div class="card-footer myCardFooter">
                            <form action="AdminUpdateServiceController" method="POST">
                                <table>
                                    <tr>
                                        <td>Service ID</td>
                                        <td><input type="text" name="txtServiceID" value="${requestScope.SERVICE.serviceID}" readonly="true"/></td>
                                    </tr>
                                    <tr>
                                        <td>Service Name</td>
                                        <td><input type="text" name="txtServiceName" value="${requestScope.SERVICE.serviceName}" required="true"/></td>
                                    </tr>
                                    <tr>
                                        <td>Price</td>
                                        <td><input type="number" min="1" name="txtPrice" value="${requestScope.SERVICE.price}" required="true"/></td>
                                    </tr>
                                    <tr>
                                        <td>Image</td>
                                        <td><input type="file" name="txtImage"/></td>
                                    </tr>

                                    <tr>
                                        <td>Slot</td>
                                        <td><input name="txtSlot"  min="1" type="number" value="${requestScope.SERVICE.slot}" required="true"/></td>
                                    </tr>
                                </table>
                                <input type="submit" name="action" class="form-control btn btn-success w-25" value="Update"/>
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
