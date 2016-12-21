<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Exam Information</title>
        <link rel="stylesheet" type="text/css" href="/resources/script/jquery-ui-1.11.4/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="/resources/style/style.css">
        <script src="/resources/script/jquery-ui-1.11.4/external/jquery/jquery.js"></script>
        <script src="/resources/script/jquery-ui-1.11.4/jquery-ui.js"></script>
    </head>
    <body>


        <div id="content">
            <h1>Create Exam</h1>
            <c:url var="addAction" value="/exams/add"/>
            <form:form method="POST" action="${addAction}" commandName="examForm" >
                <table>
                    <c:if test="${!empty exam.name}">
                        <tr>
                            <td>
                                <form:label path="id">
                                    <spring:message text="ID"/>
                                </form:label>
                            </td>
                            <td>
                                <form:input path="id" readonly="true" size="8" disabled="true"/>
                                <form:hidden path="id"/>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            <form:label path="name">
                                <spring:message text="Exam Name"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="name"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="cfu">
                                <spring:message text="CFU"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="cfu"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="code">
                                <spring:message text="Code"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="code"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <c:if test="${!empty exam.name}">
                                <input type="submit"
                                       value="<spring:message text="Edit Exam"/>"/>
                            </c:if>
                            <c:if test="${empty exam.name}">
                                <input type="submit"
                                       value="<spring:message text="Add Exam"/>"/>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </form:form>

            <div>


                <a href="../../WEB-INF/views/createExam.jsp">Create Examinations</a>
                <h1>Exam List</h1>

                <c:if test="${!empty listExams}">
                    <table class="tg">
                        <tr>
                            <th width="80">ID</th>
                            <th width="120">Name</th>
                            <th width="120">CFU</th>
                            <th width="120">CODE</th>
                            <th width="60">Edit</th>
                            <th width="60">Delete</th>
                        </tr>
                        <c:forEach items="${listExams}" var="examForm">
                            <tr>
                                <td>${exam.id}</td>
                                <td><a href="/examdata/${exam.id}" target="_blank">${exam.name}</a></td>
                                <td>${exam.cfu}</td>
                                <td>${exam.code}</td>
                                <td><a href="<c:url value='/edit/${exam.id}'/>">Edit</a></td>
                                <td><a href="<c:url value='/remove/${exam.id}'/>">Delete</a></td>
                            </tr>
                            
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </body>
</html>