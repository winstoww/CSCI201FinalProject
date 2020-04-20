import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class SkillParallel extends RecursiveTask<Double> {
	protected Profile curr;
	protected Profile other;
	
	public SkillParallel(Profile curr, Profile other)
	{
		this.curr=curr;
		this.other = other;
	}
	
	public Double compute()
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
}
