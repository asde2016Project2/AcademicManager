<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <a href="<c:url value="/admin/create/studyplan"/>" class="btn btn-primary">Create Study Plan</a>
</div>
<br/>

<div class="row">
	<table class="table table-bordered">
		<thead>
			<tr>
					<th>Study plan name</th>
					<th>Degree course name</th>
					<th>Detail</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="studyPlan" items="${studyPlans}">
				<tr>
						<td>${studyPlan.name}</td>
						<td>${studyPlan.degreeCourse.name}</td>
						<td>
							<span class="inline"><a href="<c:url value="/admin/detail/studyplan/${studyPlan.studyPlanId}"/>" class="btn btn-info">Detail</a></span>
						</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
