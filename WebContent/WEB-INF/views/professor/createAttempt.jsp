<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Create Attempt</h1>

<form method="post" action="createAttempt">
<label for="dates">Exam Date </label>
<input id="examDate" name="examDate" type="date" value="01-01-2017"/>

<label for="dates">Open Registration </label>
<input id="startingDate" name="startingDate" type="date" value="01-01-2017"/>
<br>
<label for="dates">Close Registration </label>
<input id="endingDate" name="endingDate" type="date" value="01-01-2017"/>

<br>
<label>Class: </label>
<select name="classRoom">
	<option>Choose</option>
	<option>mt1</option>
	<option>mt2</option>
	<option>mt3</option>
	<option>mt4</option>
	<option>mt5</option>
	<option>mt6</option>
	<option>mt8</option>
  	</select>
<label>Exam Session </label>
<select name="examSession">
	<option>Choose</option>
	<c:forEach items="${examSessions}" var="examSes">
    	<option><c:out value="${examSes.examSessionId}"></c:out>---<c:out value="${examSes.startingDate}"/>/<c:out value="${examSes.endingDate}"/></option>
  	</c:forEach>
  	</select>
  	
<br>
<label>Exam: </label>
<select name="exam">
    <c:forEach items="${exams}" var="exam">
    <option> <c:out value="${exam.name}"/></option>
    </c:forEach>
  </select>
  
  <input type="submit" value="Submit">
</form>
<br><br>
<c:if test="${not empty error}">
   Error: ${error}
</c:if>