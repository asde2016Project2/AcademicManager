<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <h3>Student Menu</h3>

	<ul>
		<li><a href="<%=request.getContextPath()%>/">Home</a></li>
		<li><a href="<%=request.getContextPath()%>/student/registrationAppeals">Exam Registration Appeals</a></li>
		<li><a href="<%=request.getContextPath()%>/student/examReservationBoard">Exam Reservation Board</a></li>
		<li><a href="<%=request.getContextPath()%>/student/reserveExam">Book Exam</a></li>
	</ul>