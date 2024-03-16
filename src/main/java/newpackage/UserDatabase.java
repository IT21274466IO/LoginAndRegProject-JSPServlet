/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

import java.sql.*;

/**
 *
 * @author imantha_o
 */

public class UserDatabase {
    
    public Connection con;
    
    // Constructor to initialize the connection
    public UserDatabase(Connection con){
        if (con == null) {
            throw new IllegalArgumentException("Connection cannot be null");
        }
        this.con = con;
    }
    
    // Method to save user data
    public boolean saveUser(User user) {
        boolean set = false;
        
        try {
            // Insert user data on Registration
            String query = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
            
            // Using try-with-resources to automatically close PreparedStatement
            try (PreparedStatement pt = this.con.prepareStatement(query)) {
                pt.setString(1, user.getName());
                pt.setString(2, user.getEmail());
                pt.setString(3, user.getPassword());
                int rowsAffected = pt.executeUpdate();
                // Check if any rows were affected by the query
                if (rowsAffected > 0) {
                    set = true;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return set;
    }
    
    // User login
    public User logUser(String email, String pass){
        User user = null;
        
        try{
            String query = "SELECT * FROM users WHERE email=? AND password=?";
            
            try (PreparedStatement pst = this.con.prepareStatement(query)) {
                pst.setString(1, email);
                pst.setString(2, pass);
                
                try (ResultSet rs = pst.executeQuery()) {
                    if(rs.next()) {
                        user = new User();
                        user.setId(rs.getInt("id"));
                        user.setEmail(rs.getString("email"));
                        user.setName(rs.getString("name"));
                        user.setPassword(rs.getString("password"));
                    }
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
    
    public boolean verifyPassword(int userId, String password) throws SQLException {
        // Implement logic to verify password against the database
        // Here's an example (replace with your actual implementation):
        String query = "SELECT * FROM users WHERE id=? and password=?";
        try (PreparedStatement pst = this.con.prepareStatement(query)) {
          pst.setInt(1, userId);
          pst.setString(2, password);

          ResultSet rs = pst.executeQuery();
          return rs.next(); // Return true if a user is found with the matching ID and password
        }
  }

    
    // Method to update user data
    public boolean updateUser(User user) {
        boolean updated = false;

        try {
            // Update user data
            String query = "UPDATE users SET name=?, email=?, password=? WHERE id=?";

            // Using try-with-resources to automatically close PreparedStatement
            try (PreparedStatement pt = this.con.prepareStatement(query)) {
                pt.setString(1, user.getName());
                pt.setString(2, user.getEmail());
                pt.setString(3, user.getPassword());
                pt.setInt(4, user.getId());

                int rowsAffected = pt.executeUpdate();
                // Check if any rows were affected by the query
                if (rowsAffected > 0) {
                    updated = true;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    // Method to delete user data
    public boolean deleteUser(int userId) {
        boolean deleted = false;

        try {
            // Delete user data
            String query = "DELETE FROM users WHERE id=?";

            // Using try-with-resources to automatically close PreparedStatement
            try (PreparedStatement pt = this.con.prepareStatement(query)) {
                pt.setInt(1, userId);

                int rowsAffected = pt.executeUpdate();
                // Check if any rows were affected by the query
                if (rowsAffected > 0) {
                    deleted = true;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }
}
