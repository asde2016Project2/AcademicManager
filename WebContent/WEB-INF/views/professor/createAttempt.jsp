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

                <c:if test="${!empty message }">
                    <div class="row">
                        <div class="alert alert-success">
                            ${message}
                        </div>
                    </div>
                </c:if>

                <form:form method="post" action="createAttempt" cssClass="horizontalForm">    
                    <div class="row">

                        <div class="col-lg-4 col-sm-12">
                            <div class="form-group">
                                <label for="startingDate" class="control-label col-md-4 col-sm-4 col-xs-12">Open Registration </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input  id="startingDate" name="startingDate"  type="text" class="form-control date-picker col-md-7 col-xs-12" required="required"/>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-sm-12">
                            <div class="form-group">
                                <label for="endingDate" class="control-label col-md-4 col-sm-4 col-xs-12">Close Registration </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input  id="endingDate" name="endingDate"  type="text" class="form-control date-picker col-md-7 col-xs-12" required="required"/>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-sm-12">
                            <div class="form-group">
                                <label for="examDate" class="control-label col-md-4 col-sm-4 col-xs-12">Exam Date </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input id="examDate" name="examDate" type="text" class="form-control date-picker" required="required" />                    
                                </div>
                            </div>
                        </div>

                    </div>


                    <br/>
                    <br/>                    


                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Class: </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="classRoom" class="form-control" required="required">
                                    <option value="mt1">mt1</option>
                                    <option value="mt2">mt2</option>
                                    <option value="mt3">mt3</option>
                                    <option value="mt4">mt4</option>
                                    <option value="mt5">mt5</option>
                                    <option value="mt6">mt6</option>
                                    <option value="mt8">mt8</option>
                                </select>
                            </div>
                        </div>     
                    </div>



                    <br/>

                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Exam Session </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select name="examSession" class="form-control" required="required">
                                    <option value="">Choose</option>
                                    <c:forEach items="${examSessions}" var="examSes">                                        
                                        <option value="${examSes.examSessionId}"> ${examSes.startingDataString} --- ${examSes.endingDataString}"></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>




                    <div class="ln_solid"></div>
                    <div class="form-group row">
                        <div class="col-lg-12 text-center">                            
                            <button type="submit" class="btn btn-success">Submit</button>
                        </div>
                    </div>
                </form:form>


                <c:if test="${!empty error }">
                    <div class="row">
                        <div class="alert alert-danger">
                            ${error}
                        </div>
                    </div>
                </c:if>         


            </div>
        </div>
    </div>
</div>

