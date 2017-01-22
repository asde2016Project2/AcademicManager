<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div>
      <h1>Exam Booklet</h1>
        
        <label>Exam Name:</label>
         <label>${examName} <label>
        <br>
        <label>Student Name:</label>
        <label>${studFullName}</label>
        <br>
        <label>Number of Students Registered:</label>
        <lable>${signedStudent}</lable>
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
				<div class="divTableCell"><c:out value="${userAttemptRegistration.attempt.professor.firstName} ${userAttemptRegistration.attempt.professor.lastName}"></c:out> </div>
				<div class="divTableCell">
					<form method="post">
						<input id="userAtId" type="hidden" name="userAtRegId" value="${userAttemptRegistration.attempt.attemptId}" />
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