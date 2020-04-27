DROP DATABASE IF EXISTS UserDatabase;
CREATE DATABASE UserDatabase;

USE UserDatabase;

CREATE TABLE Profile (
    profileID INT(11) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    latitude DOUBLE(12,9 ) NOT NULL,
	longitude Double(12, 9) NOT NULL
);

CREATE TABLE instrument_skill(
	instrumentID INT(11) PRIMARY KEY AUTO_INCREMENT,
    profileID INT(11) NOT NULL,
    instrumentName VARCHAR(45) NOT NULL,
    skill INT(2) NOT NULL,
    FOREIGN KEY (profileID) REFERENCES Profile(profileID)
);

CREATE TABLE genre_rate(
	genreID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    profileID INT(11) NOT NULL,
    genreName varchar(50) NOT NULL,
    genreRating INT(2) NOT NULL,
	FOREIGN KEY (profileID) REFERENCES Profile(profileID)
);

