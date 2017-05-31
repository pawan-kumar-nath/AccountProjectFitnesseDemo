# AccountProjectFitnesseDemo

Primary objectives of this project are:
1) To demonstrate a basic Dropwizard based project
2) To support demo of parallel Fitnesse project

Technology stack used:
1) Dropwizard framework
2) Spring IOC
3) JDBI Library for database interaction
4) REST with JAX-RS
5) Maven
6) Swagger for API Documentation

In order to run this project, please do following steps:
1) Get a oracle database 
2) Run \AccountProjectFitnesseDemo\database\scripts.sql on database using SQL client like SQL Developer, TOAD, etc.
3) Update Database Host and Credentials in file \AccountProjectFitnesseDemo\run.sh
4) Build using MAVEN (command:  mvn install)
5) Run application by command:  ./run.sh

Ports:
Application run on port: 9000
Debugging Port: 4005

Swagger UI URL: http://localhost:9000/fitnesse/demo/swagger 

API Details:
1) To Create Customer:
 POST    /fitnesse/demo/customer/{customerName}
 
 Header:
  Accept:application/json
  Content-Type:application/json
  
2) To Deposit Amount:
 POST    /fitnesse/demo/account/?operation=deposit
 
 Header:
  Accept:application/json
  Content-Type:application/json
 
 Body of Request:
 {
	"customerName":"PAWAN",
	"amount":100
 }

3) To Withdraw Amount:
 POST    /fitnesse/demo/account/?operation=withdraw
 
 Header:
  Accept:application/json
  Content-Type:application/json

 Body of Request:
 {
	"customerName":"PAWAN",
	"amount":100
 }
 
4) To Check Balance:
 GET     /fitnesse/demo/customer/{customerName}
 
 Header:
  Accept:application/json
  Content-Type:application/json
 
