package newpackage;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import newpackage.ConnectionPro;

import newpackage.User;
import newpackage.UserDatabase;

public class UpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get session and check if user is logged in
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loguser");
        if (user == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        // Get user input from the form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Perform basic client-side validation (improve with JS later)
        String errorMessage = "";
        if (name == null || name.isEmpty()) {
            errorMessage += "Name cannot be empty. <br>";
        }
        if (email == null || email.isEmpty()) {
            errorMessage += "Email cannot be empty. <br>";
        } else if (!isValidEmail(email)) { // Add a method to validate email format
            errorMessage += "Invalid email format. <br>";
        }
        if (!newPassword.isEmpty() && (newPassword.isEmpty() || !newPassword.equals(confirmPassword))) {
            errorMessage += "New password and confirm password don't match. <br>";
        }

        // If errors exist, redirect back to update page with error message
        if (!errorMessage.isEmpty()) {
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateUser.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Update user data
        UserDatabase userDatabase = null;
        try {
            userDatabase = new UserDatabase(ConnectionPro.getConnection()); // Establish connection
            user.setName(name);
            user.setEmail(email);

            // Update password only if provided
            if (!newPassword.isEmpty()) {
                // Validate old password (assuming a method to check password in UserDatabase)
                if (!userDatabase.verifyPassword(user.getId(), oldPassword)) {
                    errorMessage = "Incorrect old password.";
                    request.setAttribute("errorMessage", errorMessage);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("updateUser.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                user.setPassword(newPassword); // Update password if valid
            }

            if (userDatabase.updateUser(user)) {
                // Update successful, set success message in session
                session.setAttribute("successMessage", "Profile updated successfully!");
                response.sendRedirect("welcome.jsp"); // Redirect to home page
            } else {
                errorMessage = "Failed to update profile. Please try again.";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("updateUser.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorMessage = "Internal server error. Please try again later.";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateUser.jsp");
            dispatcher.forward(request, response);
        } finally {
            if (userDatabase != null) {
                try {
                    userDatabase.con.close(); // Close connection
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Add a method to validate email format (implement your logic here)
    private boolean isValidEmail(String email) {
        // Implement your email validation logic here (e.g., using regular expressions)
        return true; // Replace with your implementation
    }
}
