<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<h1>${pageTitle}</h1>

<div class="row">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th >ID</th>
				<th>Exam Name</th>
				<th>Date Hour</th>
				<th>Building Class Room</th>
				<th>Registration Starting Date</th>
				<th>Registration Ending Date</th>
				<th>Professor</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="attempt" items="${listOfExamReservation}"
				varStatus="status">

				<tr>
					<td>${status.index+1 }</td>
					<td>${attempt.exam.name}</td>
					<td>${attempt.examDate}</td>
					<td>${attempt.classroom}</td>
					<td>${attempt.startRegistrationDate}</td>
					<td>${attempt.endRegistrationDate}</td>
					<td><c:out value="${attempt.professor.firstName}" /> <c:out
							value="${attempt.professor.lastName}" /></td>
							<td><a class="btn btn-default"
						href="<c:url  value='/student/list/ExamReserve/${attempt.attemptId}'/>">Book</a></td>
			
					
				</tr>

			</c:forEach>
		</tbody>
	</table>
</div>
