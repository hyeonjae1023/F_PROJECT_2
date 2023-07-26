/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 10.4.28-MariaDB : Database - my_first_project
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my_first_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `my_first_project`;

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `loginId` char(100) NOT NULL,
  `Email` char(100) NOT NULL,
  `nickName` char(100) NOT NULL,
  `loginPw` char(100) NOT NULL,
  `name` char(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `nickName` (`nickName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `member` */

insert  into `member`(`id`,`regDate`,`updateDate`,`loginId`,`Email`,`nickName`,`loginPw`,`name`) values 
(1,'2023-07-26 10:07:30','2023-07-26 10:22:45','admin','admin','admin','opp','관리자'),
(2,'2023-07-26 10:07:30','2023-07-26 10:22:45','user1','user1','홍길동','opp','신동우'),
(3,'2023-07-26 10:10:00','2023-07-26 10:22:45','oi','oe','onn','opp','on');

/*Table structure for table `movieArticle` */

DROP TABLE IF EXISTS `movieArticle`;

CREATE TABLE `movieArticle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `title` char(100) NOT NULL,
  `memberId` int(1) unsigned NOT NULL,
  `boardId` int(1) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `boardId` (`boardId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `movieArticle` */

insert  into `movieArticle`(`id`,`regDate`,`title`,`memberId`,`boardId`) values 
(1,'2023-05-31 00:00:00','범죄도시3',1,1),
(2,'2023-06-14 00:00:00','엘리멘탈',2,2),
(3,'2023-06-28 00:00:00','여름날 우리',3,3),
(4,'2023-07-12 00:00:00','미션 임파서블 : 데드 레코닝',4,4),
(5,'2023-07-19 00:00:00','인시디어스 : 빨간 문',5,5),
(6,'2023-07-20 00:00:00','명탐정 코난 : 흑철의 어영',6,6);

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `title` char(100) NOT NULL,
  `body` char(100) NOT NULL,
  `name` char(100) NOT NULL,
  `grades` float(10,1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `review` */

insert  into `review`(`id`,`regDate`,`updateDate`,`title`,`body`,`name`,`grades`) values 
(1,'2023-07-26 10:07:30','2023-07-26 10:07:30','범죄도시3','오늘 범죄도시3 보고 왔는데, 너무 재밌었어요','홍길동',4.8),
(2,'2023-07-26 10:07:30','2023-07-26 10:07:30','엘리멘탈','엘리멘탈 진짜 최고ㅠㅠㅠㅠ','홍길동',1.5),
(3,'2023-07-26 10:07:30','2023-07-26 10:07:30','범죄도시3','노잼ㅠㅠㅠㅠ','admin',5.0),
(4,'2023-07-26 10:07:30','2023-07-26 10:07:30','여름날 우리','난 겨울이 조은뎅ㅠㅠㅠㅠ','신동우',3.7),
(5,'2023-07-26 10:07:30','2023-07-26 10:07:30','엘리멘탈','캬캬캬ㅠㅠㅠㅠ','admin',4.1);

/*Table structure for table `seats` */

DROP TABLE IF EXISTS `seats`;

CREATE TABLE `seats` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `movieTitle` char(100) NOT NULL,
  `title` char(100) NOT NULL,
  `nickName` char(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `seats` */

insert  into `seats`(`id`,`regDate`,`movieTitle`,`title`,`nickName`) values 
(1,'2023-07-26 10:07:30','범죄도시3','A1','홍길동'),
(2,'2023-07-26 10:07:30','엘리멘탈','A2','홍길동'),
(3,'2023-07-26 10:07:30','여름날 우리','A3','홍길순'),
(4,'2023-07-26 10:07:30','범죄도시3','A4','홍길순'),
(5,'2023-07-26 10:11:07','여름날 우리','J13','onn'),
(6,'2023-07-26 10:11:07','여름날 우리','J14','onn'),
(7,'2023-07-26 10:13:06','여름날 우리','J11','onn'),
(8,'2023-07-26 10:13:06','여름날 우리','J12','onn');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
