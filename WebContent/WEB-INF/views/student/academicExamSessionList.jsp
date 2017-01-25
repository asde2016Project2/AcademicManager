<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="page-title">
    <div class="title_left">
        <h3>${pageTitle}</h3>
    </div>

    <div class="title_right">      
    </div>
</div>

<div class="row">

    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Academic ExamSession List</h2>           
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <p class="text-muted font-13 m-b-30">
                   
                </p>
	<table class="table datatable table-hover table-centered table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
	
		<thead>
			<tr>
				<th width="80">ID</th>
				<th width="220">Degree Course</th>
				<th width="120">Appeal</th>
				<th width="120">Entry</th>
				<th width="120">Academic Year</th>
				<th width="60">Edit</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="examSession" items="${listExamSession}"
				varStatus="status">

				<tr>
					<td>${status.index+1 }</td>
					<td>${examSession.degreeCourse.name}</td>
					<td>${examSession.startingDate}</td>
					<td>${examSession.endingDate}</td>
					<td>${examSession.academicYear}</td>
					<td><a class="btn btn-default"
						href="<c:url  value='/student/academicExamSessionList/examSession/${examSession.examSessionId}'/>">View</a></td>
				</tr>

			</c:forEach>
		</tbody>

	</table>

</div>

</div>


</div>


</div>


