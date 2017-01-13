<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Appeal List</title>
        <link href="<c:url value="/resources/core/css/bootstrap.min.css" />"
              rel="stylesheet">
    </head>
    <body>
        <jsp:include page="studentDashboard.jsp" />

        <div class="container">
            <div class="bs-docs-section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="page-header">
                            <h1 id="tables">Registration Appeal list</h1>
                        </div>

                        <div class="bs-component">
                            <c:if test="${!empty listExamSession }">
                                <table class="table table-striped table-hover ">
                                    <thead>
                                        <tr>
                                            <th width="80">ID</th>
                                            <th width="120">Degree Course</th>
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
                                                <td><a href="${pageContext.request.contextPath}/registrationAppeals/examSession/${examSession.sessionId}">View</a></td>

                                            </tr>

                                        </c:forEach>
                                    </tbody>

                                    <tbody>
                                    <div class="pagination-container">
                                        <c:forEach var="page" begin="1" end="${numberOfPages}">
                                            <c:url var="url" value="/stuent/registrationAppeals/${page}" />
                                            <span id="page-number"> <a href="${url}"> ${page}
                                                </a>
                                            </span>
                                        </c:forEach>
                                    </div>
                                    </tbody>

                                </table>
                            </c:if>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </body>
</html>