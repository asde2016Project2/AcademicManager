<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="bookedExam" items="${listStudentBooked}">
	<c:out value="${bookedExam.attempt.attemptId} ${bookedExam.student.userId}"></c:out>
</c:forEach>

<div>
      <h1>Exam Booklet</h1>
        
        <label>Exam Name:</label>
         <label>${examName} <label>
        <br>
        <label>Student Name:</label>
        <label>${studentName}</label>
        <br>
        <label>Number of Students Registered:</label>
        <lable>23</lable>
        <br>
        <label></label>
        <label>Exam Type:</label>
        <label>Writen Exam</label>
        <br>

    <c:if test="${!empty listStudentBooked}">
    <div class="divTable">
	<div class="divTableBody">
		<div class="divTableRow">
			<div class="divTableCell"><strong>ID</strong></div>
			<div class="divTableCell"><strong>Day</strong></div>
			<div class="divTableCell"><strong>ClassRoom</strong></div>
			<div class="divTableCell"><strong>Professor</strong></div>
			<div class="divTableCell"><strong>Reserve</strong></div>
		</div>
		<c:forEach var="userAttemptRegistration" items="${listStudentBooked}"  varStatus="status">
			<div class="divTableRow">
		     	<div class="divTableCell">${status.index+1}</div>
				<div class="divTableCell">${userAttemptRegistration.attempt.examDate}</div>
				<div class="divTableCell">${userAttemptRegistration.attempt.classroom}</div>
				<div class="divTableCell">${userAttemptRegistration.attempt.professor.firstName}</div>
				<div class="divTableCell">
					<form method="post">
						<input id="userAtId" type="hidden" name="userAtRegId" value="${userAttemptRegistration.userAtRegId}" />
						<button id="btnCancel" type="submit" name="cancel" value="${userAttemptRegistration.attempt.status}">Cancel</button>
						<button id="btnSignup" type="submit" disabled="disabled"  name="signup" value="${userAttemptRegistration.status}">Signup</button>
					</form>
										
				</div>
			</div>
		</c:forEach>	
	</div>
</div>
</c:if>

</div>