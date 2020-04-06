import java.text.DecimalFormat;
import java.util.ArrayList;

public class Profile {
	private String name;
	private String password;
	private String email;
	private Location Location;
	private ArrayList<String> instruments;
	private ArrayList<Double> genres;
	private ArrayList<Integer> skill;
	private boolean isPermanent;
	
	public Profile(String name,String password, String email, Location Location,ArrayList<String> instruments,
			ArrayList<Double> genres,ArrayList<Integer> skill, boolean isPermanent)
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
	
	public ArrayList<Double> matchmake(ArrayList<Profile> profiles)
	{
		ArrayList<Double> results =new ArrayList<Double>();
		for(int i=0; i<profiles.size(); i++)
		{
			results.add(new Double(0));
		}
		
		for(int i=0; i<profiles.size(); i++)
		{

			Double loc_score= location_score(this, profiles.get(i));
			Double genre_score= genre_score(this, profiles.get(i));
			Double skill_score= skill_score(this, profiles.get(i));
			if(skill_score!=-1)
			{
				results.set(i, loc_score+ genre_score+ skill_score);
			}
			else
			{
				results.set(i, skill_score);
			}
		}
		if(results.size()==1 && results.get(0)== -1)
		{
			return null;
		}
		return results;
	}
	
	private Double location_score(Profile curr, Profile other)
	{
		Double distance= distance(curr.getLocation().getLatitude(), other.getLocation().getLatitude(), 
				curr.getLocation().getLongitude(), other.getLocation().getLongitude());
	    Double score= new Double(Math.atan(0.005*distance)/(Math.PI/2));
	    score=distance/(0.005+distance);
	    score=Math.exp(-0.005*distance);

		return score;
	}
	
	private Double genre_score(Profile curr, Profile other)
	{
		ArrayList<Double> curr_genres= curr.getGenres();
		ArrayList<Double> other_genres= other.getGenres();
//		Integer min_index_curr=-1;
//		Integer min_index_other=-1;
		Double min_score= Double.MAX_VALUE;
		for(int i=0; i<curr_genres.size();i++)
		{
			for(int j=0; j<other_genres.size();j++)
			{
				Double temp_score= (curr_genres.get(i) + other_genres.get(j))/2;
				if(temp_score< min_score)
				{
					min_score= temp_score;
//					min_index_curr= i;
//					min_index_other= j;
				}
				
			}
		}
		min_score *=2;
		min_score -=1;
		
		
		Double OldValue= min_score;
		Double OldMin= new Double(1);
	    Double NewMin= new Double(0);
	    Double OldRange = (new Double(10) - new Double(1))  ;
		Double NewRange = (new Double(1) - new Double(0))  ;
		Double score= ( 1-  (((OldValue - OldMin) * NewRange) / OldRange) + NewMin);
		return score;
		
	}
	private Double skill_score(Profile curr, Profile other)
	{
		Double final_score= new Double(0);
		Double min_difference= Double.MAX_VALUE;
		ArrayList<String> curr_instruments= curr.getInstruments();
		ArrayList<String> other_instruments= other.getInstruments();
		ArrayList<Integer> curr_skills= curr.getSkill();
		ArrayList<Integer> other_skills= other.getSkill();
		
		if(curr_instruments.size()==1 && other_instruments.size()==1 && curr_instruments.get(0).equals(other_instruments.get(0)))
		{
			final_score -= 1;
			return final_score;
		}
		
		for(int i=0; i<curr_instruments.size(); i++)
		{
			for(int j=0; j<other_instruments.size();i++)
			{
				if(curr_instruments.get(i)!= other_instruments.get(j))
				{
					Double temp_diff=  (double) ((curr_skills.get(i) + other_skills.get(j)) /2);
					if(temp_diff < min_difference)
					{
						min_difference= temp_diff;
					}
				}
			}
		}
		
		min_difference+=1;
		Double OldValue= min_difference;
		Double OldMin= new Double(1);
	    Double NewMin= new Double(0);
	    Double OldRange = (new Double(10) - new Double(1))  ;
		Double NewRange = (new Double(1) - new Double(0))  ;
		final_score= ( 1-  (((OldValue - OldMin) * NewRange) / OldRange) + NewMin);
		
		return final_score;
	}
	
	//Formula to calculate distance between current location and destination
	public static double distance(double lat1, double  lat2, double long1, double long2)
	{
		double total = 3963.0 * Math.acos(
				Math.sin(Math.toRadians(lat1))*Math.sin(Math.toRadians(lat2)) +
				Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2))*Math.cos(Math.toRadians(long2 - long1))
			);
	    DecimalFormat df2 = new DecimalFormat("#.#");
		return Double.parseDouble(df2.format(total));
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

	public ArrayList<Double> getGenres() {
		return genres;
	}

	public ArrayList<Integer> getSkill() {
		return skill;
	}

	public boolean isPermanent() {
		return isPermanent;
	}
}
