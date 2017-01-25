<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>
<div class="row">
<div class="x_content">     
	   <c:if test="${!empty message }">
                    <div class="row">
                        <div class="alert alert-success">
                            ${message}
                        </div>
                    </div>
                </c:if>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Exam Name</th>
					<th>Date Hour</th>
					<th>Building Class Room</th>
					<th>Registration Starting Date</th>
					<th>Registration Ending Date</th>
					<th>Professor</th>
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

							<button disabled="disabled" class="btn btn-danger" id="btnCancel" type="submit">Cancel</button>
							<input id="userAtId" type="hidden" name="attemptId"
								value="${attempt.attemptId}" />
							<button class="btn btn-default" id="btnSignup" type="submit"
								>Signup</button>
								 <c:if test="${!empty message }">
                    <div class="row">
                        <div class="alert alert-success">
                            ${message}
                        </div>
                    </div>
                </c:if>
						</form>
					</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		     <c:if test="${!empty error }">
                    <div class="row">
                        <div class="alert alert-danger">
                            ${error}
                        </div>
                    </div>
                </c:if>         

            </div>
		
	
</div>
