-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: udon
-- ------------------------------------------------------
-- Server version	8.0.23-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `club_post_file`
--

DROP TABLE IF EXISTS `club_post_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_post_file` (
  `post_id` int NOT NULL,
  `club_id` int NOT NULL,
  `file_id` int NOT NULL,
  PRIMARY KEY (`post_id`,`club_id`,`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_post_file`
--

LOCK TABLES `club_post_file` WRITE;
/*!40000 ALTER TABLE `club_post_file` DISABLE KEYS */;
INSERT INTO `club_post_file` VALUES (6,124,31),(6,124,32),(8,139,38),(8,139,39),(13,142,80),(14,159,116),(15,160,123),(15,160,124),(15,160,125),(16,159,126),(16,159,127),(17,159,128),(17,159,129),(19,169,131),(19,169,132),(25,170,136),(41,198,209),(41,198,210),(44,196,211),(44,196,212),(45,194,213),(47,0,214),(47,0,215),(59,198,224),(59,198,225),(60,192,230),(62,199,242),(63,199,244),(64,199,245),(65,202,246),(65,202,247),(66,199,248),(67,194,250),(68,194,262),(69,196,263),(70,192,271),(71,197,272),(73,203,275),(74,204,282),(78,198,355),(79,195,358),(80,198,360),(80,198,361),(81,205,362),(81,205,363);
/*!40000 ALTER TABLE `club_post_file` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 16:02:31