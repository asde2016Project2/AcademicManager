<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>

<div class="form-message">
	<%
		//show additional message set in the controller
	%>
	<h2>${message}</h2>
</div>

<%
	//show additional error set in the controller
%>
<div class="form-error">${error}</div>

<div class="register-form">
	
	<form:form method="post" modelAttribute="studentForm" id="studentForm">


 <label for="username">Username</label>
    <form:input path="username" id="username"></form:input>
         
        <br/>
        <br/>
        
         <label for="password">Password</label>
    <form:input path="password" id="password"></form:input>
         
        <br/>
        <br/>
        
         <label for="firstName">FirstName</label>
    <form:input path="firstName" id="firstName"></form:input>
         
        <br/>
        <br/>
        
         <label for="lastName">Last Name</label>
    <form:input path="lastName" id="lastName"></form:input>
         
        <br/>
        <br/>
        
         <label for="email">Email</label>
    <form:input path="email" id="email"></form:input>
         
        <br/>
        <br/>
        
         <label for="age">Age</label>
    <form:input path="age" id="age"></form:input>
         
        <br/>
        <br/>
        
      
        
         <label for="dateOfBirth">Date of Birth</label>
    <form:input path="dateOfBirth" id="dateOfBirth"></form:input>
         
        <br/>
        <br/>


 

        <label for="studyPlanId">Study Plan</label>
    <form:select path="studyPlanId" id="studyPlanId" name="studyPlanId">   
        <c:forEach var="studyPlanItem" items="${studyPlanList}">
            <form:option value="${studyPlanItem.studyPlanId}" label="${studyPlanItem.name}" />
        </c:forEach>        
    </form:select>



        
          <br/>
        <br/>
        
    <form:button type="submit" value="create"> Register </form:button>
        
</form:form>

		

</div>

