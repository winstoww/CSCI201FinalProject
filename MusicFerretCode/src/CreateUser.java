import java.sql.*;
 
public class CreateUser {
 
    public Profile createNewUser(String email, String password, String name, ***) throws SQLException, ClassNotFoundException { //create new user and return user profile
                                                                                                                                //add more into constructor like location, isPermanent, instruments, etc
        String dbURL = "jdbc:mysql://localhost:3306/My201SQL"; //"My201SQL"-> the name of your SQL connection in MySQL Workbench
        String dbUser = "root";
        String dbPassword = "password"; //"password"-> your saved password
 
        Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        String sql = "INSERT INTO User (email, password, name, ***) VALUES (?, ?, ?, ***)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        statement.setString(3, name);
        statement.setString(4, ***);
          
        int rowsInserted = statement.executeUpdate(); 
        
        Profile user = null;
        
        if (rowsInserted > 0) { //new user was created
            String sql = "SELECT * FROM User WHERE email = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                user = new Profile(result.getString("name"), result.getString("password"), result.getString("email"), ***Location Location***, ***ArrayList<String> instruments***, //not sure how to store this info in database yet
                                      ***ArrayList<Integer> genres***, ***ArrayList<Integer> skill***, ***boolean isPermanent***); 
            }
        }
 
        connection.close();
 
        return user; //if user returns null then couldn't find in database -> incorrect email/password
    }
}
