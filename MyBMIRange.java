/*
 * Author: Zixin CHENG
 * Course: SENG3400
 * Student number: 3218124
 */

import java.util.*;
import java.text.DecimalFormat;
public class MyBMIRange
{
	// Hardcode for username and password
	private static final String username = "admin";
	private static final String password = "bodymass";
	
	// Variable for call count
	private static int callCount = 0;
	
	// Lists to store data
	private static List<String> nameList = new ArrayList<String>();
	private static List<String> upperList = new ArrayList<String>();
	private static List<String> lowerList = new ArrayList<String>();
	private static List<String> isNormal = new ArrayList<String>(); // Normal or diserable		
	
	// Private function, validate username and password
	private static boolean validate (String user, String pwd)
	{
		boolean isValid = false;
		if (user.equals(username) && pwd.equals(password))
		    isValid = true;
		return isValid;
	}
	
	// Search the name of range and return the position
	private static int searchName(String searchName)
	{
		// Default position: -1
		int position = -1;
		
		// Length of the list 
		// The lengths for four lists must be same, so just get the length of one list
		int length = nameList.size();
		// Traverse all elements
		for (int i = 0; i < length; i ++)
		{
			// If name founded, save position jump out the for loop
			if (searchName.equals(nameList.get(i)))
			{
				position = i;
				break;
			}
		}
		return position;
	}
	
	// Check whether the range is overlap
	private static boolean checkOverlap (String lower, String upper)
	{
		boolean isOverlap = false;
		int length = upperList.size();
		
		// Traverse all elements in upper
		for (int i = 0; i < length; i ++)
		{
			// If "*" was found
			if (upperList.get(i).equals("*"))
			{
				// If the upper of other ranges is larger than the lower range of a range with higher (*)
				if (Double.parseDouble(upper) > Double.parseDouble(lowerList.get(i)))
				{
					isOverlap = true;
					break;
				}
			}
			// If * was not found
			else 
			{
				// Algorithm to check the overlap:
				// If there are any upper in the list is lower than the lower range given, and
				// If there are any lower in the lost is higher than the upper range given
				// Overlap happened
				// If overlap happened, set isOverlap to true and break the loop
				if (Double.parseDouble(lower) < Double.parseDouble(upperList.get(i)) && Double.parseDouble(lowerList.get(i)) < Double.parseDouble(upper))
				{
					isOverlap = true;
					break;
				}
			}
		}
		return isOverlap;
	}
	
	// Check whether the normal is already exist
	// Only one normal = true are allowed in all ranges
	private static boolean checkNormal ()
	{
		int length = isNormal.size();
		boolean isExist = false;
		// Traverse all elements in isNormal
		for (int i = 0; i < length; i ++)
		{
			// If there are any element is true, set isExist to true and break the for loop
			if (isNormal.get(i).equals("true"))
			{
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	
	// 	Add range 
	public static boolean addRange(String user, String pwd, String lower,
	String upper, String name, boolean normal)
	{
		// call count increasement
		callCount ++;
		boolean isSuccess = false;
		
		// Validate username and password
		if (validate(user,pwd) == false)
		    isSuccess = false;
		else 
		{
			// If name is exist, return false
			if (searchName(name)!= -1)
			{
				isSuccess = false;
			}
			// If set lower to *, return false
			else if (lower.equals("*"))
			{
				isSuccess = false;
			}
			// If lower is higher than upper, return false
			else if (!upper.equals("*") && Double.parseDouble(lower) > Double.parseDouble(upper))
			{
				isSuccess = false;
			}
			// If overlap happened, return false
			else if (checkOverlap(lower,upper) == true)
			{
				isSuccess = false;
			}
			// If normal already exist and still set normal to true, return false
			else if (checkNormal() == true && normal == true)
			{
				isSuccess = false;
			}
			else 
			{
				// Add information to lists
				nameList.add(name);
				lowerList.add(lower);
				upperList.add(upper);
				if (normal == true)
				    isNormal.add("true");
				else 
				    isNormal.add("false");
				isSuccess = true;
			}
		}		
		return isSuccess;
	}
	
	// Delete range
	public static boolean deleteRange (String user, String pwd, String name)
	{
		// Call count increasement
		callCount ++;
		boolean isSuccess = false;
		
		// Get the postion of name
		int position = searchName(name);
		
		// If provide wrong username and password, return false
		if (validate(user,pwd) == false)
		    isSuccess = false;
		// If name not found, return false
		else if (position == -1)
		    isSuccess = false;
		else 
		{
			// Remove information from lists
			nameList.remove(position);
			lowerList.remove(position);
			upperList.remove(position);
			isNormal.remove(position);
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean setName (String user, String pwd, String oldName, String newName)
	{
		// Call count increasement
		callCount ++;
		boolean isSuccess = false;
		
		// Get the postion of name
		int position = searchName (oldName);
		
		// If user provided wrong username or password, return false
		if (validate(user,pwd) == false)
		    isSuccess = false;
		// If name not found, return false
		else if (position == -1)
		    isSuccess = false;
		else 
		{
			// Update name
		    nameList.set(position, newName);
			isSuccess = true;	
		}
		return isSuccess;
	}
	
	// Return the call count
	public static int callCount(String user, String pwd)
	{
		// Call count increasement
		callCount ++;
		int returnCount = 0;
		
		// If user provided wrong username or password, return false
		if (validate(user,pwd) == false)
		    returnCount = -1;
		else 
		{
			// Return the call count
			returnCount = callCount;
		}
		return returnCount;
	}
	
	// Calculate the BMI and get the range
	public static String getResult (String weight, String height)
	{
		// Call count increasement 
		callCount ++;
		String result = new String();
		int length = nameList.size();
		
		// Convert height and Weight
		double heightDouble = Double.parseDouble(height);
		double weightDouble = Double.parseDouble(weight);
		
		// Calculate BMI 
		double BMI = weightDouble / ((heightDouble / 100) * (heightDouble / 100));
		boolean isDefined = false;
		int rangePosition = -1;
		
		// Traverse all elements
		for (int i = 0; i < length; i ++)
		{
			// If upper is not equals to *, and BMI is in a particular range 
			// Get the position of the range and break the for loop
			if ( !(upperList.get(i).equals("*")) 
			    && BMI >= Double.parseDouble(lowerList.get(i)) 
			    && BMI <= Double.parseDouble(upperList.get(i)))
			{
				isDefined = true;
				rangePosition = i;
				break;
			}
			// If upper is equals to * and BMI is higher than the lower value
			// Get the position of the range and break the for loop
			else if (upperList.get(i).equals("*") && BMI >= Double.parseDouble(lowerList.get(i)))
			{
				isDefined = true;
				rangePosition = i;
				break;
			}
		}
		// If range was defined, return back the value
		if (isDefined == true)
		{
			DecimalFormat df = new DecimalFormat("####0.00");
		    result = "BMI: " + String.valueOf(df.format(BMI)) 
		           + ", CLASS: " + nameList.get(rangePosition).toUpperCase();
		}
		// If range is undefined, return back UNDEFINED
		else 
		{
			result = "UNDEFINED";
		}
		return result;
	}
	
	// List all ranges defined
	public static String listRanges()
	{
		// Call count increasement
		callCount ++;
		String result = new String();
		int size = nameList.size();
		
		// If size of lists is equals to 0, return UNDEFINED
		if (size == 0)
		    result = "UNDEFINED";
		else 
		{
			// Else, return all range(s) defined
			for (int i = 0; i < size; i ++)
			{
				result += "CLASS: " + nameList.get(i).toUpperCase()
						+ ", RANGE: " + lowerList.get(i) + " - "
						+ upperList.get(i) + "\n";
			}
		}
		return result;
	}
	
	// Provide the ideal range of Weights for a given height
	public static String listWeights(String height)
	{
		String result = new String();
		// If normal not exist, return UNDEFINED
		if (checkNormal() == false)
		    result = "UNDEFINED";
		else 
		{
			int idealPosition = - 1;
			int size = nameList.size();
			double heightMeter = Double.parseDouble(height) / 100;
			for (int i = 0; i < size; i ++)
			{
				// If true founded, return back the position
				if (isNormal.get(i).equals("true"))
				{
					idealPosition = i;
					break;
				}
			}
			// If upper is equals to *
			if (upperList.get(idealPosition).equals("*"))
			{
				// Calculate the ideal Weight range
				// Ideal range: lower * height^2 - upper * height^2
				result = String.valueOf(Double.parseDouble(lowerList.get(idealPosition)) 
				* heightMeter * heightMeter) + " - *";
			}
			// If upper is not equals to *
			else 
			{
				// Calculate the ideal Weight range
				// Ideal range: lower * height^2 - upper * height^2
				result = String.valueOf(Double.parseDouble(lowerList.get(idealPosition)) 
				* heightMeter * heightMeter) + " - " +
				String.valueOf(Double.parseDouble(upperList.get(idealPosition)) 
				* heightMeter * heightMeter);
			}
		}
		return result;
	}
}