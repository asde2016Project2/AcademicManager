<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Study Plans Dashboard</title>
	</head>
	<body>
		<div id="label-study-plans">Study Plans</div>
		<div id="list-study-plans">
			<button type="submit" class="new">
				+new
			</button>
			<div class="divTable">
				<div class="divTableBody">
					<div class="divTableRow">
						<div class="divTableCell">Study plan</div>
						<div class="divTableCell">Degree Course</div>
						<div class="divTableCell">Operations</div>
					</div>
					<c:forEach var="studyPlan" items="${studyPlans}">
						<div class="divTableRow">
							<div class="divTableCell">${studyPlan.name}</div>
							<div class="divTableCell">${studyPlan.degreeCourse.name}</div>
							<div class="divTableCell">
								<form><button type="submit" class="update">update</button></form>
								<form><button type="submit" name="${studyPlan.name}" class="details">details</button></form>
								<button type="submit" class="delete">delete</button>
							</div>
						</div>
					</c:forEach>	
				</div>
			</div>
			
		</div>
	</body>
</html>