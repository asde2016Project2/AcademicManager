<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Session: </h1>

<form action="registerExam" method="post">
  exam name: <input type="text" name="examname"><br>
  <input type="submit" value="Submit">
</form>

exam: <c:out value="${examName}"/>
 <table class="tg">
            <tr>
                <th width="80">username Student</th>
                <th width="120">First Name</th>
                <th width="120">Last Name</th>
                <th width="120">nome esame</th>
                <th width="120">grade</th>
            </tr>
           
       <c:forEach items="${userar}" var="uar">
             <tr>
              	<td> <c:out value="${uar.student.username}"/></td>
            	<td> <c:out value="${uar.student.firstName}"/></td>
                <td> <c:out value="${uar.student.lastName}" /></td>
                <td> <c:out value="${uar.attempt.exam.name}" /></td>
            	<td>
            	<form action="addCareerExam" method="post">
            	<input type="text" name="studentUsername" value="${uar.student.username}" hidden="true">
            	<input type="number" name="attemptId" value="${uar.attempt.attemptId}" hidden="true">
            	<input type="number" name="grade">
            		<input type="submit" value="Submit" onsubmit="history.go(-1);">
            	 </form>
            	</td>
             </tr>
       
       </c:forEach>
    
      		
      
</table>