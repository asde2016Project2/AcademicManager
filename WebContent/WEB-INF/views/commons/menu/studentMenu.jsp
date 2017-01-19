<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <h3>Student Menu</h3>

    <ul>
        <li>
            <a href="<c:url value="/student/dashboard"/>"> Dashboard</a>
        </li>
	<li><a href="<c:url value="/student/registrationAppeals" />">Registration Appeals</a></li>
	<li><a href="<c:url value="/student/list/ExamReserve" />">Exam ReservationBoard</a></li>
	<li><a href="<c:url value="/student/reserveExam" />">Reserve Exam</a></li>
	<li><a href="<c:url value="/student/visualizeStudyPlan" />">Visualize Study Plan</a></li>
	<li><a href="<c:url value="/logout" />">Logout</a></li>
</ul>
