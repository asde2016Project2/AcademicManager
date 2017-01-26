<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>



<div class="row">

    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">            
            <div class="x_content">  

                <form:form method="post" action="registerExam" cssClass="form-inline">                     
                    <div class="form-group"> 
                        <label for="examname" class="control-label">Exam Name</label>                     
                        <select name="examname" class="form-control" >   
                            <option value="Select an Exam" label="Select an Exam"></option>

                            <c:forEach items="${exams}" var="exam">
                                <option value="${exam.name}" label="${exam.name}" ></option>
                            </c:forEach>        

                        </select>   
                    </div>
                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> Search</button>
                </form:form>
            </div>
        </div>
    </div>
</div>



<div class="row">

    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Exam list</h2>           
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <p class="text-muted font-13 m-b-30">
                    Lorem ipsum
                </p>

                <table class="table datatable table-hover table-centered table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                    <tr>
                        <th width="80">Student Username</th>
                        <th width="120">First Name</th>
                        <th width="120">Last Name</th>
                        <th width="120">Exam</th>
                        <th width="120">Grade</th>
                    </tr>

                    <c:forEach items="${userar}" var="uar">
                        <tr>
                            <td> <c:out value="${uar.student.username}"/></td>
                            <td> <c:out value="${uar.student.firstName}"/></td>
                            <td> <c:out value="${uar.student.lastName}" /></td>
                            <td> <c:out value="${uar.attempt.exam.name}" /></td>
                            <td>
                                <form action="addCareerExam" method="post">
                                    <input type="text" name="studentUsername" value="${uar.student.username}" hidden="true">
                                    <input type="number" name="attemptId" value="${uar.attempt.attemptId}" hidden="true">
                                    <input type="number" name="grade">
                                    <input type="submit" value="Submit" onsubmit="history.go(-1);">
                                </form>
                            </td>
                        </tr>

                    </c:forEach>



                </table>

            </div>
        </div>
    </div>
</div>