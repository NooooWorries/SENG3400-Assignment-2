/*
 * Author: Zixin CHENG
 * Course: SENG3400
 * Student number: 3218124
 */


import localhost.axis.MyBMIAdmin_jws.*;

public class MyBMIAdminClient
{
	// Declare server instance
	private static MyBMIAdminService service;
	private static MyBMIAdmin server;
	
	public static void main(String[] args) 
	{
		try 
		{
			// Create server instance
			service = new MyBMIAdminServiceLocator();
			server = service.getMyBMIAdmin();
			
			// If provide insufficient attributes
			// Print error message and exit the program
			if (args.length < 1)
			{
				System.err.println("Please provide sufficient information.");
				System.exit(1);
			}
			
			// Call service that user provided
			callService(args);
			  
			System.exit(0);
		} 
		catch (Exception e) 
		{	
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	private static void callService (String[] args)
	{
		String task = args[0];
		try 
		{
			switch(task)
			{
				// Service: add range
				case "addRange":
				    if (args[6].equals("true"))
                        System.out.println(String.valueOf(server.addRange(args[1],args[2],args[3],args[4],args[5],true)));
					else
					    System.out.println(String.valueOf(server.addRange(args[1],args[2],args[3],args[4],args[5],false)));
					break;
				
				// Service: delete range
				case "deleteRange":
				    System.out.println(String.valueOf(server.deleteRange(args[1],args[2],args[3])));
				    break;
				
				// Service: set name
				case "setName":
				    System.out.println(String.valueOf(server.setName(args[1],args[2],args[3],args[4])));
				    break;
				
				// Service: call count
				case "callCount":
				    System.out.println(server.callCount(args[1],args[2]));
				    break;
				
				// Default: Wrong function provided
				default:
				    System.err.println("Error: Function not exist");
			}
		} 
		// Catch an exception if the number of arguments are incorrent
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.err.println("Error: Incorrect number of arguments");
		}
		// Catch other exceptions
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
}