<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
     <h1>Exam Booklet</h1>
        
        <label>Exam Name:</label>
         <label>${examName} <label>
        <br>
        <label>Student Name:</label>
        <label>${studentName}</label>
        <br>
        <label>Number of Students:</label>
        <lable>${signedStudent}</lable>
        <br>
        
        <label></label>
        <label>Exam Type:</label>
        <label>Writen Exam</label>
        <br>
        <label>Test Method</label>
        <label></label>

     

    
    <c:if test="${!empty attempts}">
    <div class="divTable">
	<div class="divTableBody">
		<div class="divTableRow">
			<div class="divTableCell"><strong>ID</strong></div>
			<div class="divTableCell"><strong>Day</strong></div>
			<div class="divTableCell"><strong>ClassRoom</strong></div>
			<div class="divTableCell"><strong>Professor</strong></div>
			<div class="divTableCell"><strong>Reserve</strong></div>
		</div>
		<c:forEach var="attempt" items="${attempts}"  varStatus="status">
			<div class="divTableRow">
		     	<div class="divTableCell">${status.index+1}</div>
				<div class="divTableCell">${attempt.examDate}</div>
				<div class="divTableCell">${attempt.classroom}</div>
				<div class="divTableCell">${attempt.professor.firstName}</div>
				<div class="divTableCell">
					<form method="post">
						<input type="hidden" name="attemptId" value="${attempt.attemptId}" />
						<button type="submit" name="cancel" value="${attempt.status}">Cancel</button>
					</form>					
					<form method="post">
					    <input type="hidden" name="attemptId" value="${attempt.attemptId}" />
					    <button type="submit" name="signup" value="${attempt.status}">Signup</button>
					</form>
				</div>
			</div>
		</c:forEach>	
	</div>
</div>
</c:if>

</div>