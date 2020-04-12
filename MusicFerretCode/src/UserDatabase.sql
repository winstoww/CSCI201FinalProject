DROP DATABASE IF EXISTS UserInfo;
CREATE DATABASE UserInfo;

USE UserInfo;

CREATE TABLE User (
    ID INT(11) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL
);

INSERT INTO User (name, email, password)
	VALUES	('Jane Doe', 'Jane@usc.edu', 'mypass');
