<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>

<span>average weighted score after projection: ${avgScore}</span><br>
<span>graduation basic grade after projection: ${gbg}</span><br><br>

<div class="divTable">
    <div class="divTableBody">
        <div class="divTableRow">
            <div class="divTableCell"><strong>Exam</strong></div>
            <div class="divTableCell"><strong>CFU</strong></div>
            <div class="divTableCell"><strong>Grade</strong></div>
        </div>
        <c:forEach var="nameExam" items="${nameExams}" varStatus="status">
            <div class="divTableRow">
                <div class="divTableCell">${nameExam}</div>
                <div class="divTableCell">${cfuExams[status.index]}</div>
                <div class="divTableCell">${gradeExams[status.index]}</div>
            </div>
        </c:forEach>	
    </div>
</div>
<br>
<a href="<c:url value="/student/projection"/>">Make another projection</a>