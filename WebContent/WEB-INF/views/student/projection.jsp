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
		
			<div class="row">
				<div class="form-group">
					<div class="col-md-4">
						<form:input type="text" path ="nameExams" name="${careerExam.exam.name}" value="${careerExam.exam.name}" readonly="true"
						cssClass="form-control col-md-4"/>
					</div>
					<div class="col-md-4">
						<form:input type="text" path ="cfuExams" name="${careerExam.exam.cfu}" value="${careerExam.exam.cfu}" readonly="true"
						cssClass="form-control col-md-4"/>
					</div>
					<div class="col-md-4">
						<form:input type="text" path ="gradeExams" name="${careerExam.grade}" value="${careerExam.grade}" readonly="true"
						cssClass="form-control col-md-4"/>
					</div>
				</div>
			</div><br>
		</c:if>
		
		<c:if test="${careerExam.done eq false}">
			<div class="row">
				<div class="form-group">
					<div class="col-md-4">
						<form:input type="text" path ="nameExams" name="${careerExam.exam.name}" value="${careerExam.exam.name}" readonly="true"
						cssClass="form-control col-md-4"/>
					</div>
				
					<div class="col-md-4">
						<form:input type="text" path ="cfuExams" name="${careerExam.exam.cfu}" value="${careerExam.exam.cfu}" readonly="true"
						cssClass="form-control col-md-4"/>
					</div>
				
					<div class="col-md-4">
						<form:select path="gradeExams" size="1" cssClass="form-control col-md-4">
							<form:option value="" label="select grade"></form:option>
							<c:forEach var="i" begin="18" end="30">
								<form:option value="${i}" label="${i}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
			</div><br>
		</c:if>
	</c:forEach>
	<form:button class="btn btn-primary" type="submit" value="projection">projection</form:button>
</form:form>




