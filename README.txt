SENG3400 Assignment 2

Student number: 3218124


User manual
A. Files:
  
  This software contains

  1. Server side codes:

     - MyBMIAdmin.java: Server side, administrator program

     - MyBMIServer.java: Server side, general client program


  2. Client side codes:

     - MyBMIAdminClient.java: Client side, administrator program

     - MyBMIClient.java: Client side, general client program

  
3. Data class

     - MyBMIRange: WEB-INF, used to store and processing data


  4. WSDL files

     - MyBMIAdmin.xml: WSDL file for admin server

     - MyBMIServer.xml: WSDL file for general client server



B. How to start the software (u represent the volume label):

 

 1. Compile the file MyBMIRange.java. And copy the class file (MyBMIRange.class) to u:\tomcat\webapps\axis\WEB-INF\classes


  2. Copy the server side files (MyBMIAdmin.java & MyBMIServer.java) to u:\tomcat\webapps\axis\ and alter the extension name to jws. (Alter name to MyBMIAdmin.jws & MyBMIServer.jws)


  3. Create a new folder called "u:\3218124_client"


  4. Copy the WSDL files (MyBMIAdmin.xml & MyBMIServer.xml) to u:\3218124_client


  5. Copy u:\axis\lib\log4j.properties into u:\3218124_client\


  6. Use cmd tool to change folder to 3218124_client and run the script below:

     - java org.apache.axis.wsdl.WSDL2Java MyBMIAdmin.xml

     - java org.apache.axis.wsdl.WSDL2Java MyBMIServer.xml


  7. Run the script below step by step

     - cd u:\3218124_client\localhost\axis\MyBMIAdmin_jws

     - javac *.java

     - cd u:\3218124_client\localhost\axis\MyBMIServer_jws

     - javac *.java


  8. Copy client files (MyBMIAdminClient.java and MyBMIClient.java) to u:\3218124_client and compile them.
  
 
  Now the server had already been set up

.

C. How to use the software
  Default admin username and password: admin bodymass
  Start tomcat server before use
  1. Admin - Add range
     java MyBMIAdminClient addRange username password lower upper name isNormal
     For example: java MyBMIAdminClient addRange admin bodymass 28 "*" fat false
     Notice: * means unlimitted, and only acceptable for upper. If you want to type in *, use "*"
  2. Admin - Delete range
     java MyBMIAdminClient deleteRange username password name
     For example: java MyBMIAdminClient deleteRange admin bodymass fat
  3. Admin - Set name
     java MyBMIAdminClient setName username password oldName newName
     For example: java MyBMIAdminClient setName admin bodymass fat overweight
  4. Admin - Call count
     java MyBMIAdminClient callCount username password
     For example: java MyBMIAdminClient callCount admin bodymass
  5. Client - Calculate BMI
     java MyBMIClient calcBMI weight height
     For example: java MyBMIClient calcBMI 63 183
     Notice: weight in kg and height in cm
  6. Client - List ranges
     For example: java MyBMIClient listRanges
  7. Client - List weights
     java MyBMIClient listWeights height
     For example: java MyBMIClient listWeights 183
     Notice: height in cm
