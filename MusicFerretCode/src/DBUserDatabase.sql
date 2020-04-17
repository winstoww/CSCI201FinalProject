DROP DATABASE IF EXISTS UserInfo;
CREATE DATABASE UserInfo;

USE UserInfo;

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
    FOREIGN KEY (profileID) REFERENCES Profile(profileID)
);

CREATE TABLE Skills(
    instrumentID INT(11) NOT NULL,
    skill INT(2) NOT NULL,
	FOREIGN KEY (instrumentID) REFERENCES Instruments(instrumentID)
);

CREATE TABLE Genres(
	genreID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    profileID INT(11) NOT NULL,
    genreName VARCHAR(45) NOT NULL,
    genreRating INT(2) NOT NULL,
	FOREIGN KEY (profileID) REFERENCES Profile(profileID)
);


INSERT INTO Profile (name, password, email,latitude,longitude)
	VALUES ("Arjun Mitra", "trojan", "mitraarj@usc.edu", "34.0224", "-118.2851");

INSERT INTO Instruments(profileID, instrument)
	VALUES (1, "guitar"),
    (1, "piano");

INSERT INTO Skills(instrumentID, skill)
	VALUES (1, 8),
	(2, 2);
    
INSERT INTO Genres(profileID, genreName , genreRating )
	VALUES (1,"POP", 2 ),
	(1, "Rock", 5),
    (1, "Jazz" , 8),
    (1, "Country" , 8 ),
    (1, "Folk" , 9),
    (1, "Blues", 8),
    (1, "Hip Hop" ,3 ),
    (1, "Other" ,  5);
    