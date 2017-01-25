<%@page import="it.unical.asde.uam.helper.SessionHelper"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="left_col scroll-view">
    <div class="navbar nav_title" style="border: 0;">
        <a href="<c:url value="/"/>" class="site_title"><i class="fa fa-paw"></i> <span>AcMan!</span></a>
    </div>

    <div class="clearfix"></div>

    <!-- /menu profile quick info -->

    <br />

    <!-- sidebar menu -->
    <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
        <div class="menu_section">            
            <ul class="nav side-menu">
                <li><a href="<c:url value="/"/>"><i class="fa fa-home"></i> Home</a></li>                           
            </ul>
            <ul class="nav side-menu">
                <li><a href="<c:url value="/student/register" />"><i class="fa fa-graduation-cap"></i> Register as Student</a></li>
            </ul>
            <ul class="nav side-menu">
                <li><a href="<c:url value="/professor/register" />"><i class="fa fa-users"></i> Register as Professor</a></li>                                               
            </ul>
            <ul class="nav side-menu">
                <li><a href="<c:url value="/admin/register" />"><i class="fa fa-user"></i> Register as Administrator</a></li>                                               
            </ul>
        </div>

        <div class="menu_section">
            <h3><i class="fa fa-institution"></i> Explore The University</h3>
            <ul class="nav side-menu">                
                <li><a href="<c:url value="/listStudyPlan"/>"><i class="fa fa-language"></i> Show Study Plans</a></li>                                                                                                                                                 
            </ul>
        </div>
            
            <div class="menu_section">
            <h3><i class="fa fa-cogs"></i> About Us</h3>
            <ul class="nav side-menu">                
                <li><a href="<c:url value="/authors"/>"><i class="fa fa-question"></i> Who we are</a></li>                                                                                                                                                 
            </ul>
        </div>

    </div>
    <!-- /sidebar menu -->

</div>