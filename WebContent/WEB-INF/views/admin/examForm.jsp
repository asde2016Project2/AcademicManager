<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
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
				<button type="submit" class="btn btn-primary">Submit</button>
                        
             

        </form:form>
</div>

