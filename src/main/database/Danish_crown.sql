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
    packageType VARCHAR(50),
    animal VARCHAR(255),
    FOREIGN KEY (animal) REFERENCES Animal(animalId)
);

CREATE TABLE Recall (
    recallId VARCHAR(255) PRIMARY KEY,
    animalId VARCHAR(255),
    reason VARCHAR(255),
    FOREIGN KEY (animalId) REFERENCES Animal(animalId)
);

INSERT INTO Animal (animalId, weight, arrivalDate, status) VALUES
('A001', 500.75, '2024-01-10', 'Healthy'),
('A002', 620.50, '2024-01-15', 'Sick'),
('A003', 450.25, '2024-01-20', 'Healthy');

INSERT INTO Part (partId, weight, animalId, type) VALUES
('P001', 250.00, 'A001', 'Shoulder'),
('P002', 150.00, 'A001', 'Leg'),
('P003', 200.00, 'A002', 'Rib'),
('P004', 300.00, 'A003', 'Shoulder');

INSERT INTO Tray (trayId, maxWeight, partType) VALUES
('T001', 1000.00, 'P001'),
('T002', 1500.00, 'P003'),
('T003', 1200.00, 'P002');

INSERT INTO Product (productId, packageType) VALUES
('PR001', 'Box'),
('PR002', 'Bag'),
('PR003', 'Carton');

INSERT INTO Recall (recallId, animalId, reason) VALUES
('R001', 'A002', 'Contamination'),
('R002', 'A003', 'Quality Issue');

