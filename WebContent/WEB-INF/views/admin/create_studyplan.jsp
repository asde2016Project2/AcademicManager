<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>NEW STUDY PLAN</h2>

<form:form method="post" modelAttribute="studyPlanForm" id="studyPlanForm">

    <label for="name">Name Study Plan</label>
    <form:input path="name" id="name"></form:input>
         
        <br/>
        <br/>

        <label for="degreeCourseId">Degree Course</label>
    <form:select path="degreeCourseId" id="degreeCourseId" name="degreeCourseId">   
        <c:forEach var="degreeCourseItem" items="${degreeCourseList}">
            <form:option value="${degreeCourseItem.degreeCourseId}" label="${degreeCourseItem.name}" />
        </c:forEach>        
    </form:select>
        
        <br/>
        <br/>
	<!-- 
	<c:forEach items="${roleList}" var="role">
            <tr>
                <td><form:checkbox path="roles" value="${role}" label="${role.id}" /></td>
                <td><c:out value="${role.name}" /></td>
            </tr>
        </c:forEach> -->
    <p>Exam List</p>
        <c:forEach var="exam" items="${examList}">
            <form:checkbox path="examList" value="${exam.id}" label="${exam.name}" /><br>
        </c:forEach>		 
        <br/>
        <br/>
        
    <form:button type="submit" value="create">Save</form:button>
        
</form:form>

