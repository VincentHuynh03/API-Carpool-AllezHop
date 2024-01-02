--
-- Script de génération de la structure de la base de données du service AllezHop
--
--
CREATE DATABASE Allezhop;

USE Allezhop;

CREATE TABLE IF NOT EXISTS adresse (
    `id` int NOT NULL AUTO_INCREMENT,
  `appartement` varchar(255) DEFAULT NULL,
  `numéro_municipal` varchar(255) NOT NULL,
  `rue` varchar(255) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `état` char(2) DEFAULT NULL,
  `code_postal` varchar(25) NOT NULL,
  `pays` char(2) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE if not EXISTS utilisateur (
	utilisateur_code VARCHAR(500) PRIMARY KEY,
	nom VARCHAR(255) not null,
	prénom VARCHAR(255) not null,
	courriel VARCHAR(255) not null,
	est_conducteur TINYINT(1),
	est_passager TINYINT(1)
);


CREATE TABLE if not EXISTS trajet (
	`code` int NOT NULL AUTO_INCREMENT,
	`destination` int NOT NULL,
	`position_départ` int NOT NULL,
	`heure_arrivée` timestamp NOT NULL,
	`heure_départ_max` timestamp NOT NULL,
	`conducteur` VARCHAR(500) NOT NULL,
	PRIMARY KEY (`code`),
	KEY `trajet_adresse_fk1_idx` (`destination`,`position_départ`),
	KEY `trajet_adresse_fk2_idx` (`position_départ`),
	KEY `trajet_ibfk_1` (`conducteur`),
	CONSTRAINT `trajet_adresse_fk1` FOREIGN KEY (`destination`) REFERENCES `adresse` (`id`),
	CONSTRAINT `trajet_adresse_fk2` FOREIGN KEY (`position_départ`) REFERENCES `adresse` (`id`),
	CONSTRAINT `trajet_ibfk_1` FOREIGN KEY (`conducteur`) REFERENCES `utilisateur` (`utilisateur_code`)
);

CREATE TABLE IF NOT EXISTS réservation (
  `code` int NOT NULL AUTO_INCREMENT,
  `passager` VARCHAR(255) NOT NULL,
  `trajet_code` int NOT NULL,
  `horodatage` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`code`),
  KEY `passager` (`passager`),
  KEY `trajet_code` (`trajet_code`),
  CONSTRAINT `réservation_ibfk_1` FOREIGN KEY (`passager`) REFERENCES `utilisateur` (`utilisateur_code`),
  CONSTRAINT `réservation_ibfk_2` FOREIGN KEY (`trajet_code`) REFERENCES `trajet` (`code`) ON delete CASCADE
);
