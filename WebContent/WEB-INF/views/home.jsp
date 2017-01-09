<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Academic Manager</title>
        <link rel="stylesheet" type="text/css" href="/resources/script/jquery-ui-1.11.4/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="/resources/style/style.css">
        <script src="/resources/script/jquery-ui-1.11.4/external/jquery/jquery.js"></script>
        <script src="/resources/script/jquery-ui-1.11.4/jquery-ui.js"></script>

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
                $("#footer").load("../resources/footer.html");
            });
        </script>
    </head>
    <body>
        <div id="wrapper">
            <div id="header"></div>

            <h3>Exam manager</h3>
            <br/>
            <a href="<c:url value="login"/>" target="_blank">Login</a>
            <a href="<c:url value="student/exams"/>" target="_blank">Exams list</a>
            <a href="<c:url value="student/studentlist"/>" target="_blank"> Student List</a>
            <a href="<c:url value="student/student"/>" target="_blank">Student Registration</a>
            <br/>

        </div>

        <div id="footer"></div>
    </body>
</html>