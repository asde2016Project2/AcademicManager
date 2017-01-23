<%@page import="it.unical.asde.uam.helper.SessionHelper"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="left_col scroll-view">
    <div class="navbar nav_title" style="border: 0;">
        <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Gentelella Alela!</span></a>
    </div>

    <div class="clearfix"></div>

    <!-- menu profile quick info -->
    <div class="profile clearfix">
        <div class="profile_pic">
        	<%
        	String defaultPic = "/resources/images/default-user.png";
        	if(SessionHelper.getUserProfessorLogged(session).getPhoto() != null) {
        		out.print("diverso da null");
        		defaultPic = SessionHelper.getPhoto(SessionHelper.getUserProfessorLogged(session).getPhoto());
        		%>
        		<img src="<c:url value="data:image/jpeg;base64,<%=defaultPic%>"/>" class="img-circle profile_img">
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
            <span>Professor,</span>
            <h2>John Doe</h2>
        </div>
        <div class="clearfix"></div>
    </div>
    <!-- /menu profile quick info -->

    <br />

    <!-- sidebar menu -->
    <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
        <div class="menu_section active">
            <h3>Main Menu</h3>
            <ul class="nav side-menu">
                <li class="active"><a href="<c:url value="/professor/dashboard"/>"><i class="fa fa-home"></i> Dashboard</a></li>                           
            </ul>
        </div>

        <div class="menu_section">
            <h3>Manage Students</h3>
            <ul class="nav side-menu">                
                <li>
                    <a><i class="fa fa-home"></i> Students <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                        <li><a href="<c:url value="/professor/studentExtraExamSession" />">Student Extra Exam Session</a></li>
                        <li><a href="<c:url value="/professor/informationStudent" />">Information Student</a></li>                                               
                    </ul>
                </li>                                
            </ul>
        </div>
      

    </div>
    <!-- /sidebar menu -->
   
</div>