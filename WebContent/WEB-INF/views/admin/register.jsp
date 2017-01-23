<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>

<div class="form-message">
    <% //show additional message set in the controller %>
    <h2>${message}</h2>
</div>

 <% //show additional error set in the controller %>
    <div class="form-error">${error}</div> 

<div class="register-form">
    <form:form method="post" action="register" modelAttribute="administrator">


        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:label path="username">Username</form:label>
                <form:input path="username" type="text" name="username" id="username" />
                <form:errors path="username" />
            </div>
        </spring:bind>
        <br/>

        <label for="password">Password</label>
        <form:password path="password" id="password" name="password"></form:password>
            <br/>
        <form:errors path="password" class="error"></form:errors>            

            <br/>
            <hr/>
            <br/>

        <form:label path="firstName">First Name</form:label>
        <form:input path="firstName" id="firstName" name="firstName"></form:input>
            <br/>
        <form:errors path="firstName" class="error"></form:errors>

            <br/>
            <br/>

            <label for="lastName">Last Name</label>
        <form:input path="lastName" id="lastName" name="lastName"></form:input>
            <br/>
        <form:errors path="lastName" class="error"></form:errors>     

            <br/>
            <br/>

            <label for="email">Email</label>
        <form:input path="email" id="email" name="email" ></form:input>
            <br/>
        <form:errors path="email" class="error"></form:errors>     

            <br/>
            <br/>

            <label for="age">Age</label>
        <form:input path="age" id="age" name="age"></form:input>
            <br/>
        <form:errors path="age" class="error"></form:errors>     

            <br/>
            <br/>

            <label for="dateOfBirth">Date of birth</label>
        <form:input path="dateOfBirth" id="dateOfBirth" name="dateOfBirth"></form:input>
            <br/>
        <form:errors path="dateOfBirth" class="error"></form:errors>     

            <br/>
            <br/>        

            <input class="btn-submit" type="submit" value="Register" />
    </form:form>

    <%
    /*
        // an alternative way of display errors
        <spring:hasBindErrors name="professor">
            <c:forEach var="error" items="${errors.allErrors}">
                <div class="form-error">${error}</div> 
                <br />
            </c:forEach>
        </spring:hasBindErrors>
        
        //another way too
        <form:errors path="*" class="has-error" />
    */
    %>        
         
    
   

</div>

