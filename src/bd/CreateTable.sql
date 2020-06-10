CREATE TABLE USERS(
	id_user int NOT NULL AUTO_INCREMENT,
	nom varchar(255) NOT NULL,
	prenom varchar(255) NOT NULL,
	login varchar(255) UNIQUE NOT NULL,
	password BLOB NOT NULL,
	PRIMARY KEY(id_user)
);


CREATE TABLE FRIENDS(
	from_id int,
	to_id int,
	date_ajout TIMESTAMP NOT NULL,
	PRIMARY KEY (from_id,to_id),
	FOREIGN KEY(from_id) REFERENCES USERS(id_user) ON DELETE CASCADE,
	FOREIGN KEY(to_id) REFERENCES USERS(id_user) ON DELETE CASCADE
);


CREATE TABLE CONNEXIONS(
	connexion_key varchar(32),
	id_user INT NOT NULL,
	date_connexion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	root Boolean NOT NULL,
	PRIMARY KEY(Connexion_key),
	FOREIGN KEY(id_user) REFERENCES USERS(id_user) ON DELETE CASCADE
);


