<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
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
                <h2>List Exam Reservation</h2>           
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <p class="text-muted font-13 m-b-30">
                    
                </p>
	<table class="table datatable table-hover table-centered table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th >ID</th>
				<th>Exam Name</th>
				<th>Date Hour</th>
				<th>Building Class Room</th>
				<th>Registration Starting Date</th>
				<th>Registration Ending Date</th>
				<th>Professor</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="attempt" items="${listOfExamReservation}"
				varStatus="status">

				<tr>
					<td>${status.index+1 }</td>
					<td>${attempt.exam.name}</td>
					<td>${attempt.examDate}</td>
					<td>${attempt.classroom}</td>
					<td>${attempt.startRegistrationDate}</td>
					<td>${attempt.endRegistrationDate}</td>
					<td><c:out value="${attempt.professor.firstName}" /> <c:out
							value="${attempt.professor.lastName}" /></td>
							<td><a class="btn btn-default"
						href="<c:url  value='/student/list/ExamReserve/${attempt.attemptId}'/>">Book</a></td>
			
					
				</tr>

			</c:forEach>
		</tbody>
	</table>
</div>
</div>
</div>
</div>

