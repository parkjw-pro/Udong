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
-- Table structure for table `club_post`
--

DROP TABLE IF EXISTS `club_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_post` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `club_id` int NOT NULL,
  `post_content` varchar(500) DEFAULT NULL,
  `post_views` int DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_open` varchar(10) DEFAULT '1',
  `post_like_count` int DEFAULT '0',
  `post_tag` varchar(100) DEFAULT NULL,
  `is_updated` tinyint(1) DEFAULT '0',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `post_comment_count` int DEFAULT '0',
  `user_id` varchar(20) NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FK_club_TO_club_post_1` (`club_id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_post`
--

LOCK TABLES `club_post` WRITE;
/*!40000 ALTER TABLE `club_post` DISABLE KEYS */;
INSERT INTO `club_post` VALUES (41,198,'요새 개발중인 플랫폼 aka. 우동!',57,'2021-02-17 21:53:44','badge1',1,'',0,'2021-02-17 21:53:44',0,'aceace'),(43,198,'개발 너무 힘들다... ㅠㅠㅠ 모두 화이팅!!',60,'2021-02-17 23:57:07','badge1',1,'',0,'2021-02-17 23:57:07',5,'aceace'),(44,196,'이번에 우리나라 피아니스트 조성진이 248년 만에 모차르트 곡으로 판명난 소나타 곡을 초연했대요!!',25,'2021-02-18 00:01:13','badge1',1,'',0,'2021-02-18 00:01:13',3,'aceace'),(45,194,'저 원래 콜라만 마셔도 못자는데,,, 요새 개발 때문에 매일같이 철야하느라 진짜 커피를 달고살게 되었네요..',71,'2021-02-18 00:03:43','badge1',1,'',0,'2021-02-18 00:03:43',0,'aceace'),(47,0,'오늘 5시쯤 찍은 하늘 사진 너무 예뻐!!\n#하늘 #노을 #갬성',0,'2021-02-18 00:08:50','badge1',0,'#하늘#노을#갬성',0,'2021-02-18 00:08:50',0,'aceace'),(59,198,'싸피 할로위파티도 했었는데.. 오프라인 가고싶다 ㅠㅠㅠ #싸피 #할로위파티',52,'2021-02-18 01:21:01','badge1',3,'#싸피#할로위파티',0,'2021-02-18 01:21:01',2,'ssafy1'),(60,192,'여러분 해바라기 같은 사랑 해보신적 있나요?',40,'2021-02-18 01:28:31','badge1',0,'',0,'2021-02-18 01:28:31',1,'ssafy1'),(61,192,'안녕하세요 용쓰입니다. 잘부탁드립니다 감사합니다.',36,'2021-02-18 13:56:07','badge1',0,'',0,'2021-02-18 13:56:07',0,'yong1'),(62,199,'다들 득근득근하세요',19,'2021-02-18 14:22:51','badge3',2,'',0,'2021-02-18 14:22:51',0,'ssafy1111'),(63,199,'근손실 방지위원회에서 나왔습니다.',18,'2021-02-18 14:24:11','badge3',1,'',0,'2021-02-18 14:24:11',0,'ssafy1111'),(64,199,'그룹장님 여기서 뭐하십니까',17,'2021-02-18 14:25:17','badge3',1,'',0,'2021-02-18 14:25:17',0,'ssafy1111'),(65,202,'멀티캠퍼스 앞에서 선릉역 방면으로 오후 5-6쯤 하늘 보면 진짜 예뻐요 ㅎㅎㅎ',3,'2021-02-18 14:25:25','badge1',1,'',0,'2021-02-18 14:25:25',1,'ssafy1'),(66,199,'포켓몬 최종 진화',16,'2021-02-18 14:27:07','badge3',1,'',0,'2021-02-18 14:27:07',2,'ssafy1111'),(67,194,'그 시절 카페인중독',53,'2021-02-18 14:28:34','badge3',1,'',0,'2021-02-18 14:28:34',0,'ssafy1111'),(68,194,'커피 없인 살 수 없는 몸이 되어버렸어.,.',49,'2021-02-18 14:33:39','badge3',2,'',0,'2021-02-18 14:33:39',1,'ssafy1111'),(69,196,'이 영화 보신분??',17,'2021-02-18 14:37:49','badge3',1,'',0,'2021-02-18 14:37:49',1,'ssafy1111'),(70,192,'태양을 피하고 싶었어',19,'2021-02-18 14:46:49','badge3',1,'',0,'2021-02-18 14:46:49',2,'ssafy1111'),(71,197,'치즈 붕어빵  호 vs 불호',9,'2021-02-18 14:49:30','badge3',2,'',0,'2021-02-18 14:49:30',2,'ssafy1111'),(72,203,'자신의 mbti유형 남겨봐요!',12,'2021-02-18 14:51:09','badge2',0,'',0,'2021-02-18 14:51:09',0,'aceace'),(73,203,'전 estj입니다! 이름하여 사업가형!!',11,'2021-02-18 14:51:34','badge2',1,'',0,'2021-02-18 14:51:34',0,'aceace'),(74,204,'2021년 2월 8일 베스트팀 선정~~~!!! 축하측하?????',2,'2021-02-18 15:09:40','badge1',0,'',0,'2021-02-18 15:09:40',0,'aaaaaa'),(75,205,'안녕하세요 무싸트 !!!!!!!!! 잘부탁합니다.',9,'2021-02-18 20:06:10','badge1',1,'',0,'2021-02-18 20:06:10',3,'yong1'),(76,206,'이번주 토요일 등산하실분?',2,'2021-02-19 02:14:12','badge1',0,'',0,'2021-02-19 02:14:12',0,'zenohero'),(77,198,'SSAFY 4기 화이팅!!!',16,'2021-02-19 02:43:49','badge2',1,'',0,'2021-02-19 02:43:49',1,'aceace'),(78,198,'하 발표준비랑 이제 다 끝! 끝! 끄읏으으으읏ㄹ< !!!<br>#개발',13,'2021-02-19 03:37:11','badge1',2,'#개발',0,'2021-02-19 03:37:11',2,'aaaaaa'),(79,195,'안녕하세여 자전거 은둔고수입니다 .ㅎ 잘부탁드립니다.',6,'2021-02-19 10:04:40','badge1',0,'',0,'2021-02-19 10:04:40',0,'lastday'),(80,198,'안녕하세요 제가 싸피로 2행시 한 번 해보겠습니다!<br>싸! 싸이버거 먹고싶다<br>피! 피곤하다<br><br>네 감사합니다<br>그럼 이만~',7,'2021-02-19 10:15:59','badge1',3,'',0,'2021-02-19 10:15:59',2,'bulgen'),(81,205,'새롭게 그것으로 다 같이 다가간다~!!',1,'2021-02-19 10:20:32','badge1',1,'',0,'2021-02-19 10:20:32',0,'bulgen');
/*!40000 ALTER TABLE `club_post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 16:03:20
