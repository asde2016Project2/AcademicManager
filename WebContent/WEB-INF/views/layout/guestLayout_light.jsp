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
        
        <link href="<c:url value="/resources/vendors/bootstrap-daterangepicker/daterangepicker.css"/>" rel="stylesheet">
        
        <!-- Datatables -->
        <link href="<c:url value="/resources/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css"/>" rel="stylesheet">
        
        
        <!-- Custom Theme Style -->        
        <link href="<c:url value="/resources/css/custom.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/customization.css"/>" rel="stylesheet">

    </head>

    <body class="nav-md lightBody">
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col">
                    <tiles:insertAttribute  name="sidebar" />   
                </div>

                <!-- page content -->
                <div class="right_col" role="main">
                    <div>
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
        
        <script src="<c:url value="/resources/vendors/moment/min/moment.min.js"/>"></script>    
        <script src="<c:url value="/resources/vendors/bootstrap-daterangepicker/daterangepicker.js"/>"></script>               
        
        <script src="<c:url value="/resources/vendors/datatables.net/js/jquery.dataTables.min.js"/>"></script>
        <script src="<c:url value="/resources/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"/>"></script>
        <script src="<c:url value="/resources/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/vendors/datatables.net-buttons/js/buttons.flash.min.js"/>"></script>
        <script src="<c:url value="/resources/vendors/datatables.net-buttons/js/buttons.html5.min.js"/>"></script>
        <script src="<c:url value="/resources/vendors/datatables.net-buttons/js/buttons.print.min.js"/>"></script>
        <script src="<c:url value="/resources/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"/>"></script>
        <script src="<c:url value="/resources/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"/>"></script>
        <script src="<c:url value="/resources/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"/>"></script>
        <script src="<c:url value="/resources/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"/>"></script>
        <script src="<c:url value="/resources/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"/>"></script>

        <!-- Custom Theme Scripts -->        
        <script src="<c:url value="/resources/js/custom.min.js"/>"></script>        
        <script src="<c:url value="/resources/js/customization.js"/>"></script>     
    </body>
</html>
