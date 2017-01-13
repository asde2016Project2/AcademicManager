<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Exam Create</title>

<link href="<c:url value="/resources/core/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/script/jquery-ui-1.11.4"/>"
	rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function() {
		$('#examTable').DataTable();
	});
</script>
</head>
<body>
	<jsp:include page="adminDashboard.jsp" />

	<div class="container">
		<div class="bs-docs-section">
			<div class="row">
				<div class="col-lg-12">
					<div class="page-header">
						<h1 id="tables">Exam list</h1>
					</div>

					<div class="bs-component">
						<div class="divTable">
							<table class="table table-striped table-hover ">
								<thead>
									<tr>
										<th width="80">ID</th>
										<th width="120">Name</th>
										<th width="120">CFU</th>
										<th width="120">CODE</th>

										<th colspan="3" class="actions">Actions</th>
									</tr>
								</thead>
								<c:forEach var="exam" items="${listExams}" varStatus="status">
									<tbody>
										<tr>
											<td>${status.index+1}</td>
											<td>${exam.name}</td>
											<td>${exam.cfu}</td>
											<td>${exam.code}</td>
											<td><a
												href="${pageContext.request.contextPath}/exams/edit/${exam.id}">Edit Exam</a></td>
											<td><a href="<c:url value='/exams/edit/${exam.id}'/>">Edit</a></td>
											<td><a href="<c:url value='/exams/delete/${exam.id}'/>">Delete</a></td>
										</tr>
									</tbody>
									<tbody>
										<div class="pagination-container">
											<c:forEach var="page" begin="1" end="${numberOfPages}">
												<c:url var="url" value="/admin/exams/${page}" />
												<span id="page-number"> <a href="${url}"> ${page}
												</a>
												</span>
											</c:forEach>
										</div>
										<tr>
											<td colspan="6"><a
												href="${pageContext.request.contextPath}/admin/examForm">Add
													Exam</a></td>
										</tr>

									</tbody>

								</c:forEach>

							</table>
							<label>This is it</label>
						</div>

					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>