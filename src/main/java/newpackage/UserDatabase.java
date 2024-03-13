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
    
private Connection con;
    
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
        
        try{
            // Insert user data on Registration
            String query = "INSERT INTO userstest(name, email, password) VALUES (?, ?, ?)";
            
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
    
    //user login
    public User logUser(String email, String pass){
        User user = null;
        
        try{
            
            String query = "SELECT * FROM users WHERE email=? and password=?";
            
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, pass);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail("email");
                user.setName("name");
                user.setPassword("password");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return user;
    }
}