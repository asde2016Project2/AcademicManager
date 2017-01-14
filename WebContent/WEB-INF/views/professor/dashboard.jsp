<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Professor Dashboard</h1>

<br/>
	<a href="<c:url value="/professor/createSession"/>"> Create Session</a>
    <a href="<c:url value="/professor/viewAllSession"/>"> List Session</a>
<br/>