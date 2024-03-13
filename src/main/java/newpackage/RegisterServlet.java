package newpackage;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRegistration(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create User object
        User userModel = new User(name, email, password);

        // Obtain database connection
        Connection connection = ConnectionPro.getConnection();

        try {
            // Create UserDatabase instance with the obtained connection
            UserDatabase userDatabase = new UserDatabase(connection);

            // Save user data
            boolean isUserSaved = userDatabase.saveUser(userModel);

            if (isUserSaved) {
                // Redirect to success page
                response.sendRedirect("index.jsp");
            } else {
                // Redirect to error page
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace of the exception
            // Redirect to error page
            response.sendRedirect("error.jsp");
        }
    }
}
