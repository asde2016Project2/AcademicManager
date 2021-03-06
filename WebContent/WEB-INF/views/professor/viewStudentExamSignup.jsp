<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
                <h2>Approve Attempt Student</h2>           
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
					             <td>
                                    <span class="inline">
                                    <form method="post">
                                  <input id="txtAccepts" type="hidden" name="userAtRegId"
								value="${userAttemptReg.userAtRegId}" />
							<button class="btn btn-default" id="btnAccepts" type="submit"
								>Accept</button>
                                    </form>
                                    </span>
                                    <span class="inline">
                                    <form method="post">
                                    <button class="btn btn-danger" type="submit" name="refuse" value="${userAttemptReg.userAtRegId}">refuse</button>
                                    </form>
                                    </span>
                                </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>
</div>

</div>

