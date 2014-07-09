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
        
        <link rel="stylesheet" type="text/css" href="PinTubeStyleSheet.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script type="text/JavaScript" src="PinTubeFunctions.js"></script>
        
    </head>
    <body>
        <header>
        <h4>Welcome ${user}</h4>
        <form action="PinTubeServlet/logout" method="GET">
            <input type="submit" value="Log Out"/>
        </form>
        
            <!--<form id="searchbox" method="GET">-->
                <input type="text" name="searchQ" placeholder="Search YouTube" onkeyup="search()"/> 
                <input type="button" onclick='search()' value="Search"/>
            <!--</form>-->
        </header>
        <div id="main">
            
        </div>
    </body>
</html>
