<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Student Menu</h3>
<ul>
	<li><a href="<c:url value="/admin/examForm" />">Register Exam</a></li>
	<li><a href="<c:url value="/admin/careerExamForm" />">CareerExam Form</a></li>
	<li><a href="<c:url value="/admin/exams/" />">List Exams</a></li>
	<li><a href="<c:url value="/admin/careerExams/" />">List Exams</a></li>
	<li><a href="<c:url value="/logout" />">Logout</a></li>
</ul>

