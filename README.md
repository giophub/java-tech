# maven-kickstart

Step 1: the user info <br>
// check the user and email of the project <br> 
cmd:> git config --local user.name <br>
cmd:> git config --local user.email <br>

// set the right user info
cmd:> git config --local user.email "yourUserName or Name Surname" <br>
cmd:> git config --local user.email your.email@google.com <br>


## Deploy on Tomcat
There is Tomcat7 embedded in the `pom.xml`. To deploy the application and run the command:
* to compile and generate the war file: `mvn clean package -DskipTests`
* to deploy and run: `mvn tomcat7:run`

Or in one step: `mvn clean package -DskipTests tomcat7:run`