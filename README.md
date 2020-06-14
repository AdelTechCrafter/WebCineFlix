# WebCineFlix:
Social Network Application following the API REST made as part of PC3R(Programmation Concurrente Reactive et Repartie) teaching unit at Sorbonne University


##Pr�sentation du projet:
WebCineFlix est un r�seau social d�velopp� en utilisant une architecture client-serveur avec une approche orient�e ressource (API REST)  et qui propose aux utilisateurs des services de messagerie, d'ajout d'amis et de commentaires. Le serveur est d�velopp� en java avec des services impl�ment�s sous la forme de servlets qui peuvent interroger une base de donn�e MySQL(utilisateurs,amis,connexions) et une base de donn�e NoSQL(commentaires,messages priv�s). Pour interroger les tables relationelles avec java JDBC est utilis�. Les commentaires et les messages sont stock�s dans des collections NoSQL et non des tables relationnelles pour pallier � d'�ventuels probl�mes de performance d�s � la surcharge de requ�tes concernant ces ressources.
Concernant la partie cliente, html, css et javascript ont �t� utilis�s: les requ�tes entre le client et le serveur sont r�alis�es de mani�re asynchrone en utilisant des appels Ajax de la librairie JQuery et les r�ponses du serveur sont au format JSON.
Chaque service b�n�ficie d'une sp�cifiation propre dans le package src.Specifications

##Pr�requis pour faire tourner l'application   
###Servers:    
-Serveur HTTP Apache   
-Serveur MySQL (version recommand�e: 8.0)   
-Serveur d'application Tomcat (version recommand�e: 7)   
-Serveur MongoDB (version recommand�e: 4.2)   
 
###optional:   
-outils all-in one :XAAMP (MySQL with phpmyadmin+Apache+Tomcat)   
-monitoring: MongoDBCompassCommunity (graphical interface for monitoring mongoDB collections)   
 
###jars Eclipse: 
-javax.servlet-api-3.1.0.jar 
-json-org.jar 
-mongo-java-driver-3.12.5.jar 
-mysql-connector-java-5.1.18-bin.jar 
##Configuration des bases de donn�es: 
-src.bd.DBStatic

##Initialisation des tables et collections:  
-MySQL: src.bd.CreateTable.sql  
-MongoDB: src.bd.CreateMessage.txt  

##Deploiement
-Dans un terminal lancer le serveur MongoDB (mongod.exe) et ouvrir l'interface graphique pour visualiser les collections (DBCompassCommunity).   
-Sur XAAMP lancer les serveurs Apache, MySQL et Tomcat.  
-Lancer phpmyadmin sur XAAMP (admin mySQL) pour visualiser les tables.   
-Sur Tomcat d�ployer le .war de l'application puis lancer l'application, cette action redirigera sur l'index du site(index.html).   
-enjoy !
    







