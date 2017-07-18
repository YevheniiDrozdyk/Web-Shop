-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: gretong
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` tinyint(2) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Acoustic'),(4,'Bass'),(2,'Classic'),(3,'Electro');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instrument`
--

DROP TABLE IF EXISTS `instrument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instrument` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `vendor_code` varchar(15) NOT NULL,
  `id_producer` tinyint(2) NOT NULL,
  `adapter` tinyint(1) NOT NULL,
  `price` int(6) NOT NULL,
  `id_category` tinyint(2) NOT NULL,
  `image` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_category` (`id_category`),
  KEY `id_producer` (`id_producer`),
  CONSTRAINT `instrument_fk1` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`),
  CONSTRAINT `instrument_fk2` FOREIGN KEY (`id_producer`) REFERENCES `producer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instrument`
--

LOCK TABLES `instrument` WRITE;
/*!40000 ALTER TABLE `instrument` DISABLE KEYS */;
INSERT INTO `instrument` VALUES (1,'AD810',2,0,2699,1,'AD810.jpg'),(2,'CGC390N',4,0,1699,2,'CGC390N.jpg'),(3,'CR230BK',3,1,10094,3,'CR230BK.jpg'),(4,'LTD66RW',1,1,37029,4,'LTD66RW.jpg'),(5,'AC200WC',2,1,12783,3,'AC200WC.jpg'),(6,'CGC360N',4,0,5076,2,'CGC360N.jpg'),(7,'NTX700BK',1,1,20262,2,'NTX700BK.jpg'),(8,'ESC-80',3,0,3325,2,'ESC-80.jpg'),(9,'TRBX174BL',1,1,7200,4,'TRBX174BL.jpg'),(10,'YDT-02',3,0,3099,1,'YDT-02.jpg');
/*!40000 ALTER TABLE `instrument` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` varchar(36) NOT NULL,
  `status` enum('FORMED','ACCEPTED','COMPLETED','CANCELED') NOT NULL,
  `detailing` varchar(50) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `ord_fk1_idx` (`id_user`),
  CONSTRAINT `ord_fk1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES ('11efa61b-cf98-4e6c-b6e5-57ec9d165d66','FORMED','Order was formed. Now waiting for payment.','2017-06-01 18:00:37',1),('3bda3a07-babe-4c43-83b1-4b40ffcca04d','FORMED','Order was formed. Now waiting for payment.','2017-06-01 18:27:39',1),('5eb9a92a-45fe-48fd-9bad-0ea118decaf2','FORMED','Order was formed. Now waiting for payment.','2017-06-01 18:26:20',1),('909975e6-be0b-4b2e-9e25-f360aa9dc154','FORMED','Order was formed. Now waiting for payment.','2017-06-01 18:01:53',1),('c10ba7ad-80be-4337-94ef-c81c6df391cc','FORMED','Order was formed. Now waiting for payment.','2017-06-01 18:22:22',1),('d2628402-0a07-4233-8b74-f5a4cc0c372f','FORMED','Order was formed. Now waiting for payment.','2017-06-01 18:24:36',1),('eb0ed4e9-c236-4239-bc72-4f3e51159cd4','ACCEPTED','Order was formed. Now waiting for payment.','2017-06-01 21:29:15',1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_instrument`
--

DROP TABLE IF EXISTS `order_instrument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_instrument` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_order` varchar(36) NOT NULL,
  `id_instrument` int(6) NOT NULL,
  `quantity` int(6) NOT NULL,
  `price` int(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ord_ins_fk2_idx` (`id_instrument`),
  KEY `ord_ins_fk1_idx` (`id_order`),
  CONSTRAINT `ord_ins_fk1` FOREIGN KEY (`id_order`) REFERENCES `order` (`id`),
  CONSTRAINT `ord_ins_fk2` FOREIGN KEY (`id_instrument`) REFERENCES `instrument` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_instrument`
--

LOCK TABLES `order_instrument` WRITE;
/*!40000 ALTER TABLE `order_instrument` DISABLE KEYS */;
INSERT INTO `order_instrument` VALUES (8,'11efa61b-cf98-4e6c-b6e5-57ec9d165d66',1,1,2699),(9,'11efa61b-cf98-4e6c-b6e5-57ec9d165d66',2,1,1699),(10,'909975e6-be0b-4b2e-9e25-f360aa9dc154',6,10,5076),(11,'909975e6-be0b-4b2e-9e25-f360aa9dc154',7,6,20262),(12,'c10ba7ad-80be-4337-94ef-c81c6df391cc',5,10,12783),(13,'c10ba7ad-80be-4337-94ef-c81c6df391cc',6,2,5076),(14,'c10ba7ad-80be-4337-94ef-c81c6df391cc',7,3,20262),(15,'c10ba7ad-80be-4337-94ef-c81c6df391cc',8,4,3325),(16,'d2628402-0a07-4233-8b74-f5a4cc0c372f',1,1,2699),(17,'5eb9a92a-45fe-48fd-9bad-0ea118decaf2',4,1,37029),(18,'3bda3a07-babe-4c43-83b1-4b40ffcca04d',1,1,2699),(19,'eb0ed4e9-c236-4239-bc72-4f3e51159cd4',2,25,1699);
/*!40000 ALTER TABLE `order_instrument` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producer`
--

DROP TABLE IF EXISTS `producer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producer` (
  `id` tinyint(2) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producer`
--

LOCK TABLES `producer` WRITE;
/*!40000 ALTER TABLE `producer` DISABLE KEYS */;
INSERT INTO `producer` VALUES (2,'Cort'),(3,'Fender'),(4,'Maxtone'),(1,'Yamaha');
/*!40000 ALTER TABLE `producer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `avatar` varchar(260) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Yevhenii','Drozdyk','Yevhenii_Drozdyk@epam.com','Password0','Yevhenii_Drozdyk@epam.com.jpg'),(2,'Johny','Depp','Johny.Depp@ukr.net','Password1','Johny.Depp@ukr.net.png');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-02  0:30:06
