<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Session: </h1>

 <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="120">DEGREE COURSE</th>
                <th width="120">ACADEMIC YEAR</th>
                <th width="120">STARTING DATE</th>
                <th width="120">ENDING DATE</th>
                
            </tr>
       <c:forEach items="${lista}" var="examSession">
            <tr>
            	<td><c:out value="${examSession.examSessionId}"/></td>
                <td><c:out value="${examSession.degreeCourse.name}" /></td>
                <td><c:out value="${examSession.academicYear}" /></td>
                <td><c:out value="${examSession.startingDataString}"/></td>
                <td><c:out value="${examSession.endingDataString}"/></td>
            </tr>
            
       </c:forEach>
</table>

<table class="tg">
            <tr>
                <th width="80">Exam Date</th>
                <th width="120">Start</th>
                <th width="120">end</th>
                <th width="120">Name Exam</th>
                <th width="120">Room</th>
                <th width="120">ProfUsern</th>
                <th width="120">attemptID</th>
            </tr>
       <c:forEach items="${attemptList}" var="attempt">
            <tr>
            	<td><c:out value="${attempt.examDate}"/></td>
                <td><c:out value="${attempt.examSession.startingDataString}" /></td>
                <td><c:out value="${attempt.examSession.endingDataString}" /></td>
                <td><c:out value="${attempt.exam.name}"/></td>
                <td><c:out value="${attempt.classroom}"/></td>
                <td><c:out value="${attempt.professor.username}"/></td>
                <td><c:out value="${attempt.attemptId}"/></td>
            </tr>
            
       </c:forEach>
</table>