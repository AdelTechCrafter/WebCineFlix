# WebCineFlix:
Social Network Application following the API REST made as part of PC3R(Programmation Concurrente Reactive et Repartie) teaching unit at Sorbonne University


##Présentation du projet:
WebCineFlix est un réseau social développé en utilisant une architecture client-serveur avec une approche orientée ressource (API REST)  et qui propose aux utilisateurs des services de messagerie, d'ajout d'amis et de commentaires. Le serveur est développé en java avec des services implémentés sous la forme de servlets qui peuvent interroger une base de donnée MySQL(utilisateurs,amis,connexions) et une base de donnée NoSQL(commentaires,messages privés). Pour interroger les tables relationelles avec java JDBC est utilisé. Les commentaires et les messages sont stockés dans des collections NoSQL et non des tables relationnelles pour pallier à d'éventuels problèmes de performance dûs à la surcharge de requêtes concernant ces ressources.
Concernant la partie cliente, html, css et javascript ont été utilisés: les requêtes entre le client et le serveur sont réalisées de manière asynchrone en utilisant des appels Ajax de la librairie JQuery et les réponses du serveur sont au format JSON.
Chaque service bénéficie d'une spécifiation propre dans le package src.Specifications

##Prérequis pour faire tourner l'application   
###Servers:    
-Serveur HTTP Apache   
-Serveur MySQL (version recommandée: 8.0)   
-Serveur d'application Tomcat (version recommandée: 7)   
-Serveur MongoDB (version recommandée: 4.2)   
 
###optional:   
-outils all-in one :XAAMP (MySQL with phpmyadmin+Apache+Tomcat)   
-monitoring: MongoDBCompassCommunity (graphical interface for monitoring mongoDB collections)   
 
###jars Eclipse: 
-javax.servlet-api-3.1.0.jar 
-json-org.jar 
-mongo-java-driver-3.12.5.jar 
-mysql-connector-java-5.1.18-bin.jar 
##Configuration des bases de données: 
-src.bd.DBStatic

##Initialisation des tables et collections:  
-MySQL: src.bd.CreateTable.sql  
-MongoDB: src.bd.CreateMessage.txt  

##Deploiement
-Dans un terminal lancer le serveur MongoDB (mongod.exe) et ouvrir l'interface graphique pour visualiser les collections (DBCompassCommunity).   
-Sur XAAMP lancer les serveurs Apache, MySQL et Tomcat.  
-Lancer phpmyadmin sur XAAMP (admin mySQL) pour visualiser les tables.   
-Sur Tomcat déployer le .war de l'application puis lancer l'application, cette action redirigera sur l'index du site(index.html).   
-enjoy !
    







