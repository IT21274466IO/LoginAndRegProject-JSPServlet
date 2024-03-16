package newpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author imantha_o
 */

public class ConnectionPro {
    private static final String URL = "jdbc:mysql://localhost:3306/jsp_project";
    private static final String USER = "root";
    private static final String PASSWORD = "MYSQL#12345ok";

    private static Connection con;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection to database successful.");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(ConnectionPro.class.getName()).log(Level.SEVERE, null, e);
            throw new SQLException("Failed to connect to the database.", e);
        }
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                Logger.getLogger(ConnectionPro.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}

