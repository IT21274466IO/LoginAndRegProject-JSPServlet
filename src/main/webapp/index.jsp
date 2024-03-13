<%-- 
    Document   : index
    Created on : Mar 12, 2024, 7:57:05 PM
    Author     : imant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <div>
            <div>
                <img src="src" alt="No Image"/>
                <h1>Login Account</h1>
                
                <form action="LoginServlet" method="post">
                    <p>Username</p>
                    <input type="text" placeholder="Username" name="email" required>
                    <p>Password</p>
                    <input type="password" placeholder="Password" name="password" required>
                    <input type="submit" value="Login"><br><br>
                    <a href="#">Forget Password</a><br><br><br>
                    <a href="registration.jsp">Create New Account</a>
                </form>
            </div>
        </div>
    </body>
</html>
