<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>${pageTitle}</title>

        <!-- Bootstrap -->
        <link href="<c:url value="/resources/vendors/bootstrap/dist/css/bootstrap.min.css"/>" rel="stylesheet">        
        <!-- Font Awesome -->        
        <link href="<c:url value="/resources/vendors/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
        <!-- NProgress -->        
        <link href="<c:url value="/resources/vendors/nprogress/nprogress.css"/>" rel="stylesheet">
        <!-- Animate.css -->        
        <link href="<c:url value="/resources/vendors/animate.css/animate.min.css"/>" rel="stylesheet">

        <!-- Custom Theme Style -->        
        <link href="<c:url value="/resources/css/custom.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/customization.css"/>" rel="stylesheet">
    </head>

    <body class="nav-md loginBody">
        <tiles:insertAttribute  name="body" />              
    </body>
</html>
