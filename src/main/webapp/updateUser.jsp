<%-- 
    Document   : updateUser
    Created on : Mar 14, 2024, 10:04:24 AM
    Author     : imant
--%>
<%@ page import="newpackage.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  // Get user object from session (assuming user is already logged in)
  User user = (User) session.getAttribute("loguser");
  if (user == null) {
    response.sendRedirect("error.jsp");
    return;
  }
%>

<!DOCTYPE html>
<html>
<head>
  <title>Update Profile</title>
</head>
<body>

  <form action="UpdateServlet" method="post">
    <table>
      <tr>
        <td>Name:</td>
        <td><input type="text" name="name" value="<%= user.getName() %>" /></td>
      </tr>
      <tr>
        <td>Email:</td>
        <td><input type="text" name="email" value="<%= user.getEmail() %>" /></td>
      </tr>
      <tr>
        <td>Old Password:</td>  <td><input type="password" name="oldPassword" /></td>
      </tr>
      <tr>
        <td>New Password:</td>  <td><input type="password" name="newPassword" /></td>
      </tr>
      <tr>
        <td>Confirm New Password:</td>  <td><input type="password" name="confirmPassword" /></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Update Profile" /></td>
        <td><a href="confirmDelete.jsp">Delete Profile</a></td>
      </tr>
    </table>
  </form>

</html>
