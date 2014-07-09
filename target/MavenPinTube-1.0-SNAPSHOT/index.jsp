<%-- 
    Document   : index
    Created on : Jun 16, 2014, 8:33:42 AM
    Author     : chad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to PinTube!</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    </head>
    <body>
        <h1>Please Log In </h1>
        
        <p>${incorrect}</p>
        <form action="PinTubeUserServlet" method="POST">
            Username: <input name="username" type="text"/>
            <br/>
            Password: <input name="password" type="password"/>
            <br/>
            <input type="submit"/>
        </form>
        <h3><a href="CreateUser.jsp">Sign Up</a></h3>
    </body>
</html>
