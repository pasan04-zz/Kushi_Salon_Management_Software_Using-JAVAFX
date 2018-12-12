BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `totalIncome` (
	`appIncome`	INTEGER,
	`salseIncome`	INTEGER,
	`totalIncomee`	INTEGER
);
INSERT INTO `totalIncome` VALUES (10000,20000,200000);
CREATE TABLE IF NOT EXISTS `totalExpanse` (
	`deEx`	INTEGER,
	`maEx`	INTEGER,
	`Salary`	INTEGER,
	`totalEX`	INTEGER
);
INSERT INTO `totalExpanse` VALUES ('w',17500,10000,120061);
CREATE TABLE IF NOT EXISTS `service_table` (
	`ServiceId`	INTEGER,
	`ServiceName`	TEXT,
	`ServicePrice`	REAL,
	PRIMARY KEY(`ServiceId`)
);
INSERT INTO `service_table` VALUES (1,'Bridle dressing',2000.0);
INSERT INTO `service_table` VALUES (2,'Eyebrow',100.0);
INSERT INTO `service_table` VALUES (3,'Full Face Threading',500.0);
INSERT INTO `service_table` VALUES (4,'Clean up',650.0);
INSERT INTO `service_table` VALUES (5,'Pimple Treatment',1250.0);
INSERT INTO `service_table` VALUES (6,'Facial',1500.0);
INSERT INTO `service_table` VALUES (7,'Oil Treatment',700.0);
INSERT INTO `service_table` VALUES (8,'Tonic Treatment',500.0);
INSERT INTO `service_table` VALUES (9,'Conditioner Treatment',900.0);
INSERT INTO `service_table` VALUES (10,'Hena Tratment',350.0);
INSERT INTO `service_table` VALUES (11,'Dan Druff Tratment',900.0);
INSERT INTO `service_table` VALUES (12,'Eye Treatment',300.0);
INSERT INTO `service_table` VALUES (13,'Layer cuts',700.0);
INSERT INTO `service_table` VALUES (14,'Baby cuts',200.0);
INSERT INTO `service_table` VALUES (15,'Hair Relaxing',4500.0);
INSERT INTO `service_table` VALUES (16,'Hair Rebonding Crown Area',3200.0);
INSERT INTO `service_table` VALUES (17,'Hair Rebonding Ear to Ear',2500.0);
INSERT INTO `service_table` VALUES (18,'Hair Rebonding',5500.0);
INSERT INTO `service_table` VALUES (19,'Hair Colouring',1000.0);
INSERT INTO `service_table` VALUES (20,'Hair coluring Maincure',1400.0);
INSERT INTO `service_table` VALUES (21,'Hair coluring Pedicure',2000.0);
INSERT INTO `service_table` VALUES (22,'Scrubbing Hands',400.0);
INSERT INTO `service_table` VALUES (23,'Scrubbing Legs',600.0);
INSERT INTO `service_table` VALUES (24,'Wax Hands',900.0);
INSERT INTO `service_table` VALUES (25,'Wax Legs',1000.0);
INSERT INTO `service_table` VALUES (26,'Test Service',1000.0);
INSERT INTO `service_table` VALUES (29,'hairStraight',100.0);
CREATE TABLE IF NOT EXISTS `salincom` (
	`salId`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`salIncome`	INTEGER NOT NULL
);
INSERT INTO `salincom` VALUES (1,50000);
INSERT INTO `salincom` VALUES (2,500);
CREATE TABLE IF NOT EXISTS `payment_installment` (
	`aid`	INTEGER,
	`remainingPaymentt`	REAL,
	PRIMARY KEY(`aid`)
);
INSERT INTO `payment_installment` VALUES (9,0.0);
CREATE TABLE IF NOT EXISTS `package_table` (
	`PackageId`	INTEGER,
	`PackageName`	TEXT,
	`Service1`	TEXT,
	`Service2`	TEXT,
	`Service3`	TEXT,
	`PackagePrice`	INTEGER,
	PRIMARY KEY(`PackageId`)
);
INSERT INTO `package_table` VALUES (1,'Bridal Pack','Hair dressing','Makeup','Dressing',10000);
INSERT INTO `package_table` VALUES (2,'Simple Pack','Eye brows','Waxing','Threading',1000);
INSERT INTO `package_table` VALUES (3,'Lite Pack','Hair relaxing','Hair rebonding','Hair colouring',12000);
INSERT INTO `package_table` VALUES (4,'Shine Pack','Conditioner treatment','Hena treatment','Dan druff tratment',1500);
INSERT INTO `package_table` VALUES (5,'Gold Pack','Clean up','Pimple Treatment','Eyebrow',7000);
INSERT INTO `package_table` VALUES (6,'Silver Pack','Hair Rebonding Crown Area','Hair Relaxing','Hair coluring Pedicure',10000);
INSERT INTO `package_table` VALUES (7,'Platinum Pack','Baby cuts','Layer cuts','Facial',6000);
INSERT INTO `package_table` VALUES (8,'Beauty Pack','Scrubbing Legs','Scrubbing Hands','Eyebrow',3000);
INSERT INTO `package_table` VALUES (9,'Face Pack','Facial','Clean up','Oil Treatment',4000);
INSERT INTO `package_table` VALUES (10,'Cool Pack','Hair Rebonding Ear to Ear','Hair Rebonding Crown Area','Hena Tratment',3000);
CREATE TABLE IF NOT EXISTS `maExpan` (
	`maId`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`maExpance`	INTEGER NOT NULL
);
INSERT INTO `maExpan` VALUES (1,10000);
INSERT INTO `maExpan` VALUES (2,10000);
CREATE TABLE IF NOT EXISTS `deExpan` (
	`exId`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`deexpance`	INTEGER NOT NULL
);
INSERT INTO `deExpan` VALUES (1,6000);
INSERT INTO `deExpan` VALUES (2,8000);
CREATE TABLE IF NOT EXISTS `appincom` (
	`appId`	INTEGER NOT NULL UNIQUE,
	`appIncome`	INTEGER NOT NULL,
	PRIMARY KEY(`appId`)
);
INSERT INTO `appincom` VALUES (1,2000);
INSERT INTO `appincom` VALUES (2,2500);
INSERT INTO `appincom` VALUES (3,8000);
CREATE TABLE IF NOT EXISTS `PorL` (
	`totincome`	INTEGER,
	`totalexpanse`	INTEGER,
	`profitLoset`	INTEGER
);
INSERT INTO `PorL` VALUES (1000,15000,250000);
CREATE TABLE IF NOT EXISTS `Payment` (
	`payid`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`cid`	int,
	`aid`	int,
	`payDate`	TEXT,
	`payTime`	TEXT,
	`total`	double,
	CONSTRAINT `fk_aid` FOREIGN KEY(`aid`) REFERENCES `Appointment`(`aid`),
	CONSTRAINT `fk_cid` FOREIGN KEY(`cid`) REFERENCES `Customer`(`nic`)
);
INSERT INTO `Payment` VALUES (2,6,28,'2018/09/19','21:21:09',25000.0);
INSERT INTO `Payment` VALUES (3,2,11,'2018/09/20','21:28:35',12435.0);
INSERT INTO `Payment` VALUES (4,1,9,'2018/09/20','21:29:04',6217.5);
INSERT INTO `Payment` VALUES (6,1,9,'2018/09/21','21:40:28',6217.5);
INSERT INTO `Payment` VALUES (7,3,12,'2018/09/21','21:44:13',12435.0);
CREATE TABLE IF NOT EXISTS `Esalary` (
	`salaryId`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`salary`	INTEGER NOT NULL
);
INSERT INTO `Esalary` VALUES (1,10000);
INSERT INTO `Esalary` VALUES (2,5000);
CREATE TABLE IF NOT EXISTS `BankAcc` (
	`BankName`	INTEGER,
	`Branch`	INTEGER,
	`AccountNu`	INTEGER,
	`AccountHol`	INTEGER
);
CREATE TABLE IF NOT EXISTS `Attendance` (
	`EID`	INTEGER NOT NULL,
	`Attendance`	INTEGER
);
INSERT INTO `Attendance` VALUES (1,'Present');
CREATE TABLE IF NOT EXISTS `Appointment` (
	`AppointID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Date`	TEXT,
	`Time`	TEXT,
	`employee`	TEXT,
	`custID`	INTEGER,
	`custName`	TEXT,
	`custAddress`	TEXT,
	`TeleNum`	TEXT,
	`services`	TEXT,
	`packages`	NUMERIC,
	`total`	REAL
);
INSERT INTO `Appointment` VALUES (9,'2018-08-22','10:25','Nilumi',1,'Madhuu','Galle','1234567890','Full Face Threading','Bridal Pack',12435.0);
INSERT INTO `Appointment` VALUES (11,'2018-08-22','10:25','Nilumi',2,'Ushadi','Colombo','1234567890','Facial','Bridal Pack',12435.0);
INSERT INTO `Appointment` VALUES (12,'2018-08-22','10:25','Shenaya',3,'Shenaya','Colombo','1234567890','Oil Treatment','Simple Pack',12435.0);
INSERT INTO `Appointment` VALUES (18,'2018-08-28','21:20','Shenaya',4,'Bhagya','Rathnapura','0123456789','Dan Druff Tratment','Gold Pack',2520.0);
INSERT INTO `Appointment` VALUES (20,'2018-08-28','5:67','Shenaya',5,'Dinuli','Malabe','0778541526','Hair straightening','Simple pack',4500.0);
INSERT INTO `Appointment` VALUES (27,'2018-09-19','11:23','Employee',6,'Prasadini','Nugegoda','0775896641','Hair Relaxing','Simple pack',500.0);
INSERT INTO `Appointment` VALUES (28,'2018-09-19','4:20','Employee',6,'Dilki','Nawala','0778496222','Hena Tratment','Platinum Pack',25000.0);
COMMIT;
