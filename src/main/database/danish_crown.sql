create schema "Danish crown";
set schema 'Danish crown';

CREATE TABLE Animal (
    animalId VARCHAR(255) PRIMARY KEY,
    weight DECIMAL,
    arrivalDate DATE,
    status VARCHAR(50)
);

CREATE TABLE Part (
    partId VARCHAR(255) PRIMARY KEY,
    weight DECIMAL,
    animalId VARCHAR(255),
    type VARCHAR(50),
    FOREIGN KEY (animalId) REFERENCES Animal(animalId)
);

CREATE TABLE Tray (
    trayId VARCHAR(255) PRIMARY KEY,
    maxWeight DECIMAL,
    partType VARCHAR(50),
    FOREIGN KEY (partType) REFERENCES Part(partId)
);

CREATE TABLE Product (
    productId VARCHAR(255) PRIMARY KEY,
    packageType VARCHAR(50)
);

CREATE TABLE Recall (
    recallId VARCHAR(255) PRIMARY KEY,
    animalId VARCHAR(255),
    reason VARCHAR(255),
    FOREIGN KEY (animalId) REFERENCES Animal(animalId)
);