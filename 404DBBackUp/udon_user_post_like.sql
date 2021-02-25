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
-- Table structure for table `user_post_like`
--

DROP TABLE IF EXISTS `user_post_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_post_like` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `post_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_post_like`
--

LOCK TABLES `user_post_like` WRITE;
/*!40000 ALTER TABLE `user_post_like` DISABLE KEYS */;
INSERT INTO `user_post_like` VALUES (82,'aceace',81),(83,'aceace',83),(84,'aceace',82),(85,'ssafy1',82),(86,'ssafy1',85),(87,'ssafy1',83),(88,'ssafy1',86),(89,'ssafy1',87),(90,'ssafy1',88),(91,'ssafy1',89),(92,'ssafy1111',89),(93,'ssafy1111',87),(95,'ssafy1111',86),(96,'ssafy1111',85),(97,'ssafy1',92),(98,'aceace',95),(99,'aceace',94),(100,'aceace',93),(101,'ssafy1111',95),(102,'ssafy1111',94),(103,'ssafy1111',93),(105,'ssafy1111',101),(106,'ssafy1111',102),(107,'aceace',106),(108,'aceace',107),(109,'aceace',105),(110,'aceace',104),(111,'gyuyong',107),(112,'ssafy1111',109),(113,'aceace',109),(114,'aaaaaa',110),(115,'aaaaaa',108),(116,'aaaaaa',107),(117,'aaaaaa',106),(118,'aaaaaa',105),(119,'aaaaaa',104),(120,'aaaaaa',103),(121,'aaaaaa',102),(123,'aaaaaa',101),(124,'aaaaaa',100),(125,'aaaaaa',97),(126,'aaaaaa',95),(127,'aaaaaa',94),(128,'aaaaaa',93),(129,'aaaaaa',92),(131,'aaaaaa',112),(132,'aaaaaa',111),(134,'ssafy1',114),(135,'ssafy1',113),(136,'ssafy1',112),(137,'ssafy1',111),(138,'ssafy1',110),(139,'ssafy1',105),(140,'ssafy1',101),(141,'ssafy1',100),(142,'aceace',116),(143,'aceace',115),(144,'aceace',114),(145,'aceace',113),(146,'aaaaaa',116),(147,'aaaaaa',115),(148,'aaaaaa',109),(149,'aceace',110),(150,'ssafy1111',120),(152,'yong1',120),(153,'ssafy1111',118),(154,'yong1',121),(155,'zenohero',121),(156,'yong1',124),(157,'aeaeaeae',127),(158,'aceace',127),(159,'ssafy1111',127),(160,'bulgen',127),(161,'bulgen',114),(162,'bulgen',109),(163,'bulgen',110),(164,'bulgen',129),(165,'ssafy1',129),(166,'ssafy1',127),(167,'ssafy1',126),(168,'ssafy1111',129),(169,'aceace',126),(170,'aceace',130);
/*!40000 ALTER TABLE `user_post_like` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 16:03:46
