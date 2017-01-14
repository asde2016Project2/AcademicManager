<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="<c:url value="/resources/core/css/bootstrap.min.css"/>"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="navbar-header">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=request.getContextPath()%>/">Home</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/exams/">Exam</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/careerexams/">CareerExam</a></li>
			</ul>
		</div>
	</div>
</body>
</html>

