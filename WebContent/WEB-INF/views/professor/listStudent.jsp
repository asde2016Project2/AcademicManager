<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Session: </h1>

 <table class="tg">
            <tr>
                <th width="80">USERNAME</th>
                <th width="120">FIRST NAME</th>
                <th width="120">SURNAME</th>
                <th width="120">DEGREE COURSE</th>
                
            </tr>
       <c:forEach items="${listaStudenti}" var="student">
            <tr>
            	<td><c:out value="${student.username}" /></td>
            	<td><c:out value="${student.firstName}"/></td>
                <td><c:out value="${student.lastName}" /></td>
                <td><c:out value="${student.studyPlan.degreeCourse.name}"/><c:out value="${student.studyPlan.name}"/></td>
            </tr>
            
       </c:forEach>
</table>