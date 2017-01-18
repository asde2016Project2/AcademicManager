<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Visualize Career</title>

<link href="<c:url value="/resources/core/css/bootstrap.min.css"/>"
	rel="stylesheet">

</head>
<body>
<!--  author: Nello -->
	<div class="panel panel-default">
		<div class="panel-body" style="min-height: 100; max-height: 100;">

			<label>Career of ${studentName}</label>

			<table class="table table-striped table-hover ">
				<thead>
					<tr>
						<th width="80">ID</th> 
						<th width="120">Name</th>
						<th width="120">CFU</th>
						<th width="120">Grade</th>
						<th width="120">Date</th>

					</tr>
				</thead>
				<c:forEach var="cexam" items="${listCareerExams}" varStatus="status">
					<tbody>
						<tr>
							<td>${status.index+1}</td>
							<td>${cexam.exam.name}</td>
							<td>${cexam.exam.cfu}</td>
							<td>${cexam.grade}</td>
							<td>${cexam.dateOfExam}</td>
							
						</tr>
					</tbody>


				</c:forEach>

			</table>
			
		</div>

	</div>
</body>
</html>