How to run this application:

1. Open Eclipse.

2. Select File -> Open Projects from file system -> Directory -> Choose the contact_manager folder -> Finish

3. In the Package Explorer tab on the left hand side, right click on contact_manager -> Java Build -> PathLibraries -> ClassPath

4. If the file mysql-connector-java-8.0.12.jar is already added in the ClassPath, select cancel. Else click Add JARs Select mysql-connector-java-8.0.12.jar which is located in folder ‘lib’ of the contact_manager project. Click on Apply and Close

5. Right click on the file ContactManagerApp.java -> Run as Java Application

6. Open MySQL Workbench. Run the createSchema.sql file to create the required tables

7. Run PopulateDatabase.java to load a list of sample contacts in your application to get started (optional). This code loads the entries from the contacts.csv file.

The application is ready to use👍👍👍