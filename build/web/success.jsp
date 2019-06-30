<%-- 
    Document   : success
    Created on : Jun 15, 2019, 2:04:18 PM
    Author     : 99hai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .container-all{
                position: relative;
            }
            .container-success{
                position: absolute;
                left:30%;
                top:150px;
                border: 1px solid black;
                width: 550px;
                height: 200px;
                background-color: rgba(234,231,231,0.5);
                padding: 20px;
                box-shadow: 5px 10px #888888;
            }
        </style>
    </head>
    <body>
        <div class="container-all">
            <div class="container-success">
                <h1>Buy Success ! Thanks for your crash !</h1>
                <a href="index.jsp">Back To home</a>
            </div>
        </div>
    </body>
</html>
