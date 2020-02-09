# isa-psw-tim08

Web application for clinical center.

## Download instructions

These commands will get you a copy of project for dev and testing purposes
```
$ git clone https://github.com/Mraseta/isa-psw-tim08.git
```

## Prerequisites

To successfully run the application on your local machine please install following software

* Java SDK v1.8 [*(download link)*](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Apache Maven v3.6.3 [*(download link)*](https://maven.apache.org/download.cgi) (or use IDE of your choice, such as IntelliJ Idea, Eclipse etc.)
* NodeJS v12.13.0 [*(download link)*](https://nodejs.org/en/blog/release/v12.13.0/)
* PostgreSQL v12.0 [*(download link)*](https://www.postgresql.org/download/)

## Configuration

Run pgAdmin and create database named *isa*, set password to *admin* and set username to *postgres*

Open cmd or terminal and navigate to psw-isa-front folder. When opened type *npm install* to install all libraries

## Starting

### Backend

1) If you are using Apache Maven, go to the psw-isa-back directory inside the application's root directory, open cmd or terminal and type: *mvn spring-boot:run*

2) In case you are using ide, just open/import the psw-isa-back directory in it and run MedicalcenterApplication file.

### Frontend

Open cmd or terminal and navigate to psw-isa-front folder. When opened type *ng serve* and application should start.

## Deployment

## Authors
Student 1: Stefan Micic 
Student 2: Marko Raseta
Student 3: Nikola Roncevic
