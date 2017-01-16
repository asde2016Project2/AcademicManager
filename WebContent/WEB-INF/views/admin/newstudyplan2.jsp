<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>NEW STUDY PLAN</h2>

<form:form method="post" modelAttribute="studyPlanForm" id="studyPlanForm">

	<label for="nameStudyPlan">Name Study Plan</label>
	<form:input path="nameStudyPlan" id="nameStudyPlan"></form:input><br><br>
	
	<label for="nameDegreeCourse">Name Degree Course</label>
	<form:select path="nameDegreeCourse" id="nameDegreeCourse">                    
		<form:options items="${degreeCourseList}" />
    </form:select><br><br>
    
    <label for="nameExams">Exams</label>
    <form:checkboxes path="nameExams" id="nameExams" items="${examList}" delimiter="<br>" /><br><br>
    
	<form:button type="submit" value="create">create</form:button>
</form:form>
