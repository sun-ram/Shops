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
-- Table structure for table `product_uom`
--

DROP TABLE IF EXISTS `product_uom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_uom` (
  `uom_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `abbreviation` varchar(25) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `active` varchar(45) DEFAULT NULL,
  `created_on` varchar(45) DEFAULT NULL,
  `updated_on` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uom_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_uom`
--

LOCK TABLES `product_uom` WRITE;
/*!40000 ALTER TABLE `product_uom` DISABLE KEYS */;
INSERT INTO `product_uom` VALUES (12,147,'kg','Product of Malaysia / Indonesia.Fresh Lady Finger pre-packed for your convenience. With zero saturated fats or cholesterol, lady fingers are extremely low in calories and dense with nutrients. Apart from the usual vitamins and minerals, lady fingers also have one of the highest content of phyto nutrients and antioxidants. Consumption of lady fingers are also said to improve diabetic conditions!',NULL,NULL,NULL,NULL,NULL),(13,148,'gm','Product of Malaysia.Wild Rocket is one of the nutritious green-leafy vegetable of Mediterranean origin. Young tender rocket leaves are a great addition to salads, sandwiches, and burgers. Fresh greens can be used in soups, stews, juices, and cooked as a vegetable. Prepare Italian style arugula pasta with added goat cheese!',NULL,NULL,NULL,NULL,NULL),(15,150,'kg','Product of Malaysia.Basil is a wonderfully versatile herb, used frequently in cuisines throughout the world. Green basil has large, oval-shaped leaves that are a shiny green in colour. It has a distinctive, slightly sweet smell and taste, and is well-known for its use in Italian cuisine.',NULL,NULL,NULL,NULL,NULL),(16,151,'kg','Product of Malaysia.Fresh Romaine Lettuce individually packed to preserve freshness and for your convenience. 2 cups of romaine lettuce (approx 100g) will provide more than your daily requirement of vitamin K and half of your daily vitamin A requirement. Its cool crunchy texture makes it the perfect green for salad days!',NULL,NULL,NULL,NULL,NULL),(17,153,'kg','Feresh',NULL,NULL,NULL,NULL,NULL),(19,155,'ml','Clean & clean',NULL,NULL,NULL,NULL,NULL),(20,156,'gm','Feel the god',NULL,NULL,NULL,NULL,NULL),(21,162,'kg','G1',NULL,NULL,NULL,NULL,NULL),(22,163,'ml','adadad',NULL,NULL,NULL,NULL,NULL),(34,176,'kg','Product of Malaysia / Vietnam / Thailand / China.Fresh Tomatoes pre-packed for your convenience. Tomatoes are',NULL,NULL,NULL,NULL,NULL),(40,191,'gm','Product of China.Fresh White Shimmer Mushrooms pre-packed in individual trays and sealed for your convenience.',NULL,NULL,NULL,NULL,NULL),(43,194,'gm','Baked from high protein flour and enriched with vitamins and minerals. Especially popular with bigger families, it\'s high in vitamins B1, B2, B3, Calcium and Iron, and has no trans fat.',NULL,NULL,NULL,NULL,NULL),(44,195,'gm','This bread is high in fibre and yet soft and delicious.\n\nMade with 50% wholemeal flour, high in vitamins B1, B2, B3, Calcium and Iron, and no cholesterol and trans fat.',NULL,NULL,NULL,NULL,NULL),(45,196,'gm','Goodness of milk',NULL,NULL,NULL,NULL,NULL),(46,197,'gm','Good for Health',NULL,NULL,NULL,NULL,NULL),(47,198,'kg','Good for health',NULL,NULL,NULL,NULL,NULL),(48,199,'kg','Seed less and sweet to eat',NULL,NULL,NULL,NULL,NULL),(49,200,'gm','sweet as sugar',NULL,NULL,NULL,NULL,NULL),(50,201,'gm','sweet as sugar',NULL,NULL,NULL,NULL,NULL),(51,202,'gm','Good for health',NULL,NULL,NULL,NULL,NULL),(52,203,'kg','Product of Australia / Europe / South Africa / USA.A blend of sweet and tart, these green berries are lushiously delightful',NULL,NULL,NULL,NULL,NULL),(53,204,'kg','Cherry grapes with sweat taste',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `product_uom` ENABLE KEYS */;
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
