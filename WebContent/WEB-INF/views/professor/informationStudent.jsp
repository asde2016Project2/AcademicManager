<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>           

<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">            
            <div class="x_content">  
                <form action="informationStudent" method="post" class="form-inline">
                    <div class="form-group">
                        <label class="control-label"> Username:</label> 
                        <input type="username" name="username"class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> Search</button>
                </form>
            </div>
        </div>
    </div>
</div>
<br/>
<c:if test="${!empty student }">
    <div class="row">
        <div class="col-lg-3">
            <div class="profile_img">    

                <c:if test="${!empty student.photo }">
                    <img src="<c:url value="data:image/jpeg;base64,/resources/images/${student.photo}"/>" class="img-rounded">
                </c:if>
                <c:if test="${empty student.photo }">
                    <img src="<c:url value="data:image/jpeg;base64,/resources/images/default-user.png"/>" class="img-rounded">
                </c:if>

            </div>
        </div>
        <div class="col-lg-9">
            <p><span class="label label-default">First Name:</span> ${student.firstName}</p>
            <p><span class="label label-default">Last Name:</span> ${student.lastName}</p>
            <p><span class="label label-default">Username:</span> ${student.username}</p>
            <p><span class="label label-default">Email:</span> ${student.email}</p>
        </div>
    </div>
</c:if>

<div class="row">

    <div class="col-md-12 col-sm-12 col-xs-12 mycontent">
        <div class="x_panel">
            <div class="x_title">
                <h2>Student Exam</h2>           
                <div class="clearfix"></div>
            </div>
            <div class="x_content">               

                <table class="table datatable table-hover table-centered table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">

                    <thead>
                        <tr>
                            <th width="80">Exam</th>
                            <th width="120">CFU</th>
                            <th width="120">Done</th>
                            <th width="120">Grade</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${infoStudent}" var="infoStud">
                            <tr>
                                <td><c:out value="${infoStud.exam.name}" /></td>
                                <td><c:out value="${infoStud.exam.cfu}"/></td>
                                <td><c:out value="${infoStud.done}" /></td>
                                <td><c:out value="${infoStud.grade}"/></td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>