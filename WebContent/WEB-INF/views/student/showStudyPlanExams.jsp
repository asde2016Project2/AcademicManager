<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Visualize Study Plan</title>

<link href="<c:url value="/resources/core/css/bootstrap.min.css"/>"
	rel="stylesheet">

</head>
<body>
<!--  author: Nello -->
	<div class="panel panel-default">
		<div class="panel-body" style="min-height: 100; max-height: 100;">

			<label> ${studyPlanName} </label>

			<table class="table table-striped table-hover ">
				<thead>
					<tr>
						<th width="80">ID</th> 
						<th width="120">Name</th>
						<th width="120">CFU</th>
						<th width="120">CODE</th>

					</tr>
				</thead>
				<c:forEach var="spexam" items="${listStudyPlanExams}" varStatus="status">
					<tbody>
						<tr>
							<td>${status.index+1}</td>
							<td>${spexam.exam.name}</td>
							<td>${spexam.exam.cfu}</td>
							<td>${spexam.exam.code}</td>
							
						</tr>
					</tbody>


				</c:forEach>

			</table>
			
		</div>

	</div>
</body>
</html>