# WSD - HealthManagementSystem


## Dev Environment:
- OS: Windows 11
- Java: 22.0.1
- Maven: 3.9.12
- Tomcat: 11.0
- Eclipse: 2024-09
- H2: 2.4.240
- Sublime Text: Build 4200
- Postman: 11.82.6
- SoapUI: 5.9.1

## Details:
### Basic Setup
- Open the `WSD-HealthcareManagementSystem` folder in the command terminal.
- If the executable JAR is not in ./target, then run `mvn clean package`
- Then run `java -jar ./target/clinic-connect-server-1.0.0.jar` to execute and run the service.
- To import the collections, open up Postman and drag/drop the collection JSON in the left side-bar.
- (Service JAR must be running) To run individual requests, click one of the requests in the collection and click "send". 
- (Service JAR must be running) To run the collection tests, click the "..." (View more actions) next to the collection name , click "Run", and then click "Start Run".
