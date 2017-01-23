<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  author: Nello -->
<label> ${pageTitle}</label>
<br/>
<br/>
<br/>
<div class="divTable">
    <div class="divTableBody">
        <div class="divTableRow">
            <div class="divTableCell"><strong>Study plan</strong></div>
            <div class="divTableCell"><strong>Degree Course</strong></div>
            <div class="divTableCell"><strong>Actions</strong></div>
        </div>
        <c:forEach var="studyPlan" items="${studyPlans}">
            <div class="divTableRow">
                <div class="divTableCell">${studyPlan.name}</div>
                <div class="divTableCell">${studyPlan.degreeCourse.name}</div>
                <div class="divTableCell">

                    <div>

                        <a href="<c:url value="/student/details/studyplan/${studyPlan.studyPlanId}"/>">Details</a>                       
                    </div>        


                </div>
            </div>
        </c:forEach>	
    </div>
</div>