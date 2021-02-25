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
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club` (
  `club_id` int NOT NULL AUTO_INCREMENT,
  `club_name` varchar(20) NOT NULL,
  `member_number` int DEFAULT NULL,
  `club_content` varchar(500) DEFAULT NULL,
  `is_open` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `area_code` varchar(20) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `file_id` int NOT NULL,
  PRIMARY KEY (`club_id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (192,'해바라기',4,'해바라기 사랑하는 사람들 이리로~~~~~~~~~~~~',1,'2021-02-17 19:17:02','1168065000','yong1',191),(194,'카페인중독',5,'역삼동 커피 좋아하시는 분들 정보공유해요 ㅎㅎㅎ',1,'2021-02-17 21:41:43','1168065000','aceace',204),(195,'3통자전거',3,'역삼2동 3통 자전거 동호회',0,'2021-02-17 21:51:36','1168065000','aceace',205),(196,'피아노',4,'피아노 좋아하는 사람~~!',1,'2021-02-17 21:51:51','1168065000','aceace',206),(197,'붕어빵',3,'함께 역삼동 붕어빵 지도 만들어봐요',1,'2021-02-17 21:52:08','1168065000','aceace',207),(198,'SSAFY',7,'SSAFY 출신들을 위한 정보 공유 공간',1,'2021-02-17 21:52:30','1168065000','aceace',208),(199,'헬스',3,'역삼동 헬창이들 모여모여라~~!!',1,'2021-02-18 01:24:21','1168065000','ssafy1',227),(200,'역삼주민회',2,'역삼동 주민회를 위한 그룹입니다.',0,'2021-02-18 01:25:47','1168065000','ssafy1',228),(201,'역삼초엄마',2,'역삼초등학교 학부모들의 모임! 모두모두 모여랏',0,'2021-02-18 01:27:38','1168065000','ssafy1',229),(202,'노을',2,'역삼동 노을사진 공유해봐요!',1,'2021-02-18 14:24:01','1168065000','ssafy1',243),(203,'MBTI',2,'MBTI 모르는 아싸들 없지?',1,'2021-02-18 14:50:49','1168065000','aceace',274),(204,'4반4팀',2,'싸피5기 서울4반 4팀',0,'2021-02-18 15:08:31','1168065000','aaaaaa',281),(205,'MUSAT',4,'현역이 아닌 대한민국 장병들만 신청 부탁드립니다! ',1,'2021-02-18 20:04:59','1168065000','rkdqhrms4',308),(206,'등산',2,'같이 등산해여~ 계양산 조아여',1,'2021-02-19 02:13:15','1168065000','zenohero',348),(207,'닭강정',3,'닭강정 좋아하는 사람들의 모임',0,'2021-02-19 02:49:15','1168065000','yong1',354);
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 16:03:10
