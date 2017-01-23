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
			<c:forEach var="student" items="${listStudents}">
				<tr>
					<td>${student.username}</td>
					<td>${student.firstName}</td>
					<td>${student.lastName}</td>
					<td>${student.dateOfBirth}</td>
					<td>${student.email}</td>
					<td>
						<span class="inline"><form method="post"><button class="btn btn-primary" type="submit" name="accept" value="${student.username}">accept</button></form></span>
						<span class="inline"><form method="post"><button class="btn btn-danger" type="submit" name="refuse" value="${student.username}">refuse</button></form></span>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>