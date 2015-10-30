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
-- Table structure for table `inventory_line`
--

DROP TABLE IF EXISTS `inventory_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_line` (
  `inventory_line_id` int(11) NOT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL,
  `isactive` char(1) NOT NULL DEFAULT 'Y',
  `updated` datetime DEFAULT NULL,
  `updatedby` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `createdby` datetime DEFAULT NULL,
  `inventory_id` int(11) NOT NULL,
  `storage_bin_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `booked_qty` int(11) DEFAULT NULL,
  `available_qty` int(11) DEFAULT NULL,
  `uom_id` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`inventory_line_id`),
  UNIQUE KEY `inventory_line_id_UNIQUE` (`inventory_line_id`),
  KEY `fk_inventory_line_merchant_id_idx` (`merchant_id`),
  KEY `fk_inventory_line_store_idx` (`store_id`),
  KEY `fk_inventory_line_inventory_idx` (`inventory_id`),
  KEY `fk_inventory_line_storage_bin_idx` (`storage_bin_id`),
  KEY `fk_inventory_line_product_idx` (`product_id`),
  KEY `fk_inventory_line_uom_idx` (`uom_id`),
  CONSTRAINT `fk_inventory_line_merchant` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`merchant_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_line_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_line_inventory` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`inventory_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_line_product` FOREIGN KEY (`product_id`) REFERENCES `product_details` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_line_storage_bin` FOREIGN KEY (`storage_bin_id`) REFERENCES `storage_bin` (`storage_bin_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_line_uom` FOREIGN KEY (`uom_id`) REFERENCES `product_uom` (`uom_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_line`
--

LOCK TABLES `inventory_line` WRITE;
/*!40000 ALTER TABLE `inventory_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory_line` ENABLE KEYS */;
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
