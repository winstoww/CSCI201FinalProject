import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class Profile {
	protected String name;
	protected String password;
	protected String email;
	protected int profileID;
	protected Location Location;//stores coordinates
	protected ArrayList<String> instruments;//stores the name of the instruments
	protected ArrayList<Integer> genres;//stores the integer representing the amount of interest the user 
	//has on the list of genres we provide to them
	protected ArrayList<Integer> skill;// stores the integer representing the skill level associated with each instrument
	protected boolean isPermanent;//tells us if the account is permanent or temporary
	
	public Profile(int profileID, String name,String password, String email, Location Location,ArrayList<String> instruments,
			ArrayList<Integer> genres,ArrayList<Integer> skill, boolean isPermanent)
	{
		this.profileID = profileID;
		this.name=name;
		this.password=password;
		this.email= email;
		this.Location= Location;
		this.instruments=instruments;
		this.genres=genres;
		this.skill=skill;
		this.isPermanent=isPermanent;
	}
	
	public ArrayList<Profile> matchmake(ArrayList<Profile> profiles)
	{
		ArrayList<Double> results =new ArrayList<Double>(); // stores the compatability score for each profile that this user can match up with
		for(int i=0; i<profiles.size(); i++)
		{
			results.add(new Double(0));// initializing scores to 0
		}
		
		for(int i=0; i<profiles.size(); i++)// each loop generates the score for one pair of profiles
		{

//			Double loc_score= location_score(this, profiles.get(i));//getting location score
//			Double genre_score= genre_score(this, profiles.get(i));//getting genre score
//			Double skill_score= skill_score(this, profiles.get(i));//getting skill level score
			ForkJoinPool pool = new ForkJoinPool();
			LocationParrallel loc= new LocationParrallel(this,profiles.get(i));
			GenreParrallel genre= new GenreParrallel(this,profiles.get(i));
			SkillParallel skill= new SkillParallel(this,profiles.get(i));
		
			pool.execute(loc);
			pool.execute(genre);
			pool.execute(skill);
			Double loc_score= loc.join();
			Double genre_score= genre.join();
			Double skill_score=skill.join();
			if(skill_score!=-1)
			{
				results.set(i, loc_score+ genre_score+ skill_score);//setting the score in the results array for normal case
			}
			else
			{
				results.set(i, skill_score); // case when the two users play one instruments and it is the same instruments, they should not
				//be matched. giving score of -1 to denote that they should not match
			}
		}
		if(results.size()==1 && results.get(0)== -1)
		{
			return null;// if their exists only one other profile and they play only on instrument (the same one as you), then the profile doesnt gets matched
		}
		ArrayList<Profile> matches =new ArrayList<Profile>();
		ArrayList<Integer> indexes= new ArrayList<Integer>();
		
		
		for(int i=0; i<4; i++)
		{
			if(i>= results.size())
			{
				break;
			}
			Double max= new Double(Double.MIN_VALUE);
			Integer index = -1;
			for(int j=0; j<profiles.size(); j++)
			{
				if(matches.contains(profiles.get(j)) == false)
				{
					if(results.get(j) <0)
					{
						continue;
					}
					if(results.get(j) > max )
					{
						index=j;
						max= results.get(j);
					}
				}
			}
			if(index ==-1)
			{
				break;
			}
			matches.add(profiles.get(index));
		}
		
		if(matches.size()==0)
		{
			return null;
		}
		return matches;
		//return results;//otherwise return matches
	}
	
	private Double location_score(Profile curr, Profile other)
	{
		Double distance= distance(curr.getLocation().getLatitude(), other.getLocation().getLatitude(), 
				curr.getLocation().getLongitude(), other.getLocation().getLongitude());// getting distance
		//using a transformation function to convert distances to scores between 0 and 1
	    Double score= new Double(Math.atan(0.005*distance)/(Math.PI/2));
	    score=distance/(0.005+distance);
	    score=Math.exp(-0.005*distance);
	    System.out.println(other.getName() + "'s location score= "+ score);
		return score;
	}
	
	private Double genre_score(Profile curr, Profile other)
	{
		ArrayList<Integer> curr_genres= curr.getGenres();
		ArrayList<Integer> other_genres= other.getGenres();
//		Integer min_index_curr=-1;
//		Integer min_index_other=-1;
		Double min_score= Double.MAX_VALUE;// will store the value corresponding to most liked genre by the two profiles(an average)
		// low values correspond to the user liking the genre more so will be looking for the min value
		for(int i=0; i<curr_genres.size();i++)
		{
			Double temp_score= Math.abs((double) ((curr_genres.get(i) + other_genres.get(i))/2.0) );// getting the average score for each genre
			if(temp_score< min_score)
			{
				min_score= temp_score;// if this score is bettter (lower) than the min, we have found a new min (i.e a new best liked genre)
			}
		}
		;
		
		// using transformations to convert scores to between a range of 0-1
		min_score *=2;
		min_score -=1;
		
		
		Double OldValue= min_score;
		Double OldMin= new Double(1);
	    Double NewMin= new Double(0);
	    Double OldRange = (new Double(10) - new Double(1))  ;
		Double NewRange = (new Double(1) - new Double(0))  ;
		Double score= ( 1-  (((OldValue - OldMin) * NewRange) / OldRange) + NewMin);
		System.out.println(other.getName() + "'s genre score= "+ score);
		return score;
		
	}
	private Double skill_score(Profile curr, Profile other)
	{
		Double final_score= new Double(0);//stores final score
		Double min_difference= Double.MAX_VALUE; // will store the instrument pair where the skill difference is the least
		ArrayList<String> curr_instruments= curr.getInstruments();
		ArrayList<String> other_instruments= other.getInstruments();
		ArrayList<Integer> curr_skills= curr.getSkill();
		ArrayList<Integer> other_skills= other.getSkill();
		
		if(curr_instruments.size()==1 && other_instruments.size()==1 && curr_instruments.get(0).equals(other_instruments.get(0)))
		{//case when the two profiles only play one instrument and its the same instrument, we do not want them to match. So i give a score
			//of -1 to denote this
			final_score -= 1;
			System.out.println(other.getName() + "'s skill score= "+ final_score);
			return final_score;
		}
		//otherwise go through all possible instrument combinations to see which pair of instruments have the least skill gap
		for(int i=0; i<curr_instruments.size(); i++)
		{
			for(int j=0; j<other_instruments.size();j++)
			{
				if(curr_instruments.get(i)!= other_instruments.get(j))// ignoring same instrument combinations 
				{
					Double temp_diff=  Math.abs((double) (curr_skills.get(i) - other_skills.get(j))); // getting the skill gap
					if(temp_diff < min_difference) // updating min skill gap if necessary
					{
						min_difference= temp_diff;
					}
				}
			}
		}
		
		// using transformations to convert scores to between a range of 0-1
		min_difference+=1;
		Double OldValue= min_difference;
		Double OldMin= new Double(1);
	    Double NewMin= new Double(0);
	    Double OldRange = (new Double(10) - new Double(1))  ;
		Double NewRange = (new Double(1) - new Double(0))  ;
		final_score= ( 1-  (((OldValue - OldMin) * NewRange) / OldRange) + NewMin);
		System.out.println(other.getName() + "'s skill score= "+ final_score);
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

	public ArrayList<Integer> getGenres() {
		return genres;
	}

	public ArrayList<Integer> getSkill() {
		return skill;
	}

	public boolean isPermanent() {
		return isPermanent;
	}
	
	public String toString()
	{
		String output="";
		output+= "name = "+ getName() + "\n";
		output+= "password = "+ getPassword() + "\n";
		output+= "email = "+ getEmail() + "\n";
		output += "latitude= " + getLocation().getLatitude() + " longitude= " + getLocation().getLongitude() + "\n";
		ArrayList<String> instruments = getInstruments();
		for(int i=0; i<instruments.size(); i++)
		{
			if(i!=0)
			{
				output+= " and "+ instruments.get(i);
			}
			else
			{
				output+= "instruments = "+ instruments.get(i);
			}
		}
		output += "\n";
		ArrayList<Integer> skills = getSkill();
		for(int i=0; i<skills.size(); i++)
		{
			if(i!=0)
			{
				output+= " and "+ skills.get(i);
			}
			else
			{
				output+= "skills = "+ skills.get(i);
			}
		}
		output += "\n";
		ArrayList<Integer> genres = getGenres();
		for(int i=0; i<genres.size(); i++)
		{
			if(i!=0)
			{
				output+= " and "+ genres.get(i);
			}
			else
			{
				output+= "genres = "+ genres.get(i);
			}
		}
		output += "\n";
		if(isPermanent()== true)
		{
			output+= "account = permanent";
		}
		else
		{
			output += "account = temporary";
		}
		output+="\n";
		return output;
	}
