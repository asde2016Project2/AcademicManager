<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default">
    <div class="bs-docs-section">
        <div class="row">
            <div class="col-lg-6">
                <div class="page-header">
                    <h1 id="tables">Exam Registration Appeals</h1>
                </div>

                <div class="bs-component">
                    <div class="divTable">
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
                                            <td><a href="<c:url value='/student/registrationAppeals/examSession/${examSession.sessionId}'/>">View</a></td>
                                        </tr>

                                    </c:forEach>
                                </tbody>

                                <tbody>
                                    <!-- div class="pagination-container">
                                    <c:forEach var="page" begin="1" end="${numberOfPages}">
                                        <c:url var="url" value="registrationAppeals/${page}" />
                                        <span id="page-number"> <a href="${url}"> ${page}
                                            </a>
                                        </span>
                                    </c:forEach>
                                </div>
                                </tbody -->

                            </table>
                        </c:if>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
