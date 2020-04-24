import java.sql.*;
 
public class DBUpdateUserSettings {
 
    public Profile updateName(String email, String password, String newName) throws SQLException, ClassNotFoundException { //update name and return updated user profile
        String dbURL = "jdbc:mysql://localhost/UserDatabase"; 
        String dbUser = "root";
        String dbPassword = "password"; //"password"-> your saved password
        Connection connection = null;
 	      PreparedStatement statement = null;
     
        connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        String sql = "UPDATE User SET name=? WHERE email=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, newName);
        statement.setString(2, email);
        
        int rowsUpdated = statement.executeUpdate(); 
        
        Profile user = null;
        
        if (rowsUpdated > 0) { //name was updated
            //user = session.getAttribute("user");
            //user.updateNameinProfile(newName); //add function in profile class to update name
        }
 
        try {
            if (statement != null) {
               statement.close();
            }
            if (connection != null) {
               connection.close();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
 
        return user; //if user returns null then didn't update name correctly
    }
 
     public Profile updateEmail(String email, String password, String newEmail) throws SQLException, ClassNotFoundException { //update email and return updated user profile
        String dbURL = "jdbc:mysql://localhost/UserDatabase"; 
        String dbUser = "root";
        String dbPassword = "password"; //"password"-> your saved password
        Connection connection = null;
 	      PreparedStatement statement = null;
 
        connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        String sql = "UPDATE User SET email=? WHERE email=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, newEmail);
        statement.setString(2, email);
        
        int rowsUpdated = statement.executeUpdate(); 
        
        Profile user = null;
        
        if (rowsUpdated > 0) { //email was updated
            //user = session.getAttribute("user");
            //user.updateEmailinProfile(newEmail); //add function in profile class to update email
        }
 
        try {
            if (statement != null) {
               statement.close();
            }
            if (connection != null) {
               connection.close();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
 
        return user; //if user returns null then didn't update email correctly
    }
 
     public Profile updatePassword(String email, String password, String newPassword) throws SQLException, ClassNotFoundException { //update password and return updated user profile
        String dbURL = "jdbc:mysql://localhost/UserDatabase"; 
        String dbUser = "root";
        String dbPassword = "password"; //"password"-> your saved password
        Connection connection = null;
 	      PreparedStatement statement = null;
 
        connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        String sql = "UPDATE User SET password=? WHERE email=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, newPassword);
        statement.setString(2, email);
        
        int rowsUpdated = statement.executeUpdate(); 
        
        Profile user = null;
        
        if (rowsUpdated > 0) { //password was updated
            //user = session.getAttribute("user");
            //user.updatePasswordinProfile(newPassword); //add function in profile class to update password
        }
 
        try {
            if (statement != null) {
               statement.close();
            }
            if (connection != null) {
               connection.close();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
 
        return user; //if user returns null then didn't update password correctly
    }
}
