<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="divTable">
	<div class="divTableBody">
		<div class="divTableRow">
			<div class="divTableCell"><strong>Username</strong></div>
			<div class="divTableCell"><strong>First name</strong></div>
			<div class="divTableCell"><strong>Last name</strong></div>
			<div class="divTableCell"><strong>Date of birth</strong></div>
			<div class="divTableCell"><strong>Email</strong></div>
			<div class="divTableCell"><strong>Operations</strong></div>
		</div>
		<c:forEach var="professor" items="${listProfessors}">
			<div class="divTableRow">
				<div class="divTableCell">${professor.username}</div>
				<div class="divTableCell">${professor.firstName}</div>
				<div class="divTableCell">${professor.lastName}</div>
				<div class="divTableCell">${professor.dateOfBirth}</div>
				<div class="divTableCell">${professor.email}</div>
				<div class="divTableCell">
					<form method="post"><button type="submit" name="accept" value="${professor.username}">accept</button></form>
					<form method="post"><button type="submit" name="refuse" value="${professor.username}">refuse</button></form>
				</div>
			</div>
		</c:forEach>	
	</div>
</div>
