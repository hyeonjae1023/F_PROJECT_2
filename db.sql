# 데이터베이스 삭제/생성/선택
DROP DATABASE IF EXISTS my_first_project;
CREATE DATABASE my_first_project;

USE my_first_project;

DROP TABLE IF EXISTS movieArticle;

CREATE TABLE movieArticle (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` CHAR(100) NOT NULL,
	price INT UNSIGNED NOT NULL
);

SELECT * FROM movieArticle;

INSERT INTO movieArticle
SET regDate = '2023-05-31',
title = '범죄도시3',
`body` = '범죄 잡는 마동석',
price = '15000';

INSERT INTO movieArticle
SET regDate = '2023-06-14',
title = '엘리멘탈',
`body` = '4원소들의 열정 넘치는 스토리',
price = '15000';

INSERT INTO movieArticle
SET regDate = '2023-06-28',
title = '여름날 우리',
`body` = '너에게 풍덩 빠져버렸던 17살 여름',
price = '15000';

INSERT INTO movieArticle
SET regDate = '2023-07-12',
title = '미션 임파서블 : 데드 레코닝',
`body` = '가장 위험한 작전!',
price = '15000';

INSERT INTO movieArticle
SET regDate = '2023-07-19',
title = '인시디어스 : 빨간 문',
`body` = '램버트 가족이 다시 겪게 되는 끔찍한 악몽 이야기',
price = '15000';

INSERT INTO movieArticle
SET regDate = '2023-07-20',
title = '명탐정 코난 : 흑철의 어영',
`body` = '믿고 보는 코난',
price = '15000';

SELECT * FROM movieArticle;

CREATE TABLE `member` (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(100) NOT NULL UNIQUE,
	Email CHAR(100) NOT NULL UNIQUE,
	nickName CHAR(100) NOT NULL UNIQUE,
	loginPw CHAR(100) NOT NULL,
	`name` CHAR(100) NOT NULL
);

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
Email = 'admin',
nickName = 'admin',
`name` = '관리자';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
Email = 'user1',
nickName = '홍길동',
`name` = '신동우';

SELECT * FROM `member`;

CREATE TABLE review (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` CHAR(100) NOT NULL,
	`name` CHAR(100) NOT NULL,
	grades FLOAT(10, 1) NOT NULL
);

INSERT INTO review
SET regDate = NOW(),
updateDate = NOW(),
title = '범죄도시3',
`body` = '오늘 범죄도시3 보고 왔는데, 너무 재밌었어요',
`name` = '홍길동',
grades = 4.8;

INSERT INTO review
SET regDate = NOW(),
updateDate = NOW(),
title = '엘리멘탈',
`body` = '엘리멘탈 진짜 최고ㅠㅠㅠㅠ',
`name` = '홍길동',
grades = 1.5;

INSERT INTO review
SET regDate = NOW(),
updateDate = NOW(),
title = '범죄도시3',
`body` = '노잼ㅠㅠㅠㅠ',
`name` = 'admin',
grades = 5.0;

INSERT INTO review
SET regDate = NOW(),
updateDate = NOW(),
title = '여름날 우리',
`body` = '난 겨울이 조은뎅ㅠㅠㅠㅠ',
`name` = '신동우',
grades = 3.7;

INSERT INTO review
SET regDate = NOW(),
updateDate = NOW(),
title = '엘리멘탈',
`body` = '캬캬캬ㅠㅠㅠㅠ',
`name` = 'admin',
grades = 4.1;


SELECT * FROM review;

DROP TABLE IF EXISTS seats;

CREATE TABLE seats (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	movieTitle CHAR(100) NOT NULL,
	title CHAR(100) NOT NULL,
	nickName CHAR(100) NOT NULL
);

INSERT INTO seats
SET regDate = NOW(),
movieTitle = '범죄도시3',
title = 'A1',
nickName = '홍길동';

INSERT INTO seats
SET regDate = NOW(),
movieTitle = '엘리멘탈',
title = 'A2',
nickName = '홍길동';

INSERT INTO seats
SET regDate = NOW(),
movieTitle = '여름날 우리',
title = 'A3',
nickName = '홍길순';

INSERT INTO seats
SET regDate = NOW(),
movieTitle = '범죄도시3',
title = 'A4',
nickName = '홍길순';

SELECT * FROM seats;