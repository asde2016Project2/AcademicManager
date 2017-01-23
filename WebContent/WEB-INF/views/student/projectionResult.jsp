<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>

<span>average weighted score after projection: ${avgScore}</span><br>
<span>graduation basic grade after projection: ${gbg}</span><br><br>

<div class="row">
	<table class="table table-bordered">
		<thead>
			<tr>
	            <th>Exam</th>
	            <th>CFU</th>
	            <th>Grade</th>
	        <tr>
        </thead>
        <tbody>
        	<c:forEach var="nameExam" items="${nameExams}" varStatus="status">
	            <tr>
	                <td>${nameExam}</td>
	                <td>${cfuExams[status.index]}</td>
	                <td>${gradeExams[status.index]}</td>
	            </tr>
        	</c:forEach>	
        </tbody>
    </table>
</div>
	        
<br>
<a href="<c:url value="/student/projection"/>" class="btn btn-primary">Make another projection</a>