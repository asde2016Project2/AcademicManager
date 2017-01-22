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
		<c:forEach var="admin" items="${listAdministrators}">
			<div class="divTableRow">
				<div class="divTableCell">${admin.username}</div>
				<div class="divTableCell">${admin.firstName}</div>
				<div class="divTableCell">${admin.lastName}</div>
				<div class="divTableCell">${admin.dateOfBirth}</div>
				<div class="divTableCell">${admin.email}</div>
				<div class="divTableCell">
					<form method="post"><button type="submit" name="accept" value="${admin.username}">accept</button></form>
					<form method="post"><button type="submit" name="refuse" value="${admin.username}">refuse</button></form>
				</div>
			</div>
		</c:forEach>	
	</div>
</div>
