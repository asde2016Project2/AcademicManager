<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:putAttribute name="title" value="Professor Dashboard" />
<h1>Professor Dashboard</h1>
<br/>
<a href="<c:url value="/logout" />">Logout</a>