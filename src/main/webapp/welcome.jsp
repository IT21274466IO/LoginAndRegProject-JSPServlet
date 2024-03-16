<%-- 
    Document   : welcome
    Created on : Mar 14, 2024, 9:13:16 AM
    Author     : imant
--%>
<%@page  import="newpackage.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% User user = (User) session.getAttribute("loguser");
    if (user == null) {
            response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">                                                                                                             
        <title>Welcome Page</title>
    </head>
    <body>
        <h1>Hello Welcome! <%= user.getName()%> </h1>
        <h3>Your Account ID: <%= user.getId()%></h3>
        <h3>Your Email Address: <%= user.getEmail()%></h3>
        
        <a href="updateUser.jsp">Update my Profile</a>
        
        <a href="LogoutServlet">Logout</a>
    </body>
</html>
