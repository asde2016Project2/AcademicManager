<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exam Form</title>
<link href="<c:url value="/resources/core/css/bootstrap.min.css"/>"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="adminDashboard.jsp" />
	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h1 id="forms">New Exam</h1>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-6">
				<div class="well bs-component">

					<form:form method="POST" modelAttribute="exam" class="form-horizontal">
					
						<fieldset>
							<legend>Add new Exam Information</legend>

							<%--Exam name--%>
							<div class="form-group row">
								<label class="col-xs-2 col-form-label">Exam name</label>
								<div class="col-xs-10">
									<div class="radio">
										<form:input class="form-control" type="text" path="name"
											placeholder="Exam Name" />
										
									</div>
								</div>
							</div>


							<div class="form-group row">
								<label for="cfu" class="col-xs-2 col-form-label">CFU</label>
								<div class="col-xs-8">
									<form:input class="form-control" type="number" path="cfu"
										placeholder="cfu" id="cfu" />
								</div>
							</div>

							<div class="form-group row">
								<label for="code" class="col-xs-2 col-form-label">Code</label>
								<div class="col-xs-8">
									<form:input class="form-control" type="number" path="code"
										placeholder="code" id="code" />

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

				</div>
			</div>
		</div>
	</div>
	
</body>
</html>