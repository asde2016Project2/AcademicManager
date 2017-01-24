<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  author: Nello -->
<label> </label>

<div class="page-title">
    <div class="title_left">
        <h3>${pageTitle}</h3>
    </div>

    <div class="title_right">      
    </div>
</div>


<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>Study Plan List</h2>           
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <p class="text-muted font-13 m-b-30">
                Lorem ipsum
            </p>
            <table class="table datatable table-centered table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Study Plan</th>
                        <th>Degree Course</th>
                        <th>&nbsp;</th>                       
                    </tr>
                </thead>
                <tbody>               
                    <c:forEach var="studyPlan" items="${studyPlans}">
                        <tr>                        
                            <td>${studyPlan.name}</td>
                            <td>${studyPlan.degreeCourse.name}</td>
                            <td>
                                <a class="btn btn-info" href="<c:url value="/details/studyplan/${studyPlan.studyPlanId}"/>">Details</a>                       
                            </td>
                        </tr>                    
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>
