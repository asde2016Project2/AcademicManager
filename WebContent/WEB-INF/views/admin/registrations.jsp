<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="divTable">
	<div class="divTableBody">
		<div class="divTableRow">
			<div class="divTableCell"><strong>Username</strong></div>
			<div class="divTableCell"><strong>First name/Last name</strong></div>
			<div class="divTableCell"><strong>Operations</strong></div>
		</div>
		<c:forEach var="student" items="${listStudents}">
			<div class="divTableRow">
				<div class="divTableCell">${student.username}</div>
				<div class="divTableCell">${student.firstName} ${student.lastName}</div>
				<div class="divTableCell">
					<form method="post"><button type="submit" name="accept" value="${student.username}">accept</button></form>
					<form method="post"><button type="submit" name="refuse" value="${student.username}">refuse</button></form>
				</div>
			</div>
		</c:forEach>	
	</div>
</div>
