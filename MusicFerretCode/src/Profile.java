import java.util.ArrayList;

public class Profile {
	private String name;
	private String password;
	private String email;
	private Location Location;
	private ArrayList<String> instruments;
	private ArrayList<String> genres;
	private ArrayList<Integer> skill;
	private boolean isPermanent;
	
	public Profile(String name,String password, String email, Location Location,ArrayList<String> instruments,
			ArrayList<String> genres,ArrayList<Integer> skill, boolean isPermanent)
	{
		this.name=name;
		this.password=password;
		this.email= email;
		this.Location= Location;
		this.instruments=instruments;
		this.genres=genres;
		this.skill=skill;
		this.isPermanent=isPermanent;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Location getLocation() {
		return Location;
	}

	public ArrayList<String> getInstruments() {
		return instruments;
	}

	public ArrayList<String> getGenres() {
		return genres;
	}

	public ArrayList<Integer> getSkill() {
		return skill;
	}

	public boolean isPermanent() {
		return isPermanent;
	}
}
