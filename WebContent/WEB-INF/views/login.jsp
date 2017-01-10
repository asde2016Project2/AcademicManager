<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>AcademicManager - Login</title>
    </head>
    <body>
        <h1>Login</h1>
        
        <form:form method="post" action="login" modelAttribute="loginForm">
            
            <label for="nameInput">Name: </label>
            <form:input path="username" id="username"></form:input>
            <form:errors path="username" cssclass="error"></form:errors>
            <br/>
            <br/>
            <label for="password">Password</label>
            <form:password path="password" id="password"></form:password>
            <form:errors path="password" cssclass="error"></form:errors>            
            <br/>
            <br/>
            <label for="profileType">Profile Type</label>
            <form:select path="profileType" id="profileType" name="profileType">
                <option value="">Select Type</option>
                <option value="administrator">Administrator</option>
                <option value="professor">Professor</option>
                <option value="Student">Student</option>
            </form:select>
            <form:errors path="profileType" cssclass="error"></form:errors> 
            <br/>
            <br/>
            <input type="submit" value="Login" />
        </form:form>
        
        <div>${error}</div>
        
    </body>
</html>
