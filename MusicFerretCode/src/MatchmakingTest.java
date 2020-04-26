import java.util.ArrayList;

public class MatchmakingTest {
	public static void main(String []args)
	{
		same_instrument_test();
	}
	
	
	
	public static void location_test()
	{
		ArrayList<String> instruments= new ArrayList<String>();
		ArrayList<String> instruments2= new ArrayList<String>();
		ArrayList<Integer> genres= new ArrayList<Integer>();
		ArrayList<Integer> skill= new ArrayList<Integer>();
		instruments.add("guitar");
		instruments2.add("piano");
		skill.add(new Integer(5));
		for(int i=0; i<10; i++)
		{
			genres.add(new Integer(i+1));
		}
		Profile usc= new Profile(1,"usc", "trojan", "@usc.edu", new Location(34.0224,118.2851), instruments, genres, skill, true );
		Profile ucla= new Profile(2,"ucla", "bruin", "@ucla.edu", new Location(34.0689,118.4452), instruments2, genres, skill, true );
		Profile berkeley= new Profile(3,"berkeley", "bears", "@berkeley.edu", new Location(37.8715,122.2730), instruments2, genres, skill, true );
		ArrayList<Profile> matches= new ArrayList<Profile>();
		matches.add(ucla);
		matches.add(berkeley);
		ArrayList<Profile> scores= usc.matchmake(matches);
		System.out.println(scores);
//		System.out.println("ucla score= " + scores.get(0));
//		System.out.println("berkeley score= " + scores.get(1));
		return;
	}
	
	public static void genre_test()
	{
		ArrayList<String> instruments= new ArrayList<String>();
		ArrayList<String> instruments2= new ArrayList<String>();
		ArrayList<Integer> genres= new ArrayList<Integer>();
		ArrayList<Integer> genres2= new ArrayList<Integer>();
		ArrayList<Integer> skill= new ArrayList<Integer>();
		instruments.add("guitar");
		instruments2.add("piano");
		skill.add(new Integer(5));
		for(int i=0; i<10; i++)
		{
			genres.add(new Integer(i+1));
			genres2.add(new Integer(11 - (i+1)) );
		}
		Profile target= new Profile(1,"usc", "trojan", "@usc.edu", new Location(34.0224,118.2851), instruments, genres, skill, true );
		Profile better= new Profile(2,"ucla", "bruin", "@ucla.edu", new Location(34.0224,118.2851), instruments2, genres, skill, true );
		Profile worse= new Profile(3,"berkeley", "bears", "@berkeley.edu", new Location(34.0224,118.2851), instruments2, genres2, skill, true );
		ArrayList<Profile> matches= new ArrayList<Profile>();
		matches.add(better);
		matches.add(worse);
		
		ArrayList<Profile> scores=target.matchmake(matches);
		System.out.println(scores);
//		System.out.println("better score= " + scores.get(0));
//		System.out.println("worse score= " + scores.get(1));
		return;
	}
	
	public static void high_skill_test()
	{
		ArrayList<String> instruments= new ArrayList<String>();
		ArrayList<String> instruments2= new ArrayList<String>();
		ArrayList<String> instruments3= new ArrayList<String>();
		ArrayList<Integer> genres= new ArrayList<Integer>();
		ArrayList<Integer> skill= new ArrayList<Integer>();
		ArrayList<Integer> skill2= new ArrayList<Integer>();
		instruments.add("guitar");
		instruments2.add("piano");
		instruments3.add("drums");
		skill.add(new Integer(10));
		skill2.add(new Integer(1));
		for(int i=0; i<10; i++)
		{
			genres.add(new Integer(i+1));
		}
		Profile target= new Profile(1,"usc", "trojan", "@usc.edu", new Location(34.0224,118.2851), instruments, genres, skill, true );
		Profile better= new Profile(2,"ucla", "bruin", "@ucla.edu", new Location(34.0224,118.2851), instruments2, genres, skill, true );
		Profile worse= new Profile(3,"berkeley", "bears", "@berkeley.edu", new Location(34.0224,118.2851), instruments3, genres, skill2, true );
		ArrayList<Profile> matches= new ArrayList<Profile>();
		matches.add(better);
		matches.add(worse);
	
		ArrayList<Profile> scores=target.matchmake(matches);
		System.out.println(scores);
//		System.out.println("better score= " + scores.get(0));
//		System.out.println("worse score= " + scores.get(1));
		return;
	}
	
	public static void low_skill_test()
	{
		ArrayList<String> instruments= new ArrayList<String>();
		ArrayList<String> instruments2= new ArrayList<String>();
		ArrayList<String> instruments3= new ArrayList<String>();
		ArrayList<Integer> genres= new ArrayList<Integer>();
		ArrayList<Integer> skill= new ArrayList<Integer>();
		ArrayList<Integer> skill2= new ArrayList<Integer>();
		instruments.add("guitar");
		instruments2.add("piano");
		instruments3.add("drums");
		skill.add(new Integer(10));
		skill2.add(new Integer(1));
		for(int i=0; i<10; i++)
		{
			genres.add(new Integer(i+1));
		}
		Profile target= new Profile(1,"usc", "trojan", "@usc.edu", new Location(34.0224,118.2851), instruments, genres, skill2, true );
		Profile better= new Profile(2,"ucla", "bruin", "@ucla.edu", new Location(34.0224,118.2851), instruments2, genres, skill2, true );
		Profile worse= new Profile(3,"berkeley", "bears", "@berkeley.edu", new Location(34.0224,118.2851), instruments3, genres, skill, true );
		ArrayList<Profile> matches= new ArrayList<Profile>();
		matches.add(better);
		matches.add(worse);
		ArrayList<Profile> scores=target.matchmake(matches);
		System.out.println(scores);
//		System.out.println("better score= " + scores.get(0));
//		System.out.println("worse score= " + scores.get(1));
		return;
	}
	
	public static void same_instrument_test()
	{
		ArrayList<String> instruments= new ArrayList<String>();
		ArrayList<String> instruments2= new ArrayList<String>();
		
		ArrayList<Integer> genres= new ArrayList<Integer>();
		ArrayList<Integer> skill= new ArrayList<Integer>();

		instruments.add("guitar");
		instruments2.add("piano");
		skill.add(new Integer(10));
		for(int i=0; i<10; i++)
		{
			genres.add(new Integer(i+1));
		}
		Profile target= new Profile(1,"usc", "trojan", "@usc.edu", new Location(34.0224,118.2851), instruments, genres, skill, true );
		Profile better= new Profile(2,"ucla", "bruin", "@ucla.edu", new Location(34.0224,118.2851), instruments2, genres, skill, true );
		Profile worse= new Profile(3,"berkeley", "bears", "@berkeley.edu", new Location(34.0224,118.2851), instruments, genres, skill, true );
		ArrayList<Profile> matches= new ArrayList<Profile>();
		matches.add(better);
		matches.add(worse);
		ArrayList<Profile> scores=target.matchmake(matches);
		System.out.println(scores);
//		System.out.println("better score= " + scores.get(0));
//		System.out.println("worse score= " + scores.get(1));
		return;
	}
}
	

