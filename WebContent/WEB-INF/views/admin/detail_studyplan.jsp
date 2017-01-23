<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<table class="table table-bordered">
		<thead>
			<tr>
					<th>Code</th>
					<th>Name</th>
					<th>CFU</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="exam" items="${exams}">
				<tr>
					<td>${exam.code}</td>
					<td>${exam.name}</td>
					<td>${exam.cfu}</td>
				</tr>
			</c:forEach>	
		</tbody>
	</table>
</div>
			
		