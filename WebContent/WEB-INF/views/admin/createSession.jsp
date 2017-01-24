<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h1>${pageTitle}</h1>                                
            </div>
            <div class="x_content">  
                <form:form method="post" action="createSession" cssClass="form-horizontal form-label-left" validate="" modelAttribute="examSessionForm">
                    <spring:bind path="startingDate">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="startingDate" cssClass="control-label col-md-3 col-sm-3 col-xs-12">Start Date</form:label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                <form:input path="startingDate"  name="startingDate" id="startingDate" cssClass="form-control date-picker col-md-7 col-xs-12" required="required" />
                            </div>
                            <form:errors path="startingDate" cssClass="error form-error col-md-6 col-md-offset-3 col-xs-12" />
                        </div>
                    </spring:bind>

                    <spring:bind path="endingDate">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="endingDate" cssClass="control-label col-md-3 col-sm-3 col-xs-12">Ending Date</form:label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                <form:input path="endingDate"  name="endingDate" id="endingDate" cssClass="form-control date-picker col-md-7 col-xs-12" required="required" />
                            </div>
                            <form:errors path="endingDate" cssClass="error form-error col-md-6 col-md-offset-3 col-xs-12" />
                        </div>
                    </spring:bind>



                    <spring:bind path="degreeCourse">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="degreeCourse" cssClass="control-label col-md-3 col-sm-3 col-xs-12">Degree Course</form:label>
                                <div class="col-md-6 col-sm-6 col-xs-12">                                
                                <form:select path="degreeCourse" id="degreeCourse" name="degreeCourse" cssClass="form-control col-md-7 col-xs-12" required="required">   
                                    <form:option value="" label="Select a Degree Course" />
                                    <c:forEach items="${degreeCourses}" var="degree">
                                        <form:option value="${degree.degreeCourseId}" label="${degree.name}" />
                                    </c:forEach>        
                                </form:select>
                            </div>
                            <form:errors path="degreeCourse" cssClass="error form-error col-md-6 col-md-offset-3 col-xs-12"></form:errors>                                                                  
                            </div>
                    </spring:bind>

                    <spring:bind path="academicYear">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="academicYear" cssClass="control-label col-md-3 col-sm-3 col-xs-12">Academic Year</form:label>
                                <div class="col-md-6 col-sm-6 col-xs-12">                                
                                <form:select path="academicYear" id="academicYear" name="academicYear" cssClass="form-control col-md-7 col-xs-12" required="required">   
                                    <form:option value="" label="Select Year" />                                          
                                    <form:option value="2016/2017" label="2016/2017" />                                          
                                    <form:option value="2017/2018" label="2017/2018" />                                          
                                </form:select>
                            </div>
                            <form:errors path="academicYear" cssClass="error form-error col-md-6 col-md-offset-3 col-xs-12"></form:errors>                                                                  
                            </div>
                    </spring:bind>             
                    
                    <div class="ln_solid"></div>
                    <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3 text-center">                            
                            <button type="submit" class="btn btn-success">Submit</button>
                        </div>
                    </div>

                </form:form>

                <c:if test="${not empty error}">
                    <div class="row">
                        <div class="alert alert-danger">
                            Error: ${error}  
                        </div>
                    </div>                        
                </c:if>
            </div>
        </div>
    </div>
</div>