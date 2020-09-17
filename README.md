I'am an OC student finishing his tenth project.

The purpose of this project is to create a website for the library network of a municipality. Users will be able to consult the list of available books, the number of copies remaining, the list of their loans and may extend them. Requests relating to the management of loans and returns for staff is implemented, it only remains to design the interface.

This project is made up of 3 REST services:

LibraryBatch implemented with Spring batch whose objective is to send an email every day to users who are behind on their loans. It communicates with the LibraryRestApi thanks to a Feign client via a proxy.

LibraryRestApi which implements all the business logic (controller and service layer). This interface absorbs requests from feign clients.

ClientUi which implements the view (Thymeleaf template). This feign client is the intermediary with the website. It absorbs all POST and GET requests to send them to the LibraryRestApi service through a proxy (proxy package).

Prerequesite:

PostgreSql: On the repository you can find an generation script of the database and a Dump
If you want to set a database with some data: createdb p7 & psql p7 < Db_dump_p7.sql

If you want to set only database table: copy/paste DB_CREATE_p7 when running postgres with your own role.

Apache Maven 3.6.2

OpenJdk8

Apache Tomcat 8.5.45 (This project has an embedded tomcat server)

Follow this instruction to deploy this library management application.

Spring boot allow us to create jar files with embedded tomcat server!

To deploy:

Fork and clone this project, open a terminal and open the project directory:

java -jar ClientUi-0.0.1-SNAPSHOT.jar running on port 8081

java -jar LibraryBatch-0.0.1-SNAPSHOT.jar running on port 8080

java -jar LibraryRestApi-0.0.1-SNAPSHOT.jar-0.0.1-SNAPSHOT.jar running on port 8082

To change TCP port, fork the project and modify port serie from application.properties file for each microservice

You can put these instructions into sh file, easier to execute!

now to run, open your browser and enter the following link:

localhost:8081/

Enjoy!
