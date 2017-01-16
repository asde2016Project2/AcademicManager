<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>Empty Footer</h3>
<div>
    <div class="footer-box">
        <h3>Insert dummy data</h3>
        <ul>
            <li>        
                <a href="<c:url value="/dummyData/registerProfessor" />">Create dummy Professor</a>
                <p>From 1 to 6:  username <b>prof1</b>...  -  <b>password:</b> 123456</p>
            </li>
            <li>        
                <a href="<c:url value="/dummyData/registerStudent" />">Create dummy Student</a>
                <p>From 1 to 6:  username <b>stud1</b>...  -  <b>password</b>: 123456</p>
            </li> 
             <li>        
                <a href="<c:url value="/dummyData/registerAdmin" />">Create dummy Admin</a>
                <p>From 1 to 6:  username <b>admin</b>...  -  <b>password</b>: 123456</p>
            </li> 
            <li style="margin-top: 10px">                
                <a href="<c:url value="/dummyData/addeDegreeCourse" />">Create Dummy Degree Course</a>                                
            </li>
            <li style="margin-top: 10px">       
                <a href="<c:url value="/dummyData/registerStudyPlan" />">Create dummy Study Plan</a>                                
            </li>  

        </ul>
    </div>
    <div class="footer-box">
        <h3>Registration</h3>
        <ul>
            <li>        
                <a href="<c:url value="/professor/register" />">Register Professor</a>
                <p></p>
            </li>         
            <li>        
                <a href="<c:url value="/student/register" />">Register Student</a>
                <p></p>
            </li>     
        </ul>
    </div>
    <div class="footer-box">
        <h3>Link</h3>
        <ul>
            <li>        
                <a href="<c:url value="/" />">Home</a>
            </li>            
        </ul>
    </div>
</div>