<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>Admin Menu</h3>
<ul>
    <li>
        <a href="<c:url value="/admin/dashboard"/>"> Dashboard</a>       
    </li>
    <li>
      <a href="<c:url value="/admin/examForm" />">Create Exam</a>
    </li>
  <li>
       <a href="<c:url value="/admin/exams/" />">List Exams</a>
     </li>
	  <li>
      <a href="<c:url value="/admin/careerExamForm" />">CareerExam Form</a>
    </li>	  
	   <li>
        <a href="<c:url value="/admin/careerExams/" />">List CareerExams</a>
     </li>
     <li>
        <a href="<c:url value="/admin/registrations"/>">Student Approval</a>
    </li>
   <li>
        <a href="<c:url value="/admin/create/studyplan"/>">Create Study Plan</a>
    </li>  
    <li>
        <a href="<c:url value="/admin/list/studyplan"/>">List Study Plan</a>
    </li>       
    <li>
        <a href="<c:url value="/logout" />">Logout</a>        
    </li>
</ul>
