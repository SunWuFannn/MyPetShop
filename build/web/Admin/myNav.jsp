<%-- 
    Document   : myNav
    Created on : Jun 30, 2019, 4:48:35 PM
    Author     : 99hai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav id="sidebar">
    <div class="sidebar-header">
        <h3>WelCome ${sessionScope.FULLNAME}</h3>
        <h5><a href="/PETWATER/LogoutController">Logout</h5>
    </div>

    <ul class="list-unstyled components">
        <li class="active">
            <a href="/PETWATER/LoadAccessoryAdmin?idPage=1">
                <i class="fa fa-home"></i>
                Manage All Product
            </a>
        </li>
        <li>
            <a href="/PETWATER/LoadServiceAdminController">
                <i class="fa fa-paperclip"></i>
                Manage Service for Pet
            </a>
        </li>
        <li>
            <a href="/PETWATER/AdminViewPetController?idPage=1">
                <i class="fa fa-paperclip"></i>
                Manage Pet
            </a>
        </li>
        <li>
            <a href="/PETWATER/AdminViewOrderController?idPage=1">
                <i class="fa fa-paperclip"></i>
                Manage Order
            </a>
        </li>
        <li>
            <a href="/PETWATER/AdminManageServiceProcessController">
                <i class="fa fa-paperclip"></i>
                Manage In Process Service
            </a>
        </li>
        <li>
            <a href="/PETWATER/AdminManageHistoryService?idPage=1">
                <i class="fa fa-paperclip"></i>
                History of Service Process
            </a>
        </li>
        <li>
            <a href="/PETWATER/AdminManageAccount?idPage=1">
                <i class="fa fa-paperclip"></i>
                Manage Account
            </a>
        </li>
        <li>
            <a href="/PETWATER/Admin/registerAdmin.jsp">
                <i class="fa fa-paperclip"></i>
                Register Admin
            </a>
        </li>
    </ul>
</nav>