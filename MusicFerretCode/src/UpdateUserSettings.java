import java.sql.*;
 
public class UserSettings {
 
    public Profile updateName(String email, String password, String newName) throws SQLException, ClassNotFoundException { //update name and return updated user profile
        String dbURL = "jdbc:mysql://localhost:3306/My201SQL"; //"My201SQL"-> the name of your SQL connection in MySQL Workbench
        String dbUser = "root";
        String dbPassword = "password"; //"password"-> your saved password
 
        Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        String sql = "UPDATE User SET name=? WHERE email=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newName);
        statement.setString(2, email);
        
        int rowsUpdated = statement.executeUpdate(); 
        
        Profile user = null;
        
        if (rowsUpdated > 0) { //name was updated
            String sql = "SELECT * FROM User WHERE email = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                user = new Profile(result.getString("name"), password, email, ***Location Location***, ***ArrayList<String> instruments***, //not sure how to store this info in database yet
                                      ***ArrayList<Integer> genres***, ***ArrayList<Integer> skill***, ***boolean isPermanent***); 
            }
        }
 
        connection.close();
 
        return user; //if user returns null then couldn't find in database -> incorrect email/password
    }
}
