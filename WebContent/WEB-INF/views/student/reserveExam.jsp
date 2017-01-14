<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <link href="<c:url value="/resources/code/css/bootstrap.min.css"/>"
              rel="stylesheet">


    </head>
    <body>
        <jsp:include page="studentDashboard.jsp" />
        <h1>Reservation for Examinations</h1>

        <form action=""></form>
        <label>Name:</label>
        <label></label>
        <label>Course</label>
        <label></label>
        <label>NumberofStudents</label>
        <label></label>
        <label>Exam Type</label>
        <label></label>
        <label>Test Method</label>
        <label></label>


        <div class="panel panel-default">
            <div class="panel-body">

                <fieldset>
                    <legend>Exam Booklet</legend>
                    <h1>Exam Booklet</h1>
                    <c:if test="${!empty listResrveExams}">
                        <div class="bs-component">
                            <table class="table table-striped table-hover ">
                                <thead>
                                    <tr>
                                        <th width="80">ID</th>
                                        <th width="120">Day</th>
                                        <th width="120">ClassRoom</th>
                                        <th width="120">Professor</th>
                                        <th width="60">Student</th>
                                        <th width="60">Reserve</th>
                                    </tr>
                                </thead>
                                <c:forEach var="userAttemptRegistration" items="${listResrveExams}"
                                           varStatus="status">

                                    <tr>
                                        <td>${status.index+1 }</td>
                                        <td>${userAttemptRegistration.attempt.examDate}</td>
                                        <td>${userAttemptRegistration.attempt.classroom}</td>
                                        <td>${userAttemptRegistration.attempt.professor.firstName}</td>
                                        <td>${userAttemptRegistration.student.firstName}</td>
                                    </tr>

                                </c:forEach>
                            </table>
                        </div>
                    </c:if>

                </fieldset>
            </div>
        </div>

    </body>
</html>