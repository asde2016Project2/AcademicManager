<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default">
	<div class="bs-docs-section">
		<div class="row">
			<div class="col-lg-8">
				<c:if test="${!empty listStudentExamSignup }">
					<table class="table table-hover ">
						<thead>
							<tr>
								<th width="80">ID</th>
								<th width="120">Exam</th>
								<th width="120">Date</th>
								<th width="120">Registration Starting Date</th>
								<th width="120">Registration Ending Date</th>
								<th width="120">Student Full Name</th>
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
									<td><c:out value="${userAttemptReg.student.firstName}" />
										<c:out value="${userAttemptReg.student.lastName}" /></td>
									<td>
									<form method="post">
									<a class="btn btn-primary"
										href="<c:url value='/professor/accept/viewStudentExamSignup/${userAttemptReg.userAtRegId}'/>">Accept</a>
										</form>
										</td>
									<td><form method="post">
									<a class="btn btn-danger"
										href="<c:url value='/professor/reject/viewStudentExamSignup/${userAttemptReg.userAtRegId}'/>">Reject</a>
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

