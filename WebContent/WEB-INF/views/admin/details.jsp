<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="everything">
	<div id="header">
		<div id="button-menu">
			<img src="<c:url value="/resources/images/menu.png"/>" />
		</div>
	</div>
	<div id="content">
		<div id="label-study-plans">Study Plans</div>
		<div id="list-study-plans">
			<div id="operation">
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
			</div>
		</div>
		<div id="menu">
			<ul>
				<li>
					<img src="<c:url value="/resources/images/admin/studyplans.png"/>" />
					<a href="studyPlans">Study Plans</a>
				</li>
				<li>
					<img src="<c:url value="/resources/images/admin/registrations.png"/>" />
					<a href="registrations">Registrations</a>
				</li>
				<li>
					<img src="<c:url value="/resources/images/admin/logout.png"/>" />
					<a href="logout">Logout</a>
				</li>
			</ul>
		</div>
	</div>
</div>