<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <a href="<c:url value="/admin/create/studyplan"/>">[ + Create Study Plan ]</a>
</div>
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
                        <form method="post" action="<c:url value="#"/>">
                            <input type="hidden" name="id" value="${studyPlan.studyPlanId}" />
                            <button type="submit" class="update">update</button>
                        </form>
                    </div>
                    <div>
                        <a href="<c:url value="/admin/detail/studyplan/${studyPlan.studyPlanId}"/>">Detail</a>                       
                    </div>        
                    <div>
                        <form method="post" action="<c:url value="/admin/delete/studyplan"/>">
                            <input type="hidden" name="id" value="${studyPlan.studyPlanId}" />
                            <button type="submit" class="delete">delete</button>
                        </form>
                    </div>

                </div>
            </div>
        </c:forEach>	
    </div>
</div>