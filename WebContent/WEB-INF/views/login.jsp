<%-- 
    Document   : login
    Created on : Dec 25, 2016, 11:09:15 PM
    Author     : Gezahegn
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login page</title>
    <link href="<c:url value="/resources/core/css/bootstrap.css" />" rel="stylesheet">
</head>

<body>
   
    <div class="container">
        <div id="mainWrapper">
            <div class="login-container">
                <div class="login-card">
                    <div class="login-form">
                        <form:form  method="post" class="form-horizontal" commandName="login">
                           
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                                <input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
                            </div>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                            </div>
                           

                            <div class="form-actions">
                                <input type="submit" class="btn btn-block btn-primary btn-default" value="Log in">
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
