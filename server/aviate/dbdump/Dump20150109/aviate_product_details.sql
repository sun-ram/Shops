CREATE DATABASE  IF NOT EXISTS `aviate` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `aviate`;
-- MySQL dump 10.13  Distrib 5.5.40, for debian-linux-gnu (x86_64)
--
-- Host: 182.74.202.178    Database: aviate
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.12.10.1-log

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
-- Table structure for table `product_details`
--

DROP TABLE IF EXISTS `product_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_details` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_type_id` int(11) DEFAULT NULL,
  `product_offer_id` int(11) DEFAULT NULL,
  `productName` varchar(100) DEFAULT NULL,
  `measurement` varchar(100) DEFAULT NULL,
  `brand` varchar(45) DEFAULT NULL,
  `group_count` varchar(45) DEFAULT NULL,
  `avilability` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `product_description` varchar(500) DEFAULT NULL,
  `updatedOn` timestamp NULL DEFAULT NULL,
  `createdBy` varchar(100) DEFAULT NULL,
  `updatedBy` varchar(100) DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `merchantId` int(11) DEFAULT NULL,
  `active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_details`
--

LOCK TABLES `product_details` WRITE;
/*!40000 ALTER TABLE `product_details` DISABLE KEYS */;
INSERT INTO `product_details` VALUES (1,1,NULL,'Udhaiyam gram dal','1 kg',NULL,NULL,NULL,NULL,'Udhaiyam gram dal',NULL,NULL,NULL,NULL,1,NULL),(2,196,NULL,'Moong dal green','1 kg',NULL,NULL,NULL,NULL,'Moong dal green',NULL,NULL,NULL,NULL,1,NULL),(3,11,NULL,'Orid dal','1 kg',NULL,NULL,NULL,NULL,'Orid dal',NULL,NULL,NULL,NULL,1,NULL),(4,11,NULL,'Toor dal','1 kg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(6,9,NULL,'Cashwnuts','100 g',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(7,2,NULL,'LionDates','200 g',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(8,3,NULL,'GRB ghee','200 ml',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(9,3,NULL,'Idhayam Gingelly Oil','500 ml',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(10,3,NULL,'Goldwinner Sunflower Oil ','1 l',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(11,4,NULL,'Wheat Flour','1 kg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-03 14:20:51',1,NULL),(12,4,NULL,'Ginni Premium Chakki Fresh Atta','1kg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-03 14:20:51',NULL,NULL),(13,5,NULL,'Organica Organic Olive Oil Carrot Pickle','300g','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-03 14:20:51',NULL,NULL),(14,6,NULL,'Long Grain Brown Rice ','50 LB',NULL,NULL,NULL,NULL,'Rice has been a staple food source around the world for centuries, especially in eastern cultures. Rice is eaten by over half of the world population and provides 20% of the world\'s dietary energy supply',NULL,NULL,NULL,'2014-12-03 14:20:51',NULL,NULL),(32,87,NULL,'Lion Date','5kg',NULL,NULL,NULL,NULL,'healty food.',NULL,NULL,NULL,'2014-12-05 22:04:19',NULL,NULL),(129,211,NULL,'Apple','5 kg',NULL,NULL,NULL,NULL,'Good for health',NULL,NULL,NULL,'2014-12-23 10:54:56',NULL,'\0'),(131,214,NULL,'Red Grape','0.5 kg',NULL,NULL,NULL,NULL,'Sweet and fresh',NULL,NULL,NULL,'2014-12-23 11:05:03',NULL,'\0'),(132,1000,1,'test','5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-23 19:31:24',NULL,'\0'),(133,1001,2,'test22','5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 05:25:20',NULL,'\0'),(134,1001,3,'test22','5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 05:33:40',NULL,'\0'),(135,1001,1,'test22','5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 05:55:00',NULL,'\0'),(136,1001,1,'test33','5',NULL,'3',NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 06:02:45',NULL,'\0'),(137,218,1,'das','120',NULL,'12',NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 06:28:30',NULL,'\0'),(138,219,4,'N73','150',NULL,'5',NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 06:43:40',NULL,'\0'),(139,219,4,'N73','150',NULL,'5',NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 06:43:40',NULL,'\0'),(141,218,NULL,'ewrrw','dsad',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 06:52:38',NULL,'\0'),(144,221,NULL,'tomotto Chipps','12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 10:27:21',NULL,'\0'),(147,226,NULL,'Lady Finger','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 12:33:55',NULL,'\0'),(148,229,NULL,'Crunchy Fresh Wild Rocket Bowl','500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 13:13:40',NULL,'\0'),(150,230,NULL,'Live Well Herbs Sweet Basil','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 13:18:41',NULL,'\0'),(151,231,NULL,'Romaine Lettuce','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 13:22:02',NULL,'\0'),(153,233,NULL,'Red roses','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-26 06:59:49',NULL,'\0'),(155,235,NULL,'Horpic','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-26 09:22:05',NULL,'\0'),(162,240,NULL,'grap','10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-30 10:17:28',NULL,'\0'),(163,240,NULL,'dsad','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-30 11:27:45',NULL,'\0'),(176,222,NULL,'Cherry tomato','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-01 13:00:27',NULL,'\0'),(191,231,NULL,'White Shimeji Mushrooms','150',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-03 10:21:43',NULL,'\0'),(194,241,NULL,'Gardenia Jumbo 600 Enriched White Bread','200',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-08 06:25:09',NULL,'\0'),(195,241,NULL,'Gardenia Finegrain Wholemeal Bread','300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-08 06:36:19',NULL,'\0'),(196,243,NULL,'Milk Bikes','300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-09 07:01:37',NULL,'\0'),(197,242,NULL,'Milk Biscuts','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-09 07:18:52',NULL,'\0'),(198,222,NULL,'Green Apple','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-09 11:22:59',NULL,'\0'),(199,225,NULL,'Black grapes','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-09 11:24:28',NULL,'\0'),(200,225,NULL,'Kiwi fruit','500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-09 11:26:47',NULL,'\0'),(201,225,NULL,'Kiwi fruit','500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-09 11:26:48',NULL,'\0'),(202,222,NULL,'Gala apples','500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-09 11:33:36',NULL,'\0'),(203,225,NULL,'Green grapes','500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-09 11:47:05',NULL,'\0'),(204,225,NULL,'Red Grapes','500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-09 11:51:18',NULL,'\0');
/*!40000 ALTER TABLE `product_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-09 21:04:55