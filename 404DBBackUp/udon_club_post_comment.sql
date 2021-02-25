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
-- Table structure for table `club_post_comment`
--

DROP TABLE IF EXISTS `club_post_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_post_comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `club_id` int NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `comm_content` varchar(500) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `comm_like_count` int DEFAULT '0',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_post_comment`
--

LOCK TABLES `club_post_comment` WRITE;
/*!40000 ALTER TABLE `club_post_comment` DISABLE KEYS */;
INSERT INTO `club_post_comment` VALUES (5,8,139,'ssafy','좋은글 감사합니다','2021-01-29 08:10:37',0),(6,8,139,'ssafy','좋은글 감사합니다','2021-01-29 08:10:46',0),(7,13,142,'ssafy13','댓글1','2021-02-08 14:21:57',1),(8,13,142,'ssafy13','반가워요~~~ 좋은 하루 보내세요!','2021-02-08 14:52:27',0),(9,13,142,'ssafy13','새로운 댓글~~~','2021-02-09 00:26:39',1),(10,13,142,'ssafy13','새로운 댓글이에요','2021-02-09 00:39:04',1),(11,12,142,'ssafy13','안녕','2021-02-09 02:10:22',1),(12,13,142,'ssafy13','댓글!','2021-02-09 02:11:23',0),(13,12,142,'ssafy13','하위','2021-02-09 02:12:26',0),(14,12,142,'ssafy13','하이용','2021-02-09 02:12:48',1),(15,12,142,'ssafy13','이런','2021-02-09 02:12:53',1),(16,13,142,'ssafy13','댓글~~~','2021-02-09 02:13:28',1),(17,13,142,'ssafy13','댓글~','2021-02-09 02:13:43',1),(18,13,142,'ssafy13','댓글~!!!','2021-02-09 02:13:46',1),(19,13,142,'ssafy13','댓글~!!!새로운 댓글~~~','2021-02-09 02:13:53',1),(20,13,142,'ssafy13','댓글~!!!새로운 댓글~~~ㅋㅋ','2021-02-09 02:14:01',1),(21,13,142,'ssafy13','댓글~!!!새로운 댓글~~~','2021-02-09 02:14:42',1),(22,13,142,'ssafy13','댓글~!!!새로운 댓글~~~','2021-02-09 02:14:47',1),(23,13,142,'ssafy13','댓글~!!!새로운 댓글~~~','2021-02-09 02:15:54',0),(24,13,142,'ssafy13','댓글~!!!새로운 댓글!!','2021-02-09 02:16:00',0),(25,13,142,'ssafy13','각오는....!','2021-02-09 04:52:23',1),(26,13,142,'ssafy13','각오는....!','2021-02-09 04:52:24',1),(27,13,142,'ssafy13','각오는....!','2021-02-09 04:52:25',1),(28,13,142,'ssafy13','각오는....! 이렇게....','2021-02-09 04:52:32',0),(29,13,142,'ssafy13','디자인...','2021-02-09 04:52:46',1),(30,13,142,'ssafy13','insta..... 우동만의 feed............... 홈페이지 완성된 느낌...','2021-02-09 04:53:29',1),(31,13,142,'ssafy13','댓~글','2021-02-09 05:07:44',1),(32,14,159,'yong123','안녕하세요~~','2021-02-09 06:42:15',0),(33,13,142,'ssafy13','새 댓글','2021-02-09 07:48:37',0),(34,13,142,'ssafy13','댓글','2021-02-09 08:40:05',1),(35,13,142,'ssafy13','댓글','2021-02-09 08:40:17',0),(36,13,142,'ssafy13','','2021-02-10 00:52:28',0),(37,14,159,'yong123','하이 용욱!','2021-02-10 01:35:41',0),(38,14,159,'yong123','하이 용욱!','2021-02-10 01:35:43',0),(39,14,159,'yong123','하이 용욱!','2021-02-10 01:36:04',0),(40,14,159,'yong123','안녕','2021-02-10 01:36:21',0),(41,17,159,'yong123','ㅎㅇㅎㅇㅎㅇ','2021-02-10 01:56:37',0),(42,23,170,'aceace','참고로 신림역 4출 앞에서 한 블럭 걸어가도 팔아요!','2021-02-10 06:40:03',0),(43,21,169,'gyuyong','댓글!!','2021-02-15 06:43:38',0),(44,43,198,'aceace','앗싸 낼이면 끝난다!','2021-02-17 23:57:19',0),(45,43,198,'aceace','근데 바로 또 다음 프로젝트가 있어...','2021-02-17 23:58:31',0),(46,43,198,'aceace','오늘 면담에서 프로님이 싸피의 유일한 단점이 너무 빡시다는거라더라','2021-02-17 23:58:58',0),(47,43,198,'aceace','나도 그렇게 생각해!!! 이게 도대체 뭐야!!!','2021-02-17 23:59:18',0),(48,44,196,'aceace','이거 들어봤는데 곡은 좀 그저그렇더라구요..','2021-02-18 00:01:46',0),(49,44,196,'aceace','하지만 모차르트의 새로운 작품이 초연한다는 것에 큰 의의가 있는 것 같아요!','2021-02-18 00:02:04',0),(50,65,202,'ssafy1','거기 매일 지나가는데 회사가 늦게 마쳐서 항상 어두워요 아쉽네요 ㅠㅠㅠ','2021-02-18 14:25:58',0),(51,66,199,'ssafy1','ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ','2021-02-18 14:30:02',0),(52,66,199,'ssafy1','피카츄 비율 8등신정도 될듯','2021-02-18 14:30:17',0),(53,44,196,'ssafy1111','멋있네요 자랑스러워요!!','2021-02-18 14:38:10',0),(54,59,198,'gyuyong','할로윈파티 아니고 할로위파티...??!?!?','2021-02-18 14:47:41',0),(55,71,197,'ssafy1111','붕어빵은 팥이지','2021-02-18 14:49:44',0),(56,70,192,'yong1','해바라기가 참이쁘네요!','2021-02-18 18:01:28',0),(57,70,192,'yong1','여러분들은 어떤가요!','2021-02-18 18:01:40',0),(58,69,196,'aceace','너무 재밌어요 이거!!','2021-02-18 18:06:54',0),(59,75,205,'rkdqhrms4','넵 환영합니다 !!만약 현역이시면 추방하겠습니다..','2021-02-18 20:06:48',0),(60,75,205,'yong1','헉스헉스','2021-02-18 20:07:39',0),(61,60,192,'yong1','소바님은 해보셨나요?? ','2021-02-19 02:04:17',0),(62,78,198,'bulgen','ㅋㅋㅋㅋㅋ루팡님 수고하셨어요','2021-02-19 10:13:32',0),(63,77,198,'bulgen','싸피 4기 서울4반 모두 화이팅!!!','2021-02-19 10:13:48',0),(64,59,198,'bulgen','ㅋㅋㅋㅋㅋㅋㅋ할로위파티 뭐죠...','2021-02-19 10:17:20',0),(65,75,205,'bulgen','ㅋㅋㅋㅋㅋㅋㅋㅋㅋ','2021-02-19 10:18:12',0),(66,68,194,'aceace','ㅋㅋㅋㅋㅋ20대 중반부터는 커피 달고 살아야하는거 같아요..','2021-02-19 10:37:38',0),(67,71,197,'aceace','붕어빵은 슈크림이지','2021-02-19 10:38:29',0),(68,80,198,'ssafy1','싸 싸늘하다 피 피곤하다','2021-02-19 11:54:24',0),(69,78,198,'ssafy1','우동 화이팅~~!!','2021-02-19 11:54:42',0),(70,43,198,'ssafy1','화이팅!','2021-02-19 11:55:03',0),(71,80,198,'aceace','아싸아사','2021-02-19 13:57:16',0);
/*!40000 ALTER TABLE `club_post_comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 16:02:21
