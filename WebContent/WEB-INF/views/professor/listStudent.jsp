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


                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th width="80">USERNAME</th>
                            <th width="120">FIRST NAME</th>
                            <th width="120">SURNAME</th>
                            <th width="120">DEGREE COURSE</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaStudenti}" var="student">
                            <tr>
                                <td><c:out value="${student.username}" /></td>
                                <td><c:out value="${student.firstName}"/></td>
                                <td><c:out value="${student.lastName}" /></td>
                                <td><c:out value="${student.studyPlan.degreeCourse.name}"/><c:out value="${student.studyPlan.name}"/></td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>