<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>
<div class="row">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>Exam Name</th>
				<th>Date Hour</th>
				<th>Building Class Room</th>
				<th>Student Full Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="userAttemptRegistration" items="${listStudentBooked}"
				varStatus="status">

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

							<button class="btn btn-danger" id="btnCancel" type="submit">Cancel</button>

							<button class="btn btn-default" id="btnSignup" type="submit"
								disabled="disabled">Signup</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
