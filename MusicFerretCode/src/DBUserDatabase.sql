DROP DATABASE IF EXISTS UserDatabase;
CREATE DATABASE UserDatabase;

USE UserDatabase;

CREATE TABLE Profile (
    profileID INT(11) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    latitude DOUBLE(12,9 ) NOT NULL,
	longitude VARCHAR(45) NOT NULL
);

CREATE TABLE Instruments(
	instrumentID INT(11) PRIMARY KEY AUTO_INCREMENT,
    profileID INT(11) NOT NULL,
    instrument VARCHAR(45) NOT NULL,
    skill INT(2) NOT NULL,
    FOREIGN KEY (profileID) REFERENCES Profile(profileID)
);

CREATE TABLE Genres(
	genreID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    profileID INT(11) NOT NULL,
    genreRating INT(2) NOT NULL,
	FOREIGN KEY (profileID) REFERENCES Profile(profileID)
);


INSERT INTO Profile (name, password, email,latitude,longitude)
	VALUES ("Arjun Mitra", "trojan", "mitraarj@usc.edu", "34.0224", "-118.2851");

INSERT INTO Instruments(profileID, instrument ,skill)
	VALUES (1, "guitar",8),
    (1, "piano",2);

-- INSERT INTO Skills(instrumentID, skill)
-- 	VALUES (1, 8),
-- 	(2, 2);
    
INSERT INTO Genres(profileID , genreRating )
	VALUES (1, 2 ),
	(1, 5),
    (1 , 8),
    (1 , 8 ),
    (1 , 9),
    (1, 8),
    (1 ,3 ),
    (1,  5);
