<%@page import="it.unical.asde.uam.helper.SessionHelper"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="left_col scroll-view">
    <div class="navbar nav_title" style="border: 0;">
        <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>AcMan!</span></a>
    </div>

    <div class="clearfix"></div>

    <!-- menu profile quick info -->
    <div class="profile clearfix">
        <div class="profile_pic">
            
        	<%
        	String defaultPic = "/resources/images/default-user.png";
        	if(SessionHelper.getUserStudentLogged(session).getPhoto() != null) {
        		defaultPic = SessionHelper.getPhoto(SessionHelper.getUserStudentLogged(session).getPhoto());
        		%>
        		<img src="data:image/jpeg;base64,<%=defaultPic%>" class="img-circle profile_img">
        		<%
        	}
        	else {
        		%>
        		<img src="<c:url value="<%=defaultPic%>"/>" class="img-circle profile_img">
        		<%
        	}
        	%>
            
        
        </div>
        <div class="profile_info">
            <span>Student,</span>
            <h2>John Doe</h2>
        </div>
        <div class="clearfix"></div>
    </div>
    <!-- /menu profile quick info -->

    <br />

    <!-- sidebar menu -->
    <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
        <div class="menu_section ">
            <h3>Main Menu</h3>
            <ul class="nav side-menu">
                <li><a href="<c:url value="/student/dashboard"/>"><i class="fa fa-home"></i> Dashboard</a></li>                           
            </ul>
        </div>

        <div class="menu_section">
            <h3>Didattic Offer</h3>
            <ul class="nav side-menu">                
                <li>
                    <a><i class="fa fa-certificate"></i> Exam <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                        <li><a href="<c:url value="/student/academicExamSessionList" />">Academic ExamSessions List</a></li>
                        <li><a href="<c:url value="/student/list/ExamReserve" />">Reservation Board</a></li>                                               
                        <li><a href="<c:url value="/student/cancelExamBook" />">Reserved Exam</a></li>                                              
                    </ul>
                </li>                
                <li><a><i class="fa fa-database"></i> Career & Study Plan <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                        <li><a href="<c:url value="/student/visualizeStudyPlan" />">Show Study Plan</a></li>
                        <li><a href="<c:url value="/student/visualizeCareer"/>">Show Career</a></li>                                                                                                                                                                                                                                                                                                                     
                    </ul>
                </li>
                <li><a><i class="fa fa-area-chart"></i> Statistics <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                        <li><a href="<c:url value="/student/visualizeStatistics" />">Show Statistic</a></li>
                        <li><a href="<c:url value="/student/projection"/>">Make Projection</a></li>                                                                                                                                                                                                                                                                                                                     
                    </ul>
                </li>
            </ul>
        </div>
       

    </div>
    <!-- /sidebar menu -->
   
</div>