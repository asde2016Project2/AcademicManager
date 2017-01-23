<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div>

    <div class="login_wrapper">

        <div class="animate form login_form">
            <section class="login_content">
                <div>
                    <h1><i class="fa fa-paw"></i> AcademicManager</h1>                    
                </div>

                <form:form method="post" action="" modelAttribute="loginForm">

                    <h1>Login</h1>
                    <div>     
                        <div>
                            <form:input path="username" id="username" cssClass="form-control" required="true"></form:input>                            
                            <form:errors path="username" cssClass="error form-error"></form:errors>                        
                            </div>
                            <div>
                            <form:password path="password" id="password" cssClass="form-control" required="true"></form:password>                                
                            <form:errors path="password" cssClass="form-error error"></form:errors>    
                            </div>
                            <div>
                            <form:select path="profileType" id="profileType" name="profileType" cssClass="form-control">
                                <option value="">Select Type</option>
                                <option value="administrator">Administrator</option>
                                <option value="professor">Professor</option>
                                <option value="student">Student</option>
                            </form:select>                            
                            <form:errors path="profileType" cssClass="form-error error"></form:errors> 
                            </div>

                        <c:if test="${!empty error }">
                            <br/>
                            <div class="clearfix"></div>
                            <div class="row alert alert-danger">${error}</div> 
                            <div class="clearfix"></div>
                        </c:if>

                        <div>
                            <br/>
                            <button type='submit' class="btn btn-default submit">Login</button>
                        </div>

                        <div class="clearfix"></div>


                        <div class="separator">

                            <p class="change_link">
                                <a href="<c:url value="/student/register" />" class="to_register"> Register as Student </a>
                            </p>


                            <p class="change_link">
                                <a href="<c:url value="/professor/register" />" class="to_register"> Register as Professor </a>
                            </p>

                            <p class="change_link">
                                <a href="<c:url value="/admin/register" />" class="to_register"> Register as Admin </a>
                            </p>

                            <div class="clearfix"></div>
                            <br />

                            <div>                                    
                                <p>©2017 All Rights Reserved. - AcademicManager v0.0.1</p>
                            </div>
                        </div>
                    </form:form>





            </section>
        </div>                            



    </div>



</div>




