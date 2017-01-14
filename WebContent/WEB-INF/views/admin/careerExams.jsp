<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>CareerExam Create</title>

<link href="<c:url value="/resources/core/css/bootstrap.min.css"/>"
	rel="stylesheet">

</head>
<body>

	<div class="panel panel-default">
		<div class="bs-docs-section">
			<div class="row">
				<div class="col-lg-6">
					<div class="page-header">
						<h1 id="tables">Exam list</h1>
					</div>

					<div class="bs-component">
						<div class="divTable">

							<c:if test="${!empty listCareerExams}">
								<table class="table table-striped table-hover ">
									<thead>
										<tr>
											<th width="80">ID</th>
											<th width="120">Done</th>
											<th width="120">Grade</th>
											<th width="120">Mandatory</th>
											<th width="60">Edit</th>
											<th width="60">Delete</th>
										</tr>
									</thead>
									<c:forEach var="careerExam" items="${listCareerExams}"
										varStatus="status">

										<tr>
											<td>${status.index+1 }</td>
											<td>${careerExam.done}</td>
											<td>${careerExam.grade}</td>
											<td>${careerExam.mandatory}</td>
											<td><a
												href="<c:url value='/careerExams/edit/${careerExam.careerExamId}'/>">Edit</a></td>
											<td><a
												href="<c:url value='/careerExams/delete/${careerExam.careerExamId}'/>">Delete</a></td>
										</tr>

									</c:forEach>
								</table>
							</c:if>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>