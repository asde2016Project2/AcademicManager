<%@page import="it.unical.asde.uam.helper.SessionHelper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="nav_menu">
    <nav>
        <div class="nav toggle">
            <a id="menu_toggle"><i class="fa fa-bars"></i></a>
        </div>

        <ul class="nav navbar-nav navbar-right">
            <li class="">
                <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
          <!--      <img src="<c:url value="/resources/images/img.jpg"/>" /> -->
                    <%=SessionHelper.getUserAdministratorLogged(session).getFirstName() %>
                    <%=SessionHelper.getUserAdministratorLogged(session).getLastName() %>
                    <span class=" fa fa-angle-down"></span>
                </a>
                <ul class="dropdown-menu dropdown-usermenu pull-right">
                    
                    <li><a href="<c:url value="/logout"/>"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                </ul>
            </li>

        </ul>
    </nav>
</div>