import java.sql.*;
 
public class DBGetUserByEmail {
 
    public static Profile getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        String dbURL = "jdbc:mysql://localhost/UserDatabase"; 
        String dbUser = "root";
        String dbPassword = "password"; //"password"-> your saved password
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
	    
	      Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        String sql = "SELECT * FROM Profile WHERE email = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        result = statement.executeQuery();
 
        Profile user = null;
 
        if (result.next()) {
            Location location = new Location(result.getString("latitude"), result.getString("longitude"));

            PreparedStatement statement2 = null;
            ResultSet result2 = null;
            String sql2 = "SELECT * FROM Instruments WHERE profileID = ?";
      	    statement2 = connection.prepareStatement(sql2);
            statement2.setInt(1, result.getInt("profileID"));
            result2 = statement2.executeQuery();
		
	          ArrayList<String> instruments = new ArrayList<String>();
            ArrayList<Integer> skill = new ArrayList<Integer>();
            while (result2.next()) {
                instruments.add(result2.getString("instrument"));
                skill.add(result2.getString("skill"));
            }
	    
            PreparedStatement statement3 = null;
            ResultSet result3 = null;
            String sql3 = "SELECT * FROM Genres WHERE profileID = ?";
      	    statement3 = connection.prepareStatement(sql3);
            statement3.setInt(1, result.getInt("profileID"));
            result3 = statement3.executeQuery();
		
	          ArrayList<Integer> genres = new ArrayList<Integer>();
            while (result3.next()) {
                genres.add(result3.getInt("genreRating"));
            }
		
            user = new Profile(result.getInt("profileID"), result.getString("name"), result.getString("password"), result.getString("email"), 
                        location, instruments, genres, skill, true); 
            
	    
            try {
    	           if (result3 != null) {
    		             result3.close();
                 }
                 if (statement3 != null) {
                     statement3.close();
                 }
            } catch (SQLException sqle) {
                 System.out.println(sqle.getMessage());
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
    	      if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
 
        return user; //if user returns null then couldn't find in database -> incorrect email
    }
}
