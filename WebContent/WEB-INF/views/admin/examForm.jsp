<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default">
    <div class="panel-body">


        <form:form method="POST" modelAttribute="exam" cssClass="form-horizontal">

            <fieldset>
                <legend>Add new Exam Information</legend>

                <%--Exam name--%>
                <div class="form-group row">
                    <label class="col-xs-2 col-form-label">Exam name</label>
                    <div class="col-xs-10">
                        <div class="radio">
                            <form:input class="form-control" type="text" path="name"
                                        placeholder="Exam Name" />

                        </div>
                    </div>
                </div>


                <div class="form-group row">
                    <label for="cfu" class="col-xs-2 col-form-label">CFU</label>
                    <div class="col-xs-6">
                        <form:input class="form-control" type="number" path="cfu"
                                    placeholder="cfu" id="cfu" />
                    </div>
                </div>

                <div class="form-group row">
                    <label for="code" class="col-xs-2 col-form-label">Code</label>
                    <div class="col-xs-6">
                        <form:input class="form-control" type="number" path="code"
                                    placeholder="code" id="code" />

                    </div>
                </div>




                <div class="form-group">
                    <div class="col-lg-6 col-lg-offset-6">
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <a class="btn btn-default" onclick="history.back()">Back</a>
                    </div>
                </div>

            </fieldset>

        </form:form>

    </div>
</div>
