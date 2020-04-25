import java.sql.*;
 
public class DBUserLogin {
 
    public static Profile tryLogin(String email, String password) throws SQLException, ClassNotFoundException {
        String dbURL = "jdbc:mysql://localhost/UserDatabase"; 
        String dbUser = "root";
        String dbPassword = "password"; //"password"-> your saved password
	Connection connection = null;
 	PreparedStatement statement = null;
	ResultSet result = null;
	    
	Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        String sql = "SELECT * FROM Profile WHERE email = ? and password = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
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
  	    while (result2.next()) {
		instruments.add(result2.getString("instrument"));
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
		
	    PreparedStatement statement4 = null;
	    ResultSet result4 = null;
	    String sql4 = "SELECT * FROM Skills WHERE profileID = ?"; //instrumentID not profileID - not sure how to store this in arraylist<integer>
      	    statement4 = connection.prepareStatement(sql4);
            statement4.setInt(1, result.getInt("profileID"));
            result4 = statement4.executeQuery();
		
	    ArrayList<Integer> skill = new ArrayList<Integer>(); //integer?
  	    while (result4.next()) {
		//skill.add(result4.getInt("skill"));
	    }
		
            user = new Profile(result.getInt("profileID"), result.getString("name"), result.getString("password"), result.getString("email"), location, instruments,
	    	                            genres, skill, true); 
            
	    try {
    	        if (result4 != null) {
    		    result4.close();
    	        }
    	        if (statement4 != null) {
    		    statement4.close();
    	        }
    	    } catch (SQLException sqle) {
    	        System.out.println(sqle.getMessage());
    	    }
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
 
        return user; //if user returns null then couldn't find in database -> incorrect email/password
    }
}
