import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
	String[] allGenres = {"Pop", "Rock", "Jazz", "Country", "Folk", "Blues", "Hip Hop", "Other"};
	
	public void createProfile(Profile profile) {
		
		Integer id = profile.getID();
		String name = profile.getName();
		String password = profile.getPassword();
		String email = profile.getEmail();
		Location loc = profile.getLocation();
		Double latitude = loc.getLatitude();
		Double longitude = loc.getLongitude();
		ArrayList<String> instruments = profile.getInstruments();
		ArrayList<Integer> genres = profile.getGenres();
		ArrayList<Integer> skill = profile.getSkill();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		PreparedStatement statement = null;
		Connection connection = null;
		Statement st = null;
		
		String sql = "INSERT INTO profile (profileID, name, password, email, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/ferret?user=root&password=root");
			statement = connection.prepareStatement(sql);
			statement.setString(1,  id.toString());
	        statement.setString(2, email);
	        statement.setString(3, password);
	        statement.setString(4, name);
	        statement.setString(5, latitude.toString());
	        statement.setString(6, longitude.toString());
			statement.executeUpdate();
			
			sql = "INSERT INTO instrument_skill (profileID, instrumentName, skill) VALUES (?, ?, ?)";
			statement = connection.prepareStatement(sql);
			
			for(int i = 0; i < instruments.size(); i++) {
				statement.setString(1, id.toString());
			    statement.setString(2, instruments.get(i));
			    statement.setString(3, skill.get(i).toString());
			    statement.executeUpdate();
			}
			
			sql = "INSERT INTO genre_rate (profileID, genreName, genreRating) VALUES (?, ?, ?)";
			statement = connection.prepareStatement(sql);
			
			for(int i = 0; i < allGenres.length; i++) {
				statement.setString(1, id.toString());
			    statement.setString(2, allGenres[i]);
			    statement.setString(3, genres.get(i).toString());
			    statement.executeUpdate();
			}
			
		} catch(SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
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

