<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Academic Manager - Administrator</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/admin-style.css"/>">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>	
		<script type="text/javascript" src="<c:url value="/resources/script/admin-script.js"/>"></script>
	</head>
	<body>
		<div id="everything">
			<div id="header">
				<div id="button-menu">
					<img src="<c:url value="/resources/images/menu.png"/>" />
				</div>
			</div>
			<div id="content">
				<div id="operation">
					<%@include file="/WEB-INF/views/admin/studyplans.jsp" %>
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
	</body>
</html>