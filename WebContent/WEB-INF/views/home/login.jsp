<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="login-form">
    <form:form method="post" action="login" modelAttribute="loginForm">

        <label for="nameInput">Username</label>
        <form:input path="username" id="username"></form:input>
        <br/>
        <form:errors path="username" class="error"></form:errors>
            <br/>
            <br/>
            <label for="password">Password</label>
        <form:password path="password" id="password"></form:password>
        <br/>
        <form:errors path="password" class="error"></form:errors>            
            <br/>
            <br/>
            <label for="profileType">Profile Type</label>
        <form:select path="profileType" id="profileType" name="profileType">
            <option value="">Select Type</option>
            <option value="administrator">Administrator</option>
            <option value="professor">Professor</option>
            <option value="Student">Student</option>
        </form:select>
            <br/>
        <form:errors path="profileType" class="error"></form:errors> 
            <br/>
            <br/>
            <input class="btn-submit" type="submit" value="Login" />
    </form:form>

    <div class="form-error">${error}</div> 
</div>


