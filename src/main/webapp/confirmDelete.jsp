<%-- 
    Document   : confirmDelete
    Created on : Mar 16, 2024, 12:25:31 PM
    Author     : imant
--%>

<%@ page import="java.util.UUID" %>  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  // Generate a random CSRF token
  String csrfToken = UUID.randomUUID().toString();
  session.setAttribute("csrfToken", csrfToken);
%>

<!DOCTYPE html>
<html>
<head>
  <title>Confirm Profile Deletion</title>
</head>
<body>

  Are you sure you want to delete your profile? This action cannot be undone.<br>
  All your data and account information will be permanently removed.

  <form action="DeleteUserServlet" method="post">
    <input type="hidden" name="csrfToken" value="<%= csrfToken %>" />  <input type="submit" value="Confirm Deletion" />
    <a href="updateUser.jsp">Cancel</a>
  </form>

</body>
</html>
