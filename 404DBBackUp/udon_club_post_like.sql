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
-- Table structure for table `club_post_like`
--

DROP TABLE IF EXISTS `club_post_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_post_like` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `post_id` int NOT NULL,
  `club_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_post_like`
--

LOCK TABLES `club_post_like` WRITE;
/*!40000 ALTER TABLE `club_post_like` DISABLE KEYS */;
INSERT INTO `club_post_like` VALUES (92,'ssafy13',10,148),(98,'ssafy13',11,148),(118,'ssafy13',13,142),(121,'yong123',17,159),(134,'gyuyong',21,169),(135,'ssafy13',18,142),(136,'aceace',44,196),(137,'ssafy1',59,198),(138,'ssafy1',43,198),(139,'ssafy1',41,198),(140,'ssafy1111',62,199),(141,'ssafy1',65,202),(142,'ssafy1',66,199),(143,'ssafy1',64,199),(144,'ssafy1',63,199),(145,'ssafy1',62,199),(146,'ssafy1111',68,194),(147,'ssafy1111',71,197),(149,'aceace',59,198),(150,'aceace',73,203),(151,'yong1',70,192),(152,'aceace',69,196),(153,'rkdqhrms4',75,205),(154,'bulgen',77,198),(155,'bulgen',78,198),(156,'bulgen',80,198),(157,'bulgen',59,198),(158,'bulgen',81,205),(159,'aceace',68,194),(160,'aceace',45,194),(161,'aceace',67,194),(162,'aceace',71,197),(164,'ssafy1',80,198),(165,'ssafy1',78,198),(166,'aceace',80,198);
/*!40000 ALTER TABLE `club_post_like` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 16:02:26
