import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class GenreParrallel extends RecursiveTask<Double>{
	protected Profile curr;
	protected Profile other;
	public GenreParrallel(Profile curr, Profile other)
	{
		this.curr=curr;
		this.other = other;
	}
	
	public Double compute()
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
}
