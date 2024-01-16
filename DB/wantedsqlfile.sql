-- MySQL dump 10.13  Distrib 8.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: challenge
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `contactpersoon`
--

DROP TABLE IF EXISTS `contactpersoon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contactpersoon` (
  `contactpersoon_id` int NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `tussenvoegsels` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `achternaam` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefoonnummer` int DEFAULT NULL,
  PRIMARY KEY (`contactpersoon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactpersoon`
--

LOCK TABLES `contactpersoon` WRITE;
/*!40000 ALTER TABLE `contactpersoon` DISABLE KEYS */;
INSERT INTO `contactpersoon` VALUES (2,'Christian','B','Vaughan',112386652),(3,'Rigel','U','Richard',782785457),(4,'Jena','G','Daugherty',487204583),(5,'Macey','P','Houston',201783515),(6,'Neil','B','Sandoval',925196176),(7,'Yardley','Y','Buck',251357399),(8,'Curran','X','Kennedy',622157938),(9,'Damian','R','Murphy',255331311),(10,'Bryar','S','Delgado',829857836);
/*!40000 ALTER TABLE `contactpersoon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klant`
--

DROP TABLE IF EXISTS `klant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `klant` (
  `klant_id` int NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `tussenvoegsels` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `achternaam` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `telefoonnummer` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `blockeduser` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`klant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klant`
--

LOCK TABLES `klant` WRITE;
/*!40000 ALTER TABLE `klant` DISABLE KEYS */;
INSERT INTO `klant` VALUES (1,'Zane','Z','Duncan','486','No'),(2,'Cullen','D','Payne','415','No'),(3,'Barclay','S','Mcknight','1','No'),(4,'Hashim','D','Foreman','909','No'),(5,'Murphy','C','Landry','1','Yes'),(6,'sasa','m','sasas','132313',NULL),(7,'sae','m','maas','132313',NULL),(8,'sae','m','maas','132313',NULL),(9,'sae','m','maas','132313',NULL),(10,'sahil ','r','ramlal','65446546',NULL);
/*!40000 ALTER TABLE `klant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pushedbtn`
--

DROP TABLE IF EXISTS `pushedbtn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pushedbtn` (
  `id` int NOT NULL AUTO_INCREMENT,
  `locatie` varchar(225) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sos-knop` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sos-knop` (`sos-knop`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pushedbtn`
--

LOCK TABLES `pushedbtn` WRITE;
/*!40000 ALTER TABLE `pushedbtn` DISABLE KEYS */;
INSERT INTO `pushedbtn` VALUES (1,'88.9267295232, 11.2880467968',1),(2,'0.0171252736, -27.8228430848',2),(3,'-74.4133917696, 152.2398022656',1),(4,'-3.8800212992, -158.239417344',3),(5,'-13.280231936, 160.995621376',1);
/*!40000 ALTER TABLE `pushedbtn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registreer`
--

DROP TABLE IF EXISTS `registreer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registreer` (
  `klant_id` int NOT NULL,
  `contactpersoon_id` int NOT NULL,
  PRIMARY KEY (`klant_id`,`contactpersoon_id`),
  KEY `contactpersoon_id` (`contactpersoon_id`),
  CONSTRAINT `registreer_ibfk_1` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`klant_id`),
  CONSTRAINT `registreer_ibfk_2` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`klant_id`),
  CONSTRAINT `registreer_ibfk_3` FOREIGN KEY (`contactpersoon_id`) REFERENCES `contactpersoon` (`contactpersoon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registreer`
--

LOCK TABLES `registreer` WRITE;
/*!40000 ALTER TABLE `registreer` DISABLE KEYS */;
INSERT INTO `registreer` VALUES (1,4),(1,6);
/*!40000 ALTER TABLE `registreer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sos-knop`
--

DROP TABLE IF EXISTS `sos-knop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sos-knop` (
  `sos-knop_id` int NOT NULL AUTO_INCREMENT,
  `numberrange` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `klant_id` int DEFAULT NULL,
  PRIMARY KEY (`sos-knop_id`),
  KEY `klant_id` (`klant_id`),
  CONSTRAINT `sos-knop_ibfk_1` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`klant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sos-knop`
--

LOCK TABLES `sos-knop` WRITE;
/*!40000 ALTER TABLE `sos-knop` DISABLE KEYS */;
INSERT INTO `sos-knop` VALUES (1,'54P7333745',2),(2,'15M3753844',2),(3,'58Y5758554',2),(4,'75S1082587',5),(5,'06G0136272',2);
/*!40000 ALTER TABLE `sos-knop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `wachtwoord` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `klant` int NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `klant` (`klant`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`klant`) REFERENCES `klant` (`klant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'imperdiet.ullamcorper@google.couk','ac',1),(7,'fusce@icloud.com','tellus',3),(8,'non.massa.non@icloud.couk','nec',5),(9,'cursus.integer@icloud.couk','ipsum.',4),(10,'sit@outlook.net','velit',3),(12,'cbcvbcvb','827ccb0eea8a706c4c34a16891f84e7b',3),(13,'geheim@ik.nl','nee',8),(14,'geheim@ik.nl','nee',9),(15,'80781@glr.nl','wert12345',10);
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

-- Dump completed on 2024-01-16 12:07:25
