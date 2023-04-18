## About
Welcome!  
The project you are looking at is a REST API designed for clinics made in microservices technology.  
Its main functions are:  
- creating new appointments by patients,
- appointments browsing in the dentist panel,
- managing all resources in the admin panel.

## Microservices & Front End
In order to run my application properly please use these projects:  
- Configuration server - https://github.com/AlbertHeesch/cloud,
- Discovery - https://github.com/AlbertHeesch/discovery,
- Gateway - https://github.com/AlbertHeesch/gateway,
- Rates Back End - https://github.com/AlbertHeesch/DentClinicAppRates,
- Front End - https://github.com/AlbertHeesch/DentClinicAppView,
- Integration Test Suite - https://github.com/AlbertHeesch/DentClinicAppTesting.

## Requirements:  

Java 11

Gradle

MySQL

## How to run
Set up your MaitTrap credentials and MySQL database in `application.properties` file.

Make sure that your localhost: 8080, 8081, 8082, 8083, 8084 and 8085 ports are available.

Build your gradle with `gradlew build` in terminal.

Run the projects in order:  
- Configuration server,
- Discovery,
- Gateway,
- DentAppClinic & DentAppClinicRates,
- DentAppClinicView.

Now you can check http://localhost:8082 to see if all projects are registered in discovery.

Enter the http://localhost:8085/home page in your browser.

## How to configure
My application is sending an email message after an appointment creation.  
To use this functionality please set up your email configuration in the `application.properties` file. 

