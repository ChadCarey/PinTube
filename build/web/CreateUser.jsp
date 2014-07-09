<%-- 
    Document   : CreateUser
    Created on : Jun 18, 2014, 2:27:05 PM
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
        <h4>${invalid}</h4>
        <form action="CreateUserServlet" method="POST">
            Username: <input type="text" name="user"/> <br/>
            Password: <input type="password" name="pass"/> <br/>
            Re-enter your password: <input type="password" name="pass2"/> <br/>
            <input type="submit" value="sign up"/>
        </form>
    </body>
</html>
