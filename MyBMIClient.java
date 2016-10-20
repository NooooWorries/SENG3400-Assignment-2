/*
 * Author: Zixin CHENG
 * Course: SENG3400
 * Student number: 3218124
 */

import localhost.axis.MyBMIServer_jws.*;

public class MyBMIClient
{
    // Declare server instance
    private static MyBMIServerService service;
    private static MyBMIServer server;
    
	public static void main(String[] args) 
    {
        try 
        {
            // Create server instance
		    service = new MyBMIServerServiceLocator();
            server = service.getMyBMIServer();
        
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
                // Service: calculate BMI
                case "calcBMI":
                    System.out.println(server.calcBMI(args[1], args[2]));
                    break;
                
                // Service: list ranges
                case "listRanges":
                    System.out.println(server.listRanges());
                    break;
                
                // Service: list weights
                case "listWeights":
                    System.out.println(server.listWeights(args[1]));
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