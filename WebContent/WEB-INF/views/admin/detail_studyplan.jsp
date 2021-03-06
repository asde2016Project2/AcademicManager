<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="page-title row">
    <div class="title_left col-lg-12">
        <h3>${pageTitle}</h3>
    </div>

    <div class="title_right">      
    </div>
</div>


<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Exam list</h2>           
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <p class="text-muted font-13 m-b-30">
                    Lorem ipsum
                </p>

                <table class="table datatable table-centered table-striped table-hover table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
                            <th>CFU</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="exam" items="${exams}">
                            <tr>
                                <td>${exam.code}</td>
                                <td>${exam.name}</td>
                                <td>${exam.cfu}</td>
                            </tr>
                        </c:forEach>	
                    </tbody>
                </table>

            </div>
        </div>
    </div> 
</div>

