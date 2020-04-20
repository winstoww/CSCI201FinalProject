import java.text.DecimalFormat;
import java.util.concurrent.RecursiveTask;

public class LocationParrallel extends RecursiveTask<Double> {
	protected Profile curr;
	protected Profile other;
	
	public LocationParrallel(Profile curr, Profile other)
	{
		
		this.curr=curr;
		this.other = other;
	}
	
	protected Double compute()
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
