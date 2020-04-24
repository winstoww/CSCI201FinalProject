import java.sql.*;
 
public class DBDeleteUser {
 
    public void removeUser(String email) throws SQLException, ClassNotFoundException { //delete user from database                                                                                              
        String dbURL = "jdbc:mysql://localhost/UserDatabase"; 
        String dbUser = "root";
        String dbPassword = "password"; //"password"-> your saved password
        Connection connection = null;
 	      PreparedStatement statement = null;
 
        connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        String sql = "DELETE FROM User WHERE email=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, email);
          
        int rowsDeleted = statement.executeUpdate(); 
        
        if (rowsDeleted  > 0) { //user was deleted
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
     }
}
