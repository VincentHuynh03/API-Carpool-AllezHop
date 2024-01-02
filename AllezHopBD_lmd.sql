-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

--
-- Script de population de la base de données du service AllezHop
--

USE AllezHop;

INSERT INTO utilisateur (utilisateur_code, nom, prénom, courriel, est_conducteur, est_passager)
VALUES
("auth0|65818d99132aa175d51c78d3", 'Voirenne', 'Iris', 'iris.v@gmail.com', 1, 0),
("auth0|65818de66a1998a82fac7f26", 'Doe', 'Jane', 'jane.doe@gmail.com', 1, 0),
("auth0|65818dfc536bb6debfcca492", 'Tremblay', 'Bob', 'bob.t@gmail.com', 0, 1);


INSERT INTO Adresse (numéro_municipal, rue, ville, état, code_postal, pays)
VALUES
    ('6510', '15e avenue', 'Montréal', 'QC', 'H1X2V5', 'CA'),
    ('6400', '16e avenue', 'Montréal', 'QC', 'H1X2S9', 'CA'),
    ('6578', '16e avenue', 'Montréal', 'QC', 'H1X2T2', 'CA'),
    ('6399', '12e avenue', 'Montréal', 'QC', 'H1X3A6', 'CA'),
    ('6537', '12e avenue', 'Montréal', 'QC', 'H1X3A8', 'CA'),
    ('6092', '16e avenue', 'Montréal', 'QC', 'H1X2S8', 'CA'),
    ('5705', '16e avenue', 'Montréal', 'QC', 'H1X2S7', 'CA'),
    ('6421', 'boulevard Saint-Michel', 'Montréal', 'QC', 'H1Y2E9', 'CA'),
    ('6310', 'boulevard Saint-Michel', 'Montréal', 'QC', 'H1Y2E7', 'CA'),
    ('6195', '18e avenue', 'Montréal', 'QC', 'H1X2P8', 'CA');

INSERT INTO Adresse (appartement, numéro_municipal, rue, ville, état, code_postal, pays)
VALUES
    ('12','6411', '20e avenue', 'Montréal', 'QC', 'H1X3P8', 'CA'),
    ('22','6411', '20e avenue', 'Montréal', 'QC', 'H1X3P8', 'CA'),
    ('10','6411', '20e avenue', 'Montréal', 'QC', 'H1X3P8', 'CA'),
    ('21','6411', '20e avenue', 'Montréal', 'QC', 'H1X3P8', 'CA'),
    ('33A','6411', '20e avenue', 'Montréal', 'QC', 'H1X3P8', 'CA');



INSERT INTO trajet (destination, position_départ, heure_arrivée, heure_départ_max, conducteur)
VALUES
(1, 5, '2023-01-19 08:15:00', '2023-01-19 07:40:00', "auth0|65818d99132aa175d51c78d3"),
(1, 8, '2023-11-19 15:15:00', '2023-11-01 15:00:00', "auth0|65818de66a1998a82fac7f26"),
(3, 5, '2023-11-29 15:15:00', '2023-01-29 07:40:00', "auth0|65818d99132aa175d51c78d3"),
(4, 5, '2023-11-20 15:15:00', '2023-01-20 07:40:00', "auth0|65818d99132aa175d51c78d3");


INSERT INTO réservation (passager, trajet_code)
VALUES
("auth0|65818de66a1998a82fac7f26", 1), 
("auth0|65818dfc536bb6debfcca492", 2),
("auth0|65818dfc536bb6debfcca492", 4);