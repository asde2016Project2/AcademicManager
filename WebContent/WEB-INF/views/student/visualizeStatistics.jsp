<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Visualize Statistics</title>

<link href="<c:url value="/resources/core/css/bootstrap.min.css"/>"
	rel="stylesheet">

</head>
<body>

	

<!--  author: Nello -->
	<div class="panel panel-default">
		<div class="panel-body" style="min-height: 100; max-height: 100;">

			<label>Statistics of ${studentName}</label>

			<table class="table table-striped table-hover ">
				<thead>
					<tr>
						<th width="120">Average Weighted Score</th>
						<th width="120">Earned Credits</th>
						<th width="120">Graduation Base Grade</th>

					</tr>
				</thead>
				
					<tbody>
						<tr>
							<td>${averageWeightedScore}</td>
							<td>${earnedCredits}</td>
							<td>${graduationBaseGrade}</td>
													

						</tr>
					</tbody>


				

			</table>

		</div>

	</div>
</body>


</body>
</html>