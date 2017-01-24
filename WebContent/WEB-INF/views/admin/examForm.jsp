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

                <form:form method="POST" modelAttribute="exam">
                    <%--Exam name--%>
                    <div class="form-group row">
                        <label for="degreeCourseId" class="control-label col-md-3 col-sm-3 col-xs-12">Exam Name</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input id="examName" name="examName" class="form-control" type="text" path="name"
                                        placeholder="Exam Name" />
                        </div>
                    </div>


                    <div class="form-group row">
                        <label for="cfu" class="control-label col-md-3 col-sm-3 col-xs-12">CFU</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input class="form-control" type="number" path="cfu"
                                        placeholder="cfu" id="cfu" />
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="code" class="control-label col-md-3 col-sm-3 col-xs-12">Code</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input class="form-control" type="number" path="code"
                                        placeholder="code" id="code" />

                        </div>
                    </div>
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

