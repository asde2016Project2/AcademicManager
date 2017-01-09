<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Academic Manager</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/script/jquery-ui-1.11.4/jquery-ui.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/style.css"/>">
        <script src="<c:url value="/resources/script/jquery-ui-1.11.4/external/jquery/jquery.js"/>"></script>
        <script src="<c:url value="/resources/script/jquery-ui-1.11.4/jquery-ui.js"/>"></script>

        <script>
            $(document).ready(function () {
                $("#button").button();
            });

            $(document).ready(function () {
                $("#content").buttonset();
            });
            $(document).ready(function () {
                $('#form input').on('change', function () {
                    var v = $("input[type=radio]:checked", "#form").attr("urlPage");
                    $("#result *").remove();
                    $("#result").load("../resources/" + v);
                });
            });

            $(document).ready(function () {
                $("#footer").load("resources/footer.html");
            });
        </script>
    </head>
    <body>
        <div id="wrapper">
            <div id="header"></div>

            <h3>Exam manager</h3>
            <br/>
            <a href="login" >Login</a>
            <a href="<c:url value="/student/exams"/>">Exams list</a>
            <a href="<c:url value="/student/exams/add"/>">Add Exam</a>
            <a href="<c:url value="/student/studentList"/>"> Student List</a>
            <a href="<c:url value="/professor/dashboard"/>"> Professor dashboard</a>
            <a href="<c:url value="/admin/dashboard"/>" >Admin Dashboard</a>
            <br/>

        </div>

        <div id="footer"></div>
    </body>
</html>