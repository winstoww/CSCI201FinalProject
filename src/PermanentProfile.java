import java.util.ArrayList;

public class PermanentProfile extends Profile{

	public PermanentProfile(String name, String password, String email, Location Location,
			ArrayList<String> instruments, ArrayList<Integer> genres, ArrayList<Integer> skill, boolean isPermanent) {
		super(name, password, email, Location, instruments, genres, skill, isPermanent);
		// TODO Auto-generated constructor stub
	}
	
	private void save()
	{
		//need to implement. Must save data to database
	}

}
