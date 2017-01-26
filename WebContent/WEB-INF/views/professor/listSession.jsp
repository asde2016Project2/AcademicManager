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
                
                <table class="table datatable table-hover table-centered table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th width="80">ID</th>
                            <th width="120">DEGREE COURSE</th>
                            <th width="120">ACADEMIC YEAR</th>
                            <th width="120">STARTING DATE</th>
                            <th width="120">ENDING DATE</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lista}" var="examSession">
                            <tr>
                                <td><c:out value="${examSession.examSessionId}"/></td>
                                <td><c:out value="${examSession.degreeCourse.name}" /></td>
                                <td><c:out value="${examSession.academicYear}" /></td>
                                <td><c:out value="${examSession.startingDataString}"/></td>
                                <td><c:out value="${examSession.endingDataString}"/></td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>                
    </div>
</div>