CREATE DATABASE  IF NOT EXISTS `shopsbacker` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `shopsbacker`;
-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: shopsbacker
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `ADDRESS_ID` varchar(32) NOT NULL,
  `CUSTOMER_ID` varchar(32) DEFAULT NULL,
  `LANDMARK` varchar(150) DEFAULT NULL,
  `ADDRESS1` varchar(150) NOT NULL,
  `ADDRESS2` varchar(150) DEFAULT NULL,
  `COUNTRY_ID` varchar(32) NOT NULL,
  `STATE_ID` varchar(32) NOT NULL,
  `CITY` varchar(45) DEFAULT NULL,
  `PIN_CODE` varchar(12) NOT NULL,
  `PHONE_NO` varchar(45) DEFAULT NULL,
  `LATITUDE` varchar(45) DEFAULT NULL,
  `LONGITUDE` varchar(45) DEFAULT NULL,
  `ISACTIVE` char(1) DEFAULT 'Y',
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATEDBY` varchar(32) NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ADDRESS_ID`),
  UNIQUE KEY `ADDRESS_ID_UNIQUE` (`ADDRESS_ID`),
  KEY `FK_ADDRESS_CUSTOMER_idx` (`CUSTOMER_ID`),
  KEY `FK_ADDRESS_STATE_idx` (`STATE_ID`),
  KEY `FK_ADDRESS_COUNTRY_idx` (`COUNTRY_ID`),
  CONSTRAINT `FK_ADDRESS_COUNTRY` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country` (`COUNTRY_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ADDRESS_CUSTOMER` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ADDRESS_STATE` FOREIGN KEY (`STATE_ID`) REFERENCES `state` (`STATE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES ('ff808181511baaf501511bae0a070003',NULL,NULL,'Rajakilpakkam','Kamarajapuram','356','1325','Tambaram','600073',NULL,'12.9249285','80.1589065',NULL,'123','123','2015-11-18 17:39:29','2015-11-18 17:39:29');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banner` (
  `BANNER_ID` varchar(32) NOT NULL,
  `IMAGE_ID` varchar(32) DEFAULT NULL,
  `MERCHANT_ID` varchar(32) DEFAULT NULL,
  `TAB_TITLE_SMALL` varchar(100) DEFAULT NULL,
  `TAB_TITLE_BOLD` varchar(100) DEFAULT NULL,
  `STORE_ID` varchar(32) DEFAULT NULL,
  `IS_SHOPSBACKER_BANNER` char(1) NOT NULL DEFAULT 'N',
  `ISACTIVE` char(1) NOT NULL,
  `CREATED` timestamp NULL DEFAULT NULL,
  `CREATEDBY` varchar(32) DEFAULT NULL,
  `UPDATED` timestamp NULL DEFAULT NULL,
  `UPDATEDBY` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`BANNER_ID`),
  UNIQUE KEY `BANNER_ID_UNIQUE` (`BANNER_ID`),
  KEY `FK_BANNER_IMAGE_idx` (`IMAGE_ID`),
  KEY `FK_BANNER_MERCHANT_idx` (`MERCHANT_ID`),
  KEY `FK_BANNER_STORE_idx` (`STORE_ID`),
  CONSTRAINT `FK_BANNER_IMAGE` FOREIGN KEY (`IMAGE_ID`) REFERENCES `image` (`IMAGE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_BANNER_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_BANNER_STORE` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `COUNTRY_ID` varchar(32) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `CODE` varchar(45) NOT NULL,
  `CURRENCY_CODE` varchar(45) DEFAULT NULL,
  `CURRENCY_NAME` varchar(45) DEFAULT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`COUNTRY_ID`),
  UNIQUE KEY `COUNTRY_ID_UNIQUE` (`COUNTRY_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES ('10','Antarctica','AQ','','','Y'),('100','Bulgaria','BG','BGN','Lev','Y'),('104','Myanmar','MM','MMK','Kyat','Y'),('108','Burundi','BI','BIF','Franc','Y'),('112','Belarus','BY','BYR','Ruble','Y'),('116','Cambodia','KH','KHR','Riels','Y'),('12','Algeria','DZ','DZD','Dinar','Y'),('120','Cameroon','CM','XAF','Franc','Y'),('124','Canada','CA','CAD','Dollar','Y'),('132','Cape Verde','CV','CVE','Escudo','Y'),('136','Cayman Islands','KY','KYD','Dollar','Y'),('140','Central African Republic','CF','XAF','Franc','Y'),('144','Sri Lanka','LK','LKR','Rupee','Y'),('148','Chad','TD','XAF','Franc','Y'),('152','Chile','CL','CLP','Peso','Y'),('156','China','CN','CNY','Yuan Renminbi','Y'),('158','Taiwan','TW','TWD','Dollar','Y'),('16','American Samoa','AS','USD','Dollar','Y'),('162','Christmas Island','CX','AUD','Dollar','Y'),('166','Cocos Islands','CC','AUD','Dollar','Y'),('170','Colombia','CO','COP','Peso','Y'),('174','Comoros','KM','KMF','Franc','Y'),('175','Mayotte','YT','EUR','Euro','Y'),('178','Republic of the Congo','CG','XAF','Franc','Y'),('180','Democratic Republic of the Congo','CD','CDF','Franc','Y'),('184','Cook Islands','CK','NZD','Dollar','Y'),('188','Costa Rica','CR','CRC','Colon','Y'),('191','Croatia','HR','HRK','Kuna','Y'),('192','Cuba','CU','CUP','Peso','Y'),('196','Cyprus','CY','EUR','Euro','Y'),('20','Andorra','AD','EUR','Euro','Y'),('203','Czech Republic','CZ','CZK','Koruna','Y'),('204','Benin','BJ','XOF','Franc','Y'),('208','Denmark','DK','DKK','Krone','Y'),('212','Dominica','DM','XCD','Dollar','Y'),('214','Dominican Republic','DO','DOP','Peso','Y'),('218','Ecuador','EC','USD','Dollar','Y'),('222','El Salvador','SV','USD','Dollar','Y'),('226','Equatorial Guinea','GQ','XAF','Franc','Y'),('231','Ethiopia','ET','ETB','Birr','Y'),('232','Eritrea','ER','ERN','Nakfa','Y'),('233','Estonia','EE','EEK','Kroon','Y'),('234','Faroe Islands','FO','DKK','Krone','Y'),('238','Falkland Islands','FK','FKP','Pound','Y'),('239','South Georgia and the South Sandwich Islands','GS','GBP','Pound','Y'),('24','Angola','AO','AOA','Kwanza','Y'),('242','Fiji','FJ','FJD','Dollar','Y'),('246','Finland','FI','EUR','Euro','Y'),('248','Aland Islands','AX','EUR','Euro','Y'),('250','France','FR','EUR','Euro','Y'),('254','French Guiana','GF','EUR','Euro','Y'),('258','French Polynesia','PF','XPF','Franc','Y'),('260','French Southern Territories','TF','EUR','Euro  ','Y'),('262','Djibouti','DJ','DJF','Franc','Y'),('266','Gabon','GA','XAF','Franc','Y'),('268','Georgia','GE','GEL','Lari','Y'),('270','Gambia','GM','GMD','Dalasi','Y'),('275','Palestinian Territory','PS','ILS','Shekel','Y'),('276','Germany','DE','EUR','Euro','Y'),('28','Antigua and Barbuda','AG','XCD','Dollar','Y'),('288','Ghana','GH','GHS','Cedi','Y'),('292','Gibraltar','GI','GIP','Pound','Y'),('296','Kiribati','KI','AUD','Dollar','Y'),('300','Greece','GR','EUR','Euro','Y'),('304','Greenland','GL','DKK','Krone','Y'),('308','Grenada','GD','XCD','Dollar','Y'),('31','Azerbaijan','AZ','AZN','Manat','Y'),('312','Guadeloupe','GP','EUR','Euro','Y'),('316','Guam','GU','USD','Dollar','Y'),('32','Argentina','AR','ARS','Peso','Y'),('320','Guatemala','GT','GTQ','Quetzal','Y'),('324','Guinea','GN','GNF','Franc','Y'),('328','Guyana','GY','GYD','Dollar','Y'),('332','Haiti','HT','HTG','Gourde','Y'),('334','Heard Island and McDonald Islands','HM','AUD','Dollar','Y'),('336','Vatican','VA','EUR','Euro','Y'),('340','Honduras','HN','HNL','Lempira','Y'),('344','Hong Kong','HK','HKD','Dollar','Y'),('348','Hungary','HU','HUF','Forint','Y'),('352','Iceland','IS','ISK','Krona','Y'),('356','India','IN','INR','Rupee','Y'),('36','Australia','AU','AUD','Dollar','Y'),('360','Indonesia','ID','IDR','Rupiah','Y'),('364','Iran','IR','IRR','Rial','Y'),('368','Iraq','IQ','IQD','Dinar','Y'),('372','Ireland','IE','EUR','Euro','Y'),('376','Israel','IL','ILS','Shekel','Y'),('380','Italy','IT','EUR','Euro','Y'),('384','Ivory Coast','CI','XOF','Franc','Y'),('388','Jamaica','JM','JMD','Dollar','Y'),('392','Japan','JP','JPY','Yen','Y'),('398','Kazakhstan','KZ','KZT','Tenge','Y'),('4','Afghanistan','AF','AFN','Afghani','Y'),('40','Austria','AT','EUR','Euro','Y'),('400','Jordan','JO','JOD','Dinar','Y'),('404','Kenya','KE','KES','Shilling','Y'),('408','North Korea','KP','KPW','Won','Y'),('410','South Korea','KR','KRW','Won','Y'),('414','Kuwait','KW','KWD','Dinar','Y'),('417','Kyrgyzstan','KG','KGS','Som','Y'),('418','Laos','LA','LAK','Kip','Y'),('422','Lebanon','LB','LBP','Pound','Y'),('426','Lesotho','LS','LSL','Loti','Y'),('428','Latvia','LV','LVL','Lat','Y'),('430','Liberia','LR','LRD','Dollar','Y'),('434','Libya','LY','LYD','Dinar','Y'),('438','Liechtenstein','LI','CHF','Franc','Y'),('44','Bahamas','BS','BSD','Dollar','Y'),('440','Lithuania','LT','LTL','Litas','Y'),('442','Luxembourg','LU','EUR','Euro','Y'),('446','Macao','MO','MOP','Pataca','Y'),('450','Madagascar','MG','MGA','Ariary','Y'),('454','Malawi','MW','MWK','Kwacha','Y'),('458','Malaysia','MY','MYR','Ringgit','Y'),('462','Maldives','MV','MVR','Rufiyaa','Y'),('466','Mali','ML','XOF','Franc','Y'),('470','Malta','MT','EUR','Euro','Y'),('474','Martinique','MQ','EUR','Euro','Y'),('478','Mauritania','MR','MRO','Ouguiya','Y'),('48','Bahrain','BH','BHD','Dinar','Y'),('480','Mauritius','MU','MUR','Rupee','Y'),('484','Mexico','MX','MXN','Peso','Y'),('492','Monaco','MC','EUR','Euro','Y'),('496','Mongolia','MN','MNT','Tugrik','Y'),('498','Moldova','MD','MDL','Leu','Y'),('499','Montenegro','ME','EUR','Euro','Y'),('50','Bangladesh','BD','BDT','Taka','Y'),('500','Montserrat','MS','XCD','Dollar','Y'),('504','Morocco','MA','MAD','Dirham','Y'),('508','Mozambique','MZ','MZN','Meticail','Y'),('51','Armenia','AM','AMD','Dram','Y'),('512','Oman','OM','OMR','Rial','Y'),('516','Namibia','NA','NAD','Dollar','Y'),('52','Barbados','BB','BBD','Dollar','Y'),('520','Nauru','NR','AUD','Dollar','Y'),('524','Nepal','NP','NPR','Rupee','Y'),('528','Netherlands','NL','EUR','Euro','Y'),('530','Netherlands Antilles','AN','ANG','Guilder','Y'),('533','Aruba','AW','AWG','Guilder','Y'),('540','New Caledonia','NC','XPF','Franc','Y'),('548','Vanuatu','VU','VUV','Vatu','Y'),('554','New Zealand','NZ','NZD','Dollar','Y'),('558','Nicaragua','NI','NIO','Cordoba','Y'),('56','Belgium','BE','EUR','Euro','Y'),('562','Niger','NE','XOF','Franc','Y'),('566','Nigeria','NG','NGN','Naira','Y'),('570','Niue','NU','NZD','Dollar','Y'),('574','Norfolk Island','NF','AUD','Dollar','Y'),('578','Norway','NO','NOK','Krone','Y'),('580','Northern Mariana Islands','MP','USD','Dollar','Y'),('581','United States Minor Outlying Islands','UM','USD','Dollar ','Y'),('583','Micronesia','FM','USD','Dollar','Y'),('584','Marshall Islands','MH','USD','Dollar','Y'),('585','Palau','PW','USD','Dollar','Y'),('586','Pakistan','PK','PKR','Rupee','Y'),('591','Panama','PA','PAB','Balboa','Y'),('598','Papua New Guinea','PG','PGK','Kina','Y'),('60','Bermuda','BM','BMD','Dollar','Y'),('600','Paraguay','PY','PYG','Guarani','Y'),('604','Peru','PE','PEN','Sol','Y'),('608','Philippines','PH','PHP','Peso','Y'),('612','Pitcairn','PN','NZD','Dollar','Y'),('616','Poland','PL','PLN','Zloty','Y'),('620','Portugal','PT','EUR','Euro','Y'),('624','Guinea-Bissau','GW','XOF','Franc','Y'),('626','East Timor','TL','USD','Dollar','Y'),('630','Puerto Rico','PR','USD','Dollar','Y'),('634','Qatar','QA','QAR','Rial','Y'),('638','Reunion','RE','EUR','Euro','Y'),('64','Bhutan','BT','BTN','Ngultrum','Y'),('642','Romania','RO','RON','Leu','Y'),('643','Russia','RU','RUB','Ruble','Y'),('646','Rwanda','RW','RWF','Franc','Y'),('652','Saint Barthélemy','BL','EUR','Euro','Y'),('654','Saint Helena','SH','SHP','Pound','Y'),('659','Saint Kitts and Nevis','KN','XCD','Dollar','Y'),('660','Anguilla','AI','XCD','Dollar','Y'),('662','Saint Lucia','LC','XCD','Dollar','Y'),('663','Saint Martin','MF','EUR','Euro','Y'),('666','Saint Pierre and Miquelon','PM','EUR','Euro','Y'),('670','Saint Vincent and the Grenadines','VC','XCD','Dollar','Y'),('674','San Marino','SM','EUR','Euro','Y'),('678','Sao Tome and Principe','ST','STD','Dobra','Y'),('68','Bolivia','BO','BOB','Boliviano','Y'),('682','Saudi Arabia','SA','SAR','Rial','Y'),('686','Senegal','SN','XOF','Franc','Y'),('688','Serbia','RS','RSD','Dinar','Y'),('690','Seychelles','SC','SCR','Rupee','Y'),('694','Sierra Leone','SL','SLL','Leone','Y'),('70','Bosnia and Herzegovina','BA','BAM','Marka','Y'),('702','Singapore','SG','SGD','Dollar','Y'),('703','Slovakia','SK','EUR','Euro','Y'),('704','Vietnam','VN','VND','Dong','Y'),('705','Slovenia','SI','EUR','Euro','Y'),('706','Somalia','SO','SOS','Shilling','Y'),('710','South Africa','ZA','ZAR','Rand','Y'),('716','Zimbabwe','ZW','ZWL','Dollar','Y'),('72','Botswana','BW','BWP','Pula','Y'),('724','Spain','ES','EUR','Euro','Y'),('732','Western Sahara','EH','MAD','Dirham','Y'),('736','Sudan','SD','SDG','Dinar','Y'),('74','Bouvet Island','BV','NOK','Krone','Y'),('740','Suriname','SR','SRD','Dollar','Y'),('744','Svalbard and Jan Mayen','SJ','NOK','Krone','Y'),('748','Swaziland','SZ','SZL','Lilangeni','Y'),('752','Sweden','SE','SEK','Krona','Y'),('756','Switzerland','CH','CHF','Franc','Y'),('76','Brazil','BR','BRL','Real','Y'),('760','Syria','SY','SYP','Pound','Y'),('762','Tajikistan','TJ','TJS','Somoni','Y'),('764','Thailand','TH','THB','Baht','Y'),('768','Togo','TG','XOF','Franc','Y'),('772','Tokelau','TK','NZD','Dollar','Y'),('776','Tonga','TO','TOP','Pa\'anga','Y'),('780','Trinidad and Tobago','TT','TTD','Dollar','Y'),('784','United Arab Emirates','AE','AED','Dirham','Y'),('788','Tunisia','TN','TND','Dinar','Y'),('792','Turkey','TR','TRY','Lira','Y'),('795','Turkmenistan','TM','TMT','Manat','Y'),('796','Turks and Caicos Islands','TC','USD','Dollar','Y'),('798','Tuvalu','TV','AUD','Dollar','Y'),('8','Albania','AL','ALL','Lek','Y'),('800','Uganda','UG','UGX','Shilling','Y'),('804','Ukraine','UA','UAH','Hryvnia','Y'),('807','Macedonia','MK','MKD','Denar','Y'),('818','Egypt','EG','EGP','Pound','Y'),('826','United Kingdom','GB','GBP','Pound','Y'),('831','Guernsey','GG','GBP','Pound','Y'),('832','Jersey','JE','GBP','Pound','Y'),('833','Isle of Man','IM','GBP','Pound','Y'),('834','Tanzania','TZ','TZS','Shilling','Y'),('84','Belize','BZ','BZD','Dollar','Y'),('840','United States','US','USD','Dollar','Y'),('850','U.S. Virgin Islands','VI','USD','Dollar','Y'),('854','Burkina Faso','BF','XOF','Franc','Y'),('855','Kosovo','XK','EUR','Euro','Y'),('858','Uruguay','UY','UYU','Peso','Y'),('86','British Indian Ocean Territory','IO','USD','Dollar','Y'),('860','Uzbekistan','UZ','UZS','Som','Y'),('862','Venezuela','VE','VEF','Bolivar','Y'),('876','Wallis and Futuna','WF','XPF','Franc','Y'),('882','Samoa','WS','WST','Tala','Y'),('887','Yemen','YE','YER','Rial','Y'),('891','Serbia and Montenegro','CS','RSD','Dinar','Y'),('894','Zambia','ZM','ZMK','Kwacha','Y'),('90','Solomon Islands','SB','SBD','Dollar','Y'),('92','British Virgin Islands','VG','USD','Dollar','Y'),('96','Brunei','BN','BND','Dollar','Y');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `CUSTOMER_ID` varchar(32) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `IMAGE_ID` varchar(32) DEFAULT NULL,
  `DEVICEID` varchar(45) DEFAULT NULL,
  `DEVICE_TYPE` varchar(45) DEFAULT NULL,
  `PHONE_NO` int(11) DEFAULT NULL,
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATEDBY` varchar(32) NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`CUSTOMER_ID`),
  UNIQUE KEY `CUSTOMER_ID_UNIQUE` (`CUSTOMER_ID`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_feedback`
--

DROP TABLE IF EXISTS `customer_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_feedback` (
  `CUSTOMER_FEEDBACK_ID` varchar(32) NOT NULL,
  `CUSTOMER_ID` varchar(32) NOT NULL,
  `COMMENTS` varchar(500) NOT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `STORE_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATEDBY` varchar(32) NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`CUSTOMER_FEEDBACK_ID`),
  UNIQUE KEY `CUSTOMER_FEEDBACK_ID_UNIQUE` (`CUSTOMER_FEEDBACK_ID`),
  KEY `FK_CUST_FEEDBACK_CREATED_idx` (`CREATEDBY`),
  KEY `FK_CUST_FEEDBACK_STORE_ID_idx` (`STORE_ID`),
  KEY `FK_CUST_FEEDBACK_MERCHANT_ID_idx` (`MERCHANT_ID`),
  KEY `FK_CUST_FEEDBACK_CUSTOMER_ID_idx` (`CUSTOMER_ID`),
  CONSTRAINT `FK_CUST_FEEDBACK_CUSTOMER_ID` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CUST_FEEDBACK_MERCHANT_ID` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CUST_FEEDBACK_STORE_ID` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_feedback`
--

LOCK TABLES `customer_feedback` WRITE;
/*!40000 ALTER TABLE `customer_feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_time_slot`
--

DROP TABLE IF EXISTS `delivery_time_slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_time_slot` (
  `DELIVERY_TIME_SLOT_ID` varchar(32) NOT NULL,
  `FROM_TIME` time NOT NULL,
  `TO_TIME` time NOT NULL,
  `MERCHNAT_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATEDBY` varchar(32) NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`DELIVERY_TIME_SLOT_ID`),
  UNIQUE KEY `idDELIVERY_TIME_SLOT_ID_UNIQUE` (`DELIVERY_TIME_SLOT_ID`),
  UNIQUE KEY `TIME_SLOT_UNIQUE` (`FROM_TIME`,`TO_TIME`,`MERCHNAT_ID`),
  KEY `FK_DELIV_TIME_SLOT_MERCHANT_ID_idx` (`MERCHNAT_ID`),
  CONSTRAINT `FK_DELIV_TIME_SLOT_MERCHANT_ID` FOREIGN KEY (`MERCHNAT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_time_slot`
--

LOCK TABLES `delivery_time_slot` WRITE;
/*!40000 ALTER TABLE `delivery_time_slot` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_time_slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount` (
  `DISCOUNT_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` double DEFAULT '0',
  `DISCOUNT_AMOUNT` decimal(15,2) DEFAULT '0.00',
  `MIN_QTY` int(11) DEFAULT NULL,
  `MAX_QTY` int(11) DEFAULT NULL,
  `START_DATE` timestamp NULL DEFAULT NULL,
  `END_DATE` timestamp NULL DEFAULT NULL,
  `START_TIME` time DEFAULT NULL,
  `END_TIME` time DEFAULT NULL,
  `MIN_AMOUNT` decimal(15,2) NOT NULL DEFAULT '0.00',
  `MERCHANT_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NULL DEFAULT NULL,
  `CREATEDBY` varchar(32) DEFAULT NULL,
  `UPDATED` timestamp NULL DEFAULT NULL,
  `UPDATEDBY` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`DISCOUNT_ID`),
  UNIQUE KEY `DISCOUNT_ID_UNIQUE` (`DISCOUNT_ID`),
  UNIQUE KEY `DISCOUNT_UNIQUE_NAME` (`NAME`,`MERCHANT_ID`),
  UNIQUE KEY `DISCOUNT_NAME_UNIQUE` (`NAME`,`MERCHANT_ID`),
  KEY `FK_DISCOUNT_MERCHANT_idx` (`MERCHANT_ID`),
  CONSTRAINT `FK_DISCOUNT_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite`
--

DROP TABLE IF EXISTS `favourite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favourite` (
  `FAVOURITE_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `ORDER_ID` varchar(32) NOT NULL,
  `CUSTOMER_ID` varchar(32) NOT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `STORE_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) NOT NULL,
  PRIMARY KEY (`FAVOURITE_ID`),
  UNIQUE KEY `FAVOURITE_ID_UNIQUE` (`FAVOURITE_ID`),
  UNIQUE KEY `FACOURITE_UNIQUE_NAME` (`NAME`,`CUSTOMER_ID`),
  UNIQUE KEY `FAVOURITE_NAME_UNIQUE` (`NAME`,`CUSTOMER_ID`,`MERCHANT_ID`,`STORE_ID`),
  KEY `FK_FAVOURITE_ORDER_idx` (`ORDER_ID`),
  KEY `FK_FAVOURITE_CUSTOMER_idx` (`CUSTOMER_ID`),
  KEY `FK_FAVOURITE_MERCHANT_idx` (`MERCHANT_ID`),
  KEY `FK_FAVOURITE_STORE_idx` (`STORE_ID`),
  CONSTRAINT `FK_FAVOURITE_CUSTOMER` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FAVOURITE_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FAVOURITE_ORDER` FOREIGN KEY (`ORDER_ID`) REFERENCES `sales_order` (`SALES_ORDER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FAVOURITE_STORE` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite`
--

LOCK TABLES `favourite` WRITE;
/*!40000 ALTER TABLE `favourite` DISABLE KEYS */;
/*!40000 ALTER TABLE `favourite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `IMAGE_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `TYPE` varchar(10) NOT NULL,
  `URL` varchar(200) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATEDBY` varchar(32) DEFAULT NULL,
  `UPDATEDBY` varchar(32) DEFAULT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`IMAGE_ID`),
  UNIQUE KEY `IMAGE_ID_UNIQUE` (`IMAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES ('ff808181511baaf501511bae09ff0001','2914e497eeda427d9e83d7fada378a8a','png','merchant/Prabakaran/2914e497eeda427d9e83d7fada378a8a.png','\0','123','123','2015-11-18 17:39:29','2015-11-18 17:39:29');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant` (
  `MERCHANT_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  `LOGO_ID` varchar(32) DEFAULT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATEDBY` varchar(32) NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`MERCHANT_ID`),
  UNIQUE KEY `MERCHANT_ID_UNIQUE` (`MERCHANT_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  KEY `FK_MERCHANT_USER_idx` (`USER_ID`),
  KEY `FK_MERCHANT_LOGO_idx` (`LOGO_ID`),
  CONSTRAINT `FK_MERCHANT_LOGO` FOREIGN KEY (`LOGO_ID`) REFERENCES `image` (`IMAGE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MERCHANT_USER` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant`
--

LOCK TABLES `merchant` WRITE;
/*!40000 ALTER TABLE `merchant` DISABLE KEYS */;
/*!40000 ALTER TABLE `merchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant_request`
--

DROP TABLE IF EXISTS `merchant_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant_request` (
  `MERCHANT_REQUEST_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `ADDRESS_ID` varchar(32) NOT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NULL DEFAULT NULL,
  `CREATEDBY` varchar(32) DEFAULT NULL,
  `UPDATED` timestamp NULL DEFAULT NULL,
  `UPDATEDBY` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`MERCHANT_REQUEST_ID`),
  UNIQUE KEY `MERCHANT_REQUEST_ID_UNIQUE` (`MERCHANT_REQUEST_ID`),
  KEY `FK_MERCHANT_REQ_ADDRESS_idx` (`ADDRESS_ID`),
  CONSTRAINT `FK_MERCHANT_REQ_ADDRESS` FOREIGN KEY (`ADDRESS_ID`) REFERENCES `address` (`ADDRESS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant_request`
--

LOCK TABLES `merchant_request` WRITE;
/*!40000 ALTER TABLE `merchant_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `merchant_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `missing_product`
--

DROP TABLE IF EXISTS `missing_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `missing_product` (
  `MISSING_PRODUCT_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `CUSTOMER_ID` varchar(32) NOT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `STORE_ID` varchar(32) DEFAULT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NULL DEFAULT NULL,
  `CREATEDBY` varchar(32) DEFAULT NULL,
  `UPDATED` timestamp NULL DEFAULT NULL,
  `UPDATEDBY` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`MISSING_PRODUCT_ID`),
  UNIQUE KEY `MISSING_PRODUCT_ID_UNIQUE` (`MISSING_PRODUCT_ID`),
  KEY `FK_MISS_PROD_MERCHANT_idx` (`MERCHANT_ID`),
  KEY `FK_MISS_PROD_STORE_idx` (`STORE_ID`),
  KEY `FK_MISS_PROD_CUSTOMER_idx` (`CUSTOMER_ID`),
  CONSTRAINT `FK_MISS_PROD_CUSTOMER` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MISS_PROD_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MISS_PROD_STORE` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `missing_product`
--

LOCK TABLES `missing_product` WRITE;
/*!40000 ALTER TABLE `missing_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `missing_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movement`
--

DROP TABLE IF EXISTS `movement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movement` (
  `MOVEMENT_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `MERCHANT_ID` varchar(32) DEFAULT NULL,
  `STORE_ID` varchar(32) DEFAULT NULL,
  `WAREHOUSE_ID` varchar(32) DEFAULT NULL,
  `ISMOVED` char(1) NOT NULL DEFAULT 'N',
  `ISUPDATED` char(1) NOT NULL DEFAULT 'N',
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATEDBY` varchar(32) NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`MOVEMENT_ID`),
  UNIQUE KEY `MOVEMENT_ID_UNIQUE` (`MOVEMENT_ID`),
  KEY `FK_MOVEMENT_STORE_ID_idx` (`STORE_ID`),
  KEY `FK_MOVEMENT_MERCHANT_ID_idx` (`MERCHANT_ID`),
  KEY `FK_MOVEMENTWAREHOUSE_ID_idx` (`WAREHOUSE_ID`),
  CONSTRAINT `FK_MOVEMENTWAREHOUSE_ID` FOREIGN KEY (`WAREHOUSE_ID`) REFERENCES `warehouse` (`WAREHOUSE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MOVEMENT_MERCHANT_ID` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MOVEMENT_STORE_ID` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movement`
--

LOCK TABLES `movement` WRITE;
/*!40000 ALTER TABLE `movement` DISABLE KEYS */;
/*!40000 ALTER TABLE `movement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movement_line`
--

DROP TABLE IF EXISTS `movement_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movement_line` (
  `MOVEMENT_LINE_ID` varchar(32) NOT NULL,
  `MOVEMENT_ID` varchar(32) NOT NULL,
  `FROM_BIN_ID` varchar(32) DEFAULT NULL,
  `TO_BIN_ID` varchar(32) NOT NULL,
  `QTY` int(11) NOT NULL DEFAULT '0',
  `PRODUCT_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATEDBY` varchar(32) NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`MOVEMENT_LINE_ID`),
  UNIQUE KEY `MOVEMENTLINE_ID_UNIQUE` (`MOVEMENT_LINE_ID`),
  KEY `FK_MOVEMENTLINE_FROM_BIN_ID_idx` (`FROM_BIN_ID`),
  KEY `FK_MOVEMENTLINE_TO_BIN_idx` (`TO_BIN_ID`),
  KEY `FK_MOVEMENTLINE_MOVEMENT_idx` (`MOVEMENT_ID`),
  KEY `FK_MOVEMENTLINE_PRODUCT_idx` (`PRODUCT_ID`),
  CONSTRAINT `FK_MOVEMENTLINE_FROM_BIN` FOREIGN KEY (`FROM_BIN_ID`) REFERENCES `storagebin` (`STORAGEBIN_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MOVEMENTLINE_MOVEMENT` FOREIGN KEY (`MOVEMENT_ID`) REFERENCES `movement` (`MOVEMENT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MOVEMENTLINE_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MOVEMENTLINE_TO_BIN` FOREIGN KEY (`TO_BIN_ID`) REFERENCES `storagebin` (`STORAGEBIN_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movement_line`
--

LOCK TABLES `movement_line` WRITE;
/*!40000 ALTER TABLE `movement_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `movement_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_cart`
--

DROP TABLE IF EXISTS `my_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_cart` (
  `MY_CART_ID` varchar(32) NOT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `STORE_ID` varchar(32) NOT NULL,
  `PRODUCT_ID` varchar(32) NOT NULL,
  `CUSTOMER_ID` varchar(32) NOT NULL,
  `PRODUCT_OFFER_ID` varchar(32) DEFAULT NULL,
  `PRODUCT_OFFER_LINE_ID` varchar(32) DEFAULT NULL,
  `QTY` int(11) NOT NULL DEFAULT '0',
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATEDBY` varchar(32) DEFAULT NULL,
  `UPDATEDBY` varchar(32) DEFAULT NULL,
  `CREATED` timestamp NULL DEFAULT NULL,
  `UPDATED` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`MY_CART_ID`),
  UNIQUE KEY `MY_CART_ID_UNIQUE` (`MY_CART_ID`),
  KEY `FK_MY_CART_CUSTOMER_idx` (`CUSTOMER_ID`),
  KEY `FK_MY_CART_MERCHANT_idx` (`MERCHANT_ID`),
  KEY `FK_MY_CART_STORE_idx` (`STORE_ID`),
  KEY `FK_MY_CART_PRODUCT_idx` (`PRODUCT_ID`),
  KEY `FK_MY_CART_PROD_OFFERLINE_idx` (`PRODUCT_OFFER_LINE_ID`),
  KEY `FK_MY_CART_PROD_OFFER_idx` (`PRODUCT_OFFER_ID`),
  CONSTRAINT `FK_MY_CART_CUSTOMER` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MY_CART_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MY_CART_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MY_CART_PROD_OFFER` FOREIGN KEY (`PRODUCT_OFFER_ID`) REFERENCES `product_offer` (`PRODUCT_OFFER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MY_CART_PROD_OFFERLINE` FOREIGN KEY (`PRODUCT_OFFER_LINE_ID`) REFERENCES `product_offer_line` (`PRODUCT_OFFER_LINE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MY_CART_STORE` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_cart`
--

LOCK TABLES `my_cart` WRITE;
/*!40000 ALTER TABLE `my_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `my_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_number`
--

DROP TABLE IF EXISTS `order_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_number` (
  `ORDER_NUMBER_ID` varchar(32) NOT NULL,
  `STARTING_NUMBER` int(11) NOT NULL,
  `PREFIX` varchar(10) DEFAULT NULL,
  `SUFFIX` varchar(10) DEFAULT NULL,
  `NEXT_NUMBER` int(11) DEFAULT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `STORE_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) NOT NULL,
  PRIMARY KEY (`ORDER_NUMBER_ID`),
  UNIQUE KEY `ORDER_NO_ID_UNIQUE` (`ORDER_NUMBER_ID`),
  UNIQUE KEY `ORDER_NUM_UNIQUE` (`STORE_ID`,`MERCHANT_ID`),
  KEY `ORDER_NUM_STORE_idx` (`STORE_ID`),
  KEY `ORDER_NUM_MERCHANT_idx` (`MERCHANT_ID`),
  CONSTRAINT `FK_ORDER_NUM_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ORDER_NUM_STORE` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_number`
--

LOCK TABLES `order_number` WRITE;
/*!40000 ALTER TABLE `order_number` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_number` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_tax`
--

DROP TABLE IF EXISTS `order_tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_tax` (
  `ORDER_TAX_ID` varchar(32) NOT NULL,
  `SALES_ORDER_ID` varchar(32) NOT NULL,
  `TAX_ID` varchar(32) NOT NULL,
  `TAX_AMOUNT` decimal(15,2) NOT NULL DEFAULT '0.00',
  `MERCHANT_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NULL DEFAULT NULL,
  `CREATEDBY` varchar(32) DEFAULT NULL,
  `UPDATED` timestamp NULL DEFAULT NULL,
  `UPDATEDBY` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ORDER_TAX_ID`),
  UNIQUE KEY `ORDER_TAX_ID_UNIQUE` (`ORDER_TAX_ID`),
  KEY `FK_ORDER_TAX_TAX_idx` (`TAX_ID`),
  KEY `FK_ORDER_TAX_SO_idx` (`SALES_ORDER_ID`),
  KEY `FK_ORDER_TAX_MERCHANT_idx` (`MERCHANT_ID`),
  CONSTRAINT `FK_ORDER_TAX_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ORDER_TAX_SO` FOREIGN KEY (`SALES_ORDER_ID`) REFERENCES `sales_order` (`SALES_ORDER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ORDER_TAX_TAX` FOREIGN KEY (`TAX_ID`) REFERENCES `tax` (`TAX_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_tax`
--

LOCK TABLES `order_tax` WRITE;
/*!40000 ALTER TABLE `order_tax` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_tax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `PRODUCT_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `PRODUCT_TYPE_ID` varchar(32) NOT NULL,
  `PRODUCT_CATEGORY_ID` varchar(32) NOT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `EDIBLE_TYPE` varchar(45) DEFAULT NULL,
  `GROUP_COUNT` int(11) DEFAULT NULL,
  `BRAND` varchar(45) DEFAULT NULL,
  `UOM_ID` varchar(32) NOT NULL,
  `IMAGE_ID` varchar(32) DEFAULT NULL,
  `PRICE` decimal(15,2) NOT NULL DEFAULT '0.00',
  `IS_YOUR_HOT` char(1) DEFAULT 'N',
  `DISCOUNT_ID` varchar(32) DEFAULT NULL,
  `UNIT` decimal(15,2) NOT NULL DEFAULT '0.00',
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATEDBY` varchar(32) NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`PRODUCT_ID`),
  UNIQUE KEY `PROD_NAME_UNIQUE` (`NAME`,`MERCHANT_ID`,`UOM_ID`,`UNIT`),
  KEY `FK_PROD_PRODUCT_TYPE_idx` (`PRODUCT_TYPE_ID`),
  KEY `FK_PROD_PRODUCT_CATEGORY_idx` (`PRODUCT_CATEGORY_ID`),
  KEY `FK_PROD_UOM_idx` (`UOM_ID`),
  KEY `FK_PROD_IMAGE_idx` (`IMAGE_ID`),
  KEY `FK_PROD_DISCOUNT_idx` (`DISCOUNT_ID`),
  KEY `FK_PROD_MERCHANT_idx` (`MERCHANT_ID`),
  CONSTRAINT `FK_PROD_DISCOUNT` FOREIGN KEY (`DISCOUNT_ID`) REFERENCES `discount` (`DISCOUNT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROD_IMAGE` FOREIGN KEY (`IMAGE_ID`) REFERENCES `image` (`IMAGE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROD_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROD_PRODUCT_CATEGORY` FOREIGN KEY (`PRODUCT_CATEGORY_ID`) REFERENCES `product_category` (`PRODUCT_CATEGORY_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROD_PRODUCT_TYPE` FOREIGN KEY (`PRODUCT_TYPE_ID`) REFERENCES `product_type` (`PRODUCT_TYPE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROD_UOM` FOREIGN KEY (`UOM_ID`) REFERENCES `uom` (`UOM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_category` (
  `PRODUCT_CATEGORY_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `PARENT_CATEGORY_ID` varchar(32) DEFAULT NULL,
  `STORE_ID` varchar(32) DEFAULT NULL,
  `MERCHANT_ID` varchar(32) DEFAULT NULL,
  `ORDER_SEQUENCE` int(11) DEFAULT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATEDBY` varchar(32) NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`PRODUCT_CATEGORY_ID`),
  KEY `FK_PROD_CATE_PARENT_idx` (`PARENT_CATEGORY_ID`),
  KEY `FK_PROD_CATE_MERCHANT_idx` (`MERCHANT_ID`),
  KEY `FK_PROD_CATE_STORE_idx` (`STORE_ID`),
  CONSTRAINT `FK_PROD_CATE_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROD_CATE_PARENT` FOREIGN KEY (`PARENT_CATEGORY_ID`) REFERENCES `product_category` (`PRODUCT_CATEGORY_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROD_CATE_STORE` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_image` (
  `PRODUCT_IMAGE_ID` varchar(32) NOT NULL,
  `PRODUCT_ID` varchar(32) NOT NULL,
  `IMAGE_ID` varchar(32) NOT NULL,
  `ISACTIVE` varchar(45) NOT NULL DEFAULT 'Y',
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATEDBY` varchar(32) NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`PRODUCT_IMAGE_ID`),
  UNIQUE KEY `PRODUCT_IMAGE_ID_UNIQUE` (`PRODUCT_IMAGE_ID`),
  UNIQUE KEY `PROD_IMG_UNIQUE` (`PRODUCT_ID`,`IMAGE_ID`),
  KEY `FK_PROD_IMG_PRODUCT_idx` (`PRODUCT_ID`),
  KEY `FK_PROD_IMG_IMAGE_idx` (`IMAGE_ID`),
  CONSTRAINT `FK_PROD_IMG_IMAGE` FOREIGN KEY (`IMAGE_ID`) REFERENCES `image` (`IMAGE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROD_IMG_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_inventory`
--

DROP TABLE IF EXISTS `product_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_inventory` (
  `PRODUCT_INVENTORY_ID` varchar(32) NOT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `STORE_ID` varchar(32) NOT NULL,
  `STORAGEBIN_ID` varchar(32) NOT NULL,
  `PRODUCT_ID` varchar(32) NOT NULL,
  `AVAILABLE_QTY` int(11) NOT NULL DEFAULT '0',
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATEDBY` varchar(32) NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`PRODUCT_INVENTORY_ID`),
  UNIQUE KEY `PRODUCT_INVENTORY_ID_UNIQUE` (`PRODUCT_INVENTORY_ID`),
  UNIQUE KEY `PROD_INV_UNIQUE` (`MERCHANT_ID`,`STORE_ID`,`STORAGEBIN_ID`,`PRODUCT_ID`),
  KEY `FK_PROD_INV_MERCHANT_idx` (`MERCHANT_ID`),
  KEY `FK_PROD_INV_STORE_idx` (`STORE_ID`),
  KEY `FK_PROD_INV_STORAGEBIN_idx` (`STORAGEBIN_ID`),
  KEY `FK_PROD_INV_PRODUCT_idx` (`PRODUCT_ID`),
  CONSTRAINT `FK_PROD_INV_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROD_INV_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROD_INV_STORAGEBIN` FOREIGN KEY (`STORAGEBIN_ID`) REFERENCES `storagebin` (`STORAGEBIN_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROD_INV_STORE` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_inventory`
--

LOCK TABLES `product_inventory` WRITE;
/*!40000 ALTER TABLE `product_inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_offer`
--

DROP TABLE IF EXISTS `product_offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_offer` (
  `PRODUCT_OFFER_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `PRODUCT_ID` varchar(32) NOT NULL,
  `FROM_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `TODATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `START_TIME` time NOT NULL,
  `END_TIME` time NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATEDBY` varchar(32) NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `MERCHANT_ID` varchar(32) NOT NULL,
  PRIMARY KEY (`PRODUCT_OFFER_ID`),
  UNIQUE KEY `PRODUCT_OFFER_ID_UNIQUE` (`PRODUCT_OFFER_ID`),
  UNIQUE KEY `PROD_OFFER_NAME_UNIQUE` (`NAME`,`MERCHANT_ID`),
  KEY `FK_PROD_OFFER_PRODUCT_idx` (`PRODUCT_ID`),
  KEY `FK_PROD_OFFER_MERCHANT_idx` (`MERCHANT_ID`),
  CONSTRAINT `FK_PROD_OFFER_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROD_OFFER_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_offer`
--

LOCK TABLES `product_offer` WRITE;
/*!40000 ALTER TABLE `product_offer` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_offer_line`
--

DROP TABLE IF EXISTS `product_offer_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_offer_line` (
  `PRODUCT_OFFER_LINE_ID` varchar(32) NOT NULL,
  `PRODUCT_OFFER_ID` varchar(32) NOT NULL,
  `PRODUCT_ID` varchar(32) NOT NULL,
  `DISCOUNT_PERCENTAGE` double NOT NULL DEFAULT '0',
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NULL DEFAULT NULL,
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) NOT NULL,
  PRIMARY KEY (`PRODUCT_OFFER_LINE_ID`),
  UNIQUE KEY `PRODUCT_OFFERLINE_ID_UNIQUE` (`PRODUCT_OFFER_LINE_ID`),
  KEY `PROD_OFFERLINE_OFFER_idx` (`PRODUCT_OFFER_ID`),
  KEY `PROD_OFFERLINE_PRODUCT_idx` (`PRODUCT_ID`),
  CONSTRAINT `PROD_OFFERLINE_OFFER` FOREIGN KEY (`PRODUCT_OFFER_ID`) REFERENCES `product_offer` (`PRODUCT_OFFER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PROD_OFFERLINE_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_offer_line`
--

LOCK TABLES `product_offer_line` WRITE;
/*!40000 ALTER TABLE `product_offer_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_offer_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type`
--

DROP TABLE IF EXISTS `product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_type` (
  `PRODUCT_TYPE_ID` varchar(32) NOT NULL,
  `PRODUCT_CATEGORY_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `STORE_ID` varchar(32) NOT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) NOT NULL,
  PRIMARY KEY (`PRODUCT_TYPE_ID`),
  UNIQUE KEY `PRODUCT_TYPE_ID_UNIQUE` (`PRODUCT_TYPE_ID`),
  KEY `PROD_TYPE_MERCHANT_idx` (`MERCHANT_ID`),
  KEY `PROD_TYPE_STORE_idx` (`STORE_ID`),
  KEY `PROD_TYPE_CATEGORY_idx` (`PRODUCT_CATEGORY_ID`),
  CONSTRAINT `PROD_TYPE_CATEGORY` FOREIGN KEY (`PRODUCT_CATEGORY_ID`) REFERENCES `product_category` (`PRODUCT_CATEGORY_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PROD_TYPE_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PROD_TYPE_STORE` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type`
--

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ROLE_ID` varchar(32) NOT NULL,
  `NAME` varchar(60) NOT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NULL DEFAULT NULL,
  `CREATEDBY` varchar(32) DEFAULT NULL,
  `UPDATED` timestamp NULL DEFAULT NULL,
  `UPDATEDBY` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `ROLE_ID_UNIQUE` (`ROLE_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('ab123','SUPERADMIN','super admin','Y',NULL,NULL,NULL,NULL),('ab124','MERCHANTADMIN','Merchant Admin','Y',NULL,NULL,NULL,NULL),('ab125','STOREADMIN','Store Admin','Y',NULL,NULL,NULL,NULL),('ab126','SHOPPER','Shopper','Y',NULL,NULL,NULL,NULL),('ab127','BACKER','Backer','Y',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_order`
--

DROP TABLE IF EXISTS `sales_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_order` (
  `SALES_ORDER_ID` varchar(32) NOT NULL,
  `ORDER_NO` varchar(45) NOT NULL,
  `CUSTOMER_ID` varchar(32) NOT NULL,
  `ADDRESS_ID` varchar(32) NOT NULL,
  `DELIVERY _DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DELIVERY _TIME_SLOT_ID` varchar(32) NOT NULL,
  `ISPAID` char(1) NOT NULL DEFAULT 'N',
  `AMOUNT` decimal(15,2) NOT NULL DEFAULT '0.00',
  `PAYMENT_METHOD` varchar(45) DEFAULT NULL,
  `TRANSACTION_NO` varchar(45) DEFAULT NULL,
  `STATUS` varchar(45) DEFAULT NULL,
  `TOTAL_TAX_AMOUNT` decimal(15,2) NOT NULL DEFAULT '0.00',
  `SHIPPING_CHARGE` decimal(15,2) NOT NULL,
  `NET_AMOUNT` decimal(15,2) NOT NULL DEFAULT '0.00',
  `CUSTOMER_SIGN` varchar(32) DEFAULT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `STORE_ID` varchar(32) NOT NULL,
  `SHOPPER_ID` varchar(32) DEFAULT NULL,
  `BACKER_ID` varchar(32) DEFAULT NULL,
  `DELIVERY_TIME` timestamp NULL DEFAULT NULL,
  `DELIVERY_FLAG` char(1) DEFAULT 'N',
  `CUSTOMER_FEEDBACK_ID` varchar(32) DEFAULT NULL,
  `DISCOUNT_AMOUNT` decimal(15,2) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATEDBY` varchar(32) DEFAULT NULL,
  `CREATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`SALES_ORDER_ID`),
  UNIQUE KEY `SALES_ORDER_ID_UNIQUE` (`SALES_ORDER_ID`),
  KEY `FK_SO_CUSTOMER_idx` (`CUSTOMER_ID`),
  KEY `FK_SO_ADDRESS_idx` (`ADDRESS_ID`),
  KEY `FK_SO_STORE_ID_idx` (`STORE_ID`),
  KEY `FK_SO_SHOPPER_idx` (`SHOPPER_ID`),
  KEY `FK_SO_BACKER_idx` (`BACKER_ID`),
  KEY `FK_SO_FEEDBACK_idx` (`CUSTOMER_FEEDBACK_ID`),
  CONSTRAINT `FK_SO_ADDRESS` FOREIGN KEY (`ADDRESS_ID`) REFERENCES `address` (`ADDRESS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SO_BACKER` FOREIGN KEY (`BACKER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SO_CUSTOMER` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SO_FEEDBACK` FOREIGN KEY (`CUSTOMER_FEEDBACK_ID`) REFERENCES `customer_feedback` (`CUSTOMER_FEEDBACK_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SO_SHOPPER` FOREIGN KEY (`SHOPPER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SO_STORE_ID` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_order`
--

LOCK TABLES `sales_order` WRITE;
/*!40000 ALTER TABLE `sales_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_order_line`
--

DROP TABLE IF EXISTS `sales_order_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_order_line` (
  `SALES_ORDER_LINE_ID` varchar(32) NOT NULL,
  `SALES_ORDER_ID` varchar(32) NOT NULL,
  `PRODUCT_ID` varchar(32) NOT NULL,
  `QTY` int(11) NOT NULL DEFAULT '0',
  `PRICE` decimal(15,2) NOT NULL DEFAULT '0.00',
  `GROSS_AMOUNT` decimal(15,2) NOT NULL DEFAULT '0.00',
  `NET_AMOUNT` decimal(15,2) NOT NULL DEFAULT '0.00',
  `DISCOUNT` decimal(15,2) NOT NULL DEFAULT '0.00',
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATEDBY` varchar(32) DEFAULT NULL,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`SALES_ORDER_LINE_ID`),
  UNIQUE KEY `SALES_ORDER_LINE_ID_UNIQUE` (`SALES_ORDER_LINE_ID`),
  KEY `FK_SOL_PRODUCT_idx` (`PRODUCT_ID`),
  CONSTRAINT `FK_SOL_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SOL_SALES_ORDER` FOREIGN KEY (`SALES_ORDER_LINE_ID`) REFERENCES `sales_order` (`SALES_ORDER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_order_line`
--

LOCK TABLES `sales_order_line` WRITE;
/*!40000 ALTER TABLE `sales_order_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_order_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_charges`
--

DROP TABLE IF EXISTS `shipping_charges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_charges` (
  `SHIPPING_CHARGES_ID` varchar(32) NOT NULL,
  `CHARGING_AMOUNT` decimal(15,2) NOT NULL DEFAULT '0.00',
  `AMOUNT_RANGE` decimal(15,2) DEFAULT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) NOT NULL,
  PRIMARY KEY (`SHIPPING_CHARGES_ID`),
  UNIQUE KEY `SHIPPING_CHARGES_ID_UNIQUE` (`SHIPPING_CHARGES_ID`),
  KEY `FK_SHIPPING_CHAR_MERCHANT_idx` (`MERCHANT_ID`),
  CONSTRAINT `FK_SHIPPING_CHAR_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_charges`
--

LOCK TABLES `shipping_charges` WRITE;
/*!40000 ALTER TABLE `shipping_charges` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipping_charges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `STATE_ID` varchar(32) NOT NULL,
  `COUNTRY_ID` varchar(32) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`STATE_ID`),
  UNIQUE KEY `STATE_ID_UNIQUE` (`STATE_ID`),
  KEY `FK_STATE_COUNTRY_idx` (`COUNTRY_ID`),
  CONSTRAINT `FK_STATE_COUNTRY` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country` (`COUNTRY_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES ('1','4','Badakhshan','Y'),('10','4','Herat Province','Y'),('100','20','Parròquia de Sant Julià de Lòria','Y'),('1000','234','Streymoy region','Y'),('1001','234','Suðuroy region','Y'),('1002','234','Vágar region','Y'),('1003','238','Falkland Islands (Islas Malvinas)','Y'),('1004','239','South Georgia and The South Sandwich Islands ','Y'),('1005','242','Central','Y'),('1006','242','Eastern','Y'),('1007','242','Northern','Y'),('1008','242','Rotuma','Y'),('1009','242','Western','Y'),('101','20','Parròquia dʼAndorra la Vella','Y'),('1010','246','Åland','Y'),('1011','246','Hame','Y'),('1012','246','Keski-Suomi','Y'),('1013','246','Kuopio','Y'),('1014','246','Kymi','Y'),('1015','246','Lapponia','Y'),('1016','246','Mikkeli','Y'),('1017','246','Oulu','Y'),('1018','246','Pohjois-Karjala','Y'),('1019','246','Turku ja Pori','Y'),('102','20','Parròquia dʼEscaldes-Engordany','Y'),('1020','246','Uusimaa','Y'),('1021','246','Vaasa','Y'),('1022','246','Southern Finland','Y'),('1023','246','Eastern Finland','Y'),('1024','246','Western Finland','Y'),('1025','250','Aquitaine','Y'),('1026','250','Auvergne','Y'),('1027','250','Basse-Normandie','Y'),('1028','250','Bourgogne','Y'),('1029','250','Brittany','Y'),('103','24','Benguela','Y'),('1030','250','Centre','Y'),('1031','250','Champagne-Ardenne','Y'),('1032','250','Corsica','Y'),('1033','250','Franche-Comté','Y'),('1034','250','Haute-Normandie','Y'),('1035','250','Île-de-France','Y'),('1036','250','Languedoc-Roussillon','Y'),('1037','250','Limousin','Y'),('1038','250','Lorraine','Y'),('1039','250','Midi-Pyrénées','Y'),('104','24','Bié','Y'),('1040','250','Nord-Pas-de-Calais','Y'),('1041','250','Pays de la Loire','Y'),('1042','250','Picardie','Y'),('1043','250','Poitou-Charentes','Y'),('1044','250','Provence-Alpes-Côte dʼAzur','Y'),('1045','250','Rhône-Alpes','Y'),('1046','250','Alsace','Y'),('1047','254','Guyane','Y'),('1048','260','Saint-Paul-et-Amsterdam','Y'),('1049','260','Crozet','Y'),('105','24','Cabinda','Y'),('1050','260','Kerguelen','Y'),('1051','260','Terre Adélie','Y'),('1052','260','Îles Éparses','Y'),('1053','262','Ali Sabieh','Y'),('1054','262','Dikhil   ','Y'),('1055','262','Djibouti  ','Y'),('1056','262','Obock','Y'),('1057','262','Tadjourah','Y'),('1058','262','Dikhil','Y'),('1059','262','Djibouti','Y'),('106','24','Cuando Cubango','Y'),('1060','262','Arta','Y'),('1061','266','Estuaire','Y'),('1062','266','Haut-Ogooué','Y'),('1063','266','Moyen-Ogooué','Y'),('1064','266','Ngounié','Y'),('1065','266','Nyanga','Y'),('1066','266','Ogooué-Ivindo','Y'),('1067','266','Ogooué-Lolo','Y'),('1068','266','Ogooué-Maritime','Y'),('1069','266','Woleu-Ntem','Y'),('107','24','Cuanza Norte','Y'),('1070','268','Ajaria','Y'),('1071','268','Tʼbilisi','Y'),('1072','268','Abkhazia','Y'),('1073','268','Kvemo Kartli','Y'),('1074','268','Kakheti','Y'),('1075','268','Guria','Y'),('1076','268','Imereti','Y'),('1077','268','Shida Kartli','Y'),('1078','268','Mtskheta-Mtianeti','Y'),('1079','268','Racha-Lechkhumi and Kvemo Svaneti','Y'),('108','24','Cuanza Sul','Y'),('1080','268','Samegrelo and Zemo Svaneti','Y'),('1081','268','Samtskhe-Javakheti','Y'),('1082','270','Banjul','Y'),('1083','270','Lower River','Y'),('1084','270','Central River','Y'),('1085','270','Upper River','Y'),('1086','270','Western','Y'),('1087','270','North Bank','Y'),('1088','275','Gaza Strip','Y'),('1089','275','West Bank','Y'),('109','24','Cunene','Y'),('1090','276','Baden-Württemberg','Y'),('1091','276','Bavaria','Y'),('1092','276','Bremen','Y'),('1093','276','Hamburg','Y'),('1094','276','Hesse','Y'),('1095','276','Lower Saxony','Y'),('1096','276','North Rhine-Westphalia','Y'),('1097','276','Rhineland-Palatinate','Y'),('1098','276','Saarland','Y'),('1099','276','Schleswig-Holstein','Y'),('11','4','Kabul','Y'),('110','24','Huambo','Y'),('1100','276','Brandenburg','Y'),('1101','276','Mecklenburg-Vorpommern','Y'),('1102','276','Saxony','Y'),('1103','276','Saxony-Anhalt','Y'),('1104','276','Thuringia','Y'),('1105','276','Berlin','Y'),('1106','288','Greater Accra','Y'),('1107','288','Ashanti','Y'),('1108','288','Brong-Ahafo Region','Y'),('1109','288','Central','Y'),('111','24','Huíla','Y'),('1110','288','Eastern','Y'),('1111','288','Northern','Y'),('1112','288','Volta','Y'),('1113','288','Western','Y'),('1114','288','Upper East','Y'),('1115','288','Upper West','Y'),('1116','292','Gibraltar','Y'),('1117','296','Line Islands','Y'),('1118','296','Gilbert Islands','Y'),('1119','296','Phoenix Islands','Y'),('112','24','Luanda','Y'),('1120','300','Mount Athos','Y'),('1121','300','East Macedonia and Thrace','Y'),('1122','300','Central Macedonia','Y'),('1123','300','West Macedonia','Y'),('1124','300','Thessaly','Y'),('1125','300','Epirus','Y'),('1126','300','Ionian Islands','Y'),('1127','300','West Greece','Y'),('1128','300','Central Greece','Y'),('1129','300','Peloponnese','Y'),('113','24','Malanje','Y'),('1130','300','Attica','Y'),('1131','300','North Aegean','Y'),('1132','300','South Aegean','Y'),('1133','300','Crete','Y'),('1134','304','Nordgrønland','Y'),('1135','304','Østgrønland','Y'),('1136','304','Vestgrønland','Y'),('1137','308','Saint Andrew','Y'),('1138','308','Saint David','Y'),('1139','308','Saint George','Y'),('114','24','Namibe','Y'),('1140','308','Saint John','Y'),('1141','308','Saint Mark','Y'),('1142','308','Saint Patrick','Y'),('1143','312','Guadeloupe','Y'),('1144','316','Guam','Y'),('1145','320','Alta Verapaz','Y'),('1146','320','Baja Verapaz','Y'),('1147','320','Chimaltenango','Y'),('1148','320','Chiquimula','Y'),('1149','320','El Progreso','Y'),('115','24','Moxico','Y'),('1150','320','Escuintla','Y'),('1151','320','Guatemala','Y'),('1152','320','Huehuetenango','Y'),('1153','320','Izabal','Y'),('1154','320','Jalapa','Y'),('1155','320','Jutiapa','Y'),('1156','320','Petén','Y'),('1157','320','Quetzaltenango','Y'),('1158','320','Quiché','Y'),('1159','320','Retalhuleu','Y'),('116','24','Uíge','Y'),('1160','320','Sacatepéquez','Y'),('1161','320','San Marcos','Y'),('1162','320','Santa Rosa','Y'),('1163','320','Sololá','Y'),('1164','320','Suchitepéquez','Y'),('1165','320','Totonicapán','Y'),('1166','320','Zacapa','Y'),('1167','324','Beyla','Y'),('1168','324','Boffa','Y'),('1169','324','Boké','Y'),('117','24','Zaire','Y'),('1170','324','Conakry','Y'),('1171','324','Dabola','Y'),('1172','324','Dalaba','Y'),('1173','324','Dinguiraye','Y'),('1174','324','Faranah','Y'),('1175','324','Forécariah','Y'),('1176','324','Fria','Y'),('1177','324','Gaoual','Y'),('1178','324','Guéckédou','Y'),('1179','324','Kankan','Y'),('118','24','Lunda Norte','Y'),('1180','324','Kérouané','Y'),('1181','324','Kindia','Y'),('1182','324','Kissidougou','Y'),('1183','324','Koundara','Y'),('1184','324','Kouroussa','Y'),('1185','324','Macenta','Y'),('1186','324','Mali','Y'),('1187','324','Mamou','Y'),('1188','324','Pita','Y'),('1189','324','Siguiri','Y'),('119','24','Lunda Sul','Y'),('1190','324','Télimélé','Y'),('1191','324','Tougué','Y'),('1192','324','Yomou','Y'),('1193','324','Coyah','Y'),('1194','324','Dubréka','Y'),('1195','324','Kankan','Y'),('1196','324','Koubia','Y'),('1197','324','Labé','Y'),('1198','324','Lélouma','Y'),('1199','324','Lola','Y'),('12','4','Kāpīsā','Y'),('120','24','Bengo','Y'),('1200','324','Mandiana','Y'),('1201','324','Nzérékoré','Y'),('1202','324','Siguiri','Y'),('1203','328','Barima-Waini','Y'),('1204','328','Cuyuni-Mazaruni','Y'),('1205','328','Demerara-Mahaica','Y'),('1206','328','East Berbice-Corentyne','Y'),('1207','328','Essequibo Islands-West Demerara','Y'),('1208','328','Mahaica-Berbice','Y'),('1209','328','Pomeroon-Supenaam','Y'),('121','28','Redonda','Y'),('1210','328','Potaro-Siparuni','Y'),('1211','328','Upper Demerara-Berbice','Y'),('1212','328','Upper Takutu-Upper Essequibo','Y'),('1213','332','Nord-Ouest','Y'),('1214','332','Artibonite','Y'),('1215','332','Centre','Y'),('1216','332','Nord','Y'),('1217','332','Nord-Est','Y'),('1218','332','Ouest','Y'),('1219','332','Sud','Y'),('122','28','Barbuda','Y'),('1220','332','Sud-Est','Y'),('1221','332','GrandʼAnse','Y'),('1222','332','Nippes','Y'),('1223','336','Vatican City','Y'),('1224','340','Atlántida','Y'),('1225','340','Choluteca','Y'),('1226','340','Colón','Y'),('1227','340','Comayagua','Y'),('1228','340','Copán','Y'),('1229','340','Cortés','Y'),('123','28','Saint George','Y'),('1230','340','El Paraíso','Y'),('1231','340','Francisco Morazán','Y'),('1232','340','Gracias a Dios','Y'),('1233','340','Intibucá','Y'),('1234','340','Islas de la Bahía','Y'),('1235','340','La Paz','Y'),('1236','340','Lempira','Y'),('1237','340','Ocotepeque','Y'),('1238','340','Olancho','Y'),('1239','340','Santa Bárbara','Y'),('124','28','Saint John','Y'),('1240','340','Valle','Y'),('1241','340','Yoro','Y'),('1242','344','Hong Kong','Y'),('1243','348','Bács-Kiskun','Y'),('1244','348','Baranya','Y'),('1245','348','Békés','Y'),('1246','348','Borsod-Abaúj-Zemplén','Y'),('1247','348','Budapest','Y'),('1248','348','Csongrád','Y'),('1249','348','Fejér','Y'),('125','28','Saint Mary','Y'),('1250','348','Győr-Moson-Sopron','Y'),('1251','348','Hajdú-Bihar','Y'),('1252','348','Heves','Y'),('1253','348','Komárom-Esztergom','Y'),('1254','348','Nógrád','Y'),('1255','348','Pest','Y'),('1256','348','Somogy','Y'),('1257','348','Szabolcs-Szatmár-Bereg','Y'),('1258','348','Jász-Nagykun-Szolnok','Y'),('1259','348','Tolna','Y'),('126','28','Saint Paul','Y'),('1260','348','Vas','Y'),('1261','348','Veszprém','Y'),('1262','348','Zala','Y'),('1263','352','Borgarfjardarsysla','Y'),('1264','352','Dalasysla','Y'),('1265','352','Eyjafjardarsysla','Y'),('1266','352','Gullbringusysla','Y'),('1267','352','Hafnarfjördur','Y'),('1268','352','Husavik','Y'),('1269','352','Isafjördur','Y'),('127','28','Saint Peter','Y'),('1270','352','Keflavik','Y'),('1271','352','Kjosarsysla','Y'),('1272','352','Kopavogur','Y'),('1273','352','Myrasysla','Y'),('1274','352','Neskaupstadur','Y'),('1275','352','Nordur-Isafjardarsysla','Y'),('1276','352','Nordur-Mulasysla','Y'),('1277','352','Nordur-Tingeyjarsysla','Y'),('1278','352','Olafsfjördur','Y'),('1279','352','Rangarvallasysla','Y'),('128','28','Saint Philip','Y'),('1280','352','Reykjavík','Y'),('1281','352','Saudarkrokur','Y'),('1282','352','Seydisfjordur','Y'),('1283','352','Siglufjordur','Y'),('1284','352','Skagafjardarsysla','Y'),('1285','352','Snafellsnes- og Hnappadalssysla','Y'),('1286','352','Strandasysla','Y'),('1287','352','Sudur-Mulasysla','Y'),('1288','352','Sudur-Tingeyjarsysla','Y'),('1289','352','Vestmannaeyjar','Y'),('129','31','Abşeron','Y'),('1290','352','Vestur-Bardastrandarsysla','Y'),('1291','352','Vestur-Hunavatnssysla','Y'),('1292','352','Vestur-Isafjardarsysla','Y'),('1293','352','Vestur-Skaftafellssysla','Y'),('1294','352','East','Y'),('1295','352','Capital Region','Y'),('1296','352','Northeast','Y'),('1297','352','Northwest','Y'),('1298','352','South','Y'),('1299','352','Southern Peninsula','Y'),('13','4','Lowgar','Y'),('130','31','Ağcabǝdi','Y'),('1300','352','Westfjords','Y'),('1301','352','West','Y'),('1302','356','Andaman and Nicobar Islands','Y'),('1303','356','Andhra Pradesh','Y'),('1304','356','Assam','Y'),('1305','356','Bihar','Y'),('1306','356','Chandīgarh','Y'),('1307','356','Dādra and Nagar Haveli','Y'),('1308','356','NCT','Y'),('1309','356','Gujarāt','Y'),('131','31','Ağdam','Y'),('1310','356','Haryana','Y'),('1311','356','Himāchal Pradesh','Y'),('1312','356','Kashmir','Y'),('1313','356','Kerala','Y'),('1314','356','Laccadives','Y'),('1315','356','Madhya Pradesh ','Y'),('1316','356','Mahārāshtra','Y'),('1317','356','Manipur','Y'),('1318','356','Meghālaya','Y'),('1319','356','Karnātaka','Y'),('132','31','Ağdaş','Y'),('1320','356','Nāgāland','Y'),('1321','356','Orissa','Y'),('1322','356','Pondicherry','Y'),('1323','356','Punjab','Y'),('1324','356','State of Rājasthān','Y'),('1325','356','Tamil Nādu','Y'),('1326','356','Tripura','Y'),('1327','356','Uttar Pradesh','Y'),('1328','356','Bengal','Y'),('1329','356','Sikkim','Y'),('133','31','Ağstafa','Y'),('1330','356','Arunāchal Pradesh','Y'),('1331','356','Mizoram','Y'),('1332','356','Daman and Diu','Y'),('1333','356','Goa','Y'),('1334','356','Bihār','Y'),('1335','356','Madhya Pradesh','Y'),('1336','356','Uttar Pradesh','Y'),('1337','356','Chhattisgarh','Y'),('1338','356','Jharkhand','Y'),('1339','356','Uttarakhand','Y'),('134','31','Ağsu','Y'),('1340','360','Aceh','Y'),('1341','360','Bali','Y'),('1342','360','Bengkulu','Y'),('1343','360','Jakarta Raya','Y'),('1344','360','Jambi','Y'),('1345','360','Jawa Barat','Y'),('1346','360','Central Java','Y'),('1347','360','East Java','Y'),('1348','360','Yogyakarta ','Y'),('1349','360','West Kalimantan','Y'),('135','31','Əli Bayramli','Y'),('1350','360','South Kalimantan','Y'),('1351','360','Kalimantan Tengah','Y'),('1352','360','East Kalimantan','Y'),('1353','360','Lampung','Y'),('1354','360','Nusa Tenggara Barat','Y'),('1355','360','East Nusa Tenggara','Y'),('1356','360','Central Sulawesi','Y'),('1357','360','Sulawesi Tenggara','Y'),('1358','360','Sulawesi Utara','Y'),('1359','360','West Sumatra','Y'),('136','31','Astara','Y'),('1360','360','Sumatera Selatan','Y'),('1361','360','North Sumatra','Y'),('1362','360','Timor Timur','Y'),('1363','360','Maluku ','Y'),('1364','360','Maluku Utara','Y'),('1365','360','West Java','Y'),('1366','360','North Sulawesi','Y'),('1367','360','South Sumatra','Y'),('1368','360','Banten','Y'),('1369','360','Gorontalo','Y'),('137','31','Baki','Y'),('1370','360','Bangka-Belitung','Y'),('1371','360','Papua','Y'),('1372','360','Riau','Y'),('1373','360','South Sulawesi','Y'),('1374','360','Irian Jaya Barat','Y'),('1375','360','Riau Islands','Y'),('1376','360','Sulawesi Barat','Y'),('1377','364','Āz̄ārbāyjān-e Gharbī','Y'),('1378','364','Chahār Maḩāll va Bakhtīārī','Y'),('1379','364','Sīstān va Balūchestān','Y'),('138','31','Balakǝn','Y'),('1380','364','Kohgīlūyeh va Būyer Aḩmad','Y'),('1381','364','Fārs Province','Y'),('1382','364','Gīlān','Y'),('1383','364','Hamadān','Y'),('1384','364','Īlām','Y'),('1385','364','Hormozgān Province','Y'),('1386','364','Kerman','Y'),('1387','364','Kermānshāh','Y'),('1388','364','Khūzestān','Y'),('1389','364','Kordestān','Y'),('139','31','Bǝrdǝ','Y'),('1390','364','Mazandaran','Y'),('1391','364','Markazi','Y'),('1392','364','Zanjan','Y'),('1393','364','Bushehr Province','Y'),('1394','364','Lorestān','Y'),('1395','364','Markazi','Y'),('1396','364','Semnān Province','Y'),('1397','364','Tehrān','Y'),('1398','364','Zanjan','Y'),('1399','364','Eşfahān','Y'),('14','4','Nangarhār','Y'),('140','31','Beylǝqan','Y'),('1400','364','Kermān','Y'),('1401','364','Ostan-e Khorasan-e Razavi','Y'),('1402','364','Yazd','Y'),('1403','364','Ardabīl','Y'),('1404','364','Āz̄ārbāyjān-e Sharqī','Y'),('1405','364','Markazi Province','Y'),('1406','364','Māzandarān Province','Y'),('1407','364','Zanjan Province','Y'),('1408','364','Golestān','Y'),('1409','364','Qazvīn','Y'),('141','31','Bilǝsuvar','Y'),('1410','364','Qom','Y'),('1411','364','Yazd','Y'),('1412','364','Khorāsān-e Jonūbī','Y'),('1413','364','Razavi Khorasan Province','Y'),('1414','364','Khorāsān-e Shomālī','Y'),('1415','368','Anbar','Y'),('1416','368','Al Başrah','Y'),('1417','368','Al Muthanná','Y'),('1418','368','Al Qādisīyah','Y'),('1419','368','As Sulaymānīyah','Y'),('142','31','Cǝbrayıl','Y'),('1420','368','Bābil','Y'),('1421','368','Baghdād','Y'),('1422','368','Dahūk','Y'),('1423','368','Dhi Qar','Y'),('1424','368','Diyala','Y'),('1425','368','Arbīl','Y'),('1426','368','Karbalāʼ','Y'),('1427','368','At Taʼmīm','Y'),('1428','368','Maysan','Y'),('1429','368','Nīnawá','Y'),('143','31','Cǝlilabad','Y'),('1430','368','Wāsiţ','Y'),('1431','368','An Najaf','Y'),('1432','368','Şalāḩ ad Dīn','Y'),('1433','372','Carlow','Y'),('1434','372','Cavan','Y'),('1435','372','County Clare','Y'),('1436','372','Cork','Y'),('1437','372','Donegal','Y'),('1438','372','Galway','Y'),('1439','372','County Kerry','Y'),('144','31','Daşkǝsǝn','Y'),('1440','372','County Kildare','Y'),('1441','372','County Kilkenny','Y'),('1442','372','Leitrim','Y'),('1443','372','Laois','Y'),('1444','372','Limerick','Y'),('1445','372','County Longford','Y'),('1446','372','County Louth','Y'),('1447','372','County Mayo','Y'),('1448','372','County Meath','Y'),('1449','372','Monaghan','Y'),('145','31','Dǝvǝçi','Y'),('1450','372','County Offaly','Y'),('1451','372','County Roscommon','Y'),('1452','372','County Sligo','Y'),('1453','372','County Waterford','Y'),('1454','372','County Westmeath','Y'),('1455','372','County Wexford','Y'),('1456','372','County Wicklow','Y'),('1457','372','Dún Laoghaire-Rathdown','Y'),('1458','372','Fingal County','Y'),('1459','372','Tipperary North Riding','Y'),('146','31','Füzuli','Y'),('1460','372','South Dublin','Y'),('1461','372','Tipperary South Riding','Y'),('1462','376','HaDarom','Y'),('1463','376','HaMerkaz','Y'),('1464','376','Northern District','Y'),('1465','376','H̱efa','Y'),('1466','376','Tel Aviv','Y'),('1467','376','Yerushalayim','Y'),('1468','380','Abruzzo','Y'),('1469','380','Basilicate','Y'),('147','31','Gǝdǝbǝy','Y'),('1470','380','Calabria','Y'),('1471','380','Campania','Y'),('1472','380','Emilia-Romagna','Y'),('1473','380','Friuli','Y'),('1474','380','Lazio','Y'),('1475','380','Liguria','Y'),('1476','380','Lombardy','Y'),('1477','380','The Marches','Y'),('1478','380','Molise','Y'),('1479','380','Piedmont','Y'),('148','31','Gǝncǝ','Y'),('1480','380','Apulia','Y'),('1481','380','Sardinia','Y'),('1482','380','Sicily','Y'),('1483','380','Tuscany','Y'),('1484','380','Trentino-Alto Adige','Y'),('1485','380','Umbria','Y'),('1486','380','Aosta Valley','Y'),('1487','380','Veneto','Y'),('1488','384','Valparaíso Region','Y'),('1489','384','Antofagasta Region','Y'),('149','31','Goranboy','Y'),('1490','384','Araucanía Region','Y'),('1491','384','Atacama Region','Y'),('1492','384','Biobío Region','Y'),('1493','384','Coquimbo Region','Y'),('1494','384','Maule Region','Y'),('1495','384','Santiago Metropolitan Region','Y'),('1496','384','Danane','Y'),('1497','384','Divo','Y'),('1498','384','Ferkessedougou','Y'),('1499','384','Gagnoa','Y'),('15','4','Nīmrūz','Y'),('150','31','Göyçay','Y'),('1500','384','Katiola','Y'),('1501','384','Korhogo','Y'),('1502','384','Odienne','Y'),('1503','384','Seguela','Y'),('1504','384','Touba','Y'),('1505','384','Bongouanou','Y'),('1506','384','Issia','Y'),('1507','384','Lakota','Y'),('1508','384','Mankono','Y'),('1509','384','Oume','Y'),('151','31','Hacıqabul','Y'),('1510','384','Soubre','Y'),('1511','384','Tingrela','Y'),('1512','384','Zuenoula','Y'),('1513','384','Bangolo','Y'),('1514','384','Beoumi','Y'),('1515','384','Bondoukou','Y'),('1516','384','Bouafle','Y'),('1517','384','Bouake','Y'),('1518','384','Daloa','Y'),('1519','384','Daoukro','Y'),('152','31','İmişli','Y'),('1520','384','Duekoue','Y'),('1521','384','Grand-Lahou','Y'),('1522','384','Man','Y'),('1523','384','Mbahiakro','Y'),('1524','384','Sakassou','Y'),('1525','384','San Pedro','Y'),('1526','384','Sassandra','Y'),('1527','384','Sinfra','Y'),('1528','384','Tabou','Y'),('1529','384','Tanda','Y'),('153','31','İsmayıllı','Y'),('1530','384','Tiassale','Y'),('1531','384','Toumodi','Y'),('1532','384','Vavoua','Y'),('1533','384','Abidjan','Y'),('1534','384','Aboisso','Y'),('1535','384','Adiake','Y'),('1536','384','Alepe','Y'),('1537','384','Bocanda','Y'),('1538','384','Dabou','Y'),('1539','384','Dimbokro','Y'),('154','31','Kǝlbǝcǝr','Y'),('1540','384','Grand-Bassam','Y'),('1541','384','Guiglo','Y'),('1542','384','Jacqueville','Y'),('1543','384','Tiebissou','Y'),('1544','384','Toulepleu','Y'),('1545','384','Yamoussoukro','Y'),('1546','384','Agnéby','Y'),('1547','384','Bafing','Y'),('1548','384','Bas-Sassandra','Y'),('1549','384','Denguélé','Y'),('155','31','Kürdǝmir','Y'),('1550','384','Dix-Huit Montagnes','Y'),('1551','384','Fromager','Y'),('1552','384','Haut-Sassandra','Y'),('1553','384','Lacs','Y'),('1554','384','Lagunes','Y'),('1555','384','Marahoué','Y'),('1556','384','Moyen-Cavally','Y'),('1557','384','Moyen-Comoé','Y'),('1558','384','Nʼzi-Comoé','Y'),('1559','384','Savanes','Y'),('156','31','Laçın','Y'),('1560','384','Sud-Bandama','Y'),('1561','384','Sud-Comoé','Y'),('1562','384','Vallée du Bandama','Y'),('1563','384','Worodougou','Y'),('1564','384','Zanzan','Y'),('1565','388','Clarendon','Y'),('1566','388','Hanover Parish','Y'),('1567','388','Manchester','Y'),('1568','388','Portland','Y'),('1569','388','Saint Andrew','Y'),('157','31','Lǝnkǝran','Y'),('1570','388','Saint Ann','Y'),('1571','388','Saint Catherine','Y'),('1572','388','St. Elizabeth','Y'),('1573','388','Saint James','Y'),('1574','388','Saint Mary','Y'),('1575','388','Saint Thomas','Y'),('1576','388','Trelawny','Y'),('1577','388','Westmoreland','Y'),('1578','388','Kingston','Y'),('1579','392','Aichi','Y'),('158','31','Lǝnkǝran Şǝhǝri','Y'),('1580','392','Akita','Y'),('1581','392','Aomori','Y'),('1582','392','Chiba','Y'),('1583','392','Ehime','Y'),('1584','392','Fukui','Y'),('1585','392','Fukuoka','Y'),('1586','392','Fukushima','Y'),('1587','392','Gifu','Y'),('1588','392','Gumma','Y'),('1589','392','Hiroshima','Y'),('159','31','Lerik','Y'),('1590','392','Hokkaidō','Y'),('1591','392','Hyōgo','Y'),('1592','392','Ibaraki','Y'),('1593','392','Ishikawa','Y'),('1594','392','Iwate','Y'),('1595','392','Kagawa','Y'),('1596','392','Kagoshima','Y'),('1597','392','Kanagawa','Y'),('1598','392','Kōchi','Y'),('1599','392','Kumamoto','Y'),('16','4','Orūzgān','Y'),('160','31','Masallı','Y'),('1600','392','Kyōto','Y'),('1601','392','Mie','Y'),('1602','392','Miyagi','Y'),('1603','392','Miyazaki','Y'),('1604','392','Nagano','Y'),('1605','392','Nagasaki','Y'),('1606','392','Nara','Y'),('1607','392','Niigata','Y'),('1608','392','Ōita','Y'),('1609','392','Okayama','Y'),('161','31','Mingǝcevir','Y'),('1610','392','Ōsaka','Y'),('1611','392','Saga','Y'),('1612','392','Saitama','Y'),('1613','392','Shiga','Y'),('1614','392','Shimane','Y'),('1615','392','Shizuoka','Y'),('1616','392','Tochigi','Y'),('1617','392','Tokushima','Y'),('1618','392','Tōkyō','Y'),('1619','392','Tottori','Y'),('162','31','Naftalan','Y'),('1620','392','Toyama','Y'),('1621','392','Wakayama','Y'),('1622','392','Yamagata','Y'),('1623','392','Yamaguchi','Y'),('1624','392','Yamanashi','Y'),('1625','392','Okinawa','Y'),('1626','398','Almaty','Y'),('1627','398','Almaty Qalasy','Y'),('1628','398','Aqmola','Y'),('1629','398','Aqtöbe','Y'),('163','31','Nakhichevan','Y'),('1630','398','Astana Qalasy','Y'),('1631','398','Atyraū','Y'),('1632','398','Batys Qazaqstan','Y'),('1633','398','Bayqongyr Qalasy','Y'),('1634','398','Mangghystaū','Y'),('1635','398','Ongtüstik Qazaqstan','Y'),('1636','398','Pavlodar','Y'),('1637','398','Qaraghandy','Y'),('1638','398','Qostanay','Y'),('1639','398','Qyzylorda','Y'),('164','31','Neftçala','Y'),('1640','398','East Kazakhstan','Y'),('1641','398','Soltüstik Qazaqstan','Y'),('1642','398','Zhambyl','Y'),('1643','400','Balqa','Y'),('1644','400','Ma’an','Y'),('1645','400','Karak','Y'),('1646','400','Al Mafraq','Y'),('1647','400','Tafielah','Y'),('1648','400','Az Zarqa','Y'),('1649','400','Irbid','Y'),('165','31','Oğuz','Y'),('1650','400','Mafraq','Y'),('1651','400','Amman','Y'),('1652','400','Zarqa','Y'),('1653','400','Irbid','Y'),('1654','400','Ma’an','Y'),('1655','400','Ajlun','Y'),('1656','400','Aqaba','Y'),('1657','400','Jerash','Y'),('1658','400','Madaba','Y'),('1659','404','Central','Y'),('166','31','Qǝbǝlǝ','Y'),('1660','404','Coast','Y'),('1661','404','Eastern','Y'),('1662','404','Nairobi Area','Y'),('1663','404','North-Eastern','Y'),('1664','404','Nyanza','Y'),('1665','404','Rift Valley','Y'),('1666','404','Western','Y'),('1667','408','Chagang-do','Y'),('1668','408','Hamgyŏng-namdo','Y'),('1669','408','Hwanghae-namdo','Y'),('167','31','Qǝx','Y'),('1670','408','Hwanghae-bukto','Y'),('1671','408','Kaesŏng-si','Y'),('1672','408','Gangwon','Y'),('1673','408','Pʼyŏngan-bukto','Y'),('1674','408','Pʼyŏngyang-si','Y'),('1675','408','Yanggang-do','Y'),('1676','408','Nampʼo-si','Y'),('1677','408','Pʼyŏngan-namdo','Y'),('1678','408','Hamgyŏng-bukto','Y'),('1679','408','Najin Sŏnbong-si','Y'),('168','31','Qazax','Y'),('1680','410','Jeju','Y'),('1681','410','North Jeolla','Y'),('1682','410','North Chungcheong','Y'),('1683','410','Gangwon','Y'),('1684','410','Busan','Y'),('1685','410','Seoul','Y'),('1686','410','Incheon','Y'),('1687','410','Gyeonggi','Y'),('1688','410','North Gyeongsang','Y'),('1689','410','Daegu','Y'),('169','31','Qobustan','Y'),('1690','410','South Jeolla','Y'),('1691','410','South Chungcheong','Y'),('1692','410','Gwangju','Y'),('1693','410','Daejeon','Y'),('1694','410','South Gyeongsang','Y'),('1695','410','Ulsan','Y'),('1696','414','Muḩāfaz̧atalWafrah','Y'),('1697','414','Al ‘Āşimah','Y'),('1698','414','Al Aḩmadī','Y'),('1699','414','Al Jahrāʼ','Y'),('17','4','Kandahār','Y'),('170','31','Quba','Y'),('1700','414','Al Farwaniyah','Y'),('1701','414','Ḩawallī','Y'),('1702','417','Bishkek','Y'),('1703','417','Chüy','Y'),('1704','417','Jalal-Abad','Y'),('1705','417','Naryn','Y'),('1706','417','Talas','Y'),('1707','417','Ysyk-Köl','Y'),('1708','417','Osh','Y'),('1709','417','Batken','Y'),('171','31','Qubadlı','Y'),('1710','418','Attapu','Y'),('1711','418','Champasak','Y'),('1712','418','Houaphan','Y'),('1713','418','Khammouan','Y'),('1714','418','Louang Namtha','Y'),('1715','418','Louangphrabang','Y'),('1716','418','Oudômxai','Y'),('1717','418','Phongsali','Y'),('1718','418','Saravan','Y'),('1719','418','Savannakhet','Y'),('172','31','Qusar','Y'),('1720','418','Vientiane','Y'),('1721','418','Xiagnabouli','Y'),('1722','418','Xiangkhoang','Y'),('1723','418','Khammouan','Y'),('1724','418','Loungnamtha','Y'),('1725','418','Louangphabang','Y'),('1726','418','Phôngsali','Y'),('1727','418','Salavan','Y'),('1728','418','Savannahkhét','Y'),('1729','418','Bokèo','Y'),('173','31','Saatlı','Y'),('1730','418','Bolikhamxai','Y'),('1731','418','Viangchan','Y'),('1732','418','Xékong','Y'),('1733','418','Viangchan','Y'),('1734','422','Béqaa','Y'),('1735','422','Liban-Nord','Y'),('1736','422','Beyrouth','Y'),('1737','422','Mont-Liban','Y'),('1738','422','Liban-Sud','Y'),('1739','422','Nabatîyé','Y'),('174','31','Sabirabad','Y'),('1740','422','Béqaa','Y'),('1741','422','Liban-Nord','Y'),('1742','422','Aakkâr','Y'),('1743','422','Baalbek-Hermel','Y'),('1744','426','Balzers Commune','Y'),('1745','426','Eschen Commune','Y'),('1746','426','Gamprin Commune','Y'),('1747','426','Mauren Commune','Y'),('1748','426','Planken Commune','Y'),('1749','426','Ruggell Commune','Y'),('175','31','Şǝki','Y'),('1750','426','Berea District','Y'),('1751','426','Butha-Buthe District','Y'),('1752','426','Leribe District','Y'),('1753','426','Mafeteng','Y'),('1754','426','Maseru','Y'),('1755','426','Mohaleʼs Hoek','Y'),('1756','426','Mokhotlong','Y'),('1757','426','Qachaʼs Nek','Y'),('1758','426','Quthing District','Y'),('1759','426','Thaba-Tseka District','Y'),('176','31','Şǝki','Y'),('1760','428','Dobeles Rajons','Y'),('1761','428','Aizkraukles Rajons','Y'),('1762','428','Alūksnes Rajons','Y'),('1763','428','Balvu Rajons','Y'),('1764','428','Bauskas Rajons','Y'),('1765','428','Cēsu Rajons','Y'),('1766','428','Daugavpils','Y'),('1767','428','Daugavpils Rajons','Y'),('1768','428','Dobeles Rajons','Y'),('1769','428','Gulbenes Rajons','Y'),('177','31','Salyan','Y'),('1770','428','Jēkabpils Rajons','Y'),('1771','428','Jelgava','Y'),('1772','428','Jelgavas Rajons','Y'),('1773','428','Jūrmala','Y'),('1774','428','Krāslavas Rajons','Y'),('1775','428','Kuldīgas Rajons','Y'),('1776','428','Liepāja','Y'),('1777','428','Liepājas Rajons','Y'),('1778','428','Limbažu Rajons','Y'),('1779','428','Ludzas Rajons','Y'),('178','31','Şamaxı','Y'),('1780','428','Madonas Rajons','Y'),('1781','428','Ogres Rajons','Y'),('1782','428','Preiļu Rajons','Y'),('1783','428','Rēzekne','Y'),('1784','428','Rēzeknes Rajons','Y'),('1785','428','Rīga','Y'),('1786','428','Rīgas Rajons','Y'),('1787','428','Saldus Rajons','Y'),('1788','428','Talsu Rajons','Y'),('1789','428','Tukuma Rajons','Y'),('179','31','Şǝmkir','Y'),('1790','428','Valkas Rajons','Y'),('1791','428','Valmieras Rajons','Y'),('1792','428','Ventspils','Y'),('1793','428','Ventspils Rajons','Y'),('1794','430','Bong','Y'),('1795','430','Grand Jide','Y'),('1796','430','Grand Cape Mount','Y'),('1797','430','Lofa','Y'),('1798','430','Nimba','Y'),('1799','430','Sinoe','Y'),('18','4','Kunduz Province','Y'),('180','31','Samux','Y'),('1800','430','Grand Bassa County','Y'),('1801','430','Grand Cape Mount','Y'),('1802','430','Maryland','Y'),('1803','430','Montserrado','Y'),('1804','430','Bomi','Y'),('1805','430','Grand Kru','Y'),('1806','430','Margibi','Y'),('1807','430','River Cess','Y'),('1808','430','Grand Gedeh','Y'),('1809','430','Lofa','Y'),('181','31','Siyǝzǝn','Y'),('1810','430','Gbarpolu','Y'),('1811','430','River Gee','Y'),('1812','434','Al Abyār','Y'),('1813','434','Al ‘Azīzīyah','Y'),('1814','434','Al Bayḑā’','Y'),('1815','434','Al Jufrah','Y'),('1816','434','Al Jumayl','Y'),('1817','434','Al Kufrah','Y'),('1818','434','Al Marj','Y'),('1819','434','Al Qarābūll','Y'),('182','31','Sumqayit','Y'),('1820','434','Al Qubbah','Y'),('1821','434','Al Ajaylāt','Y'),('1822','434','Ash Shāţiʼ','Y'),('1823','434','Az Zahra’','Y'),('1824','434','Banī Walīd','Y'),('1825','434','Bin Jawwād','Y'),('1826','434','Ghāt','Y'),('1827','434','Jādū','Y'),('1828','434','Jālū','Y'),('1829','434','Janzūr','Y'),('183','31','Şuşa','Y'),('1830','434','Masallatah','Y'),('1831','434','Mizdah','Y'),('1832','434','Murzuq','Y'),('1833','434','Nālūt','Y'),('1834','434','Qamīnis','Y'),('1835','434','Qaşr Bin Ghashīr','Y'),('1836','434','Sabhā','Y'),('1837','434','Şabrātah','Y'),('1838','434','Shaḩḩāt','Y'),('1839','434','Şurmān','Y'),('184','31','Şuşa Şəhəri','Y'),('1840','434','Tajura’ ','Y'),('1841','434','Tarhūnah','Y'),('1842','434','Ţubruq','Y'),('1843','434','Tūkrah','Y'),('1844','434','Zlīţan','Y'),('1845','434','Zuwārah','Y'),('1846','434','Ajdābiyā','Y'),('1847','434','Al Fātiḩ','Y'),('1848','434','Al Jabal al Akhḑar','Y'),('1849','434','Al Khums','Y'),('185','31','Tǝrtǝr','Y'),('1850','434','An Nuqāţ al Khams','Y'),('1851','434','Awbārī','Y'),('1852','434','Az Zāwiyah','Y'),('1853','434','Banghāzī','Y'),('1854','434','Darnah','Y'),('1855','434','Ghadāmis','Y'),('1856','434','Gharyān','Y'),('1857','434','Mişrātah','Y'),('1858','434','Sawfajjīn','Y'),('1859','434','Surt','Y'),('186','31','Tovuz','Y'),('1860','434','Ţarābulus','Y'),('1861','434','Yafran','Y'),('1862','438','Balzers','Y'),('1863','438','Eschen','Y'),('1864','438','Gamprin','Y'),('1865','438','Mauren','Y'),('1866','438','Planken','Y'),('1867','438','Ruggell','Y'),('1868','438','Schaan','Y'),('1869','438','Schellenberg','Y'),('187','31','Ucar','Y'),('1870','438','Triesen','Y'),('1871','438','Triesenberg','Y'),('1872','438','Vaduz','Y'),('1873','440','Alytaus Apskritis','Y'),('1874','440','Kauno Apskritis','Y'),('1875','440','Klaipėdos Apskritis','Y'),('1876','440','Marijampolės Apskritis','Y'),('1877','440','Panevėžio Apskritis','Y'),('1878','440','Šiaulių Apskritis','Y'),('1879','440','Tauragės Apskritis','Y'),('188','31','Xaçmaz','Y'),('1880','440','Telšių Apskritis','Y'),('1881','440','Utenos Apskritis','Y'),('1882','440','Vilniaus Apskritis','Y'),('1883','442','Diekirch','Y'),('1884','442','Grevenmacher','Y'),('1885','442','Luxembourg','Y'),('1886','446','Ilhas','Y'),('1887','446','Macau','Y'),('1888','450','Antsiranana','Y'),('1889','450','Fianarantsoa','Y'),('189','31','Xankǝndi','Y'),('1890','450','Mahajanga','Y'),('1891','450','Toamasina','Y'),('1892','450','Antananarivo','Y'),('1893','450','Toliara','Y'),('1894','454','Chikwawa','Y'),('1895','454','Chiradzulu','Y'),('1896','454','Chitipa','Y'),('1897','454','Thyolo','Y'),('1898','454','Dedza','Y'),('1899','454','Dowa','Y'),('19','4','Takhār','Y'),('190','31','Xanlar','Y'),('1900','454','Karonga','Y'),('1901','454','Kasungu','Y'),('1902','454','Lilongwe','Y'),('1903','454','Mangochi','Y'),('1904','454','Mchinji','Y'),('1905','454','Mzimba','Y'),('1906','454','Ntcheu','Y'),('1907','454','Nkhata Bay','Y'),('1908','454','Nkhotakota','Y'),('1909','454','Nsanje','Y'),('191','31','Xızı','Y'),('1910','454','Ntchisi','Y'),('1911','454','Rumphi','Y'),('1912','454','Salima','Y'),('1913','454','Zomba','Y'),('1914','454','Blantyre','Y'),('1915','454','Mwanza','Y'),('1916','454','Balaka','Y'),('1917','454','Likoma','Y'),('1918','454','Machinga','Y'),('1919','454','Mulanje','Y'),('192','31','Xocalı','Y'),('1920','454','Phalombe','Y'),('1921','458','Johor','Y'),('1922','458','Kedah','Y'),('1923','458','Kelantan','Y'),('1924','458','Melaka','Y'),('1925','458','Negeri Sembilan','Y'),('1926','458','Pahang','Y'),('1927','458','Perak','Y'),('1928','458','Perlis','Y'),('1929','458','Pulau Pinang','Y'),('193','31','Xocavǝnd','Y'),('1930','458','Sarawak','Y'),('1931','458','Selangor','Y'),('1932','458','Terengganu','Y'),('1933','458','Kuala Lumpur','Y'),('1934','458','Federal Territory of Labuan','Y'),('1935','458','Sabah','Y'),('1936','458','Putrajaya','Y'),('1937','462','Maale','Y'),('1938','462','Seenu','Y'),('1939','462','Alifu Atholhu','Y'),('194','31','Yardımlı','Y'),('1940','462','Lhaviyani Atholhu','Y'),('1941','462','Vaavu Atholhu','Y'),('1942','462','Laamu','Y'),('1943','462','Haa Alifu Atholhu','Y'),('1944','462','Thaa Atholhu','Y'),('1945','462','Meemu Atholhu','Y'),('1946','462','Raa Atholhu','Y'),('1947','462','Faafu Atholhu','Y'),('1948','462','Dhaalu Atholhu','Y'),('1949','462','Baa Atholhu','Y'),('195','31','Yevlax','Y'),('1950','462','Haa Dhaalu Atholhu','Y'),('1951','462','Shaviyani Atholhu','Y'),('1952','462','Noonu Atholhu','Y'),('1953','462','Kaafu Atholhu','Y'),('1954','462','Gaafu Alifu Atholhu','Y'),('1955','462','Gaafu Dhaalu Atholhu','Y'),('1956','462','Gnyaviyani Atoll','Y'),('1957','462','Alifu','Y'),('1958','462','Baa','Y'),('1959','462','Dhaalu','Y'),('196','31','Yevlax','Y'),('1960','462','Faafu','Y'),('1961','462','Gaafu Alifu','Y'),('1962','462','Gaafu Dhaalu','Y'),('1963','462','Haa Alifu','Y'),('1964','462','Haa Dhaalu','Y'),('1965','462','Kaafu','Y'),('1966','462','Lhaviyani','Y'),('1967','462','Maale','Y'),('1968','462','Meemu','Y'),('1969','462','Gnaviyani','Y'),('197','31','Zǝngilan','Y'),('1970','462','Noonu','Y'),('1971','462','Raa','Y'),('1972','462','Shaviyani','Y'),('1973','462','Thaa','Y'),('1974','462','Vaavu','Y'),('1975','466','Bamako','Y'),('1976','466','Gao','Y'),('1977','466','Kayes','Y'),('1978','466','Mopti','Y'),('1979','466','Ségou','Y'),('198','31','Zaqatala','Y'),('1980','466','Sikasso','Y'),('1981','466','Koulikoro','Y'),('1982','466','Tombouctou','Y'),('1983','466','Gao','Y'),('1984','466','Kidal','Y'),('1985','470','Malta','Y'),('1986','474','Martinique','Y'),('1987','478','Nouakchott','Y'),('1988','478','Hodh Ech Chargui','Y'),('1989','478','Hodh El Gharbi','Y'),('199','31','Zǝrdab','Y'),('1990','478','Assaba','Y'),('1991','478','Gorgol','Y'),('1992','478','Brakna','Y'),('1993','478','Trarza','Y'),('1994','478','Adrar','Y'),('1995','478','Dakhlet Nouadhibou','Y'),('1996','478','Tagant','Y'),('1997','478','Guidimaka','Y'),('1998','478','Tiris Zemmour','Y'),('1999','478','Inchiri','Y'),('2','4','Badghis Province','Y'),('20','4','Vardak','Y'),('200','32','Buenos Aires','Y'),('2000','480','Black River','Y'),('2001','480','Flacq','Y'),('2002','480','Grand Port','Y'),('2003','480','Moka','Y'),('2004','480','Pamplemousses','Y'),('2005','480','Plaines Wilhems','Y'),('2006','480','Port Louis','Y'),('2007','480','Rivière du Rempart','Y'),('2008','480','Savanne','Y'),('2009','480','Agalega Islands','Y'),('201','32','Catamarca','Y'),('2010','480','Cargados Carajos','Y'),('2011','480','Rodrigues','Y'),('2012','484','Aguascalientes','Y'),('2013','484','Baja California','Y'),('2014','484','Baja California Sur','Y'),('2015','484','Campeche','Y'),('2016','484','Chiapas','Y'),('2017','484','Chihuahua','Y'),('2018','484','Coahuila','Y'),('2019','484','Colima','Y'),('202','32','Chaco','Y'),('2020','484','The Federal District','Y'),('2021','484','Durango','Y'),('2022','484','Guanajuato','Y'),('2023','484','Guerrero','Y'),('2024','484','Hidalgo','Y'),('2025','484','Jalisco','Y'),('2026','484','México','Y'),('2027','484','Michoacán','Y'),('2028','484','Morelos','Y'),('2029','484','Nayarit','Y'),('203','32','Chubut','Y'),('2030','484','Nuevo León','Y'),('2031','484','Oaxaca','Y'),('2032','484','Puebla','Y'),('2033','484','Querétaro','Y'),('2034','484','Quintana Roo','Y'),('2035','484','San Luis Potosí','Y'),('2036','484','Sinaloa','Y'),('2037','484','Sonora','Y'),('2038','484','Tabasco','Y'),('2039','484','Tamaulipas','Y'),('204','32','Córdoba','Y'),('2040','484','Tlaxcala','Y'),('2041','484','Veracruz-Llave','Y'),('2042','484','Yucatán','Y'),('2043','484','Zacatecas','Y'),('2044','492','Monaco','Y'),('2045','496','Arhangay','Y'),('2046','496','Bayanhongor','Y'),('2047','496','Bayan-Ölgiy','Y'),('2048','496','East Aimak','Y'),('2049','496','East Gobi Aymag','Y'),('205','32','Corrientes','Y'),('2050','496','Middle Govĭ','Y'),('2051','496','Dzavhan','Y'),('2052','496','Govĭ-Altay','Y'),('2053','496','Hentiy','Y'),('2054','496','Hovd','Y'),('2055','496','Hövsgöl','Y'),('2056','496','South Gobi Aimak','Y'),('2057','496','South Hangay','Y'),('2058','496','Selenge','Y'),('2059','496','Sühbaatar','Y'),('206','32','Distrito Federal','Y'),('2060','496','Central Aimak','Y'),('2061','496','Uvs','Y'),('2062','496','Ulaanbaatar','Y'),('2063','496','Bulgan','Y'),('2064','496','Darhan Uul','Y'),('2065','496','Govĭ-Sumber','Y'),('2066','496','Orhon','Y'),('2067','498','Ungheni Judetul','Y'),('2068','498','Balti','Y'),('2069','498','Cahul','Y'),('207','32','Entre Ríos','Y'),('2070','498','Stinga Nistrului','Y'),('2071','498','Edinet','Y'),('2072','498','Găgăuzia','Y'),('2073','498','Lapusna','Y'),('2074','498','Orhei','Y'),('2075','498','Soroca','Y'),('2076','498','Tighina','Y'),('2077','498','Ungheni','Y'),('2078','498','Chişinău','Y'),('2079','498','Stînga Nistrului','Y'),('208','32','Formosa','Y'),('2080','498','Raionul Anenii Noi','Y'),('2081','498','Bălţi','Y'),('2082','498','Raionul Basarabeasca','Y'),('2083','498','Bender','Y'),('2084','498','Raionul Briceni','Y'),('2085','498','Raionul Cahul','Y'),('2086','498','Raionul Cantemir','Y'),('2087','498','Călăraşi','Y'),('2088','498','Căuşeni','Y'),('2089','498','Raionul Cimişlia','Y'),('209','32','Jujuy','Y'),('2090','498','Raionul Criuleni','Y'),('2091','498','Raionul Donduşeni','Y'),('2092','498','Raionul Drochia','Y'),('2093','498','Dubăsari','Y'),('2094','498','Raionul Edineţ','Y'),('2095','498','Raionul Făleşti','Y'),('2096','498','Raionul Floreşti','Y'),('2097','498','Raionul Glodeni','Y'),('2098','498','Raionul Hînceşti','Y'),('2099','498','Raionul Ialoveni','Y'),('21','4','Zabul Province','Y'),('210','32','La Pampa','Y'),('2100','498','Raionul Leova','Y'),('2101','498','Raionul Nisporeni','Y'),('2102','498','Raionul Ocniţa','Y'),('2103','498','Raionul Orhei','Y'),('2104','498','Raionul Rezina','Y'),('2105','498','Raionul Rîşcani','Y'),('2106','498','Raionul Sîngerei','Y'),('2107','498','Raionul Şoldăneşti','Y'),('2108','498','Raionul Soroca','Y'),('2109','498','Ştefan-Vodă','Y'),('211','32','La Rioja','Y'),('2110','498','Raionul Străşeni','Y'),('2111','498','Raionul Taraclia','Y'),('2112','498','Raionul Teleneşti','Y'),('2113','498','Raionul Ungheni','Y'),('2114','499','Opština Andrijevica','Y'),('2115','499','Opština Bar','Y'),('2116','499','Opština Berane','Y'),('2117','499','Opština Bijelo Polje','Y'),('2118','499','Opština Budva','Y'),('2119','499','Opština Cetinje','Y'),('212','32','Mendoza','Y'),('2120','499','Opština Danilovgrad','Y'),('2121','499','Opština Herceg Novi','Y'),('2122','499','Opština Kolašin','Y'),('2123','499','Opština Kotor','Y'),('2124','499','Opština Mojkovac','Y'),('2125','499','Opština Nikšić','Y'),('2126','499','Opština Plav','Y'),('2127','499','Opština Pljevlja','Y'),('2128','499','Opština Plužine','Y'),('2129','499','Opština Podgorica','Y'),('213','32','Misiones','Y'),('2130','499','Opština Rožaje','Y'),('2131','499','Opština Šavnik','Y'),('2132','499','Opština Tivat','Y'),('2133','499','Opština Ulcinj','Y'),('2134','499','Opština Žabljak','Y'),('2135','500','Saint Anthony','Y'),('2136','500','Saint Georges','Y'),('2137','500','Saint Peter','Y'),('2138','504','Agadir','Y'),('2139','504','Al Hoceïma','Y'),('214','32','Neuquén','Y'),('2140','504','Azizal','Y'),('2141','504','Ben Slimane','Y'),('2142','504','Beni Mellal','Y'),('2143','504','Boulemane','Y'),('2144','504','Casablanca','Y'),('2145','504','Chaouen','Y'),('2146','504','El Jadida','Y'),('2147','504','El Kelaa des Srarhna','Y'),('2148','504','Er Rachidia','Y'),('2149','504','Essaouira','Y'),('215','32','Río Negro','Y'),('2150','504','Fes','Y'),('2151','504','Figuig','Y'),('2152','504','Kenitra','Y'),('2153','504','Khemisset','Y'),('2154','504','Khenifra','Y'),('2155','504','Khouribga','Y'),('2156','504','Marrakech','Y'),('2157','504','Meknes','Y'),('2158','504','Nador','Y'),('2159','504','Ouarzazate','Y'),('216','32','Salta','Y'),('2160','504','Oujda','Y'),('2161','504','Rabat-Sale','Y'),('2162','504','Safi','Y'),('2163','504','Settat','Y'),('2164','504','Tanger','Y'),('2165','504','Tata','Y'),('2166','504','Taza','Y'),('2167','504','Tiznit','Y'),('2168','504','Guelmim','Y'),('2169','504','Ifrane','Y'),('217','32','San Juan','Y'),('2170','504','Laayoune','Y'),('2171','504','Tan-Tan','Y'),('2172','504','Taounate','Y'),('2173','504','Sidi Kacem','Y'),('2174','504','Taroudannt','Y'),('2175','504','Tetouan','Y'),('2176','504','Larache','Y'),('2177','504','Grand Casablanca','Y'),('2178','504','Fès-Boulemane','Y'),('2179','504','Marrakech-Tensift-Al Haouz','Y'),('218','32','San Luis','Y'),('2180','504','Meknès-Tafilalet','Y'),('2181','504','Rabat-Salé-Zemmour-Zaër','Y'),('2182','504','Chaouia-Ouardigha','Y'),('2183','504','Doukkala-Abda','Y'),('2184','504','Gharb-Chrarda-Beni Hssen','Y'),('2185','504','Guelmim-Es Smara','Y'),('2186','504','Oriental','Y'),('2187','504','Souss-Massa-Drâa','Y'),('2188','504','Tadla-Azilal','Y'),('2189','504','Tanger-Tétouan','Y'),('219','32','Santa Cruz','Y'),('2190','504','Taza-Al Hoceima-Taounate','Y'),('2191','504','Laâyoune-Boujdour-Sakia El Hamra','Y'),('2192','508','Cabo Delgado','Y'),('2193','508','Gaza','Y'),('2194','508','Inhambane','Y'),('2195','508','Maputo Province','Y'),('2196','508','Sofala','Y'),('2197','508','Nampula','Y'),('2198','508','Niassa','Y'),('2199','508','Tete','Y'),('22','4','Paktīkā','Y'),('220','32','Santa Fe','Y'),('2200','508','Zambézia','Y'),('2201','508','Manica','Y'),('2202','508','Maputo','Y'),('2203','512','Ad Dākhilīyah','Y'),('2204','512','Al Bāţinah','Y'),('2205','512','Al Wusţá','Y'),('2206','512','Ash Sharqīyah','Y'),('2207','512','Masqaţ','Y'),('2208','512','Musandam','Y'),('2209','512','Z̧ufār','Y'),('221','32','Santiago del Estero','Y'),('2210','512','Az̧ Z̧āhirah','Y'),('2211','512','Muḩāfaz̧at al Buraymī','Y'),('2212','516','Bethanien','Y'),('2213','516','Caprivi Oos','Y'),('2214','516','Kaokoland','Y'),('2215','516','Otjiwarongo','Y'),('2216','516','Outjo','Y'),('2217','516','Owambo','Y'),('2218','516','Khomas','Y'),('2219','516','Kavango','Y'),('222','32','Tierra del Fuego, Antártida e Islas del Atlán','Y'),('2220','516','Caprivi','Y'),('2221','516','Erongo','Y'),('2222','516','Hardap','Y'),('2223','516','Karas','Y'),('2224','516','Kunene','Y'),('2225','516','Ohangwena','Y'),('2226','516','Okavango','Y'),('2227','516','Omaheke','Y'),('2228','516','Omusati','Y'),('2229','516','Oshana','Y'),('223','32','Tucumán','Y'),('2230','516','Oshikoto','Y'),('2231','516','Otjozondjupa','Y'),('2232','520','Aiwo','Y'),('2233','520','Anabar','Y'),('2234','520','Anetan','Y'),('2235','520','Anibare','Y'),('2236','520','Baiti','Y'),('2237','520','Boe','Y'),('2238','520','Buada','Y'),('2239','520','Denigomodu','Y'),('224','36','Australian Capital Territory','Y'),('2240','520','Ewa','Y'),('2241','520','Ijuw','Y'),('2242','520','Meneng','Y'),('2243','520','Nibok','Y'),('2244','520','Uaboe','Y'),('2245','520','Yaren','Y'),('2246','524','Bāgmatī','Y'),('2247','524','Bherī','Y'),('2248','524','Dhawalāgiri','Y'),('2249','524','Gandakī','Y'),('225','36','New South Wales','Y'),('2250','524','Janakpur','Y'),('2251','524','Karnālī','Y'),('2252','524','Kosī','Y'),('2253','524','Lumbinī','Y'),('2254','524','Mahākālī','Y'),('2255','524','Mechī','Y'),('2256','524','Nārāyanī','Y'),('2257','524','Rāptī','Y'),('2258','524','Sagarmāthā','Y'),('2259','524','Setī','Y'),('226','36','Northern Territory','Y'),('2260','528','Provincie Drenthe','Y'),('2261','528','Provincie Friesland','Y'),('2262','528','Gelderland','Y'),('2263','528','Groningen','Y'),('2264','528','Limburg','Y'),('2265','528','North Brabant','Y'),('2266','528','North Holland','Y'),('2267','528','Utrecht','Y'),('2268','528','Zeeland','Y'),('2269','528','South Holland','Y'),('227','36','Queensland','Y'),('2270','528','Overijssel','Y'),('2271','528','Flevoland','Y'),('2272','530','Netherlands Antilles','Y'),('2273','533','Aruba','Y'),('2274','548','Ambrym','Y'),('2275','548','Aoba/Maéwo','Y'),('2276','548','Torba','Y'),('2277','548','Éfaté','Y'),('2278','548','Épi','Y'),('2279','548','Malakula','Y'),('228','36','South Australia','Y'),('2280','548','Paama','Y'),('2281','548','Pentecôte','Y'),('2282','548','Sanma','Y'),('2283','548','Shepherd','Y'),('2284','548','Tafea','Y'),('2285','548','Malampa','Y'),('2286','548','Penama','Y'),('2287','548','Shefa','Y'),('2288','554','Tasman','Y'),('2289','554','Auckland','Y'),('229','36','Tasmania','Y'),('2290','554','Bay of Plenty','Y'),('2291','554','Canterbury','Y'),('2292','554','Gisborne','Y'),('2293','554','Hawkeʼs Bay','Y'),('2294','554','Manawatu-Wanganui','Y'),('2295','554','Marlborough','Y'),('2296','554','Nelson','Y'),('2297','554','Northland','Y'),('2298','554','Otago','Y'),('2299','554','Southland','Y'),('23','4','Balkh','Y'),('230','36','Victoria','Y'),('2300','554','Taranaki','Y'),('2301','554','Waikato','Y'),('2302','554','Wellington','Y'),('2303','554','West Coast','Y'),('2304','558','Boaco','Y'),('2305','558','Carazo','Y'),('2306','558','Chinandega','Y'),('2307','558','Chontales','Y'),('2308','558','Estelí','Y'),('2309','558','Granada','Y'),('231','36','Western Australia','Y'),('2310','558','Jinotega','Y'),('2311','558','León','Y'),('2312','558','Madriz','Y'),('2313','558','Managua','Y'),('2314','558','Masaya','Y'),('2315','558','Matagalpa','Y'),('2316','558','Nueva Segovia','Y'),('2317','558','Río San Juan','Y'),('2318','558','Rivas','Y'),('2319','558','Ogun State','Y'),('232','40','Burgenland','Y'),('2320','558','Atlántico Norte','Y'),('2321','558','Atlántico Sur','Y'),('2322','562','Agadez','Y'),('2323','562','Diffa','Y'),('2324','562','Dosso','Y'),('2325','562','Maradi','Y'),('2326','562','Tahoua','Y'),('2327','562','Zinder','Y'),('2328','562','Niamey','Y'),('2329','562','Tillabéri','Y'),('233','40','Carinthia','Y'),('2330','566','Lagos','Y'),('2331','566','Abuja Federal Capital Territory','Y'),('2332','566','Ogun','Y'),('2333','566','Akwa Ibom','Y'),('2334','566','Cross River','Y'),('2335','566','Kaduna','Y'),('2336','566','Katsina','Y'),('2337','566','Anambra','Y'),('2338','566','Benue','Y'),('2339','566','Borno','Y'),('234','40','Lower Austria','Y'),('2340','566','Imo','Y'),('2341','566','Kano','Y'),('2342','566','Kwara','Y'),('2343','566','Niger','Y'),('2344','566','Oyo','Y'),('2345','566','Adamawa','Y'),('2346','566','Delta','Y'),('2347','566','Edo','Y'),('2348','566','Jigawa','Y'),('2349','566','Kebbi','Y'),('235','40','Upper Austria','Y'),('2350','566','Kogi','Y'),('2351','566','Osun','Y'),('2352','566','Taraba','Y'),('2353','566','Yobe','Y'),('2354','566','Abia','Y'),('2355','566','Bauchi','Y'),('2356','566','Enugu','Y'),('2357','566','Ondo','Y'),('2358','566','Plateau','Y'),('2359','566','Rivers','Y'),('236','40','Salzburg','Y'),('2360','566','Sokoto','Y'),('2361','566','Bayelsa','Y'),('2362','566','Ebonyi','Y'),('2363','566','Ekiti','Y'),('2364','566','Gombe','Y'),('2365','566','Nassarawa','Y'),('2366','566','Zamfara','Y'),('2367','570','Niue','Y'),('2368','574','Norfolk Island','Y'),('2369','578','Svalbard','Y'),('237','40','Styria','Y'),('2370','578','Akershus','Y'),('2371','578','Aust-Agder','Y'),('2372','578','Buskerud','Y'),('2373','578','Finnmark','Y'),('2374','578','Hedmark','Y'),('2375','578','Hordaland','Y'),('2376','578','Møre og Romsdal','Y'),('2377','578','Nordland','Y'),('2378','578','Nord-Trøndelag','Y'),('2379','578','Oppland','Y'),('238','40','Tyrol','Y'),('2380','578','Oslo county','Y'),('2381','578','Østfold','Y'),('2382','578','Rogaland','Y'),('2383','578','Sogn og Fjordane','Y'),('2384','578','Sør-Trøndelag','Y'),('2385','578','Telemark','Y'),('2386','578','Troms','Y'),('2387','578','Vest-Agder','Y'),('2388','578','Vestfold','Y'),('2389','583','Kosrae','Y'),('239','40','Vorarlberg','Y'),('2390','583','Pohnpei','Y'),('2391','583','Chuuk','Y'),('2392','583','Yap','Y'),('2393','584','Marshall Islands','Y'),('2394','585','State of Ngeremlengui','Y'),('2395','586','Federally Administered Tribal Areas','Y'),('2396','586','Balochistān','Y'),('2397','586','North West Frontier Province','Y'),('2398','586','Punjab','Y'),('2399','586','Sindh','Y'),('24','4','Jowzjān','Y'),('240','40','Vienna','Y'),('2400','586','Azad Kashmir','Y'),('2401','586','Gilgit-Baltistan','Y'),('2402','586','Islāmābād','Y'),('2403','591','Bocas del Toro','Y'),('2404','591','Chiriquí','Y'),('2405','591','Coclé','Y'),('2406','591','Colón','Y'),('2407','591','Darién','Y'),('2408','591','Herrera','Y'),('2409','591','Los Santos','Y'),('241','44','Bimini','Y'),('2410','591','Panamá','Y'),('2411','591','San Blas','Y'),('2412','591','Veraguas','Y'),('2413','598','Central','Y'),('2414','598','Gulf','Y'),('2415','598','Milne Bay','Y'),('2416','598','Northern','Y'),('2417','598','Southern Highlands','Y'),('2418','598','Western','Y'),('2419','598','Bougainville','Y'),('242','44','Cat Island','Y'),('2420','598','Chimbu','Y'),('2421','598','Eastern Highlands','Y'),('2422','598','East New Britain','Y'),('2423','598','East Sepik','Y'),('2424','598','Madang','Y'),('2425','598','Manus','Y'),('2426','598','Morobe','Y'),('2427','598','New Ireland','Y'),('2428','598','Western Highlands','Y'),('2429','598','West New Britain','Y'),('243','44','Inagua','Y'),('2430','598','Sandaun','Y'),('2431','598','Enga','Y'),('2432','598','National Capital','Y'),('2433','600','Alto Paraná','Y'),('2434','600','Amambay','Y'),('2435','600','Caaguazú','Y'),('2436','600','Caazapá','Y'),('2437','600','Central','Y'),('2438','600','Concepción','Y'),('2439','600','Cordillera','Y'),('244','44','Long Island','Y'),('2440','600','Guairá','Y'),('2441','600','Itapúa','Y'),('2442','600','Misiones','Y'),('2443','600','Ñeembucú','Y'),('2444','600','Paraguarí','Y'),('2445','600','Presidente Hayes','Y'),('2446','600','San Pedro','Y'),('2447','600','Canindeyú','Y'),('2448','600','Asunción','Y'),('2449','600','Departamento de Alto Paraguay','Y'),('245','44','Mayaguana','Y'),('2450','600','Boquerón','Y'),('2451','604','Amazonas','Y'),('2452','604','Ancash','Y'),('2453','604','Apurímac','Y'),('2454','604','Arequipa','Y'),('2455','604','Ayacucho','Y'),('2456','604','Cajamarca','Y'),('2457','604','Callao','Y'),('2458','604','Cusco','Y'),('2459','604','Huancavelica','Y'),('246','44','Ragged Island','Y'),('2460','604','Huanuco','Y'),('2461','604','Ica','Y'),('2462','604','Junín','Y'),('2463','604','La Libertad','Y'),('2464','604','Lambayeque','Y'),('2465','604','Lima','Y'),('2466','604','Provincia de Lima','Y'),('2467','604','Loreto','Y'),('2468','604','Madre de Dios','Y'),('2469','604','Moquegua','Y'),('247','44','Harbour Island, Eleuthera','Y'),('2470','604','Pasco','Y'),('2471','604','Piura','Y'),('2472','604','Puno','Y'),('2473','604','San Martín','Y'),('2474','604','Tacna','Y'),('2475','604','Tumbes','Y'),('2476','604','Ucayali','Y'),('2477','608','Abra','Y'),('2478','608','Agusan del Norte','Y'),('2479','608','Agusan del Sur','Y'),('248','44','North Abaco','Y'),('2480','608','Aklan','Y'),('2481','608','Albay','Y'),('2482','608','Antique','Y'),('2483','608','Bataan','Y'),('2484','608','Batanes','Y'),('2485','608','Batangas','Y'),('2486','608','Benguet','Y'),('2487','608','Bohol','Y'),('2488','608','Bukidnon','Y'),('2489','608','Bulacan','Y'),('249','44','Acklins','Y'),('2490','608','Cagayan','Y'),('2491','608','Camarines Norte','Y'),('2492','608','Camarines Sur','Y'),('2493','608','Camiguin','Y'),('2494','608','Capiz','Y'),('2495','608','Catanduanes','Y'),('2496','608','Cebu','Y'),('2497','608','Basilan','Y'),('2498','608','Eastern Samar','Y'),('2499','608','Davao del Sur','Y'),('25','4','Samangān','Y'),('250','44','City of Freeport, Grand Bahama','Y'),('2500','608','Davao Oriental','Y'),('2501','608','Ifugao','Y'),('2502','608','Ilocos Norte','Y'),('2503','608','Ilocos Sur','Y'),('2504','608','Iloilo','Y'),('2505','608','Isabela','Y'),('2506','608','Laguna','Y'),('2507','608','Lanao del Sur','Y'),('2508','608','La Union','Y'),('2509','608','Leyte','Y'),('251','44','South Andros','Y'),('2510','608','Marinduque','Y'),('2511','608','Masbate','Y'),('2512','608','Occidental Mindoro','Y'),('2513','608','Oriental Mindoro','Y'),('2514','608','Misamis Oriental','Y'),('2515','608','Mountain Province','Y'),('2516','608','Negros Oriental','Y'),('2517','608','Nueva Ecija','Y'),('2518','608','Nueva Vizcaya','Y'),('2519','608','Palawan','Y'),('252','44','Hope Town, Abaco','Y'),('2520','608','Pampanga','Y'),('2521','608','Pangasinan','Y'),('2522','608','Rizal','Y'),('2523','608','Romblon','Y'),('2524','608','Samar','Y'),('2525','608','Maguindanao','Y'),('2526','608','Cotabato City','Y'),('2527','608','Sorsogon','Y'),('2528','608','Southern Leyte','Y'),('2529','608','Sulu','Y'),('253','44','Mangrove Cay, Andros','Y'),('2530','608','Surigao del Norte','Y'),('2531','608','Surigao del Sur','Y'),('2532','608','Tarlac','Y'),('2533','608','Zambales','Y'),('2534','608','Zamboanga del Norte','Y'),('2535','608','Zamboanga del Sur','Y'),('2536','608','Northern Samar','Y'),('2537','608','Quirino','Y'),('2538','608','Siquijor','Y'),('2539','608','South Cotabato','Y'),('254','44','Mooreʼs Island, Abaco','Y'),('2540','608','Sultan Kudarat','Y'),('2541','608','Kalinga','Y'),('2542','608','Apayao','Y'),('2543','608','Tawi-Tawi','Y'),('2544','608','Angeles','Y'),('2545','608','Bacolod City','Y'),('2546','608','Compostela Valley','Y'),('2547','608','Baguio','Y'),('2548','608','Davao del Norte','Y'),('2549','608','Butuan','Y'),('255','44','Rum Cay','Y'),('2550','608','Guimaras','Y'),('2551','608','Lanao del Norte','Y'),('2552','608','Misamis Occidental','Y'),('2553','608','Caloocan','Y'),('2554','608','Cavite','Y'),('2555','608','Cebu City','Y'),('2556','608','Cotabato','Y'),('2557','608','Dagupan','Y'),('2558','608','Cagayan de Oro','Y'),('2559','608','Iligan','Y'),('256','44','North Andros','Y'),('2560','608','Davao','Y'),('2561','608','Las Piñas','Y'),('2562','608','Malabon','Y'),('2563','608','General Santos','Y'),('2564','608','Muntinlupa','Y'),('2565','608','Iloilo City','Y'),('2566','608','Navotas','Y'),('2567','608','Parañaque','Y'),('2568','608','Quezon City','Y'),('2569','608','Lapu-Lapu','Y'),('257','44','North Eleuthera','Y'),('2570','608','Taguig','Y'),('2571','608','Valenzuela','Y'),('2572','608','Lucena','Y'),('2573','608','Mandaue','Y'),('2574','608','Manila','Y'),('2575','608','Zamboanga Sibugay','Y'),('2576','608','Naga','Y'),('2577','608','Olongapo','Y'),('2578','608','Ormoc','Y'),('2579','608','Santiago','Y'),('258','44','South Eleuthera','Y'),('2580','608','Pateros','Y'),('2581','608','Pasay','Y'),('2582','608','Puerto Princesa','Y'),('2583','608','Quezon','Y'),('2584','608','Tacloban','Y'),('2585','608','Zamboanga City','Y'),('2586','608','Aurora','Y'),('2587','608','Negros Occidental','Y'),('2588','608','Biliran','Y'),('2589','608','Makati City','Y'),('259','44','South Abaco','Y'),('2590','608','Sarangani','Y'),('2591','608','Mandaluyong City','Y'),('2592','608','Marikina','Y'),('2593','608','Pasig','Y'),('2594','608','San Juan','Y'),('2595','612','Pitcairn Islands','Y'),('2596','616','Biala Podlaska','Y'),('2597','616','Bialystok','Y'),('2598','616','Bielsko','Y'),('2599','616','Bydgoszcz','Y'),('26','4','Sar-e Pol','Y'),('260','44','San Salvador','Y'),('2600','616','Chelm','Y'),('2601','616','Ciechanow','Y'),('2602','616','Czestochowa','Y'),('2603','616','Elblag','Y'),('2604','616','Gdansk','Y'),('2605','616','Gorzow','Y'),('2606','616','Jelenia Gora','Y'),('2607','616','Kalisz','Y'),('2608','616','Katowice','Y'),('2609','616','Kielce','Y'),('261','44','Berry Islands','Y'),('2610','616','Konin','Y'),('2611','616','Koszalin','Y'),('2612','616','Krakow','Y'),('2613','616','Krosno','Y'),('2614','616','Legnica','Y'),('2615','616','Leszno','Y'),('2616','616','Lodz','Y'),('2617','616','Lomza','Y'),('2618','616','Lublin','Y'),('2619','616','Nowy Sacz','Y'),('262','44','Black Point, Exuma','Y'),('2620','616','Olsztyn','Y'),('2621','616','Opole','Y'),('2622','616','Ostroleka','Y'),('2623','616','Pita','Y'),('2624','616','Piotrkow','Y'),('2625','616','Plock','Y'),('2626','616','Poznan','Y'),('2627','616','Przemysl','Y'),('2628','616','Radom','Y'),('2629','616','Rzeszow','Y'),('263','44','Central Abaco','Y'),('2630','616','Siedlce','Y'),('2631','616','Sieradz','Y'),('2632','616','Skierniewice','Y'),('2633','616','Slupsk','Y'),('2634','616','Suwalki','Y'),('2635','616','Szczecin','Y'),('2636','616','Tarnobrzeg','Y'),('2637','616','Tarnow','Y'),('2638','616','Torufi','Y'),('2639','616','Walbrzych','Y'),('264','44','Central Andros','Y'),('2640','616','Warszawa','Y'),('2641','616','Wloclawek','Y'),('2642','616','Wroclaw','Y'),('2643','616','Zamosc','Y'),('2644','616','Zielona Gora','Y'),('2645','616','Lower Silesian Voivodeship','Y'),('2646','616','Kujawsko-Pomorskie Voivodship','Y'),('2647','616','Łódź Voivodeship','Y'),('2648','616','Lublin Voivodeship','Y'),('2649','616','Lubusz Voivodship','Y'),('265','44','Central Eleuthera','Y'),('2650','616','Lesser Poland Voivodeship','Y'),('2651','616','Masovian Voivodeship','Y'),('2652','616','Opole Voivodeship','Y'),('2653','616','Subcarpathian Voivodeship','Y'),('2654','616','Podlasie Voivodship','Y'),('2655','616','Pomeranian Voivodeship','Y'),('2656','616','Silesian Voivodeship','Y'),('2657','616','Świętokrzyskie Voivodship','Y'),('2658','616','Warmian-Masurian Voivodeship','Y'),('2659','616','Greater Poland Voivodeship','Y'),('266','44','Crooked Island','Y'),('2660','616','West Pomeranian Voivodeship','Y'),('2661','620','Aveiro','Y'),('2662','620','Beja','Y'),('2663','620','Braga','Y'),('2664','620','Bragança','Y'),('2665','620','Castelo Branco','Y'),('2666','620','Coimbra','Y'),('2667','620','Évora','Y'),('2668','620','Faro','Y'),('2669','620','Madeira','Y'),('267','44','East Grand Bahama','Y'),('2670','620','Guarda','Y'),('2671','620','Leiria','Y'),('2672','620','Lisbon','Y'),('2673','620','Portalegre','Y'),('2674','620','Porto','Y'),('2675','620','Santarém','Y'),('2676','620','Setúbal','Y'),('2677','620','Viana do Castelo','Y'),('2678','620','Vila Real','Y'),('2679','620','Viseu','Y'),('268','44','Exuma','Y'),('2680','620','Azores','Y'),('2681','624','Bafatá','Y'),('2682','624','Quinara','Y'),('2683','624','Oio','Y'),('2684','624','Bolama','Y'),('2685','624','Cacheu','Y'),('2686','624','Tombali','Y'),('2687','624','Gabú','Y'),('2688','624','Bissau','Y'),('2689','624','Biombo','Y'),('269','44','Grand Cay, Abaco','Y'),('2690','626','Bobonaro','Y'),('2691','630','Puerto Rico','Y'),('2692','634','Ad Dawḩah','Y'),('2693','634','Al Ghuwayrīyah','Y'),('2694','634','Al Jumaylīyah','Y'),('2695','634','Al Khawr','Y'),('2696','634','Al Wakrah Municipality','Y'),('2697','634','Ar Rayyān','Y'),('2698','634','Jarayan al Batinah','Y'),('2699','634','Madīnat ash Shamāl','Y'),('27','4','Konar','Y'),('270','44','Spanish Wells, Eleuthera','Y'),('2700','634','Umm Şalāl','Y'),('2701','634','Al Wakrah','Y'),('2702','634','Jarayān al Bāţinah','Y'),('2703','634','Umm Sa‘īd','Y'),('2704','638','Réunion','Y'),('2705','642','Alba','Y'),('2706','642','Arad','Y'),('2707','642','Argeş','Y'),('2708','642','Bacău','Y'),('2709','642','Bihor','Y'),('271','44','West Grand Bahama','Y'),('2710','642','Bistriţa-Năsăud','Y'),('2711','642','Botoşani','Y'),('2712','642','Brăila','Y'),('2713','642','Braşov','Y'),('2714','642','Bucureşti','Y'),('2715','642','Buzău','Y'),('2716','642','Caraş-Severin','Y'),('2717','642','Cluj','Y'),('2718','642','Constanţa','Y'),('2719','642','Covasna','Y'),('272','48','Southern Governate','Y'),('2720','642','Dâmboviţa','Y'),('2721','642','Dolj','Y'),('2722','642','Galaţi','Y'),('2723','642','Gorj','Y'),('2724','642','Harghita','Y'),('2725','642','Hunedoara','Y'),('2726','642','Ialomiţa','Y'),('2727','642','Iaşi','Y'),('2728','642','Maramureş','Y'),('2729','642','Mehedinţi','Y'),('273','48','Northern Governate','Y'),('2730','642','Mureş','Y'),('2731','642','Neamţ','Y'),('2732','642','Olt','Y'),('2733','642','Prahova','Y'),('2734','642','Sălaj','Y'),('2735','642','Satu Mare','Y'),('2736','642','Sibiu','Y'),('2737','642','Suceava','Y'),('2738','642','Teleorman','Y'),('2739','642','Timiş','Y'),('274','48','Capital Governate','Y'),('2740','642','Tulcea','Y'),('2741','642','Vaslui','Y'),('2742','642','Vâlcea','Y'),('2743','642','Judeţul Vrancea','Y'),('2744','642','Călăraşi','Y'),('2745','642','Giurgiu','Y'),('2746','642','Ilfov','Y'),('2747','643','Adygeya','Y'),('2748','643','Altay','Y'),('2749','643','Altayskiy Kray','Y'),('275','48','Central Governate','Y'),('2750','643','Amur','Y'),('2751','643','Arkhangelskaya oblast','Y'),('2752','643','Astrakhan','Y'),('2753','643','Bashkortostan','Y'),('2754','643','Belgorod','Y'),('2755','643','Brjansk','Y'),('2756','643','Buryatiya','Y'),('2757','643','Chechnya','Y'),('2758','643','Tsjeljabinsk','Y'),('2759','643','Zabaïkalski Kray','Y'),('276','48','Muharraq Governate','Y'),('2760','643','Chukotskiy Avtonomnyy Okrug','Y'),('2761','643','Chuvashia','Y'),('2762','643','Dagestan','Y'),('2763','643','Ingushetiya','Y'),('2764','643','Irkutsk','Y'),('2765','643','Ivanovo','Y'),('2766','643','Kabardino-Balkariya','Y'),('2767','643','Kaliningrad','Y'),('2768','643','Kalmykiya','Y'),('2769','643','Kaluga','Y'),('277','50','BG80','Y'),('2770','643','Karachayevo-Cherkesiya','Y'),('2771','643','Kareliya','Y'),('2772','643','Kemerovo','Y'),('2773','643','Khabarovsk Krai','Y'),('2774','643','Khakasiya','Y'),('2775','643','Khanty-Mansiyskiy Avtonomnyy Okrug','Y'),('2776','643','Kirov','Y'),('2777','643','Komi','Y'),('2778','643','Kostroma','Y'),('2779','643','Krasnodarskiy Kray','Y'),('278','50','Dhaka','Y'),('2780','643','Kurgan','Y'),('2781','643','Kursk','Y'),('2782','643','Leningradskaya Oblastʼ','Y'),('2783','643','Lipetsk','Y'),('2784','643','Magadan','Y'),('2785','643','Mariy-El','Y'),('2786','643','Mordoviya','Y'),('2787','643','Moskovskaya Oblastʼ','Y'),('2788','643','Moscow','Y'),('2789','643','Murmansk Oblast','Y'),('279','50','Khulna','Y'),('2790','643','Nenetskiy Avtonomnyy Okrug','Y'),('2791','643','Nizjnij Novgorod','Y'),('2792','643','Novgorod','Y'),('2793','643','Novosibirsk','Y'),('2794','643','Omsk','Y'),('2795','643','Orenburg','Y'),('2796','643','Orjol','Y'),('2797','643','Penza','Y'),('2798','643','Primorskiy Kray','Y'),('2799','643','Pskov','Y'),('28','4','Laghmān','Y'),('280','50','Rājshāhi','Y'),('2800','643','Rostov','Y'),('2801','643','Rjazan','Y'),('2802','643','Sakha','Y'),('2803','643','Sakhalin','Y'),('2804','643','Samara','Y'),('2805','643','Sankt-Peterburg','Y'),('2806','643','Saratov','Y'),('2807','643','Severnaya Osetiya-Alaniya','Y'),('2808','643','Smolensk','Y'),('2809','643','Stavropolʼskiy Kray','Y'),('281','50','Chittagong','Y'),('2810','643','Sverdlovsk','Y'),('2811','643','Tambov','Y'),('2812','643','Tatarstan','Y'),('2813','643','Tomsk','Y'),('2814','643','Tula','Y'),('2815','643','Tverskaya Oblast’','Y'),('2816','643','Tjumen','Y'),('2817','643','Tyva','Y'),('2818','643','Udmurtiya','Y'),('2819','643','Uljanovsk','Y'),('282','50','Barisāl','Y'),('2820','643','Vladimir','Y'),('2821','643','Volgograd','Y'),('2822','643','Vologda','Y'),('2823','643','Voronezj','Y'),('2824','643','Yamalo-Nenetskiy Avtonomnyy Okrug','Y'),('2825','643','Jaroslavl','Y'),('2826','643','Jewish Autonomous Oblast','Y'),('2827','643','Perm','Y'),('2828','643','Krasnoyarskiy Kray','Y'),('2829','643','Kamtsjatka','Y'),('283','50','Sylhet','Y'),('2830','643','RSJA','Y'),('2831','646','Eastern Province','Y'),('2832','646','Kigali City','Y'),('2833','646','Northern Province','Y'),('2834','646','Western Province','Y'),('2835','646','Southern Province','Y'),('2836','654','Ascension','Y'),('2837','654','Saint Helena','Y'),('2838','654','Tristan da Cunha','Y'),('2839','659','Christ Church Nichola Town','Y'),('284','51','Aragatsotn','Y'),('2840','659','Saint Anne Sandy Point','Y'),('2841','659','Saint George Basseterre','Y'),('2842','659','Saint George Gingerland','Y'),('2843','659','Saint James Windwa','Y'),('2844','659','Saint John Capesterre','Y'),('2845','659','Saint John Figtree','Y'),('2846','659','Saint Mary Cayon','Y'),('2847','659','Saint Paul Capesterre','Y'),('2848','659','Saint Paul Charlestown','Y'),('2849','659','Saint Peter Basseterre','Y'),('285','51','Ararat','Y'),('2850','659','Saint Thomas Lowland','Y'),('2851','659','Saint Thomas Middle Island','Y'),('2852','659','Trinity Palmetto Point','Y'),('2853','660','Anguilla','Y'),('2854','662','Anse-la-Raye','Y'),('2855','662','Dauphin','Y'),('2856','662','Castries','Y'),('2857','662','Choiseul','Y'),('2858','662','Dennery','Y'),('2859','662','Gros-Islet','Y'),('286','51','Armavir','Y'),('2860','662','Laborie','Y'),('2861','662','Micoud','Y'),('2862','662','Soufrière','Y'),('2863','662','Vieux-Fort','Y'),('2864','662','Praslin','Y'),('2865','666','Saint-Pierre-et-Miquelon','Y'),('2866','670','Charlotte','Y'),('2867','670','Saint Andrew','Y'),('2868','670','Saint David','Y'),('2869','670','Saint George','Y'),('287','51','Gegharkʼunikʼ','Y'),('2870','670','Saint Patrick','Y'),('2871','670','Grenadines','Y'),('2872','674','Acquaviva','Y'),('2873','674','Chiesanuova','Y'),('2874','674','Domagnano','Y'),('2875','674','Faetano','Y'),('2876','674','Fiorentino','Y'),('2877','674','Borgo Maggiore','Y'),('2878','674','San Marino','Y'),('2879','674','Montegiardino','Y'),('288','51','Kotaykʼ','Y'),('2880','674','Serravalle','Y'),('2881','678','Príncipe','Y'),('2882','678','Príncipe','Y'),('2883','678','São Tomé','Y'),('2884','682','Al Bāḩah','Y'),('2885','682','Al Madīnah','Y'),('2886','682','Ash Sharqīyah','Y'),('2887','682','Al Qaşīm','Y'),('2888','682','Ar Riyāḑ','Y'),('2889','682','‘Asīr','Y'),('289','51','Lorri','Y'),('2890','682','Ḩāʼil','Y'),('2891','682','Makkah','Y'),('2892','682','Northern Borders Region','Y'),('2893','682','Najrān','Y'),('2894','682','Jīzān','Y'),('2895','682','Tabūk','Y'),('2896','682','Al Jawf','Y'),('2897','686','Dakar','Y'),('2898','686','Diourbel','Y'),('2899','686','Saint-Louis','Y'),('29','4','Paktia Province','Y'),('290','51','Shirak','Y'),('2900','686','Tambacounda','Y'),('2901','686','Thiès','Y'),('2902','686','Louga','Y'),('2903','686','Fatick','Y'),('2904','686','Kaolack','Y'),('2905','686','Kolda Region','Y'),('2906','686','Ziguinchor','Y'),('2907','686','Louga','Y'),('2908','686','Saint-Louis','Y'),('2909','686','Matam','Y'),('291','51','Syunikʼ','Y'),('2910','688','Autonomna Pokrajina Vojvodina','Y'),('2911','690','Anse aux Pins','Y'),('2912','690','Anse Boileau','Y'),('2913','690','Anse Etoile','Y'),('2914','690','Anse Louis','Y'),('2915','690','Anse Royale','Y'),('2916','690','Baie Lazare','Y'),('2917','690','Baie Sainte Anne','Y'),('2918','690','Beau Vallon','Y'),('2919','690','Bel Air','Y'),('292','51','Tavush','Y'),('2920','690','Bel Ombre','Y'),('2921','690','Cascade','Y'),('2922','690','Glacis','Y'),('2923','690','Saint Thomas Middle Island Parish','Y'),('2924','690','Grand Anse Praslin','Y'),('2925','690','Trinity Palmetto Point Parish','Y'),('2926','690','La Riviere Anglaise','Y'),('2927','690','Mont Buxton','Y'),('2928','690','Mont Fleuri','Y'),('2929','690','Plaisance','Y'),('293','51','Vayotsʼ Dzor','Y'),('2930','690','Pointe Larue','Y'),('2931','690','Port Glaud','Y'),('2932','690','Saint Louis','Y'),('2933','690','Takamaka','Y'),('2934','690','Anse aux Pins','Y'),('2935','690','Inner Islands','Y'),('2936','690','English River','Y'),('2937','690','Port Glaud','Y'),('2938','690','Baie Lazare','Y'),('2939','690','Beau Vallon','Y'),('294','51','Yerevan','Y'),('2940','690','Bel Ombre','Y'),('2941','690','Glacis','Y'),('2942','690','Grand Anse Mahe','Y'),('2943','690','Grand Anse Praslin','Y'),('2944','690','Inner Islands','Y'),('2945','690','English River','Y'),('2946','690','Mont Fleuri','Y'),('2947','690','Plaisance','Y'),('2948','690','Pointe Larue','Y'),('2949','690','Port Glaud','Y'),('295','52','Christ Church','Y'),('2950','690','Takamaka','Y'),('2951','690','Au Cap','Y'),('2952','690','Les Mamelles','Y'),('2953','690','Roche Caiman','Y'),('2954','694','Eastern Province','Y'),('2955','694','Northern Province','Y'),('2956','694','Southern Province','Y'),('2957','694','Western Area','Y'),('2958','702','Singapore','Y'),('2959','703','Banskobystrický','Y'),('296','52','Saint Andrew','Y'),('2960','703','Bratislavský','Y'),('2961','703','Košický','Y'),('2962','703','Nitriansky','Y'),('2963','703','Prešovský','Y'),('2964','703','Trenčiansky','Y'),('2965','703','Trnavský','Y'),('2966','703','Žilinský','Y'),('2967','704','An Giang','Y'),('2968','704','Bắc Thái Tỉnh','Y'),('2969','704','Bến Tre','Y'),('297','52','Saint George','Y'),('2970','704','Cao Bang','Y'),('2971','704','Cao Bằng','Y'),('2972','704','Ten Bai','Y'),('2973','704','Ðồng Tháp','Y'),('2974','704','Hà Bắc Tỉnh','Y'),('2975','704','Hải Hưng Tỉnh','Y'),('2976','704','Hải Phòng','Y'),('2977','704','Hoa Binh','Y'),('2978','704','Ha Tay','Y'),('2979','704','Hồ Chí Minh','Y'),('298','52','Saint James','Y'),('2980','704','Kiến Giang','Y'),('2981','704','Lâm Ðồng','Y'),('2982','704','Long An','Y'),('2983','704','Minh Hải Tỉnh','Y'),('2984','704','Thua Thien-Hue','Y'),('2985','704','Quang Nam','Y'),('2986','704','Kon Tum','Y'),('2987','704','Quảng Nam-Ðà Nẵng Tỉnh','Y'),('2988','704','Quảng Ninh','Y'),('2989','704','Sông Bé Tỉnh','Y'),('299','52','Saint John','Y'),('2990','704','Sơn La','Y'),('2991','704','Tây Ninh','Y'),('2992','704','Thanh Hóa','Y'),('2993','704','Thái Bình','Y'),('2994','704','Nin Thuan','Y'),('2995','704','Tiền Giang','Y'),('2996','704','Vinh Phú Tỉnh','Y'),('2997','704','Lạng Sơn','Y'),('2998','704','Binh Thuan','Y'),('2999','704','Long An','Y'),('3','4','Baghlān','Y'),('30','4','Khowst','Y'),('300','52','Saint Joseph','Y'),('3000','704','Ðồng Nai','Y'),('3001','704','Ha Nội','Y'),('3002','704','Bà Rịa-Vũng Tàu','Y'),('3003','704','Bình Ðịnh','Y'),('3004','704','Bình Thuận','Y'),('3005','704','Gia Lai','Y'),('3006','704','Hà Giang','Y'),('3007','704','Hà Tây','Y'),('3008','704','Hà Tĩnh','Y'),('3009','704','Hòa Bình','Y'),('301','52','Saint Lucy','Y'),('3010','704','Khánh Hòa','Y'),('3011','704','Kon Tum','Y'),('3012','704','Nam Hà Tỉnh','Y'),('3013','704','Nghệ An','Y'),('3014','704','Ninh Bình','Y'),('3015','704','Ninh Thuận','Y'),('3016','704','Phú Yên','Y'),('3017','704','Quảng Bình','Y'),('3018','704','Quảng Ngãi','Y'),('3019','704','Quảng Trị','Y'),('302','52','Saint Michael','Y'),('3020','704','Sóc Trăng','Y'),('3021','704','Thừa Thiên-Huế','Y'),('3022','704','Trà Vinh','Y'),('3023','704','Tuyên Quang','Y'),('3024','704','Vĩnh Long','Y'),('3025','704','Yên Bái','Y'),('3026','704','Bắc Giang','Y'),('3027','704','Bắc Kạn','Y'),('3028','704','Bạc Liêu','Y'),('3029','704','Bắc Ninh','Y'),('303','52','Saint Peter','Y'),('3030','704','Bình Dương','Y'),('3031','704','Bình Phước','Y'),('3032','704','Cà Mau','Y'),('3033','704','Ðà Nẵng','Y'),('3034','704','Hải Dương','Y'),('3035','704','Hà Nam','Y'),('3036','704','Hưng Yên','Y'),('3037','704','Nam Ðịnh','Y'),('3038','704','Phú Thọ','Y'),('3039','704','Quảng Nam','Y'),('304','52','Saint Philip','Y'),('3040','704','Thái Nguyên','Y'),('3041','704','Vĩnh Phúc','Y'),('3042','704','Cần Thơ','Y'),('3043','704','Ðắc Lắk','Y'),('3044','704','Lai Châu','Y'),('3045','704','Lào Cai','Y'),('3046','705','Notranjska','Y'),('3047','705','Koroška','Y'),('3048','705','Štajerska','Y'),('3049','705','Prekmurje','Y'),('305','52','Saint Thomas','Y'),('3050','705','Primorska','Y'),('3051','705','Gorenjska','Y'),('3052','705','Dolenjska','Y'),('3053','706','Bakool','Y'),('3054','706','Banaadir','Y'),('3055','706','Bari','Y'),('3056','706','Bay','Y'),('3057','706','Galguduud','Y'),('3058','706','Gedo','Y'),('3059','706','Hiiraan','Y'),('306','56','Bruxelles-Capitale','Y'),('3060','706','Middle Juba','Y'),('3061','706','Lower Juba','Y'),('3062','706','Mudug','Y'),('3063','706','Sanaag','Y'),('3064','706','Middle Shabele','Y'),('3065','706','Shabeellaha Hoose','Y'),('3066','706','Nugaal','Y'),('3067','706','Togdheer','Y'),('3068','706','Woqooyi Galbeed','Y'),('3069','706','Awdal','Y'),('307','56','Flanders','Y'),('3070','706','Sool','Y'),('3071','710','KwaZulu-Natal','Y'),('3072','710','Free State','Y'),('3073','710','Eastern Cape','Y'),('3074','710','Gauteng','Y'),('3075','710','Mpumalanga','Y'),('3076','710','Northern Cape','Y'),('3077','710','Limpopo','Y'),('3078','710','North-West','Y'),('3079','710','Western Cape','Y'),('308','56','Wallonia','Y'),('3080','716','Manicaland','Y'),('3081','716','Midlands','Y'),('3082','716','Mashonaland Central','Y'),('3083','716','Mashonaland East','Y'),('3084','716','Mashonaland West','Y'),('3085','716','Matabeleland North','Y'),('3086','716','Matabeleland South','Y'),('3087','716','Masvingo','Y'),('3088','716','Bulawayo','Y'),('3089','716','Harare','Y'),('309','60','Devonshire','Y'),('3090','724','Ceuta','Y'),('3091','724','Balearic Islands','Y'),('3092','724','La Rioja','Y'),('3093','724','Autonomous Region of Madrid','Y'),('3094','724','Murcia','Y'),('3095','724','Navarre','Y'),('3096','724','Asturias','Y'),('3097','724','Cantabria','Y'),('3098','724','Andalusia','Y'),('3099','724','Aragon','Y'),('31','4','Nūrestān','Y'),('310','60','Hamilton (parish)','Y'),('3100','724','Canary Islands','Y'),('3101','724','Castille-La Mancha','Y'),('3102','724','Castille and León','Y'),('3103','724','Catalonia','Y'),('3104','724','Extremadura','Y'),('3105','724','Galicia','Y'),('3106','724','Basque Country','Y'),('3107','724','Valencia','Y'),('3108','732','Western Sahara','Y'),('3109','736','Al Wilāyah al Wusţá','Y'),('311','60','Hamilton (city)','Y'),('3110','736','Al Wilāyah al Istiwāʼīyah','Y'),('3111','736','Khartoum','Y'),('3112','736','Ash Shamaliyah','Y'),('3113','736','Al Wilāyah ash Sharqīyah','Y'),('3114','736','Ba?r al Ghazal Wilayat','Y'),('3115','736','Darfur Wilayat','Y'),('3116','736','Kurdufan Wilayat','Y'),('3117','736','Upper Nile','Y'),('3118','736','Red Sea','Y'),('3119','736','Lakes','Y'),('312','60','Paget','Y'),('3120','736','Al Jazirah','Y'),('3121','736','Al Qadarif','Y'),('3122','736','Unity','Y'),('3123','736','White Nile','Y'),('3124','736','Blue Nile','Y'),('3125','736','Northern','Y'),('3126','736','Central Equatoria','Y'),('3127','736','Gharb al Istiwāʼīyah','Y'),('3128','736','Western Bahr al Ghazal','Y'),('3129','736','Gharb Dārfūr','Y'),('313','60','Pembroke','Y'),('3130','736','Gharb Kurdufān','Y'),('3131','736','Janūb Dārfūr','Y'),('3132','736','Janūb Kurdufān','Y'),('3133','736','Junqalī','Y'),('3134','736','Kassalā','Y'),('3135','736','Nahr an Nīl','Y'),('3136','736','Shamāl Baḩr al Ghazāl','Y'),('3137','736','Shamāl Dārfūr','Y'),('3138','736','Shamāl Kurdufān','Y'),('3139','736','Eastern Equatoria','Y'),('314','60','Saint Georgeʼs (parish)','Y'),('3140','736','Sinnār','Y'),('3141','736','Warab','Y'),('3142','740','Brokopondo','Y'),('3143','740','Commewijne','Y'),('3144','740','Coronie','Y'),('3145','740','Marowijne','Y'),('3146','740','Nickerie','Y'),('3147','740','Para','Y'),('3148','740','Paramaribo','Y'),('3149','740','Saramacca','Y'),('315','60','Saint Georgeʼs (city)','Y'),('3150','740','Sipaliwini','Y'),('3151','740','Wanica','Y'),('3152','748','Hhohho','Y'),('3153','748','Lubombo','Y'),('3154','748','Manzini','Y'),('3155','748','Shiselweni','Y'),('3156','752','Blekinge','Y'),('3157','752','Gävleborg','Y'),('3158','752','Gotland','Y'),('3159','752','Halland','Y'),('316','60','Sandys','Y'),('3160','752','Jämtland','Y'),('3161','752','Jönköping','Y'),('3162','752','Kalmar','Y'),('3163','752','Dalarna','Y'),('3164','752','Kronoberg','Y'),('3165','752','Norrbotten','Y'),('3166','752','Örebro','Y'),('3167','752','Östergötland','Y'),('3168','752','Södermanland','Y'),('3169','752','Uppsala','Y'),('317','60','Smithʼs','Y'),('3170','752','Värmland','Y'),('3171','752','Västerbotten','Y'),('3172','752','Västernorrland','Y'),('3173','752','Västmanland','Y'),('3174','752','Stockholm','Y'),('3175','752','Skåne','Y'),('3176','752','Västra Götaland','Y'),('3177','756','Aargau','Y'),('3178','756','Appenzell Innerrhoden','Y'),('3179','756','Appenzell Ausserrhoden','Y'),('318','60','Southampton','Y'),('3180','756','Bern','Y'),('3181','756','Basel-Landschaft','Y'),('3182','756','Kanton Basel-Stadt','Y'),('3183','756','Fribourg','Y'),('3184','756','Genève','Y'),('3185','756','Glarus','Y'),('3186','756','Graubünden','Y'),('3187','756','Jura','Y'),('3188','756','Luzern','Y'),('3189','756','Neuchâtel','Y'),('319','60','Warwick','Y'),('3190','756','Nidwalden','Y'),('3191','756','Obwalden','Y'),('3192','756','Kanton St. Gallen','Y'),('3193','756','Schaffhausen','Y'),('3194','756','Solothurn','Y'),('3195','756','Schwyz','Y'),('3196','756','Thurgau','Y'),('3197','756','Ticino','Y'),('3198','756','Uri','Y'),('3199','756','Vaud','Y'),('32','4','Parvān','Y'),('320','64','Bumthang','Y'),('3200','756','Valais','Y'),('3201','756','Zug','Y'),('3202','756','Zürich','Y'),('3203','760','Al-Hasakah','Y'),('3204','760','Latakia','Y'),('3205','760','Quneitra','Y'),('3206','760','Ar-Raqqah','Y'),('3207','760','As-Suwayda','Y'),('3208','760','Daraa','Y'),('3209','760','Deir ez-Zor','Y'),('321','64','Chhukha','Y'),('3210','760','Rif-dimashq','Y'),('3211','760','Aleppo','Y'),('3212','760','Hama Governorate','Y'),('3213','760','Homs','Y'),('3214','760','Idlib','Y'),('3215','760','Damascus City','Y'),('3216','760','Tartus','Y'),('3217','762','Gorno-Badakhshan','Y'),('3218','762','Khatlon','Y'),('3219','762','Sughd','Y'),('322','64','Chirang','Y'),('3220','762','Dushanbe','Y'),('3221','762','Region of Republican Subordination','Y'),('3222','764','Mae Hong Son','Y'),('3223','764','Chiang Mai','Y'),('3224','764','Chiang Rai','Y'),('3225','764','Nan','Y'),('3226','764','Lamphun','Y'),('3227','764','Lampang','Y'),('3228','764','Phrae','Y'),('3229','764','Tak','Y'),('323','64','Daga','Y'),('3230','764','Sukhothai','Y'),('3231','764','Uttaradit','Y'),('3232','764','Kamphaeng Phet','Y'),('3233','764','Phitsanulok','Y'),('3234','764','Phichit','Y'),('3235','764','Phetchabun','Y'),('3236','764','Uthai Thani','Y'),('3237','764','Nakhon Sawan','Y'),('3238','764','Nong Khai','Y'),('3239','764','Loei','Y'),('324','64','Geylegphug','Y'),('3240','764','Sakon Nakhon','Y'),('3241','764','Nakhon Phanom','Y'),('3242','764','Khon Kaen','Y'),('3243','764','Kalasin','Y'),('3244','764','Maha Sarakham','Y'),('3245','764','Roi Et','Y'),('3246','764','Chaiyaphum','Y'),('3247','764','Nakhon Ratchasima','Y'),('3248','764','Buriram','Y'),('3249','764','Surin','Y'),('325','64','Ha','Y'),('3250','764','Sisaket','Y'),('3251','764','Narathiwat','Y'),('3252','764','Chai Nat','Y'),('3253','764','Sing Buri','Y'),('3254','764','Lop Buri','Y'),('3255','764','Ang Thong','Y'),('3256','764','Phra Nakhon Si Ayutthaya','Y'),('3257','764','Sara Buri','Y'),('3258','764','Nonthaburi','Y'),('3259','764','Pathum Thani','Y'),('326','64','Lhuntshi','Y'),('3260','764','Bangkok','Y'),('3261','764','Phayao','Y'),('3262','764','Samut Prakan','Y'),('3263','764','Nakhon Nayok','Y'),('3264','764','Chachoengsao','Y'),('3265','764','Chon Buri','Y'),('3266','764','Rayong','Y'),('3267','764','Chanthaburi','Y'),('3268','764','Trat','Y'),('3269','764','Kanchanaburi','Y'),('327','64','Mongar','Y'),('3270','764','Suphan Buri','Y'),('3271','764','Ratchaburi','Y'),('3272','764','Nakhon Pathom','Y'),('3273','764','Samut Songkhram','Y'),('3274','764','Samut Sakhon','Y'),('3275','764','Phetchaburi','Y'),('3276','764','Prachuap Khiri Khan','Y'),('3277','764','Chumphon','Y'),('3278','764','Ranong','Y'),('3279','764','Surat Thani','Y'),('328','64','Paro District','Y'),('3280','764','Phangnga','Y'),('3281','764','Phuket','Y'),('3282','764','Krabi','Y'),('3283','764','Nakhon Si Thammarat','Y'),('3284','764','Trang','Y'),('3285','764','Phatthalung','Y'),('3286','764','Satun','Y'),('3287','764','Songkhla','Y'),('3288','764','Pattani','Y'),('3289','764','Yala','Y'),('329','64','Pemagatsel','Y'),('3290','764','Yasothon','Y'),('3291','764','Nakhon Phanom','Y'),('3292','764','Prachin Buri','Y'),('3293','764','Ubon Ratchathani','Y'),('3294','764','Udon Thani','Y'),('3295','764','Amnat Charoen','Y'),('3296','764','Mukdahan','Y'),('3297','764','Nong Bua Lamphu','Y'),('3298','764','Sa Kaeo','Y'),('3299','768','Amlame','Y'),('33','4','Dāykondī','Y'),('330','64','Samchi','Y'),('3300','768','Aneho','Y'),('3301','768','Atakpame','Y'),('3302','768','Bafilo','Y'),('3303','768','Bassar','Y'),('3304','768','Dapaong','Y'),('3305','768','Kante','Y'),('3306','768','Klouto','Y'),('3307','768','Lama-Kara','Y'),('3308','768','Lome','Y'),('3309','768','Mango','Y'),('331','64','Samdrup Jongkhar District','Y'),('3310','768','Niamtougou','Y'),('3311','768','Notse','Y'),('3312','768','Kpagouda','Y'),('3313','768','Badou','Y'),('3314','768','Sotouboua','Y'),('3315','768','Tabligbo','Y'),('3316','768','Tsevie','Y'),('3317','768','Tchamba','Y'),('3318','768','Tchaoudjo','Y'),('3319','768','Vogan','Y'),('332','64','Shemgang','Y'),('3320','768','Centrale','Y'),('3321','768','Kara','Y'),('3322','768','Maritime','Y'),('3323','768','Plateaux','Y'),('3324','768','Savanes','Y'),('3325','772','Tokelau','Y'),('3326','776','Ha‘apai','Y'),('3327','776','Tongatapu','Y'),('3328','776','Vava‘u','Y'),('3329','780','Port of Spain','Y'),('333','64','Tashigang','Y'),('3330','780','San Fernando','Y'),('3331','780','Chaguanas','Y'),('3332','780','Arima','Y'),('3333','780','Point Fortin Borough','Y'),('3334','780','Couva-Tabaquite-Talparo','Y'),('3335','780','Diego Martin','Y'),('3336','780','Penal-Debe','Y'),('3337','780','Princes Town','Y'),('3338','780','Rio Claro-Mayaro','Y'),('3339','780','San Juan-Laventille','Y'),('334','64','Thimphu','Y'),('3340','780','Sangre Grande','Y'),('3341','780','Siparia','Y'),('3342','780','Tunapuna-Piarco','Y'),('3343','784','Abū Z̧aby','Y'),('3344','784','Ajman','Y'),('3345','784','Dubayy','Y'),('3346','784','Al Fujayrah','Y'),('3347','784','Raʼs al Khaymah','Y'),('3348','784','Ash Shāriqah','Y'),('3349','784','Umm al Qaywayn','Y'),('335','64','Tongsa','Y'),('3350','788','Tunis al Janubiyah Wilayat','Y'),('3351','788','Al Qaşrayn','Y'),('3352','788','Al Qayrawān','Y'),('3353','788','Jundūbah','Y'),('3354','788','Kef','Y'),('3355','788','Al Mahdīyah','Y'),('3356','788','Al Munastīr','Y'),('3357','788','Bājah','Y'),('3358','788','Banzart','Y'),('3359','788','Nābul','Y'),('336','64','Wangdi Phodrang','Y'),('3360','788','Silyānah','Y'),('3361','788','Sūsah','Y'),('3362','788','Bin ‘Arūs','Y'),('3363','788','Madanīn','Y'),('3364','788','Qābis','Y'),('3365','788','Qafşah','Y'),('3366','788','Qibilī','Y'),('3367','788','Şafāqis','Y'),('3368','788','Sīdī Bū Zayd','Y'),('3369','788','Taţāwīn','Y'),('337','68','Chuquisaca','Y'),('3370','788','Tawzar','Y'),('3371','788','Tūnis','Y'),('3372','788','Zaghwān','Y'),('3373','788','Ariana','Y'),('3374','788','Manouba','Y'),('3375','792','Adıyaman','Y'),('3376','792','Afyonkarahisar','Y'),('3377','792','Ağrı Province','Y'),('3378','792','Amasya Province','Y'),('3379','792','Antalya Province','Y'),('338','68','Cochabamba','Y'),('3380','792','Artvin Province','Y'),('3381','792','Aydın Province','Y'),('3382','792','Balıkesir Province','Y'),('3383','792','Bilecik Province','Y'),('3384','792','Bingöl Province','Y'),('3385','792','Bitlis Province','Y'),('3386','792','Bolu Province','Y'),('3387','792','Burdur Province','Y'),('3388','792','Bursa','Y'),('3389','792','Çanakkale Province','Y'),('339','68','El Beni','Y'),('3390','792','Çorum Province','Y'),('3391','792','Denizli Province','Y'),('3392','792','Diyarbakır','Y'),('3393','792','Edirne Province','Y'),('3394','792','Elazığ','Y'),('3395','792','Erzincan Province','Y'),('3396','792','Erzurum Province','Y'),('3397','792','Eskişehir Province','Y'),('3398','792','Giresun Province','Y'),('3399','792','Hatay Province','Y'),('34','4','Panjshīr','Y'),('340','68','La Paz','Y'),('3400','792','Mersin Province','Y'),('3401','792','Isparta Province','Y'),('3402','792','Istanbul','Y'),('3403','792','İzmir','Y'),('3404','792','Kastamonu Province','Y'),('3405','792','Kayseri Province','Y'),('3406','792','Kırklareli Province','Y'),('3407','792','Kırşehir Province','Y'),('3408','792','Kocaeli Province','Y'),('3409','792','Kütahya Province','Y'),('341','68','Oruro','Y'),('3410','792','Malatya Province','Y'),('3411','792','Manisa Province','Y'),('3412','792','Kahramanmaraş Province','Y'),('3413','792','Muğla Province','Y'),('3414','792','Muş Province','Y'),('3415','792','Nevşehir','Y'),('3416','792','Ordu','Y'),('3417','792','Rize','Y'),('3418','792','Sakarya Province','Y'),('3419','792','Samsun Province','Y'),('342','68','Pando','Y'),('3420','792','Sinop Province','Y'),('3421','792','Sivas Province','Y'),('3422','792','Tekirdağ Province','Y'),('3423','792','Tokat','Y'),('3424','792','Trabzon Province','Y'),('3425','792','Tunceli Province','Y'),('3426','792','Şanlıurfa Province','Y'),('3427','792','Uşak Province','Y'),('3428','792','Van Province','Y'),('3429','792','Yozgat Province','Y'),('343','68','Potosí','Y'),('3430','792','Ankara Province','Y'),('3431','792','Gümüşhane','Y'),('3432','792','Hakkâri Province','Y'),('3433','792','Konya Province','Y'),('3434','792','Mardin Province','Y'),('3435','792','Niğde','Y'),('3436','792','Siirt Province','Y'),('3437','792','Aksaray Province','Y'),('3438','792','Batman Province','Y'),('3439','792','Bayburt','Y'),('344','68','Santa Cruz','Y'),('3440','792','Karaman Province','Y'),('3441','792','Kırıkkale Province','Y'),('3442','792','Şırnak Province','Y'),('3443','792','Adana Province','Y'),('3444','792','Çankırı Province','Y'),('3445','792','Gaziantep Province','Y'),('3446','792','Kars','Y'),('3447','792','Zonguldak','Y'),('3448','792','Ardahan Province','Y'),('3449','792','Bartın Province','Y'),('345','68','Tarija','Y'),('3450','792','Iğdır Province','Y'),('3451','792','Karabük','Y'),('3452','792','Kilis Province','Y'),('3453','792','Osmaniye Province','Y'),('3454','792','Yalova Province','Y'),('3455','792','Düzce','Y'),('3456','795','Ahal','Y'),('3457','795','Balkan','Y'),('3458','795','Daşoguz','Y'),('3459','795','Lebap','Y'),('346','70','Federation of Bosnia and Herzegovina','Y'),('3460','795','Mary','Y'),('3461','796','Turks and Caicos Islands','Y'),('3462','798','Tuvalu','Y'),('3463','800','Eastern Region','Y'),('3464','800','Northern Region','Y'),('3465','800','Central Region','Y'),('3466','800','Western Region','Y'),('3467','804','Cherkasʼka Oblastʼ','Y'),('3468','804','Chernihivsʼka Oblastʼ','Y'),('3469','804','Chernivetsʼka Oblastʼ','Y'),('347','70','Republika Srpska','Y'),('3470','804','Dnipropetrovsʼka Oblastʼ','Y'),('3471','804','Donetsʼka Oblastʼ','Y'),('3472','804','Ivano-Frankivsʼka Oblastʼ','Y'),('3473','804','Kharkivsʼka Oblastʼ','Y'),('3474','804','Kherson Oblast','Y'),('3475','804','Khmelʼnytsʼka Oblastʼ','Y'),('3476','804','Kirovohradsʼka Oblastʼ','Y'),('3477','804','Avtonomna Respublika Krym','Y'),('3478','804','Misto Kyyiv','Y'),('3479','804','Kiev Oblast','Y'),('348','70','Brčko','Y'),('3480','804','Luhansʼka Oblastʼ','Y'),('3481','804','Lʼvivsʼka Oblastʼ','Y'),('3482','804','Mykolayivsʼka Oblastʼ','Y'),('3483','804','Odessa Oblast','Y'),('3484','804','Poltava Oblast','Y'),('3485','804','Rivnensʼka Oblastʼ','Y'),('3486','804','Misto Sevastopol','Y'),('3487','804','Sumy Oblast','Y'),('3488','804','Ternopilʼsʼka Oblastʼ','Y'),('3489','804','Vinnytsʼka Oblastʼ','Y'),('349','72','Central','Y'),('3490','804','Volynsʼka Oblastʼ','Y'),('3491','804','Zakarpatsʼka Oblastʼ','Y'),('3492','804','Zaporizʼka Oblastʼ','Y'),('3493','804','Zhytomyrsʼka Oblastʼ','Y'),('3494','807','Macedonia, The Former Yugoslav Republic of','Y'),('3495','807','Aračinovo','Y'),('3496','807','Bač','Y'),('3497','807','Belčišta','Y'),('3498','807','Berovo','Y'),('3499','807','Bistrica','Y'),('35','8','Berat','Y'),('350','72','Chobe','Y'),('3500','807','Bitola','Y'),('3501','807','Blatec','Y'),('3502','807','Bogdanci','Y'),('3503','807','Opstina Bogomila','Y'),('3504','807','Bogovinje','Y'),('3505','807','Bosilovo','Y'),('3506','807','Brvenica','Y'),('3507','807','Čair','Y'),('3508','807','Capari','Y'),('3509','807','Čaška','Y'),('351','72','Ghanzi','Y'),('3510','807','Čegrana','Y'),('3511','807','Centar','Y'),('3512','807','Centar Župa','Y'),('3513','807','Češinovo','Y'),('3514','807','Čučer-Sandevo','Y'),('3515','807','Debar','Y'),('3516','807','Delčevo','Y'),('3517','807','Delogoždi','Y'),('3518','807','Demir Hisar','Y'),('3519','807','Demir Kapija','Y'),('352','72','Kgalagadi','Y'),('3520','807','Dobruševo','Y'),('3521','807','Dolna Banjica','Y'),('3522','807','Dolneni','Y'),('3523','807','Opstina Gjorce Petrov','Y'),('3524','807','Drugovo','Y'),('3525','807','Džepčište','Y'),('3526','807','Gazi Baba','Y'),('3527','807','Gevgelija','Y'),('3528','807','Gostivar','Y'),('3529','807','Gradsko','Y'),('353','72','Kgatleng','Y'),('3530','807','Ilinden','Y'),('3531','807','Izvor','Y'),('3532','807','Jegunovce','Y'),('3533','807','Kamenjane','Y'),('3534','807','Karbinci','Y'),('3535','807','Karpoš','Y'),('3536','807','Kavadarci','Y'),('3537','807','Kičevo','Y'),('3538','807','Kisela Voda','Y'),('3539','807','Klečevce','Y'),('354','72','Kweneng','Y'),('3540','807','Kočani','Y'),('3541','807','Konče','Y'),('3542','807','Kondovo','Y'),('3543','807','Konopište','Y'),('3544','807','Kosel','Y'),('3545','807','Kratovo','Y'),('3546','807','Kriva Palanka','Y'),('3547','807','Krivogaštani','Y'),('3548','807','Kruševo','Y'),('3549','807','Kukliš','Y'),('355','72','Ngamiland','Y'),('3550','807','Kukurečani','Y'),('3551','807','Kumanovo','Y'),('3552','807','Labuništa','Y'),('3553','807','Opstina Lipkovo','Y'),('3554','807','Lozovo','Y'),('3555','807','Lukovo','Y'),('3556','807','Makedonska Kamenica','Y'),('3557','807','Makedonski Brod','Y'),('3558','807','Mavrovi Anovi','Y'),('3559','807','Mešeišta','Y'),('356','72','North East','Y'),('3560','807','Miravci','Y'),('3561','807','Mogila','Y'),('3562','807','Murtino','Y'),('3563','807','Negotino','Y'),('3564','807','Negotino-Pološko','Y'),('3565','807','Novaci','Y'),('3566','807','Novo Selo','Y'),('3567','807','Obleševo','Y'),('3568','807','Ohrid','Y'),('3569','807','Orašac','Y'),('357','72','South East','Y'),('3570','807','Orizari','Y'),('3571','807','Oslomej','Y'),('3572','807','Pehčevo','Y'),('3573','807','Petrovec','Y'),('3574','807','Plasnica','Y'),('3575','807','Podareš','Y'),('3576','807','Prilep','Y'),('3577','807','Probištip','Y'),('3578','807','Radoviš','Y'),('3579','807','Opstina Rankovce','Y'),('358','72','Southern','Y'),('3580','807','Resen','Y'),('3581','807','Rosoman','Y'),('3582','807','Opština Rostuša','Y'),('3583','807','Samokov','Y'),('3584','807','Saraj','Y'),('3585','807','Šipkovica','Y'),('3586','807','Sopište','Y'),('3587','807','Sopotnica','Y'),('3588','807','Srbinovo','Y'),('3589','807','Staravina','Y'),('359','72','North West','Y'),('3590','807','Star Dojran','Y'),('3591','807','Staro Nagoričane','Y'),('3592','807','Štip','Y'),('3593','807','Struga','Y'),('3594','807','Strumica','Y'),('3595','807','Studeničani','Y'),('3596','807','Šuto Orizari','Y'),('3597','807','Sveti Nikole','Y'),('3598','807','Tearce','Y'),('3599','807','Tetovo','Y'),('36','8','Dibër','Y'),('360','76','Acre','Y'),('3600','807','Topolčani','Y'),('3601','807','Valandovo','Y'),('3602','807','Vasilevo','Y'),('3603','807','Veles','Y'),('3604','807','Velešta','Y'),('3605','807','Vevčani','Y'),('3606','807','Vinica','Y'),('3607','807','Vitolište','Y'),('3608','807','Vraneštica','Y'),('3609','807','Vrapčište','Y'),('361','76','Alagoas','Y'),('3610','807','Vratnica','Y'),('3611','807','Vrutok','Y'),('3612','807','Zajas','Y'),('3613','807','Zelenikovo','Y'),('3614','807','Želino','Y'),('3615','807','Žitoše','Y'),('3616','807','Zletovo','Y'),('3617','807','Zrnovci','Y'),('3618','818','Ad Daqahlīyah','Y'),('3619','818','Al Baḩr al Aḩmar','Y'),('362','76','Amapá','Y'),('3620','818','Al Buḩayrah','Y'),('3621','818','Al Fayyūm','Y'),('3622','818','Al Gharbīyah','Y'),('3623','818','Alexandria','Y'),('3624','818','Al Ismā‘īlīyah','Y'),('3625','818','Al Jīzah','Y'),('3626','818','Al Minūfīyah','Y'),('3627','818','Al Minyā','Y'),('3628','818','Al Qāhirah','Y'),('3629','818','Al Qalyūbīyah','Y'),('363','76','Estado do Amazonas','Y'),('3630','818','Al Wādī al Jadīd','Y'),('3631','818','Eastern Province','Y'),('3632','818','As Suways','Y'),('3633','818','Aswān','Y'),('3634','818','Asyūţ','Y'),('3635','818','Banī Suwayf','Y'),('3636','818','Būr Sa‘īd','Y'),('3637','818','Dumyāţ','Y'),('3638','818','Kafr ash Shaykh','Y'),('3639','818','Maţrūḩ','Y'),('364','76','Bahia','Y'),('3640','818','Qinā','Y'),('3641','818','Sūhāj','Y'),('3642','818','Janūb Sīnāʼ','Y'),('3643','818','Shamāl Sīnāʼ','Y'),('3644','818','Luxor','Y'),('3645','818','Helwan','Y'),('3646','818','6th of October','Y'),('3647','826','England','Y'),('3648','826','Northern Ireland','Y'),('3649','826','Scotland','Y'),('365','76','Ceará','Y'),('3650','826','Wales','Y'),('3651','831','Guernsey','Y'),('3652','833','Isle of Man','Y'),('3653','834','Arusha','Y'),('3654','834','Pwani','Y'),('3655','834','Dodoma','Y'),('3656','834','Iringa','Y'),('3657','834','Kigoma','Y'),('3658','834','Kilimanjaro','Y'),('3659','834','Lindi','Y'),('366','76','Distrito Federal','Y'),('3660','834','Mara','Y'),('3661','834','Mbeya','Y'),('3662','834','Morogoro','Y'),('3663','834','Mtwara','Y'),('3664','834','Mwanza','Y'),('3665','834','Pemba North','Y'),('3666','834','Ruvuma','Y'),('3667','834','Shinyanga','Y'),('3668','834','Singida','Y'),('3669','834','Tabora','Y'),('367','76','Espírito Santo','Y'),('3670','834','Tanga','Y'),('3671','834','Kagera','Y'),('3672','834','Pemba South','Y'),('3673','834','Zanzibar Central/South','Y'),('3674','834','Zanzibar North','Y'),('3675','834','Dar es Salaam','Y'),('3676','834','Rukwa','Y'),('3677','834','Zanzibar Urban/West','Y'),('3678','834','Arusha','Y'),('3679','834','Manyara','Y'),('368','76','Fernando de Noronha','Y'),('3680','840','Alaska','Y'),('3681','840','Alabama','Y'),('3682','840','Arkansas','Y'),('3683','840','Arizona','Y'),('3684','840','California','Y'),('3685','840','Colorado','Y'),('3686','840','Connecticut','Y'),('3687','840','District of Columbia','Y'),('3688','840','Delaware','Y'),('3689','840','Florida','Y'),('369','76','Goias','Y'),('3690','840','Georgia','Y'),('3691','840','Hawaii','Y'),('3692','840','Iowa','Y'),('3693','840','Idaho','Y'),('3694','840','Illinois','Y'),('3695','840','Indiana','Y'),('3696','840','Kansas','Y'),('3697','840','Kentucky','Y'),('3698','840','Louisiana','Y'),('3699','840','Massachusetts','Y'),('37','8','Durrës','Y'),('370','76','Mato Grosso do Sul','Y'),('3700','840','Maryland','Y'),('3701','840','Maine','Y'),('3702','840','Michigan','Y'),('3703','840','Minnesota','Y'),('3704','840','Missouri','Y'),('3705','840','Mississippi','Y'),('3706','840','Montana','Y'),('3707','840','North Carolina','Y'),('3708','840','North Dakota','Y'),('3709','840','Nebraska','Y'),('371','76','Maranhão','Y'),('3710','840','New Hampshire','Y'),('3711','840','New Jersey','Y'),('3712','840','New Mexico','Y'),('3713','840','Nevada','Y'),('3714','840','New York','Y'),('3715','840','Ohio','Y'),('3716','840','Oklahoma','Y'),('3717','840','Oregon','Y'),('3718','840','Pennsylvania','Y'),('3719','840','Rhode Island','Y'),('372','76','Mato Grosso','Y'),('3720','840','South Carolina','Y'),('3721','840','South Dakota','Y'),('3722','840','Tennessee','Y'),('3723','840','Texas','Y'),('3724','840','Utah','Y'),('3725','840','Virginia','Y'),('3726','840','Vermont','Y'),('3727','840','Washington','Y'),('3728','840','Wisconsin','Y'),('3729','840','West Virginia','Y'),('373','76','Minas Gerais','Y'),('3730','840','Wyoming','Y'),('3731','850','Virgin Islands','Y'),('3732','854','Boucle du Mouhoun','Y'),('3733','854','Cascades','Y'),('3734','854','Centre','Y'),('3735','854','Centre-Est','Y'),('3736','854','Centre-Nord','Y'),('3737','854','Centre-Ouest','Y'),('3738','854','Centre-Sud','Y'),('3739','854','Est','Y'),('374','76','Pará','Y'),('3740','854','Hauts-Bassins','Y'),('3741','854','Nord','Y'),('3742','854','Plateau-Central','Y'),('3743','854','Sahel','Y'),('3744','854','Sud-Ouest','Y'),('3745','855','Komuna e Deçanit','Y'),('3746','855','Komuna e Dragashit','Y'),('3747','855','Komuna e Ferizajt','Y'),('3748','855','Komuna e Fushë Kosovës','Y'),('3749','855','Komuna e Gjakovës','Y'),('375','76','Paraíba','Y'),('3750','855','Komuna e Gjilanit','Y'),('3751','855','Komuna e Drenasit','Y'),('3752','855','Komuna e Istogut','Y'),('3753','855','Komuna e Kaçanikut','Y'),('3754','855','Komuna e Kamenicës','Y'),('3755','855','Komuna e Klinës','Y'),('3756','855','Komuna e Leposaviqit','Y'),('3757','855','Komuna e Lipjanit','Y'),('3758','855','Komuna e Malisheves','Y'),('3759','855','Komuna e Mitrovicës','Y'),('376','76','Paraná','Y'),('3760','855','Komuna e Novobërdës','Y'),('3761','855','Komuna e Obiliqit','Y'),('3762','855','Komuna e Pejës','Y'),('3763','855','Komuna e Podujevës','Y'),('3764','855','Komuna e Prishtinës','Y'),('3765','855','Komuna e Prizrenit','Y'),('3766','855','Komuna e Rahovecit','Y'),('3767','855','Komuna e Shtërpcës','Y'),('3768','855','Komuna e Shtimes','Y'),('3769','855','Komuna e Skenderajt','Y'),('377','76','Pernambuco','Y'),('3770','855','Komuna e Thërandës','Y'),('3771','855','Komuna e Vitisë','Y'),('3772','855','Komuna e Vushtrrisë','Y'),('3773','855','Komuna e Zubin Potokut','Y'),('3774','855','Komuna e Zveçanit','Y'),('3775','858','Artigas Department','Y'),('3776','858','Canelones Department','Y'),('3777','858','Cerro Largo Department','Y'),('3778','858','Colonia Department','Y'),('3779','858','Durazno','Y'),('378','76','Piauí','Y'),('3780','858','Flores','Y'),('3781','858','Florida Department','Y'),('3782','858','Lavalleja Department','Y'),('3783','858','Maldonado Department','Y'),('3784','858','Montevideo','Y'),('3785','858','Paysandú','Y'),('3786','858','Río Negro','Y'),('3787','858','Rivera','Y'),('3788','858','Rocha','Y'),('3789','858','Salto','Y'),('379','76','State of Rio de Janeiro','Y'),('3790','858','San José','Y'),('3791','858','Soriano Department','Y'),('3792','858','Tacuarembó','Y'),('3793','858','Treinta y Tres','Y'),('3794','860','Andijon','Y'),('3795','860','Buxoro','Y'),('3796','860','Farg ona','Y'),('3797','860','Xorazm','Y'),('3798','860','Namangan','Y'),('3799','860','Navoiy','Y'),('38','8','Elbasan','Y'),('380','76','Rio Grande do Norte','Y'),('3800','860','Qashqadaryo','Y'),('3801','860','Karakalpakstan','Y'),('3802','860','Samarqand','Y'),('3803','860','Surxondaryo','Y'),('3804','860','Toshkent Shahri','Y'),('3805','860','Toshkent','Y'),('3806','860','Jizzax','Y'),('3807','860','Sirdaryo','Y'),('3808','862','Amazonas','Y'),('3809','862','Anzoátegui','Y'),('381','76','Rio Grande do Sul','Y'),('3810','862','Apure','Y'),('3811','862','Aragua','Y'),('3812','862','Barinas','Y'),('3813','862','Bolívar','Y'),('3814','862','Carabobo','Y'),('3815','862','Cojedes','Y'),('3816','862','Delta Amacuro','Y'),('3817','862','Distrito Federal','Y'),('3818','862','Falcón','Y'),('3819','862','Guárico','Y'),('382','76','Rondônia','Y'),('3820','862','Lara','Y'),('3821','862','Mérida','Y'),('3822','862','Miranda','Y'),('3823','862','Monagas','Y'),('3824','862','Isla Margarita','Y'),('3825','862','Portuguesa','Y'),('3826','862','Sucre','Y'),('3827','862','Táchira','Y'),('3828','862','Trujillo','Y'),('3829','862','Yaracuy','Y'),('383','76','Roraima','Y'),('3830','862','Zulia','Y'),('3831','862','Dependencias Federales','Y'),('3832','862','Distrito Capital','Y'),('3833','862','Vargas','Y'),('3834','882','A‘ana','Y'),('3835','882','Aiga-i-le-Tai','Y'),('3836','882','Atua','Y'),('3837','882','Fa‘asaleleaga','Y'),('3838','882','Gaga‘emauga','Y'),('3839','882','Va‘a-o-Fonoti','Y'),('384','76','Santa Catarina','Y'),('3840','882','Gagaifomauga','Y'),('3841','882','Palauli','Y'),('3842','882','Satupa‘itea','Y'),('3843','882','Tuamasaga','Y'),('3844','882','Vaisigano','Y'),('3845','887','Abyan','Y'),('3846','887','‘Adan','Y'),('3847','887','Al Mahrah','Y'),('3848','887','Ḩaḑramawt','Y'),('3849','887','Shabwah','Y'),('385','76','São Paulo','Y'),('3850','887','San’a’','Y'),('3851','887','Ta’izz','Y'),('3852','887','Al Ḩudaydah','Y'),('3853','887','Dhamar','Y'),('3854','887','Al Maḩwīt','Y'),('3855','887','Dhamār','Y'),('3856','887','Maʼrib','Y'),('3857','887','Şa‘dah','Y'),('3858','887','Şan‘āʼ','Y'),('3859','887','Aḑ Ḑāli‘','Y'),('386','76','Sergipe','Y'),('3860','887','Omran','Y'),('3861','887','Al Bayḑāʼ','Y'),('3862','887','Al Jawf','Y'),('3863','887','Ḩajjah','Y'),('3864','887','Ibb','Y'),('3865','887','Laḩij','Y'),('3866','887','Ta‘izz','Y'),('3867','887','Amanat Al Asimah','Y'),('3868','887','Muḩāfaz̧at Raymah','Y'),('3869','891','Crna Gora (Montenegro)','Y'),('387','76','Estado de Goiás','Y'),('3870','891','Srbija (Serbia)','Y'),('3871','894','North-Western','Y'),('3872','894','Copperbelt','Y'),('3873','894','Western','Y'),('3874','894','Southern','Y'),('3875','894','Central','Y'),('3876','894','Eastern','Y'),('3877','894','Northern','Y'),('3878','894','Luapula','Y'),('3879','894','Lusaka','Y'),('388','76','Pernambuco','Y'),('389','76','Tocantins','Y'),('39','8','Fier','Y'),('390','84','Belize','Y'),('391','84','Cayo','Y'),('392','84','Corozal','Y'),('393','84','Orange Walk','Y'),('394','84','Stann Creek','Y'),('395','84','Toledo','Y'),('396','86','British Indian Ocean Territory','Y'),('397','90','Malaita','Y'),('398','90','Western','Y'),('399','90','Central','Y'),('4','4','Bāmīān','Y'),('40','8','Gjirokastër','Y'),('400','90','Guadalcanal','Y'),('401','90','Isabel','Y'),('402','90','Makira','Y'),('403','90','Temotu','Y'),('404','90','Central Province','Y'),('405','90','Choiseul','Y'),('406','90','Rennell and Bellona','Y'),('407','90','Rennell and Bellona','Y'),('408','92','British Virgin Islands','Y'),('409','96','Belait','Y'),('41','8','Korçë','Y'),('410','96','Brunei and Muara','Y'),('411','96','Temburong','Y'),('412','96','Tutong','Y'),('413','100','Burgas','Y'),('414','100','Grad','Y'),('415','100','Khaskovo','Y'),('416','100','Lovech','Y'),('417','100','Mikhaylovgrad','Y'),('418','100','Plovdiv','Y'),('419','100','Razgrad','Y'),('42','8','Kukës','Y'),('420','100','Sofiya','Y'),('421','100','Varna','Y'),('422','100','Blagoevgrad','Y'),('423','100','Burgas','Y'),('424','100','Dobrich','Y'),('425','100','Gabrovo','Y'),('426','100','Oblast Sofiya-Grad','Y'),('427','100','Khaskovo','Y'),('428','100','Kŭrdzhali','Y'),('429','100','Kyustendil','Y'),('43','8','Lezhë','Y'),('430','100','Lovech','Y'),('431','100','Montana','Y'),('432','100','Pazardzhit','Y'),('433','100','Pernik','Y'),('434','100','Pleven','Y'),('435','100','Plovdiv','Y'),('436','100','Razgrad','Y'),('437','100','Ruse','Y'),('438','100','Shumen','Y'),('439','100','Silistra','Y'),('44','8','Shkodër','Y'),('440','100','Sliven','Y'),('441','100','Smolyan','Y'),('442','100','Sofiya','Y'),('443','100','Stara Zagora','Y'),('444','100','Tŭrgovishte','Y'),('445','100','Varna','Y'),('446','100','Veliko Tŭrnovo','Y'),('447','100','Vidin','Y'),('448','100','Vratsa','Y'),('449','100','Yambol','Y'),('45','8','Tiranë','Y'),('450','104','Rakhine State','Y'),('451','104','Chin State','Y'),('452','104','Ayeyarwady','Y'),('453','104','Kachin State','Y'),('454','104','Kayin State','Y'),('455','104','Kayah State','Y'),('456','104','Magwe','Y'),('457','104','Mandalay','Y'),('458','104','Pegu','Y'),('459','104','Sagain','Y'),('46','8','Vlorë','Y'),('460','104','Shan State','Y'),('461','104','Tanintharyi','Y'),('462','104','Mon State','Y'),('463','104','Rangoon','Y'),('464','104','Magway','Y'),('465','104','Bago','Y'),('466','104','Yangon','Y'),('467','108','Bujumbura','Y'),('468','108','Bubanza','Y'),('469','108','Bururi','Y'),('47','12','Alger','Y'),('470','108','Cankuzo','Y'),('471','108','Cibitoke','Y'),('472','108','Gitega','Y'),('473','108','Karuzi','Y'),('474','108','Kayanza','Y'),('475','108','Kirundo','Y'),('476','108','Makamba','Y'),('477','108','Muyinga','Y'),('478','108','Ngozi','Y'),('479','108','Rutana','Y'),('48','12','Batna','Y'),('480','108','Ruyigi','Y'),('481','108','Muramvya','Y'),('482','108','Mwaro','Y'),('483','112','Brestskaya Voblastsʼ','Y'),('484','112','Homyelʼskaya Voblastsʼ','Y'),('485','112','Hrodzyenskaya Voblastsʼ','Y'),('486','112','Mahilyowskaya Voblastsʼ','Y'),('487','112','Horad Minsk','Y'),('488','112','Minskaya Voblastsʼ','Y'),('489','112','Vitsyebskaya Voblastsʼ','Y'),('49','12','Constantine','Y'),('490','116','Krŏng Preăh Seihânŭ','Y'),('491','116','Kâmpóng Cham','Y'),('492','116','Kâmpóng Chhnăng','Y'),('493','116','Khétt Kâmpóng Spœ','Y'),('494','116','Kâmpóng Thum','Y'),('495','116','Kândal','Y'),('496','116','Kaôh Kŏng','Y'),('497','116','Krâchéh','Y'),('498','116','Môndól Kiri','Y'),('499','116','Phnum Penh','Y'),('5','4','Farah','Y'),('50','12','Médéa','Y'),('500','116','Poŭthĭsăt','Y'),('501','116','Preăh Vihéar','Y'),('502','116','Prey Vêng','Y'),('503','116','Stœ̆ng Trêng','Y'),('504','116','Svay Riĕng','Y'),('505','116','Takêv','Y'),('506','116','Kâmpôt','Y'),('507','116','Phnum Pénh','Y'),('508','116','Rôtânăh Kiri','Y'),('509','116','Siĕm Réab','Y'),('51','12','Mostaganem','Y'),('510','116','Bantéay Méan Cheăy','Y'),('511','116','Kêb','Y'),('512','116','Ŏtdâr Méan Cheăy','Y'),('513','116','Preăh Seihânŭ','Y'),('514','116','Bătdâmbâng','Y'),('515','116','Palĭn','Y'),('516','120','Est','Y'),('517','120','Littoral','Y'),('518','120','North-West Province','Y'),('519','120','Ouest','Y'),('52','12','Oran','Y'),('520','120','South-West Province','Y'),('521','120','Adamaoua','Y'),('522','120','Centre','Y'),('523','120','Extreme-Nord','Y'),('524','120','North Province','Y'),('525','120','South Province','Y'),('526','124','Alberta','Y'),('527','124','British Columbia','Y'),('528','124','Manitoba','Y'),('529','124','New Brunswick','Y'),('53','12','Saïda','Y'),('530','124','Newfoundland and Labrador','Y'),('531','124','Nova Scotia','Y'),('532','124','Ontario','Y'),('533','124','Prince Edward Island','Y'),('534','124','Quebec','Y'),('535','124','Saskatchewan','Y'),('536','124','Yukon','Y'),('537','124','Northwest Territories','Y'),('538','124','Nunavut','Y'),('539','132','Boa Vista','Y'),('54','12','Sétif','Y'),('540','132','Brava','Y'),('541','132','Maio','Y'),('542','132','Paul','Y'),('543','132','Praia','Y'),('544','132','Ribeira Grande','Y'),('545','132','Sal','Y'),('546','132','Santa Catarina   ','Y'),('547','132','São Nicolau','Y'),('548','132','São Vicente','Y'),('549','132','Tarrafal ','Y'),('55','12','Tiaret','Y'),('550','132','Mosteiros','Y'),('551','132','Praia','Y'),('552','132','Santa Catarina','Y'),('553','132','Santa Cruz','Y'),('554','132','São Domingos','Y'),('555','132','São Filipe','Y'),('556','132','São Miguel','Y'),('557','132','Tarrafal','Y'),('558','136','Creek','Y'),('559','136','Eastern','Y'),('56','12','Tizi Ouzou','Y'),('560','136','Midland','Y'),('561','136','South Town','Y'),('562','136','Spot Bay','Y'),('563','136','Stake Bay','Y'),('564','136','West End','Y'),('565','136','Western','Y'),('566','140','Bamingui-Bangoran','Y'),('567','140','Basse-Kotto','Y'),('568','140','Haute-Kotto','Y'),('569','140','Mambéré-Kadéï','Y'),('57','12','Tlemcen','Y'),('570','140','Haut-Mbomou','Y'),('571','140','Kémo','Y'),('572','140','Lobaye','Y'),('573','140','Mbomou','Y'),('574','140','Nana-Mambéré','Y'),('575','140','Ouaka','Y'),('576','140','Ouham','Y'),('577','140','Ouham-Pendé','Y'),('578','140','Vakaga','Y'),('579','140','Nana-Grébizi','Y'),('58','12','Bejaïa','Y'),('580','140','Sangha-Mbaéré','Y'),('581','140','Ombella-Mpoko','Y'),('582','140','Bangui','Y'),('583','144','Central','Y'),('584','144','North Central','Y'),('585','144','North Eastern','Y'),('586','144','North Western','Y'),('587','144','Sabaragamuwa','Y'),('588','144','Southern','Y'),('589','144','Uva','Y'),('59','12','Biskra','Y'),('590','144','Western','Y'),('591','148','Batha','Y'),('592','148','Biltine','Y'),('593','148','Borkou-Ennedi-Tibesti','Y'),('594','148','Chari-Baguirmi','Y'),('595','148','Guéra','Y'),('596','148','Kanem','Y'),('597','148','Lac','Y'),('598','148','Logone Occidental','Y'),('599','148','Logone Oriental','Y'),('6','4','Faryab Province','Y'),('60','12','Blida','Y'),('600','148','Mayo-Kébbi','Y'),('601','148','Moyen-Chari','Y'),('602','148','Ouaddaï','Y'),('603','148','Salamat','Y'),('604','148','Tandjilé','Y'),('605','152','Valparaíso','Y'),('606','152','Aisén del General Carlos Ibáñez del Campo','Y'),('607','152','Antofagasta','Y'),('608','152','Araucanía','Y'),('609','152','Atacama','Y'),('61','12','Bouira','Y'),('610','152','Bío-Bío','Y'),('611','152','Coquimbo','Y'),('612','152','Libertador General Bernardo OʼHiggins','Y'),('613','152','Los Lagos','Y'),('614','152','Magallanes y Antártica Chilena','Y'),('615','152','Maule','Y'),('616','152','Región Metropolitana','Y'),('617','152','Tarapaca','Y'),('618','152','Los Lagos','Y'),('619','152','Tarapacá','Y'),('62','12','Djelfa','Y'),('620','152','Región de Arica y Parinacota','Y'),('621','152','Región de Los Ríos','Y'),('622','156','Anhui','Y'),('623','156','Zhejiang','Y'),('624','156','Jiangxi','Y'),('625','156','Jiangsu','Y'),('626','156','Jilin','Y'),('627','156','Qinghai','Y'),('628','156','Fujian','Y'),('629','156','Heilongjiang','Y'),('63','12','Guelma','Y'),('630','156','Henan','Y'),('631','156','disputed','Y'),('632','156','Hebei','Y'),('633','156','Hunan Province','Y'),('634','156','Hubei','Y'),('635','156','Xinjiang','Y'),('636','156','Xizang','Y'),('637','156','Gansu','Y'),('638','156','Guangxi','Y'),('639','156','Guizhou','Y'),('64','12','Jijel','Y'),('640','156','Liaoning Province','Y'),('641','156','Inner Mongolia','Y'),('642','156','Ningxia','Y'),('643','156','Beijing','Y'),('644','156','Shanghai','Y'),('645','156','Shanxi','Y'),('646','156','Shandong','Y'),('647','156','Shaanxi','Y'),('648','156','Tianjin','Y'),('649','156','Yunnan Province','Y'),('65','12','Laghouat','Y'),('650','156','Guangdong','Y'),('651','156','Hainan Province','Y'),('652','156','Sichuan','Y'),('653','156','Chongqing','Y'),('654','156','PF99','Y'),('655','158','Fukien','Y'),('656','158','Kaohsiung','Y'),('657','158','Taipei','Y'),('658','158','Taiwan','Y'),('659','162','Christmas Island','Y'),('66','12','Mascara','Y'),('660','166','Cocos (Keeling) Islands','Y'),('661','170','Amazonas','Y'),('662','170','Antioquia','Y'),('663','170','Arauca','Y'),('664','170','Atlántico','Y'),('665','170','Bolívar','Y'),('666','170','Boyacá','Y'),('667','170','Caldas','Y'),('668','170','Caquetá','Y'),('669','170','Cauca','Y'),('67','12','Mʼsila','Y'),('670','170','Cesar','Y'),('671','170','Chocó','Y'),('672','170','Córdoba','Y'),('673','170','Guaviare','Y'),('674','170','Guainía','Y'),('675','170','Huila','Y'),('676','170','La Guajira','Y'),('677','170','Magdalena','Y'),('678','170','Meta','Y'),('679','170','Nariño','Y'),('68','12','Oum el Bouaghi','Y'),('680','170','Norte de Santander','Y'),('681','170','Putumayo','Y'),('682','170','Quindío','Y'),('683','170','Risaralda','Y'),('684','170','Archipiélago de San Andrés, Providencia y San','Y'),('685','170','Santander','Y'),('686','170','Sucre','Y'),('687','170','Tolima','Y'),('688','170','Valle del Cauca','Y'),('689','170','Vaupés','Y'),('69','12','Sidi Bel Abbès','Y'),('690','170','Vichada','Y'),('691','170','Casanare','Y'),('692','170','Cundinamarca','Y'),('693','170','Bogota D.C.','Y'),('694','170','Bolívar','Y'),('695','170','Boyacá','Y'),('696','170','Caldas','Y'),('697','170','Magdalena','Y'),('698','174','Anjouan','Y'),('699','174','Grande Comore','Y'),('7','4','Ghaznī','Y'),('70','12','Skikda','Y'),('700','174','Mohéli','Y'),('701','175','Mayotte','Y'),('702','178','Bouenza','Y'),('703','178','CF03','Y'),('704','178','Kouilou','Y'),('705','178','Lékoumou','Y'),('706','178','Likouala','Y'),('707','178','Niari','Y'),('708','178','Plateaux','Y'),('709','178','Sangha','Y'),('71','12','Tébessa','Y'),('710','178','Pool','Y'),('711','178','Brazzaville','Y'),('712','178','Cuvette','Y'),('713','178','Cuvette-Ouest','Y'),('714','178','Pointe-Noire','Y'),('715','180','Bandundu','Y'),('716','180','Équateur','Y'),('717','180','Kasaï-Occidental','Y'),('718','180','Kasaï-Oriental','Y'),('719','180','Katanga','Y'),('72','12','Adrar','Y'),('720','180','Kinshasa','Y'),('721','180','Bas-Congo','Y'),('722','180','Orientale','Y'),('723','180','Maniema','Y'),('724','180','Nord-Kivu','Y'),('725','180','Sud-Kivu','Y'),('726','184','Cook Islands','Y'),('727','188','Alajuela','Y'),('728','188','Cartago','Y'),('729','188','Guanacaste','Y'),('73','12','Aïn Defla','Y'),('730','188','Heredia','Y'),('731','188','Limón','Y'),('732','188','Puntarenas','Y'),('733','188','San José','Y'),('734','191','Bjelovarsko-Bilogorska','Y'),('735','191','Brodsko-Posavska','Y'),('736','191','Dubrovačko-Neretvanska','Y'),('737','191','Istarska','Y'),('738','191','Karlovačka','Y'),('739','191','Koprivničko-Križevačka','Y'),('74','12','Aïn Temouchent','Y'),('740','191','Krapinsko-Zagorska','Y'),('741','191','Ličko-Senjska','Y'),('742','191','Međimurska','Y'),('743','191','Osječko-Baranjska','Y'),('744','191','Požeško-Slavonska','Y'),('745','191','Primorsko-Goranska','Y'),('746','191','Šibensko-Kniniska','Y'),('747','191','Sisačko-Moslavačka','Y'),('748','191','Splitsko-Dalmatinska','Y'),('749','191','Varaždinska','Y'),('75','12','Annaba','Y'),('750','191','Virovitičk-Podravska','Y'),('751','191','Vukovarsko-Srijemska','Y'),('752','191','Zadarska','Y'),('753','191','Zagrebačka','Y'),('754','191','Grad Zagreb','Y'),('755','192','Pinar del Río','Y'),('756','192','Ciudad de La Habana','Y'),('757','192','Matanzas','Y'),('758','192','Isla de la Juventud','Y'),('759','192','Camagüey','Y'),('76','12','Béchar','Y'),('760','192','Ciego de Ávila','Y'),('761','192','Cienfuegos','Y'),('762','192','Granma','Y'),('763','192','Guantánamo','Y'),('764','192','La Habana','Y'),('765','192','Holguín','Y'),('766','192','Las Tunas','Y'),('767','192','Sancti Spíritus','Y'),('768','192','Santiago de Cuba','Y'),('769','192','Villa Clara','Y'),('77','12','Bordj Bou Arréridj','Y'),('770','196','Famagusta','Y'),('771','196','Kyrenia','Y'),('772','196','Larnaca','Y'),('773','196','Nicosia','Y'),('774','196','Limassol','Y'),('775','196','Paphos','Y'),('776','203','Hradec Kralove','Y'),('777','203','Jablonec nad Nisou','Y'),('778','203','Jiein','Y'),('779','203','Jihlava','Y'),('78','12','Boumerdes','Y'),('780','203','Kolin','Y'),('781','203','Liberec','Y'),('782','203','Melnik','Y'),('783','203','Mlada Boleslav','Y'),('784','203','Nachod','Y'),('785','203','Nymburk','Y'),('786','203','Pardubice','Y'),('787','203','Hlavní Mesto Praha','Y'),('788','203','Semily','Y'),('789','203','Trutnov','Y'),('79','12','Chlef','Y'),('790','203','South Moravian Region','Y'),('791','203','Jihočeský Kraj','Y'),('792','203','Vysočina','Y'),('793','203','Karlovarský Kraj','Y'),('794','203','Královéhradecký Kraj','Y'),('795','203','Liberecký Kraj','Y'),('796','203','Olomoucký Kraj','Y'),('797','203','Moravskoslezský Kraj','Y'),('798','203','Pardubický Kraj','Y'),('799','203','Plzeňský Kraj','Y'),('8','4','Ghowr','Y'),('80','12','El Bayadh','Y'),('800','203','Středočeský Kraj','Y'),('801','203','Ústecký Kraj','Y'),('802','203','Zlínský Kraj','Y'),('803','204','Atakora','Y'),('804','204','Atlantique','Y'),('805','204','Alibori','Y'),('806','204','Borgou','Y'),('807','204','Collines','Y'),('808','204','Kouffo','Y'),('809','204','Donga','Y'),('81','12','El Oued','Y'),('810','204','Littoral','Y'),('811','204','Mono','Y'),('812','204','Ouémé','Y'),('813','204','Plateau','Y'),('814','204','Zou','Y'),('815','208','Århus','Y'),('816','208','Bornholm','Y'),('817','208','Frederiksborg','Y'),('818','208','Fyn','Y'),('819','208','Copenhagen city','Y'),('82','12','El Tarf','Y'),('820','208','København','Y'),('821','208','Nordjylland','Y'),('822','208','Ribe','Y'),('823','208','Ringkøbing','Y'),('824','208','Roskilde','Y'),('825','208','Sønderjylland','Y'),('826','208','Storstrøm','Y'),('827','208','Vejle','Y'),('828','208','Vestsjælland','Y'),('829','208','Viborg','Y'),('83','12','Ghardaïa','Y'),('830','208','Fredriksberg','Y'),('831','208','Capital Region','Y'),('832','208','Central Jutland','Y'),('833','208','North Jutland','Y'),('834','208','Region Zealand','Y'),('835','208','Region South Denmark','Y'),('836','212','Saint Andrew','Y'),('837','212','Saint David','Y'),('838','212','Saint George','Y'),('839','212','Saint John','Y'),('84','12','Illizi','Y'),('840','212','Saint Joseph','Y'),('841','212','Saint Luke','Y'),('842','212','Saint Mark','Y'),('843','212','Saint Patrick','Y'),('844','212','Saint Paul','Y'),('845','212','Saint Peter','Y'),('846','214','Azua','Y'),('847','214','Baoruco','Y'),('848','214','Barahona','Y'),('849','214','Dajabón','Y'),('85','12','Khenchela','Y'),('850','214','Duarte','Y'),('851','214','Espaillat','Y'),('852','214','Independencia','Y'),('853','214','La Altagracia','Y'),('854','214','Elías Piña','Y'),('855','214','La Romana','Y'),('856','214','María Trinidad Sánchez','Y'),('857','214','Monte Cristi','Y'),('858','214','Pedernales','Y'),('859','214','Puerto Plata','Y'),('86','12','Mila','Y'),('860','214','Salcedo','Y'),('861','214','Samaná','Y'),('862','214','Sánchez Ramírez','Y'),('863','214','San Juan','Y'),('864','214','San Pedro de Macorís','Y'),('865','214','Santiago','Y'),('866','214','Santiago Rodríguez','Y'),('867','214','Valverde','Y'),('868','214','El Seíbo','Y'),('869','214','Hato Mayor','Y'),('87','12','Naama النعامة','Y'),('870','214','La Vega','Y'),('871','214','Monseñor Nouel','Y'),('872','214','Monte Plata','Y'),('873','214','San Cristóbal','Y'),('874','214','Distrito Nacional','Y'),('875','214','Peravia','Y'),('876','214','San José de Ocoa','Y'),('877','214','Santo Domingo','Y'),('878','218','Galápagos','Y'),('879','218','Azuay','Y'),('88','12','Ouargla','Y'),('880','218','Bolívar','Y'),('881','218','Cañar','Y'),('882','218','Carchi','Y'),('883','218','Chimborazo','Y'),('884','218','Cotopaxi','Y'),('885','218','El Oro','Y'),('886','218','Esmeraldas','Y'),('887','218','Guayas','Y'),('888','218','Imbabura','Y'),('889','218','Loja','Y'),('89','12','Relizane','Y'),('890','218','Los Ríos','Y'),('891','218','Manabí','Y'),('892','218','Morona-Santiago','Y'),('893','218','Napo','Y'),('894','218','Pastaza','Y'),('895','218','Pichincha','Y'),('896','218','Tungurahua','Y'),('897','218','Zamora-Chinchipe','Y'),('898','218','Sucumbios','Y'),('899','218','Napo','Y'),('9','4','Helmand Province','Y'),('90','12','Souk Ahras','Y'),('900','218','Orellana','Y'),('901','218','Provincia de Santa Elena','Y'),('902','218','Provincia de Santo Domingo de los Tsáchilas','Y'),('903','222','Ahuachapán','Y'),('904','222','Cabañas','Y'),('905','222','Chalatenango','Y'),('906','222','Cuscatlán','Y'),('907','222','La Libertad','Y'),('908','222','La Paz','Y'),('909','222','La Unión','Y'),('91','12','Tamanghasset','Y'),('910','222','Morazán','Y'),('911','222','San Miguel','Y'),('912','222','San Salvador','Y'),('913','222','Santa Ana','Y'),('914','222','San Vicente','Y'),('915','222','Sonsonate','Y'),('916','222','Usulután','Y'),('917','226','Annobón','Y'),('918','226','Bioko Norte','Y'),('919','226','Bioko Sur','Y'),('92','12','Tindouf','Y'),('920','226','Centro Sur','Y'),('921','226','Kié-Ntem','Y'),('922','226','Litoral','Y'),('923','226','Wele-Nzas','Y'),('924','231','Arsi','Y'),('925','231','Gonder','Y'),('926','231','Bale','Y'),('927','231','Eritrea','Y'),('928','231','Gamo Gofa','Y'),('929','231','Gojam','Y'),('93','12','Tipaza','Y'),('930','231','Harerge','Y'),('931','231','Ilubabor','Y'),('932','231','Kefa','Y'),('933','231','Addis Abeba','Y'),('934','231','Sidamo','Y'),('935','231','Tigray','Y'),('936','231','Welega Kifle Hager','Y'),('937','231','Welo','Y'),('938','231','Adis Abeba','Y'),('939','231','Asosa','Y'),('94','12','Tissemsilt','Y'),('940','231','Borena ','Y'),('941','231','Debub Gonder','Y'),('942','231','Debub Shewa','Y'),('943','231','Debub Welo','Y'),('944','231','Dire Dawa','Y'),('945','231','Gambela','Y'),('946','231','Metekel','Y'),('947','231','Mirab Gojam','Y'),('948','231','Mirab Harerge','Y'),('949','231','Mirab Shewa','Y'),('95','16','American Samoa','Y'),('950','231','Misrak Gojam','Y'),('951','231','Misrak Harerge','Y'),('952','231','Nazret','Y'),('953','231','Ogaden','Y'),('954','231','Omo','Y'),('955','231','Semen Gonder','Y'),('956','231','Semen Shewa','Y'),('957','231','Semen Welo','Y'),('958','231','Tigray','Y'),('959','231','Bale','Y'),('96','20','Parròquia de Canillo','Y'),('960','231','Gamo Gofa','Y'),('961','231','Ilubabor','Y'),('962','231','Kefa','Y'),('963','231','Sidamo','Y'),('964','231','Welega','Y'),('965','231','Ādīs Ābeba','Y'),('966','231','Āfar','Y'),('967','231','Āmara','Y'),('968','231','Bīnshangul Gumuz','Y'),('969','231','Dirē Dawa','Y'),('97','20','Parròquia dʼEncamp','Y'),('970','231','Gambēla Hizboch','Y'),('971','231','Hārerī Hizb','Y'),('972','231','Oromīya','Y'),('973','231','Sumalē','Y'),('974','231','Tigray','Y'),('975','231','YeDebub Bihēroch Bihēreseboch na Hizboch','Y'),('976','232','Ānseba','Y'),('977','232','Debub','Y'),('978','232','Debubawī Kʼeyih Bahrī','Y'),('979','232','Gash Barka','Y'),('98','20','Parròquia de la Massana','Y'),('980','232','Maʼākel','Y'),('981','232','Semēnawī Kʼeyih Bahrī','Y'),('982','233','Harjumaa','Y'),('983','233','Hiiumaa','Y'),('984','233','Ida-Virumaa','Y'),('985','233','Järvamaa','Y'),('986','233','Jõgevamaa','Y'),('987','233','Läänemaa','Y'),('988','233','Lääne-Virumaa','Y'),('989','233','Pärnumaa','Y'),('99','20','Parròquia dʼOrdino','Y'),('990','233','Põlvamaa','Y'),('991','233','Raplamaa','Y'),('992','233','Saaremaa','Y'),('993','233','Tartumaa','Y'),('994','233','Valgamaa','Y'),('995','233','Viljandimaa','Y'),('996','233','Võrumaa','Y'),('997','234','Norðoyar region','Y'),('998','234','Eysturoy region','Y'),('999','234','Sandoy region','Y');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storagebin`
--

DROP TABLE IF EXISTS `storagebin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storagebin` (
  `STORAGEBIN_ID` varchar(32) NOT NULL,
  `NAME` varchar(60) NOT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `ROW` varchar(10) DEFAULT NULL,
  `STACK` varchar(10) DEFAULT NULL,
  `LEVEL` varchar(10) DEFAULT NULL,
  `WAREHOUSE_ID` varchar(32) NOT NULL,
  `STORE_ID` varchar(32) NOT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) NOT NULL,
  PRIMARY KEY (`STORAGEBIN_ID`),
  UNIQUE KEY `STORAGEBIN_ID_UNIQUE` (`STORAGEBIN_ID`),
  UNIQUE KEY `STORAGEBIN_NAME_UNIQUE` (`NAME`,`ROW`,`STACK`,`LEVEL`,`WAREHOUSE_ID`),
  KEY `FK_STORAGEBIN_WAREHOUSE_idx` (`WAREHOUSE_ID`),
  KEY `FK_STORAGEBIN_STORE_idx` (`STORE_ID`),
  KEY `FK_STORAGEBIN_MERCHANT_idx` (`MERCHANT_ID`),
  CONSTRAINT `FK_STORAGEBIN_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_STORAGEBIN_STORE` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_STORAGEBIN_WAREHOUSE` FOREIGN KEY (`WAREHOUSE_ID`) REFERENCES `warehouse` (`WAREHOUSE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storagebin`
--

LOCK TABLES `storagebin` WRITE;
/*!40000 ALTER TABLE `storagebin` DISABLE KEYS */;
/*!40000 ALTER TABLE `storagebin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `STORE_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) NOT NULL,
  PRIMARY KEY (`STORE_ID`),
  UNIQUE KEY `STORE_ID_UNIQUE` (`STORE_ID`),
  UNIQUE KEY `STORE_NAME_UNIQUE` (`NAME`,`MERCHANT_ID`),
  KEY `FK_STORE_USER_idx` (`USER_ID`),
  KEY `FK_STORE_MERCHANT_idx` (`MERCHANT_ID`),
  CONSTRAINT `FK_STORE_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_STORE_USER` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tax`
--

DROP TABLE IF EXISTS `tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tax` (
  `TAX_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `TAX_PERCENTAGE` double NOT NULL DEFAULT '0',
  `MERCHANT_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) NOT NULL,
  PRIMARY KEY (`TAX_ID`),
  UNIQUE KEY `TAX_ID_UNIQUE` (`TAX_ID`),
  UNIQUE KEY `TAX_NAME_UNIQUE` (`MERCHANT_ID`,`NAME`),
  CONSTRAINT `FK_TAX_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tax`
--

LOCK TABLES `tax` WRITE;
/*!40000 ALTER TABLE `tax` DISABLE KEYS */;
/*!40000 ALTER TABLE `tax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uom`
--

DROP TABLE IF EXISTS `uom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uom` (
  `UOM_ID` varchar(32) NOT NULL,
  `NAME` varchar(10) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) NOT NULL,
  PRIMARY KEY (`UOM_ID`),
  UNIQUE KEY `UOM_ID_UNIQUE` (`UOM_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uom`
--

LOCK TABLES `uom` WRITE;
/*!40000 ALTER TABLE `uom` DISABLE KEYS */;
/*!40000 ALTER TABLE `uom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USER_ID` varchar(32) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `EMAILID` varchar(45) NOT NULL,
  `IMAGE_ID` varchar(32) DEFAULT NULL,
  `DEVEICEID` varchar(45) DEFAULT NULL,
  `STORE_ID` varchar(32) DEFAULT NULL,
  `MERCHANT_ID` varchar(32) DEFAULT NULL,
  `USER_NAME` varchar(45) NOT NULL,
  `ROLE_ID` varchar(32) DEFAULT NULL,
  `ADDRESS_ID` varchar(32) DEFAULT NULL,
  `PHONE_NO` varchar(32) DEFAULT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_NAME_UNIQUE` (`USER_NAME`),
  KEY `FK_USER_IMAGE_idx` (`IMAGE_ID`),
  KEY `FK_USER_STORE_idx` (`STORE_ID`),
  KEY `FK_USER_MERCHANT_idx` (`MERCHANT_ID`),
  KEY `FK_USER_ROLE_idx` (`ROLE_ID`),
  KEY `FK_USER_ADDRESS_idx` (`ADDRESS_ID`),
  CONSTRAINT `FK_USER_ADDRESS` FOREIGN KEY (`ADDRESS_ID`) REFERENCES `address` (`ADDRESS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_IMAGE` FOREIGN KEY (`IMAGE_ID`) REFERENCES `image` (`IMAGE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_STORE` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('ff808181511baaf501511bae0a070002','Prabakaran','$2a$10$eJifrz/5CCRb3bjUbQzyfe/z86SOGtUHQ0tNqXw1PFW.yk7l.dWp.','prabakaran.a@mitosistech.com','ff808181511baaf501511bae09ff0001',NULL,NULL,NULL,'prabakaran.a','ab123','ff808181511baaf501511bae0a070003','8489241198','Y','2015-11-20 05:20:23','123','2015-11-18 17:39:29','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse` (
  `WAREHOUSE_ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `MERCHANT_ID` varchar(32) NOT NULL,
  `STORE_ID` varchar(32) NOT NULL,
  `ISACTIVE` char(1) NOT NULL DEFAULT 'Y',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATEDBY` varchar(32) NOT NULL,
  `UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATEDBY` varchar(32) NOT NULL,
  `ADDRESS_ID` varchar(32) NOT NULL,
  PRIMARY KEY (`WAREHOUSE_ID`),
  UNIQUE KEY `WAREHOUSE_ID_UNIQUE` (`WAREHOUSE_ID`),
  UNIQUE KEY `WAREHOUSE_NAME_UNIQUE` (`NAME`,`STORE_ID`),
  KEY `FK_WAREHOUSE_MERCHANT_idx` (`MERCHANT_ID`),
  KEY `FK_WAREHOUSE_STORE_idx` (`STORE_ID`),
  KEY `FK_WAREHOUSE_ADDRESS_idx` (`ADDRESS_ID`),
  CONSTRAINT `FK_WAREHOUSE_MERCHANT` FOREIGN KEY (`MERCHANT_ID`) REFERENCES `merchant` (`MERCHANT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_WAREHOUSE_STORE` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-20 10:54:22
