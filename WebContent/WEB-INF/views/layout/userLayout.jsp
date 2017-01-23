<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" class=" ">
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

        <!-- Custom Theme Style -->        
        <link href="<c:url value="/resources/css/custom.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/customization.css"/>" rel="stylesheet">
        
        <script type="text/javascript" src="<c:url value="/resources/script/webcam.js" />"></script>
    </head>

    <body class="nav-md footer_fixed">
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col">
                    <tiles:insertAttribute  name="sidebar" />   
                </div>

                <!-- top navigation -->
                <div class="top_nav">
                    <tiles:insertAttribute  name="headerbar" />                       
                </div>
                <!-- /top navigation -->

                <!-- page content -->
                <div class="right_col" role="main">
                    <div class="">
                       <tiles:insertAttribute  name="body" />   
                    </div>
                </div>
                <!-- /page content -->

                <!-- footer content -->
                <footer>
                    <div class="pull-right">
                        AcademicManager v0.0.1
                    </div>
                    <div class="clearfix"></div>
                </footer>
                <!-- /footer content -->
            </div>
        </div>

        <!-- jQuery -->        
        <script src="<c:url value="/resources/vendors/jquery/dist/jquery.min.js"/>"></script>
        <!-- Bootstrap -->        
        <script src="<c:url value="/resources/vendors/bootstrap/dist/js/bootstrap.min.js"/>"></script>
        <!-- FastClick -->        
        <script src="<c:url value="/resources/vendors/fastclick/lib/fastclick.js"/>"></script>
        <!-- NProgress -->
        <script src="<c:url value="/resources/vendors/nprogress/nprogress.js"/>"></script>    
		
        <!-- Custom Theme Scripts -->        
        <script src="<c:url value="/resources/js/custom.min.js"/>"></script>        
    </body>
</html>
