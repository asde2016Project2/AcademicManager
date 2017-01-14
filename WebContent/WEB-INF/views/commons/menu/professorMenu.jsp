<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Professor Menu</h3>
<ul>
    <li>
        <a href="<c:url value="/professor/createSession"/>"> Create Session</a>
    </li>
    <li>
        <a href="<c:url value="/professor/viewAllSession"/>"> List Session</a>
    </li>    
    <li>        
        <a href="<c:url value="/logout" />">Logout</a>
    </li>
</ul>
