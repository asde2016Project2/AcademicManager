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

                <form:form method="post" action="register" modelAttribute="administrator" cssClass="form-horizontal form-label-left" validate="">
                    <spring:bind path="username">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="username" cssClass="control-label col-md-3 col-sm-3 col-xs-12">Username</form:label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                <form:input path="username" name="username" id="username" cssClass="form-control col-md-7 col-xs-12" required="required" />
                            </div>
                            <form:errors path="username" cssClass="error form-error col-md-6 col-md-offset-3 col-xs-12" />
                        </div>
                    </spring:bind>

                    <spring:bind path="password">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="password" cssClass="control-label col-md-3 col-sm-3 col-xs-12">Password</form:label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                <form:password path="password" name="password" id="password" cssClass="form-control col-md-7 col-xs-12" required="required" />
                            </div>
                            <form:errors path="password" cssClass="error form-error col-md-6 col-md-offset-3 col-xs-12" />
                        </div>
                    </spring:bind>

                    <spring:bind path="firstName">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="firstName" cssClass="control-label col-md-3 col-sm-3 col-xs-12">First Name</form:label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                <form:input path="firstName" name="first_name" id="first_name" cssClass="form-control col-md-7 col-xs-12" required="required" />
                            </div>
                            <form:errors path="firstName" cssClass="error form-error col-md-6 col-md-offset-3 col-xs-12" />
                        </div>
                    </spring:bind>

                    <spring:bind path="lastName">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="lastName" cssClass="control-label col-md-3 col-sm-3 col-xs-12">Last Name</form:label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                <form:input path="lastName" name="last_name" id="last_name" cssClass="form-control col-md-7 col-xs-12" required="required" />
                            </div>
                            <form:errors path="lastName" cssClass="error form-error col-md-6 col-md-offset-3 col-xs-12" />
                        </div>
                    </spring:bind>

                    <spring:bind path="email">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="email" cssClass="control-label col-md-3 col-sm-3 col-xs-12">Email</form:label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                <form:input path="email"  name="email" id="email" cssClass="form-control col-md-7 col-xs-12" required="required" />
                            </div>
                            <form:errors path="email" cssClass="error form-error col-md-6 col-md-offset-3 col-xs-12" />
                        </div>
                    </spring:bind>


                    <spring:bind path="age">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="age" cssClass="control-label col-md-3 col-sm-3 col-xs-12">Age</form:label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                <form:input path="age"  name="age" id="age" cssClass="form-control col-md-7 col-xs-12" required="required" />
                            </div>
                            <form:errors path="age" cssClass="error form-error col-md-6 col-md-offset-3 col-xs-12" />
                        </div>
                    </spring:bind>

                    <spring:bind path="dateOfBirth">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="dateOfBirth" cssClass="control-label col-md-3 col-sm-3 col-xs-12">Date Of Birth</form:label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                <form:input path="dateOfBirth"  name="dateOfBirth" id="dateOfBirth" cssClass="form-control date-picker col-md-7 col-xs-12" required="required" />
                            </div>
                            <form:errors path="dateOfBirth" cssClass="error form-error col-md-6 col-md-offset-3 col-xs-12" />
                        </div>
                    </spring:bind>

                    <div class="ln_solid"></div>
                    <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">                            
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

