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
-- Table structure for table `review_file`
--

DROP TABLE IF EXISTS `review_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_file` (
  `review_id` int DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `store_id` int DEFAULT NULL,
  `file_id` int NOT NULL,
  `rank` int DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_file`
--

LOCK TABLES `review_file` WRITE;
/*!40000 ALTER TABLE `review_file` DISABLE KEYS */;
INSERT INTO `review_file` VALUES (1307,'ssafy1111',26592210,190,1),(1308,'ssafy1111',12228541,192,1),(1309,'ssafy1111',11760115,193,1),(1310,'ssafy1111',12623799,194,1),(1313,'ssafy1111',20275330,197,1),(1314,'ssafy1111',20275330,198,1),(1320,'aceace',20275330,222,1),(1321,'aceace',26592210,223,1),(1324,'ssafy1',20275330,232,1),(1326,'ssafy1',25879426,234,1),(1328,'ssafy1111',20275330,237,1),(1329,'yong1',11951139,238,1),(1330,'yong1',20027351,256,1),(1330,'yong1',20027351,257,1),(1330,'yong1',20027351,258,1),(1331,'gyuyong',20392694,259,1),(1331,'gyuyong',20392694,260,1),(1331,'gyuyong',20392694,261,1),(1335,'ssafy1111',14065235,277,1),(1336,'ssafy1111',14065235,278,1),(1337,'ssafy1111',15996268,292,1),(1338,'ssafy1111',17073776,293,1),(1339,'ssafy1111',11985564,294,1),(1340,'ssafy1111',4308505,295,1),(1341,'ssafy1111',20423854,296,1),(1342,'ssafy1111',11780337,297,1),(1343,'ssafy1111',14919056,298,1),(1348,'ssafy1111',11951139,299,1),(1351,'ssafy1111',12455273,302,1),(1352,'ssafy1111',11859259,303,1),(1363,'rkdqhrms4',17250505,306,1),(1364,'yong1',17250505,307,1),(1365,'jeh123',12669939,309,1),(1366,'yong1',12665058,310,1),(1367,'yong1',15959958,311,1),(1367,'yong1',15959958,312,1),(1368,'yong1',16751159,313,1),(1369,'yong1',20523919,314,1),(1370,'yong1',20009822,315,1),(1371,'yong1',17026179,316,1),(1372,'yong1',20018545,317,1),(1375,'yong1',11965523,318,1),(1376,'yong1',28523840,319,1),(1378,'yong1',12074708,320,1),(1379,'yong1',16118917,321,1),(1380,'yong1',20481995,322,1),(1381,'yong1',12049213,323,1),(1382,'yong1',15497301,324,1),(1383,'yong1',16032725,325,1),(1384,'yong1',20009006,326,1),(1387,'yong1',16951204,327,1),(1388,'yong1',26054601,328,1),(1389,'yong1',15763112,329,1),(1390,'yong1',19981443,330,1),(1392,'yong1',12341634,331,1),(1394,'yong1',28486152,332,1),(1395,'yong1',16730041,333,1),(1396,'aeaeaeae',20027351,334,1),(1397,'aeaeaeae',11965523,335,1),(1398,'yong1',12065851,336,1),(1399,'aeaeaeae',15497301,337,1),(1400,'aeaeaeae',9010755,338,1),(1401,'yong1',20650922,339,1),(1403,'aeaeaeae',20481995,341,1),(1404,'yong1',25797683,342,1),(1405,'aeaeaeae',15959958,343,1),(1406,'yong1',20609204,344,1),(1410,'aeaeaeae',12049213,345,1),(1412,'aeaeaeae',9174833,346,1),(1414,'aeaeaeae',16014600,347,1),(1418,'yong1',20398065,351,1),(1419,'yong1',22712084,352,1),(1420,'yong1',22576529,353,1),(1421,'bulgen',17073776,356,1),(1422,'bulgen',25879426,357,1),(1425,'bulgen',11760115,359,1),(1431,'ssafy1',24983206,366,1);
/*!40000 ALTER TABLE `review_file` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 16:02:41