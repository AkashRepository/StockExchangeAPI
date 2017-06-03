# StockExchangeAPI
Stock Exchange REST API data using Java CachingSystem (JCS).


**Project** : Stock Exchange

This Project is used to display real time data of stock exchange companies using 3rd Party API.

Frontend : Vanilla Javascript , jQuery, fusion chart.
Business logic : J2EE, Java 7.
Backend : MySQL Database.
Server : Apache Tomecat 7.0.

**Extra** : 

 1. I have used Apache JCS (Java Caching System) an apache API used to save ticker values in Server system cache so that data can be retrieved more efficiently.
 2. Fusion chart to show case real time regression and progression graph of the respective ticker value.

**Properties** : Database connection details can be found in "**com\stockexchange\utilities\StockExchange.properties**". Please change the user name and password for MySql Database connection.

**Database dump file**  : "**StockExchange.sql**" is included in **StockExchange.zip**.

Run the Project on Apache Tomcat Server url : **http://localhost:8080/StockExchangeIndex/**.

**Instructions for running the project**  :  in file named : Deployment_Instruction.txt

1. Unzip the file **StockExchange.zip** in a folder.

2. Restore the MySQL dump "StockExchange.sql".

3. 
	(a) For Java : 

		In the User variables, add PATH value is C:\Program Files\Java\jdk1.7.0_79\bin; // Depends where JDK is installed on your system.

	(b) For Tomcat : 

		In the System variables, add JAVA_HOME value is C:\Program Files\Java\jdk1.7.0_79  // Depends where JDK is installed on your system.

		Now classpath has been set to your machine. You can access java from anywhere in your machine.

		After Done this, Go to Command Prompt and go to ApacheTomcat*x.x and move to bin folder, // Depends where you have installed ApacheTomcat in your system.

4. Copy the StockExchangeIndex.war file in Apache6.0.23\webapps folder.

5. Run Apache Tomcat Server , browse into Apache6.0.23\bin folder open command prompt and enter : %HOME%...\Apache6.0.23\bin>`start startup.bat`

6. Once Tomecat server have started open any browser (preferred Google Chrome).

7. Make sure you are connected to internet for fetching data from Google finance. 

8. Open URL : http://localhost:8080/StockExchangeIndex/  .

9. Project will launch.

