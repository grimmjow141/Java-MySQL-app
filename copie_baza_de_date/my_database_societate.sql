-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: my_database
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `societate`
--

DROP TABLE IF EXISTS `societate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `societate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nume` varchar(30) NOT NULL,
  `C.U.I` int(11) NOT NULL,
  `Platitor_TVA` enum('DA','NU') NOT NULL,
  `Nr. ordine` varchar(20) NOT NULL,
  `E.U.I.D.` varchar(21) DEFAULT NULL,
  `C.A.E.N.` int(11) NOT NULL,
  `Nr. angajați` int(10) unsigned DEFAULT NULL,
  `Nume administrator` varchar(50) DEFAULT NULL,
  `Telefon` varchar(13) DEFAULT NULL,
  `Email` varchar(35) DEFAULT NULL,
  `Județ` varchar(45) NOT NULL,
  `Localitate` varchar(45) NOT NULL,
  `Adresă efectivă` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Nume` (`Nume`),
  UNIQUE KEY `Adresă efectivă_UNIQUE` (`Adresă efectivă`),
  UNIQUE KEY `C.U.I_UNIQUE` (`C.U.I`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `Nume administrator` (`Nume administrator`),
  UNIQUE KEY `Telefon` (`Telefon`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `societate`
--

LOCK TABLES `societate` WRITE;
/*!40000 ALTER TABLE `societate` DISABLE KEYS */;
INSERT INTO `societate` VALUES (1,'BLA BLA S.R.L.',1233213,'DA','33123mlkdsa',NULL,1245,NULL,NULL,NULL,NULL,'3123','d','23123ads'),(3,'MUIE MA-TA',1564321,'DA','fjeiriq3fj',NULL,934,NULL,NULL,NULL,NULL,'KDNd','dsad','231ewd');
/*!40000 ALTER TABLE `societate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-19 18:31:33
