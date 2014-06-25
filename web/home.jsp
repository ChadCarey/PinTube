<%-- 
    Document   : home
    Created on : Jun 16, 2014, 4:16:41 PM
    Author     : chad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h4>Welcome ${user}</h4>
        <form action="PinTubeServlet/logout" method="GET">
            <input type="submit" value="Log Out"/>
        </form>
        <h1>Hello World!</h1>
    </body>
</html>
