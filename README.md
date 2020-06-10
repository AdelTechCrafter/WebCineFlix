# Webcinechill
Social Network Application following the API REST made as part of PC3R(Programmation Concurrente Reactive et Repartie) teaching unit at Sorbonne University


##Build
Databases:  
-mySQL 8.0 : store USERS , FRIENDS and CONNEXIONS   
-MongoDB 2.10 or further: store messages (Json format)  
-I use JDBC to contact mySQL and mongoDB database with java.

jars available in WEBContent/WEB-INF/lib:  
-javax.servlet-api-3.1.0.jar  
-json-org.jar  
-mongo-2.10.1.jar  
-mysql-connector-java-5.1.18-bin.jar  

##Requirements to run in local
-mySQL Server  
-php 7 or further  
-phpmyadmin  
-apache tomcat 8.5  

-you can use all-in-one toolkit :XAMPP  

##Query string examples for Servlet testing
-http://localhost:8081/WebCineFlix/HelloWorld  
-http://localhost:8081/WebTwisterAdel/CreateUser?nom=John&prenom=Doe&login=anonymous777&password=1234  






