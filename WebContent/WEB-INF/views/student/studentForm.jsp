<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="<c:url value="/resources/core/css/bootstrap.min.css"/>"
	rel="stylesheet">
</head>
<body>
	<div class="row">
		<div class="col-lg-6">
			<div class="well bs-component">
				<form:form method="post" modelAttribute="student"
					class="form-horizontal">
					<fieldset>
						<legend>Add new student</legend>

						<%--Student first name--%>
						<div class="form-group">
							<label class="col-lg-4 control-label">First name</label>
							<div class="col-lg-8">
								<div class="radio">
									<form:input type="text" path="name" placeholder="First Name" />
									<div class="has-error" style="color: #ff0700">
										<form:errors path="name" />
									</div>
								</div>
							</div>
						</div>

						<%-- Student surname --%>
						<div class="form-group">
							<label class="col-lg-4 control-label">Surnname</label>
							<div class="col-lg-8">
								<form:input type="text" path="surname" placeholder="Surnname" />
								<div class="has-error" style="color: #ff0700">
									<form:errors path="surname" />
								</div>
							</div>
						</div>

						<%-- Student gender--%>
						<div class="form-group">
							<label class="col-lg-4 control-label">Gender</label>
							<div class="col-lg-8">
								<form:input type="date" path="gender" placeholder="F/M" />
								<div class="has-error" style="color: #ff0700">
									<form:errors path="gender" />
								</div>
							</div>
						</div>

						<%--CNP--%>
						<div class="form-group">
							<label class="col-lg-4 control-label">CNP</label>
							<div class="col-lg-8">
								<form:input type="text" path="cnp" placeholder="1910206025894" />
								<div class="has-error" style="color: #ff0700">
									<form:errors path="cnp" />
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-6 col-lg-offset-6">
								<button type="submit" class="btn btn-primary">Submit</button>
								<a class="btn btn-default" onclick="history.back()">Back</a>
							</div>
						</div>

					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>