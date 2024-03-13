<%-- 
    Document   : registration
    Created on : Mar 12, 2024, 7:57:33 PM
    Author     : imant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <div class="container">
            <div class="regbox box">
                <img class="avatar" src="">
                <h1>Register Account</h1>
                <form action="RegisterServlet" method="post">
                    <p>Username</p>
                    <input type="text" placeholder="Username" name="name" required>
                    <p>Usermail</p>
                    <input type="email" placeholder="Email" name="email" required>
                    <p>Password</p>
                    <input type="password" placeholder="Password" name="password" required>
                    <input type="submit" value="Register"><br><br>
                    <a href="index.jsp">Already have an Account?</a>
                </form>
            </div>
        </div>
        
    </body>
</html>
