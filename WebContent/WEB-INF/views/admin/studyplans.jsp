<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			
			<a href="<c:url value="newstudyplan"/>">new</a>
			<div class="divTable">
				<div class="divTableBody">
					<div class="divTableRow">
						<div class="divTableCell"><strong>Study plan</strong></div>
						<div class="divTableCell"><strong>Degree Course</strong></div>
						<div class="divTableCell"><strong>Operations</strong></div>
					</div>
					<c:forEach var="studyPlan" items="${studyPlans}">
						<div class="divTableRow">
							<div class="divTableCell">${studyPlan.name}</div>
							<div class="divTableCell">${studyPlan.degreeCourse.name}</div>
							<div class="divTableCell">
								<form method="post"><button type="submit" class="update">update</button></form>
								<form method="post"><button type="submit" name="id" value="${studyPlan.studyPlanId}" class="details">details</button></form>
								<button type="submit" class="delete">delete</button>
							</div>
						</div>
					</c:forEach>	
				</div>
			</div>