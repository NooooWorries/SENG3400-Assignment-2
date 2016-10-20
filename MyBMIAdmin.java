/*
 * Author: Zixin CHENG
 * Course: SENG3400
 * Student number: 3218124
 */

public class MyBMIAdmin
{
	public boolean addRange(String user, String pwd, String lower,
	String upper, String name, boolean normal)
	{
		// Return the result of add range
		return MyBMIRange.addRange(user, pwd, lower, upper, name, normal);
	}
	
	public boolean deleteRange(String user, String pwd, String name)
	{
		// Return the result of delete range
		return MyBMIRange.deleteRange(user, pwd, name);
	}
	
	public boolean setName (String user, String pwd, String oldName, String newName)
	{
		// Return the result of set name
		return MyBMIRange.setName(user, pwd, oldName, newName);
	}
	
	public int callCount(String user, String pwd)
	{
		// Return the result of call count
		return MyBMIRange.callCount(user, pwd);
	}
}