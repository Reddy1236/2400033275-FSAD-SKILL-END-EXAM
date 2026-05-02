CREATE DATABASE fsadendexam;

USE fsadendexam;

CREATE TABLE supplier (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    date DATE,
    status VARCHAR(50),
    PRIMARY KEY (id)
);

SELECT * FROM supplier;
