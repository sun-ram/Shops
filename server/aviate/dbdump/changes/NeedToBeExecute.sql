--
-- DATE  --------       Created By   --------------   Description --------- 
-- 
-- 20/11/2015 -----------  Fayaz  -----------  ProductyType store null changes ------- 
--
ALTER TABLE `shopsbacker`.`product_type` 
DROP FOREIGN KEY `PROD_TYPE_STORE`;
ALTER TABLE `shopsbacker`.`product_type` 
CHANGE COLUMN `STORE_ID` `STORE_ID` VARCHAR(32) NULL ;
ALTER TABLE `shopsbacker`.`product_type` 
ADD CONSTRAINT `PROD_TYPE_STORE`
  FOREIGN KEY (`STORE_ID`)
  REFERENCES `shopsbacker`.`store` (`STORE_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  -- 21/11/2015 -----------  Fayaz  -----------  Customer Details changes ------- 
  
  ALTER TABLE `shopsbacker`.`customer` 
CHANGE COLUMN `NAME` `NAME` VARCHAR(45) NULL ,
CHANGE COLUMN `EMAIL` `EMAIL` VARCHAR(45) NULL DEFAULT NULL ,
ADD UNIQUE INDEX `PHONE_NO_UNIQUE` (`PHONE_NO` ASC);

ALTER TABLE `shopsbacker`.`customer` 
CHANGE COLUMN `PASSWORD` `PASSWORD` VARCHAR(100) NOT NULL ,
CHANGE COLUMN `PHONE_NO` `PHONE_NO` VARCHAR(32) NULL DEFAULT NULL ;

/*added description column in product table*/
ALTER TABLE `shopsbacker`.`product` 
ADD COLUMN `DESCRIPTION` VARCHAR(250) NULL;

-- Added by Kartheeswaran --
  
  CREATE TABLE `shopsbacker`.`password_reset_request` (
  `REQUEST_ID` VARCHAR(32) NOT NULL,
  `TOKEN_ID` VARCHAR(32) NULL,
  `USER_ID` VARCHAR(32) NULL,
  `USER_TYPE` VARCHAR(20) NULL,
  `CREATEDBY` VARCHAR(32) NOT NULL,
  `UPDATEDBY` VARCHAR(32) NOT NULL,
  `CREATED` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ISACTIVE` CHAR(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`REQUEST_ID`));
  
  --
  
 -- 24/11/2015 -----------  Riyaz  -----------  isactive is chnaged string to char  product_image -------  
 ALTER TABLE `shopsbacker`.`product_image` 
CHANGE COLUMN `ISACTIVE` `ISACTIVE` CHAR NOT NULL DEFAULT 'Y' COMMENT '' ;

 -- 24/11/2015 -----------  Srini  -----------  Merchant ForeignKey Added in SalesOrder Table -------  
ALTER TABLE `shopsbacker`.`sales_order` 
ADD INDEX `FK_SO_MERCHANT_idx` (`MERCHANT_ID` ASC)  COMMENT '';
ALTER TABLE `shopsbacker`.`sales_order` 
ADD CONSTRAINT `FK_SO_MERCHANT`
  FOREIGN KEY (`MERCHANT_ID`)
  REFERENCES `shopsbacker`.`merchant` (`MERCHANT_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  -- prabakaran ---------------------------- Sales order column name changes  --------------------
  
  ALTER TABLE `shopsbacker`.`sales_order` 
CHANGE COLUMN `DELIVERY _DATE` `DELIVERY_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
CHANGE COLUMN `DELIVERY _TIME_SLOT_ID` `DELIVERY_TIME_SLOT` VARCHAR(60) NOT NULL ;


ALTER TABLE `shopsbacker`.`sales_order_line` 
DROP FOREIGN KEY `FK_SOL_SALES_ORDER`;
ALTER TABLE `shopsbacker`.`sales_order_line` 
ADD INDEX `FK_SOL_SALES_ORDER_idx` (`SALES_ORDER_ID` ASC);
ALTER TABLE `shopsbacker`.`sales_order_line` 
ADD CONSTRAINT `FK_SOL_SALES_ORDER`
  FOREIGN KEY (`SALES_ORDER_ID`)
  REFERENCES `shopsbacker`.`sales_order` (`SALES_ORDER_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

--   30-11-2015 --------- Prabakaran A ------------------------ device type column added and device id spelling corrected for mobile device details update ------------

  ALTER TABLE `shopsbacker`.`user` 
CHANGE COLUMN `DEVEICEID` `DEVICE_ID` VARCHAR(500) NULL DEFAULT NULL ,
ADD COLUMN `DEVICE_TYPE` VARCHAR(45) NULL AFTER `DEVICE_ID`;

----   01-12-2015 -------- Prabakaran A ----------------- Sales order customer sign forign key altered ----------------
ALTER TABLE `shopsbacker`.`sales_order` 
ADD INDEX `FK_CUSTOMER_SIGN_idx` (`CUSTOMER_SIGN` ASC);
ALTER TABLE `shopsbacker`.`sales_order` 
ADD CONSTRAINT `FK_CUSTOMER_SIGN`
  FOREIGN KEY (`CUSTOMER_SIGN`)
  REFERENCES `shopsbacker`.`image` (`IMAGE_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  ----   10-12-2015 -------- Fayaz ----------------- Address table name field added ----------------
  
  ALTER TABLE `shopsbacker`.`address` 
ADD COLUMN `NAME` VARCHAR(150) NULL DEFAULT NULL AFTER `UPDATED`;

  ----   10-12-2015 -------- Prabakaran A ----------------- Sales order picked, packed, delivered, shopperasigned, backer assigned and delivered time column added ----------------

ALTER TABLE `shopsbacker`.`sales_order` 
CHANGE COLUMN `DELIVERY_TIME` `DELIVERED_TIME` TIMESTAMP NULL DEFAULT NULL ,
ADD COLUMN `SHOPPER_ASSIGNED_TIME` TIMESTAMP NULL AFTER `BACKER_ID`,
ADD COLUMN `PICKUP_START_TIME` TIMESTAMP NULL AFTER `SHOPPER_ASSIGNED_TIME`,
ADD COLUMN `PACKED_TIME` TIMESTAMP NULL AFTER `PICKUP_START_TIME`,
ADD COLUMN `BACKER_ASSIGNED_TIME` TIMESTAMP NULL AFTER `PACKED_TIME`,
ADD COLUMN `DELIVERY_START_TIME` TIMESTAMP NULL AFTER `BACKER_ASSIGNED_TIME`;
  
----   13-12-2015 -------- JAI ----------------- Added City Table ------

CREATE TABLE `shopsbacker`.`city` (
  `CITY_ID` varchar(32) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `STATE_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`CITY_ID`),
  UNIQUE KEY `ID_UNIQUE` (`CITY_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

----   13-12-2015 -------- JAI ----------------- Data For  City Table ------

INSERT INTO `shopsbacker`.`city` VALUES ('1024','Mumbai (Bombay)','1316','Y'),('1027','Chennai (Madras)','1325','Y'),('1028','Hyderabad','1303','Y'),('1030','Bangalore','1319','Y'),('1032','Nagpur','1316','Y'),('1034','Pune','1316','Y'),('1041','Kalyan','1316','Y'),('1042','Madurai','1325','Y'),('1048','Coimbatore','1325','Y'),('1049','Thane (Thana)','1316','Y'),('1052','Vishakhapatnam','1303','Y'),('1056','Vijayawada','1303','Y'),('1059','Nashik (Nasik)','1316','Y'),('1060','Hubli-Dharwad','1319','Y'),('1061','Solapur (Sholapur)','1316','Y'),('1065','Shambajinagar (Aurangabad)','1316','Y'),('1066','Cochin (Kochi)','1313','Y'),('1069','Thiruvananthapuram (Trivandrum','1313','Y'),('1070','Pimpri-Chinchwad','1316','Y'),('1074','Mysore','1319','Y'),('1076','Guntur','1303','Y'),('1079','Warangal','1303','Y'),('1083','Amravati','1316','Y'),('1084','Calicut (Kozhikode)','1313','Y'),('1087','Kolhapur','1316','Y'),('1091','Tiruchirapalli','1325','Y'),('1093','Bhiwandi','1316','Y'),('1095','Ulhasnagar','1316','Y'),('1096','Salem','1325','Y'),('1098','Malegaon','1316','Y'),('1101','Akola','1316','Y'),('1102','Belgaum','1319','Y'),('1103','Rajahmundry','1303','Y'),('1104','Nellore','1303','Y'),('1106','New Bombay','1316','Y'),('1108','Gulbarga','1319','Y'),('1112','Kakinada','1303','Y'),('1113','Dhule (Dhulia)','1316','Y'),('1115','Nanded (Nander)','1316','Y'),('1116','Mangalore','1319','Y'),('1119','Davangere','1319','Y'),('1122','Bellary','1319','Y'),('1125','Jalgaon','1316','Y'),('1127','Nizamabad','1303','Y'),('1131','Kurnool','1303','Y'),('1132','Tiruppur (Tirupper)','1325','Y'),('1136','Chandrapur','1316','Y'),('1141','Ambattur','1325','Y'),('1144','Ichalkaranji','1316','Y'),('1146','Ramagundam','1303','Y'),('1147','Eluru','1303','Y'),('1150','Pondicherry','1322','Y'),('1151','Thanjavur','1325','Y'),('1153','Tuticorin','1325','Y'),('1155','Latur','1316','Y'),('1158','Sangli','1316','Y'),('1159','Parbhani','1316','Y'),('1160','Nagar Coil','1325','Y'),('1161','Bijapur','1319','Y'),('1162','Kukatpalle','1303','Y'),('1166','Avadi','1325','Y'),('1167','Dindigul','1325','Y'),('1168','Ahmadnagar','1316','Y'),('1170','Shimoga','1319','Y'),('1172','Mira Bhayandar','1316','Y'),('1173','Vellore','1325','Y'),('1174','Jalna','1316','Y'),('1176','Anantapur','1303','Y'),('1177','Allappuzha (Alleppey)','1313','Y'),('1178','Tirupati','1303','Y'),('1182','Tiruvottiyur','1325','Y'),('1184','Secunderabad','1303','Y'),('1189','Vizianagaram','1303','Y'),('1190','Erode','1325','Y'),('1191','Machilipatnam (Masulipatam)','1303','Y'),('1193','Raichur','1319','Y'),('1197','Lalbahadur Nagar','1303','Y'),('1201','Cuddalore','1325','Y'),('1208','Kanchipuram','1325','Y'),('1210','Karimnagar','1303','Y'),('1218','Bhusawal','1316','Y'),('1222','Tenali','1303','Y'),('1224','Kollam (Quilon)','1313','Y'),('1225','Kumbakonam','1325','Y'),('1227','Timkur','1319','Y'),('1233','Adoni','1303','Y'),('1235','Tirunelveli','1325','Y'),('1237','Gadag Betigeri','1319','Y'),('1238','Proddatur','1303','Y'),('1239','Chittoor','1303','Y'),('1249','Khammam','1303','Y'),('1252','Malkajgiri','1303','Y'),('1254','Miraj','1316','Y'),('1256','Alandur','1325','Y'),('1265','Palghat (Palakkad)','1313','Y'),('1271','Cuddapah','1303','Y'),('1272','Bhimavaram','1303','Y'),('1275','Mandya','1319','Y'),('1277','Nandyal','1303','Y'),('1279','Neyveli','1325','Y'),('1281','Mahbubnagar','1303','Y'),('1288','Rajapalaiyam','1325','Y'),('1292','Bhir (Bid)','1316','Y'),('1293','Pallavaram','1325','Y'),('1298','Gondiya','1316','Y'),('1299','Tiruvannamalai','1325','Y'),('1300','Yeotmal (Yavatmal)','1316','Y'),('1304','Bidar','1319','Y'),('1305','Guntakal','1303','Y'),('1308','Tambaram','1325','Y'),('1311','Valparai','1325','Y'),('1314','Qutubullapur','1303','Y'),('1316','Hindupur','1303','Y'),('1319','Tellicherry (Thalassery)','1313','Y'),('1320','Wardha','1316','Y'),('1324','Gudivada','1303','Y'),('1327','Ongole','1303','Y'),('1336','Pudukkottai','1325','Y'),('1339','Palayankottai','1325','Y'),('1342','Hospet','1319','Y'),('1344','Achalpur','1316','Y'),('1348','Satara','1316','Y'),('1359','Hassan','1319','Y');


----   13-12-2015 -------- Anbukkani Gajendran ----------------- Added Area Table ------

CREATE TABLE `area` (
  `AREA_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `CITY_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`AREA_ID`),
  UNIQUE KEY `AREA_ID_UNIQUE` (`AREA_ID`),
  UNIQUE KEY `NAME_CITY_UNIQUE` (`NAME`,`CITY_ID`),
  KEY `FK_CITY_ID_idx` (`CITY_ID`),
  CONSTRAINT `FK_CITY_ID` FOREIGN KEY (`CITY_ID`) REFERENCES `city` (`CITY_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

----   13-12-2015 -------- Anbukkani Gajendran ----------------- Refernce data for chennai city Areas  ------

INSERT INTO `shopsbacker`.`area`(`AREA_ID`,`NAME`,`CITY_ID`,`ISACTIVE`) VALUES
('1','Adambakkam','1027','Y'),('2','Adyar','1027','Y'),('3','Alandur','1027','Y'),('4','Alwarpet','1027','Y'),('5','Alwarthirunagar','1027','Y'),('6','Ambattur','1027','Y'),('7','Aminjikarai','1027','Y'),('8','Anakaputhur','1027','Y'),('9','Anna Nagar','1027','Y'),('10','Annanur','1027','Y'),('11','Arumbakkam','1027','Y'),('12','Ashok Nagar','1027','Y'),('13','Avadi','1027','Y'),('14','Ayanavaram','1027','Y'),('15','Besant Nagar','1027','Y'),('16','Basin Bridge','1027','Y'),('17','Chepauk','1027','Y'),('18','Chetput','1027','Y'),('19','Chintadripet','1027','Y'),('20','Chitlapakkam','1027','Y'),('21','Choolai','1027','Y'),('22','Choolaimedu','1027','Y'),('23','Chrompet','1027','Y'),('24','Egmore','1027','Y'),('25','Ekkaduthangal','1027','Y'),('26','Ennore','1027','Y'),('27','Foreshore Estate','1027','Y'),('28','Fort St. George','1027','Y'),('29','George Town','1027','Y'),('30','Gopalapuram','1027','Y'),('31','Government Estate','1027','Y'),('32','Guindy','1027','Y'),('33','Guduvanchery','1027','Y'),('34','IIT Madras','1027','Y'),('35','Injambakkam','1027','Y'),('36','ICF','1027','Y'),('37','Iyyapanthangal','1027','Y'),('38','Jafferkhanpet','1027','Y'),('39','Karapakkam','1027','Y'),('40','Kattivakkam','1027','Y'),('41','Kazhipattur','1027','Y'),('42','K.K. Nagar','1027','Y'),('43','Keelkattalai','1027','Y'),('44','Kelambakkam','1027','Y'),('45','Kilpauk','1027','Y'),('46','Kodambakkam','1027','Y'),('47','Kodungaiyur','1027','Y'),('48','Kolathur','1027','Y'),('49','Korattur','1027','Y'),('50','Korukkupet','1027','Y'),('51','Kottivakkam','1027','Y'),('52','Kotturpuram','1027','Y'),('53','Kottur','1027','Y'),('54','Kovalam','1027','Y'),('55','Kovilambakkam','1027','Y'),('56','Koyambedu','1027','Y'),('57','Kundrathur','1027','Y'),('58','Madhavaram','1027','Y'),('59','Madhavaram Milk Colony','1027','Y'),('60','Madipakkam','1027','Y'),('61','Madambakkam','1027','Y'),('62','Maduravoyal','1027','Y'),('63','Manali','1027','Y'),('64','Manali New Town','1027','Y'),66','Mandaveli','1027','Y'),('67','Mangadu','1027','Y'),('68','Mannadi','1027','Y'),('69','Mathur','1027','Y'),('70','Medavakkam','1027','Y'),('71','Meenambakkam','1027','Y'),('72','Minjur','1027','Y'),('73','Mogappair','1027','Y'),('74','MKB Nagar','1027','Y'),('75','Mount Road','1027','Y'),('76','Moolakadai','1027','Y'),('77','Moulivakkam','1027','Y'),('78','Mugalivakkam','1027','Y'),('79','Mylapore','1027','Y'),('80','Nandanam','1027','Y'),('81','Nanganallur','1027','Y'),83','Neelankarai','1027','Y'),('84','Nemilichery','1027','Y'),('85','Nesapakkam','1027','Y'),('86','Nolambur','1027','Y'),('87','Noombal','1027','Y'),('88','Nungambakkam','1027','Y'),('89','Ottery','1027','Y'),('90','Padi','1027','Y'),('91','Pakkam','1027','Y'),('92','Palavakkam','1027','Y'),('93','Pallavaram','1027','Y'),('94','Pallikaranai','1027','Y'),('95','Pammal','1027','Y'),('96','Park Town','1027','Y'),('97','Parrys Corner','1027','Y'),('98','Pattabiram','1027','Y'),('99','Pattaravakkam','1027','Y'),('100','Pazhavanthangal','1027','Y'),('101','Peerkankaranai','1027','Y'),('102','Perambur','1027','Y'),('103','Peravallur','1027','Y'),('104','Perumbakkam','1027','Y'),('105','Perungalathur','1027','Y'),('106','Perungudi','1027','Y'),('107','Pozhichalur','1027','Y'),('108','Poonamallee','1027','Y'),('109','Porur','1027','Y'),('110','Pudupet','1027','Y'),('111','Purasaiwalkam','1027','Y'),('112','Puthagaram','1027','Y'),('113','Puzhal','1027','Y'),('114','Puzhuthivakkam','1027','Y'),('115','Raj Bhavan','1027','Y'),('116','Ramavaram','1027','Y'),('117','Red Hills','1027','Y'),('118','Royapettah','1027','Y'),('119','Royapuram','1027','Y'),('120','Saidapet','1027','Y'),('121','Saligramam','1027','Y'),('122','Santhome','1027','Y'),('123','Selaiyur','1027','Y'),('124','Shenoy Nagar','1027','Y'),('125','Sholavaram','1027','Y'),('126','Sholinganallur','1027','Y'),('127','Sithalapakkam','1027','Y'),('128','Sowcarpet','1027','Y'),('129','St.Thomas Mount','1027','Y'),('130','Tambaram','1027','Y'),('131','Teynampet','1027','Y'),('132','Tharamani','1027','Y'),('133','T. Nagar','1027','Y'),('134','Thirumangalam','1027','Y'),('135','Thirumullaivoyal','1027','Y'),('136','Thiruneermalai','1027','Y'),('137','Thiruninravur','1027','Y'),('138','Thiruvanmiyur','1027','Y'),('139','Tiruverkadu','1027','Y'),('140','Thiruvotriyur','1027','Y'),('141','Tirusulam','1027','Y'),('142','Tiruvallikeni','1027','Y'),('143','Tondiarpet','1027','Y'),('144','United India Colony','1027','Y'),('145','Urapakkam','1027','Y'),('146','Vandalur','1027','Y'),('147','Vadapalani','1027','Y'),('148','Valasaravakkam','1027','Y'),('149','Vallalar Nagar','1027','Y'),('150','Vanagaram','1027','Y'),('151','Velachery','1027','Y'),('152','Veppampattu','1027','Y'),('153','Villivakkam','1027','Y'),('154','Virugambakkam','1027','Y'),('155','Vyasarpadi','1027','Y'),('156','Washermanpet','1027','Y'),('157','West Mambalam','1027','Y');

----   13-12-2015 -------- Anbukkani Gajendran ----------------- Formatted Address filed added in address table ------

ALTER TABLE `shopsbacker`.`address` 
ADD COLUMN `FORMATTED_ADDRESS` VARCHAR(250) NULL AFTER `NAME`;