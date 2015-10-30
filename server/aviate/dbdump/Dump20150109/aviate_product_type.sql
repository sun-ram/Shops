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
-- Table structure for table `product_type`
--

DROP TABLE IF EXISTS `product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_type` (
  `product_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `product_type_name` varchar(100) DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT NULL,
  `createdBy` varchar(100) DEFAULT NULL,
  `updatedBy` varchar(100) DEFAULT NULL,
  `isActive` tinyint(4) DEFAULT NULL,
  `merchantId` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=246 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type`
--

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
INSERT INTO `product_type` VALUES (222,364,'Apples','2014-12-24 11:33:47',NULL,NULL,NULL,NULL,NULL),(223,364,'Citrus Fruit','2014-12-24 11:33:58',NULL,NULL,NULL,NULL,NULL),(224,364,'Exotic/Tropical Fruit','2014-12-24 11:34:13',NULL,NULL,NULL,NULL,NULL),(225,364,'Grapes','2014-12-24 11:34:28',NULL,NULL,NULL,NULL,NULL),(226,365,'Aubergine & Ladyfingers','2014-12-24 11:34:54',NULL,NULL,NULL,NULL,NULL),(227,365,'Cabbage','2014-12-24 11:35:12',NULL,NULL,NULL,NULL,NULL),(228,365,'Carrots & Root Veg','2014-12-24 11:35:31',NULL,NULL,NULL,NULL,NULL),(229,366,'Bagged Salad','2014-12-24 11:36:25',NULL,NULL,NULL,NULL,NULL),(230,366,'Herbs','2014-12-24 11:36:36',NULL,NULL,NULL,NULL,NULL),(231,366,'Lettuce','2014-12-24 11:36:55',NULL,NULL,NULL,NULL,NULL),(232,370,'salt cheese','2014-12-24 13:15:17',NULL,NULL,NULL,NULL,NULL),(233,389,'Bud roses','2014-12-26 06:42:04',NULL,NULL,NULL,NULL,NULL),(234,393,'Floor','2014-12-26 09:14:36',NULL,NULL,NULL,NULL,NULL),(235,393,'Bathroom','2014-12-26 09:15:06',NULL,NULL,NULL,NULL,NULL),(236,393,'Instruments','2014-12-26 09:15:28',NULL,NULL,NULL,NULL,NULL),(237,393,'vehicles','2014-12-26 09:16:21',NULL,NULL,NULL,NULL,NULL),(239,385,'Red apple','2014-12-26 12:29:28',NULL,NULL,NULL,NULL,NULL),(240,409,'ChildGrocery1','2014-12-30 10:15:46',NULL,NULL,NULL,NULL,NULL),(241,412,'Breads','2015-01-08 06:23:09',NULL,NULL,NULL,NULL,NULL),(242,412,'bakery','2015-01-08 12:33:09',NULL,NULL,NULL,NULL,NULL),(243,370,'bakery','2015-01-08 12:36:23',NULL,NULL,NULL,NULL,NULL),(244,365,'goods','2015-01-08 12:42:03',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
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
