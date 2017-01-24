<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Session: </h1>

 <table class="table table-bordered">
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