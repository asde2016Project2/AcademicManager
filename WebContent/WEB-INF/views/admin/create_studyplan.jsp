<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="row">

    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h1>${pageTitle}</h1>                                

            </div>

            <div class="x_content">



                <form:form method="post" modelAttribute="studyPlanForm" id="studyPlanForm">
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Name
                            Study Plan<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input path="name" id="name" required="required"
                                        cssClass="form-control col-md-7 col-xs-12"></form:input>
                            </div>
                        </div>
                        <br />
                        <br />

                        <div class="form-group">
                            <label for="degreeCourseId" class="control-label col-md-3 col-sm-3 col-xs-12">Degree
                                Course</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:select class="form-control" path="degreeCourseId" id="degreeCourseId" name="degreeCourseId">
                                <c:forEach var="degreeCourseItem" items="${degreeCourseList}">
                                    <form:option value="${degreeCourseItem.degreeCourseId}"
                                                 label="${degreeCourseItem.name}" />
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>

                    <br />
                    <br />

                    <h2>SELECT EXAMS</h2>
                    <c:forEach var="exam" items="${examList}">
                        <form:checkbox path="examList" value="${exam.id}" label="${exam.name}" />	
                        <br>
                    </c:forEach>

                    <br />

                    <div class="ln_solid"></div>
                    <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3 text-center">                            
                            <button type="submit" class="btn btn-success">Save</button>
                        </div>
                    </div>
                </form:form>


            </div>

        </div> 

    </div>

</div>
