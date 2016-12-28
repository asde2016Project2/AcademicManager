<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Exam Information</title>
    </head>
    <body>
        <h1>Exam Details</h1>

        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="120">EXAM NAME</th>
                <th width="120">CFU</th>
                <th width="120">CODE</th>
            </tr>
            
            <tr>
                <td>${exam.id}</td>
                <td>${exam.name}</td>
                <td>${exam.cfu}</td>
                <td>${exam.code}</td>
            </tr>
        </table>
    </body>
</html>