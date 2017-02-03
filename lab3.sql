CREATE TABLE Book (
	idBook INT(8) NOT NULL AUTO_INCREMENT,
	authors VARCHAR(200) NOT NULL,
	`name` VARCHAR(200) NOT NULL,
	`year` DATE NOT NULL,
	`pages` INT(5) NOT NULL,
	`idPublisher` INT(8),
	PRIMARY KEY (idBook)
);

CREATE TABLE Catalog (
	idCatalog INT(8) NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL ,
	nameOfParent VARCHAR(30) NOT NULL,
	PRIMARY KEY (idCatalog)
);

CREATE TABLE Book_Catalog (
	idBookCatalog INT(20) NOT NULL AUTO_INCREMENT,
	idBook INT(8) NOT NULL,
	idCatalog INT(8) NOT NULL,
	PRIMARY KEY (idBookCatalog)
);

CREATE TABLE `Publisher` (
	`idPublisher` INT(8) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(200) NOT NULL,
	`registeredAddress` VARCHAR(500) NOT NULL ,
	`businessAddress` VARCHAR(500) NOT NULL ,
	PRIMARY KEY (`idPublisher`)
);

CREATE TABLE `CopyOfTheBook` (
	`inventoryNumber` INT(20) NOT NULL AUTO_INCREMENT,
	`idBook` INT(8) ,
	`issue` BOOLEAN NOT NULL,
	`idUser` INT(21) NOT NULL ,
	PRIMARY KEY (`inventoryNumber`)
);

CREATE TABLE `User` (
	`idUser` INT(21) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(200) NOT NULL ,
	`middleName` VARCHAR(200) NOT NULL ,
	`lastName` VARCHAR(200) NOT NULL,
	`type` VARCHAR(200) NOT NULL,
	PRIMARY KEY (`idUser`)
);

ALTER TABLE `Book` ADD CONSTRAINT `Book_fk0` FOREIGN KEY (`idPublisher`) REFERENCES `Publisher`(`idPublisher`);

ALTER TABLE `Book_Catalog` ADD CONSTRAINT `Book_Catalog_fk0` FOREIGN KEY (`idBook`) REFERENCES `Book`(`idBook`);

ALTER TABLE `Book_Catalog` ADD CONSTRAINT `Book_Catalog_fk1` FOREIGN KEY (`idCatalog`) REFERENCES `Catalog`(`idCatalog`);

ALTER TABLE `CopyOfTheBook` ADD CONSTRAINT `CopyOfTheBook_fk0` FOREIGN KEY (`idBook`) REFERENCES `Book`(`idBook`);

ALTER TABLE `CopyOfTheBook` ADD CONSTRAINT `CopyOfTheBook_fk1` FOREIGN KEY (`idUser`) REFERENCES `User`(`idUser`);
