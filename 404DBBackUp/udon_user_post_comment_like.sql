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
-- Table structure for table `user_post_comment_like`
--

DROP TABLE IF EXISTS `user_post_comment_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_post_comment_like` (
  `comment_id` int NOT NULL,
  `post_id` int NOT NULL,
  `user_id` varchar(20) NOT NULL,
  PRIMARY KEY (`comment_id`,`post_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_post_comment_like`
--

LOCK TABLES `user_post_comment_like` WRITE;
/*!40000 ALTER TABLE `user_post_comment_like` DISABLE KEYS */;
INSERT INTO `user_post_comment_like` VALUES (33,85,'ssafy1'),(33,85,'ssafy1111'),(36,87,'ssafy1111'),(37,86,'ssafy1'),(38,89,'ssafy1111'),(43,95,'ssafy1111'),(48,44,'aceace'),(59,75,'yong1'),(64,110,'aceace'),(91,110,'aceace'),(95,116,'aaaaaa'),(96,116,'aaaaaa'),(96,116,'ssafy1111'),(97,115,'aaaaaa'),(99,116,'aaaaaa'),(105,120,'yong1'),(107,120,'yong1'),(112,122,'yong1'),(116,127,'ssafy1'),(123,127,'ssafy1');
/*!40000 ALTER TABLE `user_post_comment_like` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 16:02:23
