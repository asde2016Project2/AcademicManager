<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>NEW STUDY PLAN</h2>

<form:form method="post" modelAttribute="studyPlanForm"
	id="studyPlanForm">
	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Name
			Study Plan<span class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<form:input path="name" id="name" required="required"
				cssClass="form-control col-md-7 col-xs-12"></form:input>
		</div>
	</div>
	<br />
	<br />

	<div class="form-group">
		<label for="degreeCourseId" class="control-label col-md-3 col-sm-3 col-xs-12">Degree
			Course</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<form:select class="form-control" path="degreeCourseId" id="degreeCourseId" name="degreeCourseId">
				<c:forEach var="degreeCourseItem" items="${degreeCourseList}">
					<form:option value="${degreeCourseItem.degreeCourseId}"
						label="${degreeCourseItem.name}" />
				</c:forEach>
			</form:select>
		</div>
	</div>

	<br />
	<br />

	<h2>SELECT EXAMS</h2>
	<c:forEach var="exam" items="${examList}">
		<form:checkbox path="examList" value="${exam.id}" label="${exam.name}" />	
		<br>
	</c:forEach>
		
	<br />

	<form:button class="btn btn-primary" type="submit" value="create">Save</form:button>
</form:form>