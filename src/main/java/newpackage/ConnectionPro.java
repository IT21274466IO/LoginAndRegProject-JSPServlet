package newpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author imantha_o
 */
public class ConnectionPro {
    private static Connection con;
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_project", "root", "123456");
            System.out.println("Connection to database successful.");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Throw an exception or return a default value based on your application's logic
            throw new SQLException("Failed to connect to the database.", e);
    }
}

}
