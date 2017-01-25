<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>Exam</th>
				<th>Date</th>
				<th>Starting Date</th>
				<th>Ending Date</th>
				<th>Student Full Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="userAttemptReg" items="${listStudentExamSignup}"
				varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${userAttemptReg.attempt.exam.name}</td>
					<td>${userAttemptReg.attempt.examDate}</td>
					<td>${userAttemptReg.attempt.startRegistrationDate}</td>
					<td>${userAttemptReg.attempt.endRegistrationDate}</td>
					<td><c:out value="${userAttemptReg.student.firstName}" /> <c:out
							value="${userAttemptReg.student.lastName}" /></td>
					<td>
						<form method="post">
							<input id="userAtIds" type="hidden" name="userAtRegId"
								value="${userAttemptReg.userAtRegId}" />
							<button class="btn btn-default" id="btnAccept" type="submit">Accept</button>
						</form>
					<td><form method="post">
							
							<input id="usrreject" type="hidden" name="userAtRegId"
								value="${userAttemptReg.userAtRegId}" />
							<button class="btn btn-default" id="btnreject" type="submit">Reject</button>



						</form></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

