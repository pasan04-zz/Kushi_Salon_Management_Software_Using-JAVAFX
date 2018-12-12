BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `customer` (
	`id`	TEXT UNIQUE,
	`nic`	TEXT,
	`firstname`	TEXT,
	`lastname`	TEXT,
	`email`	TEXT,
	`address`	TEXT,
	`phonenumber`	TEXT,
	`age`	TEXT,
	PRIMARY KEY(`id`)
);
INSERT INTO `customer` VALUES ('001','951100374v','Dayawathi','Ariyapala','shalikaashan99@gmail.com','Malabe','1234567890','32');
INSERT INTO `customer` VALUES ('002','961100374v','Nayana','bandarakotuwa','shalikaashan99@gmail.com','Athurugiriya','0771234532','45');
INSERT INTO `customer` VALUES ('003','951450374v','Ushadi','perera','shalikaashan99@gmail.com','Pitakotte','0763445366','67');
INSERT INTO `customer` VALUES ('004','951156374v','Amasha','Fernandez','shalikaashan99@gmail.com','Gampaha','0715375839','29');
INSERT INTO `customer` VALUES ('005','951101234v','Hashini','Nuwaraeliya','shalikaashan99@gmail.com','Halpe','0771237653','36');
INSERT INTO `customer` VALUES ('006','981100374v','Hasini','Dinuka','shalikaashan99@gmail.com','wawniya','0721654932','20');
COMMIT;
