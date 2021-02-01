CREATE SCHEMA `udon` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;

﻿


CREATE TABLE `user` (
	`user_id`	VARCHAR(20)	NOT NULL,
	`phone_number`	INT(11)	NULL,
	`password`	VARCHAR(20)	NOT NULL,
	`created_at`	DATETIME	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`email`	VARCHAR(40)	NOT NULL,
	`is_manager`	TINYINT(1)	NULL	DEFAULT 0,
	`user_state`	INT	NULL,
	`nickname`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `group` (
	`group_id`	INT	NOT NULL AUTO_INCREMENT,
	`group_name`	VARCHAR(20)	NOT NULL,
	`member_number`	INT	NULL,
	`group_content`	VARCHAR(500)	NULL,
	`is_open`	TINYINT(1)	NOT NULL	DEFAULT 1,
	`created_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP,
	`area_code`	VARCHAR(20)	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL,
	`file_id`	INT	NOT NULL,
    PRIMARY KEY(group_id)
);

CREATE TABLE `user_badge` (
	`user_id`	VARCHAR(20)	NOT NULL,
	`badge_id`	INT	NOT NULL,
	`is_main`	TINYINT(1)	NOT NULL	DEFAULT 1
);

CREATE TABLE `group_member` (
	`user_id`	VARCHAR(20)	NOT NULL,
	`group_id`	INT	NOT NULL,
	`type`	VARCHAR(10)	NULL
);

CREATE TABLE `review` (
	`review_id`	INT	NOT NULL	AUTO_INCREMENT,
	`user_id`	VARCHAR(20)	NOT NULL,
	`store_id`	INT	NOT NULL,
	`review_content`	VARCHAR(500)	NULL,
	`views`	INT	NULL	DEFAULT 0,
	`rate`	INT	NULL,
	`created_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP,
	`review_like_count`	INT	NULL	DEFAULT 0,
    PRIMARY KEY(review_id)
);

CREATE TABLE `group_post` (
	`post_id`	INT	NOT NULL	AUTO_INCREMENT,
	`group_id`	INT	NOT NULL,
	`post_content`	VARCHAR(500)	NULL,
	`post_views`	INT	NULL	DEFAULT 0,
	`created_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP,
	`is_open`	TINYINT(1)	NULL	DEFAULT 1,
	`post_like_count`	INT	NULL	DEFAULT 0,
	`post_tag`	VARCHAR(100)	NULL,
	`is_updated`	TINYINT(1)	NULL	DEFAULT 0,
	`updated_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP,
	`post_comment_count`	INT	NULL	DEFAULT 0,
	`user_id`	VARCHAR(20)	NOT NULL,
    PRIMARY KEY(post_id)
);

CREATE TABLE `promotion` (
	`prom_id`	INT	NOT NULL	AUTO_INCREMENT,
	`user_id`	VARCHAR(20)	NOT NULL,
	`area_code`	INT	NOT NULL,
	`prom_title`	VARCHAR(50)	NULL,
	`prom_content`	VARCHAR(1000)	NULL,
	`views`	INT(10)	NULL	DEFAULT 0,
	`created_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP,
	`importance`	INT	NULL,
    PRIMARY KEY(prom_id)
);

CREATE TABLE `prom_comment` (
	`comment_id`	INT	NOT NULL,
	`prom_id`	INT	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL,
	`comm_content`	VARCHAR(500)	NULL,
	`created_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `image` (
	`file_id`	INT	NOT NULL	AUTO_INCREMENT,
	`file_name`	VARCHAR(100)	NOT NULL,
	`file_size`	VARCHAR(100)	NOT NULL,
	`file_contentType`	VARCHAR(1000)	NOT NULL,
	`file_url`	VARCHAR(1000)	NOT NULL,
    PRIMARY KEY(file_id)
);

CREATE TABLE `group_post_like` (
	`id`	INT	NOT NULL	AUTO_INCREMENT,
	`user_id`	VARCHAR(20)	NOT NULL,
	`post_id`	INT	NOT NULL,
	`group_id`	INT	NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE `group_post_comment` (
	`comment_id`	INT	NOT NULL	AUTO_INCREMENT,
	`post_id`	INT	NOT NULL,
	`group_id`	INT	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL,
	`comm_content`	VARCHAR(500)	NULL,
	`created_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP,
	`comm_like_count`	INT	NULL	DEFAULT 0,
    PRIMARY KEY(comment_id)
);

CREATE TABLE `group_post_file` (
	`post_id`	INT	NOT NULL,
	`group_id`	INT	NOT NULL,
	`file_id`	INT	NOT NULL
);

CREATE TABLE `prom_file` (
	`prom_id`	INT	NOT NULL,
	`file_id`	INT	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `report_comment` (
	`user_id`	VARCHAR(20)	NOT NULL,
	`comment_id`	INT	NOT NULL,
	`content`	VARCHAR(500)	NULL,
	`category`	VARCHAR(20)	NULL,
	`prom_id`	INT	NOT NULL
);

CREATE TABLE `store` (
	`store_id`	INT	NOT NULL,
	`loc_lat`	INT(20)	NULL,
	`loc_lng`	INT(20)	NULL,
	`store_ctg_1`	VARCHAR(10)	NULL,
	`store_ctg_2`	VARCHAR(20)	NULL,
	`store_dong_name`	VARCHAR(20)	NULL,
	`store_dong2_name`	VARCHAR(20)	NULL,
	`store_sido_name`	VARCHAR(20)	NULL,
	`store_gugun_name`	VARCHAR(20)	NULL,
	`store_dong_code`	INT(20)	NULL,
	`store_dong2_code`	INT(20)	NULL,
	`store_sido_code`	INT(20)	NULL,
	`store_gugun_code`	INT(10)	NULL,
	`store_name`	VARCHAR(20)	NULL,
	`store_addr`	VARCHAR(200)	NULL,
	`store_name_detail`	VARCHAR(20)	NULL,
	`store_state`	INT	NULL,
	`created_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `review_file` (
	`review_id`	INT	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL,
	`store_id`	INT	NOT NULL,
	`file_id`	INT	NOT NULL,
	`rank`	INT	NOT NULL	
);

CREATE TABLE `review_like` (
	`review_id`	INT	NOT NULL,
	`store_id`	INT	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `group_post_comment_like` (
	`id`	INT	NOT NULL	AUTO_INCREMENT,
	`comment_id`	INT	NOT NULL,
	`post_id`	INT	NOT NULL,
	`group_id`	INT	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL,
    PRIMARY KEY(id)
    
);

CREATE TABLE `user_address` (
	`user_address_id`	INT	NOT NULL	AUTO_INCREMENT,
	`user_id`	VARCHAR(20)	NOT NULL,
	`region_3depth_name`	VARCHAR(20)	NULL,
	`user_address_code`	VARCHAR(20)	NULL,
	`updated_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP,
	`region_1depth_name`	VARCHAR(20)	NULL,
	`region_2depth_name`	VARCHAR(20)	NULL,
	`region_4depth_name`	VARCHAR(20)	NULL,
	`region_type`	VARCHAR(2)	NULL,
	`is_main`	TINYINT	NULL,
    PRIMARY KEY(user_address_id)
    
);

CREATE TABLE `group_member_waiting` (
	`group_id`	INT	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL,
	`content`	VARCHAR(500)	NULL,
	`requested_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `user_post` (
	`post_id`	INT	NOT NULL	AUTO_INCREMENT,
	`user_id`	VARCHAR(20)	NOT NULL,
	`post_content`	VARCHAR(500)	NULL,
	`post_views`	INT	NULL	DEFAULT 0,
	`created_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP,
	`is_open`	TINYINT(1)	NULL	DEFAULT 0,
	`post_like_count`	INT	NULL	DEFAULT 0,
	`post_tag`	VARCHAR(100)	NULL,
	`is_updated`	INT	NULL	DEFAULT 0,
	`updated_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP,
	`post_comment_count`	INT	NULL	DEFAULT 0,
    PRIMARY KEY(post_id)
);

CREATE TABLE `user_post_comment` (
	`comment_id`	INT	NOT NULL	AUTO_INCREMENT,
	`post_id`	INT	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL,
	`comm_content`	VARCHAR(500)	NULL,
	`created_at`	DATETIME	NULL	DEFAULT CURRENT_TIMESTAMP,
	`comm_like_count`	INT	NULL	DEFAULT 0,
    PRIMARY KEY(comment_id)
);

CREATE TABLE `user_post_comment_like` (
	`comment_id`	INT	NOT NULL,
	`post_id`	INT	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `group_post_report` (
	`user_id`	VARCHAR(20)	NOT NULL,
	`post_id`	INT	NOT NULL,
	`content`	VARCHAR(500)	NULL,
	`category`	VARCHAR(20)	NULL,
	`group_id`	INT	NOT NULL
);

CREATE TABLE `user_post_report` (
	`user_id`	VARCHAR(20)	NOT NULL,
	`content`	VARCHAR(500)	NULL,
	`category`	VARCHAR(20)	NULL,
	`post_id`	INT	NOT NULL
);

CREATE TABLE `user_comment_report` (
	`user_id`	VARCHAR(20)	NOT NULL,
	`content`	VARCHAR(500)	NULL,
	`category`	VARCHAR(20)	NULL,
	`comment_id`	INT	NOT NULL,
	`post_id`	INT	NOT NULL
);

CREATE TABLE `group_post_comment_report` (
	`user_id`	VARCHAR(20)	NOT NULL,
	`content`	VARCHAR(500)	NULL,
	`category`	VARCHAR(20)	NULL,
	`comment_id`	INT	NOT NULL,
	`group_id`	INT	NOT NULL,
	`post_id`	INT	NOT NULL
);

CREATE TABLE `review_report` (
	`user_id`	VARCHAR(20)	NOT NULL,
	`content`	VARCHAR(500)	NULL,
	`category`	VARCHAR(20)	NULL,
	`review_id`	INT	NOT NULL,
	`store_id`	INT	NOT NULL
);

CREATE TABLE `user_post_file` (
	`post_id`	INT	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL,
	`file_id`	INT	NOT NULL
);

CREATE TABLE `user_post_like` (
	`id`	INT	NOT NULL	AUTO_INCREMENT,
	`user_id`	VARCHAR(20)	NOT NULL,
	`post_id`	INT	NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE `badge` (
	`badge_id`	INT	NOT NULL	AUTO_INCREMENT,
	`badge_name`	VARCHAR(20)	NOT NULL,
	`content`	VARCHAR(200)	NOT NULL,
	`badge_url`	VARCHAR(200)	NOT NULL,
    PRIMARY KEY(badge_id)
);

CREATE TABLE `store_temp` (
	`store_id`	INT	NOT NULL,
	`loc_lat`	INT(20)	NULL,
	`loc_lng`	INT(20)	NULL,
	`store_ctg_1`	VARCHAR(10)	NULL,
	`store_ctg_2`	VARCHAR(20)	NULL,
	`store_dong_name`	VARCHAR(20)	NULL,
	`store_dong2_name`	VARCHAR(20)	NULL,
	`store_sido_name`	VARCHAR(20)	NULL,
	`store_gugun_name`	VARCHAR(20)	NULL,
	`store_dong_code`	INT(20)	NULL,
	`store_dong2_code`	INT(20)	NULL,
	`store_sido_code`	INT(20)	NULL,
	`store_gugun_code`	INT(10)	NULL,
	`store_name`	VARCHAR(20)	NULL,
	`store_addr`	VARCHAR(200)	NULL,
	`store_name_detail`	VARCHAR(20)	NULL,
	`store_state`	INT	NULL
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`user_id`
);


ALTER TABLE `user_badge` ADD CONSTRAINT `PK_USER_BADGE` PRIMARY KEY (
	`user_id`,
	`badge_id`
);

ALTER TABLE `group_member` ADD CONSTRAINT `PK_GROUP_MEMBER` PRIMARY KEY (
	`user_id`,
	`group_id`
);




ALTER TABLE `prom_comment` ADD CONSTRAINT `PK_PROM_COMMENT` PRIMARY KEY (
	`comment_id`,
	`prom_id`,
	`user_id`
);



ALTER TABLE `group_post_file` ADD CONSTRAINT `PK_GROUP_POST_FILE` PRIMARY KEY (
	`post_id`,
	`group_id`,
	`file_id`
);

ALTER TABLE `prom_file` ADD CONSTRAINT `PK_PROM_FILE` PRIMARY KEY (
	`prom_id`,
	`file_id`,
	`user_id`
);

ALTER TABLE `report_comment` ADD CONSTRAINT `PK_REPORT_COMMENT` PRIMARY KEY (
	`user_id`
);

ALTER TABLE `store` ADD CONSTRAINT `PK_STORE` PRIMARY KEY (
	`store_id`
);

ALTER TABLE `review_file` ADD CONSTRAINT `PK_REVIEW_FILE` PRIMARY KEY (
	`review_id`,
	`user_id`,
	`store_id`,
	`file_id`
);

ALTER TABLE `review_like` ADD CONSTRAINT `PK_REVIEW_LIKE` PRIMARY KEY (
	`review_id`,
	`store_id`,
	`user_id`
);



ALTER TABLE `group_member_waiting` ADD CONSTRAINT `PK_GROUP_MEMBER_WAITING` PRIMARY KEY (
	`group_id`,
	`user_id`
);


ALTER TABLE `user_post_comment_like` ADD CONSTRAINT `PK_USER_POST_COMMENT_LIKE` PRIMARY KEY (
	`comment_id`,
	`post_id`,
	`user_id`
);

ALTER TABLE `group_post_report` ADD CONSTRAINT `PK_GROUP_POST_REPORT` PRIMARY KEY (
	`user_id`
);


ALTER TABLE `user_comment_report` ADD CONSTRAINT `PK_USER_COMMENT_REPORT` PRIMARY KEY (
	`user_id`
);

ALTER TABLE `group_post_comment_report` ADD CONSTRAINT `PK_GROUP_POST_COMMENT_REPORT` PRIMARY KEY (
	`user_id`
);

ALTER TABLE `review_report` ADD CONSTRAINT `PK_REVIEW_REPORT` PRIMARY KEY (
	`user_id`
);

ALTER TABLE `user_post_file` ADD CONSTRAINT `PK_USER_POST_FILE` PRIMARY KEY (
	`post_id`,
	`user_id`,
	`file_id`
);

ALTER TABLE `store_temp` ADD CONSTRAINT `PK_STORE_TEMP` PRIMARY KEY (
	`store_id`
);

