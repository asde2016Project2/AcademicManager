    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <h3>Admin Menu</h3>
    <ul>
        <li>
            <a href="<c:url value="registrations"/>">Registrations</a>
        </li>
        <li>
            <a href="<c:url value="/admin/list/studyplan"/>">StudyPlans</a>
        </li>
        <li>
            <a href="<c:url value="/logout" />">Logout</a>
        </li>
    </ul>
