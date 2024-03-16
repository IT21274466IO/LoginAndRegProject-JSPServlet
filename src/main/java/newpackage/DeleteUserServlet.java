/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package newpackage;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author imant
 */
public class DeleteUserServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
      HttpSession session = request.getSession();
        // Validate CSRF token
        String sessionToken = (String) session.getAttribute("csrfToken");
        String requestToken = request.getParameter("csrfToken");
        if (!sessionToken.equals(requestToken)) {
          // Handle invalid CSRF token (e.g., redirect to error page)
          return;
        }

        // Get user object from session
        User user = (User) session.getAttribute("loguser");
        if (user == null) {
            System.out.println("Cannot identify the User");
          return;
        }

        // Prompt for password confirmation (optional)
        // String enteredPassword = request.getParameter("password");
        // if (!userDatabase.verifyPassword(user.getId(), enteredPassword)) {
        //   // Handle incorrect password (e.g., display error message)
        //   return;
        // }

        // Delete user from database
        UserDatabase userDatabase = null;
        try {
            userDatabase = new UserDatabase(ConnectionPro.getConnection());
        }catch (SQLException ex) {
            Logger.getLogger(DeleteUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      // Handle database errors
      
      if (userDatabase.deleteUser(user.getId())) {
          // User deleted successfully
          session.invalidate();  // Invalidate user session
          response.sendRedirect("index.jsp");  // Redirect to home page
      } else {
          response.sendRedirect("error.jsp");
      }
      }
}

