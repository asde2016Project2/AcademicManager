<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Create Attempt</h1>

<form method="post" action="createAttempt">
<label for="dates" class="control-label col-md-3 col-sm-3 col-xs-12">Exam Date </label>
<input class="form-control" id="examDate" name="examDate" type="date" value="01-01-2017"/>
<br>
<label for="dates" class="control-label col-md-3 col-sm-3 col-xs-12">Open Registration </label>
<input class="form-control" id="startingDate" name="startingDate" type="date" value="01-01-2017"/>
<br>
<label for="dates" class="control-label col-md-3 col-sm-3 col-xs-12">Close Registration </label>
<input class="form-control" id="endingDate" name="endingDate" type="date" value="01-01-2017"/>

<br>
<label class="control-label col-md-3 col-sm-3 col-xs-12">Class: </label>
<select name="classRoom" class="form-control">
	<option>mt1</option>
	<option>mt2</option>
	<option>mt3</option>
	<option>mt4</option>
	<option>mt5</option>
	<option>mt6</option>
	<option>mt8</option>
  	</select>
<br>
<label class="control-label col-md-3 col-sm-3 col-xs-12">Exam Session </label>
<select name="examSession" class="form-control">
	<option>Choose</option>
	<c:forEach items="${examSessions}" var="examSes">
 <!--  	<option><c:out value="${examSes.examSessionId}"></c:out>---<c:out value="${examSes.startingDataString}"/>/<c:out value="${examSes.endingDataString}"/></option>
 -->   	<option value="${examSes.examSessionId}" label="${examSes.startingDataString} --- ${examSes.endingDataString}"></option>
  	</c:forEach>
  	</select>

<br>
<label class="control-label col-md-3 col-sm-3 col-xs-12">Exam: </label>
<select name="exam" class="form-control">
    <c:forEach items="${exams}" var="exam">
    <option> <c:out value="${exam.name}"/></option>
    </c:forEach>
  </select>
  <br>
  <input class="btn btn-primary" type="submit" value="Submit">
</form>
<br><br>
<c:if test="${not empty error}">
    ${error}
</c:if>