CREATE DATABASE db_organizeme CHARACTER SET utf8;

GRANT ALL PRIVILEGES ON db_organizeme.* TO 'organizeme'@'%' IDENTIFIED BY 'organizeme';

-- This is redundant with the above.
GRANT ALL PRIVILEGES ON db_organizeme.* TO 'organizeme'@'localhost' IDENTIFIED BY 'organizeme';

FLUSH PRIVILEGES;

USE db_organizeme;

INSERT INTO `tb_user` VALUES (1,'1234',0,'admin'),(2,'1234',1,'izeye'),(3,'1234',1,'always19'),(4,'1234',1,'guest');

INSERT INTO
	`tb_category`
VALUES
	(1,'Computer',NULL),
	(2,'Java',1),
	(3,'English',NULL),
	(4,'RequireJS',6),
	(5,'Technical Debt',1),
	(6,'JavaScript',1),
	(7,'Ajax',6),
	(8,'Math',NULL),
	(9,'TED',NULL),
	(10,'CommonJS',6),
	(11,'Spring',2),
	(12,'Spring Boot',11),
	(13,'Logback',2),
	(14,'Tomcat',2),
	(15,'Servlet',2),
	(16,'JSP',2),
	(17,'Misc',NULL),
	(18,'Developer',2),
	(19,'Startup',NULL),
	(20,'Health',NULL),
	(21,'Programming',1),
	(22,'Architectural Pattern',1);
