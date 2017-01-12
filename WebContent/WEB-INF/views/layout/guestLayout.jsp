<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/style.css"/>" />
        <title>${pageTitle}</title>     
    </head>  
    <body>  
        <div class="main-box boxed">
            <div id="header">
                <tiles:insertAttribute  name="header" />  
            </div>
            <div id="content" >
                <tiles:insertAttribute  name="body" />        
            </div>
            <div id="footer">
                <tiles:insertAttribute  name="footer" />
            </div>  
        </div>

    </body>  
</html> 