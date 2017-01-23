<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default">
    <div class="panel-body" style="min-height: 100; max-height: 100;">

        <form:form method="POST" modelAttribute="careerExam"
                   class="form-horizontal">
            <fieldset>
                <legend>Add new CareerExam Information</legend>

                <%--Exam name--%>
                <div class="form-group row">
                    <label class="col-xs-2 col-form-label">Completed</label>
                    <div class="col-xs-4">
                        <div class="radio">
                            <form:input class="form-control" type="text" path="done"
                                        placeholder="Completed" />
                            <div class="has-error" style="color: #ff0700">
                                <form:errors path="done" />
                            </div>
                        </div>
                    </div>
                </div>


                <div class="form-group row">
                    <label for="cfu" class="col-xs-2 col-form-label">Score</label>
                    <div class="col-xs-4">
                        <form:input class="form-control" type="number" path="grade"
                                    placeholder="grade" id="cfu" />
                    </div>
                </div>

                <div class="form-group row">
                    <label for="code" class="col-xs-2 col-form-label">Mandatory</label>
                    <div class="col-xs-4">
                        <form:input class="form-control" type="text" path="mandatory"
                                    placeholder="mandatory" id="code" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-6 col-lg-offset-6">
                        <button id="btnAddCareerExam" type="submit"
                                class="btn btn-primary">Submit</button>
                        <a class="btn btn-default" onclick="history.back()">Back</a>
                    </div>
                </div>

            </fieldset>

        </form:form>

    </div>
</div>