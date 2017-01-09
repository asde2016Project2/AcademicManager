<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Professor</h1>
        
        <form method="post" action="<c:url value="login"/>" id="idform" name="idform">
            
            <label for="username">Username</label>
            <input type="text" name="username" id="username" />
            <br/>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" />
            
            
            <input type="submit" value="Login" />
        </form>
        
    </body>
</html>
