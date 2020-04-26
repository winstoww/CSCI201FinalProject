import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		String sql = "INSERT INTO profile (profileID, name, password, email, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/ferret?user=root&password=root");
			statement = connection.prepareStatement(sql);
			statement.setString(1,  id.toString());
	        statement.setString(2, name);
	        statement.setString(3, password);
	        statement.setString(4, email);
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
	
	
	public ArrayList<Profile> getAllProfiles(){
		
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		PreparedStatement statement = null;
		Connection connection = null;
        ResultSet result = null;
        
        String sql = "SELECT * FROM profile";
        try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/ferret?user=root&password=root");
			statement = connection.prepareStatement(sql);
		    result = statement.executeQuery();
		    while(result.next()) {
		    	Integer id = null;
				String name = null;
				String password = null;
				String email = null;
				Location loc = null;
				Double latitude = null;
				Double longitude = null;
				ArrayList<String> instruments = new ArrayList<String>();
				ArrayList<Integer> genres = new ArrayList<Integer>();
				ArrayList<Integer> skill = new ArrayList<Integer>();
				
		    	id = result.getInt("profileID");
		    	name = result.getString("name");
		    	password = result.getString("password");
		    	email = result.getString("email");
		    	latitude = result.getDouble("latitude");
		    	longitude = result.getDouble("longitude");
		    	loc = new Location(latitude, longitude);
		    	
		    	PreparedStatement instrumentStatement = null;
		        ResultSet instrumentResult = null;
		    	String instrument_sql = "SELECT * FROM instrument_skill WHERE profileID = ?";
		    	instrumentStatement = connection.prepareStatement(instrument_sql);
				instrumentStatement.setString(1,  id.toString());
				instrumentResult = instrumentStatement.executeQuery();
				while(instrumentResult.next()) {
					instruments.add(instrumentResult.getString("instrumentName"));
					skill.add(Integer.parseInt(instrumentResult.getString("skill")));
				}
				
				PreparedStatement genreStatement = null;
		        ResultSet genreResult = null;
		    	String genre_sql = "SELECT * FROM genre_rate WHERE profileID = ? AND genreName = ?";
		    	genreStatement = connection.prepareStatement(genre_sql);
		    	
		    	for(String genre : allGenres) {
		    		genreStatement.setString(1,  id.toString());
		    		genreStatement.setString(2,  genre);
		    		genreResult = genreStatement.executeQuery();
		    		if(genreResult.next()) {
		    			genres.add(Integer.parseInt(genreResult.getString("genreRating")));
		    		}
		    	}
		    	Profile user = new Profile(id, name, password, email, loc, instruments, genres, skill, true);
		    	profiles.add(user);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return profiles;
	}
	
	
	public Profile getProfileByEmail(String email) {
		
		Profile profile = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		PreparedStatement statement = null;
		Connection connection = null;
        ResultSet result = null;
        
        String sql = "SELECT * FROM profile WHERE email = ?";
        try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/ferret?user=root&password=root");
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
		    result = statement.executeQuery();
		    if(result.next()) {
		    	Integer id = null;
				String name = null;
				String password = null;
				Location loc = null;
				Double latitude = null;
				Double longitude = null;
				ArrayList<String> instruments = new ArrayList<String>();
				ArrayList<Integer> genres = new ArrayList<Integer>();
				ArrayList<Integer> skill = new ArrayList<Integer>();
				
		    	id = result.getInt("profileID");
		    	name = result.getString("name");
		    	password = result.getString("password");
		    	latitude = result.getDouble("latitude");
		    	longitude = result.getDouble("longitude");
		    	loc = new Location(latitude, longitude);
		    	
		    	PreparedStatement instrumentStatement = null;
		        ResultSet instrumentResult = null;
		    	String instrument_sql = "SELECT * FROM instrument_skill WHERE profileID = ?";
		    	instrumentStatement = connection.prepareStatement(instrument_sql);
				instrumentStatement.setString(1,  id.toString());
				instrumentResult = instrumentStatement.executeQuery();
				while(instrumentResult.next()) {
					instruments.add(instrumentResult.getString("instrumentName"));
					skill.add(Integer.parseInt(instrumentResult.getString("skill")));
				}
				
				PreparedStatement genreStatement = null;
		        ResultSet genreResult = null;
		    	String genre_sql = "SELECT * FROM genre_rate WHERE profileID = ? AND genreName = ?";
		    	genreStatement = connection.prepareStatement(genre_sql);
		    	
		    	for(String genre : allGenres) {
		    		genreStatement.setString(1,  id.toString());
		    		genreStatement.setString(2,  genre);
		    		genreResult = genreStatement.executeQuery();
		    		if(genreResult.next()) {
		    			genres.add(Integer.parseInt(genreResult.getString("genreRating")));
		    		}
		    	}
		    	profile = new Profile(id, name, password, email, loc, instruments, genres, skill, true);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return profile;
	}
}