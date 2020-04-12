import java.sql.*;
 
public class DeleteUser {
 
    public void removeUser(String email) throws SQLException, ClassNotFoundException { //delete user from database                                                                                              
        String dbURL = "jdbc:mysql://localhost:3306/My201SQL"; //"My201SQL"-> the name of your SQL connection in MySQL Workbench
        String dbUser = "root";
        String dbPassword = "password"; //"password"-> your saved password
 
        Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        String sql = "DELETE FROM User WHERE email=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
          
        int rowsDeleted = statement.executeUpdate(); 
        
        if (rowsDeleted  > 0) { //user was deleted
        }
 
        connection.close();
     }
}
