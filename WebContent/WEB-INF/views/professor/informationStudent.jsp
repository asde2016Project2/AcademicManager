<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Session: </h1>

<form action="informationStudent" method="post">
<label class="control-label col-md-3 col-sm-3 col-xs-12"> Username:</label> <input type="username" name="username"class="form-control"><br>
  <input type="submit" value="Submit">
</form>

<ul>
  <li>First Name: <c:out value="${student.firstName}" /></li>
  <li>Last Name: <c:out value="${student.lastName}" /></li>
  <li>Username: <c:out value="${student.username}" /></li>
  <li>Email: <c:out value="${student.email}" /></li>
</ul>
 <table class="table table-bordered">
            <tr>
                <th width="80">Exam</th>
                <th width="120">CFU</th>
                <th width="120">Done</th>
                <th width="120">Grade</th>
            </tr>
       <c:forEach items="${infoStudent}" var="infoStud">
            <tr>
            	<td><c:out value="${infoStud.exam.name}" /></td>
            	<td><c:out value="${infoStud.exam.cfu}"/></td>
                <td><c:out value="${infoStud.done}" /></td>
                <td><c:out value="${infoStud.grade}"/></td>
            </tr>
            
       </c:forEach>
</table>