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

                <form:form method="POST" modelAttribute="degreeCourseModel">
                    <%--Exam name--%>
                    <div class="form-group row">
                        <label for="name" class="control-label col-md-2 col-sm-2 col-xs-12 text-right">Degree Course Name</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input id="name" name="name" class="form-control" type="text" path="name" placeholder="name" required="required" />
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

