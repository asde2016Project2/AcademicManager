<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="left_col scroll-view">
    <div class="navbar nav_title" style="border: 0;">
        <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Gentelella Alela!</span></a>
    </div>

    <div class="clearfix"></div>

    <!-- menu profile quick info -->
    <div class="profile clearfix">
        <div class="profile_pic">
            <img src="<c:url value="/resources/images/img.jpg"/>" alt="..." class="img-circle profile_img">
        </div>
        <div class="profile_info">
            <span>Admin,</span>
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
                <li class="active"><a href="<c:url value="/admin/dashboard"/>"><i class="fa fa-home"></i> Dashboard</a></li>                           
            </ul>
        </div>

        <div class="menu_section">
            <h3>Didattic Offer</h3>
            <ul class="nav side-menu">                
                <li>
                    <a><i class="fa fa-home"></i> Exam <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                        <li><a href="<c:url value="/admin/examForm" />">Create Exam</a></li>
                        <li><a href="<c:url value="/admin/exams/" />">List Exam</a></li>                                               
                    </ul>
                </li>                
                <li><a><i class="fa fa-desktop"></i> Study Plan <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                        <li><a href="<c:url value="/admin/create/studyplan" />">Create Study Plan</a></li>
                        <li><a href="<c:url value="/admin/list/studyplan"/>">List Study Plan</a></li>                                                                                                                                                
                    </ul>
                </li>               
            </ul>
        </div>

        <div class="menu_section">
            <h3>Manage Accounts</h3>
            <ul class="nav side-menu">                
                <li><a><i class="fa fa-edit"></i> Approval <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                        <li><a href="<c:url value="/admin/registrationStudent"/>">Students Approval</a></li>
                        <li><a href="<c:url value="/admin/registrationProfessor"/>">Professor Approval</a></li>
                        <li><a href="<c:url value="/admin/registrationAdmin"/>">Admin Approval</a></li>                                                                        
                    </ul>
                </li>                             
            </ul>
        </div>

    </div>
    <!-- /sidebar menu -->
   
</div>