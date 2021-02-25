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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(20) NOT NULL,
  `cer_code` varchar(20) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(40) NOT NULL,
  `is_manager` tinyint(1) DEFAULT '0',
  `user_state` int DEFAULT NULL,
  `nickname` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('aaaaaa',NULL,'ed02457b5c41d964dbd2f2a609d63fe1bb7528dbe55e1abf5b52c249cd735797','2021-02-18 14:57:55','bulgen@naver.co',0,NULL,'월급루팡'),('aceace',NULL,'f5e224ddeaa8f31a8b688f6697ab2041d382b00edd38d2353af4aaac2f80371a','2021-02-04 08:43:41','bulgen@never.com',0,NULL,'파송송'),('admin',NULL,'admin','2021-02-03 14:15:55','test@test.com',1,NULL,'이송영짱짱'),('aeaeaeae',NULL,'96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e','2021-02-18 03:14:02','whddnjs18212@naver.com',0,NULL,'나무늘보'),('bulgen',NULL,'33b423ed57f243c585edfa9e63a8081a2add41a1428ccdf963e93ed5345c760a','2021-02-19 09:50:29','bulgen@naver.com',0,NULL,'건전한우니쨩'),('dooly7183',NULL,'6da4b98bfb06b104ff34b6e0806df208f78a16cec2a001c0964be9fc4c18c42b','2021-02-19 13:56:09','dooly7183@naver.com',0,NULL,'프젝수고하셨어요'),('ekfn15',NULL,'b2d5f9f4f169261b27f7c457f4010af72730c1126a04115f4a951ad42ad8bd63','2021-02-01 06:21:00','dssdsdsdsd',1,0,'눈누난ㄴ'),('gyuyong',NULL,'1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','2021-02-10 01:03:46','gyuyong290@gmail.com',0,NULL,'구옹'),('jaden20',NULL,'bed24b9828b686eb4cb9a4e64d0e672ff33efcf54fe67a97b1d3f25689bc609c','2021-02-18 12:18:27','jaden20.dev@gmail.com',0,NULL,'jaden'),('jeh123',NULL,'030f5271d7e2e336c9eba2a510b04fca2e1f4eb40bfbfd14918d9f3be75457ee','2021-02-18 21:26:01','eunhae2351@naver.com',0,NULL,'나무'),('lastday',NULL,'1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','2021-02-19 09:54:38','honor159@hanmail.net',0,NULL,'개발자의숙명'),('rkdqhrms4',NULL,'b2a26879b7908ec1a1f859efa0c6ec9062dab9ffee93feecce55d4614fc3ffcd','2021-02-18 19:55:34','rkdqhrms4@naver.com',0,NULL,'이근대위'),('ssafy','1011111111','fd38b40ab0f4c05916436d8fe764acc037dfa9ee071be626d19d5a2e8751efac','2021-01-22 07:23:51','ada',0,NULL,'ssafy첫번쨰!'),('ssafy0123',NULL,'1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','2021-02-05 04:46:13','aasdsada',0,NULL,'ssafy0123'),('ssafy1',NULL,'7a51bc7033e0a8d9ef65b1c9c985d4cffd5da3ed4027cc41bb545e24b1fd8a8b','2021-02-18 01:19:42','bulgen@never.con',0,NULL,'우동소바'),('ssafy10',NULL,'1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','2021-01-31 10:02:52','asdasd',0,NULL,'honor159@hanmail.net'),('ssafy1111',NULL,'9fca3d8099135c3158a4267aaa14090705a08b527213dbc33130a94cfef241b7','2021-02-03 13:48:22','whddnjs1821@gmail.com',0,NULL,'으응우웅'),('ssafy13',NULL,'1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','2021-02-03 15:00:15','aasda',1,NULL,'규용용용용'),('ssafy2','1554545','ssafy2','2021-01-22 07:24:29','adad',0,NULL,'ssafy2'),('ssafy3',NULL,'ssafy3','2021-01-25 05:25:26','asd',0,1,'ssafy3'),('ssafy4',NULL,'c2a0ecf4cfeebcda0db5b3ba816de6b2975335efd5425447925a31ac65587254','2021-01-25 19:50:30','asd',0,NULL,'용'),('ssafy5',NULL,'asd','2021-01-27 02:05:39','asdasd',0,NULL,'ssafy5'),('ssafy6',NULL,'asd','2021-01-28 06:28:15','asdasd',0,1,'ssafy6'),('test1',NULL,'8a87f0f11bca428c86f918632a636e38d40018cf0f5e71ab95c5d86fd927906d','2021-02-04 06:50:02','test',0,NULL,'test1'),('yong1',NULL,'1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','2021-02-16 02:38:09','asasd',0,NULL,'용쓰'),('yong123',NULL,'1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','2021-02-09 01:40:36','aaaa',0,NULL,'yong123'),('yong1234',NULL,'1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','2021-02-13 18:50:28','rkdfurdyddnr@naver.com',0,NULL,'yong1234'),('zenohero',NULL,'1a1396f04bf1b84cabfb1fbbfaaccf820a4b677deacef15a8d0fdc5fd5cb9a8d','2021-02-19 02:06:22','zenohero@naver.com',0,NULL,'그냥접어');
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

-- Dump completed on 2021-02-25 16:03:25
