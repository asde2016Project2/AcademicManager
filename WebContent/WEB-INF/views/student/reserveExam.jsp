<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>
<div class="panel panel-default">
	<div class="bs-docs-section">
		<div class="row">
			<div class="col-lg-12">
				<c:if test="${!empty attempts }">
					<table class="table table-hover ">
						<thead>
							<tr>
								<th width="80">ID</th>
								<th width="220">Exam Name</th>
								<th width="120">Date Hour</th>
								<th width="120">Building Class Room</th>
								<th width="120">Registration Starting Date</th>
								<th width="120">Registration Ending Date</th>
								<th width="200">Professor</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="attempt" items="${attempts}" varStatus="status">
								<tr>
									<td>${status.index+1 }</td>
									<td>${attempt.exam.name}</td>
									<td>${attempt.examDate}</td>
									<td>${attempt.classroom}</td>
									<td>${attempt.startRegistrationDate}</td>
									<td>${attempt.endRegistrationDate}</td>
									<td><c:out value="${attempt.professor.firstName}" /> <c:out
											value="${attempt.professor.lastName}" /></td>

									<td>
										<form method="post">
											<input type="hidden" name="attemptId"
												value="${attempt.attemptId}" />
											<button  class="btn btn-danger" type="submit"
												disabled="disabled" name="cancel" value="${attempt.status}">Cancel</button>
										</form>
									</td>
									<td>
										<form method="post">
											<input type="hidden" name="attemptId"
												value="${attempt.attemptId}" />
											<button class="btn btn-primary" type="submit" name="signup"
												value="${attempt.status}">Signup</button>
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