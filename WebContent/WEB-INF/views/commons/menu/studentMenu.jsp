<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <h3>Student Menu</h3>
    <ul>
        <li>
            <a href="#">Menu Item 1</a>
        </li>
        <li>
            <a href="#">Menu Item 2</a>
        </li>
        <li>
            <a href="#">Menu Item 3</a>
        </li>
        <li>        
            <a href="<c:url value="/logout" />">Logout</a>
        </li>
    </ul>
