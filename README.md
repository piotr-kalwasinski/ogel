# Ogel Service 


Service Ogel was created to present Piotr Kalwasinski's programming skills and programming style. Service Ogel is a rest and beckend application fully based on Spring Boot with modules: Core, JPA, MVC.

JUnit5, Mockito and Spring mock MVC are used for testing.

## Requirements

Java 1.8, MySQL + JDBC, Maven

IDE: IntelliJ


## Getting the sources

Only from files - no public repository is available

## How to run application

1. Create database ogel_test
2. Create database structure by loading/running scripts: https://www.marviq.com/assessment/Ogel.zip
3. set the local MySQL root password to "test"
4. Build jar file by command :
mvn install
5. After successful build, change the  current  directory to the target 
6. Run : java -jar demo-0.0.1-SNAPSHOT.jar
7. The application will run on port 10080
8. Use Postman or browser to run test url : 
http://localhost:10080/rest/v1/machine/4x2%20brick%20mould/production?period=HOURLY&dateFrom=2018-01-07T00:00&dateTo=2018-01-07T23:59
- example result : [
                   {
                       "machineName": "4x2 brick mould",
                       "production": 16932,
                       "startHour": 0,
                       "timePeriod": "HOURLY",
                       "startDateTime": "2018-01-07T00:00:00",
                       "endDateTime": "2018-01-07T01:00:00"
                   },
                   {

 




