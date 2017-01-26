<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row">

    <div class="col-md-12 col-sm-12 col-xs-12 mycontent">
        <div class="x_panel">
            <div class="x_title">
                <h1>${pageTitle}</h1>           
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <p class="text-muted font-13 m-b-30">
                    Lorem ipsum
                </p>

                <table class="table datatable table-hover table-centered table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">

                    <thead>
                        <tr>
                            <th width="120">Start Registration</th>
                            <th width="120">End Registration</th>
                            <th width="80">Exam Date</th>
                            <th width="120">Name Exam</th>
                            <th width="120">Room</th>
                            <th width="120">Professor</th>
                        </tr>
                    </thead>

                    <thead>
                        <c:forEach items="${attemptList}" var="attempt">
                            <tr>
                                <td><c:out value="${attempt.examSession.startingDataString}" /></td>
                                <td><c:out value="${attempt.examSession.endingDataString}" /></td>
                                <td><c:out value="${attempt.examDate}"/></td>
                                <td><c:out value="${attempt.exam.name}"/></td>
                                <td><c:out value="${attempt.classroom}"/></td>
                                <td><c:out value="${attempt.professor.username}"/></td>
                            </tr>

                        </c:forEach>
                    </thead>
                </table>

            </div>
        </div>
    </div>
</div>