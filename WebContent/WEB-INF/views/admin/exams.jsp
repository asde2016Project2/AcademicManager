<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default">
    <div class="panel-body" style="min-height: 100; max-height: 100;">

        <table class="table table-striped table-hover ">
            <thead>
                <tr>
                    <th width="80">ID</th>
                    <th width="120">Name</th>
                    <th width="120">CFU</th>
                    <th width="120">CODE</th>

                    <th colspan="3" class="actions">Actions</th>
                </tr>
            </thead>
            <c:forEach var="exam" items="${listExams}" varStatus="status">
                <tbody>
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${exam.name}</td>
                        <td>${exam.cfu}</td>
                        <td>${exam.code}</td>
                        <td><a href="<c:url value='exams/delete/${exam.id}'/>">Delete</a></td>
                    </tr>
                </tbody>


            </c:forEach>

        </table>
        <label>This is it</label>
    </div>

</div>