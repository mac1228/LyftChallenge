import java.util.*;
public class LyftChallenge {

	public static void main(String[] args) {
		double[] points = new double[8];
		Scanner input = new Scanner(System.in);
		String var = "longitude";
		char point = 'a';
		for(int i = 0; i < points.length; i++){
			System.out.println("Enter " + var + " coordinate for point " + point);
			points[i] = input.nextDouble();
			var = var.equals("longitude") ? "latitude" : "longitude";
			point = i % 2 == 0 ? point : (char)(point+1);
		}
		detourDistances(points);

	}
	public static void detourDistances(double[]points){
		double a2c = calcDistance(points[0],points[1],points[4],points[5]);
		double c2d = calcDistance(points[4],points[5],points[6],points[7]);
		double d2b = calcDistance(points[6],points[7],points[2],points[3]);
		double a2b = calcDistance(points[0],points[1],points[2],points[3]);
		
		//detour for driver 1 a->c->d->b
		double detour1 = a2c + c2d + d2b;
		//detour for driver 2 c->a->b->d
		double detour2 = a2c + a2b + d2b;
		
		System.out.println("Driver 1 detour distance: " + detour1 + "\nDriver 2 detour distance: " + detour2);
		if(detour1 < detour2){
			System.out.println("Driver 1 has shorter detour distance");
		}
		else{
			System.out.println("Driver 2 has shorter detour distance");
		}
	}
	
	/*
	 *  haversine formula to calculate the great-circle distance between two points
	 */
	public static double calcDistance(double lon1, double lat1, double lon2, double lat2){
		double R = 6371; // km
		double dLat = Math.toRadians(lat2-lat1);
		double dLon = Math.toRadians(lon2-lon1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		        Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double d = R * c;
		return d;
	}
}
