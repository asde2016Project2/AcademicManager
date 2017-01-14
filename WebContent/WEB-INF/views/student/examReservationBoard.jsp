<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Student View</title>

<link href="<c:url value="/resources/core/css/bootstrap.min.css"/>"
	rel="stylesheet">

</head>
<body>


<div class="container">
		<div class="bs-docs-section">
			<div class="row">
				<div class="col-lg-12">
					<div class="page-header">
						<h1 id="tables">Exam Reservation Board list</h1>
					</div>

					<div class="bs-component">

							<div class="bs-component">
								<table class="table table-striped table-hover ">
									<thead>
										<tr>
											<th width="80">ID</th>
											<th width="120">Date Hour</th>
											<th width="120">Building Class Room</th>
											<th width="120">Registration Starting Date</th>
											<th width="120">Registration Ending Date</th>
											<th width="120">Professor</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="attempt" items="${listOfExamReservation}"
											varStatus="status">

											<tr>
												<td>${status.index+1 }</td>
												<td>${attempt.examDate}</td>
												<td>${attempt.classroom}</td>
												<td>${attempt.startRegistrationDate}</td>
												<td>${attempt.endRegistrationDate}</td>
												<td>${attempt.professor.firstName}</td>
												<td><a href="${pageContext.request.contextPath}/examReservationBoard/attempt/${attempt.attemptId}">View</a></td>
											</tr>

										</c:forEach>
									</tbody>

									<tbody>
										<div class="pagination-container">
											<c:forEach var="page" begin="1" end="${numberOfPages}">
												<c:url var="url" value="/admin/students/${page}" />
												<span id="page-number"> <a href="${url}"> ${page}
												</a>
												</span>
											</c:forEach>
										</div>
									</tbody>

								</table>
								<button>Cancel</button>
								<button>Book Appeal</button>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>