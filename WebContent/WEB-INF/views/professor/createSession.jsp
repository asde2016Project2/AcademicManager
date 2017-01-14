<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Create new Session</h1>

<form method="post" action="createSession">
<label for="dates">Exam Session's starting: </label>
<input id="startingDate" name="startingDate" type="date" value="01-01-2017"/>
<br>
<label for="dates">Exam Session's ending: </label>
<input id="endingDate" name="endingDate" type="date" value="01-01-2017"/>
<input type="submit" value="Submit">
<br>
<label>DegreeCOURSE: </label>
<select name="degreeCourse">
    <option value="Choose">Choose</option>
    <option value="Maths">Maths</option>
    <option value="Physics">Physics</option>
    <option value="Computer">Computer</option>
  </select>
<br>
<label>Academic Year: </label>
<select name="academicYear">
    <option value="Choose">Choose</option>
    <option value="2016/2017">2016/2017</option>
    <option value="2017/2018">2017/2018</option>
  </select>
</form>
<!-- <form method="post" action="createSession" commandName="dateForm">
  Starting data(dd-mm-yy):<br> 
  <input type="text" name="startingDate">
  <br>
  Ending date(dd-mm-yy):<br>
  <input type="text" name="endingDate">
  <br> Degree Course<br>
  <input type="text" name="degreeCourse">
  <br> Academic Year<br>
  <input type="text" name="academicYear">
  <input type="submit" value="Submit">
</form>
-->