import java.sql.*;
 
public class DBCreateUser {
 
    public Profile createNewUser(String email, String password, String name, double latitude, double longitude) throws SQLException, ClassNotFoundException { //create new user and return user profile
                                                                                                                                //add more into constructor like location, isPermanent, instruments, etc
        String dbURL = "jdbc:mysql://localhost/UserDatabase"; 
        String dbUser = "root";
        String dbPassword = "password"; //"password"-> your saved password
        Connection connection = null;
 	      PreparedStatement statement = null;
     
        connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        String sql = "INSERT INTO User (name, password, email, latitude, longitude) VALUES (?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        statement.setString(3, name);
        statement.setString(4, latitude);
        statement.setString(4, longitude);
          
        int rowsInserted = statement.executeUpdate(); 
        
        Profile user = null;
        
        if (rowsInserted > 0) { //new user was created
            String sql = "SELECT * FROM User WHERE email = ? and password = ?";
            PreparedStatement statement2 = null;
            ResultSet result2 = null;
         
            statement2 = connection.prepareStatement(sql);
            statement2.setString(1, email);
            statement2.setString(2, password);

            result2 = statement2.executeQuery();
            
            if (result2.next()) {
                Location location = new Location(latitude, longitude);
                user = new Profile(result.getString("profileID"), result.getString("name"), result.getString("password"), result.getString("email"), location, null, //null or empty arraylist
                                      null, null, true); 
            }
             
            try {
                if (result2 != null) {
                   result2.close();
                }
                if (statement2 != null) {
                   statement2.close();
                }
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
            }
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
 
        return user; //if user returns null then couldn't find in database -> incorrect email/password
    }
}
