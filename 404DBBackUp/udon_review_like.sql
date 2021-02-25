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
-- Table structure for table `review_like`
--

DROP TABLE IF EXISTS `review_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_like` (
  `review_id` int NOT NULL,
  `store_id` int NOT NULL,
  `user_id` varchar(20) NOT NULL,
  PRIMARY KEY (`review_id`,`store_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_like`
--

LOCK TABLES `review_like` WRITE;
/*!40000 ALTER TABLE `review_like` DISABLE KEYS */;
INSERT INTO `review_like` VALUES (1243,8697484,'ssafy1111'),(1247,20724943,'ssafy1111'),(1249,15612166,'ssafy1111'),(1254,15612166,'ssafy1111'),(1262,24719551,'aceace'),(1266,23144968,'aceace'),(1271,20027351,'ssafy1111'),(1275,4308505,'aceace'),(1276,4308505,'aceace'),(1304,22047871,'ssafy1'),(1305,12446092,'ssafy1111'),(1307,26592210,'ssafy1'),(1313,20275330,'aceace'),(1313,20275330,'aeaeaeae'),(1313,20275330,'ssafy1'),(1314,20275330,'aceace'),(1320,20275330,'ssafy1'),(1320,20275330,'ssafy1111'),(1321,26592210,'aceace'),(1321,26592210,'ssafy1'),(1321,26592210,'ssafy1111'),(1325,22047871,'ssafy1111'),(1326,25879426,'bulgen'),(1328,20275330,'aceace'),(1332,11951139,'aceace'),(1334,25879426,'bulgen'),(1337,15996268,'ssafy1111'),(1338,17073776,'ssafy1111'),(1340,4308505,'aceace'),(1340,4308505,'ssafy1111'),(1344,22047871,'ssafy1111'),(1348,11951139,'ssafy1111'),(1351,12455273,'ssafy1111'),(1359,20275330,'aceace'),(1359,20275330,'ssafy1'),(1361,11951139,'aceace'),(1363,17250505,'yong1'),(1364,17250505,'rkdqhrms4'),(1365,12669939,'jeh123'),(1365,12669939,'yong1'),(1366,12665058,'aceace'),(1396,20027351,'aeaeaeae'),(1397,11965523,'aeaeaeae'),(1399,15497301,'aeaeaeae'),(1400,9010755,'aeaeaeae'),(1403,20481995,'aeaeaeae'),(1405,15959958,'aeaeaeae'),(1410,12049213,'aeaeaeae'),(1414,16014600,'aeaeaeae'),(1417,11780337,'aeaeaeae'),(1424,15959958,'aceace'),(1428,24889472,'aaaaaa'),(1428,24889472,'aceace'),(1429,24983206,'aaaaaa'),(1430,24889472,'aaaaaa'),(1430,24889472,'aceace');
/*!40000 ALTER TABLE `review_like` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 16:02:46
