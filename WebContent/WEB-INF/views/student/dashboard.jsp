<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="everything">
			<div id="header">
				<div id="button-menu">
					<img src="<c:url value="/resources/images/menu.png"/>" />
					<%@include file="/WEB-INF/views/student/studentDashboard.jsp" %>
				</div>
			</div>
			<div id="content">
				<div id="operation">
					<%@include file="/WEB-INF/views/student/studentDashboard.jsp" %>
				</div>
				<div id="menu">
					<ul>
						<li>
							<img src="<c:url value="/resources/images/admin/studyplans.png"/>" />
							<a href="studyPlans">Study Plans</a>
						</li>
						<li>
							<img src="<c:url value="/resources/images/admin/registrations.png"/>" />
							<a href="registrations">Registrations</a>
						</li>
						<li>
							<img src="<c:url value="/resources/images/admin/logout.png"/>" />
							<a href="logout">Logout</a>
						</li>
					</ul>
				</div>
			</div>
		</div>