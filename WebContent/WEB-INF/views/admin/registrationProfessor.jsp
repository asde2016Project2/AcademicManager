<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<table class="table table-bordered">
		<thead>
			<tr>
					<th>Username</th>
					<th>First name</th>
					<th>Last name</th>
					<th>Date of birth</th>
					<th>Email</th>
					<th>Operations</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="professor" items="${listProfessors}">
				<tr>
					<td>${professor.username}</td>
					<td>${professor.firstName}</td>
					<td>${professor.lastName}</td>
					<td>${professor.dateOfBirth}</td>
					<td>${professor.email}</td>
					<td>
						<span class="inline"><form method="post"><button class="btn btn-primary" type="submit" name="accept" value="${professor.username}">accept</button></form></span>
						<span class="inline"><form method="post"><button class="btn btn-danger" type="submit" name="refuse" value="${professor.username}">refuse</button></form></span>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
