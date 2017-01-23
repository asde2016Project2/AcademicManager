<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default">
    <div class="panel-body" style="min-height: 100; max-height: 100;">

        <label>Career of ${studentName}</label>

        <table class="table table-striped table-hover ">
            <thead>
                <tr>
                    <th width="80">ID</th>
                    <th width="120">Name</th>
                    <th width="120">CFU</th>
                    <th width="120">Grade</th>
                    <th width="120">Honours</th>
                    <th width="120">Date</th>

                </tr>
            </thead>
            <c:forEach var="cexam" items="${listCareerExams}" varStatus="status">
                <tbody>
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${cexam.exam.name}</td>
                        <td>${cexam.exam.cfu}</td>
                        <td>${cexam.grade}</td>
                        <td><c:choose>
                                <c:when test="${cexam.honours==true}">Honours</c:when>
                                <c:otherwise>None</c:otherwise>    
                            </c:choose></td>
                        <td>${cexam.dateOfExam}</td>

                    </tr>
                </tbody>


            </c:forEach>

        </table>

    </div>

</div>
