<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="divTable">
	<div class="divTableBody">
		<div class="divTableRow">
				<div class="divTableCell"><strong>Code</strong></div>
				<div class="divTableCell"><strong>Name</strong></div>
				<div class="divTableCell"><strong>CFU</strong></div>
		</div>
		<c:forEach var="exam" items="${exams}">
			<div class="divTableRow">
				<div class="divTableCell">${exam.code}</div>
				<div class="divTableCell">${exam.name}</div>
				<div class="divTableCell">${exam.cfu}</div>
			</div>
		</c:forEach>
	</div>
</div>
			
		