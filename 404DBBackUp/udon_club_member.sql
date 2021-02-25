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
-- Table structure for table `club_member`
--

DROP TABLE IF EXISTS `club_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_member` (
  `user_id` varchar(20) NOT NULL,
  `club_id` int NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`,`club_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_member`
--

LOCK TABLES `club_member` WRITE;
/*!40000 ALTER TABLE `club_member` DISABLE KEYS */;
INSERT INTO `club_member` VALUES ('aaaaaa',198,'0','2021-02-19 03:36:18'),('aaaaaa',204,'1','2021-02-18 15:08:31'),('aceace',194,'1','2021-02-17 21:41:43'),('aceace',195,'1','2021-02-17 21:51:37'),('aceace',196,'1','2021-02-17 21:51:51'),('aceace',197,'1','2021-02-17 21:52:08'),('aceace',198,'1','2021-02-17 21:52:30'),('aceace',203,'1','2021-02-18 14:50:49'),('bulgen',198,'0','2021-02-19 10:14:24'),('bulgen',205,'0','2021-02-19 10:18:02'),('gyuyong',198,'0','2021-02-18 14:47:28'),('lastday',194,'0','2021-02-19 09:59:41'),('lastday',195,'0','2021-02-19 10:01:12'),('rkdqhrms4',205,'1','2021-02-18 20:04:59'),('ssafy1',192,'0','2021-02-18 01:27:47'),('ssafy1',198,'0','2021-02-18 01:20:09'),('ssafy1',199,'1','2021-02-18 01:24:21'),('ssafy1',200,'1','2021-02-18 01:25:47'),('ssafy1',201,'1','2021-02-18 01:27:38'),('ssafy1',202,'1','2021-02-18 14:24:01'),('ssafy1111',192,'0','2021-02-18 14:20:21'),('ssafy1111',194,'0','2021-02-18 14:20:27'),('ssafy1111',196,'0','2021-02-18 14:20:31'),('ssafy1111',197,'0','2021-02-18 14:20:39'),('ssafy1111',198,'0','2021-02-18 09:42:24'),('ssafy1111',199,'0','2021-02-18 14:20:56'),('yong1',192,'1','2021-02-17 19:17:03'),('yong1',193,'1','2021-02-17 19:25:49'),('yong1',194,'0','2021-02-18 13:06:18'),('yong1',205,'0','2021-02-18 20:05:14'),('yong1',207,'1','2021-02-19 02:49:15'),('yong123',207,'0','2021-02-19 03:25:26'),('zenohero',196,'0','2021-02-19 02:11:27'),('zenohero',206,'1','2021-02-19 02:13:15');
/*!40000 ALTER TABLE `club_member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 16:03:56
