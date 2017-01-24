<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>List exams</h3>

<div class="panel panel-default">
    <div class="panel-body" style="min-height: 100; max-height: 100;">

        <table class="table table-striped table-hover ">
            <thead>
                <tr>
                    <th width="80">ID</th>
                    <th width="120">Name</th>
                    <th width="120">CFU</th>
                    <th width="120">CODE</th>
                </tr>
            </thead>
            <c:forEach var="exam" items="${listExams}" varStatus="status">
                <tbody>
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${exam.name}</td>
                        <td>${exam.cfu}</td>
                        <td>${exam.code}</td>
                    </tr>
                </tbody>


            </c:forEach>

        </table>
    </div>

</div>