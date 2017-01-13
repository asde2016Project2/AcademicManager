<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Exam Information</title>
<link rel="stylesheet" type="text/css"
	href="/resources/script/jquery-ui-1.11.4/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/resources/style/style.css">
<script
	src="/resources/script/jquery-ui-1.11.4/external/jquery/jquery.js"></script>
<script src="/resources/script/jquery-ui-1.11.4/jquery-ui.js"></script>
<link href="<c:url value="/resources/core/css/bootstrap.min.css"/>"
	rel="stylesheet">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-8">
				<div class="well bs-component">

					<h1>Create Exam</h1>
					<c:url var="addAction" value="/exams/add" />
					<form:form method="POST" action="${addAction}"
						commandName="examForm">
						<fieldset>
							<legend>Add new Exam Information</legend>
							<div class="form-group">
								<label class="col-lg-4 control-label">Exam Name</label>
								<div class="col-lg-8">
									<form:input type="text" path="name" placeholder="name" />
									<div class="has-error" style="color: #ff0700">
										<form:errors path="name" />
									</div>
								</div>
							</div>


							<div class="form-group">
								<label class="col-lg-4 control-label">CFU</label>
								<div class="col-lg-8">
									<form:input type="text" path="cfu" placeholder="cfu" />
									<div class="has-error" style="color: #ff0700">
										<form:errors path="cfu" />
									</div>
								</div>
							</div>



							<div class="form-group">
								<label class="col-lg-4 control-label">Code</label>
								<div class="col-lg-8">
									<form:input type="text" path="code" placeholder="code" />
									<div class="has-error" style="color: #ff0700">
										<form:errors path="code" />
									</div>
								</div>
							</div>



							



							<div class="form-group">
								<div class="col-lg-8 col-lg-offset-6">
									<button type="submit" class="btn btn-primary">Submit</button>
									<a class="btn btn-default" onclick="history.back()">Back</a>
								</div>
							</div>

						</fieldset>

					</form:form>

					<div>

						<fieldset>
							<legend>List of Exams</legend>
							<h1>Exam List</h1>
							<c:if test="${!empty listExams}">
								<table class="tg" border="0">
									<tr>
										<th width="80">ID</th>
										<th width="120">Name</th>
										<th width="120">CFU</th>
										<th width="120">CODE</th>
										<th width="60">Edit</th>
										<th width="60">Delete</th>
									</tr>
									<c:forEach items="${listExams}" var="examForm">
										<tr>
											<td>${exam.id}</td>
											<td>${exam.name}</td>
											<td>${exam.cfu}</td>
											<td>${exam.code}</td>
											<td><a href="<c:url value='/edit/${exam.id}'/>">Edit</a></td>
											<td><a href="<c:url value='/remove/${exam.id}'/>">Delete</a></td>
										</tr>

									</c:forEach>
								</table>
							</c:if>

						</fieldset>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>