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
-- Table structure for table `user_post_file`
--

DROP TABLE IF EXISTS `user_post_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_post_file` (
  `post_id` int NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `file_id` int NOT NULL,
  PRIMARY KEY (`post_id`,`user_id`,`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_post_file`
--

LOCK TABLES `user_post_file` WRITE;
/*!40000 ALTER TABLE `user_post_file` DISABLE KEYS */;
INSERT INTO `user_post_file` VALUES (81,'aceace',216),(81,'aceace',217),(82,'aceace',218),(83,'aceace',219),(83,'aceace',220),(85,'aceace',221),(88,'ssafy1',235),(89,'ssafy1',236),(96,'yong1',239),(97,'aceace',240),(99,'ssafy1',241),(100,'ssafy1',249),(101,'ssafy1',251),(101,'ssafy1',252),(101,'ssafy1',253),(101,'ssafy1',254),(101,'ssafy1',255),(102,'aceace',264),(103,'aceace',265),(104,'aceace',266),(105,'gyuyong',267),(106,'aceace',268),(107,'aceace',269),(107,'aceace',270),(108,'aceace',273),(109,'ssafy1111',276),(110,'aceace',279),(111,'aaaaaa',280),(112,'aaaaaa',283),(113,'aaaaaa',284),(114,'aaaaaa',285),(114,'aaaaaa',286),(115,'ssafy1',287),(115,'ssafy1',288),(115,'ssafy1',289),(116,'ssafy1',290),(117,'ssafy1111',291),(118,'ssafy1111',305),(126,'aeaeaeae',349),(127,'aeaeaeae',350),(129,'bulgen',364),(129,'bulgen',365);
/*!40000 ALTER TABLE `user_post_file` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 16:03:35
