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
-- Table structure for table `storage_bin`
--

DROP TABLE IF EXISTS `storage_bin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storage_bin` (
  `storage_bin_id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) DEFAULT NULL,
  `store_id` int(11) NOT NULL,
  `isactive` varchar(45) NOT NULL DEFAULT 'Y',
  `created` timestamp NULL DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  `updatedby` varchar(45) CHARACTER SET big5 DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `xrow` int(11) NOT NULL,
  `yrow` int(11) NOT NULL,
  `zrow` int(11) NOT NULL,
  `warehouse_id` int(11) NOT NULL,
  PRIMARY KEY (`storage_bin_id`),
  UNIQUE KEY `storage_bin_id_UNIQUE` (`storage_bin_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_storage_bin_warehouse_idx` (`warehouse_id`),
  KEY `fk_storage_bin_store_idx` (`store_id`),
  KEY `fk_storage_bin_merchant_idx` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage_bin`
--

LOCK TABLES `storage_bin` WRITE;
/*!40000 ALTER TABLE `storage_bin` DISABLE KEYS */;
INSERT INTO `storage_bin` VALUES (2,1,1,'0','2015-01-07 18:30:00','0','2015-01-07 18:30:00','0','1',1,0,1,1),(3,1,1,'0','2015-01-07 18:30:00','0','2015-01-07 18:30:00','0','Mitosis-1-0-2',1,0,1,1);
/*!40000 ALTER TABLE `storage_bin` ENABLE KEYS */;
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
