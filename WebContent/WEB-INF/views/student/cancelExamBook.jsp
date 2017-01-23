<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>
<div class="panel panel-default">
	<div class="panel-body" style="min-height: 100; max-height: 100;">

		<c:if test="${!empty listStudentBooked }">
			<table class="table table-striped table-hover ">
				<thead>
					<tr>
						<th width="80">ID</th>
						<th width="120">Exam Name</th>
						<th width="120">Date Hour</th>
						<th width="120">Building Class Room</th>
						<th width="120">Student Full Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="userAttemptRegistration"
						items="${listStudentBooked}" varStatus="status">

						<tr>
							<td>${status.index+1 }</td>
							<td>${userAttemptRegistration.attempt.exam.name}</td>
							<td>${userAttemptRegistration.attempt.examDate}</td>
							<td>${userAttemptRegistration.attempt.classroom}</td>
							<td><c:out
									value="${userAttemptRegistration.attempt.professor.firstName}" />
								<c:out
									value="${userAttemptRegistration.attempt.professor.lastName}" />
							</td>
							<td>
								<form method="post">
									<input id="userAtId" type="hidden" name="userAtRegId"
										value="${userAttemptRegistration.userAtRegId}" />
										
									<button id="btnCancel" type="submit">Cancel</button>
									<button id="btnSignup" type="submit" disabled="disabled">Signup</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</div>
