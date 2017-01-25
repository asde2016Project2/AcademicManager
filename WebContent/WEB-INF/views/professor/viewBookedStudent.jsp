<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
                <h2>Approved Student Attempt</h2>           
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <p class="text-muted font-13 m-b-30">
                </p>
	<table class="table datatable table-hover table-centered table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
	
		<thead>
			<tr>
				<th>ID</th>
				<th>Exam</th>
				<th>Date</th>
				<th>Student Full Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="userAttemptReg" items="${listStudentExamSignup}"
				varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${userAttemptReg.attempt.exam.name}</td>
					<td>${userAttemptReg.attempt.examDate}</td>
					<td><c:out value="${userAttemptReg.student.firstName}" /> <c:out
							value="${userAttemptReg.student.lastName}" /></td>
					            
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>
</div>

</div>

