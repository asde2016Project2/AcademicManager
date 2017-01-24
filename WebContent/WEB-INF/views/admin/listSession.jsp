<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
            <h2>Session List</h2>           
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <p class="text-muted font-13 m-b-30">
                Lorem ipsum
            </p>
            <table class="table datatable table-centered table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Degree Course</th>
                        <th>Academic Year</th>
                        <th>Start</th>
                        <th>End</th>
                    </tr>
                </thead>
                <tbody>               
                    <c:forEach items="${lista}" var="examSession">
                        <tr>                        
                            <td>${examSession.examSessionId}</td>
                            <td>${examSession.degreeCourse.name}</td>
                            <td>${examSession.academicYear}</td>
                            <td>${examSession.startingDataString}</td>
                            <td>${examSession.endingDataString}</td>
                        </tr>                    
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div> 