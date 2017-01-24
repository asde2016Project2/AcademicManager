<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Attempts: </h1>

<table class="table table-bordered">
            <tr>
                <th width="120">Start Registration</th>
                <th width="120">End Registration</th>
                 <th width="80">Exam Date</th>
                <th width="120">Name Exam</th>
                <th width="120">Room</th>
                <th width="120">Professor</th>
            </tr>
       <c:forEach items="${attemptList}" var="attempt">
            <tr>
                <td><c:out value="${attempt.examSession.startingDataString}" /></td>
                <td><c:out value="${attempt.examSession.endingDataString}" /></td>
                <td><c:out value="${attempt.examDate}"/></td>
                <td><c:out value="${attempt.exam.name}"/></td>
                <td><c:out value="${attempt.classroom}"/></td>
                <td><c:out value="${attempt.professor.username}"/></td>
            </tr>
            
       </c:forEach>
</table>