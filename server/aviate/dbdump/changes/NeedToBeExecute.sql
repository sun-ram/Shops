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

CREATE TABLE `city` (
  `CITY_ID` varchar(32) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `STATE_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`CITY_ID`),
  UNIQUE KEY `ID_UNIQUE` (`CITY_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

----   13-12-2015 -------- JAI ----------------- Data For  City Table ------

INSERT INTO `city` VALUES ('1024','Mumbai (Bombay)','1316','Y'),('1027','Chennai (Madras)','1325','Y'),('1028','Hyderabad','1303','Y'),('1030','Bangalore','1319','Y'),('1032','Nagpur','1316','Y'),('1034','Pune','1316','Y'),('1041','Kalyan','1316','Y'),('1042','Madurai','1325','Y'),('1048','Coimbatore','1325','Y'),('1049','Thane (Thana)','1316','Y'),('1052','Vishakhapatnam','1303','Y'),('1056','Vijayawada','1303','Y'),('1059','Nashik (Nasik)','1316','Y'),('1060','Hubli-Dharwad','1319','Y'),('1061','Solapur (Sholapur)','1316','Y'),('1065','Shambajinagar (Aurangabad)','1316','Y'),('1066','Cochin (Kochi)','1313','Y'),('1069','Thiruvananthapuram (Trivandrum','1313','Y'),('1070','Pimpri-Chinchwad','1316','Y'),('1074','Mysore','1319','Y'),('1076','Guntur','1303','Y'),('1079','Warangal','1303','Y'),('1083','Amravati','1316','Y'),('1084','Calicut (Kozhikode)','1313','Y'),('1087','Kolhapur','1316','Y'),('1091','Tiruchirapalli','1325','Y'),('1093','Bhiwandi','1316','Y'),('1095','Ulhasnagar','1316','Y'),('1096','Salem','1325','Y'),('1098','Malegaon','1316','Y'),('1101','Akola','1316','Y'),('1102','Belgaum','1319','Y'),('1103','Rajahmundry','1303','Y'),('1104','Nellore','1303','Y'),('1106','New Bombay','1316','Y'),('1108','Gulbarga','1319','Y'),('1112','Kakinada','1303','Y'),('1113','Dhule (Dhulia)','1316','Y'),('1115','Nanded (Nander)','1316','Y'),('1116','Mangalore','1319','Y'),('1119','Davangere','1319','Y'),('1122','Bellary','1319','Y'),('1125','Jalgaon','1316','Y'),('1127','Nizamabad','1303','Y'),('1131','Kurnool','1303','Y'),('1132','Tiruppur (Tirupper)','1325','Y'),('1136','Chandrapur','1316','Y'),('1141','Ambattur','1325','Y'),('1144','Ichalkaranji','1316','Y'),('1146','Ramagundam','1303','Y'),('1147','Eluru','1303','Y'),('1150','Pondicherry','1322','Y'),('1151','Thanjavur','1325','Y'),('1153','Tuticorin','1325','Y'),('1155','Latur','1316','Y'),('1158','Sangli','1316','Y'),('1159','Parbhani','1316','Y'),('1160','Nagar Coil','1325','Y'),('1161','Bijapur','1319','Y'),('1162','Kukatpalle','1303','Y'),('1166','Avadi','1325','Y'),('1167','Dindigul','1325','Y'),('1168','Ahmadnagar','1316','Y'),('1170','Shimoga','1319','Y'),('1172','Mira Bhayandar','1316','Y'),('1173','Vellore','1325','Y'),('1174','Jalna','1316','Y'),('1176','Anantapur','1303','Y'),('1177','Allappuzha (Alleppey)','1313','Y'),('1178','Tirupati','1303','Y'),('1182','Tiruvottiyur','1325','Y'),('1184','Secunderabad','1303','Y'),('1189','Vizianagaram','1303','Y'),('1190','Erode','1325','Y'),('1191','Machilipatnam (Masulipatam)','1303','Y'),('1193','Raichur','1319','Y'),('1197','Lalbahadur Nagar','1303','Y'),('1201','Cuddalore','1325','Y'),('1208','Kanchipuram','1325','Y'),('1210','Karimnagar','1303','Y'),('1218','Bhusawal','1316','Y'),('1222','Tenali','1303','Y'),('1224','Kollam (Quilon)','1313','Y'),('1225','Kumbakonam','1325','Y'),('1227','Timkur','1319','Y'),('1233','Adoni','1303','Y'),('1235','Tirunelveli','1325','Y'),('1237','Gadag Betigeri','1319','Y'),('1238','Proddatur','1303','Y'),('1239','Chittoor','1303','Y'),('1249','Khammam','1303','Y'),('1252','Malkajgiri','1303','Y'),('1254','Miraj','1316','Y'),('1256','Alandur','1325','Y'),('1265','Palghat (Palakkad)','1313','Y'),('1271','Cuddapah','1303','Y'),('1272','Bhimavaram','1303','Y'),('1275','Mandya','1319','Y'),('1277','Nandyal','1303','Y'),('1279','Neyveli','1325','Y'),('1281','Mahbubnagar','1303','Y'),('1288','Rajapalaiyam','1325','Y'),('1292','Bhir (Bid)','1316','Y'),('1293','Pallavaram','1325','Y'),('1298','Gondiya','1316','Y'),('1299','Tiruvannamalai','1325','Y'),('1300','Yeotmal (Yavatmal)','1316','Y'),('1304','Bidar','1319','Y'),('1305','Guntakal','1303','Y'),('1308','Tambaram','1325','Y'),('1311','Valparai','1325','Y'),('1314','Qutubullapur','1303','Y'),('1316','Hindupur','1303','Y'),('1319','Tellicherry (Thalassery)','1313','Y'),('1320','Wardha','1316','Y'),('1324','Gudivada','1303','Y'),('1327','Ongole','1303','Y'),('1336','Pudukkottai','1325','Y'),('1339','Palayankottai','1325','Y'),('1342','Hospet','1319','Y'),('1344','Achalpur','1316','Y'),('1348','Satara','1316','Y'),('1359','Hassan','1319','Y');