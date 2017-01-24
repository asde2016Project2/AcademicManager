<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>
<div class="panel panel-default">
	<div class="bs-docs-section">
		<div class="row">
			<div class="col-lg-8">
				<c:if test="${!empty listStudentBooked }">
					<table class="table table-hover ">
						<thead>
							<tr>
								<th width="80">ID</th>
								<th width="120">Exam Name</th>
								<th width="120">Date Hour</th>
								<th width="120">Building Class Room</th>
								<th width="120">Registration Starting Date</th>
								<th width="120">Registration Ending Date</th>
								<th width="120">Professor</th>
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
									<td>${userAttemptRegistration.attempt.startRegistrationDate}</td>
									<td>${userAttemptRegistration.attempt.endRegistrationDate}</td>
									<td><c:out
											value="${userAttemptRegistration.attempt.professor.firstName}" />
										<c:out
											value="${userAttemptRegistration.attempt.professor.lastName}" /></td>

									<td>
										<form method="post">
											<input id="userAtId" type="hidden" name="attemptId"
												value="${userAttemptRegistration.attempt.attemptId}" />
											<button class="btn btn-danger" id="btnCancel" type="submit" name="cancel"
												value="${userAttemptRegistration.attempt.status}">Cancel</button>
										</form>
									</td>
									<td>
										<form method="post">
											<button class="btn btn-primary" id="btnSignup" type="submit" disabled="disabled"
												name="signup" value="${userAttemptRegistration.status}">Signup</button>
										</form>

									</td>

								</tr>

							</c:forEach>
						</tbody>

					</table>
				</c:if>
			</div>
		</div>
	</div>
</div>