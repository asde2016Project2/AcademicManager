
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>${pageTitle}</h1>

<!--<img src="data:image/jpeg;base64,${photo}" />-->

<span>${numberStudents}</span> 
<span>Student registration requests to accept or reject</span><br>
<span>${numberProfessors}</span> 
<span>Professor registration requests to accept or reject</span><br>
<span>${numberAdmins}</span> 
<span>Administrator registration requests to accept or reject</span>