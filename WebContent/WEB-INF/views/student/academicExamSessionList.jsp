<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>${pageTitle}</h1>
<div class="row">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th width="80">ID</th>
				<th width="220">Degree Course</th>
				<th width="120">Appeal</th>
				<th width="120">Entry</th>
				<th width="120">Academic Year</th>
				<th width="60">Edit</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="examSession" items="${listExamSession}"
				varStatus="status">

				<tr>
					<td>${status.index+1 }</td>
					<td>${examSession.degreeCourse.name}</td>
					<td>${examSession.startingDate}</td>
					<td>${examSession.endingDate}</td>
					<td>${examSession.academicYear}</td>
					<td><a class="btn btn-default"
						href="<c:url  value='/student/academicExamSessionList/examSession/${examSession.examSessionId}'/>">View</a></td>
				</tr>

			</c:forEach>
		</tbody>

	</table>

</div>

