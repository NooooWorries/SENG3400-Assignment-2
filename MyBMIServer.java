 /*
 * Author: Zixin CHENG
 * Course: SENG3400
 * Student number: 3218124
 */

public class MyBMIServer
{
	public static String calcBMI(String weight, String height)
	{
		// Return the result
		return MyBMIRange.getResult(weight, height); 
	}
	
	public static String listRanges()
	{
		// Return all ranges
		return MyBMIRange.listRanges();
	}
	
	public static String listWeights (String height)
	{
		// Return the ideal weigth range
		return MyBMIRange.listWeights(height);
	}
}