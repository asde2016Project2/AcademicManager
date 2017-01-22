<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>

<span>average weighted score ${avgScore}</span><br>
<span>graduation basic grade ${gbg}</span><br><br>

<form:form method="post" modelAttribute="projectionForm" id="projectionForm">
	<c:forEach var="careerExam" items="${listCareerExam}">
		<c:if test="${careerExam.done eq true}">
			<form:input type="text" path ="nameExams" name="${careerExam.exam.name}" value="${careerExam.exam.name}" readonly="true" />
			<form:input type="text" path ="cfuExams" name="${careerExam.exam.cfu}" value="${careerExam.exam.cfu}" readonly="true" />
			<form:input type="text" path ="gradeExams" name="${careerExam.grade}" value="${careerExam.grade}" readonly="true" /><br>	
		</c:if>
		<c:if test="${careerExam.done eq false}">
			<form:input type="text" path ="nameExams" name="${careerExam.exam.name}" value="${careerExam.exam.name}" readonly="true" />
			<form:input type="text" path ="cfuExams" name="${careerExam.exam.cfu}" value="${careerExam.exam.cfu}" readonly="true" /><br>
			<form:select path="gradeExams" size="1">
				<form:option value="" label="select grade"></form:option>
				<c:forEach var="i" begin="18" end="30">
					<form:option value="${i}" label="${i}"></form:option>
				</c:forEach>
			</form:select><br>	
		</c:if>
	</c:forEach>
	<form:button type="submit" value="projection">projection</form:button>
</form:form>




